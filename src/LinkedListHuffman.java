public class LinkedListHuffman<T> {
    private HuffmanNode<T> head;

    public void insertSorted(HuffmanNode<T> newNode) {
        if (head == null || newNode.frequency < head.frequency) {
            newNode.next = head;
            head = newNode;
        } else {
            HuffmanNode<T> current = head;
            while (current.next != null && current.next.frequency <= newNode.frequency) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }
    
    public void display() {
        HuffmanNode<T> iterator = head;
        while (iterator != null) {
            System.out.println("Character: " + iterator.symbol + ", Frekans: " + iterator.frequency);
            iterator = iterator.next;
        }
    }
    
    public HuffmanNode<T> delete() {
        if (head == null) {
            return null;
        }
        HuffmanNode<T> deletedNode = head;
        head = head.next;
        deletedNode.next = null;
        return deletedNode;
    }

    public int count() {
        int count = 0;
        HuffmanNode<T> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}