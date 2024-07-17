import java.util.PriorityQueue;

public class KthLargestElement {
    public int findKthLargest(int[] nums, int k) {
        // Min-Heap with size k
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
      
        for (int num : nums) {
            // Add each element to the heap
            minHeap.offer(num);
            // If heap size exceeds k, remove the smallest element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        // The root of the heap is the kth largest element
        return minHeap.peek();
    }
    public static void main(String[] args) {
        KthLargestElement solution = new KthLargestElement();
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println(solution.findKthLargest(nums, k)); // Output: 5
    }
}
