import java.util.Random;

public class Main {
    public static void main(String[] args) {
        
        // ==========================================
        // UC1: Create and Display Empty Board
        // ==========================================
        char[][] board = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }

        System.out.println("--- Tic-Tac-Toe ---");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println(); 
        }
        System.out.println("-------------------");

        // ==========================================
        // UC2: Toss to Decide First Player & Symbol
        // ==========================================
        System.out.println("\n--- Flipping the coin ---");
        
        Random random = new Random();
        // random.nextInt(2) generates either a 0 or a 1
        int toss = random.nextInt(2); 

        // Game State Variables
        char playerSymbol;
        char computerSymbol;
        int currentPlayer; // Let 1 = Human Player, 2 = Computer

        if (toss == 0) {
            System.out.println("You won the toss! You get to go first.");
            currentPlayer = 1;
            playerSymbol = 'X';
            computerSymbol = 'O';
        } else {
            System.out.println("The computer won the toss and will go first.");
            currentPlayer = 2;
            computerSymbol = 'X';
            playerSymbol = 'O';
        }

        System.out.println("Your Symbol: " + playerSymbol);
        System.out.println("Computer's Symbol: " + computerSymbol);
        System.out.println("-------------------------");
    }
}