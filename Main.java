import java.util.Random;
import java.util.Scanner; // Required for reading user input

public class Main {

    public static int getUserMove(Scanner scanner) {
        System.out.print("Enter a slot number (1-9): ");
        int slot = scanner.nextInt();
        return slot;
    }

    public static void main(String[] args) {
        // Initialize the Scanner once for the whole program
        Scanner scanner = new Scanner(System.in);

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


        System.out.println("\n--- Flipping the coin ---");
        
        Random random = new Random();
        int toss = random.nextInt(2); 

        char playerSymbol;
        char computerSymbol;
        int currentPlayer; // 1 = Human Player, 2 = Computer

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
        System.out.println("-------------------------\n");

       


        if (currentPlayer == 1) {
            System.out.println("It is your turn.");
            int selectedSlot = getUserMove(scanner);
            System.out.println("--> Acknowledged: You selected slot " + selectedSlot);
        } else {
            System.out.println("It is the computer's turn. (We will build this later!)");
        }


        scanner.close();
    }
}