package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int firstNumber = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int secondNumber = scanner.nextInt();

        String[][] grid = new String[firstNumber][secondNumber];
        for (String[] strings : grid) {
            Arrays.fill(strings, "S");
        }


        int menu = 3;
        while (menu != 0) {
            System.out.println("1. Show the seats\n2. Buy a ticket\n0. Exit");
            menu = scanner.nextInt();
            switch (menu) {
                case 1:
                    showTheSeats(grid);
                    break;
                case 2:
                    buyTicket(scanner, grid);
                    break;
                default:
                    System.out.println("Choose 1, 2 or 0");
                    break;
            }
        }


        //soldStage();


        //System.out.println("\nCinema:");
        //printBorderedGreed(grid);
    }

    private static void buyTicket(Scanner scanner, String[][] grid) {
        System.out.println("\nEnter a row number: ");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row: ");
        int seat = scanner.nextInt();

        printTicketPrice(grid, row, seat);

        grid[row - 1][seat - 1] = "B";
    }

    private static void showTheSeats(String[][] grid) {
        System.out.println("\nCinema:");
        printBorderedGreed(grid);
    }

    private static void printTicketPrice(String[][] grid, int row, int seat) {
        int ticketPrice = 0;
        int totalSeats = grid.length * (grid[grid.length - 1].length);
        if (totalSeats < 60) {
            ticketPrice = 10;
        } else {
            if (row % 2 == 1) {
                if (row < (grid.length / 2)) {
                    ticketPrice = 10;
                } else {
                    ticketPrice = 8;
                }
            } else {
                if (row <= (grid.length / 2)) {
                    ticketPrice = 10;
                } else {
                    ticketPrice = 8;
                }
            }
        }
        System.out.printf("%nTicket price: $%d", ticketPrice);
    }

    private static void soldStage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int firstNumber = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int secondNumber = scanner.nextInt();

        int totalIncome = 0;
        int totalSeats = firstNumber * secondNumber;
        if (totalSeats < 60) {
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
