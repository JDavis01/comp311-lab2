import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 *
 * @author jacob
 * @version 6/20/19
 */
public class VisitorTest {

    /**
     * Tests a simpe print visitor
     */
    @Test
    public void testSimplePrintVisitor() {
        // test print block hash
        Block b = new Block("aHash");
        PrintVisitor visitor = new PrintVisitor();
        b.accept(visitor);
        assertEquals("Block: aHash\n", visitor.getResult());

        // test print block hash and parent hash
        Block b1 = new Block("aHash");
        Block b2 = new Block("MrHash");
        PrintVisitor v1 = new PrintVisitor();
        b1.setParent(b2);
        b1.accept(v1);
        assertEquals("Block: aHash\n  Parent: MrHash\n", v1.getResult());
    }

    /**
     * Tests print with transaction
     */
    @Test
    public void testPrintTransaction() {
        // test print block with parent and transaction
        Block b = new Block("aHash");
        PrintVisitor visitor = new PrintVisitor();
        Block b2 = new Block("MrHash");
        Transaction t = new Transaction("t1");
        TxInput in = new TxInput("1", 10);
        TxOutput out = new TxOutput("2", 20);
        t.addInput(in);
        t.addOutput(out);
        b.setParent(b2);
        b.addTransaction(t);
        b.accept(visitor);

        assertEquals(
                "Block: aHash\n  Parent: MrHash\n  "
                + "Transaction: t1\n    Inputs:\n      Address: 1\n"
                + "      Amount: 10\n    Outputs:\n      Address: 2\n"
                + "      Amount: 20\n", visitor.getResult());
    }

    /**
     * Tests with many objects
     */
    @Test
    public void testComplexPrintChain() {
        // test block with children
        Block b = new Block("aHash");
        Block cb = new Block("child");
        Block gcb1 = new Block("grandChild1");
        Block gcb2 = new Block("grandChild2");
        b.addChild(cb);
        cb.addChild(gcb1);
        cb.addChild(gcb2);
        Transaction tx1 = new Transaction("101");
        TxInput in1 = new TxInput("in1", 10);
        TxOutput out1 = new TxOutput("out1", 11);
        Transaction tx2 = new Transaction("102");
        TxOutput out2 = new TxOutput("out2", 13);
        Transaction tx3 = new Transaction("103");
        TxInput in3 = new TxInput("in3", 14);
        TxOutput out3 = new TxOutput("out3", 15);
        Transaction tx4 = new Transaction("104");
        TxInput in4 = new TxInput("in4", 16);
        TxOutput out4 = new TxOutput("out4", 17);
        TxInput in5 = new TxInput("in5", 18);
        TxOutput out5 = new TxOutput("out5", 19);
        tx1.addInput(in1);
        tx1.addOutput(out1);
        tx2.addOutput(out2);
        tx3.addInput(in3);
        tx3.addOutput(out3);
        tx4.addInput(in4);
        tx4.addOutput(out4);
        tx4.addInput(in5);
        tx4.addOutput(out5);
        b.addTransaction(tx1);
        gcb1.addTransaction(tx2);
        gcb2.addTransaction(tx3);
        gcb2.addTransaction(tx4);
        PrintVisitor v = new PrintVisitor();
        b.accept(v);

        assertEquals("Block: aHash\n  Transaction: 101\n    Inputs:\n"
                + "      Address: in1\n      Amount: 10\n    Outputs:\n"
                + "      Address: out1\n      Amount: 11\nBlock: child\n"
                + "  Parent: aHash\nBlock: grandChild1\n  Parent: child\n"
                + "  Transaction: 102\n    Outputs:\n"
                + "      Address: out2\n      Amount: 13\nBlock: grandChild2\n"
                + "  Parent: child\n  Transaction: 103\n    Inputs:\n"
                + "      Address: in3\n      Amount: 14\n    Outputs:\n"
                + "      Address: out3\n      Amount: 15\n  Transaction: 104\n"
                + "    Inputs:\n      Address: in4\n      Amount: 16\n"
                + "      Address: in5\n      Amount: 18\n    Outputs:\n"
                + "      Address: out4\n      Amount: 17\n"
                + "      Address: out5\n      Amount: 19\n", v.getResult());
    }

