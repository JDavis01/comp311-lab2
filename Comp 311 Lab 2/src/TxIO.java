/**
 *
 * @author jacob
 * @version 6/20/19
 */
public abstract class TxIO implements Visitable {
    private String address;
    private int amount;

    /**
     * Constructs an output or input
     * @param address the location
     * @param amount the amount
     */
    public TxIO(String address, int amount) {
        this.address = address;
        this.amount = amount;
    }

    /**
     * Gets the address
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the amount
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }
}
