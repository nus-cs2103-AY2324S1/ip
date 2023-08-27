import javafx.scene.web.HTMLEditorSkin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {
    public static final String i4 = "    ";
    public static final String i5 = Duke.i4 + " ";
    public static final String i7 = Duke.i5 + "  ";
    public static final String line = Duke.i4 + "——————————————————————————————————————————————————————————————————";

    private String name;
    private TaskList taskList;
    private Scanner scanner;

    public Duke(String name) {
        this.name = name;
        this.taskList = new TaskList();
        this.scanner = new Scanner(System.in);
    }

    public void line() {
        System.out.println(Duke.line);
    }

    public void exit() {
        System.out.println(Duke.i5 + "Bye. Hope to see you again soon!");
    }

    public void startUp(String filePath) {
        this.greet();
        this.taskList.setHardDiskFilePath(filePath);
        this.taskList.loadData();
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

        Commands cmd = Commands.DEFAULT;
        Task task = null;

        if (input.equals("bye")) {
            cmd = Commands.BYE;
        } else if (input.equals("list")) {
            cmd = Commands.LIST;
        } else if (input.startsWith("mark")) {
            cmd = Commands.MARK;
        } else if (input.startsWith("unmark")) {
            cmd = Commands.UNMARK;
        } else if (input.startsWith("delete")) {
            cmd = Commands.DELETE;
        } else if (input.startsWith("todo")) {
            cmd = Commands.TODO;
        } else if (input.startsWith("deadline")) {
            cmd = Commands.DEADLINE;
        } else if (input.startsWith("event")) {
            cmd = Commands.EVENT;
        }



        switch (cmd) {

        case BYE:
            this.scanner.close();
            exit();
            this.line();
            return;
        case LIST:
            this.taskList.listOutEverything();
            break;
        case MARK:
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

                if (this.taskList.isOutOfRange(index)) {
                    exceptionOccurs = true;
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! Please specify a valid task number to be marked.");
                }

                this.taskList.mark(index);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            break;
        case UNMARK:
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

                if (taskList.isOutOfRange(index)) {
                    exceptionOccurs = true;
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! Please specify a valid task number to be unmarked.");
                }

                this.taskList.unmark(index);

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            break;
        case DELETE:
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

                if (taskList.isOutOfRange(index)) {
                    exceptionOccurs = true;
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! Please specify a valid task number to be deleted.");
                }

                this.taskList.remove(index);

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            break;
        case TODO:
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
            break;
        case DEADLINE:
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

                if (!input.contains("/by")) {
                    exceptionOccurs = true;
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! The format of a deadline should be 'deadline YOUR_TASK /by YOUR_DEADLINE'.");
                }

                int slashIndex = input.indexOf("/by");

                if (input.length() < slashIndex + 4) {
                    exceptionOccurs = true;
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! The format of a deadline should be 'deadline YOUR_TASK /by YOUR_DEADLINE'.");
                }

                String by = input.substring(slashIndex + 4);

                if (slashIndex <= 9) {
                    exceptionOccurs = true;
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! The title of your deadline cannot be empty.");
                }
                task = new Deadline(input.substring(9, slashIndex - 1), by);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            break;
        case EVENT:
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
            break;
        default:
            try {
                exceptionOccurs = true;
                throw new DukeException(Duke.i5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            break;
        }

        if (!exceptionOccurs && task != null) {
            this.taskList.add(task);
        }

        this.line();
        startService();
    }

    public static void main(String[] args) throws DukeException {
        Duke bot = new Duke("Kam_BOT");
        bot.startUp("./data/duke.txt");
        bot.startService();
    }
}
