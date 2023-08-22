import java.util.ArrayList;

public class Storage {
    private ArrayList<Input> previous_Commands= new ArrayList<>();

    public void add(Input input) {
        this.previous_Commands.add(input);
        System.out.println(
                String.format("Added: %s", input.get())
        );
    }

    public void listItems() {
        for (int i = 0; i < this.previous_Commands.size(); ++i) {
            System.out.println(
                    String.format("%d. %s",
                            i + 1, this.previous_Commands.get(i).get()
                    )
            );
        }
    }
}
