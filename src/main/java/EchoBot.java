import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * The main class that represents the EchoBot application.
 */
public class EchoBot {
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private ArrayList<Task> tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Initializes and sets up the EchoBot GUI, loads tasks, and displays a welcome message.
     */
    public EchoBot() {
        frame = new JFrame("EchoBot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        chatArea = new JTextArea();
        chatArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(chatArea);
        inputField = new JTextField();

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUserInput();
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        tasks = new ArrayList<>();
        ui = new Ui();
        storage = new Storage("./data/duke.txt");

        try {
            tasks = storage.loadTasks(chatArea);
        } catch (Exception e) {
            chatArea.append("An error occurred while loading tasks: " + e.getMessage() + "\n");
        }

        ui.showWelcomeMessage(chatArea);

        frame.setVisible(true);
    }

    private void handleUserInput() {
        String userInput = inputField.getText().trim();
        inputField.setText("");

        if (userInput.equalsIgnoreCase("bye")) {
            ui.showByeMessage(chatArea);

            // Delay closing the GUI by 1 seconds
            int delay = 1000;
            Timer timer = new Timer(delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose(); // Close the GUI
                }
            });
            timer.setRepeats(false); // Execute the timer only once
            timer.start();
        } else if (userInput.equalsIgnoreCase("list")) {
            ui.showTasks(tasks, chatArea);
        } else if (userInput.startsWith("todo")) {
            String taskDescription = Command.extractTaskDesc(userInput, "todo");
            Command addCommand = new AddCommand(Command.TaskType.TODO, taskDescription,
                    null, null);
            addCommand.doCommand(tasks, ui, storage, chatArea);
        } else if (userInput.startsWith("deadline")) {
            String taskDescription = Command.extractTaskDesc(userInput, "deadline");
            int indexOfBy = taskDescription.indexOf("/by");
            String deadlineDescription = taskDescription.substring(0, indexOfBy).trim();
            String by = taskDescription.substring(indexOfBy + 3).trim();
            Command addCommand = new AddCommand(Command.TaskType.DEADLINE, deadlineDescription,
                    by, null);
            addCommand.doCommand(tasks, ui, storage, chatArea);
        } else if (userInput.startsWith("event")) {
            String taskDescription = Command.extractTaskDesc(userInput, "event");
            int indexOfFrom = taskDescription.indexOf("/from");
            int indexOfTo = taskDescription.indexOf("/to");
            String eventDescription = taskDescription.substring(0, indexOfFrom).trim();
            String from = taskDescription.substring(indexOfFrom + 5, indexOfTo).trim();
            String to = taskDescription.substring(indexOfTo + 3).trim();
            Command addCommand = new AddCommand(Command.TaskType.EVENT, eventDescription, from, to);
            addCommand.doCommand(tasks, ui, storage, chatArea);
        } else if (userInput.startsWith("mark")) {
            int taskNum = Command.extractTaskNum(userInput, "mark");
            Command markCommand = new MarkCommand(taskNum);
            markCommand.doCommand(tasks, ui, storage, chatArea);
        } else if (userInput.startsWith("unmark")) {
            int taskNum = Command.extractTaskNum(userInput, "unmark");
            Command unmarkCommand = new UnmarkCommand(taskNum);
            unmarkCommand.doCommand(tasks, ui, storage, chatArea);
        } else if (userInput.startsWith("delete")) {
            int taskNum = Command.extractTaskNum(userInput, "delete");
            Command deleteCommand = new DeleteCommand(taskNum);
            deleteCommand.doCommand(tasks, ui, storage, chatArea);
        } else if (userInput.startsWith("find")) {
            String keyword = Command.extractTaskDesc(userInput, "find");
            Command findCommand = new FindCommand(keyword);
            findCommand.doCommand(tasks, ui, storage, chatArea);
        } else {
            chatArea.append("OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n");
        }
    }

    /**
     * The entry point of the EchoBot application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EchoBot();
            }
        });
    }
}
