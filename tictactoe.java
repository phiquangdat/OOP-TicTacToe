import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.spi.CurrencyNameProvider;
import javax.naming.spi.DirStateFactory;

public class tictactoe {
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    public static void main(String[] args) {
        char[][] board = {{' ', '|', ' ', '|', ' '}, 
        {'-',  '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '},
        {'-',  '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '}};
        while (true) { 
            System.out.println("Enter your placement (1-9)");
            Scanner scanner = new Scanner(System.in);
            int pos = scanner.nextInt();
            placeBoard(board, pos, "user");
        
            Random rand = new Random();
            String checkresult = checkBoard();
            placeBoard(board, rand.nextInt(9), "computer");
            
            printBoard(board);
            if(checkresult.length() > 0)
            {System.out.println(checkresult);
            break;}
        }
        
        
}
    private static void placeBoard(char[][] board, int pos, String user){
        char c = 'X';
        if (user.equals("computer"))
            {
                c = 'O';
                cpuPositions.add(pos);
            }
        else playerPositions.add(pos);
        
        switch(pos)
        {
            case 1 -> board[0][0] = c;
            case 2 -> board[0][2] = c;
            case 3 -> board[0][4] = c;
            case 4 -> board[2][0] = c;
            case 5 -> board[2][2] = c;
            case 6 -> board[2][4] = c;
            case 7 -> board[4][0] = c;
            case 8 -> board[4][2] = c;
            case 9 -> board[4][4] = c;
            }


    }
    private static String checkBoard() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4 ,7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for(List l : winning)
        {
            if(playerPositions.containsAll(l)){
            return "YOU WON!";
            }
            else if(cpuPositions.containsAll(l)){
                return "YOU LOST";
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