import java.util.Scanner;

/**
 * The Parser class is used to run the infinite loop and
 * take in inputs by the user
 *
 * @author Zi Xiang
 * @version CS2103 AY23/24 Sem 1
 */
public class Parser {
    // Initialisation of objects and variables
    String entered;
    Scanner s;
    UI ui;
    Storage storage;

    /**
     * Constructor for Parser
     */
    public Parser() {
        this.entered = "";
        this.s = new Scanner(System.in);
        this.ui = new UI();
        this.storage = new Storage();
    }
    /**
     * Method that runs the parser
     */
    public void run(){
        while (true) {
            entered = s.nextLine();
            if (entered.equals("bye")) {
                break;
            } else if (entered.equals("list")) {
                storage.display();
            } else if (entered.contains("unmark")) {
                int index = Integer.parseInt(entered.split(" ")[1]);
                this.storage.unmark(index);
            } else if (entered.contains("mark")) {
                int index = Integer.parseInt(entered.split(" ")[1]);
                this.storage.mark(index);
            } else if (entered.contains("todo")) {
                String todo = entered.substring(5);
                storage.add(new Todo(todo));
            } else if (entered.contains("deadline")) {
                String[] items = entered.substring(9).split(" /");
                storage.add(new Deadline(items[0], items[1].substring(3)));
            } else if (entered.contains("event")) {
                String[] items = entered.substring(6).split(" /");
                storage.add(new Event(items[0], items[1].substring(6), items[2].substring(4)));
            }
        }
    }
}
