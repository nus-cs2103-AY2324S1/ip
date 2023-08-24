import java.util.Objects;

public abstract class FlowController {

    //makes class EFFECTIVELY FINAL
    private FlowController() {}

    public static void main(String[] args) {
        IOFormatter.start();
        while(!Objects.equals(IOFormatter.listen(), "bye")) {
            IOFormatter.send(IOFormatter.retrieve());
        }
        IOFormatter.end();
    }

}
