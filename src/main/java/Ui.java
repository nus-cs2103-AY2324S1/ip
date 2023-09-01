public class Ui {
    private static final String line = "___________________________________________";

    /**
     * Method that prints chatbot intro
     */
    public void intro() {
        System.out.println(line);
        System.out.println("Hey, I'm joyayaya! What's the move today?");
        System.out.println(line);
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
    public static void outro() {
        System.out.println(line);
        System.out.println("Buh-bye! See you soon!");
        System.out.println(line);
    }

}
