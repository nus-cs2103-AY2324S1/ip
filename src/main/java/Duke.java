import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static String divider = "____________________________________________________________";

    public static void main(String[] args) {
        String filePath = "./data/tasks.txt";
        String directoryPath = "./data";  // Directory path
        TaskList taskList;
        List<Task> list = new ArrayList<>();
        boolean flag = true;

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Task task = Task.fromString(line);
                list.add(task);
            }

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.out.println("Creating new tasks.txt at " + directoryPath);

            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            } else {
                File file = new File(filePath);
                try {
                    file.createNewFile();
                } catch (IOException ioException) {
                    System.out.println("Oopsie something went wrong creating the file.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            taskList = new TaskList(list);
        }

        System.out.println(divider);
        System.out.println("Hello! I'm BanterBot");
        System.out.println("What can I do for you lmao?");
        System.out.println(divider);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        while (flag) {
            Command command = null;
            try {
                command = Command.parseCommand(input);

                switch (command) {
                    case LIST:
                        taskList.print();
                        break;
                    case MARK:
                        int idx = Integer.parseInt(input.split("mark", 2)[1].strip());

                        taskList.mark(idx);
                        break;
                    case UNMARK:
                        idx = Integer.parseInt(input.split("unmark", 2)[1].strip());

                        taskList.unmark(idx);
                        break;
                    case DELETE:
                        idx = Integer.parseInt(input.split("delete", 2)[1].strip());

                        taskList.delete(idx);
                        break;
                    case TODO:
                        String description = input.split("todo", 2)[1].strip();

                        taskList.add(new ToDo(description));
                        break;
                    case DEADLINE:
                        String[] parts = input.split("deadline", 2);
                        description = parts[1].split("/by", 2)[0].strip();
                        String by = parts[1].split("/by", 2)[1].strip();
                        LocalDate date = LocalDate.parse(by);

                        taskList.add(new Deadline(description, date));
                        break;
                    case EVENT:
                        parts = input.split("event", 2);
                        description = parts[1].split("/from", 2)[0].strip();
                        String time = parts[1].split("/from", 2)[1];
                        String from = time.split("/to", 2)[0].strip();
                        String to = time.split("/to", 2)[1].strip();

                        taskList.add(new Event(description, from, to));
                        break;
                    case DUE:
                        LocalDate dueDate = LocalDate.parse(input.split("due", 2)[1].strip());
                        taskList.dueOn(dueDate);
                        break;
                    case INVALID:
                        System.out.println("Invalid Command Bruh");
                        break;
                    case BYE:
                        System.out.println(divider);
                        System.out.println("Bye. Hope to see you again soon lol!");
                        System.out.println(divider);
                        flag = false;
                        return;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("DATES only in this format: '2023-02-01'. NOT THIS: " + e.getMessage());
            }

            input = scanner.nextLine();
        }
    }
}