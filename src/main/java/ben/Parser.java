package ben;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public void commandParser(String command, TaskList tasks) throws InvalidCommandException, EmptyDescriptionException {
        String[] words = command.split("\\s+");
        Task task;
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
                    throw new InvalidCommandException("\n" + "Did not include /by" + "\n");
                }

                String description = String.join(" ", Arrays.copyOfRange(words, 1, positionBy));
                if (description.isEmpty()) {
                    throw new EmptyDescriptionException("Description cannot be empty");
                }
                String by = String.join(" ", Arrays.copyOfRange(words, positionBy + 1, words.length));
                if (by.isEmpty()) {
                    throw new EmptyDescriptionException("/by cannot be empty");
                }

                task = new Deadlines(description, false, dateTimeParser(by));
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
                    throw new InvalidCommandException("\n" + "Did not include both /from and /to" + "\n");
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

                task = new Events(description, false, dateTimeParser(from), dateTimeParser(to));
                tasks.add(task, true);
                return;

            }
            throw new InvalidCommandException("Oops this Command: " + command + " is not found");
    }
    public boolean isEditListCommand(String message, TaskList tasks) {
        Pattern pattern = Pattern.compile("(unmark|mark|delete)\\s*(-?\\d+)");
        Matcher matcher = pattern.matcher(message.toLowerCase());

        if (matcher.find()) {
            // extract command
            String command = matcher.group(1);

            // extract task number
            String TaskNumber = matcher.group(2);
            int num = Integer.parseInt(TaskNumber) - 1;

            // check whether number is valid
            if (num < 0 || num >= tasks.size()) {
                Ui.showError("Please input a valid task number");
                return true;
            }

            // if valid, mark or unmark the task
            Task task = tasks.get(num);

            if (Objects.equals(command, "mark")) {
                task.mark();
                Ui.displayMessage("\n" +
                        "Nice! This task is completed\n" + task + "\n");
            } else if ((Objects.equals(command, "delete"))) {
                tasks.delete(tasks.get(num));
            } else {
                task.unmark();
                Ui.displayMessage("\n" +
                        "Okay! This task is not completed\n" + task + "\n");

            }
            return true;
        }
        return false;
    }

    public LocalDateTime dateTimeParser(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        if (dateTime.length() <= 10) {
            return LocalDateTime.parse(dateTime + " 2359", formatter);
        }
        return LocalDateTime.parse(dateTime, formatter);
    }
}
