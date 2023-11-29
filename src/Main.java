import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] playedRound = new int[9];
        int position;
        boolean winner = false;
        String playerSymbol = " ";

        String[][] gameField = new String[3][3];
        fillTheGameFieldWithPositions(gameField);
        printGameField(gameField);

        for (int i = 0; i < playedRound.length; i++) {
            do {
                while (true) {
                    System.out.print("\n\tPick the position between 1-9, ");
                    try {
                        if (i % 2 == 0) {
                            System.out.print("\tPlayer X play: ");
                            position = scanner.nextInt();
                            playerSymbol = " X ";

                        } else {
                            System.out.print("\tPlayer O play: ");
                            position = scanner.nextInt();
                            playerSymbol = " O ";
                        }
                        break;
                    } catch (Exception e) {
                        System.out.print("\t-> Enter valid number!");
                        scanner.nextLine();
                    }
                }
            } while (checkTakenPosition(playedRound, position));

            playedRound[i] = position;
            addPlayerInput(gameField, position, playerSymbol);
            printGameField(gameField);

            if (checkWin(gameField, playerSymbol)) {
                winner = true;
                break;
            }
        }

        System.out.println("\n\t|-------------------|");
        if (!winner) {
            System.out.println("\t\tIt's a draw!");
        } else {
            System.out.println("\t\tPlayer " + playerSymbol.trim() + " WIN!");
        }
        System.out.println("\t|-------------------|");
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

    public static boolean checkTakenPosition(int[] playedRound, int newPosition) {
        for (int position : playedRound) {
            if (newPosition < 1 || newPosition > playedRound.length) {
                System.out.print("\t-> Invalid move!");
                return true;
            }

            if (position == newPosition) {
                System.out.print("\t-> Position taken!");
                return true;
            }
        }
        return false;
    }

    public static void printGameField(String[][] gameField) {
        System.out.println();

        for (String[] row : gameField) {
            System.out.print("\t\t|");
            for (String column : row) {
                System.out.print(column);
                System.out.print("|");
            }
            System.out.println();
            System.out.println("\t\t-------------");
        }
    }

    public static void addPlayerInput(String[][] gameField, int playerPick, String playerSymbol) {
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

        // check diagonal from left to right
        if (gameField[0][0].equals(playerInput)
                && gameField[1][1].equals(playerInput)
                && gameField[2][2].equals(playerInput)) {
            return true;
        }

        // check diagonal from right to left
        if (gameField[0][2].equals(playerInput)
                && gameField[1][1].equals(playerInput)
                && gameField[2][0].equals(playerInput)) {
            return true;
        }

        for (int i = 0; i < gameField.length; i++) {
                // check rows
                if (gameField[i][0].equals(playerInput)
                        && gameField[i][1].equals(playerInput)
                        && gameField[i][2].equals(playerInput)) {
                    return true;
                }

                // check columns
                if (gameField[0][i].equals(playerInput)
                        && gameField[1][i].equals(playerInput)
                        && gameField[2][i].equals(playerInput)) {
                    return true;
                }
        }
        return false;
    }
}