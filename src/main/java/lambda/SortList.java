package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class MyComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }
}

public class SortList {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(
                Arrays.asList("Fred", "Jim", "Sheila")
        );

        System.out.println(names);
        names.sort(new MyComparator());
        System.out.println(names);
        names.sort((o1, o2) -> o2.length() - o1.length());
        System.out.println(names);
    }
}
