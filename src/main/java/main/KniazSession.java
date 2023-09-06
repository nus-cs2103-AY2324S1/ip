package main;

import exceptions.KniazRuntimeException;
import main.logic.command.KniazCommand;
import storage.TaskList;
import storage.save.KniazLoader;
import storage.save.KniazSaver;
import ui.UiCommand;
import ui.KniazOutputController;
import ui.inputparser.KniazLineParser;

import java.io.IOException;

/**
 * Encapsulates a session of Kniaz.
 */
public class KniazSession {
    private TaskList taskList;
    private KniazLoader loader;
    private KniazSaver saver;

    private KniazLineParser lineParser;

    private KniazOutputController outputController;

    private boolean isRunning;



    /**
     * Dummy constructor that does nothing.
     */
    public KniazSession() {

    }

    /**
     * Private constructor for initializing a KniazSession.
     *
     * @param taskList          The TaskList to use.
     * @param loader            The KniazLoader to use.
     * @param saver             The KniazSaver to use.
     * @param inputController   The KniazInputController to use.
     * @param outputController  The KniazOutputController to use.
     */
    private KniazSession(TaskList taskList,
                         KniazLoader loader,
                         KniazSaver saver,
                         KniazLineParser lineParser,
                         KniazOutputController outputController) {
        this.taskList = taskList;
        this.loader = loader;
        this.saver = saver;
        this.lineParser = lineParser;
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
        KniazLineParser lineParser = new KniazLineParser();
        KniazOutputController output = new KniazOutputController();
        TaskList tasks = new TaskList();

        output.printStartupMessage();
        try {
            tasks = loader.load();
            output.printToOutput("I managed to load your tasks from last time.");
        } catch (KniazRuntimeException e) {
            output.printToOutput(e.getMessage());
        }

        KniazSession out = new KniazSession(tasks, loader, saver, lineParser, output);
        out.isRunning = true;
        return out;

    }

    /**
     * Getter for the tasklist of this session
     * @return the tasklist of this session
     */
    public TaskList getTaskList() {
        return taskList;
    }


    /**
     * Signals the run loop to quit. Should often result in a quit
     * @return a string with "QUIT", for messaging purposes
     */
    public String quit() {
        this.isRunning = false;
        return "QUIT";
    }


    public UiCommand runOneIter(String input) {

        if (!isRunning) {
            return new UiCommand(UiCommand.UiInstructType.QUIT, "");
        }

        KniazCommand nextCommand = lineParser.parseLine(input);
        String printString;
        String flavour = outputController.getFlavourFor(nextCommand);

        try {
            String feedback = nextCommand.execute(this);
            printString = flavour + '\n' + feedback;

        } catch (KniazRuntimeException e) {
            printString = e.getUserMessage();
        }

        try {
            saver.save(this.taskList);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        UiCommand.UiInstructType uiInstructType = (this.isRunning ? UiCommand.UiInstructType.PRINT
               : UiCommand.UiInstructType.QUIT);

        return new UiCommand(uiInstructType, printString);


    }

}
