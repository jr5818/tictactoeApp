import java.util.Random;
import java.util.Scanner; 

public class Main {

    // UTILITY: Print the Board 
    public static void printBoard(char[][] board) {
        System.out.println("\n--- Current Board ---");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println(); 
        }
        System.out.println("-------------------");
    }

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

    // ==========================================
    // UC9: Check Winning Condition
    // ==========================================
    public static boolean checkWin(char[][] board, char symbol) {
        // 1. Check all 3 Rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true; // A row matches!
            }
        }
        
        // 2. Check all 3 Columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == symbol && board[1][j] == symbol && board[2][j] == symbol) {
                return true; // A column matches!
            }
        }
        
        // 3. Check the 2 Diagonals
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true; // Top-left to bottom-right match!
        }
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true; // Top-right to bottom-left match!
        }
        
        return false; // No winner yet
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random(); 

        // UC1: Initialize Board
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
        printBoard(board);

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

        // UC8: Game Loop
        boolean gameActive = true;
        int totalMoves = 0; 

        while (gameActive) {
            if (currentPlayer == 1) {
                // --- Human Turn ---
                System.out.println("It is your turn. (Symbol: " + playerSymbol + ")");
                boolean validTurnCompleted = false;
                
                while (!validTurnCompleted) {
                    int selectedSlot = getUserMove(scanner);         
                    int[] position = getBoardPosition(selectedSlot); 
                    int row = position[0];
                    int col = position[1];
                    
                    if (isValidMove(board, row, col)) {              
                        placeMove(board, row, col, playerSymbol);
                        validTurnCompleted = true;     
                    } else {
                        System.out.println("Invalid move! Try again.\n");
                    }
                }
                
                // UC9: Check if Human Won
                if (checkWin(board, playerSymbol)) {
                    printBoard(board);
                    System.out.println("🎉 CONGRATULATIONS! You won the game! 🎉");
                    gameActive = false;
                    break; // Exit the loop immediately
                }

            } else {
                // --- Computer Turn ---
                System.out.println("It is the computer's turn. (Symbol: " + computerSymbol + ")");
                boolean validTurnCompleted = false;
                
                while (!validTurnCompleted) {
                    int computerSlot = random.nextInt(9) + 1; 
                    int[] position = getBoardPosition(computerSlot);
                    int row = position[0];
                    int col = position[1];
                    
                    if (isValidMove(board, row, col)) {
                        System.out.println("Computer chose slot: " + computerSlot);
                        placeMove(board, row, col, computerSymbol);
                        validTurnCompleted = true;
                    }
                }
                
                // UC9: Check if Computer Won
                if (checkWin(board, computerSymbol)) {
                    printBoard(board);
                    System.out.println("💻 The computer wins! Better luck next time. 💻");
                    gameActive = false;
                    break; // Exit the loop immediately
                }
            }

            // Increase move count and print board
            totalMoves++;
            printBoard(board);

            // Check for Draw 
            if (totalMoves == 9) {
                System.out.println("🤝 The board is full! It's a draw! 🤝");
                gameActive = false; 
            }

            // --- Switch Turns ---
            if (currentPlayer == 1) {
                currentPlayer = 2; 
            } else {
                currentPlayer = 1; 
            }
        }

        System.out.println("Game Over! Thanks for playing.");
        scanner.close();
    }
}