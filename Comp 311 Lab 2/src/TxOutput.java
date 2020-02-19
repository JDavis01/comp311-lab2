/**
 *
 * @author jacob
 * @version 6/20/19
 */
public class TxOutput extends TxIO {
    private Transaction outerTx;

    /**
     * Constructs the output
     * @param address the location
     * @param amount the amount
     */
    public TxOutput(String address, int amount) {
        super(address, amount);
        outerTx = null;
    }

    /**
     * Gets the transaction this belongs to
     * @return the outer transaction
     */
    public Transaction getOuterTransaction() {
        return outerTx;
    }

    /**
     * Sets the transaction
     * @param tx to be set
     */
    public void setOuterTransaction(Transaction tx) {
        outerTx = tx;
    }

    /**
     * Accepts a visitor
     * @param v to be accepted
     */
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
