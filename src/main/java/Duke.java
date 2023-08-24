import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        // an array list of tasks
        List<Task> tasks = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        printHorizontalLine();
        System.out.println("\t " + "Hello! I'm Buddy! \n" +
                "\t " + "What can I do for you? ");
        printHorizontalLine();
        int count = 0;
        while (count <=100 ) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("\t " + "Bye! Hope to see you again soon! ");
                printHorizontalLine();
                break;
            }

            //marking task
            else if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.substring(5));
                tasks.get(taskIndex - 1).markAsDone();
                printHorizontalLine();
                System.out.println("\tGreat! I've marked this task as done:");
                System.out.println("\t" + taskIndex + "." + tasks.get(taskIndex-1).toString());
                printHorizontalLine();

            }

            //unmarking task
            else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.substring(7));
                tasks.get(taskIndex - 1).markAsNotDone();
                printHorizontalLine();
                System.out.println("\tOk! I've marked this task as not done yet:");
                System.out.println("\t" + taskIndex + "." + tasks.get(taskIndex-1).toString());
                printHorizontalLine();

            }

            //displaying list
            else if (input.equals("list")) {
                try {
                    printHorizontalLine();
                    System.out.println("\tHere are the tasks in your list:");
                    if (tasks.size() == 0) {
                         throw new DukeException("\tThis list is empty!");
                    }
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println("\t" + i + ". " + tasks.get(i - 1).toString());
                    }
                    printHorizontalLine();
                }
                catch (DukeException e) {
                    e.printMessage();
                }
            }

            else if (input.startsWith("delete ")) {
                try {
                    int pos = 0;
                    pos = Integer.parseInt(input.substring(7).trim());
                    if (pos > tasks.size()) {
                        throw new DukeException("\tThis number is out of bounds");
                    }
                    Task element = tasks.get(pos - 1);
                    tasks.remove(pos - 1);
                    printHorizontalLine();
                    System.out.println("\tOkie I've removed this task:\n\t" + element.toString());
                    System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
                    printHorizontalLine();
                }
                catch (DukeException e) {
                    e.printMessage();
                }
            }


            // adding task to list
            else {
                try {
                    if (input.startsWith("todo")) {
                        if (input.trim().length() <= 4) {
                            throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty");
                        }
                        Task task = new ToDo(input.substring(5));
                        tasks.add(task);
                        printHorizontalLine();
                        System.out.println("\tOk you have added this task:");
                        System.out.println("\t" + task.toString());
                        System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
                        printHorizontalLine();
                        count++;
                    }

                    else if (input.startsWith("deadline")) {
                        if (input.trim().length() <= 8) {
                            throw new DukeException("\t☹ OOPS!!! The description of a deadline cannot be empty");
                        }
                        int index = input.lastIndexOf("/by");
                        Task task = new Deadline(input.substring(9, index - 1), input.substring(index + 4));
                        tasks.add(task);
                        printHorizontalLine();
                        System.out.println("\tOk you have added this task:");
                        System.out.println("\t" + task.toString());
                        System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
                        printHorizontalLine();
                        count++;
                    }

                    else if (input.startsWith("event")) {
                        if (input.trim().length() <= 5) {
                            throw new DukeException("\t☹ OOPS!!! The description of an event cannot be empty");
                        }
                        int indexFrom = input.lastIndexOf("/from");
                        int indexTo = input.lastIndexOf("/to");
                        Task task = new Event(input.substring(6, indexFrom - 1),
                                input.substring(indexFrom + 6, indexTo - 1), input.substring(indexTo + 4));
                        tasks.add(task);
                        printHorizontalLine();
                        System.out.println("\tOk I have added this task:");
                        System.out.println("\t" + task.toString());
                        System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
                        printHorizontalLine();
                        count++;
                    }

                    else {
                        throw new DukeException("\tHey bud! Sorry I don't quite know what you mean :-(");
                    }
                }
                catch (DukeException e){
                    e.printMessage();
                }
            }
        }

    }

    public static void printHorizontalLine() {
        System.out.println("    ________________________________________________");
    }


}