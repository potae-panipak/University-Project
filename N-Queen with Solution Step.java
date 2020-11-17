
package n_queen;

import java.util.*;

/**
 *
 * @author user
 */
public class N_queen {

    private static Scanner scanRandC = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int gridNumber = 0;
        boolean check = false;
        System.out.print("Enter N (at least 4) = ");
        while (!check) {
            try {
                gridNumber = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please Enter N as Interger!!");
                scan.next();
                System.out.print("Enter N (at least 4) = ");
                continue;
            }
            if (checkBoard(gridNumber)) {
                System.out.println("Please Enter N at least 4!!");
                System.out.print("Enter N (at least 4) = ");
            } else {
                placeQueens(gridNumber);
                check = true;
            }
        }
    }

    private static boolean checkBoard(int gridSize) {
        if (gridSize < 4) {
            return true;
        } else {
            return false;
        }
    }

    private static void placeQueens(int gridSize) {
        int[][] board = new int[gridSize][gridSize];
        String checkContinue;
        int row, column;
        System.out.println("Board Layout");
        printBoard(board);

        while (true) {
            while (true) {
                try {
                    System.out.print("Enter row of first Queen = ");
                    row = scanRandC.nextInt();
                    System.out.print("Enter column of first Queen = ");
                    column = scanRandC.nextInt();
                    if (row <= 0 || column <= 0 || row > gridSize || column > gridSize) {
                        System.out.println("Please Insert row and column with in range 1 to gridsize");
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Please Enter row and column as interger");
                    scanRandC.next();
                }
            }

            board[row - 1][column - 1] = 1;
            printBoard(board);

            if (placeAllQueens(board, 0, row - 1, column - 1) == false) {
                System.out.println("No Soulution");
            } else {
                System.out.printf("===================================== \n Solution:\n");
                printBoard(board);
            }
            System.out.print("Enter y to continue with the same n = ");
            checkContinue = scanRandC.next();
            if (!checkContinue.equalsIgnoreCase("y")) {
                break;
            }
            resetBoard(board);
        }
    }

    private static boolean placeAllQueens(int board[][], int row, int selectedRow, int selectedColumn) {
        if (row >= board.length) {
            return true;
        }
        boolean isAllQueensPlaced = false;
        for (int j = 0; j < board.length; j++) {
            if (selectedRow == 0 && row == 0) {
                if (j != selectedColumn) {
                    continue;
                }
            }
            if (isSafe(board, row, j)) {
                board[row][j] = 1;
                if (selectedRow == row + 1) {
                    if (isSafe(board, selectedRow, selectedColumn)) {
                        isAllQueensPlaced = placeAllQueens(board, row + 2, selectedRow, selectedColumn);
                    } else {
                        isAllQueensPlaced = false;
                    }
                } else {
                    isAllQueensPlaced = placeAllQueens(board, row + 1, selectedRow, selectedColumn);
                }
            }
            if (isAllQueensPlaced) {
                break;
            } else {
                board[row][j] = 0;
            }
        }
        return isAllQueensPlaced;
    }

    private static boolean isSafe(int board[][], int row, int col) {

        //Left Upper Diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        //Right Upper Diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        //same Column
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        return true;
    }

    private static void printBoard(int[][] board) {
        System.out.print("       ");
        for (int row = 0; row < board.length; row++) {
            if (row == 0) {
                for (int i = 1; i <= board.length; i++) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
            System.out.print("     ");
            System.out.print((row + 1) + " ");
            for (int col = 0; col < board.length; col++) {
                if (board[row][col] == 1) {
                    System.out.print("Q ");
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
        System.out.println("");
    }

    private static void resetBoard(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                board[row][column] = 0;
            }
        }
    }

}
