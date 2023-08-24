public class InputHandler {
    public InputHandler() {}
    public void HandleInput(String input) {
        switch(input) {
            case "bye" :
                Message.GenerateExit().Print();
                Duke.Exit();
                break;
            default :
                new Message(input).Print();
                break;
        }
    }
}
