
// fixing DukeException based on my understanding of exceptions 27/8/23
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private UI helper;
    private TaskList tasks;
    private Storage storage;

    public Duke(String filePath) {
        this.helper = new UI("DukeKing");
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) {
            this.tasks = new TaskList();
            helper.noFile();
        }
    }

    public static void main(String[] args) {
        new Duke("./dataTasks.txt").run();
    }

    public void run() {
        helper.welcome();
        // setting up
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        boolean done = false;

        // looping in the program
        while (done != true) {
            // end the program
            try {
                done = Parser.parse(string, tasks, helper, storage);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                helper.printLine();
                string = sc.nextLine();
            }
        }
        // end the program
        sc.close();
        helper.bye();
    }
}
