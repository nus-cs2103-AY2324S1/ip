import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<toDo> userList = new ArrayList<>();

        System.out.println("Hello! I'm Meep.\nWhat can I do for you?");

        while (true) {
            String userCommand = scanner.nextLine();

            if (userCommand.equalsIgnoreCase("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;

            } else if (userCommand.equalsIgnoreCase("list")) {
                for (int i = 0; i < userList.size(); i++) {
                    // getting the current element in the list
                    toDo element = userList.get(i);

                    System.out.println((i + 1) + ". " + element.getStatus() + " " + element.name);
                }
            } else if (userCommand.startsWith("mark ") && userCommand.length() > 5) {
                int pos = 0;
                try {
                    pos = Integer.parseInt(userCommand.substring(5));
                } catch (NumberFormatException e) {
                    // the input after "mark" is not entirely integers.
                    toDo newElement = new toDo(userCommand);
                    userList.add(newElement);
                    System.out.println("added: " + userCommand);
                }
                // Now, we know that the input is an integer. Check if it exists in the List.
                if (pos > userList.size()) {
                    // there is no such position in the list.
                    toDo newElement = new toDo(userCommand);
                    userList.add(newElement);
                    System.out.println("added: " + userCommand);
                } else {
                    toDo element = userList.get(pos - 1);
                    element.mark();
                    System.out.println("Nice! I've marked this task as done:\n\t"
                            + element.getStatus() + " " + element.name);
                }
            } else if (userCommand.startsWith("unmark ") && userCommand.length() > 7) {
                int pos = 0;
                try {
                    pos = Integer.parseInt(userCommand.substring(7));
                } catch (NumberFormatException e) {
                    // the input after "mark" is not entirely integers.
                    toDo newElement = new toDo(userCommand);
                    userList.add(newElement);
                    System.out.println("added: " + userCommand);
                }

                // Now, we know that the input is an integer. Check if it exists in the List.
                if (pos > userList.size()) {
                    // there is no such position in the list.
                    toDo newElement = new toDo(userCommand);
                    userList.add(newElement);
                    System.out.println("added: " + userCommand);

                } else {
                    toDo element = userList.get(pos - 1);
                    element.unmark();
                    System.out.println("OK, I've marked this task as not done yet:\n\t"
                            + element.getStatus() + " " + element.name);
                }
            }
            else {
                toDo newElement = new toDo(userCommand);
                userList.add(newElement);
                System.out.println("added: " + userCommand);
            }
        }

    }
}
