package functions;

public class Ui {

    /**
     * A public constructor to initialize a new Ui instance
     *
     */
    public Ui() {
        System.out.println("____________________________________________________________");
        System.out.println("Initializing...");
    }
    public String fileNotFound() {
        return "File not found. Creating new .txt save file";
    }

    public String goodbye() {
        String message = "";
        message += "_______________________________________\n";
        message += "Bye. Hope to see you again soon!\n";
        message += "_______________________________________";
        return message;
    }
}
