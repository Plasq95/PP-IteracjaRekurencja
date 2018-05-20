package com.company;

import java.util.Stack;

public class QuickSort {

    public static void quicksortRecursion1(int[] A, int lo, int hi) {
        try {
            if (lo < hi) {

                int dp = partition1(A, lo, hi);
                quicksortRecursion1(A, lo, dp - 1);
                quicksortRecursion1(A, dp + 1, hi);
            }
        } catch (StackOverflowError e) {
            System.out.println("stack");
        }
    }

    public static int partition1(int[] A, int lo, int hi) {
        int pivot = A[lo]; //element podziałowy
        int lastSmall = lo;
        for (int j = lo; j <= hi; j++) {
            if (A[j] < pivot) {
                ++lastSmall;
                swap(A, lastSmall, j);
            }
        }
        swap(A, lo, lastSmall);

        return lastSmall;
    }

    public static void quicksortRecursion2(int[] A, int lo, int hi) {
        try {
            if (lo < hi) {

                int dp = partition2(A, lo, hi);
                quicksortRecursion2(A, lo, dp);
                quicksortRecursion2(A, dp + 1, hi);
            }
        } catch (StackOverflowError e) {
            System.out.println("stack");
        }
    }

    public static int partition2(int[] A, int lo, int hi) {
        int pivot = A[lo];
        --lo;
        ++hi;
        while (lo < hi) {
            do {
                --hi;
            } while (A[hi] > pivot);
            do {
                ++lo;
            } while (A[lo] < pivot);
            if (lo < hi) {
                swap(A, lo, hi);
            }
        }
        return hi;
    }

    public static void swap(int[] table, int i, int j) {
        int temp = table[i];
        table[i] = table[j];
        table[j] = temp;
    }

    public static int quicksortIteration(int[] A, int lo, int hi) {
        Stack st = new Stack();
        st.push(new Pair(lo, hi));
        int stackItems = 1, maxStackItems = 1;

        while (!st.empty()) {
            --stackItems;
            Pair p = (Pair) st.pop();
            if (p.left < p.right) {
                int dp = partition2(A, p.left, p.right);
                if (dp - p.left + 1 < p.right - dp) {
                    st.push(new Pair(dp + 1, p.right));
                    st.push(new Pair(p.left, dp));
                } else {
                    st.push(new Pair(p.left, dp));
                    st.push(new Pair(dp + 1, p.right));
                }
                stackItems += 2;
            }
            if (stackItems > maxStackItems) {
                maxStackItems = stackItems;
            }
        }
        //System.out.println("Max na stosie: " + maxStackItems);
        return maxStackItems;
    }

}
