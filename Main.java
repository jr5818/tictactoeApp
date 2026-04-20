import java.util.Random;
import java.util.Scanner; 

public class Main {


    public static int getUserMove(Scanner scanner) {
        System.out.print("Enter a slot number (1-9): ");
        int slot = scanner.nextInt();
        return slot;
    }

    
    public static int[] getBoardPosition(int slot) {
        // Convert 1-9 to 0-8 for zero-based math
        int index = slot - 1; 
        
        int row = index / 3;
        int col = index % 3;
        
        // Return as an array where [0] is row and [1] is column
        return new int[]{row, col}; 
    }

    public static void main(String[] args) {
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

        // ==========================================
        // UC2: Toss to Decide First Player & Symbol
        // ==========================================
        System.out.println("\n--- Flipping the coin ---");
        
        Random random = new Random();
        int toss = random.nextInt(2); 

        char playerSymbol;
        char computerSymbol;
        int currentPlayer; 

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


        // UC3 & UC4 Demonstration Flow

        if (currentPlayer == 1) {
            System.out.println("It is your turn.");
            

            int selectedSlot = getUserMove(scanner);
            
            // UC4: Convert that slot into row/col indices
            int[] position = getBoardPosition(selectedSlot);
            int row = position[0];
            int col = position[1];
            
            System.out.println("--> Math Check: Slot " + selectedSlot + " maps to array index board[" + row + "][" + col + "]");
        } else {
            System.out.println("It is the computer's turn.");
        }

        scanner.close();
    }
}