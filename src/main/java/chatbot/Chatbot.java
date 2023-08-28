package chatbot;

import chatbot.exception.ChatbotException;
import chatbot.exception.ChatbotRuntimeException;
import taskmanager.TaskManager;
import taskmanager.task.Deadline;
import taskmanager.task.Event;
import taskmanager.task.Task;
import taskmanager.task.Todo;
import util.EpochConverter;
import util.events.EventEmitter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * A chatbot to interact with.
 *
 * <p>
 *     This class is an abstraction over a chatbot that also supplies methods to allow
 *     users to easily interact with the bot, and for UI clients to subscribe to chat messages.
 * </p>
 */
public class Chatbot extends EventEmitter<ChatMessage> {

    /** The default name of the chatbot. */
    public final static String DEFAULT_NAME = "Todoify";


    private static Chatbot sharedInstance = null;
    /**
     * Returns the global singleton for the chatbot with the default name.
     * To start talking with it, call {@link Chatbot#openConversation()}.
     */
    public static Chatbot getSharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new Chatbot(Chatbot.DEFAULT_NAME);
        }
        return sharedInstance;
    }


    private final String name;
    private final ArrayList<ChatMessage> convoList;
    private final TaskManager taskManager;
    private boolean closed;



    /**
     * Creates a new chatbot with the given custom name.
     * To start talking with it, call {@link Chatbot#openConversation()}.
     *
     * @param name The name of the chatbot.
     */
    public Chatbot(String name) {
        this.name = name;
        this.convoList = new ArrayList<>();
        this.taskManager = new TaskManager();
        this.closed = true;
    }

    /**
     * Returns an iterable representing the current full conversation.
     * @return An iterable of messages for the current conversation in chronological order.
     */
    public Iterable<ChatMessage> getConversation() {
        return convoList;
    }

    /**
     * Returns the name of the chatbot.
     * @return The name of the chatbot as a String.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Starts the chatbot conversation. Messages may be sent to the chatbot after this.
     */
    public void openConversation() {
        if (!this.closed) {
            return;
        }
        this.closed = false;

        this.sendMessage(
                ChatMessage.SenderType.CHATBOT,
                String.format("Hello! I'm %s, your friendly task helper.", this.name)
        );

        try {
            this.taskManager.loadFromStorage();

            // If successful, prompt about current state.
            if (this.taskManager.getTaskCount() > 0) {
                this.sendMessage(
                        ChatMessage.SenderType.CHATBOT,
                        String.format("You've %d tasks in your list right now. :)", this.taskManager.getTaskCount())
                );
            } else {
                this.sendMessage(
                        ChatMessage.SenderType.CHATBOT,
                        "You have no tasks right now. :)"
                );
            }

        } catch (FileNotFoundException e) {
            // Do nothing.
            this.sendMessage(
                    ChatMessage.SenderType.CHATBOT,
                    "You have no tasks right now. :)"
            );

        } catch (IOException e) {
            // Warn about the error.
            this.sendMessage(
                    ChatMessage.SenderType.CHATBOT,
                    String.format(
                            "Sorry, I couldn't load your tasks.\nThe error was: [%s] %s",
                            e.getClass().getSimpleName(),
                            e.getLocalizedMessage()
                    )
            );
        }

        this.sendMessage(ChatMessage.SenderType.CHATBOT, "What can I do for you?");
    }

    /**
     * Ends the chatbot conversation. No new messages may be sent after this.
     */
    public void closeConversation() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.sendMessage(ChatMessage.SenderType.CHATBOT, "Bye! Hope to see you again soon! ^-^");
    }

    /**
     * Returns whether a conversation with the chatbot is open, which means messages can be sent.
     * @return true if the conversation is open, false otherwise.
     */
    public boolean isConversationOpen() {
        return !this.closed;
    }

    /**
     * Returns whether the conversation has clased, which means messages can no longer be sent.
     * @return true if the conversation is closed, false otherwise.
     */
    public boolean isConversationClosed() {
        return this.closed;
    }

    /**
     * Method to send a message to the chatbot from the user.
     * @param message The message string to send.
     * @return The resulting message sent.
     * @throws ChatbotRuntimeException if the conversation is closed.
     */
    public ChatMessage sendMessageFromUser(String message) {
        if (this.closed) {
            throw new ChatbotRuntimeException("Conversations are not open, so no messages may be sent!");
        }

        return this.sendMessage(ChatMessage.SenderType.USER, message);
    }




    /**
     * Internal method to send a message.
     * @param message The message to send.
     * @return The resulting message sent.
     */
    private ChatMessage sendMessage(ChatMessage.SenderType sender, String message) {
        ChatMessage msg = new ChatMessage(sender, message);
        convoList.add(msg);
        this.processMessage(msg);
        return msg;
    }

    /**
     * Internal method to process newly received messages.
     * @param message The message to process.
     */
    private void processMessage(ChatMessage message) {
        // Let's notify the listeners.
        this.fireEvent(message);

        if (message.getSenderType() == ChatMessage.SenderType.CHATBOT) {
            // For now, self messages need no further processing.
            return;
        }

        // Let's see what the other users send!
        final ChatCommand command = ChatCommand.parse(message.getMessage());
        this.processCommand(command);
    }


    /**
     * Internal method to process newly received commands.
     * @param chatCommand The command to process.
     */
    private void processCommand(ChatCommand chatCommand) {
        final String FAILURE_MESSAGE_REPLY = "Sorry, idgi :(";

        try {
            boolean dataProcessed = false;
            switch (chatCommand.getOperation()) {
                case MarkComplete:
                case UnmarkComplete:
                case Delete:
                    dataProcessed = this.processCommandAssertNumericData(chatCommand);
                    break;

                case AddTodo:
                case AddDeadline:
                case AddEvent:
                    dataProcessed = this.processCommandAssertHasData(chatCommand);
                    break;

                case List:
                case Exit:
                    dataProcessed = this.processCommandAssertNoData(chatCommand);
                    break;

                case Unknown:
                default:
                    break;
            }
            if (!dataProcessed) {
                throw new ChatbotException(FAILURE_MESSAGE_REPLY);
            }
        } catch (ChatbotException e) {
            this.sendMessage(ChatMessage.SenderType.CHATBOT, "Oops! " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
            this.sendMessage(
                    ChatMessage.SenderType.CHATBOT,
                    String.format("Oh no, something's wrong! [%s] %s", e.getClass().getSimpleName(), e.getLocalizedMessage())
            );
        }

        try {
            this.taskManager.saveToStorage();
        } catch (IOException e) {
            this.sendMessage(
                    ChatMessage.SenderType.CHATBOT,
                    "Oops! I'm having problems saving your data to storage. Your data may not be preserved." +
                            String.format("The error was: [%s] %s", e.getClass().getSimpleName(), e.getLocalizedMessage())
            );
        }
    }

    /**
     * Internal method to process commands with numeric data as input.
     * @param chatCommand The command to process.
     * @throws ChatbotException if the data field does not have numeric data as input or any command-specific error.
     * @return `true` if the command was processed, `false` otherwise.
     */
    public boolean processCommandAssertNumericData(ChatCommand chatCommand) throws ChatbotException {
        switch (chatCommand.getOperation()) {
            case MarkComplete:
            case UnmarkComplete:
            case Delete:
                break;
            default:
                return false;
        }

        int index;
        Task task;

        // Process the input
        try {
            index = Integer.parseInt(chatCommand.getData()) - 1;
        } catch (NumberFormatException | NullPointerException e) {
            throw new ChatbotException(String.format(
                    "The command '%s' must be followed by a number representing the task number!",
                    chatCommand.getName()
            ));
        }

        try {
            task = this.taskManager.getTask(index);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatbotException(String.format(
                    "There is no task in the list numbered %d!",
                    index + 1
            ));
        }

        // Let's see what we should do!
        if (chatCommand.getOperation() == ChatCommand.Operation.Delete) {

            // Delete the task accordingly. We already checked the index so it should be correct.
            this.taskManager.removeTask(index);

            // Send an appropriate reply.
            this.sendMessage(
                    ChatMessage.SenderType.CHATBOT,
                    String.format(
                            "Alright, I've deleted this task:\n   %s\nYou're left with %d tasks now! :)",
                            task,
                            this.taskManager.getTaskCount()
                    )
            );

        } else {

            // Mark the task as done or not accordingly
            boolean completed = chatCommand.getOperation() == ChatCommand.Operation.MarkComplete;
            if (task.isCompleted() == completed) {
                throw new ChatbotException(
                        completed ? "The task was already done!" : "The task was already not done!"
                );
            }
            task.markCompleted(completed);

            // Send an appropriate reply
            if (completed) {
                this.sendMessage(
                        ChatMessage.SenderType.CHATBOT,
                        String.format("Nice! I've marked this task as done:\n   %s", task.toString())
                );
            } else {
                this.sendMessage(
                        ChatMessage.SenderType.CHATBOT,
                        String.format("OK, I've marked this task as not done yet:\n   %s", task.toString())
                );
            }

        }

        return true;
    }

    /**
     * Internal method to process commands with no data nor parameters as input.
     * @param chatCommand The command to process.
     * @throws ChatbotException if the data field in fact has data as input or any command-specific error.
     * @return `true` if the command was processed, `false` otherwise.
     */
    public boolean processCommandAssertNoData(ChatCommand chatCommand) throws ChatbotException {
        switch (chatCommand.getOperation()) {
            case List:
            case Exit:
                break;
            default:
                return false;
        }

        if (!chatCommand.getData().isBlank() || chatCommand.hasParams()) {
            throw new ChatbotException(String.format(
                    "Hmm, the command '%s' should not have anything following it. Is that a typo?",
                    chatCommand.getName()
            ));
        }

        switch (chatCommand.getOperation()) {
            case List:
                StringBuilder builder = new StringBuilder();

                if (this.taskManager.getTaskCount() > 0) {
                    builder.append("Here are your tasks, glhf! :)");
                } else {
                    builder.append("Oh nice! You have no tasks! :>");
                }

                int count = 1;
                for (Task task : this.taskManager.getTasks()) {
                    builder.append("\n");
                    builder.append(count);
                    builder.append(". ");
                    builder.append(task.toString());
                    count++;
                }

                this.sendMessage(ChatMessage.SenderType.CHATBOT, builder.toString());
                break;

            case Exit:
                this.closeConversation();
                break;
        }

        return true;
    }


    /**
     * Internal method to process commands that contain some data as input.
     * @param chatCommand The command to process.
     * @throws ChatbotException if the data field does not have data as input or any command-specific error.
     * @return `true` if the command was processed, `false` otherwise.
     */
    public boolean processCommandAssertHasData(ChatCommand chatCommand) throws ChatbotException {
        switch (chatCommand.getOperation()) {
            case AddTodo:
            case AddDeadline:
            case AddEvent:
                break;
            default:
                return false;
        }

        if (chatCommand.getData().isBlank()) {
            throw new ChatbotException(String.format(
                    "The command '%s' requires some title content after the command name, but none was found!",
                    chatCommand.getName()
            ));
        }

        // Create the appropriate task
        Task task = null;
        switch (chatCommand.getOperation()) {
            case AddTodo:
                task = new Todo(chatCommand.getData());
                break;

            case AddDeadline:
                if (!chatCommand.hasParamWithUsefulValue("by")) {
                    throw new ChatbotException(
                            "The 'deadline' command requires supplying '/by <deadline>'!"
                    );
                }

                long byTimestamp;
                try {
                    byTimestamp = EpochConverter.getEpochFromISODateString(chatCommand.getParam("by"));
                } catch (DateTimeParseException e) {
                    throw new ChatbotException(
                            "The deadline supplied is invalid! It must be a correct date and follow the " +
                                    "ISO8601 date format (yyyy-MM-dd or yyyy-MM-ddThh:mm).\n" +
                                    "For example, 2023-01-31T12:34 is one such valid date."
                    );
                }

                task = new Deadline(
                        chatCommand.getData(),
                        byTimestamp
                );
                break;

            case AddEvent:
                if (!chatCommand.hasParamWithUsefulValue("from") ||
                        !chatCommand.hasParamWithUsefulValue("to")) {
                    throw new ChatbotException(
                            "The 'event' command requires supplying both '/from <date>' and '/to <date>'!"
                    );
                }

                long startTimestamp;
                long endTimestamp;
                try {
                    startTimestamp = EpochConverter.getEpochFromISODateString(chatCommand.getParam("from"));
                    endTimestamp = EpochConverter.getEpochFromISODateString(chatCommand.getParam("to"));
                } catch (DateTimeParseException e) {
                    throw new ChatbotException(
                            "The date range supplied is invalid! They must be correct dates and follow the " +
                                    "ISO8601 date format (yyyy-MM-dd or yyyy-MM-ddThh:mm:ss).\n" +
                                    "For example, 2023-01-31T12:34 is one such valid date."
                    );
                }

                task = new Event(
                        chatCommand.getData(),
                        startTimestamp,
                        endTimestamp
                );
                break;

            default:
                throw new ChatbotException("Unexpected internal error: task type was not implemented.");
        }

        // Add the task created
        this.taskManager.addTask(task);
        this.sendMessage(
                ChatMessage.SenderType.CHATBOT,
                String.format(
                        "Got it. I've added this task:\n  %s\nYou have %d tasks in your list now! :)",
                        task,
                        this.taskManager.getTaskCount()
                )
        );

        return true;
    }


}
