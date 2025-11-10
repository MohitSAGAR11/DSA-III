package Binary_Trees;

import java.util.*;

class TrieNode {
    Map<Character, TrieNode> map = new HashMap<>();
    boolean end;
}

public class Trie {
    TrieNode root = new TrieNode();

    public void insert(String s) {
        TrieNode cur = root;
        for (char c : s.toCharArray()) {
            TrieNode nxt = cur.map.get(c);
            if (nxt == null) {
                nxt = new TrieNode();
                cur.map.put(c, nxt);
            }
            cur = nxt;
        }
        cur.end = true;
    }

    public boolean search(String s) {
        TrieNode cur = root;
        for (char c : s.toCharArray()) {
            TrieNode nxt = cur.map.get(c);
            if (nxt == null)
                return false;
            cur = nxt;
        }
        return cur.end;
    }


    public boolean delete(String s) {
        return deleteHelper(root, s, 0);
    }   

    public boolean deleteHelper(TrieNode node, String s, int index) {
        if (index == s.length()) {
            if (!node.end)  // word not found
                return false;
            node.end = false;  // unmark the end of word
            return node.map.isEmpty(); // if no children, indicate to delete this node
        }

        char c = s.charAt(index);
        TrieNode nxt = node.map.get(c);
        if (nxt == null) // word not found
            return false;

        boolean shouldDeleteCurrentNode = deleteHelper(nxt, s, index + 1);
        if (shouldDeleteCurrentNode) {
            node.map.remove(c);
            return node.map.isEmpty() && !node.end;
        }
        return false;
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("fire");
        t.insert("fireball");
        t.insert("first");
        t.insert("fireman");

        System.out.println(t.search("fir")); 
        System.out.println(t.search("first"));
        System.out.println(t.search("fireball"));
        System.out.println(t.search("fireman"));

        t.delete("fireball");
        System.out.println(t.search("fireball"));
    }
}
