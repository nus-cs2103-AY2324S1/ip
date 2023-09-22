package Forgotten.GUI;

import Forgotten.Forgotten;

public class ForgottenGUI {

    Forgotten forgotten;

    public ForgottenGUI() {
        this.forgotten = new Forgotten("src/main/java/Database.txt");
    }

    /**
     * This method generates a bot reply that is based on the user's command.
     *
     * @param userCommand The command entered by the user.
     * @return Reply from the bot.
     */
    public String getResponse(String userCommand) {
        return forgotten.generateResponse(userCommand);
    }

    /**
     * This method prints the greeting message.
     */
    public String printGreetMessage() {
        String greetMessage = "Hello! I'm Forgotten\nWhat can I do for you?";
        return greetMessage;
    }

    /**
     * This method prints the bye message.
     */
    public String printByeMessage() {
        String byeMessage = "Bye. Hope to see you again soon!";
        return byeMessage;
    }
}