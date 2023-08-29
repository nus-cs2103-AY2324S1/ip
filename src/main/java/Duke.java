import java.util.Scanner;
import java.util.ArrayList;
import exception.DukeException;
import task.Event;
import task.Task;
import task.Todo;
import task.Deadline;

public class Duke {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> taskList;
    private Duke() {this.taskList = new ArrayList<Task>(); }
    public static void main(String[] args) {
        String logo =
                " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n " + logo);
        String chatBotName = "Duke";

        String header = "____________________________________________________________\n" +
                "Hello! I'm " + chatBotName +
                " \nWhat can I do for you?";

        System.out.println(header);
        Duke duke = new Duke();
        duke.executeDuke();

    }

    private void executeDuke() {
        while (true) {
            System.out.println("____________________________________________________________\n");
            String userInput = sc.nextLine();
            System.out.println("____________________________________________________________\n");

            try {
                if (userInput.equals("bye")){
                    System.out.println("\tBye. Hope to see you again soon!");
                    break;
                } else if (userInput.equals("list")) {
                    System.out.println("Here are your entries:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("\t" + (i + 1) + ". " + taskList.get(i).toString());
                    }
                } else if (userInput.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                    if (taskIndex >= 0 && taskIndex < taskList.size()) {
                        taskList.get(taskIndex).markDone();
                        System.out.println("Nice! I've marked this task as done:\n\t" +
                                taskList.get(taskIndex).toString());
                    } else {
                        System.out.println("\"☹ OOPS!!! Please provide a valid task index to mark.\"");
                    }
                } else if (userInput.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    if (taskIndex >= 0 && taskIndex < taskList.size()) {
                        taskList.get(taskIndex).markNotDone();
                        System.out.println("OK, I've marked this task as not done yet:\n\t" +
                                taskList.get(taskIndex).toString());
                    } else {
                        System.out.println("☹ OOPS!!! Please provide a valid task index to unmarked.");
                    }
                } else if (userInput.startsWith("todo ")) {
                    String todoDescription = userInput.replace("todo ", "");
                    validateTodo(todoDescription);
                    Todo newTodo = new Todo(todoDescription);
                    taskList.add(newTodo);
                    displayCompletionMessage(newTodo);
                }
                else if (userInput.startsWith("deadline ")) {
                    String[] info = validateDeadline(userInput);
                    Deadline deadline = new Deadline(info[0], info[1]);
                    taskList.add(deadline);
                    displayCompletionMessage(deadline);

                } else if (userInput.startsWith("event ")) {
                    String[] info = validateEvent(userInput);
                    Event event = new Event(info[0], info[1], info[2]);
                    taskList.add(event);
                    displayCompletionMessage(event);

                } else if (userInput.startsWith("delete ")) {
                    int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    if (taskIndex >= 0 && taskIndex < taskList.size()) {
                        Task removedTask = taskList.remove(taskIndex);
                        System.out.println("Noted. I've removed this task:\n\t" + removedTask);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    } else {
                        System.out.println("☹ OOPS!!! Please provide a valid task index to delete.");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            } catch (DukeException e) {
                System.out.println("\t" + e.getMessage());
            }


        }
    }

    private void validateTodo(String todo) throws DukeException {
        if (todo.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private String[] validateDeadline(String input) throws DukeException {
        String[] splitInput = input.replace("deadline", "").split(" /by ");

        for (int i = 0; i < splitInput.length; i++) {
            splitInput[i] = splitInput[i].trim(); //remove whitespace
        }

        if (splitInput.length != 2 || splitInput[0].isBlank() || splitInput[1].isBlank()) {
            throw new DukeException("☹ OOPS!!! Please remember to put a task description and deadline");
        }
        return splitInput;
    }

    private String[] validateEvent(String input) throws DukeException {
        String[] info = input.replace("event ", "").split(" /from ");

        if (info.length != 2) {
            throw new DukeException("☹ OOPS!!! Please provide a valid event format: event <description> /from <start time> /to <end time>");
        }

        String description = info[0];

        String[] timeInfo = info[1].split(" /to ");
        if (timeInfo.length != 2) {
            throw new DukeException("☹ OOPS!!! Please provide both start and end times for the event.");
        }

        String startDateTime = timeInfo[0];
        String endDateTime = timeInfo[1];

        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else if (startDateTime.isBlank()) {
            throw new DukeException("☹ OOPS!!! The start time of this event cannot be empty.");
        } else if (endDateTime.isBlank()) {
            throw new DukeException("☹ OOPS!!! The end time of this event cannot be empty.");
        }

        return new String[]{description, startDateTime, endDateTime};
    }





    private void displayCompletionMessage(Task task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + taskList.size() + " tasks in the list.");
    }


}
