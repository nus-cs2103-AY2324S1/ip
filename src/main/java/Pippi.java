import java.util.Scanner;
import java.util.ArrayList;

public class Pippi {

    public static void wrapText(String content) {
        String line = "_____________________________________\n";
        System.out.println(line);
        System.out.println(content);
        System.out.println(line);
    }
    public static void main(String[] args) {
        boolean inPokeball = false;
        ArrayList<String> tasks = new ArrayList<String>();
        String pippi =  "⣿⡿⠷⣶⣤⣄⡀⠀⠀⠀⢀⣤⣶⣿⣿⣿⣿⣶⣤⡀⠀⠀⠀⢀⣠⣤⣶⠾⢿⣿\n" +
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
        Scanner scanner = new Scanner(System.in);
        wrapText(pippi + "\nHello trainer, I'm Pippi!\nWhat can I do for you?");
        while (!inPokeball) {
            String userMessage = scanner.nextLine();
            if (userMessage.equalsIgnoreCase("bye")) {
                wrapText("Bye. Hope to see you again soon!");
                inPokeball = true;
            } else if (userMessage.equalsIgnoreCase("list")) {
                String all = "";
                for (int i = 0; i < tasks.size(); i++) {
                    all = all + String.valueOf(i + 1) +". " + tasks.get(i) + "\n";
                }
                wrapText(all);

            } else {
                tasks.add(userMessage);
                wrapText("added " + userMessage);
            }
        }
    }
}
