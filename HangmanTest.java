
/*
pancake
spaghetti
porkchop
broccoli
mushroom
pineapple
strawberry
cantaloupe
tangerine
raspberry
*/

/*
import java.io.*;
import java.util.*;

public class HangmanGame {

    static Scanner scanner = new Scanner(System.in);
    static String playerName;
    static int wins = 0;

    public static void main(String[] args) {
        while (true) {
            showMainMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    playGame();
                    break;
                case 2:
                    viewLeaderboard();
                    break;
                case 3:
                    addTheme();
                    break;
                case 4:
                    System.out.println("Exiting game. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Step 1: Show Main Menu
    static void showMainMenu() {
        System.out.println("\n==== Hangman Game ====");
        System.out.println("1. Play Game");
        System.out.println("2. View Leaderboard");
        System.out.println("3. Add Theme");
        System.out.println("4. Quit");
        System.out.print("Enter your choice: ");
    }

    // Step 2: Get User Choice
    static int getUserChoice() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    // Step 3: Play Game
    static void playGame() {
        System.out.print("Enter your name: ");
        playerName = scanner.nextLine();

        String theme = chooseTheme();
        String word = chooseWord(theme);
        char[] guessedWord = new char[word.length()];
        Arrays.fill(guessedWord, '_');
        int attemptsLeft = 6;
        String guessedLetters = "";

        while (attemptsLeft > 0) {
            System.out.println("\nWord: " + new String(guessedWord));
            System.out.println("Guessed letters: " + guessedLetters);
            System.out.println("Attempts remaining: " + attemptsLeft);
            System.out.print("Enter a letter: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Invalid input. Please enter a single letter.");
                continue;
            }

            char guessedChar = input.charAt(0);

            if (guessedLetters.indexOf(guessedChar) != -1) {
                System.out.println("You've already guessed that letter.");
                continue;
            }

            guessedLetters += guessedChar;
            boolean correctGuess = false;

            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guessedChar) {
                    guessedWord[i] = guessedChar;
                    correctGuess = true;
                }
            }

            if (!correctGuess) {
                attemptsLeft--;
                System.out.println("Incorrect guess.");
            }

            if (new String(guessedWord).equals(word)) {
                System.out.println("\nCongratulations! You've guessed the word: " + word);
                wins++;
                updateLeaderboard();
                break;
            }
        }

        if (attemptsLeft == 0) {
            System.out.println("\nGame over! The word was: " + word);
        }
    }

    // Step 4: Choose Theme
    static String chooseTheme() {
        List<String> themes = loadThemes();
        System.out.println("\nAvailable themes:");
        for (int i = 0; i < themes.size(); i++) {
            System.out.println((i + 1) + ". " + themes.get(i));
        }
        System.out.print("Choose a theme: ");
        int themeChoice = getUserChoice();
        return themes.get(themeChoice - 1);
    }

    // Step 5: Load Themes
    static List<String> loadThemes() {
        List<String> themes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("themes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                themes.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error loading themes: " + e.getMessage());
        }
        return themes;
    }

    // Step 6: Choose Word
    static String chooseWord(String theme) {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(theme))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error loading words for theme " + theme + ": " + e.getMessage());
        }
        return words.get((int) (Math.random() * words.size()));
    }

    // Step 7: View Leaderboard
    static void viewLeaderboard() {
        System.out.println("\n==== Leaderboard ====");
        try (BufferedReader reader = new BufferedReader(new FileReader("leaderboard.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading leaderboard: " + e.getMessage());
        }
    }

    // Step 8: Update Leaderboard
    static void updateLeaderboard() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("leaderboard.txt", true))) {
            writer.write(playerName + ": " + wins + " wins");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error updating leaderboard: " + e.getMessage());
        }
    }

    // Step 9: Add Theme
    static void addTheme() {
        System.out.print("Enter the name of the new theme: ");
        String themeName = scanner.nextLine();

        List<String> words = new ArrayList<>();
        System.out.println("Enter words for the theme (type 'stop' to finish):");
        while (true) {
            String word = scanner.nextLine().trim().toLowerCase();
            if ("stop".equals(word)) {
                break;
            }
            if (word.length() >= 7) {
                words.add(word);
            } else {
                System.out.println("Word must be at least 7 letters long.");
            }
        }

        if (!words.isEmpty()) {
            try (BufferedWriter themeWriter = new BufferedWriter(new FileWriter(themeName))) {
                for (String word : words) {
                    themeWriter.write(word);
                    themeWriter.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error saving theme: " + e.getMessage());
            }

            try (BufferedWriter themesWriter = new BufferedWriter(new FileWriter("themes.txt", true))) {
                themesWriter.write(themeName);
                themesWriter.newLine();
            } catch (IOException e) {
                System.out.println("Error updating themes list: " + e.getMessage());
            }

            System.out.println("New theme added successfully!");
        } else {
            System.out.println("No valid words entered. Theme not added.");
        }
    }
}

*/











import java.io.*;
import java.util.*;

/**
 * HangmanGame.java
 * A simple Hangman game with themes, leaderboard, and file I/O.
*/
 
public class HangmanTest {

    static Scanner scanner = new Scanner(System.in);
    static final String THEMES_FILE = "themes.txt";
    static final String LEADERBOARD_FILE = "leaderboard.txt";

