package com.foxowlet.demo.hashmap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HashMapTest {
    @Test
    void get_shouldReturnValue_whenSavedWithPut() {
        Map<Integer, String> map = new HashMap<>();
        map.put(42, "hello");

        assertEquals("hello", map.get(42));
    }

    @Test
    void get_shouldReturnMappedValues_whenDifferentKeysUsed() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");

        assertEquals("one", map.get(1));
        assertEquals("two", map.get(2));
    }

    @Test
    void map_shouldSupportNegativeKeys() {
        Map<Integer, String> map = new HashMap<>();
        map.put(-42, "negative");

        assertEquals("negative", map.get(-42));
    }

    @Test
    void map_shouldLargeNumericKeys() {
        Map<Integer, String> map = new HashMap<>();
        map.put(Integer.MAX_VALUE, "large");

        assertEquals("large", map.get(Integer.MAX_VALUE));
    }

    @Test
    void get_shouldReturnOriginalValue_whenDifferentKeysUsed() {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 1_000; ++i) {
            map.put(i, String.valueOf(i));
        }

        for (int i = 0; i < 1_000; i++) {
            assertEquals(String.valueOf(i), map.get(i));
        }
    }

    @Test
    void get_shouldReturnNull_whenNoValuePresentForKey() {
        Map<Integer, String> map = new HashMap<>();

        assertNull(map.get(42));
    }

    @Test
    void map_shouldSupportStringKeys() {
        Map<String, Integer> map = new HashMap();
        map.put("Hello", 42);

        assertEquals(42, map.get("Hello"));
    }
}
