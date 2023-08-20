public class Storage {
    private String[] tasks;
    private int count;

    public Storage() {
        this.tasks = new String[100];
        this.count = 0;
    }

    public void add(String description) {
        this.tasks[this.count] = description;
        this.count++;
        System.out.println("\tadded: " + description);
    }

    public void list() {
        for (int i = 0; i < this.count; i++) {
            System.out.printf("\t%d. %s\n", i + 1, this.tasks[i]);
        }
    }
}
