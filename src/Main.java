import java.io.*;
import java.util.*;

public class Main{
    public static void main(String args[]){

        //Scanner宣告
        Scanner scn = new Scanner(System.in);

        //Hashmap宣告
        Map<Integer, String> NosetMap = new HashMap<>();
        Map<String, String> datasetMap = new HashMap<>();

        //獲得輸入資料
        System.out.print("請輸入資料：");
        String input = scn.next();

        //Java例外處理
        try {
            BufferedReader br = new BufferedReader(new FileReader("t2.txt"));
            String line;

            //先將編號與資料分開
            int no = 1;
            while((line = br.readLine()) != null){
                String[] parts = line.split(":");
                String title = parts[0].trim();
                String data = parts[1].trim();
                NosetMap.put(no,title);
                datasetMap.put(title,data);
                no++;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        //為每一筆資料建樹並判斷內部是否有input資料群，若有則印出
        int no = 1;
        while(NosetMap.get(no)!=null){
            SuffixTree suffixTree = new SuffixTree(datasetMap.get(NosetMap.get(no)));
            boolean isIn = suffixTree.search(input);

            if (isIn)
                System.out.printf(NosetMap.get(no) + ":" + datasetMap.get(NosetMap.get(no)) + "\r\n");
            no++;
        }

    }
}

class SuffixTreeNode{
    Map<Character, SuffixTreeNode> children = new HashMap<>();
}

class SuffixTree{
    SuffixTreeNode root;

    public SuffixTree(String text){
        root = new SuffixTreeNode();
        buildSuffixTree(text);
    }

    private void buildSuffixTree(String text){
        for (int i=0;i<text.length();i++){
            String suffix = text.substring(i);
            insertSuffix(suffix,i);
        }
    }

    private void insertSuffix(String suffix, int index){
        SuffixTreeNode current = root;
        for (char c : suffix.toCharArray()){
            if(!current.children.containsKey(c))
                current.children.put(c, new SuffixTreeNode());
            current = current.children.get(c);
        }
    }

    public boolean search(String pattern){
        SuffixTreeNode current = root;
        for (char c : pattern.toCharArray()){
            if(!current.children.containsKey(c))
                return false;
            current = current.children.get(c);
        }
        return true;
    }

}