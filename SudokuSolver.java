
public class SudokuSolver {
	//input any sudoku board
	public static int[][] sudokuBoard = {
										  {7,8,0,4,0,0,1,2,0},
										  {6,0,0,0,7,5,0,0,9},
										  {0,0,0,6,0,1,0,7,8},
										  {0,0,7,0,4,0,2,6,0},
										  {0,0,1,0,5,0,9,3,0},
										  {9,0,4,0,6,0,0,0,5},
										  {0,7,0,3,0,0,0,1,2},
										  {1,2,0,0,0,7,4,0,0},
										  {0,4,9,2,0,6,0,0,7}
										};

	public static void printBoard() {
		//split into 9 cubes like actual Sudoku board
		for (int i = 0; i < sudokuBoard.length; i++) {   
			if (((i % 3) == 0) && (i != 0)) {
				System.out.println("--------------------------");
			}
			for(int j = 0 ; j < sudokuBoard[0].length; j++) {
				if (((j % 3) == 0) && (j != 0)) {
					//using System.out.print() to stay on the same line
					System.out.print(" | " + " ");
				}
				if (j == 8) {
					System.out.println(sudokuBoard[i][j]);
				}
				else {
					//using System.out.print() to stay on the same line
					System.out.print(sudokuBoard[i][j] + " ");
				}
			}

		}
	}

	private static boolean validForRow(int row, int num) {
		for (int i = 0; i < sudokuBoard[0].length; i++) {
			if (sudokuBoard[row][i] == num) {
				return true;
			}
		}
		return false;
	}

	private static boolean validForCol(int col, int num) {
		for (int i = 0; i < sudokuBoard.length; i++) {
			if (sudokuBoard[i][col] == num) {
				return true;
			}
		}
		return false;
	}

	private static boolean validForBox(int row, int col, int num) {
		int boxRow = row - row % 3;
		int boxCol = col - col % 3;
		
		for (int i = boxRow; i < boxRow + 3; i++) {
			for (int j = boxCol; j < boxCol + 3; j++) {
				if (sudokuBoard[i][j] == num) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isOkay(int row, int col, int num) {
		return !validForRow(row, num) && !validForCol(col, num) && !validForBox(row, col, num);
	}


	public static boolean solve() {
		for (int i = 0; i < sudokuBoard.length; i++) {
			for (int j = 0; j < sudokuBoard[0].length; j++) {
				if (sudokuBoard[i][j] == 0) {
					for (int number = 1; number <= sudokuBoard.length; number++) {
						if (isOkay(i, j, number)) {
							sudokuBoard[i][j] = number;
							if (solve()) {
								return true;
							}
							else {
								sudokuBoard[i][j] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println("Time to solve Sudoku!");
		System.out.println("Here is a board");
		System.out.println();
		printBoard();
		System.out.println();
		System.out.println("------------------------------");
		System.out.println();
		boolean solvedOrNot = solve();
		System.out.println("Starting to solve...");
		if (solvedOrNot) {
			System.out.println("Solved!");
			printBoard();
		}
		else {
			 System.out.println("Unsolvable");
			 printBoard();
		}
	}
}