    public static void main(String[] args) {
        while (true) {
            showMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    playGame();
                    break;
                case "2":
                    viewLeaderboard();
                    break;
                case "3":
                    addTheme();
                    break;
                case "4":
                    System.out.println("Thanks for playing!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Display main menu
    public static void showMenu() {
        System.out.println("\n===== HANGMAN GAME =====");
        System.out.println("1. Play Game");
        System.out.println("2. View Leaderboard");
        System.out.println("3. Add Theme");
        System.out.println("4. Quit");
        System.out.print("Select an option: ");
    }

    // Play Game Option
    public static void playGame() {
        System.out.print("Enter your player name: ");
        String playerName = scanner.nextLine();

        List<String> themes = loadThemes();
        if (themes.isEmpty()) {
            System.out.println("No themes available.");
            return;
        }

        System.out.println("Available Themes:");
        for (String theme : themes) {
            System.out.println("- " + theme);
        }

        System.out.print("Enter a theme: ");
        String selectedTheme = scanner.nextLine();

        File themeFile = new File(selectedTheme);
        if (!themeFile.exists()) {
            System.out.println("Theme not found.");
            return;
        }

        String[][] strWords = loadWordsFromTheme(themeFile);
        if (strWords == null) return;

        bubbleSortByRandom(strWords);
        int wins = 0;

        for (String[] wordPair : strWords) {
            String word = wordPair[0];
            boolean won = playSingleWord(word);
            if (won) {
                wins++;
            } else {
                break;
            }
        }

        saveToLeaderboard(playerName, wins);
        System.out.println("Game Over! Wins: " + wins);
    }

    // Load theme names from themes.txt
    public static List<String> loadThemes() {
        List<String> themes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(THEMES_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                themes.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading themes file.");
        }
        return themes;
    }

    // Load words from selected theme
    public static String[][] loadWordsFromTheme(File themeFile) {
        String[][] words = new String[10][2];
        Random rand = new Random();
        try (BufferedReader br = new BufferedReader(new FileReader(themeFile))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < 10) {
                if (line.length() >= 7) {
                    words[i][0] = line.trim();
                    words[i][1] = String.valueOf(rand.nextInt(100));
                    i++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading theme file.");
            return null;
        }
        return words;
    }

    // Bubble sort by random numbers (column 1)
    public static void bubbleSortByRandom(String[][] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                int num1 = Integer.parseInt(arr[j][1]);
                int num2 = Integer.parseInt(arr[j + 1][1]);
                if (num1 > num2) {
                    String[] temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // One round of guessing a word
    public static boolean playSingleWord(String word) {
        int wrongGuesses = 0;
        int maxGuesses = 6;
        Set<Integer> revealedIndexes = new HashSet<>();
        Random rand = new Random();

        while (wrongGuesses < maxGuesses) {
            drawHangman(wrongGuesses);
            displayWord(word, revealedIndexes);

            System.out.print("Guess the full word: ");
            String guess = scanner.nextLine();

            if (guess.equalsIgnoreCase(word)) {
                System.out.println("Correct! You won!");
                return true;
            } else {
                wrongGuesses++;
                if (wrongGuesses < maxGuesses) {
                    int indexToReveal = rand.nextInt(word.length());
                    revealedIndexes.add(indexToReveal);
                    System.out.println("Incorrect! Revealing a letter to help...");
                }
            }
        }

        drawHangman(wrongGuesses);
        System.out.println("You lost! The word was: " + word);
        return false;
    }

    // Draw hangman based on number of wrong guesses
    public static void drawHangman(int wrongGuesses) {
        String[] hangman = {
            "  O  ", // Head
            "  |  ", // Torso
            " /",    // Left arm
            "\\",    // Right arm
            " /",    // Left leg
            " \\"    // Right leg
        };

        System.out.println(" +---+");
        System.out.println(" |   |");
        System.out.println(" " + (wrongGuesses > 0 ? hangman[0] : ""));
        System.out.print(" " + (wrongGuesses > 2 ? hangman[2] : "") +
                         (wrongGuesses > 3 ? hangman[3] : "") + "\n");
        System.out.print(" " + (wrongGuesses > 1 ? hangman[1] : "") + "\n");
        System.out.print(" " + (wrongGuesses > 4 ? hangman[4] : "") +
                         (wrongGuesses > 5 ? hangman[5] : "") + "\n");
        System.out.println("=====");
    }

    // Show word with underscores and revealed letters
    public static void displayWord(String word, Set<Integer> revealedIndexes) {
        for (int i = 0; i < word.length(); i++) {
            if (revealedIndexes.contains(i)) {
                System.out.print(word.charAt(i) + " ");
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println();
    }

    // View leaderboard
    public static void viewLeaderboard() {
        List<String[]> scores = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(LEADERBOARD_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    scores.add(parts);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading leaderboard.");
            return;
        }

        scores.sort((a, b) -> Integer.parseInt(b[1]) - Integer.parseInt(a[1]));

        System.out.println("\n--- Leaderboard ---");
        for (String[] entry : scores) {
            System.out.println(entry[0] + " - " + entry[1]);
        }
    }

    // Save player's score
    public static void saveToLeaderboard(String name, int score) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(LEADERBOARD_FILE, true))) {
            pw.println(name + "," + score);
        } catch (IOException e) {
            System.out.println("Error writing to leaderboard.");
        }
    }

    // Add new theme
    public static void addTheme() {
        System.out.print("Enter new theme file name (e.g. Animals.txt): ");
        String newTheme = scanner.nextLine();

        List<String> newWords = new ArrayList<>();
        while (true) {
            System.out.print("Enter word (or type 'stop'): ");
            String word = scanner.nextLine();
            if (word.equalsIgnoreCase("stop")) break;

            if (word.length() >= 7) {
                newWords.add(word);
            } else {
                System.out.println("Word must be at least 7 letters.");
            }
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(newTheme))) {
            for (String word : newWords) {
                pw.println(word);
            }
        } catch (IOException e) {
            System.out.println("Error creating new theme.");
            return;
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(THEMES_FILE, true))) {
            pw.println(newTheme);
        } catch (IOException e) {
            System.out.println("Error updating themes list.");
        }

        System.out.println("Theme added successfully.");
    }
}


