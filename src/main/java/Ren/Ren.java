package ren;

import javafx.fxml.FXML;
import ren.task.Task;
import ren.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ren {
    private static RenObjectMapper objectMapper = new RenObjectMapper();
    private static TaskList tasks = objectMapper.retrieveFromHarddisk();
    ;

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    @FXML
    String getResponse(String input) {
        return RenParser.parseInputString(input, tasks);
    }

    public static void main(String[] args) {
        try {
            tasks = objectMapper.retrieveFromHarddisk();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            tasks = new TaskList(new ArrayList<Task>());
        }

        Scanner input = new Scanner(System.in);
        RenUi.printWelcomeMsg();
        String inputStr = input.nextLine();
        while (!inputStr.equals(Commands.EXIT_COMMAND.getValue())) {
            RenParser.parseInputString(inputStr, tasks);
            inputStr = input.nextLine();
        }

        input.close();
        RenUi.printGoodbyeMsg();
    }
}
