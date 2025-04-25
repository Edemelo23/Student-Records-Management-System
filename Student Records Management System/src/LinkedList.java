import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class LinkedList<T> implements Serializable, Iterable<T> {
    private Node<T> head;
    private Node<T> tail; 
    private int size;
    
    private static class Node<T> implements Serializable {
        T data;
        Node<T> next;
        Node<T> prev; 
        
        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null; 
        }
    }
    
    // modify
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }
    
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }
    
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);    
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    public void set(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = data;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void sort(Comparator<? super T> comparator) {
        if (size > 1) {
            head = mergeSort(head, comparator);
            //
            tail = head;
            while (tail != null && tail.next != null) {
                tail = tail.next;
            }
        }
    }
    
    private Node<T> mergeSort(Node<T> head, Comparator<? super T> comparator) {
        if (head == null || head.next == null) {
            return head;
        }
        Node<T> middle = getMiddle(head);
        Node<T> nextOfMiddle = middle.next;
        middle.next = null;
        nextOfMiddle.prev = null; 

        Node<T> left = mergeSort(head, comparator);
        Node<T> right = mergeSort(nextOfMiddle, comparator);

        return sortedMerge(left, right, comparator);
    }

    private Node<T> sortedMerge(Node<T> a, Node<T> b, Comparator<? super T> comparator) {
        Node<T> result;
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        if (comparator.compare(a.data, b.data) <= 0) {
            result = a;
            result.next = sortedMerge(a.next, b, comparator);
            if (result.next != null) {
                result.next.prev = result;
            }
        } else {
            result = b;
            result.next = sortedMerge(a, b.next, comparator);
            if (result.next != null) {
                result.next.prev = result;
            }
        }
        return result;
    }

    private Node<T> getMiddle(Node<T> head) {
        if (head == null) {
            return head;
        }

        Node<T> slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }
    
    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    // add
    
    public T findRecursive(Predicate<T> predicate) {
        return findRecursiveHelper(head, predicate);
    }
    
    private T findRecursiveHelper(Node<T> current, Predicate<T> predicate) {
        if (current == null) return null;
        if (predicate.test(current.data)) return current.data;
        return findRecursiveHelper(current.next, predicate);
    }
    
    public void printForwardRecursive() {
        printForwardRecursiveHelper(head);
    }
    
    private void printForwardRecursiveHelper(Node<T> current) {
        if (current == null) return;
        System.out.println(current.data);
        printForwardRecursiveHelper(current.next);
    }
    
    public void printBackwardRecursive() {
        printBackwardRecursiveHelper(tail);
    }
    
    private void printBackwardRecursiveHelper(Node<T> current) {
        if (current == null) return;
        System.out.println(current.data);
        printBackwardRecursiveHelper(current.prev);
    }
    
    // Remove method!!!
    public boolean remove(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    public T addLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        return tail.data;
    }
    
    public T addFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.data;
    }
    
    public void clear() {
        head = tail = null;
        size = 0;
    }
    
    //stream
    public Stream<T> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }
    
}