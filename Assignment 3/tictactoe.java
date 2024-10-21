import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class tictactoe {
    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {
        char[][] board = {
            {' ', '|', ' ', '|', ' '}, 
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}
        };

        printBoard(board);

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter your placement (1-9): ");
                int pos = scanner.nextInt();

                while (playerPositions.contains(pos) || cpuPositions.contains(pos) || pos < 1 || pos > 9) {
                    System.out.println("Position taken! Enter a correct position.");
                    pos = scanner.nextInt();
                }

                placeBoard(board, pos, "user");

                String result = checkBoard();
                if (!result.isEmpty()) {
                    printBoard(board);
                    System.out.println(result);
                    break;
                }
               
                Random rand = new Random();
                int cpuPos = rand.nextInt(9) + 1; 
                while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                    cpuPos = rand.nextInt(9) + 1;
                }

                placeBoard(board, cpuPos, "computer");

                printBoard(board);

                result = checkBoard();
                if (!result.isEmpty()) {
                    System.out.println(result);
                    break;
                }

                if (playerPositions.size() + cpuPositions.size() == 9) {
                    System.out.println("It's a draw!");
                    break;
                }
            }
        }
    }

    private static void placeBoard(char[][] board, int pos, String user) {
        char symbol = (user.equals("user")) ? 'X' : 'O';

        if (user.equals("user")) {
            playerPositions.add(pos);
        } else {
            cpuPositions.add(pos);
        }

        switch (pos) {
            case 1 -> board[0][0] = symbol;
            case 2 -> board[0][2] = symbol;
            case 3 -> board[0][4] = symbol;
            case 4 -> board[2][0] = symbol;
            case 5 -> board[2][2] = symbol;
            case 6 -> board[2][4] = symbol;
            case 7 -> board[4][0] = symbol;
            case 8 -> board[4][2] = symbol;
            case 9 -> board[4][4] = symbol;
        }
    }

    private static String checkBoard() {
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> midRow = Arrays.asList(4, 5, 6);
        List<Integer> botRow = Arrays.asList(7, 8, 9);
        List<Integer> leftCol = Arrays.asList(1, 4, 7);
        List<Integer> midCol = Arrays.asList(2, 5, 8);
        List<Integer> rightCol = Arrays.asList(3, 6, 9);
        List<Integer> cross1 = Arrays.asList(1, 5, 9);
        List<Integer> cross2 = Arrays.asList(3, 5, 7);

        List<List<Integer>> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List<Integer> l : winning) {
            if (playerPositions.containsAll(l)) {
                return "You won!";
            } else if (cpuPositions.containsAll(l)) {
                return "CPU wins!";
            }
        }

        return "";
    }

    private static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
