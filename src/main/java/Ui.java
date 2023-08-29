import java.util.Scanner;

public class Ui {
    private static final String SEPARATOR = "    ____________________________________________________________\n";
    private static final String INDENTATION = "     ";
    // https://www.twitchquotes.com/copypastas/5210
    private static final String ASCII_ART =
                   INDENTATION + "  ⠁⡼⠋⠀⣆⠀⠀⣰⣿⣫⣾⢿⣿⣿⠍⢠⠠⠀⠀⢀⠰⢾⣺⣻⣿⣿⣿⣷⡀⠀\n" +
                   INDENTATION + "  ⣥⠀⠀⠀⠁⠀⠠⢻⢬⠁⣠⣾⠛⠁⠀⠀⠀⠀⠀⠀⠀⠐⠱⠏⡉⠙⣿⣿⡇⠀\n" +
                   INDENTATION + "  ⢳⠀⢰⡖⠀⠀⠈⠀⣺⢰⣿⢻⣾⣶⣿⣿⣶⣶⣤⣤⣴⣾⣿⣷⣼⡆⢸⣿⣧⠀\n" +
                   INDENTATION + "  ⠈⠀⠜⠈⣀⣔⣦⢨⣿⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣅⣼⠛⢹⠀\n" +
                   INDENTATION + "  ⠀⠀⠀⠀⢋⡿⡿⣯⣭⡟⣟⣿⣿⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⡘⠀\n" +
                   INDENTATION + "  ⡀⠐⠀⠀⠀⣿⣯⡿⣿⣿⣿⣯⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⣉⢽⣿⡆⠀⠀\n" +
                   INDENTATION + "  ⢳⠀⠄⠀⢀⣿⣿⣿⣿⣿⣿⣿⠙⠉⠉⠉⠛⣻⢛⣿⠛⠃⠀⠐⠛⠻⣿⡇⠀⠀\n" +
                   INDENTATION + "  ⣾⠄⠀⠀⢸⣿⣿⡿⠟⠛⠁⢀⠀⢀⡄⣀⣠⣾⣿⣿⡠⣴⣎⣀⣠⣠⣿⡇⠀⠀\n" +
                   INDENTATION + "  ⣧⠀⣴⣄⣽⣿⣿⣿⣶⣶⣖⣶⣬⣾⣿⣾⣿⣿⣿⣿⣽⣿⣿⣿⣿⣿⣿⡇⠀⠀\n" +
                   INDENTATION + "  ⣿⣶⣈⡯⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⣹⢧⣿⣿⣿⣄⠙⢿⣿⣿⣿⠇⠀⠀\n" +
                   INDENTATION + "  ⠹⣿⣿⣧⢌⢽⣻⢿⣯⣿⣿⣿⣿⠟⣠⡘⠿⠟⠛⠛⠟⠛⣧⡈⠻⣾⣿⠀⠀⠀\n" +
                   INDENTATION + "  ⠀⠈⠉⣷⡿⣽⠶⡾⢿⣿⣿⣿⢃⣤⣿⣷⣤⣤⣄⣄⣠⣼⡿⢷⢀⣿⡏⠀⠀⠀\n" +
                   INDENTATION + "  ⠀⠀⢀⣿⣷⠌⣈⣏⣝⠽⡿⣷⣾⣏⣀⣉⣉⣀⣀⣀⣠⣠⣄⡸⣾⣿⠃⠀⠀⠀\n" +
                   INDENTATION + "  ⠀⣰⡿⣿⣧⡐⠄⠱⣿⣺⣽⢟⣿⣿⢿⣿⣍⠉⢀⣀⣐⣼⣯⡗⠟⡏⠀⠀⠀⠀\n" +
                   INDENTATION + "  ⣰⣿⠀⣿⣿⣴⡀⠂⠘⢹⣭⡂⡚⠿⢿⣿⣿⣿⡿⢿⢿⡿⠿⢁⣴⣿⣷⣶⣦⣤\n";
    private static final String INTRO = INDENTATION + "Oi! I'm Grumpy Gordon. Why are you bothering me?\n";
    private static final String OUTRO = INDENTATION + "Bye. Hope to never see you again.\n";
    private final Scanner scanner;
    private final TaskList tasks;
    private final Storage storage;

    public Ui(TaskList tasks, Storage storage) {
        this.scanner = new Scanner(System.in);
        this.tasks = tasks;
        this.storage = storage;
    }

    public void run() {
        Command command = null;
        this.showIntroMessage();

        do {
            try {
                String userInput = this.scanner.nextLine();
                command = Parser.parseCommand(userInput);
                command.execute(tasks, this, storage);
            } catch (GrumpyGordonException e) {
                this.showErrorMessage(e);
            }
        } while (!command.isExit());

        this.showOutroMessage();
        scanner.close();
    }
    public void showIntroMessage() {
        System.out.println(SEPARATOR + ASCII_ART + "\n" + INTRO + SEPARATOR);
    }
    public void showOutroMessage() {
        System.out.println(SEPARATOR + OUTRO + SEPARATOR);
    }
    public void showErrorMessage(GrumpyGordonException e) {
        System.out.println(SEPARATOR + e.getMessage() + SEPARATOR);
    }
    public void showCommandMessage(String message) {
        System.out.println(SEPARATOR + message + SEPARATOR);
    }
}
