import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ben {
    private static final String HORIZONTAL_LINE = "------------------------------------------";
    private boolean isActive = true;
    private final Scanner user = new Scanner(System.in);
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void greeting() {
        System.out.println(HORIZONTAL_LINE + "\nWhat's up! I'm Ben\nWhat can I do for you?\n" + HORIZONTAL_LINE);
    }

    public void bye() {
        System.out.println(HORIZONTAL_LINE + "\nBye. For now\n" + HORIZONTAL_LINE);
    }

    public void addSuccessMessage(Task task) {
        System.out.println(HORIZONTAL_LINE + "\nGot It! This task has been added:\n" + task +
                "\nNow you have " + tasks.size() + " items in the list\n" + HORIZONTAL_LINE);
    }

    public void add(String message) {
        String[] words = message.split("\\s+");
        Task task;
        try {
            if (words[0].equalsIgnoreCase("todo")) {
                String description = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                if (description.isEmpty()) {
                    throw new EmptyDescriptionException("Description cannot be empty");
                }

                task = new ToDos(description);
                tasks.add(task);
                addSuccessMessage(task);
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

                task = new Deadlines(description, by);
                tasks.add(task);

                addSuccessMessage(task);
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

                task = new Events(description, from, to);
                tasks.add(task);

                addSuccessMessage(task);
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

    public void listToString() {
        String message = "";
        for (int i = 1; i <= tasks.size(); i++) {
            message += i + ". " + tasks.get(i - 1).toString() + "\n";
        }
        System.out.println(HORIZONTAL_LINE + "\n" + message + HORIZONTAL_LINE);
    }

    public void Mark(Task task) {
        task.Mark();
        System.out.println(HORIZONTAL_LINE + "\n" + "Nice! This task is completed\n" + task + "\n" + HORIZONTAL_LINE);
    }

    public void Unmark(Task task) {
        task.Unmark();
        System.out.println(HORIZONTAL_LINE + "\n" + "Okay! This task is not completed\n" + task + "\n" + HORIZONTAL_LINE);
    }

    public boolean isMarkCommand(String message) {
        Pattern pattern = Pattern.compile("(unmark|mark)\\s*(-?\\d+)");
        Matcher matcher = pattern.matcher(message.toLowerCase());

        if (matcher.find()) {
            // extract command
            String command = matcher.group(1);

            // extract task number
            String TaskNumber = matcher.group(2);
            int num = Integer.parseInt(TaskNumber) - 1;

            // check whether number is valid
            if (num < 0 || num >= tasks.size()) {
                System.out.println(HORIZONTAL_LINE + "\n" + "Please input a valid task number" + "\n" + HORIZONTAL_LINE);
                return true;
            }

            // if valid, mark or unmark the task
            if (Objects.equals(command, "mark")) {
                Mark(tasks.get(num));
            } else {
                Unmark(tasks.get(num));
            }
            return true;
        }
        return false;
    }

    public void run() {
        greeting();
        while (isActive) {
            String message = user.nextLine();
            if (Objects.equals(message.toLowerCase(), "bye")) {
                deactivate();
            } else if (Objects.equals(message.toLowerCase(), "list")) {
                listToString();
            } else {
                if (!isMarkCommand(message)) {
                    add(message);
                }
            }
        }
        bye();
    }

    public static void main(String[] args) {
        Ben ben = new Ben();
        ben.run();
    }
}


