package ejemplo;

import java.util.ArrayList;

/**
 * Solves the Towers of Hanoi puzzle
 * recursively, saving moves onto a stack
 */
public class HanoiSolver {

    public static class Move {
        private int source;
        private int target;
        public Move(int source, int target) {
            this.source = source;
            this.target = target;
        }
        public int getSource() {
            return source;
        }
        public int getTarget() {
            return target;
        }
    }

    public static ArrayList<Move> solve(int count, int source, int target) {
        ArrayList<Move> moves = new ArrayList<>();
        int auxiliaryStacks = target-source-1;
        switch (auxiliaryStacks) {
            case 1:
                solve3(moves, count, source, target, source + 1, "");
                break;
            // FIXME: add more/better solvers for auxiliaryStacks > 1!
            default:
                solve4(moves, count, source, target, source + 1, source + 2, "");
                break;
        }
        return moves;
    }

    private static void solve3(ArrayList<Move> moves,
                              int n, int source, int target, int aux, String depth) {
        System.err.println(depth + n + " [" + source + "]"
                + (n == 1 ? " --> " : " --< " + aux + " >--> ")
                + " [" + target + "]");

        if (n == 1) {
            moves.add(new Move(source, target));
        } else {
            solve3(moves, n-1, source, aux, target, depth + "  ");
            solve3(moves, 1, source, target, aux, depth + "  ");
            solve3(moves, n-1, aux, target, source, depth + "  ");
        }
    }

    private static void solve4(ArrayList<Move> moves,
                               int n, int source, int target, int aux1, int aux2, String depth) {
        System.err.println(depth + n + " [" + source + "]"
                + (n == 1 ? " --> " : " --< " + aux1 + " , " + aux2 + " >--> ")
                + " [" + target + "]");

        if (n == 1) {
            moves.add(new Move(source, target));
        } else {
            int m1 = (n-1)/2;
            int m2 = (n-1) - m1;
            if (m1 > 0) {
                solve3(moves, m1, source, aux1, target, depth + "  ");
            }
            solve3(moves, m2, source, aux2, target, depth + "  ");
            solve3(moves, 1, source, target, aux1, depth + "  ");
            solve3(moves, m2, aux2, target, source, depth + "  ");
            if (m1 > 0) {
                solve3(moves, m1, aux1, target, source, depth + "  ");
            }
        }
    }
}
