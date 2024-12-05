package me.goodbee.adventofcode.days;

import me.goodbee.adventofcode.util.Day;

public class Day04 extends Day {

    public static final int[][] DIRECTIONS = new int[][]{
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1},
            {1, 1},
            {-1, 1},
            {1, -1},
            {-1, -1}
    };

    @Override
    public String getInputFile() {
        return "day04.txt";
    }

    @Override
    public String part1(String input) {
        char[][] grid = new char[input.split("\n").length][input.split("\n")[0].length()];
        int count = 0;

        String[] lines = input.split("\n");

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            for (int j = 0; j < line.length(); j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == 'X') {
                    count += countXMAS(grid, x, y);
                }
            }
        }

        return Integer.toString(count);
    }

    private int countXMAS(char[][] grid, int x, int y) {
        int count = 0;

        for (int[] dir : DIRECTIONS) {
            int nx = x, ny = y;
            boolean found = true;

            for (char letter : "XMAS".toCharArray()) {
                if (!isInBounds(grid, nx, ny) || grid[ny][nx] != letter) {
                    found = false;
                    break;
                }
                nx += dir[0];
                ny += dir[1];
            }

            if (found) {
                count++;
            }
        }

        return count;
    }

    @Override
    public String part2(String input) {
        char[][] grid = new char[input.split("\n").length][input.split("\n")[0].length()];
        int count = 0;

        String[] lines = input.split("\n");

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            for (int j = 0; j < line.length(); j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        for (int y = 0; y < grid.length; y++) {
            if(y == 0 || y == grid.length - 1) continue;
            for (int x = 0; x < grid[y].length; x++) {
                if(x == 0 || x == grid[y].length - 1) continue;
                if (grid[y][x] == 'A') {
                    count += countXMasPattern(grid, x, y) ? 1 : 0;
                }
            }
        }

        return Integer.toString(count);
    }

    private boolean countXMasPattern(char[][] grid, int x, int y) {
        boolean left = false;
        boolean right = false;

        if((grid[y - 1][x - 1] == 'M' && grid[y + 1][x + 1] == 'S') || (grid[y - 1][x - 1] == 'S' && grid[y + 1][x + 1] == 'M')) {
            left = true;
        }

        if((grid[y - 1][x + 1] == 'M' && grid[y + 1][x - 1] == 'S') || (grid[y - 1][x + 1] == 'S' && grid[y + 1][x - 1] == 'M')) {
            right = true;
        }

        return left && right;
    }


    private boolean isInBounds(char[][] grid, int x, int y) {
        return x >= 0 && y >= 0 && y < grid.length && x < grid[y].length;
    }

}
