import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BlockchainNetwork network = new BlockchainNetwork();

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        keyGen.initialize(256);
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        network.addNode(node1);
        network.addNode(node2);
        network.addNode(node3);

        Transaction tx1 = new Transaction("inp1", "out1", 20, privateKey);
        Transaction tx2 = new Transaction("inp2", "out2", 15, privateKey);
        List<Transaction> transactions = List.of(tx1, tx2);

        System.out.println("Node1 is mining a block");
        Block newBlock = node1.mineBlock(4, transactions, privateKey);

        System.out.println("sending a new block to all nodes");
        network.broadcastBlock(newBlock, publicKey);

        System.out.println("\nFinal state of all blockchains: ");
        network.showAllBlockchains();
    }
}