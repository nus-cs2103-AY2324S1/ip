package hong;

import java.util.ArrayList;
import java.util.Scanner;

import parsers.Parser;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;



public class Hong{


    public static void main(String[] args) {
        ;
    }
    private void run() {
        boolean isLoopContinuing = true;
        Ui.sayHello();
        TaskList taskList = new TaskList();
        Scanner myObj = new Scanner(System.in);
        while (isLoopContinuing) {
            String userInput = myObj.nextLine();
            Parser parser = new Parser();
            String parsedCommand = parser.parseCommand(userInput);
            assert parsedCommand.equals("") : "Parser is not working";
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
            case "find":
                taskList.findTask(userInput);
                break;
            default:
                Ui.printLine();
                Ui.print("I do not recognise that command!");
                Ui.printLine();
            }
        }
        Ui.sayBye();
    }

    /*
     * Function to return a response when given the userInput.
     *
     * @param userInput a String which represents the input from the user.
     * @return The String response to print out on the GUI.
     */
    public String getResponse(String userInput) {
        TaskList taskList = new TaskList();
        String str = "";
        Parser parser = new Parser();
        String parsedCommand = parser.parseCommand(userInput);
        switch (parsedCommand) {
        case "bye":
            str += "Bye! Hope to see you again!";
            break;
        case "list":
            str += taskList.printTasks();
            break;
        case "mark":
            str += taskList.handleMark(userInput);
            taskList.storeTasks();
            break;
        case "deadline":
            str += taskList.createDeadline(userInput);
            taskList.storeTasks();
            break;
        case "event":
            str += taskList.createEvent(userInput);
            taskList.storeTasks();
            break;
        case "todo":
            str += taskList.createTodo(userInput);
            taskList.storeTasks();
            break;
        case "delete":
            str += taskList.deleteTask(userInput);
            taskList.storeTasks();
            break;
        case "find":
            str += taskList.findTask(userInput);
            break;
        case "snooze":
            str += taskList.snoozeTask(userInput);
            taskList.storeTasks();
            break;
        default:
            str += Ui.printLine();
            str += Ui.print("I do not recognise that command!");
            str += Ui.printLine();
        }
        assert str.equals("") : "Error, bot is not saying anything!";
        return str;
    }
}
