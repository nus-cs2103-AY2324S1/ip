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

    public TaskList getTaskList() {
        return taskList;
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


    public String quit() {
        this.isRunning = false;
        return "QUIT";
    }
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
