package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import command.Command;
import parser.Parser;
import tasks.*;
import ui.Ui;
import storage.TaskFileHandler;

public class Duke {
    public static void main(String[] args) {
        runDuke();
    }
    public final static DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public static void runDuke() {
        Scanner scanner = new Scanner(System.in);
        Ui.helloWorld();
        boolean converse = true;
        while (converse) {
            String rawCommand = Ui.getUserInput(scanner);
            TaskList taskList = TaskFileHandler.readFromFile();
            Ui.getBotMessage();
            Command command = Parser.parse(rawCommand);
            command.execute(taskList);
            TaskFileHandler.saveToFile(taskList);
            if (command.isByeCommand()) {
                converse = false;
            }
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
