package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ItemListImplTest {


    private final ItemListImpl oun = new ItemListImpl(5);

    @BeforeEach
    public void setUp() {
        oun.add(1);
        oun.add(2);
        oun.add(3);
        oun.add(4);
        oun.add(5);
    }

    @Test
    void add_test() {
        int size = oun.size();
        assertEquals(6, oun.add(6));
        assertEquals(size + 1, oun.size());
    }

    @Test
    void testAdd() {
        int size = oun.size();
        int index = 1;
        assertEquals(6, oun.add(index, 6));
        assertEquals(index, oun.indexOf(6));
        assertEquals(size + 1, oun.size());
    }

    @Test
    void set() {
        int size = oun.size();
        int index = 1;
        assertEquals(6, oun.set(index,6));
        assertEquals(index, oun.indexOf(6));
        assertEquals(size,oun.size());
    }

    @Test
    void remove() {
        int size = oun.size();
        assertEquals(1, oun.remove(0));
        assertEquals(size - 1, oun.size());
    }

    @Test
    void testRemove() {
        int size = oun.size();
        assertEquals(1, oun.remove(0));
        assertEquals(size - 1, oun.size());
    }

    public static Stream<Arguments> argumentsForContains() {
        return Stream.of(Arguments.of(1,0),
                Arguments.of(2,1),
                Arguments.of(3,2));
    }

    @ParameterizedTest
    @MethodSource("argumentsForContains")
    void indexOf(Integer item, int index) {
        assertEquals(index,oun.indexOf(item));
    }

    @ParameterizedTest
    @MethodSource("argumentsForContains")
    void get(Integer item, int index) {
        assertEquals(item,oun.get(index));
    }

    @Test
    void testEquals() {
        ItemListImpl test = new ItemListImpl(5);
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        test.add(5);
        assertTrue(oun.equals(test));
    }

    @Test
    void size() {
    }

    @Test
    void isEmpty() {
        ItemListImpl test = new ItemListImpl(3);
        assertTrue(test.isEmpty());
    }

    @Test
    void toArray() {
        Integer[] test = {1, 2, 3, 4, 5};
        assertArrayEquals(test, oun.toArray());
    }

}
