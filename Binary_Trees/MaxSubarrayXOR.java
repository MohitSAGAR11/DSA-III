package Binary_Trees;

import java.util.HashMap;
import java.util.Map;

public class MaxSubarrayXOR {

    class TrieNode {
        Map<Character, TrieNode> map = new HashMap<>();
    }

    TrieNode root = new TrieNode();

    public String toBinary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 31; i >= 0; i--) {
            sb.append((num >> i) & 1);
        }
        return sb.toString();
    }

    public void insert(String s) {
        TrieNode curr = root;
        for (char c : s.toCharArray()) {
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new TrieNode());
            }
            curr = curr.map.get(c);
        }
    }

    public int findMaxXOR(int num) {
        String binary = toBinary(num);
        TrieNode curr = root;
        int maxXOR = 0;

        for (int i = 0; i < 32; i++) {
            char bit = binary.charAt(i);
            char opp = bit == '0' ? '1' : '0';

            TrieNode best = curr.map.get(opp);
            if (best != null) {
                maxXOR |= (1 << (31 - i));
                curr = best;
            } else {
                TrieNode same = curr.map.get(bit);
                if (same == null) break;
                curr = same;
            }
        }
        return maxXOR;
    }

    public int maxSubarrayXOR(int[] arr) {
        int n = arr.length;
        int[] prefix = new int[n + 1];

        prefix[0] = 0;
        insert(toBinary(prefix[0]));

        int maxXor = 0;

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] ^ arr[i];
            maxXor = Math.max(maxXor, findMaxXOR(prefix[i + 1]));
            insert(toBinary(prefix[i + 1]));
        }
        return maxXor;
    }

    public static void main(String[] args) {
        MaxSubarrayXOR solver = new MaxSubarrayXOR();
    
        int[] arr2 = {4, 6};
        System.out.println(solver.maxSubarrayXOR(arr2));

        int[] arr = {8, 1, 2, 12, 7, 6};
        System.out.println(solver.maxSubarrayXOR(arr));
    }
}
