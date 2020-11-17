package knightpath;

/**
 * @author Tae
 */
import java.util.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.shortestpath.*;

class myNode {

    private int x, y, dist;

    public myNode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public myNode(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        myNode node = (myNode) o;

        if (x != node.x) {
            return false;
        }
        if (y != node.y) {
            return false;
        }
        return dist == node.dist;
    }

    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + dist;
        return result;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDist() {
        return dist;
    }

}

class myKnight {

    private Graph<myNode, DefaultEdge> G;
    private ArrayList<myNode> pathSolution = new ArrayList<myNode>();
    private int row[] = {2, 2, -2, -2, 1, 1, -1, -1};
    private int col[] = {-1, 1, 1, -1, 2, -2, 2, -2};
    private int chessSize, flagRow, flagColumn;

    public myKnight(int N, int row, int col) {
        chessSize = N;
        flagRow = row;
        flagColumn = col;
    }

    private boolean valid(int x, int y, int N) {
        if (x < 0 || y < 0 || x >= N || y >= N) {
            return false;
        }

        return true;
    }

    public int solve(myNode src, myNode dest, int N) {

        Set<myNode> visited = new HashSet<>();

        Queue<myNode> q = new ArrayDeque<>();
        q.add(src);

        while (!q.isEmpty()) {

            myNode node = q.poll();

            int x = node.getX();
            int y = node.getY();
            int dist = node.getDist();


            if (x == dest.getX() && y == dest.getY()) {
                pathSolution.add(node);
                return dist;
            }


            if (!visited.contains(node)) {

                visited.add(node);
                for (int i = 0; i < 8; ++i) {
                    // Get the new valid position of Knight from current
                    // position on chessboard and enqueue it in the
                    // queue with +1 distance
                    int x1 = x + row[i];
                    int y1 = y + col[i];

                    if (valid(x1, y1, N)) {
                        q.add(new myNode(x1, y1, dist + 1));
                        if (!pathSolution.contains(node)) {
                            pathSolution.add(node);
                        }
                    }

                }
            }
        }
        return -1;
    }

    public boolean isConnected(myNode source, myNode target) {
        for (int i = 0; i < 8; i++) {
            if (source.getX() + row[i] == target.getX() && source.getY() + col[i] == target.getY()) {
                return true;
            }
        }
        return false;
    }

    public void setAndPrintGraph() {
        G = new SimpleDirectedGraph<>(DefaultEdge.class);
        for (int i = 0; i < pathSolution.size(); i++) {
            for (int j = i + 1; j < pathSolution.size(); j++) {
                if (pathSolution.get(j).getDist() == pathSolution.get(i).getDist() + 1) {
                    if (isConnected(pathSolution.get(i), pathSolution.get(j))) {
                        Graphs.addEdgeWithVertices(G, pathSolution.get(i), pathSolution.get(j));
                    }
                }
            }
        }
        printStep(pathSolution.get(0), pathSolution.get(pathSolution.size() - 1));
    }

    public void printStep(myNode src, myNode dest) {
        DijkstraShortestPath<myNode, DefaultEdge> shpath = new DijkstraShortestPath<>(G);
        int count = 0;
        try {
            if (shpath.getPath(src, dest) != null) {
                List<myNode> allNodes = shpath.getPath(src, dest).getVertexList();
                for (myNode s : allNodes) {
                    if (count != 0) {
                        System.out.println("Move " + count);
                        printGraph(s.getX(), s.getY());
                    }
                    count++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error Occur on Dijkstra");
        }

    }

    public void printGraph(int row, int column) {
        for (int i = 0; i < chessSize + 1; i++) {
            for (int j = 0; j < chessSize + 1; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("    ");
                } else if (i == 0) {
                    System.out.printf("%-4d", j);
                } else if (j == 0) {
                    System.out.printf("%-3d ", i);
                } else if (i == flagRow + 1 && j == flagColumn + 1 && row == flagRow && column == flagColumn) {
                    System.out.print("K*  ");
                } else if (i == row + 1 && j == column + 1) {
                    System.out.print("K   ");
                } else if (i == flagRow + 1 && j == flagColumn + 1) {
                    System.out.print("F   ");
                } else {
                    System.out.print("=   ");
                }
            }
            System.out.println();
        }
    }

}

public class KnightPath {

