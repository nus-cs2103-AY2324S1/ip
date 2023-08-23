import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasks;
    public static void main(String[] args) {
        String name = "Derek";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        Duke.tasks = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        boolean run = true;
        while (run) {
            String line = in.nextLine();
            String[] split = line.split(" ");
            try {
                switch (split[0]) {
                    case "mark":
                        int index = Integer.parseInt(split[1]) - 1;
                        if (!Duke.isTaskIndexValid(index)) {
                            throw new InvalidCommandException("☹ OOPS!!! The task index in invalid");
                        }
                        Task task = Duke.tasks.get(index);
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(task);
                        break;
                    case "unmark":
                        index = Integer.parseInt(split[1]) - 1;
                        if (!Duke.isTaskIndexValid(index)) {
                            throw new InvalidCommandException("☹ OOPS!!! The task index in invalid");
                        }
                        task = Duke.tasks.get(index);
                        task.markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(task);
                        break;
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < Duke.tasks.size(); i++) {
                            System.out.println((i + 1) + "." + Duke.tasks.get(i));
                        }
                        break;
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        run = false;
                        break;
                    case "todo":
                        String[] processedToDoInput = ToDo.processInput(split);
                        ToDo newTodo = new ToDo(processedToDoInput[0]);
                        Duke.tasks.add(newTodo);
                        Duke.printTaskAddedMessages(newTodo);
                        break;
                    case "deadline":
                        String[] processedDeadlineInput = Deadline.processInput(split);
                        Deadline newDeadline = new Deadline(processedDeadlineInput[0], processedDeadlineInput[1]);
                        Duke.tasks.add(newDeadline);
                        Duke.printTaskAddedMessages(newDeadline);
                        break;
                    case "event":
                        String[] processedEventInput = Event.processInput(split);
                        Event newEvent = new Event(processedEventInput[0], processedEventInput[1], processedEventInput[2]);
                        Duke.tasks.add(newEvent);
                        Duke.printTaskAddedMessages(newEvent);
                        break;
                    case "delete":
                        index = Integer.parseInt(split[1]) - 1;
                        if (!Duke.isTaskIndexValid(index)) {
                            throw new InvalidCommandException("☹ OOPS!!! The task index in invalid");
                        }
                        task = Duke.tasks.remove(index);
                        Duke.printTaskDeletedMessage(task);
                        break;
                    default:
                        throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (InvalidTaskException | InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printTaskAddedMessages(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        Duke.printTaskCount();
    }

    private static void printTaskDeletedMessage(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        Duke.printTaskCount();
    }

    private static void printTaskCount() {
        int tasksCount = Duke.tasks.size();
        System.out.println("Now you have " + tasksCount + (tasksCount == 1 ? " task" : " tasks") + " in the list.");
    }

    private static boolean isTaskIndexValid(int index) {
        return index >= 0 && index < Duke.tasks.size();
    }
}
