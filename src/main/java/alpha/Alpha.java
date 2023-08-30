package alpha;

// The Chatbot Aplha!
public class Alpha {

    private UI ui;
    private FileHandler fh;

    private TaskList taskList;

    private Parser parser;

    public Alpha() {
        ui = new UI();
        fh = new FileHandler();
        taskList = fh.readFromFile();
        parser = new Parser(fh, taskList, ui);
        fh.checkAndCreate();
    }

    public void run() {
        ui.introduce();
        boolean isExit = false;
        while(!isExit) {
                String input = ui.read();
                Command c = parser.parse(input);
                c.execute();
                isExit = c.isExit();
        }
        ui.goodbye();
    }

    public static void main(String[] args) {
        new Alpha().run();
    }
}
