package monadone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class UseSuperIterable {
    public static void main(String[] args) {
        SuperIterable<String> sis = new SuperIterable<>(Arrays.asList(
                "Fred", "Jim", "Sheila"
        ));

        sis.forEach((s)->System.out.println(s));
        System.out.println("----------------");

        sis
                .filter(s -> s.length() > 3)
                .map(s -> s.toUpperCase())
                .forEach((s)->System.out.println(s));
        System.out.println("----------------");

        sis
                .filter(s -> s.length() > 3)
                .map(s -> s.length())
                .forEach((s)->System.out.println(s));
        System.out.println("----------------");

        Function<String, SuperIterable<String>> transToChars = s -> {
            char [] chars = s.toCharArray();
            List<String> out = new ArrayList<>();
            for (char c : chars) {
                out.add("" + c);
            }
            return new SuperIterable(out);
        };
        sis
                .filter(s -> s.length() > 3)
                .flatMap(transToChars)
                .forEach(s -> System.out.println(s));
        System.out.println("----------------");

        sis.forEach((s)->System.out.println(s));

    }
}
