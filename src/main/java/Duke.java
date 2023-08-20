import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        int number = 0;

        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Muddy\n" + "What can I do for you?");

        while (true) {
            try {
                String input = in.nextLine();
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    if (number == 0) {
                        System.out.println("List is empty");
                        continue;
                    } else {
                        System.out.println("List:");
                        for (int i = 0; i < number; i++) {
                            Task item = list.get(i);
                            System.out.println((i + 1) + ". " + item.toString());
                        }
                    }

                } else if (input.startsWith("mark")) {
                    try {
                        int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
                        if (taskIndex >= 0 && taskIndex < number) {
                            Task item = list.get(taskIndex);
                            item.markAsDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(item.toString());
                        } else {
                            System.out.println("You have chosen an invalid task");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please provide a valid task number.");
                    }

                } else if (input.startsWith("unmark")) {
                    try {
                        int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                        if (taskIndex >= 0 && taskIndex < number) {
                            Task item = list.get(taskIndex);
                            item.markAsNotDone();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(item.toString());
                        } else {
                            System.out.println("You have chosen an invalid task");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please provide a valid task number.");
                    }
                } else if (input.startsWith("delete")) {
                    try {
                        int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                        if (taskIndex >= 0 && taskIndex < number) {
                            Task item = list.get(taskIndex);
                            list.remove(taskIndex);
                            number--;
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(item.toString());
                            System.out.println("Now you have " + number + " tasks in the list");
                        } else {
                            System.out.println("You have chosen an invalid task");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please provide a valid task number.");
                    }
                }
                else if (input.equalsIgnoreCase("todo")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                } else if (input.equalsIgnoreCase("deadline")) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                } else if (input.equalsIgnoreCase("event")) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                } else if (input.startsWith("todo")) {
                    String task = input.substring(5).trim();
                    ToDo todo = new ToDo(task);
                    list.add(todo);
                    number++;
                    System.out.println("Got it. I've added this task:\n" + todo.toString());
                    System.out.println("Now you have " + (number) + " tasks in the list.");

                } else if (input.startsWith("deadline")) {
                    int byIndex = input.indexOf("/by");
                    if (byIndex != -1) {
                        String task = input.substring(9, byIndex).trim(); // Task description
                        String date = input.substring(byIndex + 3).trim(); // Deadline day

                        Deadline deadline = new Deadline(task, date);
                        list.add(deadline);
                        number++;
                        System.out.println("Got it. I've added this task:\n" + deadline.toString());
                        System.out.println("Now you have " + (number) + " tasks in the list.");
                    } else {
                        throw new DukeException("Invalid input format.");
                    }

                } else if (input.startsWith("event")) {
                    int fromIndex = input.indexOf("/from");
                    int toIndex = input.indexOf("/to");
                    if (fromIndex != -1 && toIndex != -1) {
                        String task = input.substring(6, fromIndex).trim(); // Task description
                        String startDate = input.substring(fromIndex + 6, toIndex).trim(); // Start date
                        String endDate = input.substring(toIndex + 4).trim(); // End date

                        Events event = new Events(task, startDate, endDate);
                        list.add(event);
                        number++;
                        System.out.println("Got it. I've added this task:\n" + event.toString());
                        System.out.println("Now you have " + (number) + " tasks in the list.");
                    } else {
                        throw new DukeException("Invalid input format.");
                    }
                }
                else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        in.close();
    }
}
