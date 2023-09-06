
package duke;

import duke.tasks.Deadline;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {

    private Storage storage;
    private TaskList tasklist;
    private Ui ui;


    private static String DIRECTORY = "./data";
    private static String PATH = "./data/duke.txt";
    private boolean isRunning = true;

    public Duke() {
        this.storage = new Storage(Duke.PATH);
        this.ui = new Ui();
        try {
            this.tasklist = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showException(e); //corupt
        }
    }

    private void start() {
        this.ui.hello();
        while(this.isRunning) {
            String s = ui.readInput();
            respond(s);
        }
    }

    private void end() throws DukeException {
        this.storage.save(this.tasklist);
        this.isRunning = false;
    }


    /**
     * Mark the idx^th element in the list.
     * @param idx index in the list(1-index)
     * @return The Task marked
     */
    public Task done(int idx) {
        this.tasklist.get(idx).mark();
        return  this.tasklist.get(idx);
    }

    /**
     * Unmark the idx^th element in the list.
     * @param idx index in the list(1-index)
     * @return The Task unmarked
     */
    public Task undone(int idx) {
        this.tasklist.get(idx).unmark();
        return this.tasklist.get(idx);
    }

    /**
     * Add a deadline task based on the description of the string
     * detailing its deadline and description.
     * @param s the string
     * @return the Deadline Tasked added
     */
    public Task addDeadline(String s) throws DukeException {
        //E|1|descr|12/4/2020 1600|12/4/2020 1700
        try {
            String[] temp = s.split(" /by ", 2);
            LocalDateTime time = LocalDateTime.parse(temp[1],
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            Task res = new Deadline(temp[0], time);
            this.tasklist.add(res);
            return res;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Not enough params");
        } catch (DateTimeParseException e) {
            throw new DukeException("Date time input wrong");
        }
    }

    /**
     * Add a todo task based on the description of the string
     * detailing its deadline.
     * @param s the string
     * @return the todo Tasked added
     */
    public Task addToDo(String s) throws DukeException {
        try {
            System.out.println(s);
            Task res = new Todo(s);
            this.tasklist.add(res);
            return res;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Not enough params");
        }
    }

    /**
     * Add a event task based on the description of the string
     * detailing its start, end and description.
     * @param s the string
     * @return the Event Tasked added
     */
    public Task addEvent(String s) throws DukeException {
        //E|1|descr|12/4/2020 1600|12/4/2020 1700
        //event project meeting /from 12/4/2020 1600 /to 12/4/2020 1700
        try {
            String[] temp = s.split(" /from ", 2);
            String [] fromto = temp[1].split(" /to ",2);
            LocalDateTime from = LocalDateTime.parse(fromto[0],
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            LocalDateTime to = LocalDateTime.parse(fromto[1],
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            if (to.isBefore(from)) {
                throw new DukeException("end earlier than start");
            }
            Task res = new Events(temp[0], from, to);
            this.tasklist.add(res);
            return res;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Not enough params");
        } catch (DateTimeParseException e) {
            throw new DukeException("Date time input wrong");
        }
    }

    /**
     * Delete the idx^th task in the list.
     * @param idx the index of the task(1-index)
     * @return the Task deleted
     */

    public Task delTask(int idx) throws DukeException {
        try {

            Task t = this.tasklist.get(idx);
            this.tasklist.del(idx);
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("task does not exist");
        }
    }

    protected String respond(String s) {
        try {
            switch (Parser.parseCommand(s)) {
                case LIST:
                    return this.ui.displayList(this.tasklist);
                case BYE:
                    end();
                    return this.ui.bye();
                case MARK:
                    int idx = Integer.parseInt(Parser.doWhat(s));
                    Task t = done(idx);
                    return this.ui.showDone(t);
                case UNMARK:
                    int idx1 = Integer.parseInt(Parser.doWhat(s));
                    Task t1 = undone(idx1);
                    return this.ui.showUnDone(t1);
                case DEADLINE:
                    Task t2 = addDeadline(Parser.doWhat(s));
                    return this.ui.showAdded(t2, this.tasklist.getSize());
                case TODO:
                    Task t3 = addToDo(Parser.doWhat(s));
                    return this.ui.showAdded(t3, this.tasklist.getSize());
                case EVENT:
                    Task t4 = addEvent(Parser.doWhat(s));
                    return this.ui.showAdded(t4, this.tasklist.getSize());
                case DELETE:
                    int id3 = Integer.parseInt(Parser.doWhat(s));
                    Task t5 = delTask(id3);
                    return this.ui.showDel(t5, this.tasklist.getSize());
                case FIND:
                    return this.ui.displayList(this.tasklist.filter(Parser.doWhat(s)));
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
       Duke duke = new Duke();
       duke.start();
    }
}
