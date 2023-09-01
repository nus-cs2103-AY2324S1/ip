package duke.tasks;

/**
 * Adapted from Partial Solution given in https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html under Level-4
 * Todo class extends from the parent class Task
 * ToDos are tasks without any date/time attached to it e.g., visit new theme park
 */

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
