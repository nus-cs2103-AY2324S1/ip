package kniaz;

import java.io.IOException;

import exceptions.KniazRuntimeException;
import kniaz.logic.command.KniazCommand;
import storage.TaskList;
import storage.save.KniazLoader;
import storage.save.KniazSaver;
import ui.KniazOutputFlavourer;
import ui.UiCommand;
import ui.inputparser.KniazLineParser;



/**
 * Encapsulates a session of kniaz.Kniaz.
 */
public class KniazSession {
    private TaskList taskList;
    private KniazLoader loader;
    private KniazSaver saver;

    private KniazLineParser lineParser;

    private KniazOutputFlavourer outputFlavourer;

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
     * @param lineParser   The lineparser to use.
     * @param outputFlavourer  The KniazOutputFlavourer to use.
     */
    private KniazSession(TaskList taskList,
                         KniazLoader loader,
                         KniazSaver saver,
                         KniazLineParser lineParser,
                         KniazOutputFlavourer outputFlavourer) {
        this.taskList = taskList;
        this.loader = loader;
        this.saver = saver;
        this.lineParser = lineParser;
        this.outputFlavourer = outputFlavourer;
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
        KniazOutputFlavourer flavourer = new KniazOutputFlavourer();
        TaskList tasks = new TaskList();


        try {
            tasks = loader.load();

        } catch (KniazRuntimeException e) {
            System.out.println(e.getMessage());
        }

        KniazSession out = new KniazSession(tasks, loader, saver, lineParser, flavourer);
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


    /**
     * Runs one set of logic for the given input
     * @param input the user input
     * @return the command to execute in the user interface
     */
    public UiCommand runOneIter(String input) {

        if (!isRunning) {
            return new UiCommand(UiCommand.UiInstructType.QUIT, "");
        }

        KniazCommand nextCommand = lineParser.parseLine(input);
        String printString;
        String flavour = outputFlavourer.getFlavourFor(nextCommand.getInstruct());

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

        if (!this.isRunning) {
            return new UiCommand(UiCommand.UiInstructType.QUIT, printString);
        } else {
            return new UiCommand(UiCommand.UiInstructType.PRINT, printString);
        }

        // If KniazCommand above was to quit, this.isRunning will be set to false



    }

}
