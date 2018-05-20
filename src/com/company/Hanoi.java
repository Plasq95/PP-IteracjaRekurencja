package com.company;

/**
 * Created by lukas on 05.05.2018.
 */
public class Hanoi {
    static void HanoiReccursive(int n, String from, String mediator, String to) {
        if (n > 0) {
            HanoiReccursive(n - 1, from, to, mediator);
            HanoiReccursive(n - 1, mediator, from, to);
//            System.out.printf("Przesuwamy krążek z wieży %s do wieży %s \n ", from, to);
        }
    }
    static void HanoiIterative(int n, String ... towers ) {
        int limit = (int)Math.pow(2,n)-1; // NUMBER OF ITERATIONS = 2^n - 1
        for(int i=0;i<limit;i++){
            int disk = disk(i); // DISK TO BE MOVED
            int source = ( movements(i,disk)*direction(disk,n))%3; // SOURCE PEG
            int destination = (source + direction(disk,n))%3; // DESTINATION PEG
            move(disk,towers[source],towers[destination]);
        }
    }
    private static int disk(int i) { // RETURNS THE DISK TO BE MOVED IN i
        int C, x= i+1; // SINCE FOR STARTS WITH 0, ADDING 1
        for(C=0;x%2==0;C++){ // CONTINUOUS DIVISION BY 2 UNTIL ODD OCCURS
            x/=2;
        }
        return C; // RETURNS THE COUNTER C
    }
    private static int movements(int i, int d) { // HOW MANY TIMES DISK d HAS MOVED BEFORE STAGE i
        while(d--!=0)
            i/=2;
        return (i+1)/2;
    }
    private static int direction(int d,int n) { // EACH DISK MOVES IN SAME DIRECTION CW=1, ACW=2
        return 2 - (n + d)%2;
    }
    private static void move(int disk, String from, String to) {
//        System.out.printf("Przesuwamy krążek z wieży %s do wieży %s \n ", from, to);
    }

}

