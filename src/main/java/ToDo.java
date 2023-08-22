public class ToDo extends Task {

    public ToDo(String title) {
        super(title, false);
    }

    @Override
    public String toString() {
        if (this.done == true) {
            return "[T] " + "[X] " + this.title;
        }
        return "[T] " + "[ ] " + this.title;
    }
}
