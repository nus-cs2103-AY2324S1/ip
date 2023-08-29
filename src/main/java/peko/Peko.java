package peko;

public class Peko {
    private UserInputHandler userInputHandler;
    private UI ui;
    private StorageHandler storageHandler;
    public static void main(String[] args) {
        new Peko().run();
    }
    private void run() {
        Output.intro();
        while (true) {
            userInputHandler.newInput();
            if (!userInputHandler.processInput()) {
                break;
            }
        }
        SaveHandler.saveTo();
        Output.exit();

    }
    private Peko() {
        userInputHandler = new UserInputHandler();
        storageHandler = new StorageHandler();

    }

}
