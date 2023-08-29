package duke;

/**
 * A Task which has only a description.
 * Has a tag T.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo with description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
        this.tag = Tag.T;
    }

    @Override
    public String toString(){
        return this.description;
    }


}
