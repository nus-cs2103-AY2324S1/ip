import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Derek";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        ArrayList<Task> tasks = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        boolean run = true;
        while (run) {
            String line = in.nextLine();
            String[] split = line.split(" ");
            try {
                switch (split[0]) {
                    case "mark":
                        int index = Integer.parseInt(split[1]) - 1;
                        Task task = tasks.get(index);
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(task);
                        break;
                    case "unmark":
                        index = Integer.parseInt(split[1]) - 1;
                        task = tasks.get(index);
                        task.markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(task);
                        break;
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                        break;
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        run = false;
                        break;
                    case "todo":
                        String[] processedToDoInput = ToDo.processInput(split);
                        ToDo newTodo = new ToDo(processedToDoInput[0]);
                        tasks.add(newTodo);
                        Duke.printTaskAddedMessages(newTodo, tasks.size());
                        break;
                    case "deadline":
                        String[] processedDeadlineInput = Deadline.processInput(split);
                        Deadline newDeadline = new Deadline(processedDeadlineInput[0], processedDeadlineInput[1]);
                        tasks.add(newDeadline);
                        Duke.printTaskAddedMessages(newDeadline, tasks.size());
                        break;
                    case "event":
                        String[] processedEventInput = Event.processInput(split);
                        Event newEvent = new Event(processedEventInput[0], processedEventInput[1], processedEventInput[2]);
                        tasks.add(newEvent);
                        Duke.printTaskAddedMessages(newEvent, tasks.size());
                        break;
                    default:
                        throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (InvalidTaskException | InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printTaskAddedMessages(Task task, int tasksCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasksCount + (tasksCount == 1 ? " task" : " tasks") + " in the list.");
    }
}
