import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TaskArray tasks = new TaskArray("src/data/memory.txt");
        int taskCount = 0;

        String introduction = "____________________________________________________________\n" +
                " Hello! I'm eggbot\n" +
                " Please add a task!\n\n" +
                " To add a ToDo task, type 'todo [Task]'\n" +
                " To add a Deadline task, type 'deadline [Task /Deadline]'\n" +
                " To add an Event task, type 'event [Task /Start Date/End Date]'\n" +
                " To view tasks, type 'list' \n" +
                " To mark a task as 'done', type 'mark [index]' \n" +
                " To mark a task as 'undone', type 'unmark [index]' \n" +
                " To delete a task, type 'delete [index]' \n" +
                " To exit, type 'bye' \n" +
                "____________________________________________________________\n";

        System.out.println(introduction);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please add a task, or type list to show tasks.");

            String input = scanner.nextLine().strip();  // Read input

            if (Objects.equals(input, "bye")) {
                String output = "____________________________________________________________\n" +
                        "Bye. Hope to see you again soon! \n" +
                        "____________________________________________________________\n";

                System.out.println(output);
                break;
            } else if (Objects.equals(input, "list")) {

                if (taskCount == 0) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println("You have no tasks!");
                    System.out.println("____________________________________________________________\n");

                    continue;
                }
                
                String output = "____________________________________________________________\n" +
                        "Your tasks: \n";

//                System.out.println(output);
//
//                for (int i = 0; i < taskCount; i++) {
//                    String entryOutput = (i + 1) + ". " + tasks.get(i);
//                    if (tasks.get(i).isDone()) {
//                        entryOutput = (i + 1) + ". " + tasks.get(i);
//                    }
//                    System.out.println(entryOutput);
//                }
                tasks.displayTasks();

                System.out.println("____________________________________________________________\n");

            } else if (input.startsWith("mark")) {
                if (input.equals("mark")) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println("You cannot mark an empty task!");
                    System.out.println("____________________________________________________________\n");

                    continue;
                }
                int index = Integer.parseInt(input.substring(input.length() - 1)) - 1;

                if (index >= taskCount || index < 0) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println("Task " + (index + 1) + " not found!");
                    System.out.println("____________________________________________________________\n");

                    continue;
                }

                tasks.get(index).markAsDone();

                String output = "Nice! I've marked this task as done: \n" +
                        tasks.get(index);

                System.out.println("____________________________________________________________\n");
                System.out.println(output);
                System.out.println("____________________________________________________________\n");

            } else if (input.startsWith("unmark")) {
                if (input.equals("unmark")) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println("You cannot unmark an empty task!");
                    System.out.println("____________________________________________________________\n");

                    continue;
                }

                int index = Integer.parseInt(input.substring(input.length() - 1)) - 1;

                if (index >= taskCount) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println("Task " + (index + 1) + " not found!");
                    System.out.println("____________________________________________________________\n");

                    continue;
                }

                tasks.get(index).markAsUndone();

                String output = "OK, I've marked this task as not done yet: \n" +
                        tasks.get(index);

                System.out.println("____________________________________________________________\n");
                System.out.println(output);
                System.out.println("____________________________________________________________\n");
            } else if (input.startsWith("todo")) {
                if (input.equals("todo")) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println("You cannot add an empty 'ToDo' task!");
                    System.out.println("____________________________________________________________\n");

                    continue;
                }

                String taskName = input.substring(4).strip();
                tasks.addTask(new ToDo(taskName, false));
                taskCount += 1;

                String output = "____________________________________________________________\n" +
                        "Got it, I've added this task: \n" +
                        tasks.get(taskCount - 1) + "\n" +
                        "You now have " + taskCount + " tasks in the list. \n" +
                        "____________________________________________________________\n";

                System.out.println(output);

            } else if (input.startsWith("deadline")) {
                if (input.equals("deadline")) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println("You cannot add an empty 'Deadline' task!");
                    System.out.println("____________________________________________________________\n");

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
                    taskCount += 1;

                } catch (DukeInvalidFormatException e) {
                    String errorString = "____________________________________________________________\n" +
                            "Something went wrong! Please format the task properly and add it again. \n" +
                            "Error: " + e + "\n" +
                            "____________________________________________________________\n";

                    System.out.println(errorString);
                    continue;
                }

                String output = "____________________________________________________________\n" +
                        "Got it, I've added this task: \n" +
                        tasks.get(taskCount - 1) + "\n" +
                        "You now have " + taskCount + " tasks in the list. \n" +
                        "____________________________________________________________\n";

                System.out.println(output);

            } else if (input.startsWith("event")) {
                if (input.equals("event")) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println("You cannot add an empty 'Event' task!");
                    System.out.println("____________________________________________________________\n");

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
                    taskCount += 1;
                } catch (DukeInvalidFormatException e) {
                    String errorString = "____________________________________________________________\n" +
                            "Something went wrong! Please format the task properly and add it again. \n\n" +
                            "Error: " + e + "\n" +
                            "____________________________________________________________\n";

                    System.out.println(errorString);
                    continue;
                }

                String output = "____________________________________________________________\n" +
                        "Got it, I've added this task: \n" +
                        tasks.get(taskCount - 1) + "\n" +
                        "You now have " + taskCount + " tasks in the list. \n" +
                        "____________________________________________________________\n";

                System.out.println(output);

            } else if (input.startsWith("delete")) {
                if (input.equals("delete")) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println("Please specify a task to delete!");
                    System.out.println("____________________________________________________________\n");

                    continue;
                }

                try {
                    int index = Integer.parseInt(input.substring(6).strip()) - 1;

                    if (index >= taskCount) {
                        System.out.println("____________________________________________________________\n");
                        System.out.println("Task " + (index + 1) + " not found!");
                        System.out.println("____________________________________________________________\n");

                        continue;
                    }

                    String output = "____________________________________________________________\n" +
                            "Noted, I've removed this task: \n" +
                            tasks.get(index) + "\n" +
                            "You now have " + (taskCount - 1) + " tasks in the list. \n" +
                            "____________________________________________________________\n";

                    System.out.println(output);

                    tasks.deleteTask(index);
                    taskCount -= 1;

                } catch (java.lang.NumberFormatException e) {
                    String errorString = "____________________________________________________________\n" +
                            "Something went wrong! Please make sure you are deleting a valid task. \n\n" +
                            "Error: " + e + "\n" +
                            "____________________________________________________________\n";

                    System.out.println(errorString);
                }

            } else {
                String output = "____________________________________________________________\n" +
                        "Not a valid command! \n" +
                        "____________________________________________________________\n";

                System.out.println(output);
            }
        }

    }
}
