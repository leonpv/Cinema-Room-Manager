package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        String[][] grid = new String[7][8];
        for (String[] strings : grid) {
            Arrays.fill(strings, "S");
        }
        //System.out.println("Cinema:");
        //printBorderedGreed(grid);
        soldStage();
    }

    private static void soldStage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int firstNumber = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int secondNumber = scanner.nextInt();

        int totalIncome = 0;
        int totalSeats = firstNumber * secondNumber;
        if (totalSeats < 60){
            totalIncome = totalSeats * 10;
        } else {
            if (firstNumber % 2 == 1) {
                totalIncome = ((firstNumber / 2) * secondNumber * 10) +
                              ((totalSeats - (firstNumber / 2) * secondNumber) * 8);
            } else {
                totalIncome = ((totalSeats / 2) * 10) + ((totalSeats / 2) * 8);
            }
        }
        System.out.printf("Total income:%n$%d", totalIncome);
    }

    private static void printBorderedGreed(String[][] grid) {
        String[][] gridBordered = new String[grid.length + 1][grid[0].length + 1];
        for (int i = 0; i < gridBordered.length; i++) {
            for (int j = 0; j < gridBordered[i].length; j++) {
                if (i == 0 && j == 0) {
                    gridBordered[i][j] = " ";
                    continue;
                }
                if (i == 0) {
                    gridBordered[i][j] = String.valueOf(j);
                    continue;
                }
                if (j == 0) {
                    gridBordered[i][j] = String.valueOf(i);
                    continue;
                }
                gridBordered[i][j] = grid[i - 1][j - 1];
            }
        }
        for (int i = 0; i < gridBordered.length; i++) {
            for (int j = 0; j < gridBordered[i].length; j++) {
                System.out.print(gridBordered[i][j] + " ");
            }
            System.out.println();
        }
    }


}
