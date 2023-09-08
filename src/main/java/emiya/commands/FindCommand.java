package emiya.commands;

import emiya.emiyaexception.CannotFindWordException;
import emiya.emiyaexception.EmptyFindException;
import emiya.emiyaexception.ListEmptyException;
import emiya.task.TaskList;

/**
 * A class that represents the Find command.
 */
public class FindCommand {

    /**
     * Finds tasks in the task list that contain a specific input from the user.
     *
     * @param word The specific word that the user is searching for from among the tasks.
     * @param taskList The TaskList instance associated with the task bot.
     * @return A String that contains the tasks that contain the input from the user.
     * @throws ListEmptyException An exception that is thrown when the user tries to access the list when the list
     *     is empty.
     * @throws CannotFindWordException An exception that is thrown when the word cannot be found from the task list.
     * @throws EmptyFindException An exception that is thrown when the find command is used by the user
     *     without providing a word to find.
     */
    public static String find(String word, TaskList taskList) throws ListEmptyException, CannotFindWordException,
            EmptyFindException {
        if (word.equals("")) {
            throw new EmptyFindException();
        } else {
            return taskList.find(word);
        }
    }
}
