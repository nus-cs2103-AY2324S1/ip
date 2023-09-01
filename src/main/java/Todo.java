public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String getTaskDesc() {
        if (!this.getDone()) {
            return "T |" + " 0 | " + this.getName();
        } else {
            System.out.println("a");
            return "T |" + " 1 | " + this.getName();
        }
    }
    @Override
    public String toString() {
        if (!this.getDone()) {
            System.out.println("TaskDesc: " + this.getTaskDesc());
            return "[T][ ] " + this.getName();
        } else {
            return "[T][X] " + this.getName();
        }
    }
}
