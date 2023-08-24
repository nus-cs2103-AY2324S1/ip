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
    private boolean inPokeball = false;
    public void wrapText(String content) {
        String line = "_____________________________________\n";
        System.out.println(line + content + "\n" + line);
    }
    public void reply(String userMessage, ArrayList<Task> tasks) {
        String[] input = userMessage.split(" ", 2);
        String command = input[0];
        if (command.equalsIgnoreCase("bye")) {
            wrapText("Bye. Hope to see you again soon!");
            this.inPokeball = true;
        } else if (command.equalsIgnoreCase("list")) {
            String all = "";
            for (int i = 0; i < tasks.size(); i++) {
                Task curr = tasks.get(i);
                all = all + String.valueOf(i + 1) +"." + curr.toString() + "\n";
            }
            wrapText(all);
        } else if (command.equalsIgnoreCase("mark")) {
            int idx = Integer.parseInt(input[1]) - 1;
            tasks.get(idx).mark();
            wrapText("Nice I've marked this task as done: \n" +
                    tasks.get(idx).toString());
        } else if (command.equalsIgnoreCase("unmark")) {
            int idx = Integer.parseInt(input[1]) - 1;
            tasks.get(idx).unmark();
            wrapText("OK, I've marked this task as not done yet: \n" +
                    tasks.get(idx).toString());
        } else {
            tasks.add(new Task(userMessage));
            wrapText("added " + userMessage);
        }
    }
    public void start() {
        wrapText(pippi + "\nHello trainer, I'm Pippi!\nWhat can I do for you?");
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (!this.inPokeball) {
            String userMessage = scanner.nextLine();
            String[] input = userMessage.split(" ", 2);
            reply(userMessage, tasks);
        }
    }
    public static void main(String[] args) {
        Pippi pippi = new Pippi();
        pippi.start();
    }
}
