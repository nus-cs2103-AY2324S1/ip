package haste.commands;

import haste.data.TaskList;
import haste.tasks.Deadline;
import haste.tasks.Event;
import haste.tasks.Task;
import haste.tasks.ToDo;
import haste.ui.Ui;

import java.time.LocalDateTime;

public class AddCommand extends Command{
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;
    private String type;
    public AddCommand(String description) {
        this.description = description;
        this.type = "t";
    }

    public AddCommand(String description, LocalDateTime end) {
        this.description = description;
        this.type = "d";
        this.end = end;
    }

    public AddCommand(String description, LocalDateTime end, LocalDateTime start) {
        this.description = description;
        this.type = "e";
        this.start = start;
        this.end = end;
    }

    public void excecute(TaskList tasks, Ui ui) {
        Task newTask = null;
        switch (this.type) {
            case "todo":
                newTask = new ToDo(this.description);
                break;
            case "deadline":
                newTask = new Deadline(this.description, this.start);
                break;
            case "event":
                newTask = new Event(this.description, this.start, this.end);
                break;
        }

        TaskList.addTask(newTask);
    }


}
