package rat.storage;
public class ToDo extends Task {
    protected ToDo (String name) {
        super(name);
    }

    @Override
    public String toString() {
        String taskType = "[T]";
        return taskType + super.toString();
    }

}
