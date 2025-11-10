package Binary_Trees;

import java.util.HashMap;
import java.util.Map;

public class MaxXORTrie {
    class TrieNode {
        Map<Character, TrieNode> map = new HashMap<>(); 
    }

    TrieNode root = new TrieNode();

    // Convert number to 32-bit binary string
    public String toBinary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            sb.append(bit);
        }
        return sb.toString();
    }

    public void insert(String s) {
        TrieNode curr = root;
        for (char c : s.toCharArray()) {
            TrieNode next = curr.map.get(c);
            if (next == null) {
                next = new TrieNode();
                curr.map.put(c, next);
            }
            curr = next;
        }
    }

    // Find maximum XOR for given number
    public int findMaxXOR(int num) {
        String binary = toBinary(num);
        TrieNode curr = root;
        int maxXOR = 0;

        for (int i = 0; i < 32; i++) {
            char bit = binary.charAt(i);
            char oppositeBit = (bit == '0') ? '1' : '0';

            // Try to take opposite bit path (maximize XOR)
            if (curr.map.containsKey(oppositeBit)) {
                maxXOR |= (1 << (31 - i));  // Set this bit to 1
                curr = curr.map.get(oppositeBit);
            } else {
                // Take same bit (no choice)
                curr = curr.map.get(bit);
            }
        }

        return maxXOR;
    }

    public int findMaximumXOR(int[] nums) {
        // Step 1: Insert all numbers into trie
        for (int num : nums) {
            String binary = toBinary(num);
            insert(binary);
        }

        // Step 2: Find max XOR for each number
        int maxXOR = 0;
        for (int num : nums) {
            maxXOR = Math.max(maxXOR, findMaxXOR(num));
        }

        return maxXOR;
    }

    public static void main(String[] args) {
        MaxXORTrie trie = new MaxXORTrie();
        
        int[] nums = {3, 10, 5, 25, 2, 8};
        System.out.println("Array: [3, 10, 5, 25, 2, 8]");
        System.out.println("Maximum XOR: " + trie.findMaximumXOR(nums));
        // Output: 28 (5 XOR 25 or other pairs)
        
        int[] nums2 = {14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70};
        System.out.println("\nArray: [14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70]");
        System.out.println("Maximum XOR: " + trie.findMaximumXOR(nums2));
        // Output: 127
    }
}