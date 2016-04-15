package ejemplo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;

/**
 * Model for the Hanoi Towers puzzle.
 * Supports arbitrary numbers of stacks and disks.
 */
public class HanoiModel {

    // using Deque instead of Stack, because Stack is deprecated
    private ArrayList<Deque<Integer>> stacks;
    private int diskCount;

    /**
     * Initializes the model with all disks on the 1st stack.
     * @param stacks to use (minimum: 3)
     * @param disks of size disk to 1 (both inclusive) to place in 1st stack.
     */
    public HanoiModel(int stacks, int disks) {
        if (stacks < 3 || disks < 1) {
            throw new IllegalArgumentException("Need at least 1 disk and 3 stacks");
        }

        this.stacks = new ArrayList<>();
        this.diskCount = disks;
        for (int i=0; i<stacks; i++) {
            this.stacks.add(new ArrayDeque<Integer>());
        }
        for (int i=disks; i>=1; i--) {
            this.stacks.get(0).push(i);
        }
    }

    /**
     * Returns a (read-only) iterator to a stack
     * @param index of stack
     * @return an iterable to look at it with
     */
    public Iterable<Integer> getStack(final int index) {
        return new Iterable<Integer>() {
        	public Iterator<Integer> iterator() {
        		return stacks.get(index).descendingIterator();
        	}
        };
    }

    public int getStackCount() {
        return stacks.size();
    }

    public int getDiskCount() {
        return diskCount;
    }

    /**
     * Moves a disk from one stack to another
     * @param source stack, indexed 0 to stackCount
     * @param target stack, indexed 0 to stackCount
     */
    public void move(int source, int target) {
        Deque<Integer> sourceStack = stacks.get(source);
        Deque<Integer> targetStack = stacks.get(target);

        if (sourceStack.isEmpty()) {
            throw new IllegalArgumentException("Source stack (" + source + ") is empty!");
        } else if ( ! targetStack.isEmpty() && targetStack.peek() < sourceStack.peek()) {
            throw new IllegalArgumentException("Cannot move "
                    + sourceStack.peek() + " on top of "  + targetStack.peek() + " (from stack "
                    + source + " to stack " + target + ")");
        }

        targetStack.push(sourceStack.pop());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Iterable<Integer> s : stacks) {
            for (int i : s) {
                sb.append(" " + i);
            }
            sb.append(" ]");
        }
        return sb.toString();
    }
}
