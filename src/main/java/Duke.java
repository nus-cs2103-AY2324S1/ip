
import Exceptions.*;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import datafile.Storage;

import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    enum Commands {
        BYE,
        MARK,
        UNMARK,
        DELETE,
        LIST,
        TODO,
        DEADLINE,
        EVENT
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage tasksData = new Storage();

        ArrayList<Task> tasks = tasksData.loadFile();
        String logo =
                  "\n" +
                          "     / /                                             \n" +
                          "    / /         ___      _   __      ___       __    \n" +
                          "   / /        //___) ) // ) )  ) ) //   ) ) //   ) ) \n" +
                          "  / /        //       // / /  / / //   / / //   / /  \n" +
                          " / /____/ / ((____   // / /  / / ((___/ / //   / /   \n";
        System.out.println("Hello! I'm Lemon!" + "\nWhat can I do for you?");
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            try {
                    String commandType = input.split(" ")[0].toUpperCase();
                    Commands command = Commands.valueOf(commandType);
                    switch (command) {
                        case LIST:
                            if (tasks.size() < 1) {
                                throw new NoTasksException("");
                            }
                            System.out.println("Here are the tasks in your list:\n");
                            for (int i = 0; i < tasks.size(); i++ ) {
                                Task nextTask = tasks.get(i);
                                System.out.println(i + 1 + ". " + nextTask.toString());
                            }
                            System.out.println("\n");
                            break;
                        case MARK:
                            try {
                                int indexToMark = Integer.valueOf(input.split(" ")[1]);
                                Task markedTask = tasks.get(indexToMark - 1);
                                markedTask.markAsDone();
                                tasksData.saveTasks(tasks);
                                System.out.println("Nice! I've marked this task as done:\n " + markedTask.toString() + "\n");
                                break;
                            } catch (IndexOutOfBoundsException e) {
                                throw new InvalidTaskIndexException("");
                            }
                        case UNMARK:
                            try {
                                int indexToUnmark = Integer.valueOf(input.split(" ")[1]);
                                Task unmarkedTask = tasks.get(indexToUnmark - 1);
                                unmarkedTask.markAsUndone();
                                tasksData.saveTasks(tasks);
                                System.out.println("OK, I've marked this task as not done yet:\n " + unmarkedTask.toString() + "\n");
                                break;
                            } catch (IndexOutOfBoundsException e) {
                                throw new InvalidTaskIndexException("");
                            }
                        case TODO:
                            String[] taskSplit = input.split(" ", 2);
                            if (taskSplit.length < 2) {
                                throw new InvalidTodoException("");
                            }
                            String taskDescription = taskSplit[1];
                            tasks.add(new Todo(taskDescription));
                            tasksData.saveTasks(tasks);
                            System.out.println("Got it. I've added this task: " + taskDescription);
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
                            break;
                        case DEADLINE:
                            String task = input.split(" ", 2)[1];
                            String[] getDeadlineArray = task.split("/by ", 2);
                            if (getDeadlineArray.length < 2) {
                                throw new InvalidDeadlineException("");
                            }
                            String taskDesc = getDeadlineArray[0];
                            String by = getDeadlineArray[1];
                            Task newDeadlineTask = new Deadline(taskDesc, by);
                            tasks.add(newDeadlineTask);
                            tasksData.saveTasks(tasks);
                            System.out.println("Got it. I've added this task: " + newDeadlineTask.toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
                            break;
                        case EVENT:
                            String inputTask = input.split(" ", 2)[1];
                            String[] getEventFromArray = inputTask.split("/from ", 2);
                            if (getEventFromArray.length < 2) {
                                throw new InvalidEventException("");
                            }
                            String taskDetails = getEventFromArray[0];
                            String[] getEventToArray = getEventFromArray[1].split(" /to ", 2);
                            if (getEventToArray.length < 2) {
                                throw new InvalidEventException("");
                            }
                            String from = getEventToArray[0];
                            String to = getEventToArray[1];
                            Task newEventTask = new Event(taskDetails, from, to);
                            tasks.add(newEventTask);
                            tasksData.saveTasks(tasks);
                            System.out.println("Got it. I've added this task: " + newEventTask.toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
                            break;
                        case DELETE:
                            int inputDelete = Integer.valueOf(input.split(" ", 2)[1]) - 1;
                            try {
                                String taskToDelete = tasks.get(inputDelete).toString();
                                System.out.println("Noted. I've removed this task:\n" + taskToDelete);
                                tasks.remove(inputDelete);
                                tasksData.saveTasks(tasks);
                                System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
                            } catch (IndexOutOfBoundsException e) {
                                throw new InvalidTaskIndexException("");
                            }
                            break;
                        default:
                            throw new InvalidTaskException("");
                    }

                input = scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input = scanner.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
