package sudoku;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BasicStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class TextGrid extends JPanel {
    SudokuSolver newSolver = new SudokuSolver();
    JFrame frame = new JFrame();
    int t = 9;

    public TextGrid() {
        showIntro();
        frame.setContentPane(this);
        frame.setResizable(false);
        frame.setTitle("Sudoku Solver Game");
        setLayout(new GridLayout(t,t,0,0));
        drawTextField(t);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.BLACK);
        for (int i = 0; i <= newSolver.sudokuBoard.length; i++) {
            for (int j = 0; j <= newSolver.sudokuBoard.length; j++) {
                if (i % 3 == 0) {
                    g2.setStroke(new BasicStroke(7));
                    int coordx = getWidth() * i / 9;
                    int coordy = getHeight() * i / 9;
                    g.drawLine(coordx,0,coordx,getHeight());
                    g.drawLine(0,coordy,getWidth(),coordy);
                }
                else if (j % 3 != 0) {
                    g2.setStroke(new BasicStroke(4));
                    int cubex = getWidth() * i / 9;
                    int cubey = getHeight() * j / 9;
                    g.drawLine(cubex,0,cubex,getHeight());
                    g.drawLine(0,cubey,getWidth(),cubey);
                }
            }
        }

    }

    private void drawTextField(int t) {
        JTextField grid;
        for (int y = 0; y < t; y++) {
            for (int x = 0; x < t; x++) {
                if (newSolver.sudokuBoard[y][x] == 0) {
                    grid = new JTextField();
                    JTextField finalGrid = grid;
                    JTextField finalGrid1 = grid;
                    int finalY = y;
                    int finalX = x;
                    grid.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String grabText = finalGrid1.getText();
                            finalGrid1.setText(grabText);
                            if (!newSolver.isValid(finalY, finalX,Integer.parseInt(grabText))) {
                                JOptionPane.showMessageDialog(null, "are you sure?", "Error message", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                    grid.addKeyListener(new KeyAdapter() {
                        public void keyTyped(KeyEvent e) {
                            char checkInt = e.getKeyChar();
                            if (finalGrid.getText().length() > 0 || !Character.isDigit(checkInt)) // limit textfield to 1 number
                                e.consume();
                        }
                    });
                }
                else {
                    grid = new JTextField(Integer.toString(newSolver.sudokuBoard[y][x]));
                    grid.setEditable(false);
                }
                grid.setHorizontalAlignment(JTextField.CENTER);
                grid.setFont(new Font("Arial", Font.PLAIN, 25));
                grid.setPreferredSize(new Dimension(55, 55));
                add(grid);
            }
        }
    }
    public void showIntro() {
        JLabel label = new JLabel("<html><center>This is a very raw and simple Sudoku GUI Design<br>" +
                "The game will tell you if you have made a wrong move after pressing enter on it<br>" +
                "If you manage to get all the correct numbers on to the grid with no errors showing up for each input then you have solved the Sudoku!");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JOptionPane.showMessageDialog(null, label, "InfoBox: " + "PLEASE READ ME", JOptionPane.DEFAULT_OPTION);
    }

}
