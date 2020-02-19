/**
 *
 * @author jacob
 * @version 6/20/19
 */
public class FindOutputFilter implements FindBlockFilter {
    private String address;

    /**
     * Constructs a find output filter
     * @param address to be matched
     */
    public FindOutputFilter(String address) {
        this.address = address;
    }

    /**
     * Returns true if the block matches
     * @param b the block
     * @return true if the block matches
     */
    @Override
    public boolean matches(Block b) {
        return false;
    }

    /**
     * Returns true if the tx matches
     * @param tx the tx
     * @return true if the tx matches
     */
    @Override
    public boolean matches(Transaction tx) {
        return false;
    }

    /**
     * Returns true if the input matches
     * @param input the input
     * @return true if the input matches
     */

    @Override
    public boolean matches(TxInput input) {
        return false;
    }

    /**
     * Returns true if the output matches
     * @param output the output
     * @return true if the output matches
     */
    @Override
    public boolean matches(TxOutput output) {
        return output.getAddress().equals(address);
    }

}
