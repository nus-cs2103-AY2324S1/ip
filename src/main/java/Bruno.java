import java.time.DateTimeException;
import java.util.Scanner;

public class Bruno {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Bruno(String dirPath, String fileName) {
        ui = new UI();
        storage = new Storage(dirPath, fileName);
        try {
            tasks = new TaskList(storage, ui);
            storage.loadFile();
        } catch (BrunoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        Parser parser = new Parser(tasks);
        Scanner sc = new Scanner(System.in);
        try {
            ui.display_lines();
            ui.displayGreeting();
            ui.display_lines();
            outer:
            do {
                String s = sc.nextLine();
                ui.display_lines();
                boolean flag = parser.parse(s);
                if (!flag) {
                    ui.displayBye();
                    ui.display_lines();
                    System.exit(0);
                }
                ui.display_lines();
            } while (true);
        } catch (BrunoException e) {
            System.out.println(e.getMessage());
            ui.display_lines();
        } catch (DateTimeException e) {
            System.out.println("\tDate and Time is not in correct format.");
            ui.display_lines();
        }
    }

    public static void main(String[] args) {
        Bruno bruno = new Bruno("data/", "bruno.txt");
        bruno.run();
    }
}
