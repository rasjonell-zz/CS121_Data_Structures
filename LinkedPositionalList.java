public class LinkedPositionalList<E> implements PostionalList<E> {
  private static class Node<E> implements Position<E> {
    private E element;
    private Node<E> prev;
    private Node<E> next;

    public Node(E e, Node<E> p, Node<E> n) {
      element = e;
      prev = p;
      next = n;
    }
    
    public E getElement() throws IllegalStateException {
      if (next == null) throw new IllegalStateException("Position no longer valid");
      return element;
    }

    public Node<E> getPrev() { return prev; }
    public Node<E> getNext() { return next; }
    public void setElement(E e) { element = e; }
    public void setPrev(Node<E> p) { prev = p; }
    public void setNext(Node<E> n) { next = n; }
  }

  private Node<E> head;
  private Node<E> tail;
  private int size = 0;

  public LinkedPositionalList() {
    head = new Node<>(null, null, null);
    tail = new Node<>(null, head, null);
    head.setNext(tail);
  }

  public int size() { return size; }
  
  public boolean isEmpty() { return size == 0; }

  public Position<E> first() {
    return position(head.getNext());
  }

  public Position<E> last() {
    return position(tail.getPrev());
  }

  public Position<E> before(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return position(node.getPrev());
  }

  public Position<E> after(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return position(node.getNext());
  }

  public Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
    Node<E> newest = new Node<>(e, pred, succ);
    pred.setNext(newest);
    succ.setPrev(newest);
    size++;
    return newest;
  }

  public Position<E> addFirst(E e) {
    return addBetween(e, head, head.getNext());
  }

  public Position<E> addLast(E e) {
    return addBetween(e, tail.getPrev(), tail);
  }

  public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return addBetween(e, node.getPrev(), node);
  }

  public E set(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> node = validate(p);
    E result = node.getElement();
    node.setElement(e);
    return result;
  }

  public E remove(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    Node<E> pred = node.getPrev();
    Node<E> next = node.getNext();
    
    pred.setNext(next);
    next.setPrev(pred);
    size--;

    E result = node.getElement();
    node.setElement(null);
    node.setNext(null);
    node.setPrev(null);
    
    return result;
  }

  public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return addBetween(e, node, node.getNext());
  }

  private Node<E> validate(Position<E> p) throws IllegalArgumentException {
    if (!(p instanceof Node)) throw new IllegalArgumentException("invalid Position");

    Node<E> node = (Node<E>) p;
    if (node.getNext() == null) throw new IllegalArgumentException("Position is no longer in the list");
    return node;
  }

  private Position<E> position(Node<E> node) {
    if (node == head || node == tail) return null;
    return node;
  }
}
