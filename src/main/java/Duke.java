import command.*;
import task.TaskList;

import java.util.Scanner;

public class Duke {
    private TaskList taskList;
    private boolean isExit = false;
    public Duke() {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        String logo = "  _   _   _\n" +
                " / \\ / \\ / \\\n" +
                "( 안 | 녕 )\n" +
                " \\_/ \\_/ \\_/\n";
        System.out.println(logo + "Hello, I am your chatbot Kora!\nHow can I help you today?");
        System.out.println("------------------------------");


        taskList = new TaskList("./data/savedtask.txt");

        while (!isExit) {
            Scanner scanner = new Scanner(System.in);
            //System.out.println("------------------------------");
            //getResponse(scanner.nextLine());
            //System.out.println("------------------------------");
            while (scanner.hasNextLine()) {
                getResponse(scanner.nextLine());
            }
        }
    }

    public void getResponse(String userInput) {
        String line = "------------------------------" + "\n";
        String[] userInputArray = userInput.split("/");
        try {
            if (userInputArray[0].contains("bye")) {
                Command command = new ByeCommand();
                command.execute(taskList);
                command.printOutput(command.getCommandMessage());
                isExit = true;
            } else if (userInputArray[0].contains("list")) {
                Command command = new ListCommand();
                command.execute(taskList);
                command.printOutput(command.getCommandMessage());
            } else if (userInputArray[0].contains("unmark")) {
                Command command = new UnmarkCommand(userInputArray);
                command.execute(taskList);
                command.printOutput(command.getCommandMessage());
            } else if (userInputArray[0].contains("mark")) {
                Command command = new MarkCommand(userInputArray);
                command.execute(taskList);
                command.printOutput(command.getCommandMessage());

            } else if (userInputArray[0].contains("deadline")) {
                Command command = new DeadlineCommand(userInputArray);
                command.execute(taskList);
                command.printOutput(command.getCommandMessage());
            } else if (userInputArray[0].contains("event")) {
                Command command = new EventCommand(userInputArray);
                command.execute(taskList);
                command.printOutput(command.getCommandMessage());
            } else if (userInputArray[0].contains("todo")) {
                Command command = new ToDoCommand(userInputArray);
                command.execute(taskList);
                command.printOutput(command.getCommandMessage());
            } else if (userInputArray[0].contains("delete")) {
                Command command = new DeleteCommand(userInputArray);
                command.execute(taskList);
                command.printOutput(command.getCommandMessage());
            } else {
                System.out.println("I do not understand");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        Duke kora = new Duke();
    }
}
