package pardiyem.logic;

import java.util.ArrayList;
import java.util.Objects;
import pardiyem.parser.Parser;
import pardiyem.task.Task;

public class Pardi {
    private final String GREETING = "\nSalve, I'm Pardi\nWhat can I do for you?\n";
    private final String BYE = "\nCiao! See you again!\n";
    private ArrayList<Task> tasklist;
    public Pardi() {
        this.tasklist = new ArrayList<Task>();
    }
    public void greeting() {
        System.out.println(GREETING);
    }

    public void exit() {
        System.out.println(BYE);
    }

    public boolean run(String in) {
        Parser parser = new Parser();
        ArrayList<String> id = parser.parseCommand(in);

        switch (id.get(0)) {
            case "0":
                this.tasklist.add(new Task(in));
                System.out.println("\nadded: " + in + "\n");
                return true;
            case "1":
                return false;
            case "2": {
                for (int i = 1; i <= this.tasklist.size(); i++) {
                    Task curr = this.tasklist.get(i - 1);
                    System.out.printf("\n%d.[%s] %s", i, curr.getStatusIcon(), curr.getDescription());
                }
                System.out.print("\n\n");
                return true;
            }
            case "3": {
                Task curr = this.tasklist.get(Integer.parseInt(id.get(1)) - 1);
                System.out.printf("\n%s", curr.markAsDone());
                System.out.printf("\n[%s] %s\n\n", curr.getStatusIcon(), curr.getDescription());
                return true;
            }
            case "4": {
                Task curr = this.tasklist.get(Integer.parseInt(id.get(1)) - 1);
                System.out.printf("\n%s", curr.markAsUndone());
                System.out.printf("\n[%s] %s\n\n", curr.getStatusIcon(), curr.getDescription());
                return true;
            }
            default:
                return true;
        }
    }
}
