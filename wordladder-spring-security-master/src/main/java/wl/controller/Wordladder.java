package wl.controller;


import java.io.*;
import java.util.*;

public class Wordladder {
    /*several global variable*/
    //the queue containing stacks of words
    public static Queue<Stack<String>>ladder = new LinkedList<Stack<String>>();
    //all words in the dictionary
    public static Set<String>wordSet = new HashSet<String>();
    //the already-used neighbor
    public static Set<String>used_wordSet = new HashSet<String>();
    public static String alp = "abcdefghijklmnopqrstuvwxyz";
    private static boolean ladder_exist = false;
    public static String word1="";
    public static String word2="";
    public static void neighbor(String w,String w2,Stack<String>curs) {
        // add the neighbor words to the stack
        int len = w.length();
        for (int i = 0; i<len; ++i) {
            StringBuffer neigh = new StringBuffer(w);
            for (int j = 0; j < alp.length();j++) {
                neigh.setCharAt(i,alp.charAt(j));
                String neighStr = new String(neigh);
                if (wordSet.contains(neighStr) &&
                        !used_wordSet.contains(neighStr)) {
                    Stack<String>newStack = (Stack<String>)curs.clone();
                    newStack.add(neighStr);
                    used_wordSet.add(neighStr);
                    ladder.offer(newStack);
                }
            }
        }
    }

    private static FindLadder ladder_to_word(String word2, Stack<String>cur_stack) {
        //find the ladder ending with word2
        while (ladder.size() != 0) {
            cur_stack = ladder.peek();
            String cur_word = cur_stack.peek();
            if (cur_word.equals(word2)) {
                ladder_exist = true;
                return printLadder(word1,word2,cur_stack);
            }
            else
                neighbor(cur_word,word2,cur_stack);
            ladder.poll();
        }
        return printLadder(word1,word2,cur_stack);
    }

    private static FindLadder printLadder(String w1, String word2, Stack<String>cur_stack) {
        if (ladder_exist) {
            int size = cur_stack.size();
            String res = word2;
            System.out.println("word1:"+word1);
            //String[] result = new String[size];
            for (int i = size-1; i >= 0 ; i--) {
                String w = cur_stack.peek();
                System.out.println("w:"+w);
                if(i == size-1)
                    continue;
                res =  w + " -> "+ res ;
                cur_stack.pop();
            }
            res =  word1 +" -> " +res;
            return new FindLadder(0,"",res);
        }
        else
            return new FindLadder(1,"No word ladder found from "+word1+" back to "
                    +word2+".","");
    }

    public static FindLadder searchLadder(String w1,String w2){
        //complete process of finding ladder
        word1=w1;
        word2=w2;
        Stack<String>cur_stack = new Stack<String>();
        Stack<String>wStack = new Stack<String>();
        used_wordSet.add(w1);
        wStack.push(w1);
        ladder.offer(wStack);
        return ladder_to_word(w2,cur_stack);
    }

    public  void clear(){
        //clear the collections to get ready for the new loop
        ladder.clear();
        used_wordSet.clear();
        ladder_exist = false;
    }

    public FindLadder GetLadder(String fileName, String word1, String word2) throws IOException
    {

        FindLadder result;
        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
        File in = new File(fileName);
        if (!in.exists()) {
            return new FindLadder(1,"Unable to open that file.","");
        }

        FileReader file = new FileReader(fileName);
        BufferedReader read_file = new BufferedReader(file);
        boolean eof = false;
        String line;
        while(true) {
            line = read_file.readLine();
            if (line == null)
                break;
            for (String word : line.split(" ")) {
                wordSet.add(word);
            }
        }
        while (true) {

            if(word1.equals("")){
                return new FindLadder(1,"word1 is empty","");
            }

            if(word2.equals("")){
                return new FindLadder(1,"word2 is empty","");
            }
            word1 = word1.toLowerCase();
            word2 = word2.toLowerCase();
            //several valid tests
            if(word1.length() != word2.length())
                return new FindLadder(1,"The two words must be the same length.","");
            else if (word1.equals(word2))
                return new FindLadder(1,"The two words must be different.","");
            else {

                if (!(wordSet.contains(word1) && wordSet.contains(word2)))
                    return new FindLadder(1,"The two words must be found in the dictionary.","");
                else
                    result = searchLadder(word1, word2);
            }
            clear();
            return result;
        }
    }
}
