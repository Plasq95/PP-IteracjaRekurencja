package com.company;

import java.util.*;

public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
    }
int totalFibRuns = 0;
int totalFibRecWins = 0;
int totalFibRecDynWins = 0;
int totalFibIterWins = 0;

int totalHanoiRuns = 0;
int totalHanoiRecWins = 0;
int totalHanoiIterWins = 0;

int totalQSortRuns = 0;

    public void HanoiRun() {
        int numberOfDiscs = Integer.valueOf(jSpinnerHanoi.getValue().toString());

        //Hanoi rekurencyjnie
        long startTimeHanoiRec = System.nanoTime();
        Hanoi.HanoiReccursive(numberOfDiscs, "pierwszy", "drugi", "trzeci");
        long endTimeHanoiRec = System.nanoTime();
        long durationHanoiRec = (endTimeHanoiRec - startTimeHanoiRec);

        String hanoiRecTime = Long.toString(durationHanoiRec);
        jLabelHanoiRecScore.setText(hanoiRecTime);

        //Hanoi iteracyjnie
        long startTimeHanoiIter = System.nanoTime();
        new HanoiTower(numberOfDiscs, true).resolveIterational();
        long endTimeHanoiIter = System.nanoTime();
        long durationHanoiIter = (endTimeHanoiIter - startTimeHanoiIter);

        String hanoiIterTime = Long.toString(durationHanoiIter);
        jLabelHanoiIterScore.setText(hanoiIterTime);

        if (durationHanoiRec < durationHanoiIter) {
            jTextAreaHanoi.append(String.format("Wygrało rekurencyjne z czasem: %d, iteracyjne: %d (+ %d)\n",
                    durationHanoiRec, durationHanoiIter, durationHanoiIter - durationHanoiRec));
            totalHanoiRecWins++;
            jLabelHanoiRecWins.setText(Integer.toString(totalHanoiRecWins));
        } else {
            if (durationHanoiRec == durationHanoiIter) {
                jTextAreaHanoi.append(String.format("Remis z czasem: %d\n", durationHanoiRec));

            } else {
                jTextAreaHanoi.append(String.format("Wygrało itearcyjne z czasem: %d, rekurencyjne: %d(+%d)\n",
                        durationHanoiIter, durationHanoiRec, durationHanoiRec - durationHanoiIter));
                totalHanoiIterWins++;
                jLabelHanoiIterWins.setText(Integer.toString(totalHanoiIterWins));
            }
        }
        
    }

    public void FibRun() {
        int number = Integer.valueOf(jSpinnerFib.getValue().toString());

        //fibonacci dynamiczny rekurencyjny
        long startTimeFibDynRec = System.nanoTime();
        long fibDynRec = Fibonacci.fibDynamicRecursion(number);
        long endTimeFibDynRec = System.nanoTime();
        long durationFibDynRec = (endTimeFibDynRec - startTimeFibDynRec);

        String fibDynRecTime = Long.toString(durationFibDynRec);
        jLabelFibRecDynScore.setText(fibDynRecTime);

        String fibDynRecScore = Long.toString(fibDynRec);
        jLabelFibRecDynReturn.setText(fibDynRecScore);

        //fibonacci rekurencyjny
        long startTimeFibRec = System.nanoTime();
        long fibRec = Fibonacci.fibRecursion(number);
        long endTimeFibRec = System.nanoTime();
        long durationFibRec = (endTimeFibRec - startTimeFibRec);

        String fibRecTime = Long.toString(durationFibRec);
        jLabelFibRecScore.setText(fibRecTime);

        String fibRecScore = Long.toString(fibRec);
        jLabelFibRecReturn.setText(fibRecScore);

        //fibonacci iteracyjny
        long startTimeFibIter = System.nanoTime();
        long fibIter = Fibonacci.fibIteration(number);
        long endTimeFibIter = System.nanoTime();
        long durationFibIter = (endTimeFibIter - startTimeFibIter);

        String fibIterTime = Long.toString(durationFibIter);
        jLabelFibIterScore.setText(fibIterTime);

        String fibIterScore = Long.toString(fibIter);
        jLabelFibIterReturn.setText(fibIterScore);

        List<ExtPair<Long, String>> fibScore = new ArrayList<>();

        fibScore.add(new ExtPair<>(durationFibDynRec, "dynamiczny rekurencyjny"));
        fibScore.add(new ExtPair<>(durationFibRec, "rekurencyjny"));
        fibScore.add(new ExtPair<>(durationFibIter, "iteracyjny"));
        fibScore.sort(Comparator.comparing(ExtPair::getFirst));
        StringBuilder sb = new StringBuilder();

        fibScore.stream().forEach((entry) -> {
            sb.append("Czas: ").append(entry.getFirst()).append(", dla: ").append(entry.getSecond()).append("\n");
        });
        jTextAreaFib.setText(sb.toString());

    }

    public void QSortRun() {

        int numberOfElements = Integer.valueOf(jSpinnerQSort.getValue().toString());

        int[] tableAscending = TableDataGenerator.genRisingTable(numberOfElements);
        int[] tableDescending = tableAscending.clone();

        //odwrócenie tablicy
        for (int i = 0; i < tableDescending.length / 2; i++) {
            int temp = tableDescending[i];
            tableDescending[i] = tableDescending[tableDescending.length - i - 1];
            tableDescending[tableDescending.length - i - 1] = temp;
        }

        int[] tableRandom = TableDataGenerator.genRandomTable(numberOfElements);

        int[] tableAscendingRec1 = tableAscending.clone();
        int[] tableAscendingRec2 = tableAscending.clone();
        int[] tableAscendingIter = tableAscending.clone();

        int[] tableDescendingRec1 = tableDescending.clone();
        int[] tableDescendingRec2 = tableDescending.clone();
        int[] tableDescendingIter = tableDescending.clone();

        int[] tableRandRec1 = tableRandom.clone();
        int[] tableRandRec2 = tableRandom.clone();
        int[] tableRandIter = tableRandom.clone();

        //<editor-fold defaultstate="collapsed" desc="souty dla tablic">
        /*
         System.out.println("\nascend: ");
         for (int i = 0; i < tableAscending.length; i++) {
         System.out.print(tableAscending[i] + ", ");
         }
        
         System.out.println("\ndescend: ");
         for (int i = 0; i < tableDescending.length; i++) {
         System.out.print(tableDescending[i]+ ", ");
         }
         System.out.println("\nrand: ");
         for (int i = 0; i < tableRandom.length; i++) {
         System.out.print(tableRandom[i]+ ", ");
         }
         */
//</editor-fold>
//rosnący zbiór
        StringBuilder sb = new StringBuilder("Wyniki dla rosnącego zbioru: \n");
        {
//REC1
            List<ExtPair<Long, String>> score = new ArrayList<>();
            long startTimeQSortRec1Asc = System.nanoTime();
            QuickSort.quicksortRecursion1(tableAscendingRec1, 0, tableAscendingRec1.length - 1);
            long endTimeQSortRec1Asc = System.nanoTime();
            long durationQSortRec1Asc = (endTimeQSortRec1Asc - startTimeQSortRec1Asc);

            String qSortRec1TimeAsc = Long.toString(durationQSortRec1Asc);
            score.add(new ExtPair<>(durationQSortRec1Asc, "QuickSort Rekurencyjny 1"));
            jLabelRec1RisingScore.setText(qSortRec1TimeAsc);

//REC2
            long startTimeQSortRec2Asc = System.nanoTime();
            QuickSort.quicksortRecursion2(tableAscendingRec2, 0, tableAscendingRec2.length - 1);
            long endTimeQSortRec2Asc = System.nanoTime();
            long durationQSortRec2Asc = (endTimeQSortRec2Asc - startTimeQSortRec2Asc);

            score.add(new ExtPair<>(durationQSortRec2Asc, "QuickSort Rekurencyjny 2"));
            String qSortRec2TimeAsc = Long.toString(durationQSortRec2Asc);
            jLabelRec2RisingScore.setText(qSortRec2TimeAsc);
//ITER      
            long startTimeQSortIterAsc = System.nanoTime();
            QuickSort.quicksortIteration(tableAscendingIter, 0, tableAscendingIter.length - 1);
            long endTimeQSortIterAsc = System.nanoTime();
            long durationQSortIterAsc = (endTimeQSortIterAsc - startTimeQSortIterAsc);
            score.add(new ExtPair<>(durationQSortIterAsc, "Quicksort iteracyjny"));

            String qSortIterTimeAsc = Long.toString(durationQSortIterAsc);
            jLabelIterRisingScore.setText(qSortIterTimeAsc);
            score.sort(Comparator.comparing(ExtPair::getFirst));
            score.stream().forEach((entry) -> {
                sb.append("Czas: ").append(entry.getFirst()).append(", dla: ").append(entry.getSecond()).append("\n");
            });
        }

//malejący
        sb.append("Wyniki dla zbioru malejącego: \n");
        {
//REC1
            List<ExtPair<Long, String>> score = new ArrayList<>();
            long startTimeQSortRec1Desc = System.nanoTime();
            QuickSort.quicksortRecursion1(tableDescendingRec1, 0, tableDescendingRec1.length - 1);
            long endTimeQSortRec1Desc = System.nanoTime();
            long durationQSortRec1Desc = (endTimeQSortRec1Desc - startTimeQSortRec1Desc);
            score.add(new ExtPair<>(durationQSortRec1Desc, "QuickSort Rekurencyjny 1"));

            String qSortRec1TimeDesc = Long.toString(durationQSortRec1Desc);
            jLabelRec1DescScore.setText(qSortRec1TimeDesc);

//REC2
            long startTimeQSortRec2Desc = System.nanoTime();
            QuickSort.quicksortRecursion2(tableDescendingRec2, 0, tableDescendingRec2.length - 1);
            long endTimeQSortRec2Desc = System.nanoTime();
            long durationQSortRec2Desc = (endTimeQSortRec2Desc - startTimeQSortRec2Desc);

            score.add(new ExtPair<>(durationQSortRec2Desc, "QuickSort Rekurencyjny 2"));
            String qSortRec2TimeDesc = Long.toString(durationQSortRec2Desc);
            jLabelRec2DescScore.setText(qSortRec2TimeDesc);
//ITER      
            long startTimeQSortIterDesc = System.nanoTime();
            QuickSort.quicksortIteration(tableDescendingIter, 0, tableDescendingIter.length - 1);
            long endTimeQSortIterDesc = System.nanoTime();
            long durationQSortIterDesc = (endTimeQSortIterDesc - startTimeQSortIterDesc);
            score.add(new ExtPair<>(durationQSortIterDesc, "Quicksort iteracyjny"));

            score.sort(Comparator.comparing(ExtPair::getFirst));
            score.stream().forEach((entry) -> {
                sb.append("Czas: ").append(entry.getFirst()).append(", dla: ").append(entry.getSecond()).append("\n");
            });
            String qSortIterTimeDesc = Long.toString(durationQSortIterDesc);
            jLabelIterDescScore.setText(qSortIterTimeDesc);
        }

//losowo generowany
        sb.append("Wyniki dla zbioru generowanego losowo: \n");
        {
//REC1
            List<ExtPair<Long, String>> score = new ArrayList<>();
            long startTimeQSortRec1Rand = System.nanoTime();
            QuickSort.quicksortRecursion1(tableRandRec1, 0, tableRandRec1.length - 1);
            long endTimeQSortRec1Rand = System.nanoTime();
            long durationQSortRec1Rand = (endTimeQSortRec1Rand - startTimeQSortRec1Rand);
            score.add(new ExtPair<>(durationQSortRec1Rand, "QuickSort Rekurencyjny 1"));

            String qSortRec1TimeRand = Long.toString(durationQSortRec1Rand);
            jLabelRec1RandScore.setText(qSortRec1TimeRand);

//REC2
            long startTimeQSortRec2Rand = System.nanoTime();
            QuickSort.quicksortRecursion2(tableRandRec2, 0, tableRandRec2.length - 1);
            long endTimeQSortRec2Rand = System.nanoTime();
            long durationQSortRec2Rand = (endTimeQSortRec2Rand - startTimeQSortRec2Rand);
            score.add(new ExtPair<>(durationQSortRec2Rand, "QuickSort Rekurencyjny 2"));

            String qSortRec2TimeRand = Long.toString(durationQSortRec2Rand);
            jLabelRec2RandScore.setText(qSortRec2TimeRand);
//ITER      
            long startTimeQSortIterRand = System.nanoTime();
            QuickSort.quicksortIteration(tableRandIter, 0, tableRandIter.length - 1);
            long endTimeQSortIterRand = System.nanoTime();
            long durationQSortIterRand = (endTimeQSortIterRand - startTimeQSortIterRand);
            score.add(new ExtPair<>(durationQSortIterRand, "Quicksort iteracyjny"));

            String qSortIterTimeRand = Long.toString(durationQSortIterRand);
            jLabelIterRandScore.setText(qSortIterTimeRand);

            score.sort(Comparator.comparing(ExtPair::getFirst));
            score.stream().forEach((entry) -> {
                sb.append("Czas: ").append(entry.getFirst()).append(", dla: ").append(entry.getSecond()).append("\n");
            });
        }

        jTextAreaQSort.append(sb.toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabelFibRecInfo = new javax.swing.JLabel();
        jLabelFibRecScore = new javax.swing.JLabel();
        jLabelFibRecReturnInfo = new javax.swing.JLabel();
        jLabelFibRecReturn = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelFibRecWins = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabelFibRecDynInfo = new javax.swing.JLabel();
        jLabelFibRecDynScore = new javax.swing.JLabel();
        jLabelFibRecDynReturnInfo = new javax.swing.JLabel();
        jLabelFibRecDynReturn = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelFibRecDynWins = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabelFibIterInfo = new javax.swing.JLabel();
        jLabelFibIterScore = new javax.swing.JLabel();
        jLabelFibIterReturnInfo = new javax.swing.JLabel();
        jLabelFibIterReturn = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelFibIterWins = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jSpinnerFib = new javax.swing.JSpinner();
        jButtonStartFib = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSpinnerFibRuns = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabelFibTotalRuns = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaFib = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jSpinnerHanoi = new javax.swing.JSpinner();
        jButtonStartHanoi = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jSpinnerHanoiRuns = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        jLabelHanoiTotalRuns = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabelHanoiRecInfo = new javax.swing.JLabel();
        jLabelHanoiRecScore = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabelHanoiRecWins = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabelHanoiIterInfo = new javax.swing.JLabel();
        jLabelHanoiIterScore = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabelHanoiIterWins = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaHanoi = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jSpinnerQSort = new javax.swing.JSpinner();
        jButtonStartQSort = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jSpinnerQSortRuns = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        jLabelQSortTotalRuns = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabelRec1RisingInfo = new javax.swing.JLabel();
        jLabelRec1RisingScore = new javax.swing.JLabel();
        jLabelRec2RisingInfo = new javax.swing.JLabel();
        jLabelRec2RisingScore = new javax.swing.JLabel();
        jLabelIterRisingInfo = new javax.swing.JLabel();
        jLabelIterRisingScore = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabelRec1DescInfo = new javax.swing.JLabel();
        jLabelRec1DescScore = new javax.swing.JLabel();
        jLabelRec2DescInfo = new javax.swing.JLabel();
        jLabelRec2DescScore = new javax.swing.JLabel();
        jLabelIterDescInfo = new javax.swing.JLabel();
        jLabelIterDescScore = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabelRec1RandInfo = new javax.swing.JLabel();
        jLabelRec1RandScore = new javax.swing.JLabel();
        jLabelRec2RandInfo = new javax.swing.JLabel();
        jLabelRec2RandScore = new javax.swing.JLabel();
        jLabelIterRandInfo = new javax.swing.JLabel();
        jLabelIterRandScore = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaQSort = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Porównanie rekurencji i iteracji");

        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel13.setLayout(new java.awt.GridLayout());

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Rekurencyjny", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18))); // NOI18N

        jLabelFibRecInfo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelFibRecInfo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelFibRecInfo.setText("Wynik(ns):");

        jLabelFibRecScore.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelFibRecScore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelFibRecScore.setText("0");

        jLabelFibRecReturnInfo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelFibRecReturnInfo.setText("Liczba:");

        jLabelFibRecReturn.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelFibRecReturn.setText("0");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setText("Ile zwycięstw:");

        jLabelFibRecWins.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelFibRecWins.setText("-");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelFibRecWins, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelFibRecScore, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelFibRecInfo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelFibRecReturnInfo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelFibRecReturn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelFibRecInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelFibRecScore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelFibRecReturnInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelFibRecReturn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabelFibRecWins)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel13.add(jPanel17);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Rekurencyjny Dynamiczny", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18))); // NOI18N

        jLabelFibRecDynInfo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelFibRecDynInfo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelFibRecDynInfo.setText("Wynik(ns):");

        jLabelFibRecDynScore.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelFibRecDynScore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelFibRecDynScore.setText("0");

        jLabelFibRecDynReturnInfo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelFibRecDynReturnInfo.setText("Liczba:");

        jLabelFibRecDynReturn.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelFibRecDynReturn.setText("0");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel4.setText("Ile zwycięstw:");

        jLabelFibRecDynWins.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelFibRecDynWins.setText("-");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFibRecDynScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelFibRecDynInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelFibRecDynReturnInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelFibRecDynReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(jLabelFibRecDynWins, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelFibRecDynInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelFibRecDynScore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelFibRecDynReturnInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelFibRecDynReturn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addComponent(jLabelFibRecDynWins)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel13.add(jPanel15);

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Iteracyjnie", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18))); // NOI18N

        jLabelFibIterInfo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelFibIterInfo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelFibIterInfo.setText("Wynik(ns):");

        jLabelFibIterScore.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelFibIterScore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelFibIterScore.setText("0");

        jLabelFibIterReturnInfo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelFibIterReturnInfo.setText("Liczba:");

        jLabelFibIterReturn.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelFibIterReturn.setText("0");

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel6.setText("Ile zwycięstw:");

        jLabelFibIterWins.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelFibIterWins.setText("-");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFibIterInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelFibIterScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelFibIterReturnInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelFibIterReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(jLabelFibIterWins, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelFibIterInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelFibIterScore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelFibIterReturnInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelFibIterReturn)
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelFibIterWins)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel13.add(jPanel16);

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel18.setText("Wyraz ciągu (0 - 60):");

        jSpinnerFib.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jSpinnerFib.setModel(new javax.swing.SpinnerNumberModel(0, 0, 60, 1));

        jButtonStartFib.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonStartFib.setText("Start");
        jButtonStartFib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartFibActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Ile razy (1 - 100):");

        jSpinnerFibRuns.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jSpinnerFibRuns.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel8.setText("Ile uruchomień do tej pory:");

        jLabelFibTotalRuns.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelFibTotalRuns.setText("-");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinnerFibRuns, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtonStartFib, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jSpinnerFib, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelFibTotalRuns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinnerFib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerFibRuns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonStartFib)
                    .addComponent(jLabel8)
                    .addComponent(jLabelFibTotalRuns)))
        );

        jTextAreaFib.setEditable(false);
        jTextAreaFib.setColumns(20);
        jTextAreaFib.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextAreaFib.setRows(5);
        jScrollPane1.setViewportView(jTextAreaFib);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel4);

        jTabbedPane1.addTab("Fibonacci", jPanel1);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel25.setText("Ile krążków (1 - 100):");

        jSpinnerHanoi.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jSpinnerHanoi.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        jButtonStartHanoi.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonStartHanoi.setText("Start");
        jButtonStartHanoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartHanoiActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel9.setText("Ile razy (1 - 100):");

        jSpinnerHanoiRuns.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jSpinnerHanoiRuns.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel10.setText("Ile uruchomień do tej pory:");

        jLabelHanoiTotalRuns.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelHanoiTotalRuns.setText("-");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtonStartHanoi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSpinnerHanoi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelHanoiTotalRuns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSpinnerHanoiRuns, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinnerHanoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerHanoiRuns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonStartHanoi)
                    .addComponent(jLabel10)
                    .addComponent(jLabelHanoiTotalRuns))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setLayout(new java.awt.GridLayout());

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Rekurencyjnie", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18))); // NOI18N

        jLabelHanoiRecInfo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelHanoiRecInfo.setText("Wynik(ns):");

        jLabelHanoiRecScore.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelHanoiRecScore.setText("0");

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel11.setText("Ile zwycięstw:");

        jLabelHanoiRecWins.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelHanoiRecWins.setText("-");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHanoiRecInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelHanoiRecScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                    .addComponent(jLabelHanoiRecWins, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelHanoiRecInfo)
                .addGap(18, 18, 18)
                .addComponent(jLabelHanoiRecScore)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabelHanoiRecWins)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel20.add(jPanel21);

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Iteracyjnie", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18))); // NOI18N

        jLabelHanoiIterInfo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelHanoiIterInfo.setText("Wynik(ns):");

        jLabelHanoiIterScore.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelHanoiIterScore.setText("0");

        jLabel15.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel15.setText("Ile zwycięstw:");

        jLabelHanoiIterWins.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelHanoiIterWins.setText("-");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHanoiIterInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelHanoiIterScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                    .addComponent(jLabelHanoiIterWins, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelHanoiIterInfo)
                .addGap(18, 18, 18)
                .addComponent(jLabelHanoiIterScore)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jLabelHanoiIterWins)
                .addGap(0, 37, Short.MAX_VALUE))
        );

        jPanel20.add(jPanel22);

        jTextAreaHanoi.setColumns(20);
        jTextAreaHanoi.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextAreaHanoi.setRows(5);
        jScrollPane2.setViewportView(jTextAreaHanoi);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel5);

        jTabbedPane1.addTab("Hanoi", jPanel2);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel17.setText("Ile elementów w nowym zbiorze (1 - 50 000):");

        jSpinnerQSort.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jSpinnerQSort.setModel(new javax.swing.SpinnerNumberModel(1000, 1, 50000, 1));

        jButtonStartQSort.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonStartQSort.setText("Start");
        jButtonStartQSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartQSortActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel12.setText("Ile razy (1 - 100):");

        jSpinnerQSortRuns.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jSpinnerQSortRuns.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel13.setText("Ile uruchomień do tej pory:");

        jLabelQSortTotalRuns.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelQSortTotalRuns.setText("-");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonStartQSort, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jSpinnerQSort, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelQSortTotalRuns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSpinnerQSortRuns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinnerQSort)
                    .addComponent(jSpinnerQSortRuns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonStartQSort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13)
                    .addComponent(jLabelQSortTotalRuns))
                .addContainerGap())
        );

        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Zbiór rosnący", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18))); // NOI18N

        jLabelRec1RisingInfo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelRec1RisingInfo.setText("Rekurencyjnie1:");

        jLabelRec1RisingScore.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelRec1RisingScore.setText("0");

        jLabelRec2RisingInfo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelRec2RisingInfo.setText("Rekurencyjnie2:");

        jLabelRec2RisingScore.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelRec2RisingScore.setText("0");

        jLabelIterRisingInfo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelIterRisingInfo.setText("Iteracyjnie:");

        jLabelIterRisingScore.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelIterRisingScore.setText("0");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelRec1RisingInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(jLabelRec2RisingInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelRec1RisingScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelRec2RisingScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelIterRisingInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelIterRisingScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelRec1RisingInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelRec1RisingScore)
                .addGap(18, 18, 18)
                .addComponent(jLabelRec2RisingInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelRec2RisingScore)
                .addGap(18, 18, 18)
                .addComponent(jLabelIterRisingInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelIterRisingScore)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel10);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Zbiór malejący", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18))); // NOI18N

        jLabelRec1DescInfo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelRec1DescInfo.setText("Rekurencyjnie1:");

        jLabelRec1DescScore.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelRec1DescScore.setText("0");

        jLabelRec2DescInfo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelRec2DescInfo.setText("Rekurencyjnie2:");

        jLabelRec2DescScore.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelRec2DescScore.setText("0");

        jLabelIterDescInfo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelIterDescInfo.setText("Iteracyjnie:");

        jLabelIterDescScore.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelIterDescScore.setText("0");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelIterDescScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelRec1DescScore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelRec1DescInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(jLabelIterDescInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelRec2DescScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelRec2DescInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelRec1DescInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelRec1DescScore)
                .addGap(18, 18, 18)
                .addComponent(jLabelRec2DescInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelRec2DescScore)
                .addGap(18, 18, 18)
                .addComponent(jLabelIterDescInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelIterDescScore)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel11);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Zbiór losowy", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18))); // NOI18N

        jLabelRec1RandInfo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelRec1RandInfo.setText("Rekurencyjnie1:");

        jLabelRec1RandScore.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelRec1RandScore.setText("0");

        jLabelRec2RandInfo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelRec2RandInfo.setText("Rekurencyjnie2:");

        jLabelRec2RandScore.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelRec2RandScore.setText("0");

        jLabelIterRandInfo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelIterRandInfo.setText("Iteracyjnie:");

        jLabelIterRandScore.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelIterRandScore.setText("0");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelRec2RandInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelRec1RandScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelIterRandInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelIterRandScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelRec1RandInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(jLabelRec2RandScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelRec1RandInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelRec1RandScore)
                .addGap(18, 18, 18)
                .addComponent(jLabelRec2RandInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelRec2RandScore)
                .addGap(18, 18, 18)
                .addComponent(jLabelIterRandInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelIterRandScore)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel12);

        jTextAreaQSort.setColumns(20);
        jTextAreaQSort.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextAreaQSort.setRows(5);
        jScrollPane3.setViewportView(jTextAreaQSort);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.add(jPanel6);

        jTabbedPane1.addTab("Quicksort", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonStartHanoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartHanoiActionPerformed
        int hanoiRunsNumber = Integer.valueOf(jSpinnerHanoiRuns.getValue().toString()); 
        for (int i = 0; i < hanoiRunsNumber; i++) {
            HanoiRun();
            totalHanoiRuns++;
        }
        jLabelHanoiTotalRuns.setText(Integer.toString(totalHanoiRuns));
    }//GEN-LAST:event_jButtonStartHanoiActionPerformed

    private void jButtonStartQSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartQSortActionPerformed
        int qSortRunsNumber = Integer.valueOf(jSpinnerQSortRuns.getValue().toString());
        for (int i = 0; i < qSortRunsNumber; i++) {
            QSortRun();
            totalQSortRuns++;
        }
        jLabelQSortTotalRuns.setText(Integer.toString(totalQSortRuns));
    }//GEN-LAST:event_jButtonStartQSortActionPerformed

    private void jButtonStartFibActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartFibActionPerformed
        int fibRunsNumber = Integer.valueOf(jSpinnerFibRuns.getValue().toString()); 
        for (int i = 0; i < fibRunsNumber; i++) {
            FibRun();
            totalFibRuns++;
        }
        jLabelFibTotalRuns.setText(Integer.toString(totalFibRuns));
    }//GEN-LAST:event_jButtonStartFibActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonStartFib;
    private javax.swing.JButton jButtonStartHanoi;
    private javax.swing.JButton jButtonStartQSort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelFibIterInfo;
    private javax.swing.JLabel jLabelFibIterReturn;
    private javax.swing.JLabel jLabelFibIterReturnInfo;
    private javax.swing.JLabel jLabelFibIterScore;
    private javax.swing.JLabel jLabelFibIterWins;
    private javax.swing.JLabel jLabelFibRecDynInfo;
    private javax.swing.JLabel jLabelFibRecDynReturn;
    private javax.swing.JLabel jLabelFibRecDynReturnInfo;
    private javax.swing.JLabel jLabelFibRecDynScore;
    private javax.swing.JLabel jLabelFibRecDynWins;
    private javax.swing.JLabel jLabelFibRecInfo;
    private javax.swing.JLabel jLabelFibRecReturn;
    private javax.swing.JLabel jLabelFibRecReturnInfo;
    private javax.swing.JLabel jLabelFibRecScore;
    private javax.swing.JLabel jLabelFibRecWins;
    private javax.swing.JLabel jLabelFibTotalRuns;
    private javax.swing.JLabel jLabelHanoiIterInfo;
    private javax.swing.JLabel jLabelHanoiIterScore;
    private javax.swing.JLabel jLabelHanoiIterWins;
    private javax.swing.JLabel jLabelHanoiRecInfo;
    private javax.swing.JLabel jLabelHanoiRecScore;
    private javax.swing.JLabel jLabelHanoiRecWins;
    private javax.swing.JLabel jLabelHanoiTotalRuns;
    private javax.swing.JLabel jLabelIterDescInfo;
    private javax.swing.JLabel jLabelIterDescScore;
    private javax.swing.JLabel jLabelIterRandInfo;
    private javax.swing.JLabel jLabelIterRandScore;
    private javax.swing.JLabel jLabelIterRisingInfo;
    private javax.swing.JLabel jLabelIterRisingScore;
    private javax.swing.JLabel jLabelQSortTotalRuns;
    private javax.swing.JLabel jLabelRec1DescInfo;
    private javax.swing.JLabel jLabelRec1DescScore;
    private javax.swing.JLabel jLabelRec1RandInfo;
    private javax.swing.JLabel jLabelRec1RandScore;
    private javax.swing.JLabel jLabelRec1RisingInfo;
    private javax.swing.JLabel jLabelRec1RisingScore;
    private javax.swing.JLabel jLabelRec2DescInfo;
    private javax.swing.JLabel jLabelRec2DescScore;
    private javax.swing.JLabel jLabelRec2RandInfo;
    private javax.swing.JLabel jLabelRec2RandScore;
    private javax.swing.JLabel jLabelRec2RisingInfo;
    private javax.swing.JLabel jLabelRec2RisingScore;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinnerFib;
    private javax.swing.JSpinner jSpinnerFibRuns;
    private javax.swing.JSpinner jSpinnerHanoi;
    private javax.swing.JSpinner jSpinnerHanoiRuns;
    private javax.swing.JSpinner jSpinnerQSort;
    private javax.swing.JSpinner jSpinnerQSortRuns;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextAreaFib;
    private javax.swing.JTextArea jTextAreaHanoi;
    private javax.swing.JTextArea jTextAreaQSort;
    // End of variables declaration//GEN-END:variables
}
