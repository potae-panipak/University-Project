
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

class my_Queen {

    Scanner scan;
    int chessboard = 0;
    int fixedRow, fixedColumn;

    my_Queen() {
        scan = new Scanner(System.in);
        boolean check = false;
        while (true) {
            System.out.print("Enter N (at least 4) = ");
            while (!check) {
                try {
                    chessboard = scan.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please enter N as integer!!");
                    scan.next();
                    System.out.print("Enter N (at least 4) = ");
                    continue;
                }
                if (chessboard < 4) {
                    System.out.println("Please enter N at least 4!!");
                    System.out.print("Enter N (at least 4) = ");
                } else {
                    check = true;
                }
            } 
            String s;
            while (true) {
                createQueen(chessboard);
                System.out.print("Enter y to continue with the same n = ");
                s = scan.next();
                if (s.compareToIgnoreCase("y") != 0) {
                    break;
                }
            }
            System.out.print("Enter y to continue with another n = ");
            s = scan.next();
            if (s.compareToIgnoreCase("y") != 0) {
                break;
            }
            check = false;
        }
        System.out.println("===================ThankYou GoodBye===================");

    }

    public void solve_Queen(int n, int row, int column) {
        Stack<Integer> myStack = new Stack<Integer>();
        fixedRow = row;
        fixedColumn = column;
        int current_Column = 0;
        while (true) {
            if (myStack.size() == n) {
                break;
            }
            if (current_Column == n) {
                if (myStack.isEmpty()) {
                    showChessboard(n);
                    System.out.println("No Solution !!!");
                    break;
                }
                if ((myStack.peek() == n) && (myStack.size() == 1)) {
                    break;
                }
                if (myStack.peek() == n) {
                    myStack.pop();
                    if (myStack.size() == fixedRow + 1) {
                        myStack.pop();
                        if (fixedRow == 0 && myStack.isEmpty()) {
                            showChessboard(n);
                            System.out.println("No Solution !!!");
                            break;
                        }
                    }
                    current_Column = myStack.pop() + 1;
                } else {
                    if (myStack.size() == fixedRow + 1) {
                        myStack.pop();
                        if (fixedRow == 0 && myStack.isEmpty()) {
                            showChessboard(n);
                            System.out.println("No Solution !!!");
                            break;
                        }
                    }
                    current_Column = myStack.pop() + 1;
                }
            } else if (myStack.size() == fixedRow) {
                myStack.push(fixedColumn);
                current_Column = 0;
            } else if (check_Position_IsPlaceable(myStack, current_Column)) {
                myStack.push(current_Column);
                current_Column = 0;
            } else {
                current_Column++;
            }
        }
        if (myStack.size() == n) {
            showChessboard(n);
            System.out.println("======================Solution======================");
            printSolution(myStack);
        }
    }

    public boolean check_Position_IsPlaceable(Stack<Integer> s, int current_Column_Position) {
        for (int i = 0; i < s.size(); i++) {
            if (s.get(i) == current_Column_Position) {
                return false;
            }
            if ((s.get(i) - current_Column_Position) == (s.size() - i)) {
                return false;
            }
            if ((current_Column_Position - s.get(i)) == (s.size() - i)) {
                return false;
            }
            if (fixedColumn == current_Column_Position) {
                return false;
            }
            if ((fixedColumn - current_Column_Position) == (s.size() - fixedRow)) {
                return false;
            }
            if ((current_Column_Position - fixedColumn) == (s.size() - fixedRow)) {
                return false;
            }
        }
        if (s.isEmpty()) {
            if (fixedColumn == current_Column_Position) {
                return false;
            }
            if ((fixedColumn - current_Column_Position) == (s.size() - fixedRow)) {
                return false;
            }
            if ((current_Column_Position - fixedColumn) == (s.size() - fixedRow)) {
                return false;
            }
        }
        return true;
    }

    private static void printSolution(Stack<Integer> s) {
        System.out.print("    ");
        for (int a = 0; a < s.size(); a++) {
            System.out.printf("%-3d ", a + 1);
        }
        System.out.println();
        for (int i = 0; i < s.size(); i++) {
            System.out.printf("%-3d ", i + 1);
            for (int j = 0; j < s.size(); j++) {
                if (j == s.get(i)) {
                    System.out.print("Q   ");
                } else {
                    System.out.print("_   ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void showChessboard(int n) {
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("    ");
                } else if (i == 0) {
                    System.out.printf("%-4d", j);
                } else if (j == 0) {
                    System.out.printf("%-3d ", i);
                } else if (i == fixedRow + 1 && j == fixedColumn + 1) {
                    System.out.print("Q   ");
                } else {
                    System.out.print("_   ");
                }
            }
            System.out.println();
        }
    }

    private void showBoardLayout(int n) {
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("    ");
                } else if (i == 0) {
                    System.out.printf("%-4d", j);
                } else if (j == 0) {
                    System.out.printf("%-3d ", i);
                } else {
                    System.out.print("_   ");
                }
            }
            System.out.println();
        }
    }

    private void createQueen(int board) {
        boolean checkRow = false, checkCol = false;
        Scanner scan = new Scanner(System.in);
        int row = 0, column = 0;
        System.out.println("Board layout");
        showBoardLayout(chessboard);
        System.out.print("Enter row of first queen : ");
        while (!checkRow) {
            try {
                row = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter row as interger!!");
                scan.next();
                System.out.print("Enter row of first queen : ");
                continue;
            }
            if (row > board) {
                System.out.println("Please enter row, not exceeed N !!!!!!");
                System.out.print("Enter row of first queen : ");
            } else if (row <= 0) {
                System.out.println("Please enter row with positive integer!!!!!!");
                System.out.print("Enter row of first queen : ");
            } else {
                checkRow = true;
            }
        }

        System.out.print("Enter column of first queen : ");
        while (!checkCol) {
            try {
                column = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter column as integer!!");
                scan.next();
                System.out.print("Enter column of first queen : ");
                continue;
            }
            if (column > board) {
                System.out.println("Please enter column, not exceed N !!!!!!");
                System.out.print("Enter col of first queen : ");
            } else if (column <= 0) {
                System.out.println("Please enter column with positive integer!!!!!!");
                System.out.print("Enter column of first queen : ");
            } else {
                checkCol = true;
            }
        }
        solve_Queen(board, row - 1, column - 1);
    }

}

public class n_Queen_Problem {

    public static void main(String[] args) {
        new my_Queen();
    }
}
