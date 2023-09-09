import java.util.ArrayList;

import tasks.Task;

/**
 * Ui handles the interactions with user.
 *
 * @author Sebastian Tay
 */
public class Ui {

    /**
     * Greets the user.
     *
     * @return String containing the welcome message.
     */
    public String getWelcomeMessage() {
        return "Veda initialised. How may I help you?";
    }

    /**
     * Bids goodbye to the user.
     *
     * @return String containing exit message
     */
    public String getExitMessage() {
        return "Bye. All the best for your mission!";
    }

    /**
     * Informs user that command is unrecognised.
     *
     * @return String informing user that their input is not recognised.
     */
    public String getUnrecognisedInputMessage() {
        return "Unrecognised command.";
    }


    /**
     * List out the missions in tasks.
     *
     * @param tasks contains the missions to be listed.
     * @return a String containing the list of missions.
     */
    public String getListOfMissions(ArrayList<Task> tasks) {
        String list = "Missions: \n";

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            list += ((tasks.indexOf(task) + 1) + "." + task + "\n");
        }

        return list;
    }

    /**
     * List out the missions in tasks with the message at the top.
     *
     * @param tasks contains the missions to be listed.
     * @param message stating what keyword is being searched for.
     * @return String containing the list of missions
     */
    public String getListOfMissions(ArrayList<Task> tasks, String message) {
        String list = message + "\n";

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            list += ((tasks.indexOf(task) + 1) + "." + task + "\n");
        }

        return list;

    }
}
