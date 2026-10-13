package com.abes.library.util;

import java.util.*;

public final class CollectionUtils {
    private CollectionUtils() {}

    public static <T extends Comparable<? super T>> List<T> sortedCopy(Collection<T> values) {
        List<T> copy = new ArrayList<>(values);
        Collections.sort(copy);
        return copy;
    }

    public static <T> int count(Collection<T> values) { return values.size(); }

    public static <T> void removeMatching(Collection<T> values, java.util.function.Predicate<T> predicate) {
        Iterator<T> iterator = values.iterator();
        while (iterator.hasNext()) if (predicate.test(iterator.next())) iterator.remove();
    }
}
