import java.util.HashMap;
import java.util.Map;

public class HuffmanTree<T> {
    private HuffmanNode<T> root;
    private Map<T, String> huffmanCodes;

    public HuffmanTree() {
        this.root = null;
        this.huffmanCodes = new HashMap<>();
    }

    public void createHuffmanTree(LinkedListHuffman<T> list) {
        while (list.count() > 1) {
            HuffmanNode<T> newNode = new HuffmanNode<>(0, null); 
            newNode.left = list.delete(); 
            newNode.right = list.delete(); 
            newNode.frequency = newNode.left.frequency + newNode.right.frequency; 

            list.insertSorted(newNode); 
        }

        root = list.delete(); 
        buildHuffmanCodes(root, "");
    }

    private void buildHuffmanCodes(HuffmanNode<T> node, String code) {
        if (node != null) {
            if (node.symbol != null) {
                huffmanCodes.put(node.symbol, code);
            }
            buildHuffmanCodes(node.left, code + "0");
            buildHuffmanCodes(node.right, code + "1");
        }
    }

    public String encode(String text) {
        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            T symbol = (T) Character.valueOf(c);
            encodedText.append(huffmanCodes.get(symbol));
        }
        return encodedText.toString();
    }

    public String decode(String encodedText) {
        StringBuilder decodedText = new StringBuilder();
        HuffmanNode<T> current = root;

        for (char bit : encodedText.toCharArray()) {
            if (bit == '0' && current.left != null) {
                current = current.left;
            } else if (bit == '1' && current.right != null) {
                current = current.right;
            }

            if (current.symbol != null) {
                decodedText.append(current.symbol);
                current = root;
            }
        }

        return decodedText.toString();
    }

    public void displayTree() {
        displayTree(root);
    }

    private void displayTree(HuffmanNode<T> node) {
        if (node != null) {
            System.out.println(node.symbol + " - " + node.frequency);
            displayTree(node.left);
            displayTree(node.right);
        }
    }
    
    public void displaySCode() {
        displaySCode(root, "");
    }

    private void displaySCode(HuffmanNode<T> node, String code) {
        if (node != null) {
            if (node.symbol != null) {
                System.out.println(node.symbol + " - " + node.frequency + " - Code: " + code);
            }
            displaySCode(node.left, code + "0");
            displaySCode(node.right, code + "1");
        }
    }


}
