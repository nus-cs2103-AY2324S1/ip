package taskutility;

/**
 * The `TaskMatcher` class provides a utility method for matching a keyword with a task description.
 * It allows checking if a keyword exists within the task description string.
 */
public class TaskMatcher {

    /**
     * Checks if the given keyword exists within the task description.
     *
     * @param keyword  The keyword to search for within the task description.
     * @param taskDesc The task description to search within.
     * @return True if the keyword is found in the task description; otherwise, false.
     * @throws IllegalArgumentException If either the keyword or task description is null.
     */
    public static boolean isMatch(String keyword, String taskDesc) {
        assert keyword != null : "keyword must not be null";
        assert taskDesc != null : "taskDesc must not be null";
        String[] split = taskDesc.split(" ");
        for (int i = 0; i < split.length; i++) {
            if (keyword.equals(split[i])) {
                return true;
            }
        }
        return false;
    }
}

