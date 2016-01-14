package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		if(word.isEmpty())
			return false;
		
		String wordLower = word.toLowerCase();
		
		if(this.isWord(wordLower))
			return false;
		
		TrieNode current = this.root;
		char[] wordArray = wordLower.toCharArray();
		
		for(int i = 0; i < wordArray.length; i++){
			char c = wordArray[i];
			
			//No link to a node with this character
			if(current.getChild(c) == null){
				current = current.insert(c);
			}else{
				current = current.getChild(c);
			}
			
			if((i+1) == wordArray.length){
				current.setEndsWord(true);
				if(this.size < 1){
					this.size = 1;
				}else{
					this.size++;
				}
					
			}
		}
		
		
	    return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return this.size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) {
		// TODO: Implement this method
		if(s.isEmpty())
			return false;
		
		String sLower = s.toLowerCase();
		Set<Character> rootChars = this.root.getValidNextCharacters();
		char[] sCharArray = sLower.toCharArray();

		if (!rootChars.contains(sLower.charAt(0)))
			return false;

		// First character matches set of next valid characters from root, so
		// continue to search
		TrieNode current = root;
		boolean isInTrie = false;

		for (int i = 0; i < sCharArray.length; i++) {			

			/*
			 * We are somewhere between the root and a leaf node Let's check if
			 * we can continue
			 */
			char c = sCharArray[i];
			TrieNode child = current.getChild(c);

			// We matched as many characters as we could, so return false
			if (child == null) {
				return false;
			} else {
				current = child;
				// If last character, check if the node ends a word in the
				// dictionary
				if ((i + 1) == sCharArray.length) {
					return current.endsWord();
				}
			}

		}

		return isInTrie;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	
    	TrieNode current = root;
    	String prefixLower = prefix.toLowerCase();
		char[] sCharArray = prefixLower.toCharArray();

		for (int i = 0; i < sCharArray.length; i++) {
			char c = sCharArray[i];
			TrieNode child = current.getChild(c);

			if (child == null) {
				return new LinkedList<String>();
			} else {
				current = child;
			}

		}
		
		List<String> returnList = new LinkedList<String>();
		List<TrieNode> queue = new LinkedList<TrieNode>();
		
		queue.add(current);
		int curPosition = 0;
		
		while(queue.size() > curPosition){
			if(returnList.size() == numCompletions){
				break;
			}else{
				TrieNode cur = queue.get(curPosition);
				if(cur.endsWord())
					returnList.add(cur.getText());
				
				for(Character c : cur.getValidNextCharacters()){
					queue.add(cur.getChild(c));
				}
				curPosition++;
				
			}
		}
    	 
         return returnList;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}
