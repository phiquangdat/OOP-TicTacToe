import java.util.Scanner;
class Player {
    public Player(char symbol){
        this.playerSymbol = symbol;
    }

    private char playerSymbol;
        
    public char getSymbol(){
        return playerSymbol;
    }
    
    public void setSymbol(char symbol){
        this.playerSymbol = symbol;
    }
    
    public void movePlayer(Board board){
        Scanner sc = new Scanner(System.in);
        int pos;
        while(true){
            System.out.println("Enter your position (1-9): ");
            pos = sc.nextInt();
            if(board.isValidMove(pos))
                break;
            else
                System.out.println("Invalid move, try again!");
        }
        board.placeMove(pos, playerSymbol);
    }
}