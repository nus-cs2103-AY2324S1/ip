public class Deadline implements Task {
    private final String name;
    private final String end;
    private boolean isDone;

    public Deadline(String input) {
        int slash = input.indexOf('/');
        this.name = input.substring("deadline ".length(), slash - 1);
        this.end = input.substring(slash + " by ".length());
        this.isDone = false;
    }

    public String getTask() {
        return String.format("[%s][D] %s (by: %s)", checkDone(), name, end);
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
