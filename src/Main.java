import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //讀取t2.txt檔案
        String filename = "t2.txt";
        List<String> data = readDataFromFile(filename);
        Map<String, List<Integer>> suffixTree = buildSuffixTree(data);
        printSuffixTree(suffixTree);
    }

    private static List<String> readDataFromFile(String filename) {
        List<String> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading data from file: " + e.getMessage());
        }

        return data;
    }

    private static Map<String, List<Integer>> buildSuffixTree(List<String> data) {
        Map<String, List<Integer>> suffixTree = new HashMap<>();

        for (String line : data) {
            String[] parts = line.split(":");
            String key = parts[0];
            String[] values = parts[1].split(",");

            List<Integer> suffixList = new ArrayList<>();
            for (String value : values) {
                suffixList.add(Integer.parseInt(value));
            }

            suffixTree.put(key, suffixList);
        }

        return suffixTree;
    }

    private static void printSuffixTree(Map<String, List<Integer>> suffixTree) {
        for (Map.Entry<String, List<Integer>> entry : suffixTree.entrySet()) {
            String key = entry.getKey();
            List<Integer> suffixList = entry.getValue();

            System.out.print(key + ": ");
            for (Integer suffix : suffixList) {
                System.out.print(suffix + " ");
            }
            System.out.println();
        }
    }
}