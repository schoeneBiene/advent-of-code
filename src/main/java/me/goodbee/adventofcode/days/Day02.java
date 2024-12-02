package me.goodbee.adventofcode.days;

import me.goodbee.adventofcode.util.Day;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;

public class Day02 extends Day {
    @Override
    public String getInputFile() {
        return "day02.txt";
    }

    private boolean isReportSafe(String[] reportNums) {
        Direction direction = null;

        for (int i = 0; i < reportNums.length; i++) {
            int reportNum = parseInt(reportNums[i]);

            if(direction == null) {
                direction = parseInt(reportNums[i + 1]) < reportNum ? Direction.SINKING : Direction.RISING;
            }

            if(i != 0) {
                int lastReportNum = parseInt(reportNums[i - 1]);

                if(direction == Direction.RISING && reportNum < lastReportNum) {
                    return false;
                }

                if(direction == Direction.SINKING && reportNum > lastReportNum) {
                    return false;
                }

                int diff = abs(reportNum - lastReportNum);

                if(!(diff > 0 && diff <= 3)) {
                    return false;
                }
            }
        }

        return true;
    }

    private class ProblemDamperRes {
        public boolean getIsGood() {
            return isGood;
        }

        public boolean getDidSkip() {
            return didSkip;
        }

        private final boolean isGood;
        private final boolean didSkip;

        public ProblemDamperRes(boolean isGood, boolean didSkip) {
            this.isGood = isGood;
            this.didSkip = didSkip;
        }
    }

    private ProblemDamperRes isGoodProblemDamper(int lastReportNum, int reportNum, Integer nextReportNum, Direction direction, boolean canSkip) {
        // even if it was bad, it could be removed
        if(nextReportNum == null && canSkip) {
            return new ProblemDamperRes(true, false);
        }

        if((direction == Direction.RISING && reportNum < lastReportNum) || (direction == Direction.SINKING && reportNum > lastReportNum)) {
            if(canSkip) {
                return new ProblemDamperRes(isGoodProblemDamper(reportNum, nextReportNum, null, direction, false).getIsGood(), true);
            } else {
                return new ProblemDamperRes(false, false);
            }
        }

        int diff = abs(reportNum - lastReportNum);

        if(!(diff > 0 && diff <= 3)) {
            if(canSkip) {
                return new ProblemDamperRes(isGoodProblemDamper(reportNum, nextReportNum, null, direction, false).getIsGood(), true);
            } else {
                return new ProblemDamperRes(false, false);
            }
        }

        return new ProblemDamperRes(true, false);
    }

    /* private boolean isReportSafeProblemDamper(String[] reportNums, boolean something) {
        Direction direction = null;
        boolean didSkip = something;

        for (int i = 0; i < reportNums.length; i++) {
            int reportNum = parseInt(reportNums[i]);

            if(direction == null) {
                direction = parseInt(reportNums[i + 1]) < reportNum ? Direction.SINKING : Direction.RISING;

                if((direction == Direction.RISING && parseInt(reportNums[i + 2]) < parseInt(reportNums[i + 1])) || (direction == Direction.SINKING && parseInt(reportNums[i + 2]) > parseInt(reportNums[i + 1]))) {
                    if(didSkip) return false;

                    String[] asd = new String[reportNums.length - 1];
                    System.arraycopy(reportNums, 1, asd, 0, reportNums.length - 1);

                    return isReportSafeProblemDamper(asd, true);
                }
            }

            if(i != 0) {
                int lastReportNum = parseInt(reportNums[i - 1]);
                Integer nextReportNum = null;

                if(!(reportNums.length - 1 == i)) {
                    nextReportNum = parseInt(reportNums[i + 1]);
                }

                ProblemDamperRes result = isGoodProblemDamper(lastReportNum, reportNum, nextReportNum, direction, !didSkip);

                if(result.didSkip) {
                    didSkip = true;
                }

                if(!result.isGood) {
                    System.out.println("BAD: " + Arrays.toString(reportNums) + " (Direction: " + direction.toString() + ")");
                    return false;
                }
            }
        }

        System.out.println("GOOD: " + Arrays.toString(reportNums) + " (Direction: " + direction.toString() + ")");
        return true;
    } */

    private boolean isReportSafePart2(String[] reportNums) {
        if(isReportSafe(reportNums)) return true;

        for (int i = 0; i < reportNums.length; i++) {
            ArrayList<String> list = new ArrayList<>(Arrays.asList(reportNums));

            list.remove(i);

            if(isReportSafe(list.toArray(new String[0]))) {
                return true;
            }
        }

        return false;
    }

    private enum Direction {
        RISING,
        SINKING
    }

    @Override
    public String part1(String input) {
        String[] reports = input.split("\n");

        int safe = 0;

        for (String report : reports) {
            if(isReportSafe(report.split(" "))) {
                safe += 1;
            }
        }

        return Integer.toString(safe);
    }

    @Override
    public String part2(String input) {
        String[] reports = input.split("\n");

        int safe = 0;

        for (String report : reports) {
            if(isReportSafePart2(report.split(" "))) {
                safe += 1;
            }
        }

        return Integer.toString(safe);
    }
}
