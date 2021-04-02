package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day6 {
   public static void main(String[] args) throws IOException {
      String input = Files.readString(Paths.get("src/main/resources/Day6.in"));
      String[] candidates = input.split("\\r\\n\\r\\n");
      
      System.out.println(partOne(List.of(candidates)));
      System.out.println(partTwo(List.of(candidates)));
   }
   
   public static int partOne(List<String> declarationGroups) {
      return declarationGroups.stream().map(declarationGroup -> {
         Set<Character> answers = new HashSet<>();
         
         for (char c : declarationGroup.replaceAll("\\W", "").toCharArray()) {
            answers.add(c);
         }
         return answers.size();
      }).reduce(0, (a, b) -> a + b, Integer::sum);
   }
   
   public static int partTwo(List<String> declarationGroups) {
      return declarationGroups.stream().map(declarationGroup -> {
         Map<Character, Integer> answers = new HashMap<>();
         String[] declarations = declarationGroup.split("\\r\\n");
         
         for (String declaration : declarations) {
            for (char c : declaration.toCharArray()) {
               if (answers.containsKey(c)) {
                  answers.put(c, answers.get(c) + 1);
               }
               else {
                  answers.put(c, 1);
               }
            }
         }
         
         return (int) answers.keySet().stream().filter(key -> {
            return answers.get(key) == declarations.length;
         }).count();
      }).reduce(0, (a, b) -> a + b, Integer::sum);
   }
}
