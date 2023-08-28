import java.util.*;
public class Duke {
    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    private boolean botInUse = true;
    public Duke(){
        this("src/main/data/duke.txt");
    }

    public Duke(String filepath) {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.parser = new Parser(ui, taskList, botInUse, storage);
    }
    public void run(){
        storage.loadFileToTaskManager(taskList);
        ui.greetings();
        Scanner sc = new Scanner(System.in);
        while(botInUse) {
            String input = sc.nextLine();
            try {
                parser.listen(input);
            } catch (InvalidUserInputException e) {
                ui.invalidInputRes();
            }
            botInUse = parser.updateBotUsage();
        }
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}
