// fixing DukeException based on my understanding of exceptions 27/8/23
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public enum Activity {
        bye, list, mark, unmark, todo, deadline, event, delete,
    }
    public static void main(String[] args) {
        // welcome message
        ArrayList<Task> list = new ArrayList<>();
        String name = "DukeKing";
        String welcome = "Hello! I'm " + name + "\nWhat can I do for you?";
        printLine();
        System.out.println(welcome);
        printLine();

        // setting up
        String output = "";
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();

        //looping in the program
        while (true) {
            // end the program
            try {
                if (string.equals(Activity.bye.name())) {
                    break;

                    // reading the list
                } else if (string.equals(Activity.list.name())) {
                    printLine();
                    printList(list);
                    // marking the task to the list
                } else if (string.startsWith(Activity.mark.name())) {
                    try {
                        String[] splittedInput = string.split(" ");
                        int taskNumber = Integer.parseInt(splittedInput[1]);
                        if (!list.get(taskNumber - 1).isDone) {
                            list.get(taskNumber - 1).markAsDone();
                            String markingTask = "Nice! I've marked this task as done:";
                            printLine();
                            output = String.format("%s\n%s",markingTask, list.get(taskNumber - 1));
                            System.out.println(output);
                        } else if (list.get(taskNumber - 1).isDone) {
                            printLine();
                            throw new WrongMarkException("This task is already done.");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printLine();
                        System.out.println("OOPS!!! Must choose something to unmark.");
                    } catch (NullPointerException e) {
                        printLine();
                        System.out.println("OOPS!!! You chose air.");
                    }
                    // unmarking the task from the list
                } else if (string.startsWith(Activity.unmark.name())) {
                    try {
                        String[] splittedInput = string.split(" ");
                        int taskNumber = Integer.parseInt(splittedInput[1]);
                        if (list.get(taskNumber - 1).isDone) {
                            list.get(taskNumber - 1).markAsUndone();
                            String unMarkingTask = "OK, I've marked this task as not done yet:";
                            printLine();
                            output = String.format("%s\n%s",unMarkingTask, list.get(taskNumber - 1));
                            System.out.println(output);
                        } else {
                            printLine();
                            throw new WrongMarkException("This task is not done yet.");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printLine();
                        System.out.println("OOPS!!! Must choose something to unmark.");
                    } catch (NullPointerException e) {
                        printLine();
                        System.out.println("OOPS!!! You chose air.");
                    }
                    // if task is a todo
                } else if (string.startsWith(Activity.todo.name())) {
                    String addingTask = "Got it. I've added this task:";
                    int noOfTask = list.size() + 1;
                    String numberOfTask = "Now you have " + noOfTask + " tasks in the list.";
                    String[] splittedInput = string.split(" ");
                    if (splittedInput.length == 1) {
                        printLine();
                        throw new EmptyDetailsOfTaskError("The description of a todo cannot be empty.");
                    } else {
                        String task = string.replace("todo ", "");
                        Task currentTask = new Todo(task);
                        list.add(currentTask);
                        printLine();
                        output = String.format("%s\n  %s\n%s", addingTask, currentTask, numberOfTask);
                        System.out.println(output);
                    }
                    // if task is a dateline
                } else if (string.startsWith(Activity.deadline.name())) {
                    String addingTask = "Got it. I've added this task:";
                    int noOfTask = list.size() + 1;
                    String numberOfTask = "Now you have " + noOfTask + " tasks in the list.";
                    String[] splittedInput = string.split(" ");
                    if (splittedInput.length == 1) {
                        printLine();
                        throw new EmptyDetailsOfTaskError("The description of a deadline cannot be empty.");
                    } else {
                        String task = string.replace("deadline ", "");
                        String[] splittedTask = task.split(" /by ");
                        if (splittedTask.length == 1) {
                            printLine();
                            throw new EmptyDetailsOfTaskError("The end of a deadline cannot be empty.");
                        } else {
                            String taskName = splittedTask[0];
                            String end = splittedTask[1];
                            Task currentTask = new Deadlines(taskName, end);
                            list.add(currentTask);
                            printLine();
                            output = String.format("%s\n  %s\n%s", addingTask, currentTask, numberOfTask);
                            System.out.println(output);
                        }
                    }
                    // if task is an event
                } else if (string.startsWith(Activity.event.name())) {
                    String addingTask = "Got it. I've added this task:";
                    int noOfTask = list.size() + 1;
                    String numberOfTask = "Now you have " + noOfTask + " tasks in the list.";
                    String[] splittedInput = string.split(" ");
                    if (splittedInput.length == 1) {
                        printLine();
                        throw new EmptyDetailsOfTaskError("The description of a event cannot be empty.");
                    } else {
                        String task = string.replace("event ", "");
                        String[] splitStart = task.split(" /from ");
                        if (splitStart.length == 1) {
                            printLine();
                            throw new EmptyDetailsOfTaskError("The start of a event cannot be empty.");
                        } else {
                            String taskName = splitStart[0];
                            String[] splitEnd = splitStart[1].split(" /to ");
                            if (splitEnd.length == 1) {
                                printLine();
                                throw new EmptyDetailsOfTaskError("The end of a event cannot be empty.");
                            } else {
                                String start = splitEnd[0];
                                String end = splitEnd[1];
                                Task currentTask = new Events(taskName, start, end);
                                list.add(currentTask);
                                printLine();
                                output = String.format("%s\n  %s\n%s", addingTask, currentTask, numberOfTask);
                                System.out.println(output);
                            }
                        }
                    }
                } else if (string.startsWith(Activity.delete.name())) {
                    String[] splittedInput = string.split(" ");
                    int taskNumber = Integer.parseInt(splittedInput[1]);
                    printLine();
                    deleteTask(list, taskNumber);
                } else {
                    printLine();
                    throw new UnknownCommandException("I'm sorry, but I don't know what that means :-C");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            printLine();
            string = sc.nextLine();
        }
        // end the program
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    public static void printList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int length = 1; length < list.size() + 1; length += 1) {
            System.out.println(length + "." + list.get(length - 1));
        }
    }

    public static void deleteTask(ArrayList<Task> list, int taskNumber) {
        String deletingTask = "Noted. I've removed this task:";
        int taskInArray = list.size() - 1;
        Task removedTask = list.remove(taskNumber - 1);
        String numberOfTask = "Now you have " + taskInArray + " tasks in the list.";
        String output = String.format("%s\n  %s\n%s", deletingTask, removedTask, numberOfTask);
        System.out.println(output);
    }

    public static void printLine() {
        System.out.println("_".repeat(40));
    }
}
