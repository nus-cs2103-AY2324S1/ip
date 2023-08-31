import java.util.Scanner;

public class Blip {


    // File path for tasks
    private static String FILE_PATH = "./data/blip.txt";

    private BlipUI ui;
    private TaskList tasks;
    private BlipStorage storage;
    private BlipParser parser;

    public Blip(String filePath) {
        this.ui = new BlipUI();
        this.storage = new BlipStorage(filePath);
        this.parser = new BlipParser();
              try {
        tasks = storage.loadFile();
        } catch (BlipException e) {
            ui.showLoadingErr();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showIntro();
        Scanner scanIn = new Scanner(System.in);

        while (true) {
            String userInput;
            userInput = scanIn.nextLine();
            Command command = parser.parse(userInput);
            if (command instanceof ByeCommand) {
                command.execute(tasks, ui, storage);
                break;
            }
            command.execute(tasks, ui, storage);
        }

    }


    public static void main(String[] args) {
        new Blip(FILE_PATH).run();
    }



}
