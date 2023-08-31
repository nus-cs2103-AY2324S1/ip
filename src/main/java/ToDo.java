public class ToDo extends Task {
    private static final long serialVersionUID = -1735846338330844537L;
    public ToDo(String todo) {
        super(todo);
    }
    @Override
    public String taskString() {
        String output = "[T]" + super.taskString();
        return output;
    }
    /*@Override
    public String fileString() {
        String output = "[T]" + super.taskString();
        return output;
    }*/


}
