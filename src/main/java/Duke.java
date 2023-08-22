import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        String welcomeMessage = "____________________________________________________________\n" +
                " Hello! I'm Bongo!\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(welcomeMessage);
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String[] input = sc.nextLine().split(" ");
            Task newTask = null;
            try {
                if (input[0].equals("bye")) {
                    String goodbyeMessage = "____________________________________________________________\n" +
                            " Bye. Hope to see you again soon!\n" +
                            "____________________________________________________________";
                    System.out.println(goodbyeMessage);
                    break;
                } else if (input[0].equals("list")) {
                    StringBuilder allTasks = new StringBuilder();
                    for (int i = 0; i < tasks.size(); i++) {
                        allTasks.append(String.format(" %d.%s\n", i + 1, tasks.get(i)));
                    }
                    String tasksList = "____________________________________________________________\n" +
                            " Here are the tasks in your list:\n" +
                            allTasks +
                            "____________________________________________________________";
                    System.out.println(tasksList);
                } else if (input[0].equals("mark") || input[0].equals("unmark") || input[0].equals("delete")) {
                    if (tasks.size() == 0) throw new DukeException("OOPS!!! There are currently no tasks.");
                    if (input.length < 2) throw new DukeException("OOPS!!! Please include the task index.");
                    int taskIndex = Integer.parseInt(input[1]) - 1;
                    if (taskIndex < 0 || taskIndex > tasks.size())
                        throw new DukeException("OOPS!!! Task does not exist.");
                    Task chosenTask = tasks.get(taskIndex);
                    String taskStatusMessage = null;
                    if (input[0].equals("mark")) {
                        chosenTask.markAsDone();
                        taskStatusMessage = " Nice! I've marked this task as done:\n" +
                                String.format("  %s\n", chosenTask);
                    } else if (input[0].equals("unmark")) {
                        chosenTask.markAsUndone();
                        taskStatusMessage = " OK, I've marked this task as not done yet:\n" +
                                String.format("  %s\n", chosenTask);
                    } else {
                        tasks.remove(taskIndex);
                        taskStatusMessage = " Noted. I've removed this task:\n" +
                                String.format("  %s\n", chosenTask) +
                                String.format(" Now you have %d tasks in the list.\n", tasks.size());
                    }
                    String finalMessage = "____________________________________________________________\n" +
                            taskStatusMessage +
                            "____________________________________________________________";
                    System.out.println(finalMessage);
                    continue;
                } else if (input[0].equals("todo")) {
                    if (input.length <= 1)
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    String newTaskDesc = String.join(" ", input).substring(input[0].length() + 1);
                    newTask = new Todo(newTaskDesc);
                } else if (input[0].equals("deadline")) {
                    if (input.length <= 1)
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    String taskInput = String.join(" ", input).substring(input[0].length() + 1);
                    int index = taskInput.indexOf("/by");
                    if (index == -1) throw new DukeException("OOPS!!! Please include the deadline.");
                    String taskDesc = taskInput.substring(0, index - 1);
                    String taskDeadline = taskInput.substring(index + 4);
                    newTask = new Deadline(taskDesc, taskDeadline);
                } else if (input[0].equals("event")) {
                    if (input.length <= 1)
                        throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                    String taskInput = String.join(" ", input).substring(input[0].length() + 1);
                    int fromIndex = taskInput.indexOf("/from");
                    int toIndex = taskInput.indexOf("/to");
                    if (fromIndex == -1 || toIndex == -1)
                        throw new DukeException("OOPS!!! Please include the to and from date/time of the event.");
                    String taskDesc = taskInput.substring(0, fromIndex - 1);
                    String taskFrom = taskInput.substring(fromIndex + 6, toIndex - 1);
                    String taskTo = taskInput.substring(toIndex + 4);
                    newTask = new Event(taskDesc, taskFrom, taskTo);
                } else {
                    throw new DukeException();
                }
            } catch (DukeException e) {
                System.out.println(printError(e.getMessage()));
            }
            if (newTask != null) {
                tasks.add(newTask);
                String echoMessage = "____________________________________________________________\n" +
                        " Got it. I've added this task:\n" +
                        String.format("  %s\n", newTask) +
                        String.format(" Now you have %d tasks in the list.\n", tasks.size()) +
                        "____________________________________________________________";
                System.out.println(echoMessage);
            }
        }
    }

    public static String printError(String error) {
        return "____________________________________________________________\n" +
                String.format(" %s\n", error) +
                "____________________________________________________________";
    }
}

