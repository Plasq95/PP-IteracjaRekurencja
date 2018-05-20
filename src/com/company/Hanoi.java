package com.company;

/**
 * Created by lukas on 05.05.2018.
 */
public class Hanoi {
    static void HanoiReccursive(int n, int from, int mediator, int to) {
        if (n > 0) {
            HanoiReccursive(n - 1, from, to, mediator);
            HanoiReccursive(n - 1, mediator, from, to);
            System.out.printf("Przesuwamy krązek z wieży %s do wieży %s \n ", from, to);
        }
    }

}

