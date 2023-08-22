public class List {

    // Fixed size array to keep track of items
    private String[] list;

    // Counter to keep track of position
    private int counter;


    public List() {
        list = new String[100];
        counter = 0;
    }

    // Adds an item to the list while notifying the user
    public void add(String text) {
        list[counter] = text;
        counter++;
        System.out.println("____________________________________________________________\n" +
                "added: " + text + "\n" +
                "____________________________________________________________");
    }

    // Displays all items to the user
    public void display() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                break;
            }
            System.out.println(i + ". " + list[i]);
        }
        System.out.println("____________________________________________________________");
    }
}
