package Command;

import Tasks.TaskList;
import enums.CommandWord;

import java.util.ArrayList;

public abstract class Command {

    private final String rawCommand;

    public abstract void execute(TaskList tasklist);

    public Command(String rawCommand) {
        this.rawCommand = rawCommand;
    }

    public String getRawCommand() {
        return this.rawCommand;
    }

    public boolean isByeCommand() {
        return CommandWord.commandWordToValueMap(getArgs(rawCommand)[0]).equals(CommandWord.BYE);
    }

    public static String[] getArgs(String rawCommand) {
        ArrayList<String> result = new ArrayList<>();
        String[] words = rawCommand.split("\\s+");
        String mainCommand = words[0];
        StringBuilder subCommand = new StringBuilder();

        result.add(mainCommand);
        for (int i = 1; i < words.length; i++) {
            String currentWord = words[i];
            if (currentWord.startsWith("/")) {
                result.add(subCommand.toString().trim());
                result.add(currentWord);
                subCommand = new StringBuilder();
            } else {
                subCommand.append(" ").append(currentWord);
            }
        }

        if (!subCommand.toString().trim().isEmpty()) {
            result.add(subCommand.toString().trim());
        }

        return result.toArray(new String[0]);
    }
}
