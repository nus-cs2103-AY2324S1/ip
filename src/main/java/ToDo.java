public class ToDo extends Task {
    public ToDo(String todo) {
        super(todo);
    }
    @Override
    public String taskString() {
        String output = "[T]" + super.taskString();
        return output;
    }
}
