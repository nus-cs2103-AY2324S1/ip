package hong;

import java.util.ArrayList;
import java.util.Scanner;

import parsers.Parser;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;


public class Hong {
    private static ArrayList<Task> tasks;

    //private static final String LINE = "---------------------------------------------------------";
    public static void main(String[] args) {
        boolean isLoopContinuing = true;
        Ui.sayHello();
        TaskList taskList = new TaskList();
        Scanner myObj = new Scanner(System.in);
        while (isLoopContinuing) {
            String userInput = myObj.nextLine();
            Parser parser = new Parser();
            String parsedCommand = parser.parseCommand(userInput);
            switch (parsedCommand) {
            case "bye":
                myObj.close();
                isLoopContinuing = false;
                break;
            case "list":
                taskList.printTasks();
                break;
            case "mark":
                taskList.handleMark(userInput);
                taskList.storeTasks();
                break;
            case "deadline":
                taskList.createDeadline(userInput);
                taskList.storeTasks();
                break;
            case "event":
                taskList.createEvent(userInput);
                taskList.storeTasks();
                break;
            case "todo":
                taskList.createTodo(userInput);
                taskList.storeTasks();
                break;
            case "delete":
                taskList.deleteTask(userInput);
                taskList.storeTasks();
                break;
            default:
                Ui.printLine();
                Ui.print("I do not recognise that command!");
                Ui.printLine();
            }
        }
        Ui.sayBye();
    }

}
