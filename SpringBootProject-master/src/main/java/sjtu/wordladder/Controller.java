package sjtu.wordladder;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/main")

public class Controller {
    static StringBuilder resultBuilder = new StringBuilder();
    @RequestMapping(value = "/word1={}&word2={}", method = RequestMethod.GET)

    public static String main(@PathVariable String word1, @PathVariable String word2) {
        HashSet<String> dict = new HashSet<String>();

        try{
            dict = DicGenerate("EnglishWords.txt");
        }catch (Exception e){
            e.printStackTrace();
        }


        if(word1.length() != word2.length()){
            return "Two word is not in the same size, exit.";
        }

        return findWay(word1, word2, dict);

    }

    public static HashSet<String> DicGenerate(String filename)throws IOException{
        HashSet<String> result = new HashSet<String>();
        
        DataInputStream in = new DataInputStream(new FileInputStream(filename));
        BufferedReader br  = new BufferedReader(new InputStreamReader(in));
        String temp;

        while((temp = br.readLine())!=null)
            result.add(temp);
        br.close();
        return result;
    }


    public static String findWay(String word1, String word2, HashSet<String> dict){
        if(word1.equals(word2)){
            System.out.println(word1);
            return word1;
        }

        if(!(dict.contains(word1) && dict.contains(word2))){
            return "No Word Ladder Exist";
        }



        Stack<String> stack0 = new Stack<String>();
        stack0.push(word1);

        Queue<Stack<String>> queue = new LinkedList<Stack<String>>();
        queue.add(stack0);

        Stack<String> temp;

        while(!queue.isEmpty()){
            temp = queue.poll();
            String curWord = temp.peek();

            for(int i=0; i<curWord.length(); i++){

                String front, end;
                if(i==0){
                    front = "";
                    end = curWord.substring(i+1);
                }else if(i==curWord.length()-1){
                    front = curWord.substring(0, i);
                    end = "";
                }else{
                    front = curWord.substring(0, i);
                    end = curWord.substring(i+1);
                }

                for(int j = (int)'a'; j < (int)'z'+1; j++){
                    String newWord = front + (char)j + end;
                    
                    if(dict.contains(newWord)){
                        temp.push(newWord);
                        if(newWord.equals(word2)){
                            for(String s : temp){
                                resultBuilder.append(s);
                            }
                            return resultBuilder.toString();
                        }else{
                            Stack<String> newStack = (Stack<String>)temp.clone();
                            queue.add(newStack);
                        }
                    }
                }


            }
        }

        return "Word Ladder not exist";
    }
}