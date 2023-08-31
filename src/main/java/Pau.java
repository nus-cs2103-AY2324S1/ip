import java.util.Scanner;

public class Pau {
    private TaskList list;
    private Storage storage;
    private Ui ui;


    public Pau(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
    }

    public void run() {
        this.ui.introduction();
        String input;
        Scanner scan = new Scanner(System.in);
        this.list = this.storage.loadTasks();
        boolean canRun = true;

        list.checkList();

        while (canRun) {
            input = scan.nextLine();
            canRun = Parser.parseCommand(input, list);
            this.storage.saveTasksToFile(list);
        }
        this.ui.exit();

    }

    /**
     * Prints out a reminder to user that command is invalid.
     */
    public static void invalidCommand() {
        System.out.println("can you follow instructions");
    }

    public static void main(String[] args) {
        new Pau("./data/paulist.txt").run();
    }
}
