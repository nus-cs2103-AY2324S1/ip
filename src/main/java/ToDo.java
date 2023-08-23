public class ToDo implements Task {
    private final String name;
    private boolean isDone;

    public ToDo(String input) {
        this.name = input.substring("todo ".length());
        this.isDone = false;
    }

    public String getTask() {
        return String.format("[%s][T] %s", checkDone(), name);
    }

    public void markDone() {
        isDone = true;
        System.out.println(ChatterChicken.INDENT_BIG + this.getTask());
    }

    public void unmarkDone() {
        isDone = false;
        System.out.println(ChatterChicken.INDENT_BIG + this.getTask());
    }

    private String checkDone() {
        return isDone ? "X" : " ";
    }
}
