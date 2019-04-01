/**
 * @author Adam Cramer
 * Represents a single node in the SpellingTree
 */
public class SpellingNode {
    char value;//The letter stored by the node, root node stores ' '
    private SpellingNode[] children; //Array of node's children
    private boolean correctWord; //True if this node is at the end of the word
    private int childrenIndex;//Stores the earliest open index in the children array

    public SpellingNode(char value){
        this.value = value;
        children = new SpellingNode[26];
        correctWord = false;
        childrenIndex=0;
    }

    /**Adds a SpellingNode to children if it doesnt already exist
     * @param val value of the child
     * @return false if child already exists
     */
    public boolean addChild(char val){
        for(int i = 0; i<childrenIndex; i++){//Check current children for a child that matches val
            if(children[i].value == val)
                return false;
        }


    }

    public boolean getCorrect() {
        return correctWord;
    }

    public void setCorrect(boolean correctWord) {
        this.correctWord = correctWord;
    }
}
