package duke;

import java.time.LocalDate;

/**
 * Parses the user input.
 */
public class Parser {
    private String command;
    private TaskList taskList;
    private boolean isEnd = false;

    public Parser(String command) {
        this.command = command;
    }

    public Parser(String command, TaskList taskList) {
        this.command = command;
        this.taskList = taskList;
    }

    /**
     * Parses the user input and initiate following operations.
     *
     * @return The resulting message.
     */
    public String parse() {
        try {
            if (command.startsWith("list")) {
                return this.taskList.listTasks();
            } else if (command.startsWith("mark")) {
                return this.taskList.markTask(Integer.valueOf(command.split(" ")[1]) - 1);
            } else if (command.startsWith("todo")) {
                ToDo newToDo = createToDoFromCommand();
                return this.taskList.addTask(newToDo);
            } else if (command.startsWith("deadline")) {
                Deadline newDeadline = createDeadlineFromCommand();
                return this.taskList.addTask(newDeadline);
            } else if (command.startsWith("event")) {
                Event newEvent = createEventFromCommand();
                return this.taskList.addTask(newEvent);
            } else if (command.startsWith("fixedduration")) {
                FixedDurationTask newFixedDurationTask = createFixedDurationTaskFromCommand();
                return this.taskList.addTask(newFixedDurationTask);
            } else if (command.startsWith("delete")) {
                return this.taskList.deleteTask(Integer.valueOf(command.split(" ")[1]) - 1);
            } else if (command.startsWith("find")) {
                String keyword = command.split(" ", 2)[1];
                return this.taskList.findTask(keyword);
            } else if (command.startsWith("bye")) {
                this.isEnd = true;
                return Ui.farewellMessage();
            } else {
                throw new DukeException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            Ui.printException(e);
        }
        return "Invalid command.";
    }

    /**
     * Creates the new FixedDurationTask from the command.
     *
     * @return The new FixedDurationTask.
     */
    private FixedDurationTask createFixedDurationTaskFromCommand() {
        String name = command.split(" /for ", 2)[0].split(" ", 2)[1];
        String duration = command.split(" /for ", 2)[1];
        FixedDurationTask newFixedDurationTask = new FixedDurationTask(name, duration);
        return newFixedDurationTask;
    }

    /**
     * Creates the new Event from the command.
     *
     * @return The new Event.
     */
    private Event createEventFromCommand() {
        LocalDate startTime = LocalDate.parse(command.split(" /from ", 2)[1]
                .split(" /to ", 2)[0]);
        LocalDate endTime = LocalDate.parse(command.split(" /to ", 2)[1]);
        String name = command.split(" /from ", 2)[0].split(" ", 2)[1];
        Event newEvent = new Event(name, startTime, endTime);
        return newEvent;
    }

    /**
     * Creates the new Deadline from the command.
     *
     * @return The new Deadline.
     */
    private Deadline createDeadlineFromCommand() {
        LocalDate deadline = LocalDate.parse(command.split(" /by ", 2)[1]);
        String name = command.split(" /by ", 2)[0].split(" ", 2)[1];
        Deadline newDeadline = new Deadline(name, deadline);
        return newDeadline;
    }

    /**
     * Creates the new ToDo from the command.
     *
     * @return The new ToDo.
     */
    private ToDo createToDoFromCommand() {
        if (command.split(" ", 2).length == 1) {
            throw new DukeException(" OOPS!!! The description of a todo cannot be empty.");
        }
        ToDo newToDo = new ToDo(command.split(" ", 2)[1]);
        return newToDo;
    }

    /**
     * Returns if the parser is ended.
     */
    public boolean isEnd() {
        return this.isEnd;
    }
}
