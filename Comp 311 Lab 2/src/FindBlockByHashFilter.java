/**
 *
 * @author jacob
 * @version 6/20/19
 */
public class FindBlockByHashFilter implements FindBlockFilter {
    private String hash;

    /**
     * Contructs a hash filter
     * @param hash hash to find
     */
    public FindBlockByHashFilter(String hash) {
        this.hash = hash;
    }

    /**
     * Matches based on a block hash
     * @param b block to match
     * @return true if match
     */
    @Override
    public boolean matches(Block b) {
        return b.getHash().equals(hash);
    }

    /**
     * Matches based on transaction id
     * @param tx to match
     * @return true if match
     */
    @Override
    public boolean matches(Transaction tx) {
        return false;
    }

    /**
     * Matches based on input address
     * @param input to match
     * @return true if match
     */
    @Override
    public boolean matches(TxInput input) {
        return false;
    }

    /**
     * Matches based on output address
     * @param output to match
     * @return true if match
     */
    @Override
    public boolean matches(TxOutput output) {
        return false;
    }

}
