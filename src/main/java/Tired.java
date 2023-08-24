import java.util.ArrayList;
import java.util.Scanner;

enum TaskType {
    TODO, DEADLINE, EVENT
}
public class Tired {
    public static void main(String[] args) {

        ArrayList<Task> list = new ArrayList<Task>();

        String name = "Tired";
        String horizontalLine = "____________________________________________________________";

        System.out.println(horizontalLine);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine + "\n");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {

            System.out.println(horizontalLine);
            try {
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + "." + list.get(i).toString());
                    }
                } else if (input.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
                    if (taskIndex >= 0 && taskIndex < list.size()) {
                        list.get(taskIndex).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list.get(taskIndex).toString());
                    }
                } else if (input.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (taskIndex >= 0 && taskIndex < list.size()) {
                        list.get(taskIndex).markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(list.get(taskIndex).toString());
                    }
                } else if (input.startsWith("delete")) {
                    int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (taskIndex > list.size() || taskIndex < 1) {
                        throw new DukeException("Invalid task number!");
                    }
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(list.get(taskIndex).toString());
                    list.remove(taskIndex);
                } else {
                    TaskType taskType;
                    String[] parts = input.split("/");
                    String taskDetails = parts[0].trim();

                    if (taskDetails.startsWith("todo")) {
                        taskType = TaskType.TODO;
                        taskDetails = taskDetails.substring(4).trim();
                    } else if (taskDetails.startsWith("deadline")) {
                        taskType = TaskType.DEADLINE;
                        taskDetails = taskDetails.substring(8).trim();
                    } else if (taskDetails.startsWith("event")) {
                        taskType = TaskType.EVENT;
                        taskDetails = taskDetails.substring(5).trim();
                    } else {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }

                    switch (taskType) {
                        case TODO:
                            Task t = new ToDo(taskDetails);
                            list.add(t);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(t.toString());
                            break;
                        case DEADLINE:
                            if (parts.length != 2 || parts[1].length() < 2) {
                                // prevent java.lang.StringIndexOutOfBoundsException
                                throw new DukeException("Invalid input for a task with deadline. " +
                                        "Please input 'deadline <task name> /by <end>'");
                            }
                            String date = parts[1].substring(2).trim();
                            Task deadlineTask = new Deadline(taskDetails, date);
                            list.add(deadlineTask);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(deadlineTask.toString());
                            break;
                        case EVENT:
                            if (parts.length != 3 || parts[1].length() < 5 || parts[2].length() < 3) {
                                // prevent java.lang.StringIndexOutOfBoundsException
                                throw new DukeException("Invalid input for an event. " +
                                        "Please input 'event <event name> /from <start> /to <end>'");
                            }
                            String start = parts[1].substring(5).trim();
                            String end = parts[2].substring(3).trim();
                            Task eventTask = new Event(taskDetails, start, end);
                            list.add(eventTask);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(eventTask.toString());
                            break;
                    }
                }
                System.out.println("Now you have " + list.size() + " task(s) in the list.");
                System.out.println(horizontalLine + "\n");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println(horizontalLine + "\n");
            }
            input = sc.nextLine();
        }
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}