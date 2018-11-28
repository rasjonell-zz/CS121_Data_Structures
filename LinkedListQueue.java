public class LinkedListQueue<E> implements Queue<E> {
  private SinglyLinkedList<E> singlyLinkedList = new SinglyLinkedList<E>();
  public LinkedListQueue() {};
  public int size() { return singlyLinkedList.size(); }
  public boolean isEmpty() { return singlyLinkedList.isEmpty(); }
  public void enqueue(E e) { singlyLinkedList.addLast(e); }
  public E first() { return singlyLinkedList.first(); }
  public E dequeue() { return singlyLinkedList.removeFirst(); }
}