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
        switch(command) {
            case "bye":
                wrapText("Bye. Hope to see you again soon!");
                this.inPokeball = true;
                break;
            case "todo":
                ToDo td = new ToDo(input[1]);
                tasks.add(td);
                wrapText("Got it. I've added this task: \n" + td.toString() +
                        "\n Now you have " + tasks.size() + " tasks in the list.");
                break;
            case "deadline":
                String title = input[1].split("/by ")[0];
                String time = input[1].split("/by ")[1];
                Deadline dl = new Deadline(title, time);
                tasks.add(dl);
                wrapText("Got it. I've added this task: \n" + dl.toString() +
                        "\n Now you have " + tasks.size() + " tasks in the list.");
                break;
            case "event":
                String evTitle = input[1].split("/from ")[0];
                String duration = input[1].split("/from ")[1];
                String start = duration.split("/to ")[0];
                String end = duration.split("/to ")[1];

                Event event = new Event(evTitle, start, end);
                tasks.add(event);
                wrapText("Got it. I've added this task: \n" + event.toString() +
                        "\nNow you have " + tasks.size() + " tasks in the list.");
                break;
            case "list":
                String all = "";
                for (int i = 0; i < tasks.size(); i++) {
                    Task curr = tasks.get(i);
                    all = all + String.valueOf(i + 1) +"." + curr.toString() + "\n";
                }
                wrapText("Here are the tasks in your list: \n" + all);
                break;
            case "mark":
                int idx = Integer.parseInt(input[1]) - 1;
                tasks.get(idx).mark();
                wrapText("Nice I've marked this task as done: \n" +
                        tasks.get(idx).toString());
                break;
            case "unmark":
                int id = Integer.parseInt(input[1]) - 1;
                tasks.get(id).unmark();
                wrapText("OK, I've marked this task as not done yet: \n" +
                        tasks.get(id).toString());
                break;
            default:
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
