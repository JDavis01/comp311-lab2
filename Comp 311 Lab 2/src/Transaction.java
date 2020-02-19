import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jacob
 * @version 6/20/19
 */
public class Transaction implements Visitable {
    private String id;
    private List<TxIO> ins;
    private List<TxIO> outs;
    private Block outerBlock;

    /**
     * Constructs a transaction
     * @param id an id for transaction
     */
    public Transaction(String id) {
        this.id = id;
        ins = new LinkedList<TxIO>();
        outs = new LinkedList<TxIO>();
        outerBlock = null;
    }

    /**
     * Adds an input to this transaction
     * @param input to be added
     */
    public void addInput(TxInput input) {
        input.setOuterTransaction(this);
        ins.add(input);
    }

    /**
     * Adds an output to this transaction
     * @param output to be added
     */
    public void addOutput(TxOutput output) {
        output.setOuterTransaction(this);
        outs.add(output);
    }

    /**
     * Get this id
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets a list of inputs
     * @return the list
     */
    public List<TxIO> getInputs() {
        return ins;
    }

    /**
     * Gets a list of outputs
     * @return the list
     */
    public List<TxIO> getOutputs() {
        return outs;
    }

    /**
     * Sets this outer block
     * @param b block to be set
     */
    public void setOuterBlock(Block b) {
        outerBlock = b;
    }

    /**
     * Gets the outer block
     * @return block
     */
    public Block getOuterBlock() {
        return outerBlock;
    }

    /**
     * Accepts a vistor
     * @param v to be accepted
     */
    @Override
    public void accept(Visitor v) {
        v.preVisit(this);

        v.postInputVisit(this);

        for (TxIO in : ins) {
            in.accept(v);
        }

        v.postVisit(this);

        for (TxIO out : outs) {
            out.accept(v);
        }
    }

}
