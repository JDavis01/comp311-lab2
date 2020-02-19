import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jacob
 * @version 6/20/19
 */
public class Block implements Visitable {
    private String hash;
    private Block parent;
    private List<Transaction> transactions;
    private List<Block> blocks;

    /**
     * Constructs a block
     *
     * @param hash to identify block
     */
    public Block(String hash) {
        this.hash = hash;
        parent = null;
        transactions = new LinkedList<Transaction>();
        blocks = new LinkedList<Block>();
    }

    /**
     * Adds a child block to this block
     *
     * @param b to be added
     */
    public void addChild(Block b) {
        b.setParent(this);
        blocks.add(b);
    }

    /**
     * Gets a list of children blocks
     *
     * @return list
     */
    public List<Block> getChildren() {
        return blocks;
    }

    /**
     * Adds a transaction to this block
     *
     * @param tx to be added
     */
    public void addTransaction(Transaction tx) {
        tx.setOuterBlock(this);
        transactions.add(tx);
    }

    /**
     * Gets this blocks hash
     *
     * @return hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * Gets this blocks parent
     *
     * @return parent block
     */
    public Block getParent() {
        return parent;
    }

    /**
     * Sets this blocks parent
     *
     * @param parent to be set
     */
    public void setParent(Block parent) {
        this.parent = parent;
    }

    /**
     * Accepts a visitor to traverse chain
     *
     * @param v visitor to be accepted
     */
    @Override
    public void accept(Visitor v) {
        v.preVisit(this);
        for (Transaction tx : transactions) {
            tx.accept(v);
        }
        for (Block b : blocks) {
            b.accept(v);
        }
        v.postVisit(this);
    }

}
