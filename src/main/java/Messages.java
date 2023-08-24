import java.util.Scanner;
import java.util.ArrayList;
import exception.InvalidInputException;
import exception.UnknownCommandException;

public class Messages {

    public static final String GREET = "Hello! I'm Chatty!\nWhat can I do for you?";
    public static final String EXIT = "Bye. Hope to see you again soon!";
    private static ArrayList<String> commandsList = new ArrayList<>();
    private static TaskList taskList = new TaskList();

    public static void startChatting() {
        String exitC = "bye";
        String listC = "list";
        String doneC = "mark";
        String undoneC = "unmark";
        String deadlineC = "deadline";
        String eventC = "event";
        String todoC = "todo";

        Scanner scanner = new Scanner(System.in);

        String userCommands;
        do {
            userCommands = scanner.nextLine();
            if (!userCommands.equalsIgnoreCase(exitC)) {
                try {
                    if (userCommands.equalsIgnoreCase(listC)) {
                        taskList.listOutTasks();
                    } else if (userCommands.startsWith(doneC)) {
                        String getIndex = userCommands.substring(doneC.length() + 1);
                        int taskIndex = Integer.parseInt(getIndex) - 1;
                        taskList.markAsDone(taskIndex);
                    } else if (userCommands.startsWith(undoneC)) {
                        String getIndex = userCommands.substring(undoneC.length() + 1);
                        int taskIndex = Integer.parseInt(getIndex) - 1;
                        taskList.markAsUndone(taskIndex);
                    } else if (userCommands.startsWith(deadlineC)) {
                        String taskDescription = userCommands.substring(deadlineC.length() + 1, userCommands.indexOf("/by") - 1);
                        String deadlineInfo = userCommands.substring(userCommands.indexOf("/by") + 4);
                        if (taskDescription.isEmpty() || deadlineInfo.isEmpty()) {
                            throw new InvalidInputException();
                        }
                        Deadline task = new Deadline(taskDescription, deadlineInfo);
                        taskList.addTask(task);
                    } else if (userCommands.startsWith(eventC)) {
                        String taskDescription = userCommands.substring(eventC.length() + 1, userCommands.indexOf("/from") - 1);
                        String startDetails = userCommands.substring(userCommands.indexOf("/from") + 6, userCommands.indexOf("/to") - 1);
                        String endDetails = userCommands.substring(userCommands.indexOf("/to") + 4);
                        if (taskDescription.isEmpty() || startDetails.isEmpty() || endDetails.isEmpty()) {
                            throw new InvalidInputException();
                        }
                        Event task = new Event(taskDescription, startDetails, endDetails);
                        taskList.addTask(task);
                    } else if (userCommands.startsWith(todoC)) {
                        if (userCommands.length() <= todoC.length() + 1) {
                            throw new InvalidInputException();
                        }
                        ToDo task = new ToDo(userCommands);
                        taskList.addTask(task);
                    } else {
                        throw new UnknownCommandException();
                    }
                } catch (InvalidInputException e) {
                    System.out.println("OOPS!!! " + e.getMessage());
                } catch (UnknownCommandException e) {
                    System.out.println("OPPS!!! " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("OPPS!!! Something went wrong");
                }
            }
        } while (!userCommands.equalsIgnoreCase(exitC));

        System.out.println(EXIT);
    }

    private static void listOutCommand() {
        if (commandsList.isEmpty()) {
            System.out.println("There is currently no command in the list.");
        } else {
            System.out.println("Here is the list of commands:");
            for (int i = 0; i < commandsList.size(); i++) {
                System.out.println((i + 1) + ". " + commandsList.get(i));
            }
        }
    }

    private static void addToList(String command) {
        commandsList.add(command);
        System.out.println("I have added \"" + command + "\" to the list. ");
    }

}
