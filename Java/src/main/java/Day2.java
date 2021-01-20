package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day2 {
   private static class PasswordValidator {
      char letter;
      Integer max;
      Integer min;
      String password;
      
      public PasswordValidator(Integer min, Integer max, char letter, String password) {
         this.letter = letter;
         this.max = max;
         this.min = min;
         this.password = password;
      }
      
   }
   
   public static void main(String[] args) throws IOException {
      List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day2.in"));
      List<PasswordValidator> candidates = lines.stream().map(line -> {
         String[] result = line.split(" ");
         String[] minMax = result[0].split("-");
         return new PasswordValidator(Integer.valueOf(minMax[0]), Integer.valueOf(minMax[1]),
               result[1].charAt(0), result[2]);
      }).collect(Collectors.toList());
      
      System.out.println(partOne(candidates));
      System.out.println(partTwo(candidates));
   }
   
   public static int count(String s, char c) {
      Matcher matcher = Pattern.compile(String.valueOf(c)).matcher(s);
      int count = 0;
      
      while (matcher.find()) {
         count++;
      }
      
      return count;
   }
   
   public static long partOne(List<PasswordValidator> candidates) {
      return candidates.stream().filter(candidate -> {
         int count = count(candidate.password, candidate.letter);
         return count >= candidate.min && count <= candidate.max;
      }).count();
   }
   
   public static long partTwo(List<PasswordValidator> candidates) {
      return candidates.stream().filter(candidate -> {
         return candidate.password.charAt(candidate.min - 1) == candidate.letter
               ^ candidate.password.charAt(candidate.max - 1) == candidate.letter;
      }).count();
   }
}
