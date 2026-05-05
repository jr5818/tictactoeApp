import java.util.Random;
import java.util.Scanner; 

public class Main {

    // ==========================================
    // UC3: Accept User Slot Input
    // ==========================================
    public static int getUserMove(Scanner scanner) {
        System.out.print("Enter a slot number (1-9): ");
        return scanner.nextInt();
    }

    // ==========================================
    // UC4: Convert Slot Number to Board Index
    // ==========================================
    public static int[] getBoardPosition(int slot) {
        int index = slot - 1; 
        int row = index / 3;
        int col = index % 3;
        return new int[]{row, col}; 
    }

    // ==========================================
    // UC5: Validate User Move
    // ==========================================
    public static boolean isValidMove(char[][] board, int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false; 
        }
        if (board[row][col] != '-') {
            return false;
        }
        return true; 
    }

    // ==========================================
    // UC6: Place Move on Board (New Reusable Method)
    // ==========================================
    public static void placeMove(char[][] board, int row, int col, char symbol) {
        // Updates the state of the game board
        board[row][col] = symbol;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // UC1: Initialize Board
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

        // UC2: Toss
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

        // Player Turn Loop
        if (currentPlayer == 1) {
            System.out.println("It is your turn.");
            
            boolean validTurnCompleted = false;
            
            while (!validTurnCompleted) {
                int selectedSlot = getUserMove(scanner);         // UC3
                int[] position = getBoardPosition(selectedSlot); // UC4
                
                int row = position[0];
                int col = position[1];
                
                if (isValidMove(board, row, col)) {              // UC5
                    System.out.println("Move accepted!");
                    
                    // UC6: Use our new reusable method to place the piece!
                    placeMove(board, row, col, playerSymbol);
                    
                    validTurnCompleted = true;     
                } else {
                    System.out.println("Invalid move! That slot is either out of bounds or already taken. Try again.\n");
                }
            }
            
            // Show the updated board
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
