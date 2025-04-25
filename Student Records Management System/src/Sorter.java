import java.util.Comparator;

public class Sorter {

    
    public static <T> void selectionSort(LinkedList<T> list, Comparator<? super T> comparator) {
        for (int i = 0; i < list.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (comparator.compare(list.get(j), list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                T temp = list.get(i);
                list.set(i, list.get(minIndex));
                list.set(minIndex, temp);
            }
        }
    }

    
    public static <T> void mergeSort(LinkedList<T> list, Comparator<? super T> comparator) {
        list.sort(comparator);
    }

    
    public static Comparator<Student> getGPAComparator() {
        return new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s1.getGPA(), s2.getGPA());
            }
        };
    }

    
    public static Comparator<Student> getNameComparator() {
        return new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getName().compareTo(s2.getName());
            }
        };
    }
}