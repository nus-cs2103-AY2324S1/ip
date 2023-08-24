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
                printHorizontalLine();
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 1; i <= count; i++) {
                    System.out.println("\t" + i + ". " + tasks.get(i-1).toString());
                }
                printHorizontalLine();
            }

            // adding task to list
            else {
                if (input.startsWith("todo")) {
                Task task = new ToDo(input.substring(5));
                tasks.add(task);
                System.out.println("\tOk you have added this task:" );
                System.out.println("\t" + task.toString());
                System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
                count++;
             }
                else if (input.startsWith("deadline")) {
                    int index = input.lastIndexOf("/by");
                    Task task = new Deadline(input.substring(9, index-1), input.substring(index+4));
                    tasks.add(task);
                    System.out.println("\tOk you have added this task:" );
                    System.out.println("\t" + task.toString());
                    System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
                    count++;
                }
                else if (input.startsWith("event")) {
                    int indexFrom = input.lastIndexOf("/from");
                    int indexTo = input.lastIndexOf("/to");
                    Task task =  new Event(input.substring(6, indexFrom-1),
                            input.substring(indexFrom+6, indexTo-1), input.substring(indexTo+4));
                    tasks.add(task);
                    System.out.println("\tOk I have added this task:" );
                    System.out.println("\t" + task.toString());
                    System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
                    count++;
                }

                else {
                    printHorizontalLine();
                    System.out.println("\t" + "added: " + input);
                    tasks.add(new Task(input));
                    count++;
                    printHorizontalLine();
                }
            }
        }

    }

    public static void printHorizontalLine() {
        System.out.println("    ________________________________________________");
    }


}