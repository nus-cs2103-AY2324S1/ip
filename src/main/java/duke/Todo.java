package duke;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.tag = Tag.T;
    }

    @Override
    public String toString(){
        return this.description;
    }


}
