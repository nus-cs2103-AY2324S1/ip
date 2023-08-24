import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String NAME = "Sisyphus";
    private static final String HORIZONTAL_LINE = "_________________________________";
    private static final String LOGO = "\n" +
            "      ,-'\"\"\"`-.\n" +
            "    ,'         `.\n" +
            "   /        `    \\\n" +
            "  (    /          )\n" +
            "  |             \" |\n" +
            "  (               )\n" +
            " `.\\\\          \\ /\n" +
            "   `:.     , \\ ,\\ _\n" +
            " WE   `:-.___,-`-.{\\)\n" +
            " MUST  `.        |/ \\\n" +
            " GO      `.        \\ \\\n" +
            " ON        `-.     _\\,)\n" +
            "              `.  |,-||\n" +
            "                `.|| ||\n";

    public static void main(String[] args) {
        greet();
        handleCommand();
    }

    /**
     * Greets user with name.
     */
    public static void greet() {
        System.out.println(LOGO);
        System.out.println("Hello, I'm " + NAME + ".");
        System.out.println("What can I do for you? Do you want to roll a boulder?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Main logic flow to take in the following commands
     * 1. "bye" to exit bot
     * 2. "list" to list tasks in arrayList
     * 3. "mark" to mark task in arrayList
     * 4. "unmark" to unmark task in arrayList
     * 5. "todo" to create a todo in arrayList
     * 6. "deadline" to create a deadline in arrayList
     * 7. "event" to create an event in arrayList
     * 8. default behaviour is to add task into arrayList
     */
    public static void handleCommand() {
        boolean isChatting = true;
        Scanner scanner= new Scanner(System.in);
        String input, command, params = "";
        String[] inputArray;
        ArrayList<Task> taskList = new ArrayList<>();

        while (isChatting) {
            input = scanner.nextLine();
            inputArray = input.split(" ", 2);
            command = inputArray[0];
            if (inputArray.length > 1) {
                params = inputArray[1];
            }

            switch(command) {
                case ("bye"): {
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("Goodbye, it was nice chatting with you.");
                    System.out.println(HORIZONTAL_LINE);
                    isChatting = false;
                    break;
                }
                case ("list"): {
                    System.out.println(HORIZONTAL_LINE);
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(i + 1 + ". " + taskList.get(i));
                    }
                    System.out.println(HORIZONTAL_LINE);
                    break;
                }
                case ("mark"): {
                    if (params != "") {
                        int index = Integer.parseInt(params.split(" ", 2)[0]) - 1;
                        taskList.get(index).setDone(true);
                        System.out.println(HORIZONTAL_LINE);
                        System.out.println("The following item has been marked as done.");
                        System.out.println(taskList.get(index));
                    }
                    break;
                }
                case ("unmark"): {
                    if (inputArray.length > 1) {
                        String param = inputArray[1];
                        int index = Integer.parseInt(param) - 1;
                        taskList.get(index).setDone(false);

                        System.out.println(HORIZONTAL_LINE);
                        System.out.println("The following item has been unmarked and is now uncompleted.");
                        System.out.println(taskList.get(index));
                    }
                    break;
                }
                case ("todo"): {
                    ToDo todoTask = new ToDo(params);
                    taskList.add(todoTask);

                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("The following ToDo has been added.");
                    System.out.println(todoTask);
                    System.out.println("You now have " + taskList.size()  + " items in the list.");
                    System.out.println(HORIZONTAL_LINE);
                    break;
                }
                case ("deadline"): {
                    String description = params.split(" /by ")[0];
                    String deadline = params.split(" /by ")[1];

                    Deadline deadlineTask = new Deadline(description, deadline);
                    taskList.add(deadlineTask);

                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("The following deadline has been added.");
                    System.out.println(deadlineTask);
                    System.out.println("You now have " + taskList.size()  + " items in the list.");
                    System.out.println(HORIZONTAL_LINE);
                    break;
                }
                case ("event"): {
                    String description = params.split(" /from ")[0];
                    String fromAndToTime = params.split(" /from ")[1];
                    String from = fromAndToTime.split(" /to ")[0];
                    String to = fromAndToTime.split(" /to ")[1];

                    Event eventTask = new Event(description, from, to);
                    taskList.add(eventTask);

                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("The following event has been added.");
                    System.out.println(eventTask);
                    System.out.println("You now have " + taskList.size()  + " items in the list.");
                    System.out.println(HORIZONTAL_LINE);
                    break;
                }
                default: {
                    taskList.add(new Task(input));
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("I have added \"" + input + "\" to the list.");
                    System.out.println(HORIZONTAL_LINE);
                    break;
                }
            }
        }
    }
}
