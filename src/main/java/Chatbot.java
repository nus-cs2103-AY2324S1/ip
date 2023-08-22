import java.util.ArrayList;

public class Chatbot extends EventEmitter<Chatbot.Message> {

    /**
     * The message sender for a conversation message
     */
    public enum MessageSender {
        CHATBOT,
        USER
    }

    /**
     * The read only instance for a single message.
     */
    public class Message {
        private long timestamp;
        private MessageSender sender;
        private String message;

        private Message(MessageSender sender, String message) {
            this.timestamp = System.currentTimeMillis();
            this.sender = sender;
            this.message = message;
        }

        public long getTimestamp() {
            return this.timestamp;
        }

        public MessageSender getSender() {
            return this.sender;
        }

        public String getMessage() {
            return this.message;
        }
    }

    /** The default name of the chatbot. */
    public static String DEFAULT_NAME = "Todoify";

    /**
     * Returns a global singleton for the chatbot with the default name.
     * To start talking with it, call {@link Chatbot#openConversation()}.
     */
    public static Chatbot getInstance() {
        return new Chatbot(Chatbot.DEFAULT_NAME);
    }



    private String name;
    private ArrayList<Message> convoList = new ArrayList<>();
    private TaskManager taskManager = new TaskManager();
    private boolean closed = true;



    /**
     * Creates a new chatbot with the given custom name.
     * To start talking with it, call {@link Chatbot#openConversation()}.
     *
     * @param name The name of the chatbot.
     */
    public Chatbot(String name) {
        this.name = name;
    }

    /**
     * Obtains an iterable representing the current full conversation.
     * @return An iterable of messages for the current conversation in chronological order.
     */
    public Iterable<Message> getConversation() {
        return convoList;
    }

    /**
     * Starts the chatbot conversation. Messages may be sent to the chatbot after this.
     */
    public void openConversation() {
        if (!this.closed) {
            return;
        }
        this.closed = false;
        this.sendMessage(MessageSender.CHATBOT, String.format("Hello! I'm %s!\nWhat can I do for you?", this.name));
    }

    /**
     * Ends the chatbot conversation. No new messages may be sent after this.
     */
    public void closeConversation() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.sendMessage(MessageSender.CHATBOT, "Bye. Hope to see you again soon!");
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
     */
    public Message sendMessageFromUser(String message) {
        if (this.closed) {
            throw new RuntimeException("The conversation is not started or has ended, so no messages may be sent!");
        }

        return this.sendMessage(MessageSender.USER, message);
    }




    /**
     * Internal method to send a message.
     * @param message The message to send.
     * @return The resulting message sent.
     */
    private Message sendMessage(MessageSender sender, String message) {
        Message msg = new Message(sender, message);
        convoList.add(msg);
        this.processMessage(msg);
        return msg;
    }

    /**
     * Internal method to process newly received messages.
     * @param message The message to process.
     */
    private void processMessage(Message message) {
        // Let's notify the listeners.
        this.fireEvent(message);

        // Process whatever we need to do!
        switch (message.getSender()) {
            case USER:
                String[] parts = message.getMessage().split(" ", 2);
                String command = parts[0];
                String data = parts.length > 1 ? parts[1] : null;

                switch (command) {
                    case "mark":
                    case "unmark":
                        if (data != null) {
                            int index = Integer.parseInt(data) - 1;
                            boolean completed = command.equals("mark");

                            TaskManager.Task task = this.taskManager.getTask(index);
                            task.markCompleted(completed);

                            if (completed) {
                                this.sendMessage(
                                        MessageSender.CHATBOT,
                                        String.format("Nice! I've marked this task as done:\n   %s", task.toString())
                                );
                            } else {
                                this.sendMessage(
                                        MessageSender.CHATBOT,
                                        String.format("OK, I've marked this task as not done yet:\n   %s", task.toString())
                                );
                            }
                        }
                        break;

                    case "list":
                        if (data == null || data.isEmpty()) {
                            StringBuilder builder = new StringBuilder();
                            int count = 1;
                            for (TaskManager.Task task : this.taskManager.getTasks()) {
                                if (count > 1) {
                                    builder.append("\n");
                                }

                                builder.append(count);
                                builder.append(". ");
                                builder.append(task.toString());
                                count++;
                            }
                            this.sendMessage(MessageSender.CHATBOT, builder.toString());
                            break;
                        }
                        // Otherwise, fallthrough.

                    case "bye":
                        if (data == null || data.isEmpty()) {
                            this.closeConversation();
                            break;
                        }
                        // Otherwise, fallthrough.

                    default:
                        String text = message.getMessage();
                        this.taskManager.addTask(new TaskManager.Todo(text));
                        this.sendMessage(MessageSender.CHATBOT, "added: " + text);
                        break;
                }

            default:
                break;
        }
    }


}
