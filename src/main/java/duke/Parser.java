package duke;

import java.time.LocalDate;

/**
 * Parses the user input.
 */
public class Parser {
    private String command;
    private Storage storage;
    private TaskList taskList;
    private boolean isEnd = false;

    public Parser(String command, Storage storage, TaskList taskList) {
        this.command = command;
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Parses the user input and initiate following operations.
     */
    public String parse() {
        try{
            if (command.startsWith("list")) {
                return this.taskList.listTasks();
            } else if (command.startsWith("mark")) {
                return this.taskList.markTask(Integer.valueOf(command.split(" ")[1]) - 1);
            } else if (command.startsWith("todo")) {
                if (command.split(" ", 2).length == 1) {
                    throw new DukeException(" OOPS!!! The description of a todo cannot be empty.");
                }
                ToDo newToDo = new ToDo(command.split(" ", 2)[1]);
                return this.taskList.addTask(newToDo);
            } else if (command.startsWith("deadline")) {
                LocalDate deadline = LocalDate.parse(command.split(" /by ", 2)[1]);
                String name = command.split(" /by ", 2)[0].split(" ", 2)[1];
                Deadline newDeadline = new Deadline(name, deadline);
                return this.taskList.addTask(newDeadline);
            } else if (command.startsWith("event")) {
                LocalDate startTime = LocalDate.parse(command.split(" /from ", 2)[1]
                        .split(" /to ", 2)[0]);
                LocalDate endTime = LocalDate.parse(command.split(" /to ", 2)[1]);
                String name = command.split(" /from ", 2)[0].split(" ", 2)[1];
                Event newEvent = new Event(name, startTime, endTime);
                return this.taskList.addTask(newEvent);
            } else if (command.startsWith("delete")) {
                return this.taskList.deleteTask(Integer.valueOf(command.split(" ")[1]) - 1);
            } else if (command.startsWith("bye")) {
                this.isEnd = true;
                return Ui.farewellMessage();
            } else if (command.startsWith("find")) {
                String keyword = command.split(" ", 2)[1];
                return this.taskList.findTask(keyword);
            } else {
                throw new DukeException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            Ui.printException(e);
        }
        return "Invalid command.";
    }

    /**
     * Returns if the parser is ended.
     */
    public boolean isEnd() {
        return this.isEnd;
    }
}
