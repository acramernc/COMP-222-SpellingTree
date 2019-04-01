import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class SpellChecker {
    private static SpellingTree myTree;

    public static void main(String[] args) throws FileNotFoundException{
        myTree = new SpellingTree();
        readWords("dictionary.txt");
        myTree.printWords("", myTree.root);
        System.out.println(checkWords("test.txt"));
    }

    public static int checkWords(String fileName) throws FileNotFoundException {
        File inputFile = new File(fileName);
        Scanner in = new Scanner(inputFile);
        in.useDelimiter(" ");
        String input;
        int numWrong = 0;

        while (in.hasNext()){
            input = in.next();
            input = input.replaceAll("[^a-zA-Z ]", "").toUpperCase();//Removes punctuation and makes everything uppercase
            if(!myTree.checkWord(input)){
                System.out.println(input);
                numWrong++;
            }
        }
        return numWrong;
    }

    public static void readWords(String fileName) throws FileNotFoundException{
        File inputFIle = new File(fileName);
        Scanner in = new Scanner(inputFIle);
        in.useDelimiter(" ");
        String input;

        while (in.hasNext()){
            input = in.next();
            input = input.replaceAll("[^a-zA-Z ]", "").toUpperCase();
            myTree.addWord(input);
        }

    }
}
