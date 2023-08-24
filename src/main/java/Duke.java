import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {

    /**
     * The main method to run the Duke program, a task management application.
     * It reads user input and performs various tasks based on the input commands.
     *
     * @param args The command-line arguments (not used in this context).
     */
    public static void main(String[] args) {
        try {
            Scanner reader = new Scanner(System.in);
            List<Task> list = new ArrayList<>(100);
            System.out.println("Hello! I'm Victor\n" +
                    "What can I do for you?\n----------\n");

            label:
            while (true) {
                String input = reader.nextLine();
                String[] formattedInput = input.split(" ", 2);
                String command = formattedInput[0];
                switch (command) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        break label;
                    case "list":
                        for (int i = 0; i < list.size(); i++) {
                            Task task = list.get(i);
                            System.out.printf("%d.%s\n", i + 1, task);
                        }
                        System.out.println("----------\n");
                        break;
                    case "mark": {
                        if (formattedInput.length == 1)
                            throw new DukeException("The task number to mark cannot be empty.");
                        int taskNumber = Integer.parseInt(formattedInput[1]);
                        if (taskNumber > list.size() || taskNumber < 1) throw new DukeException("Invalid task number");
                        Task task = list.get(taskNumber - 1);
                        task.markDone();
                        System.out.printf("Nice! I've marked this task as done:\n" +
                                "%s\n" + "----------\n", task);
                        break;
                    }
                    case "unmark": {
                        if (formattedInput.length == 1)
                            throw new DukeException("The task number to unmark cannot be empty.");
                        int taskNumber = Integer.parseInt(formattedInput[1]);
                        if (taskNumber > list.size() || taskNumber < 1) throw new DukeException("Invalid task number");
                        Task task = list.get(taskNumber - 1);
                        task.unMarkDone();
                        System.out.printf("OK, I've marked this task as not done yet:\n" +
                                "%s\n" + "----------\n", task);
                        break;
                    }
                    case "todo": {
                        if (formattedInput.length == 1)
                            throw new DukeException("☹ OOPS!!! The description of a todo task cannot be empty.");
                        Task task = new Todo(formattedInput[1]);
                        list.add(task);
                        System.out.printf("Got it. I've added this task:\n" +
                                "%s\n" + "Now you have %d tasks in the list.\n" +
                                "----------\n", task, list.size());
                        break;
                    }
                    case "deadline": {
                        String[] deadlineInfo = formattedInput[1].split(" /by ");
                        if (deadlineInfo.length < 2)
                            throw new DukeException("☹ OOPS!!! Missing description or deadline of deadline task. Valid Input Syntax: deadline desc /by date");
                        String description = deadlineInfo[0];
                        String deadline = deadlineInfo[1];
                        Task task = new Deadline(description, deadline);
                        list.add(task);
                        System.out.printf("Got it. I've added this task:\n" +
                                "%s\n" + "Now you have %d tasks in the list.\n" +
                                "----------\n", task, list.size());
                        break;
                    }
                    case "event": {
                        String[] eventInfo = formattedInput[1].split(" /from | /to ");
                        if (eventInfo.length < 3)
                            throw new DukeException("☹ OOPS!!! Missing description, start time or end time of deadline task. Valid Input Syntax: event desc /from date /to date");
                        String description = eventInfo[0];
                        String from = eventInfo[1];
                        String to = eventInfo[2];
                        Task task = new Event(description, from, to);
                        list.add(task);
                        System.out.printf("Got it. I've added this task:\n" +
                                "%s\n" + "Now you have %d tasks in the list.\n" +
                                "----------\n", task, list.size());
                        break;
                    }
                    case "delete": {
                        if (formattedInput.length == 1)
                            throw new DukeException("The task number to mark cannot be empty.");
                        int taskNumber = Integer.parseInt(formattedInput[1]);
                        if (taskNumber > list.size() || taskNumber < 1) throw new DukeException("Invalid task number");
                        Task task = list.remove(taskNumber - 1);
                        System.out.printf("Noted. I've removed this task:\n" +
                                "%s\n" + "Now you have %d tasks in the list.\n" +
                                "----------\n", task, list.size());
                        break;
                    }
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
