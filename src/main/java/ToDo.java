public class ToDo extends Task {


    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getTask() {
        return String.format("[%s][T] %s", super.checkDone(), super.getName());
    }
}
