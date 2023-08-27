import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private enum command {
        bye("bye"),
        list("list"),
        mark("mark"),
        unmark("unmark"),
        deadline("deadline"),
        event("event"),
        todo("todo"),
        delete("delete");

        private final String command;

        command(String command) {
            this.command = command;
        }

        @Override
        public String toString() {
            return command;
        }
    }

    public static void writeToFile(String filePath, String fileName, Task task) throws IOException {
        FileWriter fW = new FileWriter(filePath + "/" + fileName, true);
        String div = "*|,";
        StringBuilder custom = new StringBuilder();
        if (task instanceof Todo) {
            Todo td = (Todo) task;
            custom.append("T").append(div).append(td.getDesc()).append(div)
                    .append(td.getStatus().equals("X") ? 1 : 0)
                    .append(System.lineSeparator());
        } else if (task instanceof Deadline) {
            Deadline dl = (Deadline) task;
            custom.append("D").append(div).append(dl.getDesc()).append(div).append(dl.getBy()).append(div)
                    .append(dl.getStatus().equals("X") ? 1 : 0)
                    .append(System.lineSeparator());
        } else if (task instanceof Event) {
            Event eve = (Event) task;
            custom.append("E").append(div).append(eve.getDesc()).append(div)
                    .append(eve.getFrom()).append(div).append(eve.getTo()).append(div)
                    .append(eve.getStatus().equals("X") ? 1 : 0)
                    .append(System.lineSeparator());;
        }
        fW.write(custom.toString());
        fW.close();
    }

    // n starts from 0
    public static void editFileAtLineN(String filePath, String fileName, int n, char newChar) throws IOException {
        File directory = new File(filePath);
        if (!directory.exists() ) {
            directory.mkdir();
            new FileWriter(filePath + "/" + fileName);
        }
        BufferedReader reader = new BufferedReader(new FileReader(filePath + "/" + fileName));
        String line = reader.readLine();
        StringBuilder oldContent = new StringBuilder();
        int i = 0;
        while (line != null) {
            if (i == n) {
                StringBuilder sB = new StringBuilder(line);
                sB.setCharAt(line.length() - 1, newChar);
                line = sB.toString();
            }
            oldContent.append(line).append(System.lineSeparator());
            line = reader.readLine();
            i++;
        }
        FileWriter fW = new FileWriter(filePath + "/" + fileName);
        fW.write(oldContent.toString());
        fW.close();
    }

    public static void deleteTaskFromFile(String filePath, String fileName, int n) throws IOException{
        File directory = new File(filePath);
        if (!directory.exists() ) {
            directory.mkdir();
            new FileWriter(filePath + "/" + fileName);
        }
        BufferedReader reader = new BufferedReader(new FileReader(filePath + "/" + fileName));
        String line = reader.readLine();
        StringBuilder oldContent = new StringBuilder();
        int i = 0;
        while (line != null) {
            if (i != n) {
                oldContent.append(line).append(System.lineSeparator());
            }
            line = reader.readLine();
            i++;
        }
        FileWriter fW = new FileWriter(filePath + "/" + fileName);
        fW.write(oldContent.toString());
        fW.close();
    }

    public static ArrayList<Task> fileToObjects(String filePath, String fileName) throws IOException {
        File directory = new File(filePath);
        if (!directory.exists() ) {
            directory.mkdir();
            FileWriter fw = new FileWriter(filePath + "/" + fileName,true);
        }

        ArrayList<Task> tasks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath + "/" + fileName));
        String line = reader.readLine();
        String div = "*|,";
        Task task = new Task("empty");

        while (line != null) {
            ArrayList<String> texts = new ArrayList<>();
            if (line.charAt(0) == 'T') {
                String desc = line.substring(div.length() + 1, line.lastIndexOf(div));
                task = new Todo(desc);

            } else if (line.charAt(0) == 'D') {
                String sub = line.substring(div.length() + 1);
                String desc = sub.substring(0, sub.indexOf(div));
                int firstLine = sub.indexOf(div) + div.length();
                String by = sub.substring(firstLine, sub.indexOf(div, firstLine));
                task = new Deadline(desc, by);

            } else if (line.charAt(0) == 'E') {
                String sub = line.substring(div.length() + 1);
                String desc = sub.substring(0, sub.indexOf(div));
                int firstLine = sub.indexOf(div) + div.length();
                String from = sub.substring(firstLine, sub.indexOf(div, firstLine));
                int secLine = sub.indexOf(div, firstLine) + div.length();
                String to = sub.substring(secLine, sub.indexOf(div, secLine));
                task = new Event(desc, from, to);
            }
            // check if task is completed
            if (line.charAt(line.length() - 1) == '1') {
                task.taskCompleted();
            } else {
                task.taskNotCompleted();
            }
            tasks.add(task);
            line = reader.readLine();
        }
        return tasks;
    }

    public static void main(String[] args) throws IOException {

        String filePath = "./data/";
        String fileName = "trying.txt";
        Scanner scanner = new Scanner(System.in);
        String Start = "Hello! I'm Red\nWhat can I do for you?";
        System.out.println(Start);
        ArrayList<Task> tasks = fileToObjects(filePath, fileName);

        while (true) {
            try {
                String input = scanner.nextLine();

                if (input.equals(command.bye.toString())) {
                    String End = "Bye. Hope to see you again soon!";
                    System.out.println(End);
                    break;
                }

                if (input.equals(command.list.toString())) {

                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + "." + tasks.get(i));
                    }
                    continue;
                }

                if (input.contains(command.unmark.toString())) {
                    if (tasks.isEmpty()) {
                        throw new DukeException("0 tasks in the list");
                    }
                    int selected = Integer.parseInt(input.substring(7)) - 1;
                    Task selTask = tasks.get(selected);
                    selTask.taskNotCompleted();
                    System.out.println("OK, I've marked this task as not done yet:\n" + selTask);
                    editFileAtLineN(filePath, fileName, selected, '0');
                    continue;
                }

                if (input.contains(command.mark.toString())) {
                    if (tasks.isEmpty()) {
                        throw new DukeException("0 tasks in the list");
                    }
                    int selected = Integer.parseInt(input.substring(5)) - 1;
                    Task selTask = tasks.get(selected);
                    selTask.taskCompleted();
                    System.out.println("Nice! I've marked this task as done:\n" + selTask);
                    editFileAtLineN(filePath, fileName, selected, '1');
                    continue;
                }

                if (input.contains(command.deadline.toString())) {
                    if (input.equals(command.deadline.toString())) {
                        throw new DukeException("Empty deadline");
                    }
                    if (input.charAt(8) != ' ') {
                        throw new DukeException("Invalid command");
                    }
                    if (!input.contains("/by")) {
                        throw new DukeException("Wrong deadline");
                    }

                    Parser parser = new Parser("D");
                    ArrayList<String> texts = parser.convert(input);
                    Task dl = new Deadline(texts.get(0), texts.get(1));
                    tasks.add(dl);
                    System.out.println("Got it. I've added this task:\n" + dl +
                            "\nNow you have " + tasks.size() + " tasks in the list.");
                    writeToFile(filePath, fileName, dl);
                    continue;
                }

                if (input.contains(command.event.toString())) {
                    if (input.equals(command.event.toString())) {
                        throw new DukeException("Empty event");
                    }
                    if (input.charAt(5) != ' ') {
                        throw new DukeException("Invalid command");
                    }
                    if (!input.contains("to") || !input.contains("from")) {
                        throw new DukeException("Wrong event");
                    }
                    Parser parser = new Parser("E");
                    ArrayList<String> texts = parser.convert(input);
                    Task event = new Event(texts.get(0), texts.get(1), texts.get(2));
                    tasks.add(event);
                    System.out.println("Got it. I've added this task:\n" + event +
                            "\nNow you have " + tasks.size()  + " tasks in the list.");
                    writeToFile(filePath, fileName, event);
                    continue;
                }

                if (input.contains(command.todo.toString())) {
                    if (input.equals(command.todo.toString())) {
                        throw new DukeException("Empty todo");
                    }
                    if (input.charAt(4) != ' ') {
                        throw new DukeException("Invalid command");
                    }
                    Parser parser = new Parser("T");
                    String desc = parser.convert(input).get(0);
                    if (desc.isEmpty() || desc.isBlank()) {
                        throw new DukeException("Empty todo");
                    }
                    Task todo = new Todo(desc);
                    tasks.add(todo);
                    System.out.println("Got it. I've added this task:\n" + todo +
                            "\nNow you have " + tasks.size()  + " tasks in the list.");
                    writeToFile(filePath, fileName, todo);
                    continue;
                }

                if (input.contains(command.delete.toString())) {
                    int selected = Integer.parseInt(input.substring(input.indexOf("delete") + 7)) - 1;
                    Task task = tasks.remove(selected);
                    System.out.println("Noted. I've removed this task:\n" + task +
                            "\nNow you have " + tasks.size()  + " tasks in the list.");
                    deleteTaskFromFile(filePath, fileName, selected);
                    continue;
                }

                throw new DukeException("Invalid command");

            } catch (DukeException e) {
                if (e.getMessage().equals("Invalid command")) {
                    System.out.println("You have typed an invalid command");
                } else if (e.getMessage().equals("Empty todo")) {
                    System.out.println("You cannot have an empty todo");
                } else if (e.getMessage().equals("Empty deadline")) {
                    System.out.println("You cannot have an empty deadline");
                } else if (e.getMessage().equals("Empty event")) {
                    System.out.println("You cannot have an empty event");
                } else if (e.getMessage().equals("Wrong event")) {
                    System.out.println("Your event structure is wrong");
                } else if (e.getMessage().equals("Wrong deadline")) {
                    System.out.println("Your deadline structure is wrong");
                } else {
                    System.out.println(e.getMessage());
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
