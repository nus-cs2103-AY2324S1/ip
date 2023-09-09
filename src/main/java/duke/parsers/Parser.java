package duke.parsers;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;


import java.util.ArrayList;
import java.util.Objects;

/**
 * The Parser class that makes sense of the user's input.
 */
public class Parser {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Instantiates a new Parser.
     *
     * @param ui       the ui
     * @param storage  the storage
     * @param tasklist the tasklist
     */
    public Parser(Ui ui, Storage storage, TaskList tasklist) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasklist;
    }

    /**
     * Makes sense of file data and creates tasks accordingly.
     *
     * @param input the String line in the file
     * @return the corresponding task
     * @throws DukeException the duke exception if the file data is incorrect or corrupted
     */
    public static Task parseFileInfo(String input) throws DukeException {
        String regex = " : ";
        String[] split = input.split(regex);
        if (split[0].equals("T")) {
            ToDo todo = new ToDo(split[2]);
            if (Objects.equals(split[1], "1")) {
                todo.setdone();
            }
            return todo;
        } else if (split[0].equals("D")) {
            Deadline deadline = new Deadline(split[2], split[3]);
            if (Objects.equals(split[1], "1")) {
                deadline.setdone();
            }
            return deadline;
        } else if (split[0].equals("E")) {
            Event event = new Event(split[2], split[3], split[4]);
            if (Objects.equals(split[1], "1")) {
                event.setdone();
            }
            return event;
        } else {
            throw new DukeException("cannot create task");
        }
    }

    /**
     * Makes sense of user inputs, makes changes to lists and file and outputs messages
     *
     * @param userinput the user's input
     * @throws DukeException the duke exception when the user input is incorrect
     */
    public void parse(String userinput) throws DukeException {
        if (userinput.equalsIgnoreCase("list")) {
            ui.printList(tasks.list());
        } else if (userinput.length() >= 6 && userinput.substring(0, 5).equalsIgnoreCase("mark ")) {
            try {
                int taskno = Integer.parseInt(userinput.substring(5));
                if (taskno <= tasks.list().size()) {
                    Task task = tasks.list().get(taskno - 1);
                    task.setdone();
                    ui.markedMessage(task);
                } else {
                    ui.invalidMark();
                }
            } catch (NumberFormatException e) {
                ui.invalidMark();
            }
        } else if (userinput.length() >= 4 && userinput.substring(0, 4).equalsIgnoreCase("todo")) {

            ToDo todo = new ToDo(userinput.substring(4));
            tasks.list().add(todo);
            ui.addedMessage(todo);
            ui.listMessage(tasks.list());
            storage.updateFile(tasks.list());

        } else if (userinput.length() >= 8 && userinput.substring(0, 8).equalsIgnoreCase("deadline")) {
            String[] segments = userinput.split(" /by ");
            Deadline deadline = new Deadline(segments[0].substring(8), segments[1]);
            tasks.list().add(deadline);
            ui.addedMessage(deadline);
            ui.listMessage(tasks.list());
            storage.updateFile(tasks.list());
        } else if (userinput.length() >= 7 && userinput.substring(0, 6).equalsIgnoreCase("event ")) {
            String[] segments1 = userinput.split(" /from ");
            String from = segments1[1].split(" /to ")[0];
            String[] segments2 = segments1[1].split(" /to ");
            Event event = new Event(segments1[0].substring(5), from, segments2[1]);
            tasks.list().add(event);
            ui.addedMessage(event);
            ui.listMessage(tasks.list());
            storage.updateFile(tasks.list());
        } else if (userinput.length() >= 8 && userinput.substring(0, 7).equalsIgnoreCase("delete ")) {
            try {
                int taskno = Integer.parseInt(userinput.substring(7));
                if (taskno <= tasks.list().size()) {
                    Task task = tasks.list().get(taskno - 1);
                    tasks.list().remove(taskno - 1);
                    ui.removedMessage(task);
                    ui.listMessage(tasks.list());
                } else {
                    ui.validNumberMessage();
                }
            } catch (NumberFormatException e) {
                ui.validNumberMessage();
            }
        } else if (userinput.length() >= 6 && userinput.substring(0, 5).equalsIgnoreCase("find ")) {
            ui.findmsg();
            ui.printList(tasks.contains(userinput.substring(5)));

        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public String parseString(String userinput) throws DukeException {
        if (userinput.equalsIgnoreCase("list")) {
            return ui.printListString(tasks.list());
        } else if (userinput.length() >= 6 && userinput.substring(0, 5).equalsIgnoreCase("mark ")) {
            try {
                int taskno = Integer.parseInt(userinput.substring(5));
                if (taskno <= tasks.list().size()) {
                    Task task = tasks.list().get(taskno - 1);
                    task.setdone();
                    return ui.markedMessageString(task);
                } else {
                    return ui.invalidMarkString();
                }
            } catch (NumberFormatException e) {
                return ui.invalidMarkString();
            }
        } else if (userinput.length() >= 4 && userinput.substring(0, 4).equalsIgnoreCase("todo")) {

            ToDo todo = new ToDo(userinput.substring(4));
            tasks.list().add(todo);
            storage.updateFile(tasks.list());
            return ui.addedMessageString(todo) + "%n" + ui.listMessageString(tasks.list());

        } else if (userinput.length() >= 8 && userinput.substring(0, 8).equalsIgnoreCase("deadline")) {
            String[] segments = userinput.split(" /by ");
            Deadline deadline = new Deadline(segments[0].substring(8), segments[1]);
            tasks.list().add(deadline);
            storage.updateFile(tasks.list());
            return ui.addedMessageString(deadline) + "%n" + ui.listMessageString(tasks.list());
        } else if (userinput.length() >= 7 && userinput.substring(0, 6).equalsIgnoreCase("event ")) {
            String[] segments1 = userinput.split(" /from ");
            String from = segments1[1].split(" /to ")[0];
            String[] segments2 = segments1[1].split(" /to ");
            Event event = new Event(segments1[0].substring(5), from, segments2[1]);
            tasks.list().add(event);
            storage.updateFile(tasks.list());
            return (ui.addedMessageString(event) + "%n" + ui.listMessageString(tasks.list()));
        } else if (userinput.length() >= 8 && userinput.substring(0, 7).equalsIgnoreCase("delete ")) {
            try {
                int taskno = Integer.parseInt(userinput.substring(7));
                if (taskno <= tasks.list().size()) {
                    Task task = tasks.list().get(taskno - 1);
                    tasks.list().remove(taskno - 1);
                    return ui.removedMessageString(task) + "%n" + ui.listMessageString(tasks.list());
                } else {
                    return ui.validNumberMessageString();
                }
            } catch (NumberFormatException e) {
                return ui.validNumberMessageString();
            }
        } else if (userinput.length() >= 6 && userinput.substring(0, 5).equalsIgnoreCase("find ")) {
            return ui.findmsgString() + "%n" + ui.printListString(tasks.contains(userinput.substring(5)));

        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
