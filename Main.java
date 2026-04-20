import java.util.Random;
import java.util.Scanner; 

public class Main {

    
    public static int getUserMove(Scanner scanner) {
        System.out.print("Enter a slot number (1-9): ");
        int slot = scanner.nextInt();
        return slot;
    }

    public static int[] getBoardPosition(int slot) {
        int index = slot - 1; 
        int row = index / 3;
        int col = index % 3;
        return new int[]{row, col}; 
    }


    // UC5: Validate User Move

    public static boolean isValidMove(char[][] board, int row, int col) {
        // 1. Boundary Checking (must be between 0 and 2)
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false; 
        }
        // 2. Occupancy Checking (must be empty '-')
        if (board[row][col] != '-') {
            return false;
        }
        
        return true; // Passed both checks!
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);




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


        // UC2: Toss to Decide First Player & Symbol

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

        // ==========================================
        // UC3, UC4, & UC5: The Player Turn Loop
        // ==========================================
        if (currentPlayer == 1) {
            System.out.println("It is your turn.");
            
            boolean validTurnCompleted = false;
            
            // Loop until the player gives us a valid move
            while (!validTurnCompleted) {
                int selectedSlot = getUserMove(scanner);         // UC3
                int[] position = getBoardPosition(selectedSlot); // UC4
                
                int row = position[0];
                int col = position[1];
                
                // UC5: Validate it before doing anything else!
                if (isValidMove(board, row, col)) {
                    System.out.println("Move accepted!");
                    board[row][col] = playerSymbol; // Actually place the piece!
                    validTurnCompleted = true;      // Break out of the loop
                } else {
                    // Rejected! The loop will run again.
                    System.out.println("Invalid move! That slot is either out of bounds or already taken. Try again.\n");
                }
            }
            
            // Show the updated board to prove it worked
            System.out.println("\n--- Updated Board ---");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println(); 
            }

        } else {
            System.out.println("It is the computer's turn.");
        }

        scanner.close();
    }
}