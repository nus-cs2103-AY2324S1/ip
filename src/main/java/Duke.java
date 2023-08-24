import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String bye = " Bye. Hope to see you again soon!";
        String message = "____________________________________________________________\n"
                + " Hello! I'm ChatBot\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        ArrayList<Task> tasksList = new ArrayList<>();
        int count = 0;

        System.out.println(message);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            try {
                String input = scanner.nextLine();
                System.out.println("____________________________________________________________");
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(bye);
                    System.out.println("____________________________________________________________");
                    break;

                } else if (input.equalsIgnoreCase("list")) {
                    System.out.println("Here are the tasks in your list:");
                    if (count == 0) {
                        System.out.println("\t You currently have no tasks!");
                    } else {
                        for (int i = 0; i < count; i++) {
                            System.out.println((i + 1) + "." + tasksList.get(i).toString());
                        }
                    }

                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("mark")) {
                    tasksList.get(Integer.parseInt(input.replace("mark ", "")) - 1).markDone();
                } else if (input.startsWith("unmark")) {
                    tasksList.get(Integer.parseInt(input.replace("unmark ", "")) - 1).unmarkDone();
                } else {
                    if(input.equalsIgnoreCase("todo")) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    } else if(input.equalsIgnoreCase("deadline")) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    } else if(input.equalsIgnoreCase("event")) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }

                    if (input.startsWith("todo")) {
                        tasksList.add(new Todo(input.replace("todo ", "")));
                        count++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println("\t" + tasksList.get(count - 1).toString());
                        System.out.println("Now you have " + count + " tasks in the list");
                        System.out.println("____________________________________________________________");

                    } else if (input.startsWith("event")) {
                        String[] s = input.replace("event ", "").split(" /from | /to");
                        tasksList.add(new Event(s[0], s[1], s[2]));
                        count++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println("\t" + tasksList.get(count - 1).toString());
                        System.out.println("Now you have " + count + " tasks in the list");
                        System.out.println("____________________________________________________________");

                    } else if (input.startsWith("deadline")) {
                        String[] s = input.replace("deadline ", "").split(" /by ");
                        tasksList.add(new Deadline(s[0], s[1]));
                        count++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println("\t" + tasksList.get(count - 1).toString());
                        System.out.println("Now you have " + count + " tasks in the list");
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}


