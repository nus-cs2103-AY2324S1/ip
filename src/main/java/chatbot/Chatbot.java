package chatbot;

import chatbot.command.Command;
import chatbot.task.TaskManager;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class Chatbot {
    private TaskManager taskManager;
    private Image userImage;
    private Image botImage;

    public Chatbot() {
        this.taskManager = new TaskManager();
        userImage = loadImage("/DaDuke.png");
        botImage = loadImage("/DaUser.png");
    }

    public String processUserInput(String input) {
        try {
            Command command = Parser.parseCommand(input);
            String botResponse = command.execute(taskManager);
            if (command.isExit()) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.exit(0);  // Terminates the currently running Java Virtual Machine
                    }
                }, 1000);  // Delay in milliseconds (3000ms = 3s)

                return botResponse;
            }
            return botResponse;
        } catch (ChatbotException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred.";
        }
    }

    private Image loadImage(String path) {
        InputStream imageStream = this.getClass().getResourceAsStream(path);
        if (imageStream == null) {
            System.err.println("Failed to load image from path: " + path);
            throw new RuntimeException("Image not found: " + path);
        }
        return new Image(imageStream);
    }
}
