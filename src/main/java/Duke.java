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

                try {
                    Duke.listOfTexts.toggleDone(Integer.parseInt(userInput.substring(5)), "mark");
                } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("☹ OOPS!!! Please indicate a task to mark.");
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! Please indicate an appropriate index.");
                }

            } else if (userInput.startsWith("unmark")) {

                try {
                    Duke.listOfTexts.toggleDone(Integer.parseInt(userInput.substring(7)), "unmark");
                } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("☹ OOPS!!! Please indicate a task to unmark.");
                } catch (NullPointerException e) {
                    System.out.println("☹ OOPS!!! Please indicate an appropriate index.");
                }

            } else if (userInput.startsWith("todo")) {

                try {
                    listOfTexts.addItem(new ToDos(userInput.substring(5)));
                } catch (Exception e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }

            } else if (userInput.startsWith("deadline")) {

                try {
                    // Split input into an array containing the task description, and 'by' date
                    String[] inputs = userInput.split("/");
                    listOfTexts.addItem(new Deadlines(inputs[0].substring(9), inputs[1]));
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("☹ OOPS!!! There are missing details for the deadline.");
                }

            } else if (userInput.startsWith("event")) {

                try {
                    // Split input into an array containing the task description, 'from' and 'to' date
                    String[] inputs = userInput.split("/");
                    listOfTexts.addItem(new Events(inputs[0].substring(6), inputs[1], inputs[2]));
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("☹ OOPS!!! There are missing details for the event.");
                }

            } else if (userInput.startsWith("delete")) {

                try {
                    listOfTexts.removeItem(Integer.parseInt(userInput.substring(7)));
                } catch (Exception e) {
                    System.out.println(e);
                }

            } else {

                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch(DukeException e) {
                    System.out.println(e.getMessage());
                }

            }
            userInput = myObj.nextLine();
        }

        /**
         * Exit message by bot.
         */
        System.out.println(Duke.exitString);
    }
}
