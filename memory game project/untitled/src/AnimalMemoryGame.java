import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AnimalMemoryGame {
    private List<String> animalNames;
    private List<String> cards;
    private int gridSize;
    private int pairsFound;
    private int cardFlips;

    public AnimalMemoryGame(int gridSize) {
        this.gridSize = gridSize;
        this.animalNames = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.pairsFound = 0;
        this.cardFlips = 0;

        initializeAnimalNames();
        initializeCards();
    }

    private void initializeAnimalNames() {
        animalNames.add("Cat");
        animalNames.add("Dog");
        animalNames.add("Elephant");
        animalNames.add("Giraffe");
        animalNames.add("Horse");
        animalNames.add("Kangaroo");
        animalNames.add("Lion");
        animalNames.add("Monkey");
        animalNames.add("Penguin");
        animalNames.add("Tiger");
        animalNames.add("Zebra");
    }

    private void initializeCards() {
        int totalCards = gridSize * gridSize;
        int numPairs = totalCards / 2;

        for (int i = 0; i < numPairs; i++) {
            String animal = animalNames.get(i);
            cards.add(animal);
            cards.add(animal);
        }
        Collections.shuffle(cards);
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        String[] board = new String[gridSize * gridSize];

        while (pairsFound < gridSize * gridSize / 2) {
            displayBoard(board);

            System.out.print("Enter the position of the card (1-" + (gridSize * gridSize) + ") or enter 'X' to quit: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("X")) {
                System.out.println("Game ended. Thanks for playing!");
                return;
            }

            try {
                int position = Integer.parseInt(input);

                if (position < 1 || position > gridSize * gridSize) {
                    System.out.println("Invalid position. Try again.");
                    continue;
                }

                position--; // Adjusting position to match array index

                if (board[position] != null) {
                    System.out.println("Card already revealed. Try again.");
                    continue;
                }

                String card = cards.get(position);
                board[position] = card;
                cardFlips++;

                displayBoard(board);

                if (checkMatch(board)) {
                    System.out.println("You found a pair!");
                    pairsFound++;
                } else {
                    System.out.println("Not a pair. Try again.");
                    board[position] = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");
            }
        }

        System.out.println("Congratulations! You found all the animal pairs!");
        System.out.println("Total card flips: " + cardFlips);
    }

    private void displayBoard(String[] board) {
        System.out.println("Current Board:");
        for (int i = 0; i < board.length; i++) {
            if (board[i] != null) {
                System.out.print(board[i] + " ");
            } else {
                System.out.print("- ");
            }
            if ((i + 1) % gridSize == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    private boolean checkMatch(String[] board) {
        String card1 = null;
        for (String card : board) {
            if (card != null) {
                if (card1 == null) {
                    card1 = card;
                } else {
                    if (!card.equals(card1)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the grid size: ");
        int gridSize = scanner.nextInt();

        scanner.nextLine();

        AnimalMemoryGame game = new AnimalMemoryGame(gridSize);
        game.playGame();
    }
}
