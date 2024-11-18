import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] board = new char[9][9];

        System.out.println("Enter the Sudoku board row by row (use '.' for empty cells) ans enter each rows in string format dont give space:");
        for (int i = 0; i < 9; i++) {
            String row = sc.nextLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = row.charAt(j);
            }
        }

        System.out.println("\nInput Sudoku Board:");
        printBoard(board);

        Solution solution = new Solution();
        solution.solveSudoku(board);

        System.out.println("\nSolved Sudoku Board:");
        printBoard(board);
    }

    private static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}



class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') { // Find an empty cell
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(board, row, col, num)) { // Check if num is valid
                            board[row][col] = num; // Place num
                            if (solve(board)) { // Recursively solve for next cell
                                return true;
                            }
                            board[row][col] = '.'; // Backtrack
                        }
                    }
                    return false; // No valid number can be placed
                }
            }
        }
        return true; // Solved
    }

    private boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            // Check row
            if (board[row][i] == num) return false;
            // Check column
            if (board[i][col] == num) return false;
            // Check 3x3 sub-box
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == num) {
                return false;
            }
        }
        return true;
    }
}


