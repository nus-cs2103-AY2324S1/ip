package todoify.chatbot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonSyntaxException;

import todoify.chatbot.exception.ChatbotRuntimeException;
import todoify.taskmanager.TaskManager;
import todoify.util.events.EventEmitter;


/**
 * A chatbot to interact with.
 *
 * <p>
 * This class is an abstraction over a chatbot that also supplies methods to allow users to easily interact with the
 * bot, and for UI clients to subscribe to chat messages.
 * </p>
 */
public class Chatbot extends EventEmitter<ChatMessage> {

    /**
     * The default name of the chatbot.
     */
    public static final String DEFAULT_NAME = "Todoify";


    private final String name;
    private final ArrayList<ChatMessage> convoList;
    private final TaskManager taskManager;
    private final ChatCommandExecutor commandExecutor;
    private boolean isClosed;


    /**
     * Creates a new chatbot with the given custom name. To start talking with it, call
     * {@link Chatbot#openConversation()}.
     *
     * @param name        The name of the chatbot. Uses the default if null.
     * @param taskManager The task manager the chatbot should utilise. Uses the default if null.
     */
    public Chatbot(String name, TaskManager taskManager) {
        this.name = name == null ? DEFAULT_NAME : name;
        this.convoList = new ArrayList<>();
        this.taskManager = taskManager == null ? new TaskManager() : taskManager;
        this.commandExecutor = new ChatCommandExecutor(this);
        this.isClosed = true;
    }

    /**
     * Returns an iterable representing the current full conversation.
     *
     * @return An iterable of messages for the current conversation in chronological order.
     */
    public Iterable<ChatMessage> getConversation() {
        return this.convoList;
    }

    /**
     * Returns the name of the chatbot.
     *
     * @return The name of the chatbot as a String.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the task manager instance used in this chatbot.
     *
     * @return The task manager used in this chatbot.
     */
    public TaskManager getTaskManager() {
        return this.taskManager;
    }

    /**
     * Starts the chatbot conversation. Messages may be sent to the chatbot after this.
     */
    public void openConversation() {
        if (!this.isClosed) {
            return;
        }
        this.isClosed = false;

        this.sendMessageToUser(
                String.format("Hello! I'm %s, your friendly task helper.", this.name)
        );

        try {
            this.taskManager.loadFromStorage();

            // If successful, prompt about current state.
            if (this.taskManager.getTaskCount() > 0) {
                this.sendMessageToUser(
                        String.format("You've %d tasks in your list right now! :)", this.taskManager.getTaskCount())
                );
            } else {
                this.sendMessageToUser("You have no tasks right now! :)");
            }

        } catch (FileNotFoundException e) {
            // Do nothing.
            this.sendMessageToUser("You have no tasks right now! :)");

        } catch (IOException | JsonSyntaxException e) {
            // Warn about the error.
            this.sendMessageToUser(String.format(
                    "Sorry, I couldn't load your tasks.\nThe error was: [%s] %s\n\n"
                            + "I'll be starting from a blank slate instead - run 'load' if you fixed it and want to "
                            + "retry loading.",
                    e.getClass().getSimpleName(),
                    e.getLocalizedMessage()
            ));
        }

        this.sendMessageToUser("What can I do for you?");
    }

    /**
     * Ends the chatbot conversation. No new messages may be sent after this.
     */
    public void closeConversation() {
        if (this.isClosed) {
            return;
        }
        this.isClosed = true;
        this.sendMessageToUser("Bye! Hope to see you again soon! ^-^");
    }

    /**
     * Returns whether a conversation with the chatbot is open, which means messages can be sent.
     *
     * @return true if the conversation is open, false otherwise.
     */
    public boolean isConversationOpen() {
        return !this.isClosed;
    }

    /**
     * Returns whether the conversation has clased, which means messages can no longer be sent.
     *
     * @return true if the conversation is closed, false otherwise.
     */
    public boolean isConversationClosed() {
        return this.isClosed;
    }




    /**
     * Sends a message to the chatbot from the user.
     *
     * @param message The message string to send.
     * @return The resulting message sent.
     * @throws ChatbotRuntimeException if the conversation is closed.
     */
    public ChatMessage sendMessageFromUser(String message) {
        if (this.isClosed) {
            throw new ChatbotRuntimeException("Conversations are not open, so no messages may be sent!");
        }

        return this.sendMessage(ChatMessage.SenderType.USER, message);
    }

    /**
     * Sends a message to the user from the chatbot.
     *
     * <p>
     * Note that sending as the chatbot may only be invoked from within the chatbot package, and is allowed to bypass
     * conversation closures.
     * </p>
     *
     * @param message The message string to send.
     * @return The resulting message sent.
     */
    ChatMessage sendMessageToUser(String message) {
        return this.sendMessage(ChatMessage.SenderType.CHATBOT, message);
    }

    /**
     * Sends a message with the given sender.
     *
     * @param message The message to send.
     * @return The resulting message sent.
     */
    private ChatMessage sendMessage(ChatMessage.SenderType sender, String message) {
        ChatMessage msg = new ChatMessage(sender, message);
        this.convoList.add(msg);
        this.processMessage(msg);
        return msg;
    }




    /**
     * Processes the message. Used for processing new messages as they are delivered.
     *
     * @param message The message to process.
     */
    private void processMessage(ChatMessage message) {
        // We need to notify the listeners for every message.
        this.fireEvent(message);

        // Self messages need no further processing.
        if (message.getSenderType() == ChatMessage.SenderType.CHATBOT) {
            return;
        }

        // User's messages are commands. Process them appropriately.
        this.commandExecutor.execute(
                ChatCommand.parse(message.getMessage())
        );
    }


}
