public class PalindromeChecker {
    public boolean isPalindrome(String s) {
        // Remove non-alphanumeric characters and convert to lowercase
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Check if the cleaned string is a palindrome
        int left = 0;
        int right = cleaned.length() - 1;

        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        PalindromeChecker checker = new PalindromeChecker();
        String test1 = "A man, a plan, a canal: Panama";
        String test2 = "race a car";
        String test3 = "No 'x' in Nixon";

        System.out.println(test1 + " is a palindrome: " + checker.isPalindrome(test1)); // true
        System.out.println(test2 + " is a palindrome: " + checker.isPalindrome(test2)); // false
        System.out.println(test3 + " is a palindrome: " + checker.isPalindrome(test3)); // true
    }
}
