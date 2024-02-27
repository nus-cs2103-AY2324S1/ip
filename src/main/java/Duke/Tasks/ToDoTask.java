package Duke.Tasks;

public class ToDoTask extends Task {

    private String taskDesc;

    public ToDoTask(String name) {

        super(name);
        taskDesc = name;
    }

    @Override
    public String toString() {
        return "[T] " + "| " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if ((!(o instanceof ToDoTask))) {
            return false;
        }
        ToDoTask obj = (ToDoTask) o;
        if (obj.taskDesc.equals(this.taskDesc)) {
            return true;
        }
        return false;
    }
}
