import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Ben {
    public static final String HORIZONTAL_LINE = "------------------------------------------";
    private boolean isActive = true;
    private final Scanner user = new Scanner(System.in);
    private final TaskList tasks = new TaskList();
    private final File f;

    public Ben(String filePath) {
        f = new File(filePath);
    }

    public void greeting() {
        System.out.println(HORIZONTAL_LINE + "\nWhat's up! I'm Ben\nWhat can I do for you?\n" + HORIZONTAL_LINE);
    }

    public void bye() {
        System.out.println(HORIZONTAL_LINE + "\nBye. For now\n" + HORIZONTAL_LINE);
    }

    public void saveTasks(File f) throws IOException {
        FileWriter writer = new FileWriter(f);
        writer.write(tasks.saveTasks());
        writer.close();
    }

    public void loadTasks(File f) throws FileNotFoundException{
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] words = line.split("[|]");
            String command = words[0];

            switch (command.toLowerCase()) {
                case "t":
                    tasks.add(new ToDos(words[2], Boolean.parseBoolean(words[1])), false);
                    break;
                case "d":
                    tasks.add(new Deadlines(words[2], Boolean.parseBoolean(words[1]), words[3]), false);
                    break;
                case "e":
                    tasks.add(new Events(words[2], Boolean.parseBoolean(words[1]), words[3], words[4]), false);
                    break;
            }
        }

    }
    public void parser(String message) {
        String[] words = message.split("\\s+");
        Task task;
        try {
            if (words[0].equalsIgnoreCase("todo")) {
                String description = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                if (description.isEmpty()) {
                    throw new EmptyDescriptionException("Description cannot be empty");
                }

                task = new ToDos(description, false);
                tasks.add(task, true);
                return;

            } else if (words[0].toLowerCase().contains("deadline")) {
                int positionBy = 0;

                for (int i = 1; i < words.length; i++) {
                    if (words[i].equalsIgnoreCase("/by")) {
                        positionBy = i;
                        break;
                    }
                }

                if (positionBy == 0) {
                    System.out.println(HORIZONTAL_LINE + "\n" + "Did not include /by" + "\n" + HORIZONTAL_LINE);
                    return;
                }

                String description = String.join(" ", Arrays.copyOfRange(words, 1, positionBy));
                if (description.isEmpty()) {
                    throw new EmptyDescriptionException("Description cannot be empty");
                }
                String by = String.join(" ", Arrays.copyOfRange(words, positionBy + 1, words.length));
                if (by.isEmpty()) {
                    throw new EmptyDescriptionException("/by cannot be empty");
                }

                task = new Deadlines(description, false, by);
                tasks.add(task, true);
                return;

            } else if (words[0].toLowerCase().contains("event")) {
                int positionFrom = 0;
                int positionTo = 0;

                for (int i = 1; i < words.length; i++) {
                    if (words[i].equalsIgnoreCase("/from")) {
                        positionFrom = i;
                    }

                    if (words[i].equalsIgnoreCase("/to")) {
                        positionTo = i;
                    }
                }

                if (positionFrom == 0 || positionTo == 0) {
                    System.out.println(HORIZONTAL_LINE + "\n" + "Did not include both /from and /to" + "\n" + HORIZONTAL_LINE);
                    return;
                }

                String description = String.join(" ", Arrays.copyOfRange(words, 1, positionFrom));
                if (description.isEmpty()) {
                    throw new EmptyDescriptionException("Description cannot be empty");
                }

                String from = String.join(" ", Arrays.copyOfRange(words, positionFrom + 1, positionTo));
                if (from.isEmpty()) {
                    throw new EmptyDescriptionException("/from cannot be empty");
                }

                String to = String.join(" ", Arrays.copyOfRange(words, positionTo + 1, words.length));
                if (to.isEmpty()) {
                    throw new EmptyDescriptionException("/to cannot be empty");
                }

                task = new Events(description, false, from, to);
                tasks.add(task, true);
                return;

            }
            throw new InvalidCommandException("Oops this Command: " + message + " is not found");
        } catch (EmptyDescriptionException | InvalidCommandException e) {
            System.out.println(HORIZONTAL_LINE + "\n" + e.getMessage() + "\n" + HORIZONTAL_LINE);
        }
    }

    public void deactivate() {
        isActive = false;
    }


    public void run() {
        // Load tasks from data.txt
        try {
            loadTasks(f);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        greeting();

        while (isActive) {
            String message = user.nextLine();
            if (Objects.equals(message.toLowerCase(), "bye")) {
                deactivate();
            } else if (Objects.equals(message.toLowerCase(), "list")) {
                System.out.println(HORIZONTAL_LINE + "\n" + tasks + HORIZONTAL_LINE);
            } else {
                if (!tasks.isEditListCommand(message)) {
                    parser(message);
                }
            }
        }

        try {
            saveTasks(f);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            bye();
        }
    }

    public static void main(String[] args) {
        Ben ben = new Ben("src/main/java/data/ben.txt");
        ben.run();
    }
}


