package main;

import command.*;
import task.*;
import util.Storage;


public class Main {
    private static Main INSTANCE;
    private String name = "your girlfriend";
    private Parser parser;
    private Ui ui;
    private boolean isRunning;

    private TaskList taskList;

    public Main(){
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
    }

    public static void main(String[] args) {
        initialize();
        INSTANCE.run();
    }

    private void run(){
        this.parser.executeCommand("intro");
        this.isRunning = true;
        while(this.isRunning){
            this.ui.update();
        }
        this.ui.dispose();
        return;
    }

    private static void initialize(){
        INSTANCE = new Main();
    }

    public static Main getInstance(){
        return INSTANCE;
    }

    public String getName(){
        return this.name;
    }

    public void exit(){
        this.isRunning = false;
    }


    public TaskList getTaskList(){
        return this.taskList;
    }
    public Parser getParser(){
        return this.parser;
    }

    public Ui getUi(){
        return this.ui;
    }
}
