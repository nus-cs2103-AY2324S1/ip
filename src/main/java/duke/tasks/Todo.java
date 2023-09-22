package duke.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Todo)) {
            return false;
        }
        Todo t = (Todo) o;
        return t.description.equals(this.description);
    }



    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String makeFormat() {
        return String.format("%s|%d|%s\n", "T", (isDone) ? 1 :0, description);
    }

}
