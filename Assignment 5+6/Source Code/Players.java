import java.util.Scanner;
import java.util.Random;
abstract class Players {
    private char symbol;

    public Players(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public abstract void move(Board board);
}

class Human extends Players {
    public Human(char symbol) {
        super(symbol);
    }

    @Override
    public void move(Board board) {
        Scanner sc = new Scanner(System.in);
        int pos;
        while(true) {
            System.out.print("Enter position (1-9): ");
            pos = sc.nextInt();
            if(board.isValidMove(pos))
                break;
            else
                System.out.println("Invalid move, try again.");
        }
        board.placeMove(pos, this.getSymbol());
    }
}

class Computer extends Players {
    public Computer(char symbol) {
        super(symbol);
    }

    @Override
    public void move(Board board) {
        Random r = new Random();
        int pos;
        while(true){
            pos = r.nextInt(10) + 1;
            if(board.isValidMove(pos))
                break;
        }
        System.out.println("Computer placed at: " + pos);
        board.placeMove(pos, this.getSymbol());
    }
}

