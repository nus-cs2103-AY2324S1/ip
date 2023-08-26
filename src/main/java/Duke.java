import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        froggie();
    }

    public static void froggie() throws DukeException {
        System.out.println("Hello! I'm froggie!");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        DukeList dukelist = new DukeList();
        boolean notBye = true;
        while (notBye) {
            String input = scanner.nextLine();
            String[] splited = input.split(" ", 2);

            try {
                if (splited[0].equals("bye") && splited.length == 1) {
                    notBye = false;
                    System.out.println("Bye. Hope to see you again soon!");
                } else if (splited[0].equals("list")) {
                    dukelist.printList();
                } else if (splited[0].equals("mark")) {
                    int number = Integer.parseInt(splited[1]);
                    dukelist.setDone(number);
                } else if (splited[0].equals("unmark")) {
                    int number = Integer.parseInt(splited[1]);
                    dukelist.setUndone(number);
                } else if (splited[0].equals("todo")) {
                    if (splited.length > 1) {
                        String[] job = input.split(" ", 2);
                        dukelist.addTodo(job[1]);
                    } else {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                } else if (splited[0].equals("deadline")) {
                    if (splited.length > 1) {
                        String[] splitted = input.split(" ", 2);
                        String[] deadline = splitted[1].split("/by", 2);
                        dukelist.addDeadline(deadline[0], deadline[1]);
                    } else {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }

                } else if (splited[0].equals("event")) {
                    if (splited.length > 1) {
                        String[] splitted = input.split(" ", 2);
                        String[] from = splitted[1].split("/from", 2);
                        String[] to = from[1].split("/to", 2);
                        dukelist.addEvent(from[0], to[0], to[1]);
                    } else {
                        throw new DukeException("The description of an event cannot be empty.");
                    }

                } else if (splited[0].equals("delete")){
                    int num = Integer.parseInt(splited[1]);
                    dukelist.deleteTask(num - 1);
                }
                else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException error) {
                System.out.println(error.getMessage());
            }
        }
        scanner.close();
    }


}
