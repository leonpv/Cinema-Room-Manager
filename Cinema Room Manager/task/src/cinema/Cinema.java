package cinema;

import java.util.Arrays;
import java.util.Objects;
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

        int menu = 4;
        while (menu != 0) {
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            menu = scanner.nextInt();
            switch (menu) {
                case 1 -> showTheSeats(grid);
                case 2 -> buyTicket(scanner, grid);
                case 3 -> showStatistics(grid);
                default -> System.out.println("Choose 1, 2 or 0");
            }
        }
    }

    private static void showStatistics(String[][] grid) {
        System.out.println("Number of purchased tickets: " + numberOfPurchasedTickets(grid));
        showPercentage(grid);
        showCurrentIncome(grid);
        showTotalIncome(grid);
    }

    private static void showCurrentIncome(String[][] grid) {
        int currentIncome = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (Objects.equals(grid[i][j], "B")) {
                    currentIncome += ticketPrice(grid, i + 1, j + 1);
                }
            }
        }

        System.out.println("Current income: $" + currentIncome);
    }

    private static void showPercentage(String[][] grid) {
        double totalSeats = grid.length * (grid[grid.length - 1].length);
        double percentage = (numberOfPurchasedTickets(grid) * 100 / totalSeats);
        String string = String.format("Percentage: %.2f%%", percentage);
        System.out.println(string);
    }

    private static int numberOfPurchasedTickets(String[][] grid) {
        int numberOfPurchasedTickets = 0;
        for (String[] strings : grid) {
            for (String string : strings) {
                if (Objects.equals(string, "B")) {
                    numberOfPurchasedTickets++;
                }
            }
        }
        return numberOfPurchasedTickets;
    }

    private static void buyTicket(Scanner scanner, String[][] grid) {

        while (true) {

            System.out.println("\nEnter a row number: ");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row: ");
            int seat = scanner.nextInt();


            if (row <= 0 || seat < 1 || row > grid.length || seat > grid[row - 1].length) {
                System.out.println("Wrong input!");
                continue;
            }
            if (Objects.equals(grid[row - 1][seat - 1], "B")) {
                System.out.println("That ticket has already been purchased!");
                continue;
            }

            System.out.printf("%nTicket price: $%d%n", ticketPrice(grid, row, seat));
            grid[row - 1][seat - 1] = "B";
            break;
        }

    }

    private static void showTheSeats(String[][] grid) {
        System.out.println("\nCinema:");
        printBorderedGreed(grid);
    }

    private static int ticketPrice(String[][] grid, int row, int seat) {
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
        return ticketPrice;
    }

    private static void showTotalIncome(String[][] grid) {
        int row = grid.length;
        int seat = grid[row - 1].length;

        int totalIncome = 0;
        int totalSeats = row * seat;
        if (totalSeats < 60) {
            totalIncome = totalSeats * 10;
        } else {
            if (row % 2 == 1) {
                totalIncome = ((row / 2) * seat * 10) +
                        ((totalSeats - (row / 2) * seat) * 8);
            } else {
                totalIncome = ((totalSeats / 2) * 10) + ((totalSeats / 2) * 8);
            }
        }
        System.out.printf("Total income: $%d%n", totalIncome);
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
