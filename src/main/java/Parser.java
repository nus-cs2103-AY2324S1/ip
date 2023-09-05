
public class Parser {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    public Parser(Ui ui, Storage storage, TaskList tasklist) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasklist;
    }
    public static Task ParseFileInfo(String input) throws DukeException {
        String regex = " : ";
        String[] split = input.split(regex);
        if (split[0].equals("T")) {
            ToDo todo = new ToDo(split[2]);
            if (split[1] == "1") {
                todo.setdone();
            }
            return todo;
        } else if (split[0].equals("D")) {
            Deadline deadline = new Deadline(split[2], split[3]);
            if (split[1] == "1") {
                deadline.setdone();
            }
            return deadline;
        } else if (split[0].equals("E")) {
            Event event = new Event(split[2], split[3], split[4]);
            if (split[1] == "1") {
                event.setdone();
            }
            return event;
        } else {
            throw new DukeException("cannot create task");
        }
    }

    public void parse(String userinput) throws DukeException {
        if (userinput.equalsIgnoreCase("list")) {
            ui.listlist(tasks.list());
        } else if (userinput.length() >= 6 && userinput.substring(0, 5).equalsIgnoreCase("mark ")) {
            try {
                int taskno = Integer.parseInt(userinput.substring(5));
                if (taskno <= tasks.list().size()) {
                    Task task = tasks.list().get(taskno - 1);
                    task.setdone();
                    ui.markedmsg(task);
                } else {
                    ui.invalidmark();
                }
            } catch (NumberFormatException e) {
                ui.invalidmark();
            }
        } else if (userinput.length() >= 4 && userinput.substring(0, 4).equalsIgnoreCase("todo")) {

            ToDo todo = new ToDo(userinput.substring(4));
            tasks.list().add(todo);
            ui.addedmsg(todo);
            ui.listmsg(tasks.list());
            storage.updatefile(tasks.list());

        } else if (userinput.length() >= 8 && userinput.substring(0, 8).equalsIgnoreCase("deadline")) {
            String[] segments = userinput.split(" /by ");
            Deadline deadline = new Deadline(segments[0].substring(8), segments[1]);
            tasks.list().add(deadline);
            ui.addedmsg(deadline);
            ui.listmsg(tasks.list());
            storage.updatefile(tasks.list());
        } else if (userinput.length() >= 7 && userinput.substring(0, 6).equalsIgnoreCase("event ")) {
            String[] segments1 = userinput.split(" /from ");
            String from = segments1[1].split(" /to ")[0];
            String[] segments2 = segments1[1].split(" /to ");
            Event event = new Event(segments1[0].substring(5), from, segments2[1]);
            tasks.list().add(event);
            ui.addedmsg(event);
            ui.listmsg(tasks.list());
            storage.updatefile(tasks.list());
        } else if (userinput.length() >= 8 && userinput.substring(0, 7).equalsIgnoreCase("delete ")) {
            try {
                int taskno = Integer.parseInt(userinput.substring(7));
                if (taskno <= tasks.list().size()) {
                    Task task = tasks.list().get(taskno - 1);
                    tasks.list().remove(taskno - 1);
                    ui.removedmsg(task);
                    ui.listmsg(tasks.list());
                } else {
                    ui.validnumbermsg();
                }
            } catch (NumberFormatException e) {
                ui.validnumbermsg();
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
