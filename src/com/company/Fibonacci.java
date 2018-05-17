package com.company;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
    public static Map<Integer, Long> memory = new HashMap(){{
            put(0, (long)0);
            put(1, (long)1);
    }};
    public static long fibDynamicRecursion(int n) {
        if (Fibonacci.memory.containsKey(n))
            return Fibonacci.memory.get(n);
        Fibonacci.memory.put(n, Fibonacci.fibDynamicRecursion(n-1) + fibDynamicRecursion(n-2));
        return Fibonacci.memory.get(n);
    }
    public static long fibRecursion(int n) {
        if (n <= 1) return n;
        return fibRecursion(n-1) + fibRecursion(n-2);
    }
    public static long fibIteration(int n) {
        long x = 0, y = 1, z = 1;
        for (int i = 0; i < n; i++) {
            x = y;
            y = z;
            z = x + y;
        }
        return x;
    }
}
