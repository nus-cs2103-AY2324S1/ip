import java.util.Scanner;

public class Ui {
    final static String HORIZONTAL_LINE = "_____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner =  new Scanner(System.in);
    }
    public String readCommand() {
        String command = scanner.nextLine();
        return command;
    }
    public static void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }
    public static void greet() {
        String logo = "       .__\n"
                + "  ____ |__| ____   ____\n"
                + "/    \\|  |/    \\ /  _  \\\n"
                + "|   |  \\  |   |  (  <_> )\n"
                + "|___|  /__|___|  /\\____/\n"
                + "     \\/        \\/";
        System.out.println("Hello from\n" + logo);
        Ui.showLine();
        System.out.println("Hello! I'm NINO!");
        System.out.println("What can I do for you?");
        Ui.showLine();
    }

    public static void showError(DukeException e) {
        Ui.showLine();
        System.out.println(e.getMessage());
        Ui.showLine();

    }
}
