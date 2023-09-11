package duke.taskmanagement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    public Ui ui;
    public TaskList ls;

    /**
     * Constructor for Parser class.
     * @param ui The ui object.
     * @param ls The TaskList object.
     */
    public Parser(Ui ui, TaskList ls){
        this.ls = ls;
        this.ui = ui;
    }

    /**
     * To read the command from user input and
     * parse it to Ui object to print statement.
     * @param ls The tasklist object to handle the tasks.
     * @param cmd The string input by user.
     */
    public String readCmd(TaskList ls, String cmd) {
            if (cmd.equals("bye")) {
                ui.closeScanner();
                return ui.bye();
            } else if (cmd.equals("list")) {
                int size = ls.getListSize();
                return ui.printList(size, ls.getList());
            } else if (cmd.contains("unmark")) {
                int index = Integer.parseInt(cmd.substring(7, 8));
                return ls.unmark(index);
            } else if (cmd.contains("mark")) {
                int index = Integer.parseInt(cmd.substring(5, 6));
                return ls.mark(index);
            } else if (cmd.contains("delete")) {
                int index = Integer.parseInt(cmd.substring(7, 8));
                return ls.deleteTask(index);
            } else if (cmd.contains("deadline")) {
                String[] parts = cmd.split("/by");
                if (parts.length == 2) {
                    String description = parts[0].replace("deadline", "").trim(); // Remove "deadline"
                    String deadline = parts[1].trim();
                    LocalDate d1 = LocalDate.parse(deadline);
                    String formattedDate = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    Task task = new Deadline(description, formattedDate, false);
                    return ls.addDeadlineTask(task);
                } else {
                    DukeException exp = new DukeException("deadline");
                    return exp.toString();
                }
            } else if (cmd.contains("todo")){
                String[] parts = cmd.split(" ", 2);
                if (parts.length == 2) {
                    String desc = parts[1].trim();
                    Task task = new ToDo(desc, false);
                    return ls.addToDoTask(task);
                } else {
                    DukeException exp = new DukeException("todo");
                    return exp.toString();
                }
            } else if (cmd.contains("event")) {
                String[] parts = cmd.split("/from");
                if (parts.length == 2) {
                    String desc = parts[0].replace("event", "").trim();
                    String rest = parts[1].trim();
                    String[] restParts = rest.split("/to");
                    String from = restParts[0].trim();
                    String till = restParts[1].trim();
                    Task task = new Event(desc, from, till, false);
                    return ls.addEventTask(task);
                } else {
                    DukeException exp = new DukeException("event");
                    return exp.toString();
                }
            } else if (cmd.contains("find")) {
                String[] parts = cmd.split(" ");
                String keyword = parts[1];
                return ui.printFilterList(ls.find(keyword));
            } else {
                DukeException exp = new DukeException("");
                return exp.nothing();
            }
    }
}
