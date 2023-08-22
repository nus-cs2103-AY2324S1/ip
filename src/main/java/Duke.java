import java.util.Scanner;

public class Duke {
    private final String DIVIDER = "\t____________________________________________________________";
    private final String LOGO = 
                            "\t░█████╗░██╗░░██╗░█████╗░████████╗████████╗██╗░░░██╗\n" +
                            "\t██╔══██╗██║░░██║██╔══██╗╚══██╔══╝╚══██╔══╝╚██╗░██╔╝\n" +
                            "\t██║░░╚═╝███████║███████║░░░██║░░░░░░██║░░░░╚████╔╝░\n" +
                            "\t██║░░██╗██╔══██║██╔══██║░░░██║░░░░░░██║░░░░░╚██╔╝░░\n" +
                            "\t╚█████╔╝██║░░██║██║░░██║░░░██║░░░░░░██║░░░░░░██║░░░\n" +
                            "\t░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝░░░╚═╝░░░░░░╚═╝░░░░░░╚═╝░░░\n";
    private final String GREETING = "\tHello! I'm Chatty.\n\tWhat can I do for you?";
    private final String FAREWELL = "\tBye. Have \"fun\" in school!";
    private String[] textList = new String[100];
    private int textListSize = 0;

    public static void main(String[] args) {
        // get new duke instance
        Duke duke = new Duke();

        // introduction
        System.out.println("\n\tWelcome to Chatty.\n" + duke.LOGO);
        duke.sendGreeting();

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println();
            String input = sc.nextLine().trim();

            while (!input.equals("bye")) {
                duke.handleTextInput(input);
                System.out.println();
                input = sc.nextLine().trim();
            }
        }
        // say bye
        duke.sendFarewell();
    }

    private void addToTextList(String text) {
        if (textListSize < textList.length) {
            textList[textListSize] = text;
            textListSize++;
        } else {
            System.out.println("Error: List is full.");
        }
    }

    private void sendGreeting() {
        System.out.println(DIVIDER);
        System.out.println(GREETING);
        System.out.println(DIVIDER);
    }

    private void handleTextInput(String inputString) {
        System.out.println(DIVIDER);
        if (inputString.equals("list")) {
            for (int i = 0; i < textListSize; i++) {
                System.out.println("\t" + (i + 1) + ". " + textList[i]);
            }
        } else {
            System.out.println("\tadded: " + inputString);
            addToTextList(inputString);
        }
        System.out.println(DIVIDER);
    }

    private void sendFarewell() {
        System.out.println(DIVIDER);
        System.out.println(FAREWELL);
        System.out.println(DIVIDER);
    }
}
