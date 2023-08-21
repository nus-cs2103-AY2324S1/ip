public class DukeList {
    private String[] listOfTexts;
    private int count;
    public DukeList() {
        this.listOfTexts = new String[100];
        this.count = 0;
    }

    /**
     * Function to store text written by user into a list.
     * */
    public void addItem(String text) {
        this.listOfTexts[count] = text;
        this.count++;
        System.out.printf("added: %s%n", text);
    }

    /**
     * Function to display list back to user when requested.
     * */
    public void printList() {
        for (int i = 0; i < this.count; i++) {
            System.out.printf("%d. %s%n", i+1, this.listOfTexts[i]);
        }
    }
}
