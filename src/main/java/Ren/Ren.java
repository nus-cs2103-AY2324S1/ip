package Ren;

import Ren.task.Task;
import Ren.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ren {
    public static void run() {

    }

    public static void main(String[] args) throws InvalidRenCommand {


        RenObjectMapper objectMapper = new RenObjectMapper();
        TaskList tasks;
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
