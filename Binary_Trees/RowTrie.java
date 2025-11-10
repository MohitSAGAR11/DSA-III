package Binary_Trees;

import java.util.HashMap;
import java.util.Map;

public class RowTrie {

    class TrieNode {
        Map<Integer, TrieNode> map = new HashMap<>();
        int endCount;
    }

    TrieNode root = new TrieNode();

    public void insert(int[] row) {
        TrieNode curr = root;
        for (int num : row) {
            TrieNode next = curr.map.get(num);
            if (next == null) {
                next = new TrieNode();
                curr.map.put(num, next);
            }
            curr = next;
        }
        curr.endCount++;
    }

    public boolean isUnique(int[] row) {
        TrieNode curr = root;
        for (int num : row) {
            TrieNode next = curr.map.get(num);
            if (next == null) {
                return false;
            }
            curr = next;
        }
        return curr.endCount == 1;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 0, 0, 1, 0, 0 },
                { 0, 1, 0, 1, 0, 0 },
                { 1, 1, 0, 1, 1, 1 },
                { 1, 1, 0, 0, 0, 1 },
                { 1, 1, 0, 1, 1, 1 },
                { 1, 0, 0, 1, 0, 0 },
                { 1, 0, 1, 0, 1, 0 },
                { 0, 0, 1, 1, 0, 0 },
                { 1, 1, 0, 1, 1, 0 }
        };

        RowTrie trie = new RowTrie();
        for (int[] row : matrix) {
            trie.insert(row);
        }

        int count = 0;
        for (int[] row : matrix) {
            if (trie.isUnique(row))
                count++;
        }

        System.out.println(count);
    }
}
