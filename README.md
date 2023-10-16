# Ax project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, use `./gradlew run` to run Ax. If the setup is correct, you should see something like the below as the output:
![img.png](docs/Ui.png)

## Commands
1. ### todo
   To create a todo, use `todo [todo name]`. This will reply `SUCCESS` if it was successfully added. Or else, Ax will reply with the error message.
2. ### deadline
   To create a deadline, use `deadline [deadline name] /by [Date in ISO8601 format]`. This will reply `SUCCESS` if it was successfully added. Or else, Ax will reply with the error message.
3. ### event
   To create a event, use `event [event name] /from [Date in ISO8601 format] /to [Date in ISO8601 format]`. This will reply `SUCCESS` if it was successfully added. Or else, Ax will reply with the error message.
4. ### list
   To list out the current items, use `list`.
5. ### mark
   To mark an item on the list, use `list` to view the order. Then use `mark [index]` to get Ax to mark a item as done. 
6. ### unmark
   To unmark an item on the list, use `list` to view the order. Then use `unmark [index]` to get Ax to unmark a item as done.

7. ### reminders
   To view reminders, use `reminders`. This brings up a list of **deadlines** which are past their **due date** and **events** which are past their **start date**.
8. ### bye
   `Bye` gets Ax to write to a save file, saving your todos, deadlines and events so that they remain even when you restart Ax.
