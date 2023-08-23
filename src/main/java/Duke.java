import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    static String softbreak = "--------------------------------------------------";
    static String hardbreak = "——————————————————————————————————————————————————";

    ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Greets the user with a predefined message.
     */
    private void greet() {
        System.out.println(hardbreak +
                "\n...Fingerprint match found. Verification complete. Welcome home.\n" +
                "PRTS, at your service. What would you like to do today?\n" + hardbreak);
    }

    /**
     * Bids the user farewell with a predefined message.
     */
    private void exit() {
        System.out.println(hardbreak + "\nFarewell. See you again soon.\n" + hardbreak);
    }


    /**
     * Displays a list of the user's past tasks.
     * @param tasks An array containing the user's tasks.
     */
    private void list(ArrayList<Task> tasks) {

        System.out.println("Here are your tasks for today.");

        if (tasks.isEmpty()) {
            System.out.println("...\n" + "You don't have any tasks. Enjoy your day off.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }

        System.out.println("You now have " + tasks.size() + " tasks in your list.");

    }

    /**
     * Takes the user's input and parses it to execute actions.
     * @param input The user's input read in from the Scanner.
     */
    private void parseInput(String input) throws DukeException {

        String[] splits = input.strip().split(" ", 2);
        String command = splits[0].toLowerCase();
        String detail = splits.length == 2 ? splits[1].strip() : "";

        switch (command) {

            case "todo":

                if (detail.isBlank())
                    throw new DukeException("I can't read your mind. Do add a description.");
                System.out.println("Understood. No rest for the weary, eh?");
                tasks.add(new Todo(detail));
                System.out.println(tasks.get(tasks.size() - 1));
                break;

            case "deadline":

                if (detail.isBlank())
                    throw new DukeException("I can't read your mind. Do add a description.");
                try {
                    String[] deadlineSplits = detail.split("/by", 2);
                    String deadlineDescription = deadlineSplits[0].strip();
                    String deadline = deadlineSplits[1].strip();
                    if (deadline.isBlank() || deadlineDescription.isBlank())
                        throw new DukeException("I can't read your mind. Do add more details.");
                    System.out.println("Alright. I'll make sure you don't forget it.");
                    tasks.add(new Deadline(deadlineDescription, deadline));
                    System.out.println(tasks.get(tasks.size() - 1));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Please format your description properly.");
                }
                break;

            case "event":

                if (detail.isBlank())
                    throw new DukeException("I can't read your mind. Do add a description.");
                try {
                    String[] eventSplit1 = detail.split("/from", 2);
                    String[] eventSplit2 = eventSplit1[1].split("/to", 2);
                    String eventDescription = eventSplit1[0].strip();
                    String startDate = eventSplit2[0].strip();
                    String endDate = eventSplit2[1].strip();
                    if (eventDescription.isBlank() || startDate.isBlank() || endDate.isBlank())
                        throw new DukeException("I can't read your mind. Do add more details.");
                    System.out.println("Roger that. Preparations will be underway.");
                    tasks.add(new Event(eventDescription, startDate, endDate));
                    System.out.println(tasks.get(tasks.size() - 1));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Please format your description properly.");
                }
                break;

            case "mark":

                if (detail.isBlank())
                    throw new DukeException("I can't read your mind. Provide a suitable index.");
                try {
                    int index = Integer.parseInt(detail) - 1;
                    Task task = tasks.get(index);
                    task.markDone();
                    System.out.println("Mission accomplished.");
                    System.out.println(task);
                } catch (NumberFormatException e) {
                    throw new DukeException("I can't understand that number.");
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Provide a number within range, please.");
                }
                break;

            case "unmark":

                if (detail.isBlank())
                    throw new DukeException("I can't read your mind. Provide a suitable index.");
                try {
                    int index = Integer.parseInt(detail) - 1;
                    Task task = tasks.get(index);
                    task.markUndone();
                    System.out.println("Uncharacteristic of you. More work has been added to the pile.");
                    System.out.println(task);
                } catch (NumberFormatException e) {
                    throw new DukeException("I can't understand that number.");
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Provide a number within range, please.");
                }
                break;

            case "list":

                list(tasks);
                break;

            case "thanks":

                System.out.println("...No problem.");
                break;

            case "zzz":

                if (!tasks.isEmpty()) {
                    System.out.println("There's still lots of work that needs to be done. " +
                            "We can't afford to have you resting.");
                } else {
                    System.out.println("...have a good rest.");
                }
                break;

            case "hi":

                System.out.println("Greetings.");
                break;

            default:

                System.out.println("Sorry, I didn't quite catch that. Are you having enough sleep?");

        }

    }

    public static void main(String[] args) {

        Duke prts = new Duke();
        Scanner scanner = new Scanner(System.in);

        prts.greet();

        while (true) {

            String input = scanner.nextLine();

            if (Objects.equals(input, "bye")) {
                break;
            }

            System.out.println(softbreak);

            try {
                prts.parseInput(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(hardbreak);
            }

        }

        prts.exit();

    }
}
