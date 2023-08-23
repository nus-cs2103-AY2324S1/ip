public class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void markDone() {
        isDone = true;
        System.out.printf("        [X] %s \n", name);
    }

    public void unmarkDone() {
        isDone = false;
        System.out.printf("        [ ] %s \n", name);
    }

    private String checkDone() {
        return isDone ? "X" : " ";
    }

    public void printTask(int index) {
        System.out.printf("      %d.[%s] %s \n", index, checkDone(), name);
    }
}
