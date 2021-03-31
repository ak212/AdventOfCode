package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day3 {
   public static void main(String[] args) throws IOException {
      List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day3.in"));
      
      System.out.println(partOne(lines));
      System.out.println(partTwo(lines));
   }
   
   public static long partOne(List<String> lines) {
      return traverse(lines, 3, 1);
   }
   
   private static long traverse(List<String> lines, int x, int y) {
      int len = lines.get(0).length();
      int count = 0;
      
      for (int i = y, k = x; i < lines.size(); i += y, k += x) {
         if (lines.get(i).charAt(k % len) == '#') {
            count++;
         }
      }
      
      return count;
   }
   
   public static long partTwo(List<String> lines) {
      return traverse(lines, 1, 1) * traverse(lines, 3, 1) * traverse(lines, 5, 1)
            * traverse(lines, 7, 1) * traverse(lines, 1, 2);
   }
}
