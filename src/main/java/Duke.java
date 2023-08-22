// do not know how to use DukeException as of 22/8/2023
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) throws DukeException {
        // welcome message
        String line = "_".repeat(40);
        ArrayList<Task> list = new ArrayList<>();
        String name = "DukeKing";
        String welcome = "\nHello! I'm " + name + "\nWhat can I do for you?";
        System.out.println(line + welcome + "\n" + line);

        // setting up
        String output = "";
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();

        //looping in the program
        while (true) {
            // end the program
            if (string.equals("bye")) {
                break;

                // reading the list
            } else if (string.equals("list")) {
                    System.out.println(line);
                    String openingList = "Here are the tasks in your list:";
                    System.out.println(openingList);
                    for (int length = 1; length < list.size() + 1; length += 1) {
                        System.out.println(length + "." + list.get(length - 1));
                    }
                // marking the task to the list
            } else if (string.startsWith("mark")) {
                try {
                    String[] splittedInput = string.split(" ");
                    int taskNumber = Integer.parseInt(splittedInput[1]);
                    if (!list.get(taskNumber - 1).isDone) {
                        list.get(taskNumber - 1).markAsDone();
                        String markingTask = "Nice! I've marked this task as done:";
                        output = String.format("%s\n%s\n%s", line, markingTask, list.get(taskNumber - 1));
                        System.out.println(output);
                    } else {
                        output = String.format("%s\n%s\n%s", line, "This task is already done");
                        System.out.println(output);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(line + "\nOOPS!!! Must choose something to unmark.");
                } catch (NullPointerException e) {
                    System.out.println(line + "\nOOPS!!! You chose air.");
                }
                // unmarking the task from the list
            } else if (string.startsWith("unmark")) {
                try {
                    String[] splittedInput = string.split(" ");
                    int taskNumber = Integer.parseInt(splittedInput[1]);
                    if (list.get(taskNumber - 1).isDone) {
                        list.get(taskNumber - 1).markAsUndone();
                        String unMarkingTask = "OK, I've marked this task as not done yet:";
                        output = String.format("%s\n%s\n%s", line, unMarkingTask, list.get(taskNumber - 1));
                        System.out.println(output);
                    } else {
                        output = String.format("%s\n%s\n%s", line, "This task is not done yet");
                        System.out.println(output);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(line + "\nOOPS!!! Must choose something to unmark.");
                } catch (NullPointerException e) {
                    System.out.println(line + "\nOOPS!!! You chose air.");
                }
                // if task is a todo
            } else if (string.startsWith("todo")) {
                String addingTask = "Got it. I've added this task:";
                int noOfTask = list.size() + 1;
                String numberOfTask = "Now you have " + noOfTask + " tasks in the list.";
                String[] splittedInput = string.split(" ");
                if (splittedInput.length == 1) {
                    System.out.println(line + "\nOOPS!!! The description of a todo cannot be empty.");
                } else {
                    String task = string.replace("todo ", "");
                    Task currentTask = new Todo(task);
                    list.add(currentTask);
                    output = String.format("%s\n%s\n  %s\n%s", line, addingTask, currentTask, numberOfTask);
                    System.out.println(output);
                }
                // if task is a dateline
            } else if (string.startsWith("deadline")) {
                String addingTask = "Got it. I've added this task:";
                int noOfTask = list.size() + 1;
                String numberOfTask = "Now you have " + noOfTask + " tasks in the list.";
                String[] splittedInput = string.split(" ");
                if (splittedInput.length == 1) {
                    System.out.println(line + "\nOOPS!!! The description of a deadline cannot be empty.");
                } else {
                    String task = string.replace("deadline ", "");
                    String[] splittedTask = task.split(" /by ");
                    if (splittedTask.length == 1) {
                        System.out.println(line + "\nOOPS!!! The deadline of a deadline cannot be empty.");
                    } else {
                        String taskName = splittedTask[0];
                        String end = splittedTask[1];
                        Task currentTask = new Deadlines(taskName, end);
                        list.add(currentTask);
                        output = String.format("%s\n%s\n  %s\n%s", line, addingTask, currentTask, numberOfTask);
                        System.out.println(output);
                    }
                }
                // if task is an event
            } else if (string.startsWith("event")) {
                String addingTask = "Got it. I've added this task:";
                int noOfTask = list.size() + 1;
                String numberOfTask = "Now you have " + noOfTask + " tasks in the list.";
                String[] splittedInput = string.split(" ");
                if (splittedInput.length == 1) {
                    System.out.println(line + "\nOOPS!!! The description of a event cannot be empty.");
                } else {
                    String task = string.replace("event ", "");
                    String[] splitStart = task.split(" /from ");
                    if (splitStart.length == 1) {
                        System.out.println(line + "\nOOPS!!! The start of a event cannot be empty.");
                    } else {
                        String taskName = splitStart[0];
                        String[] splitEnd = splitStart[1].split(" /to ");
                        if (splitEnd.length == 1) {
                            System.out.println(line + "\nOOPS!!! The end of a event cannot be empty.");
                        } else {
                            String start = splitEnd[0];
                            String end = splitEnd[1];
                            Task currentTask = new Events(taskName, start, end);
                            list.add(currentTask);
                            output = String.format("%s\n%s\n  %s\n%s", line, addingTask, currentTask, numberOfTask);
                            System.out.println(output);
                        }
                    }
                }
            } else if (string.startsWith("delete")) {
                String[] splittedInput = string.split(" ");
                int taskNumber = Integer.parseInt(splittedInput[1]);
                String deletingTask = "Noted. I've removed this task:";
                int taskInArray = list.size() - 1;
                Task removedTask = list.remove(taskNumber - 1);
                String numberOfTask = "Now you have " + taskInArray + " tasks in the list.";
                output = String.format("%s\n%s\n  %s\n%s", line, deletingTask, removedTask, numberOfTask);
                System.out.println(output);
            } else {
                System.out.println(line + "\nOOPS!!! I'm sorry, but I don't know what that means :-C");
            }

            System.out.println(line);
            string = sc.nextLine();
        }
        System.out.println(line + "\nBye. Hope to see you again soon!" + "\n" + line);
    }
}
