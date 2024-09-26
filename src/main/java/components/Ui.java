package components;

public class Ui {

    /**
     * Constructor for Ui class.
     */
    public Ui () {

    }

    /**
     * Prints the error message.
     */
    public String showError(DukeException e) {
        return e.toString();
    }

    public String showWelcome(String chatBotName) {
        return "Welcome to your very own Glorious Excel Sheet! I'm " + chatBotName + "\n\n" +
                "Here are the list of commands I offer"+ "\n\n" +
                "bye, list, mark, unmark, delete, find, todo, deadline, event" + "\n\n" +
                "ADDITIONAL SUPPORT FOR DUPLICATE TASK HANDLING" + "\n\n" +
                "If you try to add a task with a description that " +
                "already exists in the list, " +
                "I will ask you if you want to add it anyway" + "\n\n" +
                "If you respond with a 'yes', I will add the task" + "\n\n" +
                "If not, the task will not be added" + "\n\n" +
                "todo [DESC]" + "\n" +
                "deadline [DESC] /by yyyy-MM-dd" + "\n" +
                "event [DESC] /from yyyy-MM-dd /to yyyy-MM-dd";
    }

    /**
     * Shows the goodbye message.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }
}
