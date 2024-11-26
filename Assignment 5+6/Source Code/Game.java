import java.util.Random;
class Game {
    private final Board board;
    private final Player player;
    private final Computer computer;
    private boolean isPlayerTurn;

    public Game(char playerSymbol){
        this.board = new Board();
        this.player = new Player(playerSymbol);
        this.computer = new Computer((playerSymbol == 'X') ? 'O' : 'X');
        this.isPlayerTurn = new Random().nextBoolean();
    }
    // Check three given positions on the board contain the same non-empty symbol
    private boolean checkLine(int r1, int c1, int r2, int c2, int r3, int c3) {
        return board.boardSymbols[r1][c1] != ' ' &&
               board.boardSymbols[r1][c1] == board.boardSymbols[r2][c2] &&
               board.boardSymbols[r2][c2] == board.boardSymbols[r3][c3];
    }

    // Check Game Condition
    public String checkCondition() {
        // Check for winning conditions
        if (checkLine(0, 0, 0, 2, 0, 4) || // Top row X X X
            checkLine(2, 0, 2, 2, 2, 4) || // Middle row
            checkLine(4, 0, 4, 2, 4, 4) || // Bottom row
            checkLine(0, 0, 2, 0, 4, 0) || // Left column
            checkLine(0, 2, 2, 2, 4, 2) || // Middle column
            checkLine(0, 4, 2, 4, 4, 4) || // Right column
            checkLine(0, 0, 2, 2, 4, 4) || // Diagonal top-left to bottom-right
            checkLine(0, 4, 2, 2, 4, 0))   // Diagonal top-right to bottom-left
        {
            return isPlayerTurn ? "Player Wins" : "CPU Wins";
        }

        // Check if the board is full -> DRAW
        boolean boardFull = true;
        for (char[] row : board.boardSymbols) {
            for (char cell : row) {
                if (cell == ' ') {
                    boardFull = false;
                    break;
                }
            }
        }

        if (boardFull) {
            return "DRAW!";
        }

        // If no win or draw, the game continues
        return "";
    }

    public void startGame(){
        System.out.println((isPlayerTurn ? "Player " : "Computer ") + "goes first");
        while (true) {
            board.printBoard();
            if (isPlayerTurn) 
                player.move(board);
            else
                computer.move(board);
            if (!checkCondition().equals("")) 
                {
                    board.printBoard();
                    System.out.println(checkCondition());
                    break;
                }
            
            isPlayerTurn = !isPlayerTurn;
            
        }
    }
}
