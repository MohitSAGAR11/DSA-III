package Binary_Trees;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class shortestUniquePrefix {

    class TrieNode {
        Map<Character, TrieNode> map = new HashMap<>();
        boolean end;
        int freq;
    }

    TrieNode root = new TrieNode();

    public void insert(String s) {
        TrieNode curr = root;
        for (char c : s.toCharArray()) {
            TrieNode next = curr.map.get(c);
            if (next == null) {
                next = new TrieNode();
                curr.map.put(c, next);
            }
            next.freq++;
            curr = next;
        }
        curr.end = true;
    }

    public String getUniquePrefix(String s) {
        TrieNode curr = root;
        StringBuilder prefix = new StringBuilder();
        for (char c : s.toCharArray()) {
            TrieNode next = curr.map.get(c);
            if (next == null) {
                return "";
            }
            prefix.append(c);
            curr = next;
            if (curr.freq == 1) {
                return prefix.toString();
            }
        }
        return prefix.toString();
    }

    public static void main(String[] args) {
        String[] words = { "cat", "dog", "rat", "tiger", "raccon" };

        shortestUniquePrefix trie = new shortestUniquePrefix();
        for (String word : words) {
            trie.insert(word);
        }

        String[] ans = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            ans[i] = trie.getUniquePrefix(words[i]);
        }

        System.out.println(Arrays.toString(ans));
    }
}
