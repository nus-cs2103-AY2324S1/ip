package main;

import command.CommandAddTaskHandler;
import command.CommandExitHandler;
import command.CommandIntroHandler;
import command.CommandListHandler;
import command.CommandMarkUnmarkHandler;
import command.CommandTodoHandler;
import command.CommandDeadlineHandler;
import command.CommandEventHandler;
import command.CommandDeleteHandler;
import command.CommandFindHandler;
import command.Parser;
import task.TaskList;
import util.Storage;



public class Main {
    private static Main INSTANCE;
    private String name = "your girlfriend";
    private Parser parser;
    private Ui ui;
    private boolean isRunning;

    private TaskList taskList;

    /**
     * Default constructor of the program's main class
     */
    public Main() {
        this.parser = new Parser();
        this.ui = new Ui();
        this.taskList = Storage.createTaskListFromFile();
        this.parser.registerCommand("intro", new CommandIntroHandler());
        this.parser.registerCommand("list", new CommandListHandler());
        this.parser.registerCommand("add", new CommandAddTaskHandler());
        this.parser.registerCommand("bye", new CommandExitHandler());
        CommandMarkUnmarkHandler temp = new CommandMarkUnmarkHandler();
        this.parser.registerCommand("mark", temp);
        this.parser.registerCommand("unmark", temp);
        this.parser.registerCommand("todo", new CommandTodoHandler());
        this.parser.registerCommand("deadline", new CommandDeadlineHandler());
        this.parser.registerCommand("event", new CommandEventHandler());
        this.parser.registerCommand("delete", new CommandDeleteHandler());
        this.parser.registerCommand("find", new CommandFindHandler());
    }

    /**
     * Main function for the program
     *
     * @param args The arugments from JVM
     */
    public static void main(String[] args) {
        initialize();
        INSTANCE.run();
    }

    private void run() {
        this.parser.executeCommand("intro");
        this.isRunning = true;
        while(this.isRunning){
            this.ui.update();
        }
        this.ui.dispose();
        return;
    }

    private static void initialize() {
        INSTANCE = new Main();
    }

    /**
     * Returns the application singleton instance
     *
     * @return the instance
     */
    public static Main getInstance() {
        return INSTANCE;
    }

    /**
     * Return the name of the chat bot
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Exit the program
     */
    public void exit() {
        this.isRunning = false;
    }


    /**
     * Returns the TaskList instance
     *
     * @return the instance
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the Parser instance
     *
     * @return the instance
     */
    public Parser getParser() {
        return this.parser;
    }

    /**
     * Returns the Ui instance
     *
     * @return the instance
     */
    public Ui getUi(){
        return this.ui;
    }
}
