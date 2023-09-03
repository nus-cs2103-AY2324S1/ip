import java.util.Scanner;

import Command.Command;
import Parser.Parser;
import Tasks.*;
import Ui.Ui;
import Storage.TaskFileHandler;

public class Duke {
    public static void main(String[] args) {
        runDuke();
    }

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
}
