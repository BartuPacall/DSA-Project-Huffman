import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        LinkedListHuffman<Character> myList = new LinkedListHuffman<>();

        try {
            int c;
            float[] frequencies = new float[256]; 
            BufferedReader br = new BufferedReader(new FileReader("letter.txt")); 

            while ((c = br.read()) != -1) {
                frequencies[c]++;
            }

            System.out.println("Character Frequencies: ");
            for (int i = 0; i < frequencies.length; i++) {
                if (frequencies[i] > 0) {
                    System.out.println("Character: " + (char) i + ", Frequency: " + frequencies[i]);
                }
            }

            for (int i = 0; i < frequencies.length; i++) {
                if (frequencies[i] > 0) {
                    char character = (char) i;
                    myList.insertSorted(new HuffmanNode<>(frequencies[i], character));
                }
            }


            System.out.println("-----LINKED LIST-------");
            myList.display();
            HuffmanTree<Character> huffmanTree = new HuffmanTree<>();
            huffmanTree.createHuffmanTree(myList);
            System.out.println("Imagine the HuffmanTree :");
            huffmanTree.displayTree();
            System.out.println("Bitcodes of symbols : ");
            huffmanTree.displaySCode();

            
            br = new BufferedReader(new FileReader("source.txt")); 
            StringBuilder sourceTextBuilder = new StringBuilder();
            while ((c = br.read()) != -1) {
                sourceTextBuilder.append(huffmanTree.encode(String.valueOf((char) c)));
            }

            String encodedText = sourceTextBuilder.toString();
            System.out.println("Encoded Text: " + encodedText);

            
            BufferedWriter encodedWriter = new BufferedWriter(new FileWriter("encoded.txt")); 
            encodedWriter.write(encodedText);
            encodedWriter.close();

            BufferedReader encodedReader = new BufferedReader(new FileReader("encoded.txt"));
            StringBuilder encodedTextBuilder = new StringBuilder();
            while ((c = encodedReader.read()) != -1) {
                encodedTextBuilder.append((char) c);
            }
            encodedReader.close();

            String decodedText = huffmanTree.decode(encodedTextBuilder.toString());
            System.out.println("Decoded Text: " + decodedText);

            BufferedWriter decodedWriter = new BufferedWriter(new FileWriter("decoded.txt"));
            decodedWriter.write(decodedText);
            decodedWriter.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
