package max.tasks;

import max.commands.*;
import max.exception.MaxException;
import max.parser.Parser;
import max.storage.Storage;
import max.tasks.TaskList;
import max.tasks.*;
import max.ui.Ui;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate byDate;
    public Deadline(String item, LocalDate byDate) {
        super(item);
        this.byDate = byDate;
    }
    public Deadline(String item, LocalDate byDate, boolean isDone) {
        super(item, isDone);
        this.byDate = byDate;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.toString() + ")";
    }
    @Override
    public String saveItem() {
        return "D | " + super.saveItem() + "by: " + byDate.toString() + "\n";
    }
}