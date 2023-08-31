import java.util.Scanner;
import java.util.ArrayList;
public class Pippi {
    private String pippi =  "⣿⡿⠷⣶⣤⣄⡀⠀⠀⠀⢀⣤⣶⣿⣿⣿⣿⣶⣤⡀⠀⠀⠀⢀⣠⣤⣶⠾⢿⣿\n" +
            "⣿⡇⠀⠀⣨⣿⣿⣷⠀⢠⣿⣿⠿⠋⢉⡉⠙⠿⣿⣿⣄⣤⣾⣿⣿⣅⠀⠀⢸⣿\n" +
            "⢸⣧⠀⣰⣿⣿⣿⡏⢀⣿⣿⡏⢠⣾⣿⣿⣷⡄⢹⣿⣿⣿⣿⣿⣿⣿⣆⠀⣼⡇\n" +
            "⠈⣿⣴⣿⣿⣿⣿⡇⠈⣿⣿⣧⣾⣿⣿⣿⣿⡇⢸⣿⣿⣿⣿⣿⣿⣿⣿⣦⣿⠁\n" +
            "⠀⢹⣿⣿⣿⣿⣿⣿⣄⠙⢿⣿⣿⣿⣿⣿⠏⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀\n" +
            "⠀⠀⢻⣿⣿⣿⣿⣿⣿⣶⣄⣈⠉⠉⣉⣠⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀\n" +
            "⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⡏⢹⣿⣿⣿⣿⡏⢹⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀\n" +
            "⣴⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣧⣼⣿⣿⣿⣿⣧⣼⣿⣿⣿⣿⣿⣿⣿⣷⣿⣿⣦\n" +
            "⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣏⡉⠛⠛⢉⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿\n" +
            "⠈⠛⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠁\n" +
            "⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⠿⠿⠿⠿⠿⠿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠹⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠹⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀";
    protected static boolean inPokeball = false;

    public void start() {
        Ui.wrapText("Hello trainer, I'm Pippi!\nWhat can I do for you?");
        ArrayList<Task> tasks = Storage.read();
        Scanner scanner = new Scanner(System.in);

        while (!this.inPokeball) {
            String userMessage = scanner.nextLine();
            Parser.reply(userMessage, tasks);
        }
    }
    public static void main(String[] args) {
        Pippi pippi = new Pippi();
        pippi.start();
    }
}
