import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Scanner sc = new Scanner(System.in);

    private Storage storage;

    private StoreList list;

    private UserInterface() {}

    public UserInterface(Storage storage, StoreList list) {
        this.list = list;
        this.storage = storage;
    }
    public boolean hasNextCommand() {
        return sc.hasNext();
    }

    public void start() throws DukeException {
        Task[] tasks = Parser.parseFile(this.storage);
        this.list.addTasks(List.of(tasks));
    }
    public void readCommandLine() {
        Reading: while (true) {
            String line = sc.nextLine();
            if (line.length() == 0) {
                display("Err: No command input");
                continue Reading;
            }
            String[] instruction = line.split(" ", 2);
            Commands cmd = Commands.get(instruction[0]);
            boolean hasSecondPart = instruction.length == 2;
            String response;
            switch (cmd) {
                case bye:
                    this.save();
                    break Reading;
                case list:
                    response = list.toString();
                    display(response);
                    break;
                case deadline:
                case todo:
                case event:
                    try {
                        Task task = Parser.parseTask(cmd, instruction);
                        response = list.add(task);
                        display(response);
                    } catch (DukeException e) {
                        display(e.toString());
                    }
                    break;
                case mark:
                    try {
                        response = list.markDone(instruction[1]);
                        display(response);
                    } catch (IndexOutOfBoundsException e) {
                        display("Err: Index not in range of list.");
                    }
                    break;
                case unmark:
                    try {
                        response = list.markUndone(instruction[1]);
                        display(response);
                    } catch (IndexOutOfBoundsException e) {
                        display("Err: Index not in range of list.");
                    }
                    break;
                case delete:
                    try {
                        response = list.delete(instruction[1]);
                        display(response);
                    } catch (IndexOutOfBoundsException e) {
                        display("Err: Index not in range of list.");
                    }
                    break;
                default:
                    response = "Err: Unknown command - " + instruction[0];
                    display(response);
                    break;
            }
        }
        sc.close();
    }

    public void save() {
        String saveText = list.showSaveText();
        storage.write(saveText);
    }

    public void exit() {
        this.save();
        sc.close();
    }

    private static void display(String s) {
        System.out.println(wrapper(s));
    }

    private static String wrapper(String line) {
        String frame = "=====================";
        return String.format("%s\n%s", line, frame);
    }


}
