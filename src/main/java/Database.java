public class Database {
    private String[] db = new String[100];
    private int total = 0;  // total indicates the first free slot

    public void addEvent(String event) {
        this.db[this.total] = event;
        this.total++;
        UI.displayCheems();
        System.out.printf("I have added \"%s\" for you!%n", event);
    }

    public void displayData() {
        UI.displayCheems();
        for (int i = 0; i < this.total; i++) {
            System.out.printf("%d. %s%n", i + 1, this.db[i]);
        }
    }
}
