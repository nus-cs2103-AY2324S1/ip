package duke;

public class Duke {
    private static final String NAME = "Nino!";

    public static void main(String[] args) {
        try {
            UserInterface userInterface = new UserInterface(new Storage(), new StoreList());

            System.out.println("Hello, my name is " + Duke.NAME);
            UserInterface.display("What can I do for you?");
            userInterface.start();
            userInterface.readCommandLine();
            userInterface.exit();

        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
