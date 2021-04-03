package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day7 {
   public static class Bag {
      String color;
      int quantity;
      List<Bag> containedBags;
      
      public Bag(String color) {
         super();
         this.color = color;
         this.containedBags = new ArrayList<>();
      }
      
      public Bag(String color, int quantity) {
         super();
         this.color = color;
         this.quantity = quantity;
         this.containedBags = new ArrayList<>();
      }
      
      protected void addContainedBag(Bag bag) {
         this.containedBags.add(bag);
      }
   }
   
   public static void main(String[] args) throws IOException {
      String input = Files.readString(Paths.get("src/main/resources/Day7.in"));
      List<Bag> bags = List.of(input.split("\\r\\n")).stream().map(bagString -> {
         Matcher m = Pattern.compile("(\\S+ \\S+)").matcher(bagString);
         Bag bag = new Bag(m.find() ? m.group(1) : "");
         
         Matcher m2 = Pattern.compile("((\\d) (\\S+ \\S+))+").matcher(bagString);
         while (m2.find()) {
            Bag childBag = new Bag(m2.group(3), Integer.parseInt(m2.group(2)));
            bag.addContainedBag(childBag);
         }
         return bag;
      }).collect(Collectors.toList());
      
      System.out.println(partOne(bags));
      System.out.println(partTwo(bags));
   }
   
   public static boolean bagTraverse(Map<String, List<String>> bagMap, String target,
         String current) {
      if (bagMap.get(current).stream().anyMatch(containedBag -> containedBag.equals(target))) {
         return true;
      }
      else {
         return (bagMap.get(current).stream().anyMatch(bag -> {
            return bagTraverse(bagMap, target, bag);
         }));
      }
   }
   
   public static int bagQuantityTraverse(Map<String, List<Bag>> bagMap, String current) {
      if (bagMap.get(current).size() == 0) {
         return 1;
      }
      return bagMap.get(current).stream().map(bag -> {
         int traverseVal = bagQuantityTraverse(bagMap, bag.color);
         if (traverseVal == 1) {
            return bag.quantity;
         }
         else {
            return bag.quantity + (bag.quantity * traverseVal);
         }
      }).reduce(0, (a, b) -> a + b, Integer::sum);
   }
   
   public static int partOne(List<Bag> bags) {
      Map<String, List<String>> bagMap = new HashMap<>();
      bags.stream().forEach(bag -> {
         bagMap.put(bag.color,
               bag.containedBags.stream().map(bagx -> bagx.color).collect(Collectors.toList()));
      });
      return (int) bagMap.keySet().stream().filter(bag -> {
         return bagTraverse(bagMap, "shiny gold", bag);
      }).count();
   }
   
   public static int partTwo(List<Bag> bags) {
      Map<String, List<Bag>> bagMap = new HashMap<>();
      bags.stream().forEach(bag -> {
         bagMap.put(bag.color, bag.containedBags);
      });
      return bagQuantityTraverse(bagMap, "shiny gold");
   }
}
