import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String INDENTATION = "    ";
    private static ArrayList<Task> store = new ArrayList<Task>();

    private static String formatOutput(String output) {
        String horizontalLine = "____________________________________________________________";
        return INDENTATION + horizontalLine + "\n " +
                INDENTATION + output + '\n' + INDENTATION + horizontalLine + '\n';
    }

    private static String formatList(ArrayList storeList) {
        String str = "";
        int len = storeList.size();
        for (int i = 0; i < len; i++) {
            str = str + Integer.toString(i + 1) + ". " + storeList.get(i);
            if (i != (len - 1)) {
                str += "\n " + INDENTATION;
            }
        }
        return str;
    }

    private static void handleCommand() {
        Scanner sc = new Scanner(System.in);
        String command, commandString;
        String[] commandArray;
        int commandIndex = 0;

        while (true) {
            commandString = sc.nextLine();
            commandArray = commandString.split(" ");
            command = commandArray[0];

            if (commandString.equals("bye")) {
                System.out.println(formatOutput("Bye. Hope to see you again soon!"));
                break;
            } else if (commandString.equals("list")) {
                System.out.println(formatOutput(formatList(store)));
            } else if (command.equals("mark")) {
                commandIndex = Integer.parseInt(commandArray[1]) - 1;
                if (commandIndex > store.size()) {
                    System.out.println(formatOutput("Invalid Index!"));
                } else {
                    Task selectedTask = store.get(commandIndex);
                    selectedTask.markTask();
                    System.out.println(formatOutput("Nice! I've marked the task as done:\n" +
                            INDENTATION + selectedTask));
                }

            } else if (command.equals("unmark")) {
                if (commandIndex > store.size()) {
                    System.out.println(formatOutput("Invalid Index!"));
                } else {
                    commandIndex = Integer.parseInt(commandArray[1]) - 1;
                    Task selectedTask = store.get(commandIndex);
                    selectedTask.unmarkTask();
                    System.out.println(formatOutput("OK, I've marked this task as not done yet:\n" +
                            INDENTATION + selectedTask));
                }
            }
            else {
                store.add(new Task(commandString));
                System.out.println(formatOutput("added: " + commandString));
            }
        }

    }

    public static void main(String[] args) {

        System.out.println(formatOutput("Hello! I'm Nano\n" + INDENTATION + " What can I do for you?"));
        handleCommand();

    }
}
