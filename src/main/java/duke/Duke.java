package duke;

import command.Command;
import parser.Parser;
import storage.TaskFileHandler;
import tasks.TaskList;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    public final static DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public static void main(String[] args) {
        runDuke();
    }

    public static void runDuke() {
        Scanner scanner = new Scanner(System.in);
        Ui.helloWorld();
        boolean isConversing = true;
        while (isConversing) {

            String rawCommand = Ui.getUserInput(scanner);
            TaskList taskList = TaskFileHandler.readFromFile();
            Ui.getBotMessage();
            Command command = Parser.parse(rawCommand);
            command.execute(taskList);
            TaskFileHandler.saveToFile(taskList);
            isConversing = !command.isByeCommand();
        }
    }

    public static boolean validateDateTime(String string) {
        try {
            LocalDate.parse(string, DATETIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
