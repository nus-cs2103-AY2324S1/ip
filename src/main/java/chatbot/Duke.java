package chatbot;

import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;


public class Duke extends Application {
    /** Storage to load and write files. */
    private Storage storage; 
    /** UI to handle the printing of output. */
    private UI ui;
    /** taskHandler to store the list of Tasks and do operations. */
    private TaskList taskHandler;
    /** Interprets user input. */
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/dukeBot.png"));

    private boolean isFirstResponse;

    public Duke() {
        // root starts at ip
        this.storage = new Storage("./data/duke.txt");
        this.ui = new UI(storage);
        this.taskHandler = new TaskList();
        this.parser = new Parser(ui, taskHandler, storage);
        this.isFirstResponse = true;
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        scene = new Scene(helloWorld); // Setting the scene to be our Label

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    
        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /** 
     * Runs the program until the loop breaks.
     * The loop only breaks when the input is "bye"
     * and breaker is set to 1.
     */
    public void run() {
        /*ui.startProgram();
        
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            
            int breaker = parser.runInput(input);
            
            if (breaker == 1) {
                break;
            } else {
                continue;
            }
        }

        sc.close();*/
    }

    public String run(String input) {
        return parser.runInput(input);
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        // Label userText = new Label(userInput.getText());
        // Label dukeText = new Label(getResponse(userInput.getText()));
        String userText = userInput.getText();
        String dukeText = getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        if (isFirstResponse) {
            isFirstResponse = false;
            return ui.startProgram();
        } else {
            return run(input);
        }
    }
    
    public static void main(String[] args) {
        // new Duke().run();
        /* String print1 = "Hello! I'm Afro\n"
                        + "What can I do for you?\n";

        String str;
        List<Task> taskForce = new ArrayList<Task>();

        System.out.println(print1);

        File file = new File("./data/duke.txt");

        try {
            Scanner printSC = new Scanner(file);
            while (printSC.hasNextLine()) {
                System.out.println(printSC.nextLine());
            }
            printSC.close();

        } catch (FileNotFoundException e) {
            System.out.println("No file found!");
        }

        Scanner sc = new Scanner(System.in);

        while (true) {
            str = sc.nextLine();

            if (str.equals("bye")) {
                break;
                
            } else if (str.equals("list")) {
                String toBeSaved = "";
                for (Task task : taskForce) {
                    int index = taskForce.indexOf(task) + 1;

                    toBeSaved = toBeSaved + task + "\n";
                    System.out.println(index + ":" + task);
                }

                File path = new File("./data/duke.txt");

                try {
                    FileWriter wr = new FileWriter(path);
                    wr.write(toBeSaved);
                    wr.flush();
                    wr.close();
                } catch (IOException e) {
                    System.out.println("This will never occur.");
                }
                
                
            } else if (str.startsWith("unmark")) {
                try {
                    validateInput(str, 7);
                } catch (UserInputException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                int stringLength = str.length();
                int index = Integer.parseInt(str.substring(stringLength - 1)) - 1;
                
                Task task = taskForce.get(index);
                task.markTaskNotDone();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task.getStatusIcon() + " " + task.description);

            } else if (str.startsWith("mark")) {
                try {
                    validateInput(str, 5);
                } catch (UserInputException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                int stringLength = str.length();
                int index = Integer.parseInt(str.substring(stringLength - 1)) - 1;

                Task task = taskForce.get(index);
                task.markTaskDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task.getStatusIcon() + " " + task.description);

            } else if (str.startsWith("delete")) {
                try {
                    validateInput(str, 7);
                } catch (UserInputException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                int stringLength = str.length();
                int index = Integer.parseInt(str.substring(stringLength - 1)) - 1;
                Task task = taskForce.get(index);

                System.out.println("Noted. I have removed this task.");
                System.out.println(task);
                taskForce.remove(index);

                System.out.println("Now you have " + taskForce.size() + " tasks in the list.");

            } else {
                if (!(str.contains("todo") || str.contains("event") 
                        || str.contains("deadline"))) {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    continue;
                } else if (str.startsWith("todo")) {
                    try {
                        validateInput(str, 5);
                    } catch (UserInputException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
    
                    String[] moreStrings = str.split(" ", 2);

                    Task todo = new Todo(moreStrings[1]);
                    taskForce.add(todo);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(todo);

                } else if (str.startsWith("deadline")) {
                    try {
                        validateInput(str, 9);
                    } catch (UserInputException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    String[] moreStrings = str.split("/", 2);
                    String[] getDescription = moreStrings[0].split(" ", 2);

                    String returnBy = moreStrings[1].substring(3);

                    Task deadline = new Deadline(getDescription[1], returnBy);
                    taskForce.add(deadline);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline);

                } else if (str.startsWith("event")) {
                    try {
                        validateInput(str, 6);
                    } catch (UserInputException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    String[] moreStrings = str.split("/");
                    String[] pullStrings = moreStrings[0].split(" ", 2);

                    String from = moreStrings[1].substring(4);
                    String to = moreStrings[2].substring(2);

                    Task event = new Event(pullStrings[1], from, to);
                    taskForce.add(event);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(event);
                } 

                System.out.println("Now you have " + taskForce.size() + " tasks in the list.");
            }
        }

        sc.close();
        System.out.println("Bye. Hope to see you again soon!");*/

    }

    /* public static void validateInput(String str, int minimum) throws UserInputException {
        if (str.length() <= minimum) {
            throw new UserInputException("OOPS!!! The description of a " + str + " cannot be empty.");
        }
    }*/
}
