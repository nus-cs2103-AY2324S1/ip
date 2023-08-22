import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;
import java.util.*;

public class Task {
    protected String name;
    protected boolean done;

    protected Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void SetDoneOrNot(boolean doneOrNot) {
        this.done = doneOrNot;
    }
}

