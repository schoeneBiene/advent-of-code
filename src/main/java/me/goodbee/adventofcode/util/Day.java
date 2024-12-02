package me.goodbee.adventofcode.util;

public abstract class Day {
    public void run() {
        String input = Input.getTxt(getInputFile());

        String first = part1(input);
        String second = part2(input);

        System.out.println(this.getClass().getSimpleName() + " Part 1 output: " + first);
        System.out.println(this.getClass().getSimpleName() + " Part 2 output: " + second);
        System.out.println(" ");
    }

    public abstract String getInputFile();
    public abstract String part1(String input);
    public abstract String part2(String input);
}
