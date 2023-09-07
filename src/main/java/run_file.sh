#!/bin/sh
#

javac --module-path $JAVAFX_PATH --add-modules javafx.controls,javafx.fxml cheese/Cheesebot.java cheese/Parser/Parser.java cheese/Storage/Storage.java cheese/Task/Task.java cheese/TaskList/TaskList.java cheese/Ui/Ui.java cheese/Launcher.java cheese/dialogbox/DialogBox.java


java --module-path $JAVAFX_PATH --add-modules javafx.controls,javafx.fxml cheese.Launcher





