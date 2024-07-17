import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Concu{

    public static void main(String[] args) {
        // Create a list of integers
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        // Attempt to modify the list while iterating
        try {
            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext()) {
                Integer number = iterator.next();
                if (number == 3) {
                    // This modification will cause a ConcurrentModificationException
                    list.remove(number);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Caught ConcurrentModificationException: " + e.getMessage());
        }

        // Proper way to modify the list while iterating
        try {
            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext()) {
                Integer number = iterator.next();
                if (number == 3) {
                    // Use iterator's remove method to avoid ConcurrentModificationException
                    iterator.remove();
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Caught ConcurrentModificationException: " + e.getMessage());
        }

        // Display the modified list
        System.out.println("Modified list: " + list);
    }
}
