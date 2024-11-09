import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;

public class Node {
    private Blockchain blockchain;

    public Node() {
        this.blockchain = new Blockchain();
    }

    public Blockchain getBlockchain() {
        return blockchain;
    }

    public Block mineBlock(int difficultyTarget, List<Transaction> transactions, PrivateKey privateKey) throws Exception {
        String prevHash = (blockchain.getLastBlock() != null) ? blockchain.getLastBlock().calculateBlockHash() : "0";
        Block block = new Block(1, prevHash, transactions, difficultyTarget, privateKey);

        String target = new String(new char[difficultyTarget]).replace('\0', '0');
        while (!block.calculateBlockHash().substring(0, difficultyTarget).equals(target)) {
            block.setNonce(block.getNonce() + 1);
        }

        return block;
    }

    public boolean verifyAndAddBlock(Block block, PublicKey publicKey) throws Exception {
        Block lastBlock = blockchain.getLastBlock();
        if (lastBlock != null && !block.getPrevHash().equals(lastBlock.calculateBlockHash())) {
            return false;
        }

        if (block.verifyBlock(publicKey)) {
            blockchain.addBlock(block);
            return true;
        }
        return false;
    }
}