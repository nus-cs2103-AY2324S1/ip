package duke;

import java.time.LocalDate;

public class Parser {

    private Ui ui = new Ui();
    private TaskList list;

    public Parser(TaskList list) {
        this.list = list;
    }

    public boolean parse(String msg) {
        String[] input = msg.split(" ", 2);
        String command = input[0];
        String specifications = input.length > 1 ? input[1] : "";
        switch (command) {
        case "bye":
            return false;
        case "list":
            this.list.printList();
            break;
        case "mark":
            if (specifications.isEmpty()) {
                throw new IllegalArgumentException("Please indicate task number.");
            }
            int index = Integer.parseInt(specifications) - 1;
            this.list.mark(index);
            break;
        case "unmark":
            if (specifications.isEmpty()) {
                throw new IllegalArgumentException("Please indicate task number.");
            }
            int i = Integer.parseInt(specifications) - 1;
            this.list.unmark(i);
            break;
        case "todo":
            if (specifications.isEmpty()) {
                throw new IllegalArgumentException("OOPS!!! The description of a todo cannot be empty.");
            }
            Task toDoTask = new ToDo(specifications, false);
            this.list.store(toDoTask);
            this.ui.showTaskAdded(toDoTask, this.list.length());
            break;
        case "deadline":
            try {
                String[] splits = specifications.split("/by", 2);
                String description = splits[0];
                LocalDate date = DateParser.parseDate(splits[1]);
                Task deadlineTask = new Deadline(description, false, date);
                this.list.store(deadlineTask);
                this.ui.showTaskAdded(deadlineTask, this.list.length());
                break;
            } catch (ArrayIndexOutOfBoundsException error) {
                throw new IllegalArgumentException("OOPS!!! The description of a deadline must have <task> /by <time>.");
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
                this.ui.showTaskAdded(eventTask, this.list.length());
                break;
            } catch (ArrayIndexOutOfBoundsException error) {
                throw new IllegalArgumentException("OOPS!!! The description of an event must have <task> /from <start> /to <end>.");
            }
        case "delete":
            if (specifications.isEmpty()) {
                throw new IllegalArgumentException("Please indicate task number.");
            }
            try {
                int number = Integer.parseInt(specifications);
                Task taskToRemove = this.list.retrieve(number - 1);
                this.list.delete(number - 1);
                this.ui.showTaskDeleted(taskToRemove, this.list.length());
                break;
            } catch (IndexOutOfBoundsException error) {
                throw new IllegalArgumentException("OOPS!!! I could not find any task in that position.");
            }
        default:
            throw new IllegalArgumentException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }
}
