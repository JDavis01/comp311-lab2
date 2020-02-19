
/**
 * Visitor interface
 * @author tim
 * @version 1.0
 *
 */
public interface Visitor {
    /**
     * Previsit block
     * @param b the block
     */
    void preVisit(Block b);

    /**
     * Postvisit block
     * @param b the block
     */
    void postVisit(Block b);

    /**
     * Previsit tx
     * @param tx the tx
     */
    void preVisit(Transaction tx);

    /**
     * Postinputvisit tx
     * @param tx the tix
     */
    void postInputVisit(Transaction tx);

    /**
     * Postvisit tx
     * @param tx the tx
     */
    void postVisit(Transaction tx);

    /**
     * Visit input
     * @param input the input
     */
    void visit(TxInput input);

    /**
     * Visit output
     * @param output the output
     */
    void visit(TxOutput output);
}
