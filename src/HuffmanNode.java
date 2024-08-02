public class HuffmanNode<T> {
    public float frequency;
    public T symbol;
    public HuffmanNode<T> left, right, next;

    public HuffmanNode(float frequency, T symbol) {
        this.frequency = frequency;
        this.symbol = symbol;
        this.left = null;
        this.right = null;
        this.next = null;
    }
}