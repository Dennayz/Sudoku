package Sudoku;

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
	private static boolean isValid(int row, int col, int num) {
		//check row
		for (int i = 0; i < sudokuBoard[0].length; i++) {
			if (sudokuBoard[row][i] == num) {
				return false;
			}
		}

		//check column
		for (int j = 0; j < sudokuBoard.length; j++) {
			if (sudokuBoard[j][col] == num) {
				return false;
			}
		}

		//check if in 3x3 box (9 cubes)
		int boxRow = row - row % 3;
		int boxCol = col - col % 3;
		
		for (int i = boxRow; i < boxRow + 3; i++) {
			for (int j = boxCol; j < boxCol + 3; j++) {
				if (sudokuBoard[i][j] == num) {
					return false;
				}
			}
		}
		return true;

	}


	public static boolean solve() {
		for (int i = 0; i < sudokuBoard.length; i++) {
			for (int j = 0; j < sudokuBoard[0].length; j++) {
				if (sudokuBoard[i][j] == 0) {
					//try numbers 1 - 9
					for (int number = 1; number <= sudokuBoard.length; number++) {
						//if it is valid position, set it on board then backtrack
						if (isValid(i, j, number)) {
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