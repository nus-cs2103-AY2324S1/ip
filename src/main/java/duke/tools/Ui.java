package duke.tools;

public class Ui {
    private static final String line = "___________________________________________";

    /**
     * Method that prints chatbot intro
     */
    public String printIntro() {
        String output = line + "\n";
        output += "Hey, I'm joyayaya! What's the move today?\n";
        output += line;
        return output;
    }

    /**
     * Method to print loading error.
     */
    public void showLoadingError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Method to print outro.
     */
    public static String printOutro() {
        String output = line + "\n";
        output += "Bye! CU again!\n";
        output += line;
        return output;
    }

}
