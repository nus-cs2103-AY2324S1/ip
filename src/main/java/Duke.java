import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    static String softbreak = "--------------------------------------------------";
    static String hardbreak = "——————————————————————————————————————————————————";


    /**
     * Greets the user with a predefined message.
     */
    private static void greet() {
        System.out.println(hardbreak +
                "\n...Fingerprint match found. Verification complete. Welcome home.\n" +
                "PRTS, at your service. What would you like to do today?\n" + hardbreak);
    }

    /**
     * Bids the user farewell with a predefined message.
     */
    private static void exit() {
        System.out.println(hardbreak + "\nFarewell. See you again soon.\n" + hardbreak);
    }


    /**
     * Displays a list of the user's past tasks.
     * @param tasks An array containing the user's tasks.
     */
    private static void list(ArrayList<Task> tasks) {

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

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<>();

        greet();

        while (true) {

            String input = scanner.nextLine();

            if (Objects.equals(input, "bye")) {
                break;
            }

            System.out.println(softbreak);

            if (input.startsWith("todo ")) {

                System.out.println("Understood. No rest for the weary, eh?");
                tasks.add(new Todo(input.substring(5)));
                System.out.println(tasks.get(tasks.size() - 1));

            } else if (input.startsWith("deadline ")) {

                String[] parts = input.split("/");
                if (parts.length != 2) {
                    System.out.println("Please enter your request properly.\n" + hardbreak);
                    continue;
                }
                System.out.println("Alright. I'll make sure you don't forget it.");
                tasks.add(new Deadline(parts[0].substring(9), parts[1].substring(3)));
                System.out.println(tasks.get(tasks.size() - 1));

            } else if (input.startsWith("event ")) {

                String[] parts = input.split("/");
                if (parts.length != 3) {
                    System.out.println("Please enter your request properly.\n" + hardbreak);
                    continue;
                }
                System.out.println("Roger that. Preparations will be underway.");
                tasks.add(new Event(parts[0].substring(6), parts[1].substring(5),
                        parts[2].substring(3)));
                System.out.println(tasks.get(tasks.size() - 1));

            } else if (input.startsWith("mark ")) {

                int index = Integer.parseInt(input.substring(5)) - 1;
                if (tasks.size() <= index || index < 0) {
                    System.out.println("That isn't a valid input. Please try again.\n" + hardbreak);
                    continue;
                }
                Task task = tasks.get(index);
                task.markDone();
                System.out.println("Mission accomplished.");
                System.out.println(task);

            } else if (input.startsWith("unmark ")) {

                int index = Integer.parseInt(input.substring(7)) - 1;
                if (tasks.size() <= index || index < 0) {
                    System.out.println("That isn't a valid input. Please try again.\n" + hardbreak);
                    continue;
                }
                Task task = tasks.get(index);
                task.markUndone();
                System.out.println("Uncharacteristic of you. More work has been added to the pile.");
                System.out.println(task);

            } else if (input.equals("list")) {

                list(tasks);

            } else if (input.equals("thanks")) {

                System.out.println("...No problem.");

            } else if (input.equals("zzz")) {

                if (!tasks.isEmpty()) {
                    System.out.println("There's still lots of work that needs to be done." +
                            "We can't afford to have you resting.");
                } else {
                    System.out.println("...have a good rest.");
                }

            } else {

                System.out.println("Sorry, I didn't quite catch that. Are you having enough sleep?");

            }

            System.out.println(hardbreak);

        }

        exit();

    }
}
