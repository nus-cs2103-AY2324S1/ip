package duke.extensions;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * Custom class to search for a given keyword in the list of tasks
 */
public class SearchInTasks {
    /**
     * Returns the various tasks that match substrings of the keyword
     * @param input user's input
     * @return string representation of operayion
     * @throws DukeException
     */
    public static String handleFindTask(String input, ArrayList<Task>tasks) throws DukeException {
        StringBuilder results = new StringBuilder();
        String[] parts = input.split(" ");

        if (parts.length == 1) {
            throw new DukeException("SUI, Specify keyword to search for!\n");
        }
        if (parts.length > 2) {
            throw new DukeException("SUI, Enter only one keyword to search!\n");
        }

        int resultCounter = 1;
        String keyword = parts[1];
        boolean[] isTaskAddedToResults = new boolean[tasks.size()];

        for (int startIndex = 0; startIndex < keyword.length(); startIndex++) {
            for (int endIndex = startIndex + 1; endIndex < keyword.length(); endIndex++) {
                String keywordToSearch = keyword.substring(startIndex, endIndex + 1);
                results.append(searchForKeyword(tasks, keywordToSearch, isTaskAddedToResults, resultCounter));
            }
        }

        System.out.println(results);
        if (results.toString().equals("")) {
            return "SUI, No results found!";
        }
        assert results.length() > 0 : "SUI, results found.";
        return "Here are the results for your search: \n" + results;
    }

    /**
     * Returns all the matching tasks for the substrings
     * @param keywordToSearch keyword to search for
     * @param isTaskAddedToResults boolean array to check if the specific tasl has been added already
     * @param resultCounter the number of result tasks in the result string
     * @return
     */
    public static String searchForKeyword(ArrayList<Task> tasks, String keywordToSearch,
                                          boolean[] isTaskAddedToResults, int resultCounter) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getList().contains(keywordToSearch) && !isTaskAddedToResults[i]) {
                res.append(resultCounter).append(".").append(tasks.get(i).toString()).append("\n");
                resultCounter++;
                isTaskAddedToResults[i] = true;
            }
        }
        return res.toString();
    }

}
