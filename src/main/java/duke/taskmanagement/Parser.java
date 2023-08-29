package duke.taskmanagement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    public Ui ui;
    public TaskList ls;
    //String cmd = ui.readInCmd();
    public Parser(Ui ui, TaskList ls) {
        this.ls = ls;
        this.ui = ui;
    }

    public void readCmd(TaskList ls, String cmd) {
        while (true) {
            if (cmd.equals("bye")) {
                ui.closeScanner();
                ui.bye();
                System.exit(0);
            } else if (cmd.equals("list")) {
                int size = ls.getListSize();
                ui.printList(size, ls.getList());
                cmd = ui.readInCmd();
            } else if (cmd.contains("unmark")) {
                int index = Integer.parseInt(cmd.substring(7, 8));
                ls.unmark(index);
                cmd = ui.readInCmd();
            } else if (cmd.contains("mark")) {
                int index = Integer.parseInt(cmd.substring(5, 6));
                ls.mark(index);
                cmd = ui.readInCmd();
            } else if (cmd.contains("delete")) {
                int index = Integer.parseInt(cmd.substring(7, 8));
                ls.deleteTask(index);
                cmd = ui.readInCmd();
            } else if (cmd.contains("deadline")) {
                String[] parts = cmd.split("/by");
                if (parts.length == 2) {
                    String description = parts[0].replace("deadline", "").trim(); // Remove "deadline"
                    String deadline = parts[1].trim();
                    LocalDate d1 = LocalDate.parse(deadline);
                    String formattedDate = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    Task task = new Deadline(description, formattedDate, false);
                    ls.addDeadlineTask(task);
                } else {
                    DukeException exp = new DukeException("deadline");
                    System.out.println(exp.toString());
                }
                cmd = ui.readInCmd();
            } else if (cmd.contains("todo")){
                String[] parts = cmd.split(" ", 2);
                if (parts.length == 2) {
                    String desc = parts[1].trim();
                    Task task = new ToDo(desc, false);
                    ls.addToDoTask(task);
                } else {
                    DukeException exp = new DukeException("todo");
                    System.out.println(exp.toString());
                }
                cmd = ui.readInCmd();
            } else if (cmd.contains("event")) {
                String[] parts = cmd.split("/from");
                if (parts.length == 2) {
                    String desc = parts[0].replace("event", "").trim();
                    String rest = parts[1].trim();
                    String[] restParts = rest.split("/to");
                    String from = restParts[0].trim();
                    String till = restParts[1].trim();
                    Task task = new Event(desc, from, till, false);
                    ls.addEventTask(task);
                } else {
                    DukeException exp = new DukeException("event");
                    System.out.println(exp.toString());
                }
                cmd = ui.readInCmd();
            } else if (cmd.contains("find")) {
                String[] parts = cmd.split(" ");
                String keyword = parts[1];
                ui.printFilterList(ls.find(keyword));
                cmd = ui.readInCmd();
            } else {
                DukeException exp = new DukeException("");
                System.out.println(exp.nothing());
                cmd = ui.readInCmd();
            }
        }

    }
}
