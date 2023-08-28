import java.util.Scanner;

public class Ui {
    public static final String i4 = "    ";
    public static final String i5 = Ui.i4 + " ";
    public static final String i7 = Ui.i5 + "  ";
    public static final String line = Ui.i4 + "——————————————————————————————————————————————————————————————————";

    private String name;
    private Scanner scanner;
    private String input;

    public Ui(String name) {
        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    public static void line() {
        System.out.println(Ui.line);
    }

    public void greet() {
        Ui.line();
        System.out.println(Ui.i5 + "Hello! I'm " + this.name);
        System.out.println(Ui.i5 + "What can I do for you?");
        Ui.line();
    }

    public void exit() {
        System.out.println(Ui.i5 + "Bye. Hope to see you again soon!");
    }
    public void bye() {
        this.scanner.close();
        this.exit();
        Ui.line();
    }

    public Commands getNextUserInput() {
        this.input = this.scanner.nextLine();

        if (input.equals("bye")) {
            return Commands.BYE;
        } else if (input.equals("list")) {
            return Commands.LIST;
        } else if (input.startsWith("mark")) {
            return Commands.MARK;
        } else if (input.startsWith("unmark")) {
            return Commands.UNMARK;
        } else if (input.startsWith("delete")) {
            return Commands.DELETE;
        } else if (input.startsWith("todo")) {
            return Commands.TODO;
        } else if (input.startsWith("deadline")) {
            return Commands.DEADLINE;
        } else if (input.startsWith("event")) {
            return Commands.EVENT;
        }

        return Commands.DEFAULT;

    }

    public int mark() throws DukeException {
        if (this.input.length() == 4) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! Please specify a valid task number to be marked.");
        }
        if (this.input.charAt(4) != ' ') {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (this.input.length() == 5) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! Please specify a valid task number to be marked.");
        }

        try {
            return Integer.parseInt(input.substring(5)) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! Please specify a valid task number to be marked.");
        }
    }

    public int unmark() throws DukeException {
        if (this.input.length() == 6) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! Please specify a valid task number to be unmarked.");
        }
        if (this.input.charAt(6) != ' ') {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (this.input.length() == 7) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! Please specify a valid task number to be unmarked.");
        }

        try {
            return Integer.parseInt(input.substring(7)) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! Please specify a valid task number to be unmarked.");
        }
    }

    public int delete() throws DukeException {
        if (this.input.length() == 6) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! Please specify a valid task number to be deleted.");
        }
        if (this.input.charAt(6) != ' ') {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (this.input.length() == 7) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! Please specify a valid task number to be deleted.");
        }

        try {
            return Integer.parseInt(input.substring(7)) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! Please specify a valid task number to be deleted.");
        }
    }

    public Task todo() throws DukeException {
        if (this.input.length() == 4) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! The description of a todo cannot be empty.");
        }
        if (this.input.charAt(4) != ' ') {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (this.input.length() == 5) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! The description of a todo cannot be empty.");
        }

        return new Todo(input.substring(5));
    }

    public Task deadline() throws DukeException {
        if (this.input.length() == 8) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (this.input.charAt(8) != ' ') {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (this.input.length() == 9) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (!this.input.contains("/by")) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! The format of a deadline should be 'deadline YOUR_TASK /by YOUR_DEADLINE'.");
        }

        int slashIndex = this.input.indexOf("/by");

        if (this.input.length() < slashIndex + 4) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! The format of a deadline should be 'deadline YOUR_TASK /by YOUR_DEADLINE'.");
        }

        String by = this.input.substring(slashIndex + 4);

        if (slashIndex <= 9) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! The title of your deadline cannot be empty.");
        }

        return new Deadline(this.input.substring(9, slashIndex - 1), by);
    }

    public Task event() throws DukeException {
        if (this.input.length() == 5) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (this.input.charAt(5) != ' ') {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (this.input.length() == 6) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (!this.input.contains("/from") || !this.input.contains("/to")) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! The format of an event should be 'event YOUR_EVENT /from START_TIME /to END_TIME'.");
        }

        int fromIndex = this.input.indexOf("/from");
        int toIndex = this.input.indexOf("/to");

        if (this.input.charAt(fromIndex + 5) != ' ') {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! The format of an event should be 'event YOUR_EVENT /from START_TIME /to END_TIME'.");
        }
        if (fromIndex >= toIndex) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! The format of an event should be 'event YOUR_EVENT /from START_TIME /to END_TIME'.");
        }
        if (toIndex - 1 - (fromIndex + 6) < 1) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! The starting time of an event should not be empty.");
        }

        String from = this.input.substring(fromIndex + 6, toIndex - 1);

        if (this.input.length() < toIndex + 3) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! The ending time of an event should not be empty.");
        }
        if (this.input.charAt(toIndex + 3) != ' ') {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! The format of an event should be 'event YOUR_EVENT /from START_TIME /to END_TIME'.");
        }

        String to = this.input.substring(toIndex + 4);

        if (fromIndex - 1 - 6 < 1) {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! The title of an event should not be empty.");
        }
        if (this.input.charAt(fromIndex - 1) != ' ') {
            throw new DukeException(Ui.i5 + "☹ OOPS!!! The format of an event should be 'event YOUR_EVENT /from START_TIME /to END_TIME'.");
        }

        return new Event(this.input.substring(6, fromIndex - 1), from, to);
    }
}
