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
    String entered = "";
    Scanner s = new Scanner(System.in);
    UI ui = new UI();
    Storage storage = new Storage();

    /**
     * Method that runs the parser
     */
    public void run(){
        while (true) {
            entered = s.nextLine();
            if (entered.equals("bye")) {
                break;
            } else if (entered.equals("list")){
                storage.display();
            } else {
                storage.add(entered);
            }
        }
    }
}
