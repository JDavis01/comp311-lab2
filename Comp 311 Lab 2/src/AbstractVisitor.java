
/**
 * Base class for Visitors.
 * @author tim
 * @version 1.0
 *
 */
public abstract class AbstractVisitor implements Visitor {

    @Override
    public void preVisit(Block b) {
        //  Stubbed out
    }

    @Override
    public void postVisit(Block b) {
        //  Stubbed out
    }

    @Override
    public void preVisit(Transaction tx) {
        //  Stubbed out
    }

    @Override
    public void postInputVisit(Transaction tx) {
        //  Stubbed out
    }

    @Override
    public void postVisit(Transaction tx) {
        //  Stubbed out
    }

    @Override
    public void visit(TxInput input) {
        //  Stubbed out
    }

    @Override
    public void visit(TxOutput output) {
        //  Stubbed out
    }

}
