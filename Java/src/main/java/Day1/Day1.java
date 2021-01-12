package main.java.Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day1 {
   public static final Integer YEAR_2020 = 2020;
   
   public static void main(String[] args) throws IOException {
      List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day1/Day1.in"));
      List<Integer> numbers = lines.stream().map(line -> Integer.parseInt(line))
            .collect(Collectors.toList());
      
      System.out.println(get2020(numbers));
   }
   
   public static int get2020(List<Integer> numbers) {
      Map<Integer, Integer> sumPairs = new HashMap<>();
      for (int num : numbers) {
         sumPairs.put(num, YEAR_2020 - num);
      }
      for (Integer k : sumPairs.keySet()) {
         if (sumPairs.containsKey(sumPairs.get(k))) {
            return k * sumPairs.get(k);
         }
      }
      
      return 0;
   }
}
