package ui;


/**
 * The programme that interacts with the user.
 */
public class Ui {

    /**
     * Returns the user input.
     * @return User input.
     */
    public String readCommand() {
        MainWindow mW = new MainWindow();
        //Scanner scanner = new Scanner(System.in);
        return mW.getUserInput();
    }

    /**
     * Displays welcome message to the user.
     */
    public void welcomeMsg() {
        MainWindow mW = new MainWindow();
        String str = mW.getResponse("Hello! I'm Red\nWhat can I do for you?");
        //System.out.println("Hello! I'm Red\nWhat can I do for you?");
    }

}
