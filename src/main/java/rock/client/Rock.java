package rock.client;
import java.nio.file.Path;
import java.nio.file.Paths;

import rock.client.exceptions.RockException;
import rock.logic.commands.Commands;
import rock.logic.io.Invoker;
import rock.storage.Storage;
import rock.storage.exceptions.StorageException;
import rock.tasks.TaskList;
import rock.ui.Gui;

/**
 * Rock is the name of and the main program used
 * to run the bot for ip.
 * @author Alvis Ng (supermii2)`
 */
public class Rock {
    /** Default Filepath to save data if no filepath is specified*/
    private static final Path DEFAULT_FILEPATH = Paths.get("data/tasks.ser");

    private Gui gui;
    private TaskList taskList;
    private Storage storage;
    private Invoker invoker;
    private boolean isTerminated = false;

    /**
     * Initialises Rock with default file path
     */
    public Rock(Gui gui) {
        this(gui, DEFAULT_FILEPATH);
    }

    /**
     * Initialises Rock
     * @param path Path to save and load Rock data
     */
    public Rock(Gui gui, Path path) {
        this.gui = gui;
        this.taskList = new TaskList();
        this.invoker = new Invoker(new Commands(this));
        try {
            this.storage = new Storage(path.toAbsolutePath().toFile(), this);
        } catch (StorageException e) {
            gui.sendWarning(Response.createErrorResponse(e.getMessage()));
        }
    }

    public boolean isTerminated() {
        return isTerminated;
    }
    /**
     * Obtain a response given a command
     * @param input Command string
     * @return Response
     */
    public Response getResponse(String input) {
        try {
            return Response.createValidResponse(invoker.handle(input));
        } catch (RockException e) {
            return Response.createErrorResponse(e.getMessage());
        }
    }

    /**
     * Sends warning given an exception
     * to be displayed on gui
     * @param msg Warning message
     */
    public void sendWarning(String msg) {
        gui.sendWarning(Response.createErrorResponse(msg));
    }
    /**
     * Sets existing task list to tl
     * @param tl Task List to be set
     */
    public void setTaskList(TaskList tl) {
        this.taskList = tl;
    }
    /* Commands that call the save/load feature */
    /**
     * Saves the existing task list
    */
    public void saveFile() {
        this.saveFile(taskList);
    }

    /**
     * Saves the given task list
     * @param tl Task list to be saved
     */
    public void saveFile(TaskList tl) {
        try {
            storage.saveSaveFile(tl);
        } catch (StorageException e) {
            gui.sendWarning(Response.createErrorResponse(e.getMessage()));
        }
    }

    /**
     * Get task list attached to bot
     * @return TaskList of bot
     */
    public TaskList getTaskList() {
        return taskList;
    }
    /**
     * Terminates the program
     */
    public void terminate() {
        // Sets necessary fields to closed.
        this.isTerminated = true;
    }
}
