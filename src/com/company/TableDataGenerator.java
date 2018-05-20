package com.company;

import java.util.Random;

public class TableDataGenerator {

    //*****generowanie tablicy rosnacej
    public static int[] genRisingTable(int howMany) {
        int ran = 0;
        int[] risingTable = new int[howMany];
        for (int i = 0; i < howMany; i++) {
            Random generator = new Random();
            ran += generator.nextInt(10) + 1;
            risingTable[i] = ran;
        }
        return risingTable;
    }

        //*****generowanie tablicy malejacej przez odwrocenie rosnacej
    
        //*****generowanie tabicy full random
    public static int[] genRandomTable(int howMany) {
        int[] randomTable = new int[howMany];

        for (int i = 0; i < howMany; i++) {
            Random generator = new Random();
            int ran = generator.nextInt(1000) + 1;
            randomTable[i] = ran;
        }
        return randomTable;
    }
}
