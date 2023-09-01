package ballsorting;
import java.util.Scanner;
public class Ui {
    private Scanner sc;
    /**
     * Creates a new instance of Ui.
     * @param sc Scanner that scans user input.
     */
    public Ui(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Scans the user input.
     * @param taskList List of tasks in chatbot.
     */
    public void scan(TaskList taskList) {
        Parser parser = new Parser(sc);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                parser.parseUserInput(input, taskList);
            }
        }
    }
}
