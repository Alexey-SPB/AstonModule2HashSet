import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class AnalogHashSet {
    private static final int SIZE = 16;
    private static final float LOAD = 0.75f;
    private Node[] buckets;
    private int size;
    public AnalogHashSet(int min, int max) {
        buckets = new Node[SIZE];
        size = 0;
    }

    private static class Node {
        int value;
        Node next;
        Node(int value) {
            this.value = value;
        }
    }
    public void add(int value) {
        if (contains(value)) {
            return;
        }
        int index = getIndex(value);
        Node newNode = new Node(value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size += 1;
        if (size > LOAD * buckets.length) {
            rehash();
        }
    }
    public void remove(int value) {
        int index = getIndex(value);
        Node head = buckets[index];
        Node prev = null;
        while (head != null) {
            if (head.value == value) {
                if (prev == null) {
                    buckets[index] = head.next;
                } else {
                    prev.next = head.next;
                }
                size -= 1;
                return;
            }
            prev = head;
            head = head.next;
        }
    }
    private int getIndex(int value) {
        return hash(value) % (buckets.length - 1);
    }
    private void rehash() {
        Node[] oldBuckets = buckets;
        buckets = new Node[2 * oldBuckets.length];
        size = 0;
        for (Node head : oldBuckets) {
            while (head != null) {
                add(head.value);
                head = head.next;
            }
        }
    }
    private int hash(int value) {
        int hash = value * buckets.length;
        return hash ^ (hash >>> 16);
    }
    public boolean contains(int value) {
        int index = getIndex(value);
        Node head = buckets[index];
        while (head != null) {
            if (head.value == value) {
                return true;
            }
            head = head.next;
        }
        return false;
    }
}
class AnalogArrayList {
    private static final int SIZE = 16;
    private int[] mas = new int[SIZE];
    private int elements = 0;
    AnalogArrayList() {
    }
    public void add(int x) {
        mas[elements] = x;
        elements += 1;
    }
    public int get(int i) {
        checkIndex(i);
        return mas[i];
    }
    public void remove(int x) {
        checkIndex(x);
        for (int i = x; i < elements; i++) {
            mas[i] = mas[i + 1];
        }
        elements -= 1;
    }
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(mas, elements));
    }
    public void addAll(int[] x) {
        for (int i = 0; i < x.length; i++) {
            mas[elements] = x[i];
            elements += 1;
        }
    }
    private void checkIndex(int i) {
        if (i < 0 || i >= elements) {
            throw new IndexOutOfBoundsException("Index: " + i + "elements:" + elements);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        AnalogHashSet myHashSet = new AnalogHashSet(1, 16);
        myHashSet.add(7);
        System.out.println(myHashSet.contains(7));
        myHashSet.add(5);
        System.out.println(myHashSet.contains(5));
        System.out.println(myHashSet.contains(3));
        myHashSet.remove(7);
        System.out.println(myHashSet.contains(7));
        System.out.println(myHashSet);

        AnalogArrayList list = new AnalogArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        System.out.println(list.toString());
        list.add(11);
        System.out.println(list.toString());
        list.remove(3);
        System.out.println(list.toString());
        System.out.println(list.get(7));
        list.addAll(new int[]{12, 13, 14});
        System.out.println(list.toString());
        list.add(4);
        System.out.println(list);

        List<Studends> studends = Arrays.asList(
                new Studends("Саша", Arrays.asList(
                        new Book("Колобок", 2020, 10),
                        new Book("Сказки братьев Гримм",2001, 200),
                        new Book("Гарри Поттер", 2010, 500),
                        new Book("Маша и медведь", 2009, 30),
                        new Book("Физика", 1975, 350)
                )),
                new Studends("Игорь", Arrays.asList(
                        new Book("Властелин колец", 2005, 1500),
                        new Book("Игра престолов", 2018, 580),
                        new Book("Химия", 1999, 250),
                        new Book("Статистика", 2003, 478),
                        new Book("Дюна", 2020, 500)
                ))
        );
        studends.stream()
                .peek(System.out::println)
                .flatMap(studend -> studend.getBooks().stream())
                .sorted((x1, x2) -> Integer.compare(x1.getLists(), x2.getLists()))
                .filter(book -> book.getYear() > 2000)
                .distinct()
                .limit(3)
                .map(Book::getYear)
                .findFirst()
                .ifPresentOrElse(
                        year -> System.out.println("Год издания книги:" + year),
                        () -> System.out.println("Такой книги нет")
                );
    }
}

