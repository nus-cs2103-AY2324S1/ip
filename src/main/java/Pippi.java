import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

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
    private static final String filePath = "./data/Pippi.txt";
    public void wrapText(String content) {
        String line = "_____________________________________\n";
        System.out.println(line + content + "\n" + line);
    }

    public ArrayList<Task> read() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts =line.split("\\s*\\|\\s*");
            if (parts.length == 0) {
                continue;
            }
            String type = parts[0];
            String status = parts[1];
            switch (type) {
                case "T":
                    ToDo t = new ToDo(parts[2]);
                    // System.out.println(status);
                    if (status.equals("1")) {
                        // System.out.println("called");
                        t.mark();
                    }
                    tasks.add(t);
                    break;
                case "D":
                    Deadline d = new Deadline(parts[2], parts[3]);
                    if (status.equals("1")) {
                        d.mark();
                    }
                    tasks.add(d);
                    break;
                case "E":
                    String start = parts[3].split("to ")[0];
                    String end = parts[3].split("to ")[1];
                    Event e = new Event(parts[2], start, end);
                    if (status.equals("1")) {
                        e.mark();
                    }
                    tasks.add(e);
                    break;
            }
        }
        // FileReader will open that file from that
        // directory, if there is no file found it will
        // throw an IOException
        } catch (IOException e) {
            System.out.println("No file found exception");
        }
        return tasks;
    }

    public void update(ArrayList<Task> tasks) {
        try {
            // Creating a FileWriter object
            FileWriter fw = new FileWriter(filePath);
            String all = "";
            for (int i = 0; i < tasks.size(); i++) {
                Task curr = tasks.get(i);
                all = all + curr.toMemory() + "\n";
            }
            fw.write(all);
            fw.close();

        } catch (IOException e) {
            System.out.println("File input/output not found exception");
        }
    }

    public void reply(String userMessage, ArrayList<Task> tasks) {
        try {
            String[] input = userMessage.split(" ", 2);
            String command = input[0];
            switch (command) {
                case "bye":
                    wrapText("Bye. Hope to see you again soon!");
                    this.inPokeball = true;
                    break;
                case "todo":
                    if ((input.length < 2) || input[1].trim().isEmpty()) {
                        wrapText("Moonblast!!! The description of a todo cannot be empty.");
                        break;
                    }
                    ToDo td = new ToDo(input[1]);
                    tasks.add(td);
                    update(tasks);
                    wrapText("Got it. I've added this task:\n" + td.toString() +
                            "\nNow you have " + tasks.size() + " tasks in the list.");
                    break;
                case "deadline":
                    if (input.length < 2 || input[1].trim().isEmpty()) {
                        throw new PippiException("Metronome!!! The description and due time of a deadline cannot be empty.");
                    }
                    if (input[1].split("/by").length < 2) {
                        throw new PippiException("Metronome!!! Due time or description is missing");
                    }
                    String title = input[1].split("/by ")[0].trim();
                    String due = input[1].split("/by ")[1];
                    Deadline dl = new Deadline(title, due);
                    tasks.add(dl);
                    update(tasks);
                    wrapText("Got it. I've added this task:\n" + dl.toString() +
                            "\nNow you have " + tasks.size() + " tasks in the list.");
                    break;
                case "event":
                    if (input.length < 2 || input[1].trim().isEmpty()) {
                        throw new PippiException("Pound!!! The description and start, end time of an event cannot be empty.");
                    }
                    if (input[1].split("/from").length < 2) {
                        throw new PippiException("Pound!!! Event title or duration is missing");
                    }
                    String evTitle = input[1].split("/from ")[0].trim();
                    String duration = input[1].split("/from ")[1];

                    if (duration.split("/to ").length < 2) {
                        throw new PippiException("Pound!!! Start or end time is missing");
                    }
                    String start = duration.split("/to ")[0];
                    String end = duration.split("/to ")[1];

                    Event event = new Event(evTitle, start, end);
                    tasks.add(event);
                    update(tasks);
                    wrapText("Got it. I've added this task:\n" + event.toString() +
                            "\nNow you have " + tasks.size() + " tasks in the list.");
                    break;
                case "list":
                    String all = "";
                    for (int i = 0; i < tasks.size(); i++) {
                        Task curr = tasks.get(i);
                        all = all + String.valueOf(i + 1) + "." + curr.toString() + "\n";
                    }
                    wrapText("Here are the tasks in your list:\n" + all);
                    break;
                case "mark":
                    int idx = Integer.parseInt(input[1]) - 1;
                    tasks.get(idx).mark();
                    wrapText("Nice I've marked this task as done:\n" +
                            tasks.get(idx).toString());
                    update(tasks);
                    break;
                case "unmark":
                    int id = Integer.parseInt(input[1]) - 1;
                    tasks.get(id).unmark();
                    wrapText("OK, I've marked this task as not done yet:\n" +
                            tasks.get(id).toString());
                    update(tasks);
                    break;
                case "delete":
                    int i = Integer.parseInt(input[1]) - 1;
                    wrapText("Noted. I've removed this task:\n" +
                            tasks.get(i).toString() +
                            "\nNow you have " + (tasks.size() - 1) + " tasks in the list.");
                    tasks.remove(i);
                    update(tasks);
                    break;
                default:
                    wrapText("Amnesia!!! I'm sorry, but I don't know what that means");
            }
        } catch (PippiException e) {
            wrapText(e.getMessage());
        }
    }
    public void start() {
        wrapText("Hello trainer, I'm Pippi!\nWhat can I do for you?");
        ArrayList<Task> tasks = read();
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
