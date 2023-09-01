package duke;

public class Todo extends Task {
    protected char type;
    public Todo(String description) {
        super(description);
        this.type = 'T';
    }

    @Override
    public String printDesc() {
        return "[T]" + super.printDesc();
    }
    @Override
    public String getDescription() {
        return "T~" + super.getDescription();
    }
}
