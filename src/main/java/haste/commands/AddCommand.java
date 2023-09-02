package haste.commands;

import haste.data.TaskList;
import haste.tasks.Deadline;
import haste.tasks.Event;
import haste.tasks.Task;
import haste.tasks.ToDo;
import haste.ui.Ui;

import java.time.LocalDateTime;

public class AddCommand extends Command{
    private String desc;
    private LocalDateTime start;
    private LocalDateTime end;
    private String type;
    public AddCommand(String desc) {
        this.desc = desc;
        this.type = "t";
    }

    public AddCommand(String desc, LocalDateTime end) {
        this.desc = desc;
        this.type = "d";
        this.end = end;
    }

    public AddCommand(String desc, LocalDateTime end, LocalDateTime start) {
        this.desc = desc;
        this.type = "e";
        this.start = start;
        this.end = end;
    }

    public void execute(TaskList tasks, Ui ui) {
        Task newTask = null;
        switch (this.type) {
            case "t":
                newTask = new ToDo(this.desc, false);
                break;
            case "d":
                newTask = new Deadline(this.desc, this.end, false);
                break;
            case "e":
                newTask = new Event(this.desc, this.start, this.end, false);
                break;
        }
        tasks.addTask(newTask);
        ui.add(newTask.toString(), tasks);
    }
}
