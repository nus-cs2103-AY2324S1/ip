package main;

import exceptions.KniazRuntimeException;
import main.logic.command.CommandFactory;
import main.logic.command.KniazCommand;
import main.logic.handler.CommandHandler;
import storage.TaskList;
import storage.save.KniazLoader;
import storage.save.KniazSaver;
import ui.KniazInputController;
import ui.KniazOutputController;

import java.io.IOException;

public class KniazSession {
    private TaskList taskList;
    private KniazLoader loader;
    private KniazSaver saver;

    private KniazInputController inputController;

    private KniazOutputController outputController;

    private boolean isRunning;

    /**
     * Getter for the tasklist of this session
     * @return the tasklist of this session
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Dummy constructor that does nothing
     */
    public KniazSession() {

    }

    private KniazSession(TaskList taskList,
                         KniazLoader loader,
                         KniazSaver saver,
                         KniazInputController inputController,
                         KniazOutputController outputController) {
        this.taskList = taskList;
        this.loader = loader;
        this.saver = saver;
        this.inputController = inputController;
        this.outputController = outputController;
        this.isRunning = true;

    }

    /**
     * Factory method that also performs any needed tasks that a KniazSession would
     * be expected to when starting up (Like printing starting messages,etc.)
     * @return a new KniazSession
     */
    public static KniazSession init() {
        KniazLoader loader = new KniazLoader();
        KniazSaver saver = new KniazSaver();
        KniazInputController input = new KniazInputController();
        KniazOutputController output = new KniazOutputController();
        TaskList tasks = new TaskList();

        output.printStartupMessage();
        try {
            tasks = loader.load();
            output.printToOutput("I managed to load your tasks from last time.");
        } catch (KniazRuntimeException e){
            output.printToOutput(e.getMessage());
        }


        return new KniazSession(tasks,loader,saver,input,output);

    }


    /**
     * Signals the run loop to quit. Should often result in a quit
     * @return a string with "QUIT", for messaging purposes
     */
    public String quit() {
        this.isRunning = false;
        return "QUIT";
    }

    /**
     * Start the main loop of this Session, to run it
     * @return a boolean true
     */
    public boolean run(){
        isRunning = true;
        while (isRunning){
            KniazCommand nextCommand = inputController.nextLine();
            String printString;
            String flavour = outputController.getFlavourFor(nextCommand);
            try {
                String feedback = nextCommand.execute(this);
                printString = flavour + '\n' + feedback;

            } catch (KniazRuntimeException e){
                printString = e.getUserMessage();
            }


            outputController.printToOutput(printString);
            try {
                saver.save(this.taskList);
            } catch (Exception e) {
                outputController.printToOutput(e.toString());
            }
        }
        return true;
    }
}
