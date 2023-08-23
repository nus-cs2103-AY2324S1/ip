public class Task {
    private final String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void printTask(int index) {
        System.out.printf("      %d. %s \n", index, name);
    }
}
