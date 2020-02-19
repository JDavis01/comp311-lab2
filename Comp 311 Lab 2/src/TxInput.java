/**
 *
 * @author jacob
 * @version 6/20/19
 */
public class TxInput extends TxIO {
    private Transaction outerTx;

    /**
     * Constructs an input
     * @param address a location
     * @param amount the amount
     */
    public TxInput(String address, int amount) {
        super(address, amount);
        outerTx = null;
    }

    /**
     * Gets the transaction that this belongs to
     * @return outer transaction
     */
    public Transaction getOuterTransaction() {
        return outerTx;
    }

    /**
     * Sets the outer transaction
     * @param tx to be set
     */
    public void setOuterTransaction(Transaction tx) {
        outerTx = tx;
    }
 /**
  * Accepts a visitor
  * @param v the visitor to be accepted
  */
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
