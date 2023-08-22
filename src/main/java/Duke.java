import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String i4 = "    ";
    private static final String i5 = Duke.i4 + " ";
    private static final String i7 = Duke.i5 + "  ";
    private static final String line = Duke.i4 + "——————————————————————————————————————————————————————————————————";
    private String name;
    private List<Task> list;
    private Scanner scanner;

    public Duke(String name) {
        this.name = name;
        this.list = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void line() {
        System.out.println(Duke.line);
    }

    public void exit() {
        System.out.println(Duke.i5 + "Bye. Hope to see you again soon!");
    }

    public void greet() {
        this.line();
        System.out.println(Duke.i5 + "Hello! I'm " + this.name);
        System.out.println(Duke.i5 + "What can I do for you?");
        this.line();
    }

    public void startService() throws DukeException {
        String input = this.scanner.nextLine();
        boolean exceptionOccurs = false;

        this.line();

        if (input.equals("bye")) {
            scanner.close();
            exit();
            this.line();
            return;
        } else if (input.equals("list")) {
            System.out.println(Duke.i5 + "Here are the tasks in your list:");
            for (int i = 0; i < this.list.size(); i++) {
                System.out.println(Duke.i5 + (i + 1) + "." + this.list.get(i));
            }
        } else if (input.startsWith("mark")) {
            try {
                if (input.length() == 4) {
                    exceptionOccurs = true;
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! Please specify a valid task number to be marked.");
                }
                if (input.charAt(4) != ' ') {
                    exceptionOccurs = true;
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                if (input.length() == 5) {
                    exceptionOccurs = true;
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! Please specify a valid task number to be marked.");
                }

                int index = 0;

                try {
                    index = Integer.parseInt(input.substring(5)) - 1;
                } catch (NumberFormatException e) {
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! Please specify a valid task number to be marked.");
                }

                if (index < 0 || this.list.size() <= index) {
                    exceptionOccurs = true;
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! Please specify a valid task number to be marked.");
                }
                this.list.get(index).mark();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.startsWith("unmark")) {
            try {
                if (input.length() == 6) {
                    exceptionOccurs = true;
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! Please specify a valid task number to be unmarked.");
                }
                if (input.charAt(6) != ' ') {
                    exceptionOccurs = true;
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                if (input.length() == 7) {
                    exceptionOccurs = true;
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! Please specify a valid task number to be unmarked.");
                }

                int index = 0;

                try {
                    index = Integer.parseInt(input.substring(7)) - 1;
                } catch (NumberFormatException e) {
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! Please specify a valid task number to be unmarked.");
                }

                if (index < 0 || this.list.size() <= index) {
                    exceptionOccurs = true;
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! Please specify a valid task number to be unmarked.");
                }

                this.list.get(index).unmark();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.startsWith("delete")) {

            try {

                if (input.length() == 6) {
                    exceptionOccurs = true;
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! Please specify a valid task number to be deleted.");
                }
                if (input.charAt(6) != ' ') {
                    exceptionOccurs = true;
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                if (input.length() == 7) {
                    exceptionOccurs = true;
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! Please specify a valid task number to be deleted.");
                }

                int index = 0;

                try {
                    index = Integer.parseInt(input.substring(7)) - 1;
                } catch (NumberFormatException e) {
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! Please specify a valid task number to be deleted.");
                }

                if (index < 0 || this.list.size() <= index) {
                    exceptionOccurs = true;
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! Please specify a valid task number to be deleted.");
                }

                Task task = this.list.remove(index);
                System.out.println(Duke.i5 + "Noted. I've removed this task:");
                System.out.println(Duke.i7 + task);
                System.out.println(Duke.i5 + "Now you have " + this.list.size() + " tasks in the list.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        } else {

            Task task = null;

            if (input.startsWith("todo")) {
                try {
                    if (input.length() == 4) {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    if (input.charAt(4) != ' ') {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    if (input.length() == 5) {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    task = new Todo(input.substring(5));
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("deadline")) {
                try {
                    if (input.length() == 8) {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! The description of a deadline cannot be empty.");
                    }

                    if (input.charAt(8) != ' ') {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    if (input.length() == 9) {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! The description of a deadline cannot be empty.");
                    }

                    if (!input.contains("/by ")) {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! The format of a deadline should be 'deadline YOUR_TASK /by YOUR_DEADLINE'.");
                    }

                    int slashIndex = input.indexOf('/');

                    if (input.length() < slashIndex + 4) {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! The format of a deadline should be 'deadline YOUR_TASK /by YOUR_DEADLINE'.");
                    }
                    String by = input.substring(slashIndex + 4);

                    if (input.charAt(9) >= slashIndex) {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! The title of your deadline cannot be empty.");
                    }
                    task = new Deadline(input.substring(9, slashIndex - 1), by);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("event")) {
                try {
                    if (input.length() == 5) {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    if (input.charAt(5) != ' ') {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    if (input.length() == 6) {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    if (!input.contains("/from") || !input.contains("/to")) {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! The format of an event should be 'event YOUR_EVENT /from START_TIME /to END_TIME'.");
                    }

                    int fromIndex = input.indexOf("/from");
                    int toIndex = input.indexOf("/to");

                    if (input.charAt(fromIndex + 5) != ' ') {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! The format of an event should be 'event YOUR_EVENT /from START_TIME /to END_TIME'.");
                    }

                    if (fromIndex >= toIndex) {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! The format of an event should be 'event YOUR_EVENT /from START_TIME /to END_TIME'.");
                    }

                    if (toIndex - 1 - (fromIndex + 6) < 1) {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! The starting time of an event should not be empty.");
                    }
                    String from = input.substring(fromIndex + 6, toIndex - 1);

                    if (input.length() < toIndex + 3) {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! The ending time of an event should not be empty.");
                    }

                    if (input.charAt(toIndex + 3) != ' ') {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! The format of an event should be 'event YOUR_EVENT /from START_TIME /to END_TIME'.");
                    }

                    String to = input.substring(toIndex + 4);

                    if (fromIndex - 1 - 6 < 1) {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! The title of an event should not be empty.");
                    }

                    if (input.charAt(fromIndex - 1) != ' ') {
                        exceptionOccurs = true;
                        throw new DukeException(Duke.i5 + "☹ OOPS!!! The format of an event should be 'event YOUR_EVENT /from START_TIME /to END_TIME'.");
                    }

                    task = new Event(input.substring(6, fromIndex - 1), from, to);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    exceptionOccurs = true;
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (!exceptionOccurs) {
                this.list.add(task);

                System.out.println(Duke.i5 + "Got it. I've added this task:");
                System.out.println(Duke.i7 + task);
                System.out.println(Duke.i5 + "Now you have " + this.list.size() + " tasks in the list.");
            }

        }
        this.line();
        startService();
    }

    public static void main(String[] args) throws DukeException {
        Duke bot = new Duke("Kam_BOT");
        bot.greet();
        bot.startService();
    }
}



