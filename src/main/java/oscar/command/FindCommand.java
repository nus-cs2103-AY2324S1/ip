package oscar.command;

import oscar.essential.InfoList;
import oscar.essential.Storage;
import oscar.exception.OscarException;

/**
 * Command to find a task in the info list.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Instantiates a find command.
     *
     * @param k String used to find infos.
     */
    public FindCommand(String k) {
        this.keyword = k;
    }

    /**
     * Find an info by searching for a keyword.
     *
     * @param infos   ArrayList of infos.
     * @param storage File loading and saving handler.
     * @return String output of find command.
     * @throws OscarException Failure to execute command.
     */
    @Override
    public String execute(InfoList infos, Storage storage) throws OscarException {
        assert infos != null;
        validate();
        return infos.find(keyword);
    }

    /**
     * Validates keyword used to find task.
     *
     * @throws OscarException Keyword is blank or not within 200 characters.
     */
    public void validate() throws OscarException {
        if (keyword.isEmpty()) {
            throw new OscarException("Sorry! The keyword of a find command cannot be empty.\n");
        } else if (keyword.length() > 200) {
            throw new OscarException("Sorry! The keyword of a find command cannot exceed 200 characters.\n");
        }
    }
}
