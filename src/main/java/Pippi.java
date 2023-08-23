import java.util.Scanner;
public class Pippi {
    private static boolean inPokeball = false;
    public static void wrapText(String content) {
        String line = "_____________________________________\n";
        System.out.println(line);
        System.out.println(content + "\n");
        System.out.println(line);
    }
    public static void main(String[] args) {
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
            } else {
                wrapText(userMessage);
            }
        }
    }
}
