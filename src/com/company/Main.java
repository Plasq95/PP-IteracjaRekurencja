package com.company;

import java.util.Date;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        new HanoiTower(3, true).resolveIterational();
//
//        int[] tablicaPosortowana100 = new int[100];
//        int[] tablicaOdwrotniePosortowana100 = new int[100];
//        int[] tablicaRandom100 = new int[100];
//
//        int[] tablicaPosortowana1000 = new int[1000];
//        int[] tablicaOdwrotniePosortowana1000 = new int[1000];
//        int[] tablicaRandom1000 = new int[1000];
//
//        //*****generowanie tablicy rosnacej
//        {
//            int ran = 0;
//            for (int i = 0; i < 100; i++) {
//                Random generator = new Random();
//                ran += generator.nextInt(10) + 1;
//                tablicaPosortowana100[i] = ran;
//            }
//        }
//
//        {
//            int ran = 0;
//            for (int i = 0; i < 1000; i++) {
//                Random generator = new Random();
//                ran += generator.nextInt(10) + 1;
//                tablicaPosortowana1000[i] = ran;
//            }
//        }
//        //*****generowanie tablicy malejacej przez odwrocenie rosnacej
//        for (int i = 0; i < 100; i++) {
//            tablicaOdwrotniePosortowana100[i] = tablicaPosortowana100[99 - i];
//        }
//        for (int i = 0; i < 1000; i++) {
//            tablicaOdwrotniePosortowana1000[i] = tablicaPosortowana1000[999 - i];
//        }
//
//        //*****generowanie tabicy full random
//        for (int i = 0; i < 100; i++) {
//            Random generator = new Random();
//            int ran = generator.nextInt(100) + 1;
//            tablicaRandom100[i] = ran;
//        }
//
//        for (int i = 0; i < 1000; i++) {
//            Random generator = new Random();
//            int ran = generator.nextInt(1000) + 1;
//            tablicaRandom1000[i] = ran;
//        }
//        /*for (int i = 0; i <= tablicaPosortowana100.length - 1; i++) {
//         System.out.print(tablicaPosortowana100[i] + ", ");
//
//         }
//         System.out.println("");
//
//         for (int i = 0; i <= tablicaOdwrotniePosortowana100.length - 1; i++) {
//         System.out.print(tablicaOdwrotniePosortowana100[i] + ", ");
//
//         }
//         System.out.println("");
//
//         for (int i = 0; i <= tablicaRandom100.length - 1; i++) {
//         System.out.print(tablicaRandom100[i] + ", ");
//
//         }
//         System.out.println("");*/
//
//
//        long startTime1 = System.nanoTime();
//        QuickSort.quicksortRekurencyjny1(tablicaPosortowana100, 1, tablicaPosortowana100.length - 1);
//        long endTime1 = System.nanoTime();
//
//        long startTime2 = System.nanoTime();
//        QuickSort.quicksortRekurencyjny2(tablicaPosortowana100, 1, tablicaPosortowana100.length - 1);
//        long endTime2 = System.nanoTime();
//
//        long startTime3 = System.nanoTime();
//        QuickSort.quicksortIteracyjny(tablicaPosortowana100, 1, tablicaPosortowana100.length - 1);
//        long endTime3 = System.nanoTime();
//
//        long startTime12 = System.nanoTime();
//        QuickSort.quicksortRekurencyjny1(tablicaOdwrotniePosortowana100, 1, tablicaOdwrotniePosortowana100.length - 1);
//        long endTime12 = System.nanoTime();
//
//        long startTime22 = System.nanoTime();
//        QuickSort.quicksortRekurencyjny2(tablicaOdwrotniePosortowana100, 1, tablicaOdwrotniePosortowana100.length - 1);
//        long endTime22 = System.nanoTime();
//
//        long startTime32 = System.nanoTime();
//        QuickSort.quicksortIteracyjny(tablicaOdwrotniePosortowana100, 1, tablicaOdwrotniePosortowana100.length - 1);
//        long endTime32 = System.nanoTime();
//
//        long startTime13 = System.nanoTime();
//        QuickSort.quicksortRekurencyjny1(tablicaRandom100, 1, tablicaRandom100.length - 1);
//        long endTime13 = System.nanoTime();
//
//        long startTime23 = System.nanoTime();
//        QuickSort.quicksortRekurencyjny2(tablicaRandom100, 1, tablicaRandom100.length - 1);
//        long endTime23 = System.nanoTime();
//
//        long startTime33 = System.nanoTime();
//        QuickSort.quicksortIteracyjny(tablicaRandom100, 1, tablicaRandom100.length - 1);
//        long endTime33 = System.nanoTime();
//
//
//
//        long duration1 = (endTime1 - startTime1);
//        long duration2 = (endTime2 - startTime2);
//        long duration3 = (endTime3 - startTime3);
//
//        long duration12 = (endTime12 - startTime12);
//        long duration22 = (endTime22 - startTime22);
//        long duration32 = (endTime32 - startTime32);
//
//        long duration13 = (endTime13 - startTime13);
//        long duration23 = (endTime23 - startTime23);
//        long duration33 = (endTime33 - startTime33);
//
//        System.out.println("tablice100:");
//        System.out.println("czas1: " + duration1);
//        System.out.println("czas2: " + duration2);
//        System.out.println("czas3: " + duration3);
//        System.out.println("");
//
//        System.out.println("czas12: " + duration12);
//        System.out.println("czas22: " + duration22);
//        System.out.println("czas32: " + duration32);
//        System.out.println("");
//
//        System.out.println("czas12: " + duration13);
//        System.out.println("czas22: " + duration23);
//        System.out.println("czas32: " + duration33);
//
//
//        long startTime;
//
//        startTime = new Date().getTime();
//        Hanoi.HanoiReccursive (30,"A","B","C");
//        System.out.println("Podejście rekurencyjne wykonane zostało w: "+ (new Date().getTime() - startTime));
//
//        startTime = new Date().getTime();
//        Hanoi.HanoiIterative (30,"A","C","B");
//        System.out.println("Podejście iteracyjne wykonane zostało w: "+ (new Date().getTime() - startTime));
//
//        startTime = new Date().getTime();
//        System.out.println("Wynik: "+ Fibonacci.fibRecursion(50));
//        System.out.println("Podejście rekurencyjne wykonane zostało w: "+ (new Date().getTime() - startTime));
//
//        startTime = new Date().getTime();
//        System.out.println("Wynik: "+ Fibonacci.fibIteration(50));
//        System.out.println("Podejście iteracyjne wykonane zostało w: "+ (new Date().getTime() - startTime));
//
//        startTime = new Date().getTime();
//        System.out.println("Wynik: "+ Fibonacci.fibDynamicRecursion(50));
//        System.out.println("Podejście rekurencyjne z wykorzystaniem programowania dynamicznego wykonane zostało w: "+ (new Date().getTime() - startTime));
    }
}
