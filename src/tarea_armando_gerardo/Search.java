/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea_armando_gerardo;

import java.util.Arrays;

/**
 *
 * @author Gerardo Vargas Fernández
 * @author Armando Sibaja Elizondo
 */
public class Search {

    public static int[][] setMatrix() {
        int[][] myMtr = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                myMtr[i][j] = (int) (Math.random() * 100);
            }
        }
        return myMtr;
    }

    public static void printMatrx(int[][] myMtr) {
        for (int i = 0; i < 10; i++) {
            Arrays.sort(myMtr[i]);
            for (int j = 0; j < 10; j++) {
                if (myMtr[i][j] >= 10) {
                    System.out.print(myMtr[i][j] + " ");
                } else {
                    System.out.print(myMtr[i][j] + "  ");
                }

            }
            System.out.print("\n");
        }

    }

    public static void binariSearch(int[][] myMtr, int num) {
        boolean found = false;
        int position = 0;
        for (int i = 0; i < 10; i++) {
            if ((position = Arrays.binarySearch(myMtr[i], num)) >= 0) {
                System.out.println("\nThe number " + num + " is the position " + i + "," + position);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("\nNumber " + num + " couldn´t be found.");
        }
    }
}
