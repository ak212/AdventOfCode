package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {
   public static final Integer YEAR_2020 = 2020;
   
   public static void main(String[] args) throws IOException {
      List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day1.in"));
      List<Integer> numbers = lines.stream().map(line -> Integer.parseInt(line))
            .collect(Collectors.toList());
      
      System.out.println(partOne(numbers));
      System.out.println(partTwo(numbers));
   }
   
   public static int partOne(List<Integer> numbers) {
      for (int num : numbers) {
         if (numbers.contains(YEAR_2020 - num)) {
            return num * (YEAR_2020 - num);
         }
      }
      return 0;
   }
   
   public static int partTwo(List<Integer> numbers) {
      for (int i = 0; i < numbers.size() - 1; i++) {
         for (int k = i + 1; k < numbers.size(); k++) {
            if (numbers.contains(YEAR_2020 - (numbers.get(i) + numbers.get(k)))) {
               return (YEAR_2020 - (numbers.get(i) + numbers.get(k))) * numbers.get(i)
                     * numbers.get(k);
            }
         }
      }
      
      return 0;
   }
}
