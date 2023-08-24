import java.util.Scanner;

public class Duke {
    private static final String BYE_FLAG = "bye";
    private static final String LIST_FLAG = "list";
    private static final String MARK_FLAG = "mark";
    private static final String UNMARK_FLAG = "unmark";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMsg = "Hello from\n" + logo;
        printOutput(welcomeMsg);

        // greet the users
        String greetings = "Hello! I'm Orion\n"
                + "What can I do for you?\n";
        printOutput(greetings);

        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();

            // skip past empty lines
            if (input.isEmpty()) {
                continue;
            }

            String[] parts = input.split("\\s+", 2);
            String command = parts[0];
            String value = parts.length >= 2 ? parts[1].trim() : "";

            try {
                if (command.equals(BYE_FLAG)) {
                    if (!value.isEmpty()) {
                        throw new DukeException("Oops!!! The bye command should not be followed by any description");
                    }

                    printOutput("Bye. Hope to see you again soon!\n");
                    break;
                }

                if (command.equals(LIST_FLAG)) {
                    if (!value.isEmpty()) {
                        throw new DukeException("Oops!!! The list command should not be followed by any description");
                    }

                    String output = taskManager.listTasks();
                    printOutput(output);
                    continue;
                }

                if (command.equals(MARK_FLAG)) {
                    Scanner tempSc = new Scanner(value);

                    if (!tempSc.hasNextInt()) {
                        tempSc.close();
                        throw new DukeException("Oops!!! Invalid argument of a mark command");
                    }

                    int index = tempSc.nextInt();
                    if (tempSc.hasNext()) {
                        // means invalid formatting for done command
                        tempSc.close();
                        throw new DukeException("Oops!!! Invalid argument of a mark command");
                    }

                    String output = taskManager.markTask(index, true);
                    printOutput(output);
                    tempSc.close();
                    continue;
                }

                if (command.equals(UNMARK_FLAG)) {
                    Scanner tempSc = new Scanner(value);

                    if (!tempSc.hasNextInt()) {
                        tempSc.close();
                        throw new DukeException("Oops!!! Invalid argument of an unmark command");
                    }

                    int index = tempSc.nextInt();
                    if (tempSc.hasNext()) {
                        // means invalid formatting for done command
                        tempSc.close();
                        throw new DukeException("Oops!!! Invalid argument of an unmark command");
                    }

                    String output = taskManager.markTask(index, false);
                    printOutput(output);
                    tempSc.close();
                    continue;
                }

                String output = taskManager.addTask(new Task(input));
                printOutput(output);

            } catch (DukeException exc) {
                printOutput(exc.getMessage() + "\n");
            }
        }
        sc.close();
    }

    private static void drawLine() {
        int lineLength = 60; // Adjust the length of the line as needed
        char horizontalLineChar = '\u2500'; // Unicode character for a horizontal line

        for (int i = 0; i < lineLength; i++) {
            System.out.print(horizontalLineChar);
        }
        System.out.println();
    }

    private static void printOutput(String output) {
        System.out.println(output);
        drawLine();
    }
}
