package duke;

import java.time.LocalDate;

public class Parser {

    private Ui ui = new Ui();
    private TaskList list;
    private String filepath;

    /** Creates a parser to parse commands inputed by user.
     *
     * @param list List to be updated by each command.
     */
    public Parser(TaskList list, String filepath) {
        this.filepath = filepath;
        this.list = list;
    }

    /** Parses the input of the user.
     *
     * @param msg Inputed string by user.
     * @return Whether the bot should wait for next command or stop.
     */
    public String parse(String msg) {
        String[] input = msg.split(" ", 2);
        String command = input[0];
        String specifications = input.length > 1 ? input[1] : "";
        switch (command) {
        case "bye":
            Storage.updateStorage(this.filepath, this.list);
            return ui.farewell();
        case "list":
            return this.list.printList();
        case "mark":
            if (specifications.isEmpty()) {
                throw new IllegalArgumentException("Please indicate task number.");
            }
            int index = Integer.parseInt(specifications) - 1;
            return this.list.mark(index);
        case "unmark":
            if (specifications.isEmpty()) {
                throw new IllegalArgumentException( "Please indicate task number.");
            }
            int i = Integer.parseInt(specifications) - 1;
            return this.list.unmark(i);
        case "todo":
            if (specifications.isEmpty()) {
                throw new IllegalArgumentException("OOPS!!! The description of a todo cannot be empty.");
            }
            Task toDoTask = new ToDo(specifications, false);
            this.list.store(toDoTask);
            return this.ui.showTaskAdded(toDoTask, this.list.length());
        case "deadline":
            try {
                String[] splits = specifications.split("/by", 2);
                String description = splits[0];
                LocalDate date = DateParser.parseDate(splits[1]);
                Task deadlineTask = new Deadline(description, false, date);
                this.list.store(deadlineTask);
                return this.ui.showTaskAdded(deadlineTask, this.list.length());
            } catch (ArrayIndexOutOfBoundsException error) {
                throw new IllegalArgumentException(
                        "OOPS!!! The description of a deadline must have <task> /by <time>.");
            }
        case "event":
            try {
                String[] split = specifications.split("/from", 2);
                String event = split[0];
                String[] timings = split[1].split("/to", 2);
                LocalDate start = DateParser.parseDate(timings[0]);
                LocalDate end = DateParser.parseDate(timings[1]);
                Task eventTask = new Event(event, false, start, end);
                this.list.store(eventTask);
                return this.ui.showTaskAdded(eventTask, this.list.length());
            } catch (ArrayIndexOutOfBoundsException error) {
                throw new IllegalArgumentException(
                        "OOPS!!! The description of an event must have <task> /from <start> /to <end>.");
            }
        case "delete":
            if (specifications.isEmpty()) {
                throw new IllegalArgumentException("Please indicate task number.");
            }
            try {
                int number = Integer.parseInt(specifications);
                Task taskToRemove = this.list.retrieve(number - 1);
                this.list.delete(number - 1);
                return this.ui.showTaskDeleted(taskToRemove, this.list.length());
            } catch (IndexOutOfBoundsException error) {
                throw new IllegalArgumentException("OOPS!!! I could not find any task in that position.");
            }
        case "find":
            if (specifications.isEmpty()) {
                throw new IllegalArgumentException("Please indicate word to find");
            }
            return this.list.find(specifications);
        default:
            throw new IllegalArgumentException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
