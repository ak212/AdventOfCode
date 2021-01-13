import { readFileSync } from 'fs'

const YEAR_2020: number = 2020

const partOne = (numbers: number[]): number => {
   for (const num of numbers) {
      if (numbers.includes(YEAR_2020 - num)) {
         return num * (YEAR_2020 - num)
      }
   }
}

const partTwo = (numbers: number[]): number => {
   for (let i = 0; i < numbers.length - 1; i++) {
      for (let k = i + 1; k < numbers.length; k++) {
         if (numbers.find((n) => n === YEAR_2020 - (numbers[i] + numbers[k]))) {
            return (YEAR_2020 - (numbers[i] + numbers[k])) * numbers[i] * numbers[k]
         }
      }
   }
   return 0
}

const numbers: number[] = readFileSync("./Day1.in", "utf-8")
   .split("\n")
   .map((line) => parseInt(line))

console.log(partOne(numbers))
console.log(partTwo(numbers))

/* tsc Day1.ts
   node Day1.js */
