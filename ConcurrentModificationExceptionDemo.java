import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ConcurrentModificationException;
public class ConcurrentModificationExceptionDemo {
    public static void main(String[] args) {
        // Create a list of strings
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");

        // Attempt to modify the list while iterating
        try {
            for (String item : list) {
                if (item.equals("three")) {
                    // This modification will cause a ConcurrentModificationException
                    list.remove(item);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Caught ConcurrentModificationException: " + e.getMessage());
        }

        // Proper way to modify the list while iterating
        try {
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String item = iterator.next();
                if (item.equals("three")) {
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
