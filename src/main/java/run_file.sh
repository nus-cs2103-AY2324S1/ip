#!/bin/sh
#

javac --module-path $JAVAFX_PATH --add-modules javafx.controls,javafx.fxml cheese/Cheesebot.java cheese/parser/Parser.java cheese/storage/Storage.java cheese/task/Task.java cheese/tasklist/TaskList.java cheese/ui/Ui.java cheese/Launcher.java cheese/dialogbox/DialogBox.java


java --module-path $JAVAFX_PATH --add-modules javafx.controls,javafx.fxml cheese.Launcher





