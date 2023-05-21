package com.foxowlet.demo.hashmap;


public class Main {
    public static void main(String[] args) {
        Map<BadKey, Integer> map = new HashMap<>();
        for (int i = 0; i < 1_000_000; i++) {
            map.put(new BadKey(i), i);
        }
        measureTime(() -> map.get(new BadKey(0)));
        measureTime(() -> map.get(new BadKey(99_999)));
        measureTime(() -> map.get(new BadKey(100_000)));
    }

    private static class BadKey {
        private int value;

        private BadKey(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BadKey badKey = (BadKey) o;
            return value == badKey.value;
        }

        @Override
        public int hashCode() {
            return value % 1000;
        }
    }

    private static void measureTime(Runnable action) {
        long runTime = 0;
        int iterations = 10;
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            action.run();
            long endTime = System.nanoTime();
            runTime += endTime - startTime;
        }
        System.out.printf("Elapsed time: %,d%n", runTime / iterations);
    }
}
