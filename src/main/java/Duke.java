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

    public static void main(String[] args) throws SisyphusException{
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
     * 1. "bye" to exit chat
     * 2. "list" to list tasks
     * 3. "mark" to mark task
     * 4. "unmark" to unmark task
     * 5. "delete" to delete task
     * 5. "todo" to create a todo
     * 6. "deadline" to create a deadline
     * 7. "event" to create an event
     * 8. default behaviour is to add task
     */
    public static void handleCommand() throws SisyphusException {
        boolean isChatting = true;
        Scanner scanner= new Scanner(System.in);
        String input, command, params = "";
        String[] inputArray;
        Storage storage = new Storage();

        TaskList taskList = storage.loadData();


        while (isChatting) {
            input = scanner.nextLine();
            try {
                inputArray = input.split(" ", 2);
                command = inputArray[0];
                if (inputArray.length > 1) {
                    params = inputArray[1];
                }

                switch (command) {
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
                        System.out.println(i + 1 + ". " + taskList.getTask(i));
                    }
                    System.out.println(HORIZONTAL_LINE);
                    break;
                }
                case ("mark"): {
                    int index;
                    try {
                        index = Integer.parseInt(params.split(" ")[0]) - 1;
                        taskList.markTask(index);
                        storage.writeFile(taskList);
                    } catch (Exception e) {
                        throw new SisyphusException("You must include a valid task number. "
                                + "Use list to see what is valid.");
                    }
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("The following item has been marked as done.");
                    System.out.println(taskList.getTask(index));
                    break;
                }
                case ("unmark"): {
                    int index;
                    try {
                        index = Integer.parseInt(params.split(" ")[0]) - 1;
                        taskList.unmarkTask(index);
                        storage.writeFile(taskList);
                    } catch (Exception e) {
                        throw new SisyphusException("You must include a valid task number. "
                                + "Use list to see what is valid.");
                    }

                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("The following item has been unmarked and is now uncompleted.");
                    System.out.println(taskList.getTask(index));
                    break;
                }
                case ("delete"): {
                    int index;
                    Task deletedTask;
                    try {
                        index = Integer.parseInt(params.split(" ")[0]) - 1;
                        deletedTask = taskList.getTask(index);
                        taskList.deleteTask(index);
                        storage.writeFile(taskList);
                    } catch (Exception e) {
                        throw new SisyphusException("You must include a valid task number. "
                                + "Use list to see what is valid.");
                    }

                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("The following item has been deleted from the list.");
                    System.out.println(deletedTask);
                    break;
                }
                case ("todo"): {
                    if (params == "" || params == null) {
                        throw new SisyphusException("Include a description for the ToDo. \nHere is an example: " +
                                "todo Roll Boulder");
                    }
                    ToDo todoTask = new ToDo(params);
                    taskList.addTask(todoTask);
                    storage.writeFile(taskList);

                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("The following ToDo has been added.");
                    System.out.println(todoTask);
                    System.out.println("You now have " + taskList.size() + " items in the list.");
                    System.out.println(HORIZONTAL_LINE);
                    break;
                }
                case ("deadline"): {
                    String description, deadline;
                    try {
                        description = params.split(" /by ")[0];
                        deadline = params.split(" /by ")[1];
                    } catch (Exception e) {
                        throw new SisyphusException("Include both description and deadline for a deadline. \nHere " +
                                "is an example: deadline roll boulder /by eternity");
                    }

                    Deadline deadlineTask = new Deadline(description, deadline);
                    taskList.addTask(deadlineTask);
                    storage.writeFile(taskList);

                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("The following deadline has been added.");
                    System.out.println(deadlineTask);
                    System.out.println("You now have " + taskList.size() + " items in the list.");
                    System.out.println(HORIZONTAL_LINE);
                    break;
                }
                case ("event"): {
                    String description, fromAndToTime, from, to;
                    try {
                        description = params.split(" /from ")[0];
                        fromAndToTime = params.split(" /from ")[1];
                        from = fromAndToTime.split(" /to ")[0];
                        to = fromAndToTime.split(" /to ")[1];
                    } catch (Exception e) {
                        throw new SisyphusException("Include the description, from and to time for an event. \nHere is" +
                                " an example: event roll boulder /from past /to eternity");
                    }

                    Event eventTask = new Event(description, from, to);
                    taskList.addTask(eventTask);
                    storage.writeFile(taskList);

                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("The following event has been added.");
                    System.out.println(eventTask);
                    System.out.println("You now have " + taskList.size() + " items in the list.");
                    System.out.println(HORIZONTAL_LINE);
                    break;
                }
                default: {
                    throw new SisyphusException("Enter a valid command. Available comments are " +
                            "bye, list, event, deadline, todo, mark, unmark.");
                }
                }
            } catch (SisyphusException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
