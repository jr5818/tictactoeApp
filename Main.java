public class Main {
    public static void main(String[] args) {
        // 1. Create a 3x3 character array
        char[][] board = new char[3][3];

        // 2. Initialize all cells with '-'
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }

        // 3. Print the board clearly
        System.out.println("--- Tic-Tac-Toe ---");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println(); // Move to the next line after each row
        }
        System.out.println("-------------------");
    }
}