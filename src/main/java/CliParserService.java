import java.util.Scanner;

public class CliParserService {
    private final Duke dukeBot;
    private final OutputService outputService;

    public CliParserService(Duke dukeBot) {
        this.dukeBot = dukeBot;
        this.outputService = new OutputService();
    }
    
    public static void main(String[] args) {
        Duke changooseBot = new Duke("Changoose");
        CliParserService cliParserService = new CliParserService(changooseBot);
        OutputService outputService = new OutputService();
        String startMessage = String.format("Hello! I'm %s%nWhat can I do for you?", changooseBot.getBotName());
        String endMessage = "Bye! Hope to see you again soon!";

        outputService.echo(startMessage);
        cliParserService.parse();
        outputService.echo(endMessage);
    }

    public void parse() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String commandInput = scanner.nextLine();
            String[] words = commandInput.split(" ");

            switch (words[0]) {
                case "bye":
                    return;
                case "list":
                    outputService.printTasks(dukeBot.getTaskList());
                    break;
                case "mark":
                    if (words.length > 1 && isNumeric(words[1])) {
                        int taskNumber = Integer.parseInt(words[1]);
                        dukeBot.markTask(taskNumber - 1); // task numbers start from 1
                    }
                    break;
                case "unmark":
                    if (words.length > 1 && isNumeric(words[1])) {
                        int taskNumber = Integer.parseInt(words[1]);
                        dukeBot.unmarkTask(taskNumber - 1); // task numbers start from 1
                    }
                    break;
                default:
                    dukeBot.addTask(commandInput);
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

}
