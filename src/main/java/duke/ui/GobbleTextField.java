package duke.ui;

import duke.DukeException;
import duke.command.Command;
import duke.Parser;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class GobbleTextField extends TextField {

    public GobbleTextField() {
        super();
        super.setPrefSize(325, 60);
        super.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-font-size: 15px;");
        super.setPromptText("Enter command here");
    }

//    public void listenToUserInput(GobbleChatContainer chat) {
//        String input = super.getText();
//        try {
//            Command command = Parser.parse(input);
//
//            command.execute();
//        } catch (DukeException e) {
//            System.out.println(e.getMessage());
//        }
//        super.clear();
//    }
}
