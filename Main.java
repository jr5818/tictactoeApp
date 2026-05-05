import java.util.Random;
import java.util.Scanner; 

public class Main {

    // UC3: Accept User Slot Input
    public static int getUserMove(Scanner scanner) {
        System.out.print("Enter a slot number (1-9): ");
        return scanner.nextInt();
    }

    // UC4: Convert Slot Number to Board Index
    public static int[] getBoardPosition(int slot) {
        int index = slot - 1; 
        int row = index / 3;
        int col = index % 3;
        return new int[]{row, col}; 
    }

    // UC5: Validate User Move
    public static boolean isValidMove(char[][] board, int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false; 
        }
        if (board[row][col] != '-') {
            return false;
        }
        return true; 
    }

    // UC6: Place Move on Board
    public static void placeMove(char[][] board, int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random(); // We can use this for the toss AND the computer move

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
        int toss = random.nextInt(2); 

        char playerSymbol;
        char computerSymbol;
        int currentPlayer; 

        if (toss == 0) {
            System.out.println("You won the toss! You get to go first.\n");
            currentPlayer = 1;
            playerSymbol = 'X';
            computerSymbol = 'O';
        } else {
            System.out.println("The computer won the toss and will go first.\n");
            currentPlayer = 2;
            computerSymbol = 'X';
            playerSymbol = 'O';
        }

        // ==========================================
        // UC3 to UC7: The Turn Logic
        // ==========================================
        if (currentPlayer == 1) {
            // Human Turn
            System.out.println("It is your turn. (Symbol: " + playerSymbol + ")");
            boolean validTurnCompleted = false;
            
            while (!validTurnCompleted) {
                int selectedSlot = getUserMove(scanner);         
                int[] position = getBoardPosition(selectedSlot); 
                int row = position[0];
                int col = position[1];
                
                if (isValidMove(board, row, col)) {              
                    System.out.println("Move accepted!");
                    placeMove(board, row, col, playerSymbol);
                    validTurnCompleted = true;     
                } else {
                    System.out.println("Invalid move! That slot is either out of bounds or already taken. Try again.\n");
                }
            }

        } else {
            // UC7: Computer Turn (Easy Level)
            System.out.println("It is the computer's turn. (Symbol: " + computerSymbol + ")");
            boolean validTurnCompleted = false;
            
            while (!validTurnCompleted) {
                // Generate a random slot between 1 and 9
                int computerSlot = random.nextInt(9) + 1; 
                
                // Reuse the exact same methods we built for the human!
                int[] position = getBoardPosition(computerSlot);
                int row = position[0];
                int col = position[1];
                
                if (isValidMove(board, row, col)) {
                    System.out.println("Computer chose slot: " + computerSlot);
                    placeMove(board, row, col, computerSymbol);
                    validTurnCompleted = true;
                }
                // If it picked an invalid move, the loop just quietly runs again!
            }
        }

        // Show the updated board to prove it worked (whether human or computer played)
        System.out.println("\n--- Updated Board ---");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println(); 
        }

        scanner.close();
    }
}