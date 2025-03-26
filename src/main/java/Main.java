import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task 1");
        List<String> names = List.of("Slava", "Ivan", "Maria", "Pater");
        String resultOdd = isBetweenOdd(names);
        System.out.println(resultOdd + "\n");

        System.out.println("Task 2");
        String resultUppCaseAndWithEnd = String.valueOf(isUppCaseAndWithEnd(names));
        System.out.println(resultUppCaseAndWithEnd + "\n");

        System.out.println("Task 3");
        String[] sortNumber = {"1, 2, 0", "4, 5"};
        String resultSortNumb = takeAndSort(sortNumber);
        System.out.println(resultSortNumb + "\n");

        System.out.println("Task 4");
        long seed = 0L;
        long a = 25214903917L;
        long c = 11L;
        long m = 2^48L;
        Stream<Long> randomNumbers = endLessStream(a, c, m, seed);
        randomNumbers.limit(5).forEach(System.out::println);
        System.out.print("\n");

        System.out.println("Task 5");
        Stream<String> firstStream = Stream.of("Is it ", "you to be ", "about me? ", "it be ", "first ");
        Stream<String> secondStream = Stream.of("right for ", "concerned ", "When will ", "the ", "timeтак");
        Stream<String> zipStream = zip(firstStream, secondStream);
        zipStream.forEach(System.out::print);
        System.out.println("\n");
    }

    //____________________Task1_______________________
    public static String isBetweenOdd(List<String> names) {
        return IntStream.range(0, names.size())
                .filter(i -> i % 2 == 1)
                .mapToObj(i -> (i) + ". " + names.get(i))
                .collect(Collectors.joining(", "));
    }

    //____________________Task2_______________________
    public static List<String> isUppCaseAndWithEnd(List<String> upperCase) {
        return upperCase.stream()
                .sorted(Comparator.reverseOrder())
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    //____________________Task3_______________________
    public static String takeAndSort(String[] number) {
        return Arrays.stream(number)
                .flatMap(s -> Arrays.stream(s.split(",")))
                .map(String::trim)
                .map(Integer::parseInt)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    //____________________Task4_______________________
    public static Stream<Long> endLessStream(long a, long c, long m, long seed) {
        return Stream.iterate(seed, n -> (a * n + c) % m);
    }

    //____________________Task5_______________________
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> firstIterator = first.iterator();
        Iterator<T> secondIterator = second.iterator();

        return Stream.iterate(0, i -> firstIterator.hasNext() && secondIterator.hasNext(), i -> i + 1)
                .flatMap(i -> Stream.of(firstIterator.next(), secondIterator.next()));
    }
}