public class Store {
    String[] items = new String[100];
    int numOfItems = 0;

    public void add(String item) {
        items[numOfItems] = item;
        numOfItems++;
        MessagePrint.print("added: " + item);
    }

    public void list() {
        String listOfItems = "";
        for(int i = 0; i < numOfItems; ++i) {
            listOfItems += (i + 1) + ". " + items[i] + "\n";
        }
        MessagePrint.print(listOfItems);
    }
}
