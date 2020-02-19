import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jacob
 * @version 6/20/19
 */
public class MainBranchVisitor extends AbstractVisitor {

    private Block root = null;
    private int depth = 0;
    private int maxDepth = 0;
    private Block maxBlock = null;

    /**
     * Previsit block
     * @param b the block
     */
    @Override
    public void preVisit(Block b) {
        if (depth == 0) {
            root = b;
        }
        if (b.getChildren().isEmpty()) {
            if (depth > maxDepth) {
                maxDepth = depth;
                maxBlock = b;
            }
        }
        depth++;
    }

    /**
     * Postvisit block
     * @param b the block
     */
    @Override
    public void postVisit(Block b) {
        depth--;
    }

    /**
     * Gets the longest chain
     * @return list of blocks
     */
    public List<Block> getChain() {
        LinkedList<Block> longestChain = new LinkedList<Block>();
        Block current = maxBlock;
        longestChain.addFirst(maxBlock);
        while (!current.equals(root)) {
            maxDepth--;
            longestChain.addFirst(current.getParent());
            current = current.getParent();
        }
        return longestChain;
    }
}
