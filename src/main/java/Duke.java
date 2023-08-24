import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Hello! I am Nila");
        System.out.println("What can I do for you?");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        ArrayList<Task> tasklist = new ArrayList<Task>();

        while (!str.equals("bye")) {
            String[] input = str.split(" ", 2);

            try {
                if (str.equals("todo") || str.equals("deadline") || str.equals("event")) {
                    throw new DukeException("OOPS!!! The description of a " + str + " cannot be empty.");
                } else if (str.equals("mark") || str.equals("unmark") || str.equals("delete")) {
                    throw new DukeException("OOPS!!! Please specify which task you want to mark, unmark or delete .");
                }

                else if (input[0].equals("list")){
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");

                    System.out .println(" Here are the tasks in your list:");
                    int x = 1;
                    for (Task task : tasklist) {
                        System.out.println(x + "." + task.getStatusIcon());
                        x++;
                    }
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                    str = sc.nextLine();
                } else if (input[0].equals("mark")) {
                    int index = Integer.parseInt(input[1]) - 1;
                    tasklist.get(index).markAsDone();
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println(" Nice! I've marked this task as done:\n" + "   " + tasklist.get(index).getStatusIcon());
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                    str = sc.nextLine();
                } else if (input[0].equals("unmark")) {
                    int index = Integer.parseInt(input[1]) - 1;
                    tasklist.get(index).unmark();
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println(" OK, I've marked this task as not done yet:\n" + "   " + tasklist.get(index).getStatusIcon());
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                    str = sc.nextLine();
                } else if (input[0].equals("todo")) {
                    String description = input[1];
                    tasklist.add(new Todo(description));
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println(" Got it. I've added this task:\n" + "   " + tasklist.get(tasklist.size() - 1).getStatusIcon());
                    System.out.println(" Now you have " + tasklist.size() +" tasks in the list.");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                    str = sc.nextLine();
                } else if (input[0].equals("deadline")) {
                    String[] full_desc = input[1].split(" /by ");
                    String description = full_desc[0];
                    String by = full_desc[1];
                    tasklist.add(new Deadline(description, by));
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println(" Got it. I've added this task:\n" + "   " + tasklist.get(tasklist.size() - 1).getStatusIcon());
                    System.out.println(" Now you have " + tasklist.size() +" tasks in the list.");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                    str = sc.nextLine();
                } else if (input[0].equals("event")) {
                    String[] full_desc = input[1].split(" /from | /to ");
                    String description = full_desc[0];
                    String from = full_desc[1];
                    String to = full_desc[2];
                    tasklist.add(new Event(description, from, to));
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println(" Got it. I've added this task:\n" + "   " + tasklist.get(tasklist.size() - 1).getStatusIcon());
                    System.out.println(" Now you have " + tasklist.size() +" tasks in the list.");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                    str = sc.nextLine();
                } else if (input[0].equals("delete")) {
                    int index = Integer.parseInt(input[1]) - 1;
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println(" Noted. I've removed this task:\n" + "   " + tasklist.get(index).getStatusIcon());
                    tasklist.remove(index);
                    System.out.println(" Now you have " + tasklist.size() + " tasks in the list.");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                    str = sc.nextLine();
                }

                else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println(e.getMessage());
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                str = sc.nextLine();
            }
        }

        sc.close();
        System.out.println("\nBye. Hope to see you again soon!");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
    }
}
