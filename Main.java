public class Main {
  public static void main(String[] args) {
    // ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>(10);
    LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<Integer>();

    linkedListQueue.enqueue(1);
    System.out.println(linkedListQueue.size());
    System.out.println(linkedListQueue.dequeue());
    System.out.println(linkedListQueue.size());
  }
}