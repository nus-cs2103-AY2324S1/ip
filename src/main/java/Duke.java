import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {

    private static String greetingString =  "Hello! I'm ChampionSOS\nWhat can I do for you?";
    private static String exitString =  "Bye. Hope to see you again soon!";
    private static DukeList listOfTexts = new DukeList();

    private static void parseStorage(Scanner data) {
        try {
            String[] input;
            while (data.hasNextLine()) {
                input = data.nextLine().split("\\| ");
                if (input[0].equals("T ")) {
                    Duke.listOfTexts.addItemFromStorage(new ToDos(input[2]), input[1]);
                } else if (input[0].equals("D ")) {
                    Duke.listOfTexts.addItemFromStorage(new Deadlines(input[2], input[3]), input[1]);
                } else if (input[0].equals("E ")) {
                    Duke.listOfTexts.addItemFromStorage(new Events(input[2], input[3], input[4]), input[1]);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static void startBot() {
        try {
            System.out.println(Duke.greetingString);
            Files.createDirectories(Paths.get("./data"));
            File newFile = new File("./data/duke.txt");
            boolean createdNewFile = newFile.createNewFile();
            if (!createdNewFile) {
                Scanner data = new Scanner(newFile);
                Duke.parseStorage(data);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    private static void run(String[] args) {
        /**
         * Initial greeting by bot,
         * initial set up of scanner object
         * and data folder to save tasks.
         */
        Duke.startBot();
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
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! Please indicate an appropriate index.");
                }

            } else if (userInput.startsWith("todo")) {

                try {
                    Duke.listOfTexts.addItem(new ToDos(userInput.substring(5)));
                } catch (Exception e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }

            } else if (userInput.startsWith("deadline")) {

                try {
                    // Split input into an array containing the task description, and 'by' date
                    String[] inputs = userInput.split("/");
                    Duke.listOfTexts.addItem(new Deadlines(inputs[0].substring(9), inputs[1]));
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("☹ OOPS!!! There are missing details for the deadline.");
                }

            } else if (userInput.startsWith("event")) {

                try {
                    // Split input into an array containing the task description, 'from' and 'to' date
                    String[] inputs = userInput.split("/");
                    Duke.listOfTexts.addItem(new Events(inputs[0].substring(6), inputs[1], inputs[2]));
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("☹ OOPS!!! There are missing details for the event.");
                }

            } else if (userInput.startsWith("delete")) {

                try {
                    Duke.listOfTexts.removeItem(Integer.parseInt(userInput.substring(7)));
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("☹ OOPS!!! Please indicate a task to delete.");
                } catch (NullPointerException e) {
                    System.out.println("☹ OOPS!!! Please indicate an appropriate index.");
                }

            } else {

                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch(DukeException e) {
                    System.out.println(e.getMessage());
                }

            }
            Duke.listOfTexts.saveList();
            userInput = myObj.nextLine();
        }

        /**
         * Exit message by bot.
         */
        myObj.close();
        System.out.println(Duke.exitString);
    }
    public static void main(String[] args) {
        Duke.run(args);
    }
}