    /**
     * Tests main branch visitor
     */
    @Test
    public void testMainBranchVisitor() {
        Block b1 = new Block("1");
        Block b2 = new Block("2");
        Block b3 = new Block("3");
        Block b4 = new Block("4");
        Block b5 = new Block("5");
        Block b6 = new Block("6");
        Block b7 = new Block("7");
        Block b8 = new Block("8");
        Block b9 = new Block("9");
        Block b10 = new Block("10");
        b2.setParent(b1);
        b4.setParent(b1);
        b7.setParent(b4);
        b8.setParent(b4);
        b9.setParent(b8);
        b3.setParent(b2);
        b5.setParent(b3);
        b6.setParent(b5);
        b10.setParent(b6);
        // same chain as the lab doc
        b1.addChild(b2);
        b1.addChild(b4);
        b4.addChild(b7);
        b4.addChild(b8);
        b8.addChild(b9);
        b2.addChild(b3);
        b3.addChild(b5);
        b5.addChild(b6);
        b6.addChild(b10);

        List<Block> lChain = new LinkedList<Block>();
        lChain.add(b1);
        lChain.add(b2);
        lChain.add(b3);
        lChain.add(b5);
        lChain.add(b6);
        lChain.add(b10);

        MainBranchVisitor mv = new MainBranchVisitor();
        b1.accept(mv);

        assertEquals(lChain, mv.getChain());

        b4.accept(mv);
        List<Block> lChain2 = new LinkedList<Block>();
        lChain2.add(b4);
        lChain2.add(b8);
        lChain2.add(b9);

        assertEquals(lChain2, mv.getChain());

    }

    /**
     * Tests finding block by hash
     */
    @Test
    public void testHashFilter() {
        Block b1 = new Block("odd");
        Block b2 = new Block("2");
        Block b3 = new Block("odd");
        Block b4 = new Block("4");
        Block b5 = new Block("odd");
        Block b6 = new Block("6");
        Block b7 = new Block("odd");
        Block b8 = new Block("8");
        Block b9 = new Block("odd");
        Block b10 = new Block("10");
        b1.addChild(b2);
        b1.addChild(b4);
        b4.addChild(b7);
        b4.addChild(b8);
        b8.addChild(b9);
        b2.addChild(b3);
        b3.addChild(b5);
        b5.addChild(b6);
        b6.addChild(b10);
        FindBlockByHashFilter hf = new FindBlockByHashFilter("8");
        FindBlockVisitor fv = new FindBlockVisitor(hf);
        List<Block> match = new LinkedList<Block>();
        match.add(b8);
        b1.accept(fv);

        assertEquals(match, fv.getMatches());

        FindBlockByHashFilter hf2 = new FindBlockByHashFilter("odd");
        FindBlockVisitor fv2 = new FindBlockVisitor(hf2);
        List<Block> matches = new LinkedList<Block>();
        matches.add(b1);
        matches.add(b3);
        matches.add(b5);
        matches.add(b7);
        matches.add(b9);
        b1.accept(fv2);

        assertEquals(matches, fv2.getMatches());
    }

