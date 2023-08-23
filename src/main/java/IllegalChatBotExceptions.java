public class IllegalChatBotExceptions extends Exception{
    private String horizontal = "____________________________________________________________";
    IllegalChatBotExceptions(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return horizontal + "\n☹ OOPS!!! The description of a " + this.getMessage() + " cannot be empty.\n" + horizontal;
    }
}
