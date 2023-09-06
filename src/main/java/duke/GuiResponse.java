package duke;

/**
 *  class for duke gui response.
 */
public class GuiResponse {
    /**
     * show welcome message
     */
    public String getWelcomeMessage (){
        return "Hello! I'm ChatBot" + "\n" + "What can I do for you?" + "\n";
    }

    /**
     * show goodbye message
     */
    public String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * print the list of task
     * @param tasks the TaskList
     */
    public String getTaskList(TaskList tasks) {
        String responseString = "";
        int size = tasks.getSize();
        for (int i = 0; i < size; i++) {
            Task curr = tasks.getTask(i);
            responseString += (i + 1) + ". " + curr;
            responseString += "\n";
        }
        return responseString;
    }

    /**
     * printed when task is added
     * @param curr current task being added
     * @param taskSize the getSize of TaskList
     */
    public String getAddTask(Task curr, int taskSize) {
        String responseString = "";
        responseString += "Got it. I've added this task:";
        responseString += "\n";
        responseString += curr;
        responseString += "\n";
        responseString += "Now you have " + taskSize + " tasks in the list.";
        return responseString;
    }

    /**
     * printed when task is deleted
     * @param curr current task being deleted
     * @param taskSize the getSize of TaskList
     */
    public String getDelete(Task curr, int taskSize) {
        String responseString = "";
        responseString += "Noted. I've removed this task:";
        responseString += "\n";
        responseString += curr;
        responseString += "\n";
        responseString += "Now you have " + taskSize + " tasks in the list.";
        return responseString;
    }

    /**
     * printed when task is marked as done
     * @param curr current task
     * @param index index of the task in TaskList
     */
    public String getMark(Task curr, int index) {
        String responseString = "";
        responseString += "Nice! I've marked this task as done:";
        responseString += "\n";
        responseString += (index + 1) + ". " + curr;
        return responseString;
    }
    /**
     * printed when task is marked as not done
     * @param curr current task
     * @param index index of the task in TaskList
     */
    public String getUnmark(Task curr, int index) {
        String responseString = "";
        responseString += "Ok, I've marked this task as not done yet:";
        responseString += "\n";
        responseString += (index + 1) + ". " + curr;
        return responseString;
    }

    /**
     * show loading error
     * @param message error message
     */
    public String getLoadingError(String message) {
        return message;
    }

    /**
     * Print search query result
     * @param results a TaskList of search result
     */
    public String getQueryResult(TaskList results) {
        String responseString = "";
        responseString += "Here are the matching tasks in your list:";
        responseString += "\n";
        getTaskList(results);
        return responseString;
    }
}
