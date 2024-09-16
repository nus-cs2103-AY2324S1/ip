package main;

import command.CommandAddTaskHandler;
import command.CommandDeadlineHandler;
import command.CommandDeleteHandler;
import command.CommandDurationHandler;
import command.CommandEventHandler;
import command.CommandExitHandler;
import command.CommandFindHandler;
import command.CommandIntroHandler;
import command.CommandListHandler;
import command.CommandMarkUnmarkHandler;
import command.CommandTodoHandler;
import command.Parser;
import javafx.application.Application;
import task.TaskList;
import util.Storage;



public class Main {
    private static Main INSTANCE;
    private String name = "Duke";
    private Parser parser;
    private boolean isRunning;

    private TaskList taskList;

    /**
     * Default constructor of the program's main class
     */
    public Main() {
        this.parser = new Parser();
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
        this.parser.registerCommand("duration", new CommandDurationHandler());
    }

    /**
     * Main function for the program
     *
     * @param args The arguments from JVM
     */
    public static void main(String[] args) {
        initialize();
        INSTANCE.run(args);
    }

    private void run(String[] args) {
        Application.launch(Ui.class, args);
        return;
    }

    private static void initialize() {
        INSTANCE = new Main();
    }

    /**
     * Load task list from file.
     * This should be called after the Ui instance with all Ui components has been initialized
     * in order to use the Ui for reporting errors when reading file.
     */
    public void loadTaskListFromFile() {
        this.taskList = Storage.createTaskListFromFile();
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
        System.exit(0);
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
}
