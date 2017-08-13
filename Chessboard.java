import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank and Bethany
 * @version 2015.07.12
 */
public class Chessboard {
    private List<Queen> queens = new ArrayList<Queen>();

    /**
     * Empty constructor
     */
    public Chessboard() {
    }

    /**
     * Console 
     */
    public void Display() {
        if (queens.size() == 8) {
            for (Queen q : queens) {
                q.Display();
            }
        }
    }

    /**
     * @throws Exception, adds queens 
     */
    public void Solve() throws Exception {
        for (int i = 1; i <= 8; i++) {
            Queen q = new Queen(i);
            q.FindPosition(queens);
            queens.add(q);
        }

        int count = 1;
        while (true) {
            System.out.println("Solution " + count++);

            this.Display();
            System.out.println("");
            Queen lastQueen = queens.get(queens.size() - 1);
            queens.remove(queens.size() - 1);
            lastQueen.FindPosition(queens);
            queens.add(lastQueen);
        }

    }

    /**
     * @param args main method
     */
    public static void main(String[] args) {
        Chessboard chessboard = new Chessboard();
        try {
            chessboard.Solve();
        } catch (Exception e) {
            System.out.println("Found all solutions");
        }
    }
}
