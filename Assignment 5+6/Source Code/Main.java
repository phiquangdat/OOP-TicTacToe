import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your symbol (X or O): ");
        char symbol = scanner.next().charAt(0);
        Game game = new Game(symbol);
        game.startGame();
    }
}
