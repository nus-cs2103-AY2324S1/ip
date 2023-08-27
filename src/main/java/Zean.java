import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The main class for the chatbot.
 *
 * @author Zhong Han
 */
public class Zean {

    public static void main(String[] args) {
        Ui.greet("Zean");
        try {
            Ui.takeInstructions();
            Ui.exit();
        } catch (FileNotFoundException e) {
            System.out.println("\tOOPS! Something went wrong with the file.\n\tShutting down now...");
        } catch (IOException e) {
            System.out.println("\t OOPS! The file cannot be created.\n\tShutting down now...");
        }

    }
}
