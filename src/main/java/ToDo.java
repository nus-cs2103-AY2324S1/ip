public class ToDo extends Task {
    private static final long serialVersionUID = -1735846338330844537L;
    //included serialVersionUID indicated as the Task implements Serializable Interface
    public ToDo(String todo) {
        super(todo);
    }
    @Override
    public String taskString() {
        String output = "[T]" + super.taskString();
        return output;
    }


}
