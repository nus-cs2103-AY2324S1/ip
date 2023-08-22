import java.util.Scanner;

import command.ByeCommand;
import command.Command;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import task.TaskList;
import task.Task;
public class Kora {

    private TaskList taskList;
    public Kora() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I am your chatbot Kora!\nHow can I help you today?");
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
        Task currentTask = new Task(taskList.getNextIndex(), userInput);
        String[] userInputArray = userInput.split(" ");
        try {
            if (userInputArray[0].equals("bye")) {
                Command command = new ByeCommand();
                command.execute(taskList, 0);
                command.printOutput(command.getCommandMessage());
            } else if (userInputArray[0].equals("list")) {
                Command command = new ListCommand();
                command.execute(taskList, 0);
                command.printOutput(command.getCommandMessage());
            } else if (userInputArray[0].equals("mark")) {
                int inputTaskIndex = Integer.valueOf(userInputArray[1]);
                Command command = new MarkCommand();
                command.execute(taskList, inputTaskIndex);
                command.printOutput(command.getCommandMessage());
            } else if (userInputArray[0].equals("unmark")) {
                int inputTaskIndex = Integer.valueOf(userInputArray[1]);
                Command command = new UnmarkCommand();
                command.execute(taskList, inputTaskIndex);
                command.printOutput(command.getCommandMessage());
            } else {
                taskList.addTask(currentTask);
                System.out.println(line + "added: " + userInput + "\n" + line);
            }
        } catch (Exception e) {
            System.out.println(line + e.getMessage());
        }
    }
    public static void main(String[] args) {
        Kora kora = new Kora();
    }
}
