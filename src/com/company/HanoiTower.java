package com.company;

import java.util.Stack;

/**
 * Created by lukas on 20.05.2018.
 */
public class HanoiTower {
    Stack<Integer>[] towers;
    Integer size;
    boolean isLogInfo;

    HanoiTower(int n, boolean isLogInfo) {
        this.size = new Integer(n);
        towers = new Stack[3];
        towers[0] = fillFirstTower();
        towers[1] = new Stack<>();
        towers[2] = new Stack<>();
        this.isLogInfo = isLogInfo;
    }
    Stack<Integer> fillFirstTower() {
        Stack <Integer> tower = new Stack<>();
        int i = new Integer(size);
        while (i>0) {
            tower.push(new Integer(i));
            i--;
        }
        return tower;
    }
    public void resolveIterational() {
        int n = 0;
        while (!isTowerCorrect(towers[1])) {
            int smallestElem = move(n,(n+1)%3);
            if (isTowerCorrect(towers[(n+1)%3]))
                break;
            if (towers[n].size()>0 && towers[n].peek()!=smallestElem)
                move(n ,(n+2)%3);
            else
                move((n+2)%3, n);
            n = (n+1)%3;
        }
    }
    private boolean isTowerCorrect(Stack<Integer> tower){
        if (tower.size()!=this.size)
            return false;
        int prevValue = this.size+1;
        for(Integer disk : tower) {
            if (disk>prevValue)
                return false;
            prevValue = disk;
        }
        return true;
    }
    private int move(int indexFrom, int indexTo) {
        if (isLogInfo)
            System.out.printf("Przesuwamy krązek z wieży %s do wieży %s \n ", indexFrom+1, indexTo+1);
        int elem = towers[indexFrom].pop();
        towers[indexTo].push(elem);
        return elem;
    }
}
