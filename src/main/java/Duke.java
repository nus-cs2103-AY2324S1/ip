import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeException {
        String logo =" ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣤⣶⣶⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                    + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⣾⠿⠛⠋⠉⠩⣄⠘⢿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                    + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⡏⠑⠒⠀⠀⣀⣀⠀⠀⢹⠈⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                    + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣷⡀⢀⣰⣿⡿⣿⣧⠀⠀⢡⣾⣧⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                    + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣿⣿⣿⣯⣴⣿⠿⣄⣤⣾⡿⠟⠛⠛⠿⢿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                    + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⣶⠿⠛⠋⠙⣿⣏⠀⠀⢻⣿⣡⣀⣀⠀⠀⠀⠀⢹⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                    + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣾⠿⠋⠁⠀⣀⣤⣶⣾⣿⣿⣤⣤⣾⣿⠉⠉⠙⠻⣿⠆⢀⣾⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                    + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⡿⠋⠁⠀⣀⣴⣿⠿⠛⠉⠀⢀⣿⡿⠿⠟⢿⣆⠀⢀⣴⣯⣴⣿⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                    + "⠀⠀⠀⠀⠀⠀⠀⠀⣠⡾⠋⠀⠀⣠⣾⠟⠋⠀⠀⠀⠀⠀⣈⣿⣷⣤⣴⣾⣿⣈⣻⣿⡟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                    + "⠀⠀⠀⠀⠀⠀⠀⣰⡿⠁⠀⣠⡾⠋⠁⠀⠀⢀⣠⣴⠶⠞⠛⠛⠋⠉⠉⠉⠉⠙⠛⠻⠷⣦⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                    + "⠀⠀⠀⠀⠀⠀⠰⣿⠁⠀⠀⣿⣄⣀⣠⣴⡾⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⣿⠿⣶⣄⠀⠀⠀⢀⣠⡄⠀⠀⠀⠀⠀⠀⠀ \n"
                    + "⠀⠀⠀⠀⠀⠀⠀⠈⠛⠶⠶⢾⣿⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢷⣄⠉⠙⠻⠿⠟⢹⡇⠀⠀⠀⠀⠀⠀⠀ \n"
                    + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠟⠁⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣄⠀⠀⠀⠀⠘⣿⣿⣦⣀⠀⠲⣾⣁⠀⠀⠀⠀⠀⡀⠀ \n"
                    + "⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣦⡀⠀⠀⠀⣿⡿⣿⣿⣿⡆⠀⠉⠛⠛⠛⠛⢻⡏⠀ \n"
                    + "⠀⠀⠀⠀⠀⠀⠀⣠⡾⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⡄⣸⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⠛⢦⡀⠰⣿⣿⣿⣽⣿⡇⠀⠀⠀⠀⠀⢠⡿⠀⠀ \n"
                    + "⠀⠀⠀⠀⣀⣤⡾⢻⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⡏⠙⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠰⣽⣶⣄⠉⠻⣿⣿⣧⠀⠀⢀⣤⣾⠟⠁⠀⠀ \n"
                    + "⢰⣶⡾⠛⠋⠉⠀⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⠀⢸⣸⡇⠀⣨⣿⣾⡋⠀⠀⠀⠀⢀⠀⠀⣿⡀⠀⠈⠛⢷⣄⠈⠛⣿⡆⠀⠘⣿⡀⠀⠀⠀⠀ \n"
                    + "⠀⠙⠿⣦⣀⠀⠀⠀⠀⠀⠀⡾⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠋⠀⠙⢿⣦⣀⠀⠀⠘⣷⣄⣹⣧⠀⠀⠀⠈⢻⣦⠀⠈⠋⠀⠀⠘⣧⠀⠀⠀⠀ \n"
                    + "⠀⠀⠀⠈⠛⠿⢶⡶⠃⠀⣰⠃⠀⠀⠀⠀⠀⢠⣿⠃⠀⠀⠀⠀⠀⠀⠉⠻⢷⣦⣤⣘⣿⡛⠛⠀⢀⣴⣶⣦⡹⣷⡀⠀⠀⠀⠀⠸⣧⠀⠀⠀ \n"
                    + "⠀⠀⠀⠀⠀⢠⡿⠃⠀⢀⡟⠀⠀⠀⠀⠀⠀⣼⣿⠀⠀⢀⣴⣿⣿⣷⡄⠀⠀⠈⠉⠉⠉⠉⠀⠀⢸⣿⣿⣿⣷⠻⣧⠀⠀⠀⠀⠀⢿⡆⠀⠀ \n"
                    + "⠀⠀⠀⠀⢰⣿⠁⠀⠀⢸⠁⠀⠀⠀⠀⠈⠋⣿⠀⠀⠸⣿⣿⣿⣿⣿⡷⠀⠀⠀⠀⠀⠀⠀⠀  ⠈⠛⠿⠟⠃⠀⢻⣧⠀⠀⠀⠸⣧⠀⠀ \n"
                    + "⠀⠀⠀⠀⣿⡇⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠉⠻⠿⠿⠋⠀⢠⡀⠀⠀⣀⣀⣀⣸⠇⠀⠀⠀⠀⠀⠈⢿⣧⠀⠀⠀⠀⣿⡀⠀ \n"
                    + "⠀⠀⠀⢰⣿⠁⠀⠀⢰⡏⠀⠀⠀⠀⠀⠀⠀⠀⢿⡀⠀⠀⠀⠀⠀ ⠀⠀⠈⠛⠒⠛⠉⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⢸⡟⠀⠀⠀⠀⢸⡇⠀ \n"
                    + "⠀⠀⠀⢸⣿⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⠁⠀⠀⠀⠀⢸⣿⠀ \n"
                    + "⠀⠀⠀⢸⣿⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⣀⣤⣶⡿⠋⣿⠀⠀⠀⠀⠀⠀⣿⠀ \n"
                    + "⠀⠀⠀⠘⣿⡄⠀⠀⢸⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣧⣄⣀⣀⣀⣠⣤⣶⣶⣾⣿⣿⣿⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⣠⠀⠀⢠⣿⡄ \n"
                    + "⠀⠀⠀⠀⢻⣧⠀⠀⠸⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣟⠛⢻⡟⢻⡉⠉⣫⣀⠀⠀⠀⠉⠉⠛⠙⠿⣷⣿⡿⠁⢀⣰⡟⠀⠀⢸⣿⠀ \n"
                    + "⠀⠀⠀⠀⠈⢻⣧⡀⠀⠹⣷⡀⠀⠀⠀⠀⢠⠀⠀⠀⠘⣿⣿⣿⢻⡿⠀⠈⠷⠟⠁⠘⢾⣿⣶⣤⣶⣾⡇⠀⣿⣿⣤⣶⣿⣿⠃⠀⠀⣾⡟⠀ \n"
                    + "⠀⠀⠀⠀⠀⠈⠻⣷⣄⡀⠹⣷⣄⠀⠀⠀⢸⣷⣤⡀⠀⠈⢻⣿⣯⣤⠀⠀⣠⡀⠀⢀⣼⣿⣿⣿⣿⣟⠁⠐⠿⣿⣿⣿⣿⠋⠀⢀⣾⠟⠀⠀ \n"
                    + "⠀⠀⠀⠀⠀⠀⠀⠈⠙⠿⣷⣮⣽⣷⣶⣤⣤⣿⣿⣿⣷⣶⣦⣭⣿⣿⣧⣠⠵⢯⡆⠚⣯⢿⠋⠛⠛⢫⣀⣠⣾⣿⢿⣿⣥⣤⠶⠛⠁⠀⠀⠀ \n"
                    + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⢁⣾⣿⣿⣿⠿⠿⠿⠿⠻⢿⣿⣿⣷⣦⣤⣤⣀⣤⣤⣄⣶⣿⣿⡿⠟⠉⠀⠀⢻⡄⠀⠀⠀⠀⠀⠀⠀ \n"
                    + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⡇⠀⠀⣀⡀⠈⢿⣧⠀⠉⠙⠛⠛⠛⠛⠛⠛⠉⠁⠀⠀⠀⠀⠀⠀⢿⡄⠀⠀⠀⠀⠀⠀ \n"
                    + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣴⣿⣿⡏⠛⠉⢻⣿⣿⣿⣾⣿⣿⠀⢸⣿⠀⠀⠀⠀⠀⠙⠓⠢⠀⠀⠀⠀⠀⠸⠀⠀⠀⠘⣿⡄⠀⠀⠀⠀⠀ \n";

        String msg = "----------------------------------------------------------------- \n"
                + "Hello! I'm Paimon! \n"
                + "What can I do for you? \n"
                + "-----------------------------------------------------------------";

        System.out.println(logo + "\n" + msg);

        Scanner sc = new Scanner(System.in);

        boolean running = true;

        String input;

        int num;

        ArrayList<Task> ls = new ArrayList<>();

        while (running) {
            input = sc.next();

            if (input.equals("mark")) {
                num = sc.nextInt();
                Task t = ls.get(num - 1);
                t.markDone();
                System.out.println("Nice! I've marked this task as done: \n"
                        + t.toString()
                        + "\n-----------------------------------------------------------------");
            } else if (input.equals("unmark")) {
                num = sc.nextInt();
                Task t = ls.get(num - 1);
                t.markUndone();
                System.out.println("OK, I've marked this task as not done yet: \n"
                        + t.toString()
                        + "\n-----------------------------------------------------------------");
            } else if (input.equals("bye")) {
                System.out.println("Bye Bye! See you soon :D"
                        + "\n-----------------------------------------------------------------");
                running = false;
            } else if (input.equals("list")) {
                int i = 1;
                System.out.println("Here are the tasks in your list: ");
                for (Task t : ls) {
                    System.out.println(i + ". " + t.toString());
                    i++;
                }
                System.out.println("-----------------------------------------------------------------");
            } else if (input.equals("todo")) {
                sc.skip(" ");
                String des = sc.nextLine();
                Todo t = new Todo(des);
                ls.add(t);

                System.out.println(
                        "Got it. Task successfully added: \n"
                                + t.toString()
                                + "\nNow you have " + ls.size() + " tasks in the list"
                                + "\n-----------------------------------------------------------------");
            } else if (input.equals("deadline")) {
                if (!sc.hasNext()) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty."
                            + "\n-----------------------------------------------------------------");
                }
                String des = "";

                while (sc.hasNext()) {
                    String next = sc.next();
                    if (next.equals("/by")) {
                        break;
                    }
                    des += next + " ";
                }

                String by = sc.nextLine();
                Deadline d = new Deadline(des, by);
                ls.add(d);

                System.out.println(
                        "Got it. Task successfully added: \n"
                                + d.toString()
                                + "\nNow you have " + ls.size() + " tasks in the list"
                                + "\n-----------------------------------------------------------------");
            } else if (input.equals("event")) {
                String des = "";
                String start = "";

                while (sc.hasNext()) {
                    String next = sc.next();
                    if (next.equals("/from")) {
                        break;
                    }
                    des += next + " ";
                }
                String duration = sc.nextLine();

                Scanner subSc = new Scanner(duration);

                while (subSc.hasNext()) {
                    String next = subSc.next();
                    if (next.equals("/to")) {
                        break;
                    }
                    start += next + " ";
                }
                String end = subSc.nextLine();
                Event e = new Event(des, start, end);
                ls.add(e);

                System.out.println(
                        "Got it. Task successfully added: \n"
                                + e.toString()
                                + "\nNow you have " + ls.size() + " tasks in the list"
                                + "\n-----------------------------------------------------------------");
            } else if (input.equals("delete")) {
                num = sc.nextInt();
                Task task = ls.get(num - 1);
                ls.remove(num - 1);
                System.out.println("Noted. I've removed this task: \n"
                    + task.toString()
                    + "\nNow you have " + ls.size() + " tasks in the list");
            } else {
                // throws exception and prints error message if no keyphrase is given
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

            }
        }
        sc.close();
    }
}

