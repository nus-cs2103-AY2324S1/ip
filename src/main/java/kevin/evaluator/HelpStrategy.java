package kevin.evaluator;

import java.util.ArrayList;

import kevin.exception.KevinException;
import kevin.storage.FileStorage;
import kevin.tasklist.TaskList;
import kevin.ui.Logger;

/**
 * A class responsible for the logic for HELP command.
 */
public class HelpStrategy extends BaseStrategy {
    /**
     * Constructor to initialize HelpStrategy.
     * @param taskList This is the TaskList where the tasks are stored and operations are defined.
     * @param arguments This is an ArrayList where all the needed arguments are stored.
     */
    public HelpStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }

    /**
     * Handles the logic of the HELP command.
     * @param logger This is the Logger that handles System.out.println.
     * @param fileStorage This is the FileStorage that handles the storage in the local computer.
     * @param isInFile This is the boolean to show whether the task is in the local computer's file.
     * @return Returns a boolean that determines the continuation of the evaluation.
     * @throws KevinException On the detection of errors.
     */
    @Override
    public String evaluate(Logger logger, FileStorage fileStorage, boolean isInFile) throws KevinException {
        String helpCommand = new StringBuilder().append("Available commands: \n")
                .append("1. todo <taskName> \n\tmake a new todo task\n")
                .append("2. deadline <taskName> /by <date> \n\tmake a new deadline task. \n\t")
                .append("date format: \"d/MM/yyyy HHmm\" or \"dd/MM/yyyy HHmm\"\n")
                .append("3. event <taskName> /from <startingTime> /to <endingTime>")
                .append("\n\tmake a new event task.")
                .append("4. list \n\tlist all your tasks\n")
                .append("5. mark <indexNumber> \n\tmark task as finished\n")
                .append("6. unmark <indexNumber> \n\tmark task as unfinished\n")
                .append("7. find <keyword> \n\tfind tasks that contains the keyword\n")
                .append("8. delete <indexNumber> \n\tdelete task\n")
                .append("9. bye \n\texit the programme\n")
                .append("10. help \n\trequest for help on how to use the bot\n").toString();
        return helpCommand;
    }
}
