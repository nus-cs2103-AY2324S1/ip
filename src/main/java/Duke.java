import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws InvalidTextException, EmptyDescriptionException, InvalidTaskException, DeadlineUnclearException, DurationUnclearException {
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + "Hello! I'm ChatGP0");
        System.out.println("     " + "What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
        String input = scan.nextLine();
        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + "Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        Task task = list.get(i);
                        System.out.println("     " + (i + 1) + "." + task.toString());
                    }
                    System.out.println("    ____________________________________________________________");
                } else if (input.startsWith("todo ") || (input.startsWith("todo") && input.length() == 4)) {
                    try {
                        if (input.length() <= 5 || input.substring(5).isBlank()) {
                            throw new EmptyDescriptionException();
                        }
                        Task task = new ToDo(input.substring(5));
                        list.add(task);
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + task.toString());
                        System.out.println("     Now you have " + list.size() + " tasks in the list.");
                        System.out.println("    ____________________________________________________________");
                    } catch (EmptyDescriptionException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println("    ____________________________________________________________");
                    }
                } else if (input.startsWith("deadline ") || (input.startsWith("deadline") && input.length() == 8)) {
                    try {
                        if (input.length() <= 9 || input.substring(9).isBlank()) {
                            throw new EmptyDescriptionException();
                        }
                        String[] details = input.substring(9).split(" /by ");
                        if (details.length != 2) {
                            throw new DeadlineUnclearException();
                        }
                        Task task = new Deadline(details[0], details[1]);
                        list.add(task);
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + task.toString());
                        System.out.println("     Now you have " + list.size() + " tasks in the list.");
                        System.out.println("    ____________________________________________________________");
                    } catch (EmptyDescriptionException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The description of a deadline cannot be empty.");
                        System.out.println("    ____________________________________________________________");
                    } catch (DeadlineUnclearException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The deadline is unclear.");
                        System.out.println("    ____________________________________________________________");
                    }
                } else if (input.startsWith("event ") || (input.startsWith("event") && input.length() == 5)) {
                    try {
                        if (input.length() <= 6 || input.substring(6).isBlank()) {
                            throw new EmptyDescriptionException();
                        }
                        String[] details = input.substring(6).split(" /from | /to ");
                        if (details.length != 3 || !input.contains(" /from ") || !input.contains(" /to ")) {
                            throw new DurationUnclearException();
                        }
                        Task task = new Event(details[0], details[1], details[2]);
                        list.add(task);
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + task.toString());
                        System.out.println("     Now you have " + list.size() + " tasks in the list.");
                        System.out.println("    ____________________________________________________________");
                    } catch (EmptyDescriptionException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The description of a event cannot be empty.");
                        System.out.println("    ____________________________________________________________");
                    } catch (DurationUnclearException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The duration is unclear.");
                        System.out.println("    ____________________________________________________________");
                    }
                } else if (input.startsWith("mark ") && input.length() > 5 && input.substring(5).matches("\\d+")) {
                    try {
                        int number = Integer.parseInt(input.substring(5));
                        if (number > list.size() || number <= 0) {
                            throw new InvalidTaskException();
                        }
                        Task task = list.get(number - 1);
                        task.mark();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     " + "Nice! I've marked this task as done:");
                        System.out.println("       " + task.toString());
                        System.out.println("    ____________________________________________________________");
                    } catch (InvalidTaskException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! This task does not exist :O");
                        System.out.println("    ____________________________________________________________\n");
                    }
                } else if (input.startsWith("unmark ") && input.length() > 7 && input.substring(7).matches("\\d+")) {
                    try {
                        int number = Integer.parseInt(input.substring(7));
                        if (number > list.size() || number <= 0) {
                            throw new InvalidTaskException();
                        }
                        Task task = list.get(number - 1);
                        task.unmark();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     " + "OK, I've marked this task as not done yet:");
                        System.out.println("       " + task.toString());
                        System.out.println("    ____________________________________________________________");
                    } catch (InvalidTaskException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! This task does not exist :O");
                        System.out.println("    ____________________________________________________________\n");
                    }
                } else {
                    throw new InvalidTextException();
                }
            } catch (InvalidTextException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("    ____________________________________________________________\n");
            } finally {
                input = scan.nextLine();
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + "Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}