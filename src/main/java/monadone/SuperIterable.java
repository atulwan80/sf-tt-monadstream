package monadone;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
    private Iterable<E> self;

    public SuperIterable(Iterable<E> data) {
        self = data;
    }

    public SuperIterable<E> filter(Predicate<E> pred) {
        List<E> results = new ArrayList<>();
        self.forEach(e -> {
            if (pred.test(e)) results.add(e);
        });

        return new SuperIterable(results);
    }

    public <F> SuperIterable<F> map(Function<E, F> op) {
        List<F> results = new ArrayList<>();
        for (E e : self) {
            F f = op.apply(e);
            results.add(f);
        }
        return new SuperIterable(results);
    }

    public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> op) {
        List<F> results = new ArrayList<>();
//        self.forEach(e -> op.apply(e).forEach(f -> results.add(f)));

        for (E e : self) {
            SuperIterable<F> out1 = op.apply(e);
            for (F f : out1) {
                results.add(f);
            }
        }
        return new SuperIterable(results);
    }

//    public void forEvery(Consumer<E> op) {
//        for (E e : self) {
//            op.accept(e);
//        }
//    }

    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }
}
