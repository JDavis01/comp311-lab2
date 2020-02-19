import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jacob
 * @version 6/20/19
 */
public class FindBlockVisitor extends AbstractVisitor {
    private FindBlockFilter filter;
    private List<Block> matches;

    /**
     * Constructs a find block visitor
     *
     * @param filter the search criteria
     */
    public FindBlockVisitor(FindBlockFilter filter) {
        this.filter = filter;
        matches = new LinkedList<Block>();
    }

    /**
     * Previsits a block
     *
     * @param b added to list if matches
     */
    @Override
    public void preVisit(Block b) {
        if (filter.matches(b)) {
            matches.add(b);
        }
    }

    /**
     * Previsits a transaction
     *
     * @param tx to be checked for a match
     */
    @Override
    public void preVisit(Transaction tx) {
        if (filter.matches(tx)) {
            if (!matches.contains(tx.getOuterBlock())) {
                matches.add(tx.getOuterBlock());
            }
        }
    }

    /**
     * Visits an output
     *
     * @param output to be checked for a match
     */
    @Override
    public void visit(TxOutput output) {
        if (filter.matches(output)) {
            if (!matches.contains(
                    output.getOuterTransaction().getOuterBlock())) {
                matches.add(output.getOuterTransaction().getOuterBlock());
            }
        }
    }

    /**
     * Gets blocks that were matched
     *
     * @return list of blocks
     */
    public List<Block> getMatches() {
        return matches;
    }
}
