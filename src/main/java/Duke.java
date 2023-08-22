public class Duke {

    private static final Chatbot chatbot = Chatbot.getInstance();

    public static void main(String[] args) {

        // Perform some setups.
        chatbot.addEventListener(message -> {
            if (message.getSender() != Chatbot.MessageSender.USER) {
                System.out.println(message.getMessage());
            } else {
                System.out.println();
            }
            System.out.println();
        });

        // Start processing the conversation.
        chatbot.openConversation();
        chatbot.closeConversation();

    }
}
