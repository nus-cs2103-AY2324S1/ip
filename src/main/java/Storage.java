/**
 * The Storage class contains a fixed sized array that
 * stores the input from the parser class and can also
 * display the stored values to the user
 *
 * @author Zi Xiang
 * @version CS2103 AY23/24 Sem 1
 */
public class Storage {
    // Initialising variables and objects
    String[] storage = new String[100];
    int index = 0;
    UI ui = new UI();

    /** Add item to the storage */
    public void add(String item) {
        ui.line();
        if (index >= 100){
            System.out.println("Storage at maximum capacity");
        } else {
            storage[index] = item;
            index += 1;
            System.out.println("added: " + item);
        }
        ui.line();
    }

    /** Display Items in storage */
    public void display() {
        ui.line();
        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + ". " + storage[i]);
        }
        ui.line();
    }
}
