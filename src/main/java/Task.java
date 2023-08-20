public class Task {

    String list;

    int taskIndex;

    boolean completed;

    public void setList(String list, int taskIndex) {
        this.list = list;
        this.taskIndex = taskIndex;
        completed = false;
    }

    public String getList() {
        return this.list;
    }

    public int getToDoIndex() {
        return this.taskIndex;
    }

    public void setCompleted(boolean flag) {
        this.completed = flag;
    }

    public String getCompleted() {
        return this.completed ? "[X]" : "[ ]";
    }
}
