
import Exceptions.*;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>(100);
        String logo =
                  "\n" +
                          "                                                     \n" +
                          "     / /                                             \n" +
                          "    / /         ___      _   __      ___       __    \n" +
                          "   / /        //___) ) // ) )  ) ) //   ) ) //   ) ) \n" +
                          "  / /        //       // / /  / / //   / / //   / /  \n" +
                          " / /____/ / ((____   // / /  / / ((___/ / //   / /   \n";
        System.out.println("Hello! I'm Lemon!" + logo + "\nWhat can I do for you?");
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    if (tasks.size() < 1) {
                        throw new NoTasksException("");
                    }
                    System.out.println("Here are the tasks in your list: \n");
                    for (int i = 0; i < tasks.size(); i++ ) {
                        Task nextTask = tasks.get(i);
                        System.out.println(i + 1 + ". " + nextTask.toString());
                    }
                    System.out.println("\n");
                } else {
                    String command = input.split(" ")[0].toLowerCase();
                    switch (command) {
                        case "mark":
                            int indexToMark = Integer.valueOf(input.split(" ")[1]);
                            try {
                                Task markedTask = tasks.get(indexToMark - 1);
                                markedTask.markAsDone();
                                System.out.println("Nice! I've marked this task as done: \n " + markedTask.toString() + "\n");
                                break;
                            } catch (IndexOutOfBoundsException e) {
                                throw new InvalidTaskIndexException("");
                            }
                        case "unmark":
                            int indexToUnmark = Integer.valueOf(input.split(" ")[1]);
                            try {
                                Task unmarkedTask = tasks.get(indexToUnmark - 1);
                                unmarkedTask.markAsUndone();
                                System.out.println("OK, I've marked this task as not done yet: \n " + unmarkedTask.toString() + "\n");
                                break;
                            } catch (IndexOutOfBoundsException e) {
                                throw new InvalidTaskIndexException("");
                            }
                        case "todo":
                            String[] taskSplit = input.split(" ", 2);
                            if (taskSplit.length < 2) {
                                throw new InvalidTodoException("");
                            }
                            String taskDescription = taskSplit[1];
                            tasks.add(new Todo(taskDescription));
                            System.out.println("Got it. I've added this task: " + taskDescription);
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
                            break;
                        case "deadline":
                            String task = input.split(" ", 2)[1];
                            String[] getDeadlineArray = task.split("/by ", 2);
                            if (getDeadlineArray.length < 2) {
                                throw new InvalidDeadlineException("");
                            }
                            String taskDesc = getDeadlineArray[0];
                            String by = getDeadlineArray[1];
                            Task newDeadlineTask = new Deadline(taskDesc, by);
                            tasks.add(newDeadlineTask);
                            System.out.println("Got it. I've added this task: " + newDeadlineTask.toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
                            break;
                        case "event":
                            String inputTask = input.split(" ", 2)[1];
                            String[] getEventFromArray = inputTask.split("/from ", 2);
                            if (getEventFromArray.length < 2) {
                                throw new InvalidEventException("");
                            }
                            String taskDetails = getEventFromArray[0];
                            String[] getEventToArray = getEventFromArray[1].split("/to ", 2);
                            if (getEventToArray.length < 2) {
                                throw new InvalidEventException("");
                            }
                            String from = getEventToArray[0];
                            String to = getEventToArray[1];
                            Task newEventTask = new Event(taskDetails, from, to);
                            tasks.add(newEventTask);
                            System.out.println("Got it. I've added this task: " + newEventTask.toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
                            break;
                        default:
                            throw new InvalidTaskException("");
                    }
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
