package me.goodbee.adventofcode.days;

import me.goodbee.adventofcode.util.Day;

import java.util.ArrayList;
import java.util.Arrays;

public class Day01 extends Day {
    @Override
    public String getInputFile() {
        return "day01.txt";
    }

    @Override
    public String part1(String input) {
        String[] pairs = input.split("\n");

        int[] leftNums = new int[pairs.length];
        int[] rightNums = new int[pairs.length];

        for (int i = 0; i < pairs.length; i++) {
            String[] arr = pairs[i].split(" {3}");

            leftNums[i] = Integer.parseInt(arr[0]);
            rightNums[i] = Integer.parseInt(arr[1]);
        }

        Arrays.sort(leftNums);
        Arrays.sort(rightNums);

        int totalDistance = 0;

        for (int i = 0; i < pairs.length; i++) {
            int leftNum = leftNums[i];
            int rightNum = rightNums[i];

            totalDistance += leftNum > rightNum ? leftNum - rightNum : rightNum - leftNum;
        }

        return Integer.toString(totalDistance);
    }

    @Override
    public String part2(String input) {
        String[] pairs = input.split("\n");

        int[] leftNums = new int[pairs.length];
        int[] rightNums = new int[pairs.length];

        for (int i = 0; i < pairs.length; i++) {
            String[] arr = pairs[i].split(" {3}");

            leftNums[i] = Integer.parseInt(arr[0]);
            rightNums[i] = Integer.parseInt(arr[1]);
        }

        int similarityScore = 0;

        for (int i = 0; i < pairs.length; i++) {
            int leftnum = leftNums[i];
            int multiply = 0;

            for (int rightIndex = 0; rightIndex < pairs.length; rightIndex++) {
                int rightNum = rightNums[rightIndex];

                if(leftnum == rightNum) {
                    multiply += 1;
                }
            }

            similarityScore += leftnum * multiply;
        }

        return Integer.toString(similarityScore);
    }
}
