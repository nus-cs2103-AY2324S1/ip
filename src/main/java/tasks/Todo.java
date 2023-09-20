package tasks;

/**
 * Adapted from Partial Solution provided by https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html
 * .
 * A child class of Tasks to create tasks without any date/time attached to it e.g., visit new theme park.
 * .
 * The {@code Todo} class represents a specific type of task called "to-do" in a task management application.
 * It is a child class of the {@code Task} class and inherits its properties and methods.
 * .
 * A to-do task is a task without any date or time attached to it. It includes a task description and
 * a boolean flag indicating whether the task is done or not.
 * .
 * This class provides a constructor to create a to-do task and overrides methods to customize its
 * string representation using {@code toString()} and data representation using {@code getData()}.
 */
public class Todo extends Task {

    /**
     * Public constructor to create a todo task
     *
     * @param description task description
     * @param isDone      boolean on whether task is done
     */
    public Todo(String description, Boolean isDone) {

        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getData() {
        String marked = isDone ? "1" : "0";
        return "T | " + marked + " | " + this.taskDesc;
    }
}
