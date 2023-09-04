package ax;
import java.util.Scanner;

import ax.commands.Parser;
import ax.display.Ui;
import ax.storage.Storage;

/**
 * The main Ax Class for the chatbot
 */
public class Ax {

    /**
     * the main command for Ax chatbot
     * @param args does nothing?
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Ui.greet();
        Storage.readSave();
        while (true) {
            boolean done = Parser.getInput(scanner);
            if (done) {
                break;
            }
        }
        Ui.bye();
    }
}