    public static void main(String[] args) {
        while (true) {
        int chessboard = 0, row = 0, col = 0;
        boolean checkboard = false;
        boolean checkRow = false, checkCol = false;
        boolean checkRowf = false, checkColf = false;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter board size (at least 5) = ");
        while (!checkboard) {
            try {
                String tem = scan.nextLine();
                chessboard = Integer.parseInt(tem);
            } catch (Exception e) {
                System.out.println("Please Enter board size as Interger!!");
                System.out.print("Enter board size (at least 5) = ");
                continue;
            }
            if (chessboard < 5) {
                System.out.println("Please Enter board size at least 5!!");
                System.out.print("Enter board size (at least 5 ) = ");
            } else {
                checkboard = true;
            }
        }
        System.out.println("initial");
        for (int i = 0; i < chessboard + 1; i++) {
            for (int j = 0; j < chessboard + 1; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("    ");
                } else if (i == 0) {
                    System.out.printf("%-4d", j);
                } else if (j == 0) {
                    System.out.printf("%-3d ", i);
                } else {
                    System.out.print("=   ");
                }
            }
            System.out.println();
        }

        System.out.print("Enter knight position(row) = ");
        while (!checkRow) {
            try {
                String tem = scan.nextLine();
                row = Integer.parseInt(tem);;
            } catch (Exception e) {
                System.out.println("Please Enter row as Interger!!");
                System.out.print("Enter knight position(row) : ");
                continue;
            }
            if (row > chessboard) {
                System.out.println("Please enter row less than board !!!!!!");
                System.out.print("Enter knight position(row) :  ");
            } else if (row <= 0) {
                System.out.println("Please enter row with positive integer!!!!!!");
                System.out.print("Enter knight position(row) : ");
            } else {
                checkRow = true;
            }
        }

        System.out.print("Enter knight position(column) = ");
        while (!checkCol) {
            try {
                String tem = scan.nextLine();
                col = Integer.parseInt(tem);
            } catch (Exception e) {
                System.out.println("Please Enter column as Interger!!");
                System.out.print("Enter knight position(column) : ");
                continue;
            }
            if (col > chessboard) {
                System.out.println("Please enter column less than board !!!!!!");
                System.out.print("Enter knight position(column) :  ");
            } else if (col <= 0) {
                System.out.println("Please enter coloum with positive integer!!!!!!");
                System.out.print("Enter knight position(column) : ");
            } else {
                checkCol = true;
            }
        }
        int rowK = row;
        int colK = col ;
        myNode src = new myNode(row - 1, col - 1, 0);

        System.out.print("Enter flag position(row) = ");

        while (!checkRowf) {
            try {
                String tem = scan.nextLine();
                row = Integer.parseInt(tem);
            } catch (Exception e) {
                System.out.println("Please Enter row as Interger!!");
                System.out.print("Enter flag position(row) : ");
                continue;
            }
            if (row > chessboard) {
                System.out.println("Please enter row less than board !!!!!!");
                System.out.print("Enter flag position(row) :  ");
            } else if (row <= 0) {
                System.out.println("Please enter row with positive integer!!!!!!");
                System.out.print("Enter flag position(row) : ");
            } else {
                checkRowf = true;
            }
        }

        System.out.print("Enter flag position(column) = ");
        while (!checkColf) {
            try {
                String tem = scan.nextLine();
                col = Integer.parseInt(tem);
            } catch (Exception e) {
                System.out.println("Please Enter column as Interger!!");
                System.out.print("Enter flag position(column) : ");
                continue;
            }
            if (col > chessboard) {
                System.out.println("Please enter column less than board !!!!!!");
                System.out.print("Enter flag position(column) :  ");
            } else if (col <= 0) {
                System.out.println("Please enter coloum with positive integer!!!!!!");
                System.out.print("Enter flag position(column) : ");
            } else {
                checkColf = true;
            }
        }

        System.out.println("initial");
        myNode dest = new myNode(row - 1, col - 1);
        myKnight knight = new myKnight(chessboard, row - 1, col - 1);
        knight.printGraph(rowK-1, colK-1);
        int step = knight.solve(src, dest, chessboard);
        if (step > 0) {
            System.out.println("Minimum number of steps required is " + step);
            knight.setAndPrintGraph();
        } else if (step == 0) {
            System.out.println("You Selected flag and Knight at the same place");
        } else {
            System.out.println("No Solution");
        }
               
            System.out.print("Enter y to continue or any key to end program  = ");
            String s = scan.next();
            if (s.compareToIgnoreCase("y") != 0) {
                System.out.println("===== THANK YOU =====");
                break;
            }
        }
    }

}
