public class Event implements Task {
    private final String name;
    private final String start;
    private final String end;
    private boolean isDone;

    public Event(String input) {
        String[] fields = input.split("/");
        this.name = fields[0].substring("event ".length(), fields[0].length() - 1);
        this.start = fields[1].substring("from ".length(), fields[1].length() - 1);
        this.end = fields[2].substring("to ".length());
        this.isDone = false;
    }

    public String getTask() {
        return String.format("[%s][E] %s (from: %s to: %s)", checkDone(), name, start, end);
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
