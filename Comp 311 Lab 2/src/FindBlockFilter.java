
/**
 * Filter interface
 * @author tim
 * @version 1.0
 *
 */
public interface FindBlockFilter {
    /**
     * Returns true if the block matches
     * @param b the block
     * @return true if the block matches
     */
    boolean matches(Block b);

    /**
     * Returns true if the tx matches
     * @param tx the tx
     * @return true if the tx matches
     */
    boolean matches(Transaction tx);

    /**
     * Returns true if the input matches
     * @param input the input
     * @return true if the input matches
     */
    boolean matches(TxInput input);

    /**
     * Returns true if the output matches
     * @param output the output
     * @return true if the output matches
     */
    boolean matches(TxOutput output);
}
