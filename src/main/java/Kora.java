import java.util.Scanner;

import command.*;
import task.TaskList;
import task.Task;
public class Kora {

    private TaskList taskList;
    public Kora() {
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

        taskList = new TaskList();
        boolean isExit = false;
        while (!isExit) {
            Scanner scanner = new Scanner(System.in);
            //System.out.println("------------------------------");
            getResponse(scanner.nextLine());
            //System.out.println("------------------------------");
        }
    }

    public void getResponse(String userInput) {
        String line = "------------------------------" + "\n";
        String[] userInputArray = userInput.split("/");
        System.out.println(userInputArray[0]);
        //Task currentTask = new Task(userInput);
        try {
            if (userInputArray[0].contains("bye")) {
                Command command = new ByeCommand();
                command.execute(taskList);
                command.printOutput(command.getCommandMessage());
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
            } else {
                System.out.println("I do not understand");
            }
        } catch (Exception e) {
            System.out.println(line + e.getMessage() + e.toString() + e.getCause() + e.getStackTrace());
        }
    }
    public static void main(String[] args) {
        Kora kora = new Kora();
    }
}
