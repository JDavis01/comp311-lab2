/**
 *
 * @author jacob
 * @version 6/20/19
 */
public class PrintVisitor extends AbstractVisitor {

    private StringBuffer sb = new StringBuffer();

    /**
     * Previsit block
     * @param b the block
     */
    @Override
    public void preVisit(Block b) {
        sb.append("Block: " + b.getHash() + "\n");
        if (b.getParent() != null) {
            sb.append("  Parent: " + b.getParent().getHash() + "\n");
        }
    }

    /**
     * Previsit tx
     * @param tx the tx
     */
    @Override
    public void preVisit(Transaction tx) {
        sb.append("  Transaction: " + tx.getId() + "\n");
    }

    /**
     * Postinputvisit tx
     * @param tx the tix
     */
    @Override
    public void postInputVisit(Transaction tx) {
        if (!tx.getInputs().isEmpty()) {
            sb.append("    Inputs:\n");
        }
    }

    /**
     * Visit input
     * @param input the input
     */
    @Override
    public void visit(TxInput input) {
        sb.append("      Address: " + input.getAddress() + "\n");
        sb.append("      Amount: " + input.getAmount() + "\n");
    }

    /**
     * Postvisit tx
     * @param tx the tx
     */
    @Override
    public void postVisit(Transaction tx) {
        if (!tx.getOutputs().isEmpty()) {
            sb.append("    Outputs:\n");
        }
    }

    /**
     * Visit output
     * @param output the output
     */
    @Override
    public void visit(TxOutput output) {
        sb.append("      Address: " + output.getAddress() + "\n");
        sb.append("      Amount: " + output.getAmount() + "\n");
    }

    /**
     * Gets string representation of block chain
     * @return a string
     */
    public String getResult() {
        return sb.toString();
    }

}
