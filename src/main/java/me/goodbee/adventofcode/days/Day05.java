package me.goodbee.adventofcode.days;

import me.goodbee.adventofcode.util.Day;

import java.util.*;

import static java.lang.Integer.parseInt;

public class Day05 extends Day {
    @Override
    public String getInputFile() {
        return "day05.txt";
    }

    public static String[] reverse(String[] arr) {
        List<String> list = Arrays.asList(arr);
        Collections.reverse(list);
        return list.toArray(new String[0]);
    }

    public ArrayList<Integer> makeSomeNumbers(int first, int second) {
        ArrayList<Integer> a = new ArrayList<>();

        a.add(first);
        a.add(second);

        return a;
    }

    @Override
    public String part1(String input) {
        String[] lines = input.split("\n");

        ArrayList<ArrayList<Integer>> conditions = new ArrayList<>();

        for (String line : lines) {
            if(!line.matches("\\d\\d\\|\\d\\d")) break;

            String[] nums = line.split("\\|");
            conditions.add(makeSomeNumbers(parseInt(nums[0]), parseInt(nums[1])));
        }

        int sum = 0;

        for(String line : lines) {
            if(line.matches("\\d\\d\\|\\d\\d")) continue;
            if(line == "") continue;

            String[] nums = line.split(",");
            String[] revNums = reverse(nums);

            boolean bad = false;
            ArrayList<ArrayList<Integer>> relevant = new ArrayList<>();

            for (String numString : revNums) {
                int num = parseInt(numString);

                for (ArrayList<Integer> condition : conditions) {
                    if (condition.get(0) == num) {
                        relevant.add(makeSomeNumbers(num, condition.get(1)));
                    }
                }

                for (ArrayList<Integer> rel : relevant) {
                    if(num == rel.get(1)) {
                        bad = true;
                    }
                }

                if(bad) break;
            }

            if(!bad) {
                sum += parseInt(nums[(nums.length - 1) / 2]);
            }
        }

        return Integer.toString(sum);
    }

    private static ArrayList<Integer> reorderUpdate(String update, ArrayList<ArrayList<Integer>> conditions) {
        String[] stringList = update.split(",");
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String s : stringList) {
            numbers.add(parseInt(s));
        }

        boolean sorted = false;
        while (!sorted) {
            sorted = true;

            for (ArrayList<Integer> condition : conditions) {
                Integer left = condition.get(0);
                Integer right = condition.get(1);

                if (numbers.contains(left) && numbers.contains(right)) {
                    int leftIndex = numbers.indexOf(left);
                    int rightIndex = numbers.indexOf(right);

                    if (leftIndex > rightIndex) {
                        Collections.swap(numbers, leftIndex, rightIndex);
                        sorted = false;
                    }
                }
            }
        }

        return numbers;
    }

    @Override
    public String part2(String input) {
        String[] lines = input.split("\n");

        ArrayList<ArrayList<Integer>> conditions = new ArrayList<>();

        for (String line : lines) {
            if(!line.matches("\\d\\d\\|\\d\\d")) break;

            String[] nums = line.split("\\|");
            conditions.add(makeSomeNumbers(parseInt(nums[0]), parseInt(nums[1])));
        }

        ArrayList<String> badLines = new ArrayList<>();

        for(String line : lines) {
            if(line.matches("\\d\\d\\|\\d\\d")) continue;
            if(line == "") continue;

            String[] nums = line.split(",");
            String[] revNums = reverse(nums);

            boolean bad = false;
            ArrayList<ArrayList<Integer>> relevant = new ArrayList<>();

            for (String numString : revNums) {
                int num = parseInt(numString);

                for (ArrayList<Integer> condition : conditions) {
                    if (condition.get(0) == num) {
                        relevant.add(makeSomeNumbers(num, condition.get(1)));
                    }
                }

                for (ArrayList<Integer> rel : relevant) {
                    if(num == rel.get(1)) {
                        bad = true;
                    }
                }

                if(bad) break;
            }

            if(bad) {
                badLines.add(line);
            }
        }

        int sum = 0;

        for (String badLine : badLines) {
            ArrayList<Integer> newList = reorderUpdate(badLine, conditions);

            sum += newList.get((newList.size() - 1) / 2);
        }

        return Integer.toString(sum);
    }
}
