import java.time.LocalDate;
import java.util.Scanner;

public class Sisyphus {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Sisyphus() {
        ui = new Ui();
        storage = new Storage();
        tasks = storage.loadData();
        parser = new Parser();
    }


    private static final String NAME = "Sisyphus";
    private static final String HORIZONTAL_LINE = "_________________________________";
    private static final String LOGO = "\n" +
            "      ,-'\"\"\"`-.\n" +
            "    ,'         `.\n" +
            "   /        `    \\\n" +
            "  (    /          )\n" +
            "  |             \" |\n" +
            "  (               )\n" +
            " `.\\\\          \\ /\n" +
            "   `:.     , \\ ,\\ _\n" +
            " WE   `:-.___,-`-.{\\)\n" +
            " MUST  `.        |/ \\\n" +
            " GO      `.        \\ \\\n" +
            " ON        `-.     _\\,)\n" +
            "              `.  |,-||\n" +
            "                `.|| ||\n";
    public void run() {
        ui.greet();
        boolean isChatting = true;
        while (isChatting) {
            try {
                String fullCommand = ui.readLine();
                parser.run(fullCommand, tasks, storage, ui);
                isChatting = parser.getActiveStatus();
            } catch (SisyphusException e){
                System.out.println(e.getMessage());
            }
        }
        ui.exit();
    }
    public static void main(String[] args) throws SisyphusException{
        Sisyphus sisyphus = new Sisyphus();
        sisyphus.run();
    }

}
