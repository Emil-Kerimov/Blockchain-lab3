import java.security.*;
import java.util.ArrayList;
import java.util.List;

class BlockchainNetwork {
    private List<Node> nodes;

    public BlockchainNetwork() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void broadcastBlock(Block block, PublicKey publicKey) throws Exception {
        for (Node node : nodes) {
            if (node.verifyAndAddBlock(block, publicKey)) {
                System.out.println("Block added to node's blockchain.");
            } else {
                System.out.println("Block verification failed at a node.");
            }
        }
    }

    public void showAllBlockchains() {
        for (Node node : nodes) {
            System.out.println("Node's blockchain:\n" + node.getBlockchain());
        }
    }
}