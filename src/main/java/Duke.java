import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String NAME = "Moira";
    private static String SPACER = "--------------------------------------------------------------------------";
    private static boolean IS_RECEIVING_INPUT = false;
    private static ArrayList<String> ALL_USER_INPUTS;

    public static void main(String[] args) {
        ALL_USER_INPUTS = new ArrayList<>();
        greet();
        while (IS_RECEIVING_INPUT) {
            getUserInput();
        }
        exit();
    }

    private static void greet() {
        System.out.println(SPACER);
        System.out.println("Howdy, I'm + " + NAME + ", your friendly personal assistant!");
        System.out.println("What can I do for you today?");
        System.out.println(SPACER);
        IS_RECEIVING_INPUT = true;
    }

    private static void exit() {
        IS_RECEIVING_INPUT = false;
        System.out.println(SPACER);
        System.out.println("See ya later, alligator! I'm waiting here if you need anything :>");
        System.out.println(SPACER);
    }

    private static void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        if (userInput.equals("bye")) {
            IS_RECEIVING_INPUT = false;
        } else if (userInput.equals("list")) {
            System.out.println(SPACER);
            printAllUserInputs();
            System.out.println(SPACER);
        } else {
            addInputToList(userInput);
            System.out.println(SPACER);
            System.out.println("Added: " + userInput);
            System.out.println(SPACER);
        }
    }

    private static void addInputToList(String newInput) {
        ALL_USER_INPUTS.add(newInput);
    }

    private static void printAllUserInputs() {
        int index = 1;
        for (String input : ALL_USER_INPUTS) {
            System.out.println(index + ". " + input);
            index++;
        }
    }
}
