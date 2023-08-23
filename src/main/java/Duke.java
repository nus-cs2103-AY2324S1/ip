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

            if (userCommand.equalsIgnoreCase("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;

            } else if (userCommand.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < userList.size(); i++) {
                    // getting the current element in the list
                    Task element = userList.get(i);

                    System.out.println((i + 1) + ". " + element.toPrint());
                }
            } else if (userCommand.startsWith("mark ") && userCommand.length() > 5) {
                int pos = 0;
                try {
                    // getting the number of the task to check off
                    pos = Integer.parseInt(userCommand.substring(5).trim());

                } catch (NumberFormatException e) {
                    // the input after "mark" is not entirely integers.
                    Task newElement = new Task(userCommand);
                    userList.add(newElement);
                    System.out.println("added: " + userCommand);
                }

                // Now, we know that the input is an integer. Check if it exists in the List.
                if (pos > userList.size()) {
                    // there is no such position in the list. add it as a task
                    Task newElement = new Task(userCommand);
                    userList.add(newElement);
                    System.out.println("added: " + newElement.toPrint());

                } else {
                    // there is such a position. set as marked.
                    Task element = userList.get(pos - 1);
                    element.mark();
                    System.out.println("Nice! I've marked this task as done:\n\t"
                            + element.toPrint());
                }

            } else if (userCommand.startsWith("unmark ") && userCommand.length() > 7) {
                int pos = 0;
                try {
                    // see if what is after "unmark" is an integer
                    pos = Integer.parseInt(userCommand.substring(7).trim());

                } catch (NumberFormatException e) {
                    // the input after "mark" is not entirely integers.
                    Task newElement = new Task(userCommand);
                    userList.add(newElement);
                    System.out.println("added: " + newElement.toPrint());
                }

                // Now, we know that the input is an integer. Check if it exists in the List.
                if (pos > userList.size()) {
                    // there is no such position in the list. Add it as a task
                    Task newElement = new Task(userCommand);
                    userList.add(newElement);
                    System.out.println("added: " + newElement.toPrint());

                } else {
                    Task element = userList.get(pos - 1);
                    element.unmark();
                    System.out.println("OK, I've marked this task as not done yet:\n\t"
                            + element.toPrint());
                }
            } else if (userCommand.startsWith("todo ") && userCommand.length() > 5) {
                String name = userCommand.substring(5).trim();
                ToDo element = new ToDo(name);
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

            } else if (userCommand.startsWith("deadline ") && userCommand.length() > 9) {
                String text = userCommand.substring(8).trim();
                int dueByPos = text.indexOf("/");
                String name = text.substring(0, dueByPos - 1);
                String dueBy = text.substring(dueByPos + 3).trim();
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

            } else if (userCommand.startsWith("event ") && userCommand.length() > 6) {
                String text = userCommand.substring(6).trim();
                int beginPos = text.indexOf("/");
                String name = text.substring(0, beginPos - 1);
                text = text.substring(beginPos + 5).trim();
                int endPos = text.indexOf("/");
                String begin = text.substring(0, endPos - 1);
                String end = text.substring(endPos + 3).trim();
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

            else {
                Task newElement = new Task(userCommand);
                userList.add(newElement);
                System.out.println("added: " + userCommand);
            }
        }

    }
}