    /**
     * Tests find by transaction id
     */
    @Test
    public void testFindTransactionFilter() {
        Block b = new Block("aHash");
        Block cb = new Block("child");
        Block pb = new Block("parentBlock");
        Block gcb1 = new Block("grandChild1");
        Block gcb2 = new Block("grandChild2");
        b.addChild(cb);
        cb.setParent(pb);
        cb.addChild(gcb1);
        cb.addChild(gcb2);
        Transaction tx1 = new Transaction("101");
        TxInput in1 = new TxInput("in1", 10);
        TxOutput out1 = new TxOutput("out1", 11);
        Transaction tx2 = new Transaction("101");
        TxOutput out2 = new TxOutput("out2", 13);
        Transaction tx3 = new Transaction("101");
        TxInput in3 = new TxInput("in3", 14);
        TxOutput out3 = new TxOutput("out3", 15);
        Transaction tx4 = new Transaction("104");
        TxInput in4 = new TxInput("in4", 16);
        TxOutput out4 = new TxOutput("out4", 17);
        TxInput in5 = new TxInput("in5", 18);
        TxOutput out5 = new TxOutput("out5", 19);
        Transaction tx5 = new Transaction("101");
        tx1.addInput(in1);
        tx1.addOutput(out1);
        tx2.addOutput(out2);
        tx3.addInput(in3);
        tx3.addOutput(out3);
        tx4.addInput(in4);
        tx4.addOutput(out4);
        tx4.addInput(in5);
        tx4.addOutput(out5);
        b.addTransaction(tx1);
        gcb1.addTransaction(tx2);
        gcb2.addTransaction(tx3);
        gcb2.addTransaction(tx4);
        gcb2.addTransaction(tx5);

        FindTransactionFilter tf = new FindTransactionFilter("104");
        FindBlockVisitor fv = new FindBlockVisitor(tf);
        b.accept(fv);
        List<Block> match = new LinkedList<Block>();
        match.add(gcb2);

        assertEquals(match, fv.getMatches());

        FindTransactionFilter tf2 = new FindTransactionFilter("101");
        FindBlockVisitor fv2 = new FindBlockVisitor(tf2);
        b.accept(fv2);
        List<Block> matches = new LinkedList<Block>();
        matches.add(b);
        matches.add(gcb1);
        matches.add(gcb2);

        assertEquals(matches, fv2.getMatches());
    }

    /**
     * Tests find by output address
     */
    @Test
    public void testOutputFilter() {
        Block b = new Block("aHash");
        Block cb = new Block("child");
        Block pb = new Block("parentBlock");
        Block gcb1 = new Block("grandChild1");
        Block gcb2 = new Block("grandChild2");
        b.addChild(cb);
        cb.setParent(pb);
        cb.addChild(gcb1);
        cb.addChild(gcb2);
        Transaction tx1 = new Transaction("101");
        TxInput in1 = new TxInput("in1", 10);
        TxOutput out1 = new TxOutput("out1", 11);
        Transaction tx2 = new Transaction("102");
        TxOutput out2 = new TxOutput("out5", 13);
        Transaction tx3 = new Transaction("103");
        TxInput in3 = new TxInput("in3", 14);
        TxOutput out3 = new TxOutput("out5", 15);
        Transaction tx4 = new Transaction("104");
        TxInput in4 = new TxInput("in4", 16);
        TxOutput out4 = new TxOutput("out5", 17);
        TxInput in5 = new TxInput("in5", 18);
        TxOutput out5 = new TxOutput("out5", 19);

        tx1.addInput(in1);
        tx1.addOutput(out1);
        tx2.addOutput(out2);
        tx3.addInput(in3);
        tx3.addOutput(out3);
        tx4.addInput(in4);
        tx4.addOutput(out4);
        tx4.addInput(in5);
        tx4.addOutput(out5);
        b.addTransaction(tx1);
        gcb1.addTransaction(tx2);
        gcb2.addTransaction(tx3);
        gcb2.addTransaction(tx4);

        FindOutputFilter of = new FindOutputFilter("in5");
        FindBlockVisitor fv = new FindBlockVisitor(of);
        b.accept(fv);

        List<Block> empty = new LinkedList<Block>();

        assertEquals(empty, fv.getMatches());

        FindOutputFilter of2 = new FindOutputFilter("out5");
        FindBlockVisitor fv2 = new FindBlockVisitor(of2);
        b.accept(fv2);

        List<Block> matches = new LinkedList<Block>();
        matches.add(gcb1);
        matches.add(gcb2);

        assertEquals(matches, fv2.getMatches());
    }
}
