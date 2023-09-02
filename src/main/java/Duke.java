import Models.Deadline;
import Models.Event;
import Models.ToDo;
import Exceptions.DukeInvalidFormatException;
import Models.TaskArray;

import java.util.Objects;
import java.util.Scanner;

import static Printers.BasicOutputPrinter.printBasicOutput;
import static Printers.ErrorOutputPrinter.printErrorOutput;

public class Duke {
    public static void main(String[] args) {
        TaskArray tasks = new TaskArray();

        String introduction = " Hello! I'm eggbot\n" +
                " Please add a task!\n\n" +
                " To add a ToDo task, type 'todo [Task]'\n" +
                " To add a Deadline task, type 'deadline [Task /Deadline]'\n" +
                " To add an Event task, type 'event [Task /Start Date/End Date]'\n" +
                " To view tasks, type 'list' \n" +
                " To mark a task as 'done', type 'mark [index]' \n" +
                " To mark a task as 'undone', type 'unmark [index]' \n" +
                " To delete a task, type 'delete [index]' \n" +
                " To exit, type 'bye'";

        printBasicOutput(introduction);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please add a task, or type list to show tasks.");

            String input = scanner.nextLine().strip();  // Read input

            if (Objects.equals(input, "bye")) {
                printBasicOutput("Bye. Hope to see you again soon!");

                break;
            } else if (Objects.equals(input, "list")) {

                if (tasks.isEmpty()) {
                    printBasicOutput("You have no tasks!");

                    continue;
                }
                
                String output = "Your tasks: \n" + tasks;
                printBasicOutput(output);

            } else if (input.startsWith("mark")) {
                if (input.equals("mark")) {
                    printErrorOutput("You cannot mark an empty task!");

                    continue;
                }
                int index = Integer.parseInt(input.substring(input.length() - 1)) - 1;

                if (index >= tasks.size() || index < 0) {
                    printErrorOutput("Task " + (index + 1) + " not found!");

                    continue;
                }

                tasks.markTask(index);

                String output = "Nice! I've marked this task as done: \n" +
                        tasks.get(index);

                printBasicOutput(output);

            } else if (input.startsWith("unmark")) {
                if (input.equals("unmark")) {
                    printErrorOutput("You cannot unmark an empty task!");

                    continue;
                }

                int index = Integer.parseInt(input.substring(input.length() - 1)) - 1;

                if (index >= tasks.size()) {
                    printErrorOutput("Task " + (index + 1) + " not found!");

                    continue;
                }

                tasks.unmarkTask(index);

                String output = "OK, I've marked this task as not done yet: \n" +
                        tasks.get(index);

                printBasicOutput(output);
            } else if (input.startsWith("todo")) {
                if (input.equals("todo")) {
                    printErrorOutput("You cannot add an empty 'ToDo' task!");

                    continue;
                }

                String taskName = input.substring(4).strip();
                tasks.addTask(new ToDo(taskName, false));

                String output = "Got it, I've added this task: \n" +
                        tasks.get(tasks.size() - 1) + "\n" +
                        "You now have " + tasks.size() + " tasks in the list.";

                printBasicOutput(output);

            } else if (input.startsWith("deadline")) {
                if (input.equals("deadline")) {
                    printErrorOutput("You cannot add an empty 'Deadline' task!");

                    continue;
                }

                try {
                    String task = input.substring(8).strip();
                    String[] taskArr = task.split("/", 2);

                    if (taskArr.length != 2) {
                        String errorStr = "\nFormat for Deadline task incorrect!\n" +
                                "Expected 2 parts (Task name & deadline) in input, got " + taskArr.length;
                        throw new DukeInvalidFormatException(errorStr);
                    }

                    tasks.addTask(new Deadline(taskArr[0].strip(), false, taskArr[1].strip()));

                } catch (DukeInvalidFormatException e) {
                    String errorString = "Something went wrong! Please format the task properly and add it again. \n" +
                            "Error: " + e;

                    printErrorOutput(errorString);
                    continue;
                }

                String output = "Got it, I've added this task: \n" +
                        tasks.get(tasks.size() - 1) + "\n" +
                        "You now have " + tasks.size() + " tasks in the list";

                printBasicOutput(output);

            } else if (input.startsWith("event")) {
                if (input.equals("event")) {
                    printErrorOutput("You cannot add an empty 'Event' task!");

                    continue;
                }

                try {
                    String task = input.substring(5).strip();
                    String[] taskArr = task.split("/", 3);

                    if (taskArr.length != 3) {
                        String errorStr = "\nFormat for Event task incorrect!\n" +
                                "Expected 3 parts (Task name, start & end time) in input, got " + taskArr.length;
                        throw new DukeInvalidFormatException(errorStr);
                    }

                    tasks.addTask(new Event(taskArr[0].strip(), false, taskArr[1].strip(), taskArr[2].strip()));

                } catch (DukeInvalidFormatException e) {
                    String errorString = "Something went wrong! Please format the task properly and add it again. \n\n" +
                            "Error: " + e;

                    printErrorOutput(errorString);
                    continue;
                }

                String output = "Got it, I've added this task: \n" +
                        tasks.get(tasks.size() - 1) + "\n" +
                        "You now have " + tasks.size() + " tasks in the list.";

                printBasicOutput(output);

            } else if (input.startsWith("delete")) {
                if (input.equals("delete")) {
                    printErrorOutput("Please specify a task to delete!");

                    continue;
                }

                try {
                    int index = Integer.parseInt(input.substring(6).strip()) - 1;

                    if (index >= tasks.size()) {
                        printErrorOutput("Task " + (index + 1) + " not found!");

                        continue;
                    }

                    String output = "Noted, I've removed this task: \n" +
                            tasks.get(index) + "\n" +
                            "You now have " + (tasks.size() - 1) + " tasks in the list.";

                    printBasicOutput(output);

                    tasks.deleteTask(index);

                } catch (java.lang.NumberFormatException e) {
                    String errorString = "Something went wrong! Please make sure you are deleting a valid task. \n\n" +
                            "Error: " + e;

                    printErrorOutput(errorString);
                }

            } else {
                printErrorOutput("Not a valid command!");
            }
        }

    }
}
