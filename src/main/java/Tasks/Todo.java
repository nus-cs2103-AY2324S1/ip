package Tasks;

/**
 * Adapted from Partial Solution provided by https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html
 * A child class of Tasks to create tasks tasks without any date/time attached to it e.g., visit new theme park.
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