package me.goodbee.adventofcode.days;

import me.goodbee.adventofcode.util.Day;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 extends Day {
    @Override
    public String getInputFile() {
        return "day03.txt";
    }

    @Override
    public String part1(String input) {
        AtomicInteger res = new AtomicInteger();
        String[] lines = input.split("\n");
        Pattern regex = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");

        for (String line : lines) {
            Matcher matcher = regex.matcher(line);

            matcher.results().forEach(matchResult -> {
                int first = Integer.parseInt(matchResult.group(1));
                int second = Integer.parseInt(matchResult.group(2));

                res.addAndGet(first * second);
            });
        }

        return res.toString();
    }

    @Override
    public String part2(String input) {
        int res = 0;
        String[] lines = input.split("\n");
        Pattern regex = Pattern.compile("((?<isMul>mul)\\((?<f>\\d{1,3}),(?<s>\\d{1,3})\\))|((?<isDo>do)\\(\\))|(?<isDont>don't)\\(\\)");

        boolean shouldDo = true;

        for (String line : lines) {
            Matcher matcher = regex.matcher(line);

            while (matcher.find()) {
                if(matcher.group("isMul") != null && shouldDo) {
                    res += Integer.parseInt(matcher.group("f")) * Integer.parseInt(matcher.group("s"));
                } else if(matcher.group("isDo") != null) {
                    shouldDo = true;
                } else if(matcher.group("isDont") != null) {
                    shouldDo = false;
                }
            }
        }

        return Integer.toString(res);
    }
}
