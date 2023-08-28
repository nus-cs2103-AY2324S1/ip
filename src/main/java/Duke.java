
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    private ArrayList<Task> l;
    private boolean isRunning = true;

    private Duke() {
        this.l = new ArrayList<>();
    }

    private enum Command {
        LIST, BYE, MARK, UNMARK, DEADLINE, TODO, EVENT, DELETE, UNSPECIFIED
    }

    private static final String greet = "Hello! I'm ";
    private static final String name = "siri";
    private static final String msg = "What can I do for you?";

    private static final String bye = "Bye. Hope to see you again soon!";

    private Command parseCommand(String s) {
        if (s.equals("bye")) {return Command.BYE;}
        if (s.equals("list")) {return Command.LIST;}
        if (s.startsWith("mark")) {return Command.MARK;}
        if (s.startsWith("unmark")) {return Command.UNMARK;}
        if (s.startsWith("todo")) {return Command.TODO;}
        if (s.startsWith("deadline")) {return Command.DEADLINE;}
        if (s.startsWith("event")) {return Command.EVENT;}
        if (s.startsWith("delete")) {return Command.DELETE;}
        else return Command.UNSPECIFIED;
    }

    private void hello() {
        System.out.println(greet + name);
        System.out.println(msg);
    }

    private void start() {
        hello();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine() && this.isRunning) {
            String s = sc.nextLine();
            respond(s);
        }
    }

    private void end() {
        this.isRunning = false;
        System.out.println(bye);
    }

    private void added(Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t.toString());
        System.out.println("Now I have " + l.size() + " tasks in the list");
    }

    private void del(Task t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + l.size() +  " tasks in the list");
    }

    private void respond(String s) {
        Command c = parseCommand(s);
        try {
            switch (c) {
                case LIST:
                    System.out.println("Here are the tasks in your list:");
                    int count = 1;
                    for (Task ss : this.l) {
                        String res = String.format("%d.%s", count++, ss.toString());
                        System.out.println(res);
                    }
                    break;

                case BYE:
                    end();
                    return;

                case MARK:
                    int idx = Integer.parseInt(s.substring(5));
                    this.l.get(idx - 1).mark();
                    break;

                case UNMARK:
                    int idx1 = Integer.parseInt(s.substring(7));
                    this.l.get(idx1 - 1).unmark();
                    break;

                case DEADLINE:
                    if (s.trim().length() <= 8) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String t = s.substring(9);
                    String[] temp = t.split("/by");
                    if (temp.length < 2) {
                        throw new DukeException("OOPS!!! The description of a deadline must have a deadline.");
                    }
                    Deadline d = new Deadline(temp[0], temp[1].trim());
                    this.l.add(d);
                    added(d);
                    break;

                case TODO:
                    if (s.trim().length() <= 4) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    String t1 = s.substring(5);

                    Todo temp1 = new Todo(t1);
                    this.l.add(temp1);
                    added(temp1);
                    break;

                case EVENT:
                    if (s.trim().length() <= 5) {
                        throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                    }
                    String t2 = s.substring(6);
                    String[] temp2 = t2.split("/");
                    if (temp2.length < 3) {
                        throw new DukeException("OOPS!!! The description of a event must have from and to.");
                    }
                    Events e = new Events(temp2[0], temp2[1].substring(4).trim(), temp2[2].substring(2).trim());
                    this.l.add(e);
                    added(e);
                    break;

                case DELETE:
                    int idx3 = Integer.parseInt(s.substring(7));
                    Task t3 = this.l.get(idx3 - 1);
                    this.l.remove(idx3 - 1);
                    del(t3);
                    break;

                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
