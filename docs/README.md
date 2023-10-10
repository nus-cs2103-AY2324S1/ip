# Zac's Chatbot! :singapore:

> Life is great. I love to record my tasks - SunTze

Welcome to Zac's chatbot. I can help you record down your tasks, so do check me out!

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version

1. Locate the `src/main/java/duke.Launcher.java` file, and run it
2. Or, you can run `java -ea -jar build/libs/Duke-v0.2.jar` from the root directory to execute the JAR file

> [!NOTE] <br>
> Do not fret if you see the warning "Unsupported JavaFX configuration".
> It is perfectly fine!

## Features

- Record down your tasks as a Todo, Deadline, or Event :clipboard:
- Display all your tasks
- Mark a task as completed/uncompleted
- Delete tasks when they are no longer needed :x:
- Find a task by its description
- Undo the latest command if you messed up :back:
- Saves your tasks for future use :infinity:

## How do I use Zac's Chatbot?

1. Type the `help` command if you are too lazy to read on :smile:
1. Add a **Todo** task
    1. `todo borrow book`
1. Add a **Deadline** task
    1. `deadline return book /by Sun 1700"`
1. Add an **Event** task
    1. `event project meeting /from Mon 2pm /to 4pm`
1. Display all your tasks
    1. `list`
1. Mark a task as completed/uncompleted
    1. `mark 1` or `unmark 1` (marks/unmarks 1st task in your list)
1. Delete task
    1. `delete 1` (deletes 1st task in your list)
1. Find a task by description
    1. `find book`
1. Undo the latest command
    1. `undo`
1. End the chat
    1. `bye`