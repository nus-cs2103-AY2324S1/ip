import java.util.Scanner;
import java.util.ArrayList;


public class Brotherman {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        ArrayList<Task> userList = new ArrayList<Task>();


        System.out.println(
                "___________________________________________________________\n"
                + "Hello! I'm Brotherman\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n");

        String userCommand = input.nextLine();

        while (!userCommand.equals("bye")) {

            if (userCommand.equals("list")) {
                int start = 1;
                for (Task listItems : userList) {
                    System.out.println(start + ". " + listItems.toString());
                    start++;
                }
            } else if (userCommand.split(" ")[0].equals("mark")) {
                int task = Integer.parseInt(userCommand.split(" ")[1]) - 1;

                if (task < 0 || task >= userList.size()) {
                    System.out.println("Brotherman the value you put in wrong.  Try again.");
                } else {
                    userList.get(task).markAsDone();
                    System.out.println(
                            "___________________________________________________________\n"
                                    + "The task is done Brotherman \n"
                                    + userList.get(task).toString()
                                    + " \n"
                                    + "___________________________________________________________\n"
                    );
                }
            } else if (userCommand.split(" ")[0].equals("unmark")) {
                int task = Integer.parseInt(userCommand.split(" ")[1]) - 1;

                if (task < 0 || task >= userList.size()) {
                    System.out.println("Brotherman the value you put in wrong.  Try again.");
                } else {
                    userList.get(task).unmarkAsDone();
                    System.out.println(
                            "___________________________________________________________\n"
                                    + "The task is now undone Brotherman \n"
                                    + userList.get(task).toString()
                                    + " \n"
                                    + "___________________________________________________________\n"
                    );
                }

            } else if (userCommand.split(" ")[0].equals("todo")) {

                userList.add(new Todo(userCommand.split("todo")[1]));

                System.out.println(
                        "___________________________________________________________\n"
                        + "Got you Brotherman! New Todo added to the list. \n"
                        + userList.get(userList.size() - 1).toString()
                        + " \n"
                        + "You have " + userList.size() + " tasks in the list! \n"
                        + "___________________________________________________________\n"
                );

            } else if (userCommand.split(" ")[0].equals("deadline")) {

                userList.add(new Deadline(userCommand.split("deadline ")[1]));

                System.out.println(
                        "___________________________________________________________\n"
                        + "Got you Brotherman! New deadline added to the list.\n"
                        + userList.get(userList.size() - 1).toString()
                        + " \n"
                        + "You have " + userList.size() + " tasks in the list!\n"
                        + "___________________________________________________________\n"
                );

            } else if (userCommand.split(" ")[0].equals("event")) {

                userList.add(new Event(userCommand.split("event")[1]));
                System.out.println(
                        "___________________________________________________________\n"
                        + "Got you Brotherman! New event added to the list.\n"
                        + userList.get(userList.size() - 1).toString()
                        + " \n"
                        + "You have " + userList.size() + " tasks in the list!\n"
                        + "___________________________________________________________\n"
                );
            } else {

                System.out.println("brotherman input a todo, event or deadline!");
            }

            userCommand = input.nextLine();

        }
        System.out.println(
                "___________________________________________________________\n"
                + "Bye, see you again Brotherman!\n"
                + "___________________________________________________________\n"

        );
    }
}
