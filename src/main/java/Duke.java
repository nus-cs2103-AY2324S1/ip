import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private final String botName;
    private final List<Task> taskList;
    private final OutputService outputService;

    public Duke(String botName) {
        this.botName = botName;
        this.taskList = new ArrayList<>();
        this.outputService = new OutputService();
    }

    public static void main(String[] args) {
        Duke changooseBot = new Duke("Changoose");
        String startMessage = String.format("Hello! I'm %s%nWhat can I do for you?", changooseBot.getBotName());
        String endMessage = "Bye! Hope to see you again soon!";

        changooseBot.getOutputService().echo(startMessage);
        changooseBot.startParse();
        changooseBot.getOutputService().echo(endMessage);
    }

    private void startParse() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String commandInput = scanner.nextLine();
            String[] words = commandInput.split(" ");

            switch (words[0]) {
                case "bye":
                    return;
                case "list":
                    outputService.printTasks(getTaskList());
                    break;
                case "mark":
                    if (words.length > 1 && isNumeric(words[1])) {
                        int taskNumber = Integer.parseInt(words[1]);
                        markTask(taskNumber - 1); // task numbers start from 1
                    }
                    break;
                case "unmark":
                    if (words.length > 1 && isNumeric(words[1])) {
                        int taskNumber = Integer.parseInt(words[1]);
                        unmarkTask(taskNumber - 1); // task numbers start from 1
                    }
                    break;
                default:
                    addTask(commandInput);
                    outputService.echo(commandInput, "added: ");
            }
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

    private String getBotName() {
        return this.botName;
    }

    private OutputService getOutputService() {
        return this.outputService;
    }

    public boolean addTask(String task) {
        return this.taskList.add(new Task(task));
    }

    public void markTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            outputService.echo(String.format("Invalid Task Index: %s provided.", index));
            return;
        }
        Task task = taskList.get(index);
        task.markAsDone();
        List<String> displayText = new ArrayList<>();
        displayText.add("Nice! I've marked this task as done:");
        displayText.add(outputService.indentLeft(task.toString()));
        outputService.echo(displayText);
    }

    public void unmarkTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            outputService.echo(String.format("Invalid Task Index: %s provided.", index));
            return;
        }
        Task task = taskList.get(index);
        task.markAsNotDone();
        List<String> displayText = new ArrayList<>();
        displayText.add("OK, I've marked this task as not done yet:");
        displayText.add(outputService.indentLeft(task.toString()));
        outputService.echo(displayText);
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }
}
