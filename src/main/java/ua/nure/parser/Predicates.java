package ua.nure.parser;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Utility class for working with predicates.
 */
public class Predicates {

    /** Prevent instantiation of this utility class. */
    private Predicates() {

    }

    /**
     * A utility function to find distinct by class field.
     *
     * @param keyExtractor
     *         the identifier from object from container
     * @param <T>
     *         a type of input elements to the reduction operation
     * @return the {@code Predicate} to filer list
     * @implNote Provides static utility methods for creating and working {@code Predicate}
     *         that helps to get unique elements from list(their first occurrence).
     */
    public static <T> Predicate<T> distinctByFirstOccurrence(
            Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * A utility function to find distinct by class field.
     *
     * @param keyExtractor
     *         the identifier from object from container
     * @param <T>
     *         a type of input elements to the reduction operation
     * @return the {@code Predicate} to filer list
     * @implNote Provides static utility methods for creating and working {@code Predicate}
     *         that helps to get unique elements from list(their last occurrence).
     */
    public static <T> Predicate<T> distinctByLastOccurrence(
            Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.put(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
