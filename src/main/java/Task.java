import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;
import java.util.*;

public class Task {
    public String name;
    public boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void SetDoneOrNot(boolean doneOrNot) {
        this.done = doneOrNot;
    }
}

class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        String done = this.done ? "[X]" : "[ ]";
        return "[T]" + done + " " + this.name;
    }
}

class Deadline extends Task {
    String due;
    public Deadline(String description) {
        super(description.split(" /by ")[0]);
        this.due = description.split(" /by ")[1];
    }

    @Override
    public String toString() {
        String done = this.done ? "[X]" : "[ ]";
        return "[D]" + done + " " + this.name
                + " (by: " + this.due + ")";
    }
}

class Event extends Task {
    String start;
    String end;
    public Event(String description) {
        super(description.split(" /from ")[0]);
        this.start = description.split(" /from ")[1].split(" /to ")[0];
        this.end = description.split(" /from ")[1].split(" /to ")[1];
    }

    @Override
    public String toString() {
        String done = this.done ? "[X]" : "[ ]";
        return "[E]" + done + " " + this.name
                + " (from: " + this.start + " to: " + this.end + ")";
    }
}
