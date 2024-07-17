import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Interval {
    int start;
    int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }
}

class IntervalNode {
    Interval interval;
    int max;
    IntervalNode left, right;

    IntervalNode(Interval interval) {
        this.interval = interval;
        this.max = interval.end;
    }
}

public class IntervalTree {
    private IntervalNode root;

    // Helper method to insert a new interval
    private IntervalNode insert(IntervalNode node, Interval interval) {
        if (node == null) {
            return new IntervalNode(interval);
        }

        int l = node.interval.start;

        if (interval.start < l) {
            node.left = insert(node.left, interval);
        } else {
            node.right = insert(node.right, interval);
        }

        if (node.max < interval.end) {
            node.max = interval.end;
        }

        return node;
    }

    public void insertInterval(int start, int end) {
        Interval interval = new Interval(start, end);
        root = insert(root, interval);
    }

    // Helper method to delete an interval
    private IntervalNode delete(IntervalNode root, Interval interval) {
        if (root == null) {
            return null;
        }

        if (interval.start < root.interval.start) {
            root.left = delete(root.left, interval);
        } else if (interval.start > root.interval.start) {
            root.right = delete(root.right, interval);
        } else if (interval.start == root.interval.start && interval.end == root.interval.end) {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            IntervalNode temp = minValueNode(root.right);
            root.interval = temp.interval;
            root.right = delete(root.right, temp.interval);
        } else {
            root.right = delete(root.right, interval);
        }

        if (root != null) {
            root.max = Math.max(root.interval.end, Math.max(getMax(root.left), getMax(root.right)));
        }

        return root;
    }

    private IntervalNode minValueNode(IntervalNode node) {
        IntervalNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private int getMax(IntervalNode node) {
        return (node == null) ? Integer.MIN_VALUE : node.max;
    }

    public void deleteInterval(int start, int end) {
        Interval interval = new Interval(start, end);
        root = delete(root, interval);
    }

    // Helper method to find all overlapping intervals
    private void findOverlapping(IntervalNode node, Interval interval, List<Interval> result) {
        if (node == null) {
            return;
        }

        if (doOverlap(node.interval, interval)) {
            result.add(node.interval);
        }

        if (node.left != null && node.left.max >= interval.start) {
            findOverlapping(node.left, interval, result);
        }

        findOverlapping(node.right, interval, result);
    }

    public List<Interval> findOverlappingIntervals(int start, int end) {
        List<Interval> result = new ArrayList<>();
        Interval interval = new Interval(start, end);
        findOverlapping(root, interval, result);
        return result;
    }

    private boolean doOverlap(Interval i1, Interval i2) {
        return i1.start <= i2.end && i2.start <= i1.end;
    }

    public static void main(String[] args) {
        IntervalTree tree = new IntervalTree();
        Scanner sc = new Scanner(System.in);
        int choice =0;
        do{
            System.out.println("------------------------------------------------------------");
            System.out.println("0].Exit\n1].Insert Interval.\n2].Get Overlapping intervals.");
            System.out.println("------------------------------------------------------------");
            System.out.println("Enter the choice :");
            choice = sc.nextInt();
            switch(choice)
            {
              case 0:
              {
                System.out.println("Exiting...");
                break;
              }
              case 1:
              {
                System.out.println("Enter the start and end of interval : ");
                int start = sc.nextInt();
                int end = sc.nextInt();
               if(start<=end)
               {
                tree.insertInterval(start,end);
               }
               else
               {
                System.out.println("Enter valid interval start should be less than end...");
               }
                break;
              } 
              case 2:
              {
                System.out.println("Enter the start and end of interval in which you want to find overlapping Intervals : ");
                int start = sc.nextInt();
                int end = sc.nextInt();
                System.out.println("Overlapping intervals with ["+start+","+end+"] :" + tree.findOverlappingIntervals(start, end));
                break;
              }
              default :
              {
                System.out.println("Enter Appropriate choice...[0,1,2]");
              }
            }
        }while(choice != 0);
 }
}
