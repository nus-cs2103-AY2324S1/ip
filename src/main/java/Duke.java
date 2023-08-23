import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Task> userList = new ArrayList<>();

        System.out.println("Hello! I'm Meep.\nWhat can I do for you?");

        while (true) {
            String userCommand = scanner.nextLine();

            // BYE
            if (userCommand.equalsIgnoreCase("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;

            // LIST
            } else if (userCommand.equalsIgnoreCase("list")) {
                if (userList.size() <= 0) {
                    System.out.println("You currently have no tasks in your list.");
                    continue;
                }
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < userList.size(); i++) {
                    // getting the current element in the list
                    Task element = userList.get(i);

                    System.out.println((i + 1) + ". " + element.toPrint());
                }

            // MARK
            } else if (userCommand.startsWith("mark")) {
                if (userCommand.trim().equalsIgnoreCase("mark")) {
                    System.out.println("Oh, no! Please specify which task you wish to mark.");
                } else {
                    int pos = 0;
                    try {
                        // getting the number of the task to check off
                        pos = Integer.parseInt(userCommand.substring(5).trim());

                    } catch (NumberFormatException e) {
                        System.out.println("Oh, no! Please insert a valid integer.");
                        continue;
                    }

                    // Now, we know that the input is an integer. Check if it exists in the List.
                    if (pos <= 0 || pos > userList.size()) {
                        System.out.println("Oh, no! The given task does not exist.");

                    } else {
                        // there is such a position. set as marked.
                        Task element = userList.get(pos - 1);
                        element.mark();
                        System.out.println("Nice! I've marked this task as done:\n\t"
                                + element.toPrint());
                    }
                }

            // UNMARK
            } else if (userCommand.startsWith("unmark")) {
                if (userCommand.trim().equalsIgnoreCase("unmark")) {
                    System.out.println("Oh, no! Please specify which task you wish to unmark.");
                } else {
                    int pos = 0;
                    try {
                        pos = Integer.parseInt(userCommand.substring(7).trim());

                    } catch (NumberFormatException e) {
                        System.out.println("Oh, no! Please insert a valid integer.");
                        continue;
                    }

                    // Now, we know that the input is an integer. Check if it exists in the List.
                    if (pos <= 0 || pos > userList.size()) {
                        System.out.println("Oh, no! The given task does not exist.");

                    } else {
                        Task element = userList.get(pos - 1);
                        element.unmark();
                        System.out.println("OK, I've marked this task as not done yet:\n\t"
                                + element.toPrint());
                    }

                }

            // TODO
            } else if (userCommand.startsWith("todo")) {

                // When the input after "todo" is empty
                if (userCommand.trim().equalsIgnoreCase("todo")) {
                    System.out.println("Oh, no! The description of todo cannot be empty.");

                // For inputs such as "todoshdvhsdvh"
                } else if (!userCommand.startsWith("todo ")) {
                    System.out.println("Sorry, I don't understand what that means. " +
                            "Please leave a spacing after \"todo\".");

                // a valid todo input
                } else {
                    // Getting everything after todo, trimming any access spaces
                    String name = userCommand.substring(5).trim();
                    // initialising the task
                    ToDo element = new ToDo(name);
                    // adding to task list
                    userList.add(element);

                    System.out.println(
                            "Got it. I've added this task:\n\t"
                                    + element.toPrint()
                    );
                    if (userList.size() == 1) {
                        System.out.println("Now you have 1 task in the list.");
                    } else {
                        System.out.println("Now you have " + userList.size() + " tasks in the list.");
                    }
                }


            // DEADLINE
            } else if (userCommand.startsWith("deadline")) {

                // when the input after "deadline" is empty
                if (userCommand.trim().equalsIgnoreCase("deadline")) {
                    System.out.println("Oh, no! The description of deadline cannot be empty.");

                // for inputs such as "deadlines", "deadlinesbhsd"
                } else if (!userCommand.startsWith("deadline ")) {
                    System.out.println("Sorry, I don't understand what that means. " +
                            "Please leave a spacing after \"deadline\".");

                // input does not have a deadline
                } else if (!userCommand.contains("/") || !userCommand.contains("/by ")
                        || userCommand.trim().endsWith("/by")) {
                    System.out.println("Oh, no! Please add a deadline for the task.");

                // input contains deadline, but there is no task name
                } else if (userCommand.substring(9).trim().startsWith("/by ")) {
                    System.out.println("Oh, no! Please provide a name for the deadline.");

                // by now, should have name and deadline.
                } else {
                    String text = userCommand.substring(9).trim();
                    int dueByPos = text.indexOf("/by ");
                    String name = text.substring(0, dueByPos - 1).trim();
                    String dueBy = text.substring(dueByPos + 4).trim();
                    Deadline element = new Deadline(name, dueBy);
                    userList.add(element);

                    System.out.println(
                            "Got it. I've added this task:\n\t"
                                    + element.toPrint()
                    );
                    if (userList.size() == 1) {
                        System.out.println("Now you have 1 task in the list.");
                    } else {
                        System.out.println("Now you have " + userList.size() + " tasks in the list.");
                    }
                }

                // EVENT
            } else if (userCommand.startsWith("event")) {

                // When input after "event" is empty
                if (userCommand.trim().equalsIgnoreCase("event")) {
                    System.out.println("Oh, no! The description of event cannot be empty.");

                    // for inputs such as "eventdkbhjds"
                } else if (!userCommand.startsWith("event ")) {
                    System.out.println("Sorry, I don't understand what that means. " +
                            "Please leave a spacing after \"event\".");

                    // does not have from and to, or if it ends without an input
                } else if (!userCommand.contains("/") || !userCommand.contains("/from ")
                        || !userCommand.contains("/to ") || userCommand.trim().endsWith("/from")
                        || userCommand.trim().endsWith("/to")) {
                    System.out.println("Oh, no! Please add a start and end to the event.");

                    // to ensure that the from comes before the to
                } else if (!userCommand.substring(userCommand.indexOf("/from ")).contains("/to ")) {
                    System.out.println("Oh, no! Please specify the start of the event first.");

                    // Here, the input has both a from and to, but no name
                } else if (userCommand.substring(6).trim().startsWith("/from")) {
                    System.out.println("Oh, no! Please provide a name for the event.");

                    // if there is no start provided
                } else if (userCommand.substring(userCommand.indexOf("/from ") + 6).trim().startsWith("/to ")) {
                    System.out.println("Oh, no! Please add input to the start of the event.");

                } else {
                    String text = userCommand.substring(6).trim();
                    int beginPos = text.indexOf("/from ");
                    String name = text.substring(0, beginPos - 1).trim();
                    text = text.substring(beginPos + 6);
                    int endPos = text.indexOf("/to ");
                    String begin = text.substring(0, endPos - 1).trim();
                    String end = text.substring(endPos + 4).trim();
                    Event element = new Event(name, begin, end);
                    userList.add(element);

                    System.out.println(
                            "Got it. I've added this task:\n\t"
                                    + element.toPrint()
                    );
                    if (userList.size() == 1) {
                        System.out.println("Now you have 1 task in the list.");
                    } else {
                        System.out.println("Now you have " + userList.size() + " tasks in the list.");
                    }

                }

            } else {
                System.out.println("Sorry, I don't understand what that means.");
            }
        }

    }
}
