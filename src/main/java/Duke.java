import java.util.Scanner;

public class Duke {
    private static String greetingString =  "Hello! I'm ChampionSOS\nWhat can I do for you?";
    private static String exitString =  "Bye. Hope to see you again soon!";
    private static DukeList listOfTexts = new DukeList();
    public static void main(String[] args) {

        /**
         * Initial greeting by bot.
         */
        System.out.println(Duke.greetingString);
        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();

        /**
         * Main loop to get user input. Stops when user inputs 'bye'.
         */
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                Duke.listOfTexts.printList();
            } else if (userInput.startsWith("mark")) {
                Duke.listOfTexts.toggleDone(Integer.parseInt(userInput.substring(5)), "mark");
            } else if (userInput.startsWith("unmark")) {
                Duke.listOfTexts.toggleDone(Integer.parseInt(userInput.substring(7)), "unmark");
            } else {
                listOfTexts.addItem(userInput);
            }
            userInput = myObj.nextLine();
        }

        /**
         * Exit message by bot.
         */
        System.out.println(Duke.exitString);
    }
}
