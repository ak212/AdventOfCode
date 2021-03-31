package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Day5 {
   public static final char LEFT = 'L';
   public static final char RIGHT = 'R';
   public static final char FRONT = 'F';
   public static final char BACK = 'B';
   
   public static void main(String[] args) throws IOException {
      List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day5.in"));
      
      System.out.println(partOne(lines));
      System.out.println(partTwo(lines));
   }
   
   public static int getSeatId(String boardingPass) {
      double rowLowerBound = 0;
      double rowUpperBound = 127;
      double columnLowerBound = 0;
      double columnUpperBound = 7;
      
      for (int i = 0; i < boardingPass.length(); i++) {
         char c = boardingPass.charAt(i);
         if (c == FRONT) {
            rowUpperBound = Math.floor((rowUpperBound + rowLowerBound) / 2);
         }
         else if (c == BACK) {
            rowLowerBound = Math.ceil((rowUpperBound + rowLowerBound) / 2);
         }
         else if (c == LEFT) {
            columnUpperBound = Math.floor((columnUpperBound + columnLowerBound) / 2);
         }
         else if (c == RIGHT) {
            columnLowerBound = Math.ceil((columnUpperBound + columnLowerBound) / 2);
         }
         
      }
      return (int) (rowUpperBound * 8 + columnUpperBound);
   }
   
   public static int partOne(List<String> lines) {
      int maxSeatId = -1;
      for (String line : lines) {
         int seatId = getSeatId(line);
         maxSeatId = maxSeatId < seatId ? seatId : maxSeatId;
      }
      return maxSeatId;
   }
   
   public static int partTwo(List<String> lines) {
      Set<Integer> seatIds = lines.stream().map(line -> getSeatId(line))
            .collect(Collectors.toSet());
      Optional<Integer> yourSeat = seatIds.stream()
            .filter(seatId -> !seatIds.contains(seatId + 1) && seatIds.contains(seatId + 2))
            .findFirst();
      return yourSeat.get() + 1;
   }
}
