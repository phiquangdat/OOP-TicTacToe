class Board {
    //Create a static variable for the board to be used the whole program
    public char[][] boardSymbols = {{' ', '|', ' ', '|', ' '},
    {'-', '+', '-', '+', '-'},
    {' ', '|', ' ', '|', ' '},
    {'-', '+', '-', '+', '-'},
    {' ', '|', ' ', '|', ' '}};

    //Print the board using nested loops
    public void printBoard() {
    for(char[] row : boardSymbols) {
        for(char cell : row) {
            System.out.print(cell);
        }
        System.out.println();
    }
}

    public void placeMove(int pos, char symbol) {
    switch(pos){
        case 1 -> {boardSymbols[0][0] = symbol;}
        case 2 -> {boardSymbols[0][2] = symbol;}
        case 3 -> {boardSymbols[0][4] = symbol;}
        case 4 -> {boardSymbols[2][0] = symbol;}
        case 5 -> {boardSymbols[2][2] = symbol;}
        case 6 -> {boardSymbols[2][4] = symbol;}
        case 7 -> {boardSymbols[4][0] = symbol;}
        case 8 -> {boardSymbols[4][2] = symbol;}
        case 9 -> {boardSymbols[4][4] = symbol;}
    }
}
    public boolean isValidMove(int pos){
        switch(pos){
            case 1 -> {return boardSymbols[0][0] == ' ';}
            case 2 -> {return boardSymbols[0][2] == ' ';}
            case 3 -> {return boardSymbols[0][4] == ' ';}
            case 4 -> {return boardSymbols[2][0] == ' ';}
            case 5 -> {return boardSymbols[2][2] == ' ';}
            case 6 -> {return boardSymbols[2][4] == ' ';}
            case 7 -> {return boardSymbols[4][0] == ' ';}
            case 8 -> {return boardSymbols[4][2] == ' ';}
            case 9 -> {return boardSymbols[4][4] == ' ';}
            default -> {return false;}
        }
    }
}

