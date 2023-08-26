package pardiyem.logic;

import java.util.ArrayList;
import java.util.Objects;
import pardiyem.parser.Parser;
import pardiyem.task.Deadline;
import pardiyem.task.Event;
import pardiyem.task.Task;
import pardiyem.task.Todo;
import pardiyem.task.TaskList;

public class Pardi {
    private final String GREETING = "\nSalve, I'm Pardi\nWhat can I do for you?\n";
    private final String BYE = "\nCiao! See you again!\n";
    private TaskList tasklist;
    public Pardi() {
        this.tasklist = new TaskList();
    }
    public void greeting() {
        System.out.println(GREETING);
    }

    public void exit() {
        System.out.println(BYE);
    }

    public boolean run(ArrayList<String> id) throws NoSuchMethodException, IllegalArgumentException, ArrayIndexOutOfBoundsException {
        switch (id.get(0)) {
            case "-1":
                throw new NoSuchMethodException("Whoops, I do not recognize that command");
            case "1":
                if (!id.get(1).isEmpty()) {
                    throw new IllegalArgumentException(String.format("You used \"%s\" as an argument. A bye command shouldn't have any arguments", id.get(1)));
                }
                return false;
            case "2": {
                if (!id.get(1).isEmpty()) {
                    throw new IllegalArgumentException(String.format("You used \"%s\" as an argument. A list command shouldn't have any arguments", id.get(1)));
                }
                System.out.printf("%s\n\n", this.tasklist.toString());
                return true;
            }
            case "3": {
                System.out.print(this.tasklist.mark(id.get(1)));
                return true;
            }
            case "4": {
                System.out.print(this.tasklist.unmark(id.get(1)));
                return true;
            }
            case "5": {
                Task curr = new Todo(id.get(1));
                this.tasklist.add(curr);
                System.out.printf("\nGot it. I've added this task:\n%s\nNow you have %d task(s) in the list\n\n", curr.toString(), this.tasklist.size());
                return true;
            }
            case "6": {
                ArrayList<String> args = Deadline.parseDesc(id.get(1));
                Task curr = new Deadline(args.get(0), args.get(1));
                this.tasklist.add(curr);
                System.out.printf("\nGot it. I've added this task:\n%s\nNow you have %d task(s) in the list\n\n", curr.toString(), this.tasklist.size());
                return true;
            }
            case "7": {
                ArrayList<String> args = Event.parseDesc(id.get(1));
                Task curr = new Event(args.get(0), args.get(1), args.get(2));
                this.tasklist.add(curr);
                System.out.printf("\nGot it. I've added this task:\n%s\nNow you have %d task(s) in the list\n\n", curr.toString(), this.tasklist.size());
                return true;
            }
            case "8": {
                System.out.print(this.tasklist.delete(id.get(1)));
            } default:
                return true;
        }
    }
}
