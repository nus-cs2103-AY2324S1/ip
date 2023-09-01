package glub;

import glub.task.TaskList;
import glub.task.TaskType;

import java.util.Scanner;

public class Parser {
    TaskList taskList;
    Storage storage;
    boolean isListening = true;
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    public void listen() {
        Scanner inputScanner = new Scanner(System.in);
        while (isListening) {
            try {
                String command = inputScanner.next();
                switch (command) {
                case "bye":
                    isListening = false;
                    Ui.sayGoodbye();
                    break;
                case "list":
                    Ui.printListMsg(this.taskList);
                    break;
                case "mark":
                    taskList.mark(inputScanner.nextInt());
                    break;
                case "unmark":
                    taskList.unmark(inputScanner.nextInt());
                    break;
                case "delete":
                    taskList.deleteTask(inputScanner.nextInt());
                    break;
                case "todo":
                    String todo = inputScanner.nextLine();
                    taskList.addTask(todo, TaskType.TODO, false);
                    Ui.printAddMsg(taskList.getTaskList());
                    storage.saveTasks(taskList.getTaskList());
                    break;
                case "deadline":
                    String deadline = inputScanner.nextLine();
                    taskList.addTask(deadline, TaskType.DEADLINE, false);
                    Ui.printAddMsg(taskList.getTaskList());
                    storage.saveTasks(taskList.getTaskList());
                    break;
                case "event":
                    String event = inputScanner.nextLine();
                    taskList.addTask(event, TaskType.EVENT, false);
                    Ui.printAddMsg(taskList.getTaskList());
                    storage.saveTasks(taskList.getTaskList());
                    break;
                default:
                    throw new GlubException("OOPS!! I'm sorry, but I don't know what that means :-(\n");
                }
            } catch (GlubException ex) {
                Ui.printError(ex.getMessage());
            }
        }
    }
}
