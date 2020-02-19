
/**
 * Visitable interface
 * @author tim
 * @version 1.0
 *
 */
public interface Visitable {
    /**
     * Accept a Visitor
     * @param v the Visitor
     */
    void accept(Visitor v);
}
