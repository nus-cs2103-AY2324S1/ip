package seedu.duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * Represents my version of a chatbot.
 * It is mainly used for recording tasks like
 * todos, deadlines and events.
 *
 * @author KAM JIA YUE
 * @since 2023-08-29
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private TaskList taskList;
    private Ui ui;

    /**
     * An alternative constructor for this chatbot.
     */
    public Duke() {
        this.taskList = new TaskList();
        this.ui = new Ui("Kam_BOT");
    }

    /**
     * The main constructor for this chatbot.
     *
     * @param name Name of this chatbot.
     */
    public Duke(String name) {
        this.taskList = new TaskList();
        this.ui = new Ui(name);
    }

    /**
     * Start and set up the chatbot.
     *
     * @param filePath Path of the storage file.
     */
    public void startUp(String filePath) {
        this.ui.greet();
        this.taskList.setHardDiskFilePath(filePath);
        this.taskList.loadData();
    }

    /**
     * Starts the service of interacting with the user.
     *
     * @throws DukeException If there are user input errors.
     */
    public void startService() throws DukeException {
        boolean exceptionOccurs = false;

        Commands cmd = this.ui.getNextUserInput();

        Ui.line();

        Task task = null;

        try {
            switch (cmd) {
            case BYE:
                this.ui.bye();
                return;
            case LIST:
                this.taskList.listOutEverything();
                break;
            case MARK:
                int markIndex = this.ui.mark();
                if (this.taskList.isOutOfRange(markIndex)) {
                    throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a valid task number to be marked.");
                }
                this.taskList.mark(markIndex);
                break;
            case UNMARK:
                int unmarkIndex = this.ui.unmark();
                if (taskList.isOutOfRange(unmarkIndex)) {
                    throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a valid task number to be unmarked.");
                }
                this.taskList.unmark(unmarkIndex);
                break;
            case DELETE:
                int deleteIndex = this.ui.delete();
                if (taskList.isOutOfRange(deleteIndex)) {
                    throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a valid task number to be deleted.");
                }
                this.taskList.remove(deleteIndex);
                break;
            case TODO:
                task = this.ui.todo();
                break;
            case DEADLINE:
                task = this.ui.deadline();
                break;
            case EVENT:
                task = this.ui.event();
                break;
            case FIND:
                String toFind = this.ui.find();
                this.taskList.find(toFind);
                break;
            default:
                throw new DukeException(Ui.I5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            exceptionOccurs = true;
            System.out.println(e.getMessage());
        }

        if (!exceptionOccurs && task != null) {
            this.taskList.add(task);
        }

        Ui.line();
        startService();
    }

    /**
     * Starts JavaFX service.
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage stage) {

        this.scrollPane = new ScrollPane();
        this.dialogContainer = new VBox();
        this.scrollPane.setContent(dialogContainer);

        this.userInput = new TextField();
        this.sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(this.scrollPane, this.userInput, this.sendButton);

        Scene scene = new Scene(mainLayout); // Setting the scene to be our Label

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        this.scrollPane.setPrefSize(385, 535);
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        this.scrollPane.setVvalue(1.0);
        this.scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        this.dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        this.userInput.setPrefWidth(325.0);

        this.sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(this.scrollPane, 1.0);

        AnchorPane.setBottomAnchor(this.sendButton, 1.0);
        AnchorPane.setRightAnchor(this.sendButton, 1.0);

        AnchorPane.setLeftAnchor(this.userInput , 1.0);
        AnchorPane.setBottomAnchor(this.userInput, 1.0);



        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * This the main method which uses this chatbot.
     *
     * @param args Unused.
     * @throws DukeException If there are any errors occured.
     */
    public static void main(String[] args) throws DukeException {
        Duke bot = new Duke("Kam_BOT");
        bot.startUp("./data/duke.txt");
        bot.startService();
    }
}
