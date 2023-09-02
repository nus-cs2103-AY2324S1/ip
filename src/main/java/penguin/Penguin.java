package penguin;

import java.time.format.DateTimeParseException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

/**
 * Penguin is the main logic of Penguin chatbot; its main responsibility is to parse commands and handle errors.
 */
public class Penguin extends Application {
    private static final String GREETING = "Honk! I'm Penguin! What can I do for you?";
    private static final String GOODBYE = "Honk! Hope to see you again soon!";
    private static final String MARK = "Honk honk! You did task ";
    private static final String UNMARK = "Fishes! You didn't do task ";
    private static final String TODO = "Honk! You'll have to do ";
    private static final String DEADLINE = "Flap flap flap! Must do this ";
    private static final String EVENT = "Fish party?! ";

    private static final String DELETE = "This task is thrown into the sea! ";

    private UI ui;
    private TaskList taskList;
    private Storage memory;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    /**
     * Constructor for Penguin chatbot.
     */
    public Penguin() {
        this.ui = new UI();
        this.taskList = new TaskList();
        this.memory = new Storage("data/memory.txt");
        this.parser = new Parser();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // more code to be added here later
    }

    /**
     * Runs the Penguin chatbot.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {

        new Penguin().run();
    }

    /**
     * Starts the main logic of Penguin chatbot; parses commands and handles errors.
     */
    public void run() {
        ui.out(GREETING);
        boolean running = true;
        this.taskList = memory.load();

        while (running) {
            try {
                String command = ui.in();
                String[] spl = null;
                int taskNo;

                switch (parser.parse(command)) {
                    case "bye":
                        ui.out(GOODBYE);
                        running = false;
                        break;
                    case "list":
                        ui.out(taskList.printList());
                        break;
                    case "mark":
                        spl = command.split(" ", 2);
                        taskNo = Integer.parseInt(spl[1]);
                        taskList.list.get(taskNo - 1).done = true;
                        ui.out(MARK + taskList.list.get(taskNo - 1).getDisplay());
                        break;
                    case "unmark":
                        spl = command.split(" ", 2);
                        taskNo = Integer.parseInt(spl[1]);
                        taskList.list.get(taskNo - 1).done = false;
                        ui.out(UNMARK + taskList.list.get(taskNo - 1).getDisplay());
                        break;
                    case "todo":
                        spl = command.split("todo ");
                        ToDo newToDo = new ToDo(spl[1]);
                        taskList.addTask(newToDo);
                        ui.out(TODO + newToDo.getDisplay());
                        break;
                    case "deadline":
                        spl = command.split("deadline | /by ");
                        Deadline newDeadline = new Deadline(spl[1], spl[2]);
                        taskList.addTask(newDeadline);
                        ui.out(DEADLINE + newDeadline.getDisplay());
                        break;
                    case "event":
                        spl = command.split("event | /from | /to ");
                        Event newEvent = new Event(spl[1], spl[2], spl[3]);
                        taskList.addTask(newEvent);
                        ui.out(EVENT + newEvent.getDisplay());
                        break;
                    case "delete":
                        spl = command.split(" ");
                        taskNo = Integer.parseInt(spl[1]);
                        ui.out(DELETE + taskList.list.remove(taskNo - 1).getDisplay());
                        break;
                    case "find":
                        spl = command.split(" ");
                        String match = spl[1];
                        ui.out(taskList.findInList(match));
                        break;
                    case "unknown":
                        throw new PenguinUnknownCommandException();

                }

                memory.save(taskList);
            } catch (PenguinException e) {
                ui.out("Fishes!! " + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.out("No fishes? One or more fields in your command is empty or malformed.");
            } catch(DateTimeParseException e) {
                ui.out("Dates must be in penguin format yyyy-mm-dd!");
            } catch (Exception e) {
                ui.out("Flap flap flap flap!! An unexpected error occurred...");
            }
        }

    }
}
