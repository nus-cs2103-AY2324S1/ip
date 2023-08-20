public class Task {

    String list;

    int taskIndex;

    boolean completed;

    public Task(int index) {
        this.list = "";
        this.taskIndex = index;
        this.completed = false;
    }

    public String setList(String list) {
        this.list = list;
        return "added: " + list;
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

    public String setMarked() {
        this.completed = true;
        return "Nice! I've marked this task as done:\n" + "  " + this.getCompleted() + " " + this.list;
    }

    public String setUnmarked() {
        this.completed = false;
        return "OK, I've marked this task as not done yet:\n" + "  " + this.getCompleted() + " " + this.list;
    }

    public String getCompleted() {
        return this.completed ? "[X]" : "[ ]";
    }
}
