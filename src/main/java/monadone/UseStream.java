package monadone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UseStream {
    public static void main(String[] args) {
        List<String> sis = Arrays.asList(
                "Fred", "Jim", "Sheila"
        );

        sis./*stream().*/forEach((s)->System.out.println(s));
        System.out.println("----------------");

        sis.stream()
                .filter(s -> s.length() > 3)
                .map(s -> s.toUpperCase())
                .forEach((s)->System.out.println(s));
        System.out.println("----------------");

        sis.stream()
                .filter(s -> s.length() > 3)
                .map(s -> s.length())
                .forEach((s)->System.out.println(s));
        System.out.println("----------------");

        Function<String, Stream<String>> transToChars = s -> {
            char [] chars = s.toCharArray();
            List<String> out = new ArrayList<>();
            for (char c : chars) {
                out.add("" + c);
            }
            return out.stream();
        };
        sis.stream()
                .filter(s -> s.length() > 3)
                .flatMap(transToChars)
                .forEach(s -> System.out.println(s));
        System.out.println("----------------");

        sis.forEach((s)->System.out.println(s));
        System.out.println("----------------");

        boolean allLong = sis.stream()
//                .filter(s -> s.length() > 3)
                .peek(s -> System.out.println(s))
                .allMatch(s -> s.length() > 3); // && "short-circuit"
        System.out.println(allLong);
        System.out.println("----------------");

        IntStream.generate(() -> ThreadLocalRandom.current().nextInt(1, 7))
                .limit(100)
                .forEach(v -> System.out.println(v));
        System.out.println("----------------");

        IntStream.iterate(1, x -> x + 1)
                .skip(50)
                .limit(100)
                .forEach(x -> System.out.println(x));
        System.out.println("----------------");

        OptionalInt res = IntStream.iterate(1, x -> x + 1)
                .limit(10)
                .reduce((a, b) -> a + b);

        res.ifPresent(v -> System.out.println("The sum is " + v));
        System.out.println("----------------");

    }
}
