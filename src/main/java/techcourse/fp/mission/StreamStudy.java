package techcourse.fp.mission;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

public class StreamStudy {

    public static long countWords() throws IOException {
        String contents = Files.readString(Paths
                .get("src/main/resources/fp/war-and-peace.txt"));

        return Arrays.stream(contents.split("[\\P{L}]+"))
                .filter(word -> word.length() > 12)
                .count();
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(number -> number * 2)
                .collect(Collectors.toUnmodifiableList());
    }

    public static long sumAll(List<Integer> numbers) {
        return sum(numbers, (number) -> true);
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) {
        return sum(numbers, (number) -> number > 3) * 2L;
    }

    private static long sum(List<Integer> numbers, IntPredicate predicate) {
        return numbers.stream()
                .filter(predicate::test)
                .mapToInt(number -> number)
                .sum();
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths
                .get("src/main/resources/fp/war-and-peace.txt"));

        Arrays.stream(contents.split("[\\P{L}]+"))
                .filter(it -> it.length() > 12)
                .map(String::toLowerCase)
                .distinct()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .limit(100)
                .forEach(System.out::println);
    }
}
