import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Adam Cramer
 * Implements a tree consisting of SpellingNodes
 */
public class SpellingTree {
    public final SpellingNode root;//Points to root of tree, convention states this should be all caps, however instructions indicate it shouldn't be

    public SpellingTree(){
        root = new SpellingNode(' ');
    }

    /**
     * Adds word to SpellingTree, Instructions dont indicate what it should return so I will assume
     * that it will return false if the word has already been added
     */
    public boolean addWord(String word){
        SpellingTreeIterator iter = new SpellingTreeIterator();
        char[] wordArray = word.toCharArray();
        int count = 0;//basic counter to optimize code

        while(count < wordArray.length && iter.hasChild(wordArray[count])){//iterates through tree until the next letter in the word doesnt exist or it has iterated through the entire word
            iter.goTo(wordArray[count]);
            count++;
        }

        if(count==wordArray.length&& iter.current.getCorrect())//Checks if word was already in tree
            return false;

        for(int i = count; i<wordArray.length; i++){//puts missing letters in
            iter.newChild(wordArray[i]);
            iter.goTo(wordArray[i]);
        }
        iter.current.setCorrect(true);//marks last letter as end of word
        return true;
    }

    /**
     * Checks for word in SpellingTree
     * @return true if word is present
     */
    public boolean checkWord(String word){
        SpellingTreeIterator iter = new SpellingTreeIterator();
        char[] wordArray = word.toCharArray();

        for(char val: wordArray){
            if(!iter.hasChild(val))
                return false;
            iter.goTo(val);
        }
        return iter.current.getCorrect();
    }

    private class SpellingTreeIterator{
        SpellingNode current;
        private Stack<SpellingNode> trace;

        public SpellingTreeIterator(){
            current = root;
        }

        /**
         * @return true if current has a child associated with val
         */
        public boolean hasChild(char val){
            try {
                current.getChildAt(val);
                return true;
            }
            catch (NoSuchElementException e){
                return false;
            }
        }

        /**
         * Moves Iterator to SpellingNode associated with val
         */
        public void goTo(char val){
            trace.push(current);
            current = current.getChildAt(val);
        }

        /**
         * moves iterator up a level in the tree
         */
        public void prev(){
            if(trace.empty())
                throw new IndexOutOfBoundsException("trace is empty");
            current = trace.pop();
        }

        /**
         * adds a child to current using addChild method from SpellingNode
         */
        public void newChild(char val){
            current.addChild(val);
        }



    }

}
