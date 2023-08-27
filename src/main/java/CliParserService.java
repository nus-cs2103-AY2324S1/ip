import exception.TaskParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CliParserService {
    private final Duke dukeBot;
    private final OutputService outputService;
    private final TaskFactory taskFactory;

    public CliParserService(Duke dukeBot) {
        this.dukeBot = dukeBot;
        this.outputService = new OutputService();
        this.taskFactory = new TaskFactory();
    }

    public void parse() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            String[] input = line.split(" ");
            String command = input[0].toLowerCase();

            switch (command) {
                case "bye":
                    return;
                case "list":
                    outputService.printTasks(dukeBot.getTaskList());
                    break;
                case "mark":
                    if (input.length > 1 && isNumeric(input[1])) {
                        int taskNumber = Integer.parseInt(input[1]);
                        dukeBot.markTask(taskNumber - 1); // task numbers start from 1
                    }
                    break;
                case "unmark":
                    if (input.length > 1 && isNumeric(input[1])) {
                        int taskNumber = Integer.parseInt(input[1]);
                        dukeBot.unmarkTask(taskNumber - 1); // task numbers start from 1
                    }
                    break;
                case "todo":
                case "deadline":
                case "event":
                    parseTaskCommand(line);
                    break;
                default:
                    outputService.echo(String.format("Command: %s not recognised!", input[0]));
            }
        }
    }

    private void parseTaskCommand(String line) {
        String[] parsedInput = line.split("/");
        String[] temp = parsedInput[0].split(" ");
        String taskType = temp[0];
        String taskName = String.join(" ", Arrays.copyOfRange(temp, 1, temp.length));

        String[] taskArgs = Arrays.copyOfRange(parsedInput,1, parsedInput.length);
        try {
            Task task = taskFactory.createTask(taskType, taskName, taskArgs);
            dukeBot.addTask(task);
            List<String> displayText = new ArrayList<>();
            displayText.add("Got it. I've added this task:");
            displayText.add(outputService.indentLeft(task.toString()));
            int numberOfTasks = dukeBot.getTaskList().size();
            displayText.add(String.format("Now you have %s %s in the list",
                    numberOfTasks,
                    numberOfTasks == 1 ? "task" : "tasks"));
            outputService.echo(displayText);
        } catch (TaskParseException e) {
            return;
        }
    }

    // Utility method to check if a string is numeric
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
