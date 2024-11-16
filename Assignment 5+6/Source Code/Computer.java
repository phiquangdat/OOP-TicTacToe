import java.util.Random;
class Computer {
    private final char computerSymbol;

    public Computer(char symbol){
        this.computerSymbol = symbol;
    }
    public char getSymbol(){ 
        return this.computerSymbol;
    }

    public void moveComputer(Board board){
        Random r = new Random();
        int pos;
        while(true)
        {
            pos = 1 + r.nextInt(10) ;
            if(board.isValidMove(pos))
                break;
        }
        System.out.println("Computer placed at position : " + pos);
        board.placeMove(pos, computerSymbol);
    }
}
