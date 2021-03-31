package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day4 {
   private static class Credentials {
      String byr;
      String iyr;
      String eyr;
      String hgt;
      String hcl;
      String ecl;
      String pid;
      
      public Credentials(String byr, String iyr, String eyr, String hgt, String hcl, String ecl,
            String pid) {
         super();
         this.byr = byr;
         this.iyr = iyr;
         this.eyr = eyr;
         this.hgt = hgt;
         this.hcl = hcl;
         this.ecl = ecl;
         this.pid = pid;
      }
      
      public boolean isLooseValid() {
         return !this.byr.equals("") && !this.iyr.equals("") && !this.eyr.equals("")
               && !this.hgt.equals("") && !this.hcl.equals("") && !this.ecl.equals("")
               && !this.pid.equals("");
      }
      
      private boolean isInvalidBirthYear() {
         return !isInt(this.byr) || Integer.valueOf(byr) < 1920 || Integer.valueOf(byr) > 2002;
      }
      
      private boolean isInvalidIssueYear() {
         return !isInt(this.iyr) || Integer.valueOf(iyr) < 2010 || Integer.valueOf(iyr) > 2020;
      }
      
      private boolean isInvalidExpirationYear() {
         return !isInt(this.eyr) || Integer.valueOf(eyr) < 2020 || Integer.valueOf(eyr) > 2030;
      }
      
      private boolean isInvalidHeight() {
         if (this.hgt.matches("\\d{2,3}\\w{2}")) {
            Pattern p = Pattern.compile("(\\d+)(\\w+)");
            Matcher m = p.matcher(this.hgt);
            if (m.find()) {
               String value = m.group(1);
               String unit = m.group(2);
               if ((unit.equals("cm")
                     && (Integer.valueOf(value) < 150 || Integer.valueOf(value) > 193))
                     || (unit.equals("in")
                           && (Integer.valueOf(value) < 59 || Integer.valueOf(value) > 76)
                           || !List.of("in", "cm").contains(unit))) {
                  return true;
               }
            }
            else {
               return true;
            }
         }
         else {
            return true;
         }
         return false;
      }
      
      private boolean isInvalidHairColor() {
         return !this.hcl.matches("#[0-9a-f]{6}");
      }
      
      private boolean isInvalidEyeColor() {
         return !List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(this.ecl);
      }
      
      private boolean isInvalidPassportId() {
         return !this.pid.matches("\\d{9}");
      }
      
      public boolean isStrictValid() {
         return !isInvalidBirthYear() && !isInvalidIssueYear() && !isInvalidExpirationYear()
               && !isInvalidHeight() && !isInvalidHairColor() && !isInvalidEyeColor()
               && !isInvalidPassportId();
      }
   }
   
   public static boolean isInt(String str) {
      return str.matches("\\d+");
   }
   
   public static void main(String[] args) throws IOException {
      String input = Files.readString(Paths.get("src/main/resources/Day4.in"));
      String[] candidates = input.split("\\r\\n\\r\\n");
      List<Credentials> credentials = List.of(candidates).stream()
            .map(candidate -> new Credentials(findAttribute(candidate, "byr"),
                  findAttribute(candidate, "iyr"), findAttribute(candidate, "eyr"),
                  findAttribute(candidate, "hgt"), findAttribute(candidate, "hcl"),
                  findAttribute(candidate, "ecl"), findAttribute(candidate, "pid")))
            .collect(Collectors.toList());
      
      System.out.println(partOne(credentials));
      System.out.println(partTwo(credentials));
   }
   
   private static String findAttribute(String candidate, String attr) {
      String pattern = "(" + attr + ":(\\S+))";
      Pattern r = Pattern.compile(pattern);
      Matcher m = r.matcher(candidate);
      
      if (m.find()) {
         return m.group(2);
      }
      
      return "";
   }
   
   public static long partOne(List<Credentials> credentials) {
      return credentials.stream().filter(credential -> credential.isLooseValid()).count();
   }
   
   public static long partTwo(List<Credentials> credentials) {
      return credentials.stream().filter(credential -> credential.isStrictValid()).count();
   }
}
