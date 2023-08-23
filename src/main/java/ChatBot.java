public class ChatBot {
    private String message = "";
    private String name;

    private class Message {
        private String line = "____________________________________________________________\n";
        private String message = "";

        public Message(String message) {
            this.message = message;
        }

        public void send() {
            System.out.println(message + "\n" + line);
        }
    }

    public ChatBot(String name) {
        this.name = name;
    }
    public void intro() {
        Message intro = new Message("____________________________________________________________\n" + 
        "Hello! I am " + this.name + ".\n" + 
        "What can I do for you today?");
        intro.send();
    }

    public void goodbye() {
        Message goodbye = new Message("Bye. Hope to see you again!");
        goodbye.send();
    }

    public static void main(String[] args) {
        ChatBot chatbot = new ChatBot("Bobby Wasabi");
        chatbot.intro();
        chatbot.goodbye();
    }
}
