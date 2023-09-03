package Duke;

import java.util.Scanner;
import Duke.Tasks.*;
import Duke.Exceptions.*;

public class Duke {
    public static void main(String[] args) {
        Ui.printWelcome();
        Scanner sc = new Scanner(System.in);
        Ui iu = new Ui();
        Parser her = new Parser();
        TaskList tasks = new TaskList();
        boolean running = true;
        while (running) {
            String entry = sc.nextLine();
            running = her.inputs(entry, tasks, iu);
        }
    }
}
