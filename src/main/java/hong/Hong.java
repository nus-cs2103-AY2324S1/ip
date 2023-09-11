package hong;

import java.util.ArrayList;
import java.util.Scanner;

import parsers.Parser;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;



public class Hong{
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
        default:
            str += Ui.printLine();
            str += Ui.print("I do not recognise that command!");
            str += Ui.printLine();
        }
        assert str.equals("") : "Error, bot is not saying anything!";
        return str;
    }
}
