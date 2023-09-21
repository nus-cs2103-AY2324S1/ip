package oreo.command;

import oreo.exception.IllegalDateTimeException;
import oreo.task.Task;
import oreo.task.TaskList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class HelpCommand extends Command {
    private String command;

    private Scanner tokeniser;

    private final String GENERAL_MESSAGE = getGeneralMessage();

    private String getGeneralMessage() {
        try (InputStream in = getClass().getResourceAsStream("/help/general.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            return "oh no something went wrong";
        }
    }

    public HelpCommand(String command, Scanner tokeniser) {
        this.command = command;
        this.tokeniser = tokeniser;
    }

    public String getHelpMessage(String content) {
        try (InputStream in = getClass().getResourceAsStream("/help/" + content + ".txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String execute(TaskList tasks) {
        if (!tokeniser.hasNext()) {
            switch (command) {
            case "help":
                return GENERAL_MESSAGE;
            default:
                return "Hi there! I am Oreo! Seems like you need help!\n" +
                        "type \"help\" to learn more!";
            }
        }
        String content = tokeniser.next();
        return getHelpMessage(content);
    }

    @Override
    public String executeEditMode(TaskList tasks, int index, Task oldTask) throws IllegalDateTimeException {
        return execute(tasks);
    }
}
