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
     * Displays a list of the user's past inputs.
     * @param inputs An array containing the user's inputs.
     */
    private static void list(ArrayList<Task> inputs) {

        System.out.println("Here are your tasks for today.");

        if (inputs.isEmpty()) {
            System.out.println("...\n" + "You haven't said anything yet...");
        }

        for (int i = 0; i < inputs.size(); i++) {
            System.out.println((i + 1) + ". " + inputs.get(i));
        }

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> inputs = new ArrayList<>();

        greet();

        while (true) {

            String input = scanner.nextLine();

            if (Objects.equals(input, "bye")) {
                break;
            }

            System.out.println(softbreak);

            if (input.startsWith("mark ")) {
                Task task = inputs.get(Integer.parseInt(input.substring(5)) - 1);
                task.markDone();
                System.out.println("Mission accomplished.");
                System.out.println(task);
            } else if (input.startsWith("unmark ")) {
                Task task = inputs.get(Integer.parseInt(input.substring(7)) - 1);
                task.markUndone();
                System.out.println("Uncharacteristic of you. More work has been added to the pile.");
                System.out.println(task);
            } else if (input.equals("list")) {
                list(inputs);
            } else {
                System.out.println("added: " + input);
                inputs.add(new Task(input));
            }

            System.out.println(hardbreak);

        }

        exit();

    }
}
