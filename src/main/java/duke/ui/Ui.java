package duke.ui;

import java.util.ArrayList;
import javax.swing.JTextArea;

import duke.task.Task;

/**
 * Provides methods related to user interface.
 */
public class Ui {
    /**
     * Displays the EchoBot logo.
     */
    public static void showLogo(JTextArea chatArea) {
        chatArea.append("     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n");
    }

    /**
     * Displays the welcome message.
     *
     * @param chatArea JTextArea where the message will be displayed.
     */
    public void showWelcomeMessage(JTextArea chatArea) {
        chatArea.append("Hello! I'm EchoBot\n");
        showLogo(chatArea);
        chatArea.append("What can I do for you?\n");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks    The list of tasks.
     * @param chatArea JTextArea where the message will be displayed.
     */
    public void showTasks(ArrayList<Task> tasks, JTextArea chatArea) {
        chatArea.append("Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            chatArea.append("    " + (i + 1) + ". ");
            task.display(chatArea);
        }
    }

    /**
     * Displays the goodbye message.
     *
     * @param chatArea JTextArea where the message will be displayed.
     */
    public void showByeMessage(JTextArea chatArea) {
        chatArea.append("Bye. Hope to see you again soon!\n");
    }
}
