import java.util.Scanner;
import java.util.ArrayList;

/**
 * The main class for Duke Chatbot
 */
public class Duke {
    /** Variable to show horizontal lines */
    static final String LINE_BREAK = "--------------------------------------------------"
            + "--------------------------------------------------------";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        System.out.println("\n");
        System.out.println(LINE_BREAK);
        System.out.println("Welcome. My name is Duke");
        System.out.println("What will you do today?");

        while (true) {
            try {
                System.out.println(LINE_BREAK);
                System.out.println("\n");
                String input = sc.nextLine().trim();
                System.out.println(LINE_BREAK);
                String[] split = input.split(" ");
                if (split[0].toLowerCase().equals("list")) {
                    if (split.length > 1) {
                        throw new InvalidCommandSyntaxException("'list' command must not be followed by anything");
                    }
                    if (list.size() == 0) {
                        System.out.println("List is empty");
                    } else {
                        for (int i = 1; i < list.size() + 1; i++) {
                            Task current = list.get(i - 1);
                            System.out.println(i + ". " + current.convertToString());
                        }
                    }
                } else if (split[0].toLowerCase().equals("mark")) {
                    if (split.length == 1) {
                        throw new MissingTaskNumberException("Task number cannot be empty");
                    } else if (list.size() < 1) {
                        throw new EmptyListException("List is currently empty");
                    }
                    int taskNum = Integer.parseInt(input.substring(5));
                    if (taskNum > list.size() || taskNum < 1) {
                        throw new InvalidTaskNumberException("Task number is out of bounds, please try again");
                    }
                    list.get(taskNum - 1).markAsDone();
                } else if (split[0].toLowerCase().equals("unmark")) {
                    if (split.length == 1) {
                        throw new MissingTaskNumberException("Task number cannot be empty");
                    } else if (list.size() < 1) {
                        throw new EmptyListException("List is currently empty");
                    }
                    int taskNum = Integer.parseInt(input.substring(7));
                    if (taskNum > list.size() || taskNum < 1) {
                        throw new InvalidTaskNumberException("Task number is out of bounds, please try again");
                    }
                    list.get(taskNum - 1).markAsNotDone();
                } else if (split[0].toLowerCase().equals("delete")) {
                    if (split.length == 1) {
                        throw new MissingTaskNumberException("Task number cannot be empty");
                    } else if (list.size() < 1) {
                        throw new EmptyListException("List is currently empty");
                    }
                    int taskNum = Integer.parseInt(input.substring(7));
                    if (taskNum > list.size() || taskNum < 1) {
                        throw new InvalidTaskNumberException("Task number is out of bounds, please try again");
                    }
                    Task.deleteTask(list.get(taskNum - 1), list);
                } else if (split[0].toLowerCase().equals("todo")) {
                    if (input.trim().length() <= 4) {
                        throw new MissingTaskDescriptionException("Todo task description cannot be empty");
                    }
                    String taskName = input.substring(5);
                    ToDo.printTaskAdded(taskName, list);
                } else if (split[0].toLowerCase().equals("deadline")) {
                    if (input.trim().length() <= 8) {
                        throw new MissingTaskDescriptionException("Deadline task description cannot be empty");
                    }
                    String[] taskDesc = input.substring(9).split("/by");
                    if (taskDesc.length < 2) {
                        throw new InvalidTaskTimeException("Deadline task must have exactly one /by deadline");
                    }
                    String taskName = taskDesc[0].trim();
                    if (taskName.length() == 0) {
                        throw new MissingTaskNameException("Deadline task name cannot be empty");
                    }
                    String deadline = taskDesc[1].trim();
                    Deadline.printTaskAdded(taskName, deadline, list);
                } else if (split[0].toLowerCase().equals("event")) {
                    if (input.trim().length() <= 5) {
                        throw new MissingTaskDescriptionException("Event task description cannot be empty");
                    }
                    String[] taskDesc = input.substring(6).split("/from");
                    if (taskDesc.length < 2 || taskDesc.length > 2) {
                        throw new InvalidTaskTimeException(
                                "Event task must have exactly one /from and one /to times, in that order");
                    }
                    String taskName = taskDesc[0].trim();
                    String[] fromAndTo = taskDesc[1].split("/to");
                    if (fromAndTo.length < 2 || fromAndTo.length > 2) {
                        throw new InvalidTaskTimeException(
                                "Event task must have exactly one /from and one /to times, in that order");
                    }
                    if (taskName.length() == 0) {
                        throw new MissingTaskNameException("Event task name cannot be empty");
                    }
                    String start = fromAndTo[0].trim();
                    String end = fromAndTo[1].trim();
                    Event.printTaskAdded(taskName, start, end, list);
                } else if (split[0].toLowerCase().equals("bye")) {
                    if (split.length > 1) {
                        throw new InvalidCommandSyntaxException("'bye' command must not be followed by anything");
                    }
                    System.out.println("I hope you enjoy my service. Thank you and goodbye");
                    System.out.println(LINE_BREAK);
                    break;
                } else {
                    throw new InvalidCommandException("No such command exists, please try again");
                }
            } catch (InvalidCommandException exception) {
                System.out.println("!ERROR! " + exception);
            } catch (InvalidCommandSyntaxException exception) {
                System.out.println("!ERROR! " + exception);
            } catch (MissingTaskDescriptionException exception) {
                System.out.println("!ERROR! " + exception);
            } catch (MissingTaskNameException exception) {
                System.out.println("!ERROR! " + exception);
            } catch (InvalidTaskTimeException exception) {
                System.out.println("!ERROR! " + exception);
            } catch (InvalidTaskNumberException exception) {
                System.out.println("!ERROR! " + exception);
            } catch (MissingTaskNumberException exception) {
                System.out.println("!ERROR! " + exception);
            } catch (EmptyListException exception) {
                System.out.println("!ERROR! " + exception);
            }
        }

        // Close the scanner
        sc.close();
    }
}
