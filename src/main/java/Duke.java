import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        try {
            Scanner reader = new Scanner(System.in);
            List<Task> list = new ArrayList<>(100);
            System.out.println("Hello! I'm Victor\n" +
                    "What can I do for you?\n----------\n");

            while (true) {
                String input = reader.nextLine();
                String[] formattedInput = input.split(" ", 2);
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    for (int i = 0; i < list.size(); i++) {
                        Task task = list.get(i);
                        System.out.printf("%d.%s\n", i + 1, task);
                    }
                    System.out.println("----------\n");
                } else if (formattedInput[0].equals("mark")) {
                    if (formattedInput.length == 1) throw new DukeException("The task number to mark cannot be empty.");
                    int taskNumber = Integer.parseInt(formattedInput[1]);
                    if (taskNumber > list.size() || taskNumber < 1) throw new DukeException("Invalid task number");
                    Task task = list.get(taskNumber - 1);
                    task.markDone();
                    System.out.printf("Nice! I've marked this task as done:\n" +
                            "%s\n" + "----------\n", task);
                } else if (formattedInput[0].equals("unmark")) {
                    if (formattedInput.length == 1) throw new DukeException("The task number to unmark cannot be empty.");
                    int taskNumber = Integer.parseInt(formattedInput[1]);
                    if (taskNumber > list.size() || taskNumber < 1) throw new DukeException("Invalid task number");
                    Task task = list.get(taskNumber - 1);
                    task.unMarkDone();
                    System.out.printf("OK, I've marked this task as not done yet:\n" +
                            "%s\n" + "----------\n", task);
                } else if (formattedInput[0].equals("todo")) {
                    if (formattedInput.length == 1) throw new DukeException("☹ OOPS!!! The description of a todo task cannot be empty.");
                    Task task = new Todo(formattedInput[1]);
                    list.add(task);
                    System.out.printf("Got it. I've added this task:\n" +
                            "%s\n" + "Now you have %d tasks in the list.\n" +
                            "----------\n", task, list.size());
                } else if (formattedInput[0].equals("deadline")) {
                    int indexOfBy = input.indexOf("/by");
                    if (indexOfBy == -1) throw new DukeException("Deadline not provided");
                    int indexOfDeadline = input.indexOf("deadline");
                    if ((indexOfDeadline + 9) > (indexOfBy - 1)) throw new DukeException("☹ OOPS!!! The description of a deadline task cannot be empty.");
                    String description = input.substring(indexOfDeadline + 9, indexOfBy - 1);
                    if (indexOfBy + 4 > input.length()) throw new DukeException("☹ OOPS!!! The deadline of a deadline task cannot be empty.");
                    String deadline = input.substring(indexOfBy + 4);
                    Task task = new Deadline(description, deadline);
                    list.add(task);
                    System.out.printf("Got it. I've added this task:\n" +
                            "%s\n" + "Now you have %d tasks in the list.\n" +
                            "----------\n", task, list.size());
                } else if (formattedInput[0].equals("event")) {
                    int indexOfFrom = input.indexOf("/from");
                    if (indexOfFrom == -1) throw new DukeException("From time not provided");
                    int indexOfTo = input.indexOf("/to");
                    if (indexOfTo == -1) throw new DukeException("To time not provided");
                    int indexOfEvent = input.indexOf("event");
                    if ((indexOfEvent + 6) > (indexOfFrom - 1)) throw new DukeException("☹ OOPS!!! The description of a event task cannot be empty.");
                    String description = input.substring(indexOfEvent + 6, indexOfFrom - 1);
                    if ((indexOfFrom + 6) > (indexOfTo - 1)) throw new DukeException("☹ OOPS!!! The from time of a event task cannot be empty.");
                    String from = input.substring(indexOfFrom + 6, indexOfTo - 1);
                    if (indexOfTo + 4 > input.length()) throw new DukeException("☹ OOPS!!! The to time of a event task cannot be empty.");
                    String to = input.substring(indexOfTo + 4);
                    Task task = new Event(description, from, to);
                    list.add(task);
                    System.out.printf("Got it. I've added this task:\n" +
                            "%s\n" + "Now you have %d tasks in the list.\n" +
                            "----------\n", task, list.size());
                } else if (formattedInput[0].equals("delete")) {
                    if (formattedInput.length == 1) throw new DukeException("The task number to mark cannot be empty.");
                    int taskNumber = Integer.parseInt(formattedInput[1]);
                    if (taskNumber > list.size() || taskNumber < 1) throw new DukeException("Invalid task number");
                    Task task = list.remove(taskNumber - 1);
                    System.out.printf("Noted. I've removed this task:\n" +
                            "%s\n" + "Now you have %d tasks in the list.\n" +
                            "----------\n", task, list.size());
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }
}
