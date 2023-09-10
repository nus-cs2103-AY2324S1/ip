package noelPackage.helper;

import noelPackage.tasks.Task;

public class Parser {

    /**
     * The task list to manipulate.
     */
    private final Tasklist tasks;

    /**
     * The storage manager to persist tasks.
     */
    private final Storage storage;

    /**
     * Constructs a Parser object.
     *
     * @param tasks   The task list to manipulate.
     * @param storage The storage manager to use.
     */
    public Parser(Tasklist tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Prints a formatted message to the console.
     *
     * @param message The message to print.
     */
    public static void printFunction(String message){
        String filler = "____________________________________________________________";
        System.out.println(filler + "\n" + message + "\n" + filler);
    }

    /**
     * Parses a user command and performs the corresponding action.
     *
     * @param nextLine The command entered by the user.
     * @return -1 if the command is "bye", 0 otherwise.
     */
    public int parseCommand(String nextLine) {
        String command;
        if (nextLine.equals("bye")) {
            return -1;
        } else if (nextLine.equals("list")) {
            tasks.printTaskList();
        } else if (nextLine.contains(" ")) {

            String[] result = nextLine.split(" ");
            command = result[0];

            switch (command) {

            case "mark": {
                int taskNum = Integer.parseInt(result[1]);
                taskNum = taskNum - 1;
                tasks.markAsDone(taskNum);
                storage.writeToFile(tasks.getTaskAsList());
                break;
            }
            case "unmark": {
                int taskNum = Integer.parseInt(result[1]);
                tasks.unMark(taskNum);
                storage.writeToFile(tasks.getTaskAsList());
                break;
            }
            case "todo":
                result = nextLine.split("todo ");
                if (result.length == 0) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    tasks.addToDo(result[1]);
                    storage.writeToFile(tasks.getTaskAsList());
                }
                break;

            case "deadline":
                result = nextLine.split("deadline ");
                if (result.length == 0) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                } else {
                    String[] deadlineHelper = result[1].split(" /by ");
                    if (deadlineHelper.length == 2) {
                        tasks.addDeadline(deadlineHelper[0], deadlineHelper[1]);
                        storage.writeToFile(tasks.getTaskAsList());
                    } else {
                        System.out.println("OOPS!!! Remember to add the date/description");
                    }
                }
                break;

            case "event":
                result = nextLine.split("event ");
                if (result.length == 0) {
                    System.out.println("OOPS!!! The description of a event cannot be empty.");
                } else {
                    String[] eventsHelper = result[1].split(" /from ");
                    command = eventsHelper[0];
                    if (eventsHelper.length == 2) {
                        eventsHelper = eventsHelper[1].split(" /to ");
                        if (eventsHelper.length == 2) {
                            tasks.addEvent(command, eventsHelper[0], eventsHelper[1]);
                            storage.writeToFile(tasks.getTaskAsList());
                        } else {
                            System.out.println("Insufficient commands provided!");
                        }
                    } else {
                        System.out.println("Insufficient commands provided!");
                    }
                }
                break;

            case "delete":
                result = nextLine.split("delete ");
                if (result.length == 0) {
                    System.out.println("OOPS!!! The description of a delete cannot be empty.");
                } else {
                    int intToRemove = Integer.parseInt(result[1]) - 1;
                    Task taskToDel = tasks.getTask(intToRemove);
                    tasks.remove(intToRemove);

                    String delStart = "Noted. I've removed this task:\n";
                    String delEnd = "Now you have " + tasks.getSize() + " tasks in the list.";
                    printFunction(delStart + taskToDel + "\n" + delEnd);
                    storage.writeToFile(tasks.getTaskAsList());
                    break;
                }

            default:
                System.out.println("Invalid Option!");
            }
        } else {
            System.out.println("Invalid Option!");
        }
        return 0;
    }
}
