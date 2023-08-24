public class ToDoTask extends Task{
    public static String type = "T";
    public ToDoTask(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                this.type,
                this.checkIsDone() ? "X" : " ",
                this.getName());
    }
}
