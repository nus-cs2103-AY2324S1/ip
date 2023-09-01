package ballsorting;
import java.util.Scanner;
public class Ui {
    private Scanner sc;
    public Ui(Scanner sc) {
        this.sc = sc;
    }
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
