public class ToDo extends Task {
    public ToDo(String todo) {
        super(todo);
    }

    public ToDo(String todo, boolean isDone) {
        super(todo, isDone);
    }
    @Override
    public String taskString() {
        String output = "[T]" + super.taskString();
        return output;
    }

    @Override
    public String saveTaskString() {
        String status = (this.isDone ? "1" : "0");
        return "T" + "|" + status + "|" + this.description;
    }

}
