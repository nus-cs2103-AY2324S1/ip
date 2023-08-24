import java.util.Scanner;

public class Duke {

    // substring: begIndex (inclusive) up to the endIndex (exclusive)
    public static void main(String[] args) {
        int itemsAdded = 0;
        Task[] taskList = new Task[100]; // assume there will be <= 100 tasks
        System.out.println("Hello friend :> My name is John, nice to meet you! " +
                "What do you have to do today?");

        Scanner sc = new Scanner(System.in); // do not put this in the while loop

        while (true) {
            System.out.print("Reply John: ");
            String command = sc.nextLine(); // using .next() is wrong - only reads first word

            if (command.equals("bye")) {
                System.out.println("Bye for now, hope to see you soon.");
                break;
            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");

                for (int i = 1; i <= itemsAdded; i++) {
                    Task task = taskList[i - 1];
                    System.out.println(i + ". " + taskList[i - 1].toString());
                    // adding toString() to use the overridden one in Task, etc.
                }
            } else if (command.startsWith("mark ")) { // space behind is needed!
                int taskPos = Integer.parseInt(command.substring(5)) - 1; // convert substring to int

                // only numbers >= 0 and < total number are valid
                if (taskPos >= 0 && taskPos < itemsAdded) {
                    taskList[taskPos].markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + "[X] " + taskList[taskPos].description);
                } else {
                    System.out.println("Invalid.");
                }

            } else if (command.startsWith("unmark ")) {
                int taskPos = Integer.parseInt(command.substring(7)) - 1; // convert substring to int

                // only numbers >= 0 and < total number are valid
                if (taskPos >= 0 && taskPos < itemsAdded) {
                    taskList[taskPos].unmark();
                    System.out.println("OK, I've marked this task as not done yet:\n" + "[ ] " + taskList[taskPos].description);
                } else {
                    System.out.println("Invalid.");
                }

            } else if (command.startsWith("todo ")) { // description starting index = 5
                String description = command.substring(5);

                taskList[itemsAdded] = new ToDo(description); // add new command

                System.out.println("Got it. I've added this task:\n" + "  " + taskList[itemsAdded]
                        + "\nNow you have " + (itemsAdded + 1) + " tasks in the list.");

                itemsAdded++; // increment number of items

            } else if (command.startsWith("deadline ")) { // description starting index = 9
                // indexOf: searches for the substring and returns the index of the first character
                String description = command.substring(9, command.indexOf(" /by "));
                String by = command.substring(command.indexOf(" /by ") + 5); // from " " to the specified date is 5

                taskList[itemsAdded] = new Deadline(description, by); // add new command

                System.out.println("Got it. I've added this task:\n" + "  " + taskList[itemsAdded]
                        + "\nNow you have " + (itemsAdded + 1) + " tasks in the list.");

                itemsAdded++; // increment number of items

            } else if (command.startsWith("event ")) { // description starting index = 6
                String description = command.substring(6, command.indexOf(" /from "));
                // from " " to 'from' date is 7
                String from = command.substring(command.indexOf(" /from ") + 7, command.indexOf(" /to "));
                // from " " to 'to' date is 5
                String to = command.substring(command.indexOf(" /to ") + 5);

                taskList[itemsAdded] = new Event(description, from, to); // add new command

                System.out.println("Got it. I've added this task:\n" + "  " + taskList[itemsAdded]
                        + "\nNow you have " + (itemsAdded + 1) + " tasks in the list.");

                itemsAdded++; // increment number of items

            } else {
                taskList[itemsAdded] = new Task(command); // add new command
                System.out.println("added: " + command); // show user that it is added
                itemsAdded++; // increment number of items
            }
        }

        sc.close();

    }
}


