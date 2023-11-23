import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] playingRound = new int[9];
        int pickPosition;
        boolean winner = false;
        String playerSymbol = " ";

        String[][] gameField = new String[3][3];
        fillTheGameFieldWithPositions(gameField);
        printGameField(gameField);

        for (int i = 0; i < playingRound.length; i++) {
            do {
                while (true) {
                    try {
                        System.out.println("Pick the position between 1-9: ");
                        if (i % 2 == 0) {
                            System.out.println("Player X play: ");
                            pickPosition = scanner.nextInt();
                            playerSymbol = "X";

                        } else {
                            System.out.println("Player O play");
                            pickPosition = scanner.nextInt();
                            playerSymbol = "O";
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("Enter valid number between 1-9");
                        scanner.nextLine();
                    }
                }
            } while (checkTakenPosition(playingRound, pickPosition));

            playingRound[i] = pickPosition;
            addPlayerInput(gameField, pickPosition, playerSymbol);
            printGameField(gameField);

            if (checkWin(gameField, playerSymbol)) {
                winner = true;
                break;
            }
        }

        System.out.println("\n|------------------|");
        if (!winner) {
            System.out.println("\tIt's a draw!");
        } else {
            System.out.println("\tPlayer " + playerSymbol.trim() + " WIN!");
        }
        System.out.println("|------------------|");
    }

    public static void fillTheGameFieldWithPositions(String[][] gameField) {
        int position = 1;
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                gameField[i][j] = " " + position + " ";
                position++;
            }
        }
    }

    public static boolean checkTakenPosition(int[] positionsArray, int newPosition) {
        for (int position : positionsArray) {
            if (newPosition < 1 || newPosition > positionsArray.length) {
                System.out.println("Invalid move!");
                return true;
            }

            if (position == newPosition) {
                System.out.println("Position taken!");
                return true;
            }
        }
        return false;
    }

    public static void printGameField(String[][] gameField) {
        System.out.println();

        for (String[] row : gameField) {
            System.out.print("|");
            for (String column : row) {
                System.out.print(column);
                System.out.print("|");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public static void addPlayerInput(String[][] gameField, int playerPick, String playerSymbol) {
        playerSymbol = " " + playerSymbol + " ";
        switch (playerPick) {
            case 1 -> gameField[0][0] = playerSymbol;
            case 2 -> gameField[0][1] = playerSymbol;
            case 3 -> gameField[0][2] = playerSymbol;
            case 4 -> gameField[1][0] = playerSymbol;
            case 5 -> gameField[1][1] = playerSymbol;
            case 6 -> gameField[1][2] = playerSymbol;
            case 7 -> gameField[2][0] = playerSymbol;
            case 8 -> gameField[2][1] = playerSymbol;
            case 9 -> gameField[2][2] = playerSymbol;
        }
    }

    public static boolean checkWin(String[][] gameField, String playerInput) {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {

                if (gameField[i][0].trim().equals(playerInput)
                        && gameField[i][1].trim().equals(playerInput)
                        && gameField[i][2].trim().equals(playerInput)) {
                    return true;
                }

                if (gameField[0][j].trim().equals(playerInput)
                        && gameField[1][j].trim().equals(playerInput)
                        && gameField[2][j].trim().equals(playerInput)) {
                    return true;
                }

                if (gameField[0][0].trim().equals(playerInput)
                        && gameField[1][1].trim().equals(playerInput)
                        && gameField[2][2].trim().equals(playerInput)) {
                    return true;
                }

                if (gameField[0][2].trim().equals(playerInput)
                        && gameField[1][1].trim().equals(playerInput)
                        && gameField[2][0].trim().equals(playerInput)) {
                    return true;
                }
            }
        }
        return false;
    }
}