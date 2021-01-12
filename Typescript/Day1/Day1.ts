import { readFileSync } from 'fs'

const YEAR_2020: number = 2020

const productOf2020Sum = (numbers: number[]): number => {
   const sumPairs: Map<number, number> = new Map()
   for (const num of numbers) {
      sumPairs.set(num, YEAR_2020 - num)
   }

   for (const k of Array.from(sumPairs.keys())) {
      if (sumPairs.has(sumPairs.get(k))) {
         return k * sumPairs.get(k)
      }
   }
}

const numbers: number[] = readFileSync("./Day1.in", "utf-8")
   .split("\n")
   .map((line) => parseInt(line))

console.log(productOf2020Sum(numbers))
