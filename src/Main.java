import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Blockchain blockchain = new Blockchain();
        Node node = new Node(blockchain);

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        keyGen.initialize(256);
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();

        Transaction tx1 = new Transaction("input", "out", 20, privateKey);
        Transaction tx2 = new Transaction("inp", "output", 10, privateKey);

        // Майнінг
        System.out.println("Майнінг...");
        Block minedBlock = node.mineBlock(4, List.of(tx1, tx2), privateKey);
        System.out.println("Новий блок: \n" + minedBlock);

        System.out.println("Локальний блокчейн:");
        System.out.println(blockchain);
    }
}
