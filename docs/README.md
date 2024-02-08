# ValerieBot User Guide

Welcome to ValerieBot! ValerieBot is a command-line task management application designed
to help you keep track of your tasks.
This user guide will walk you through the various features and commands you can use with ValerieBot.

## Getting Started

To get started with ValerieBot, follow these steps:

1. Make sure you have the Java Development Kit (JDK) installed on your computer. You can download it from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html).

2. Download or clone the ValerieBot project to your local machine.

3. Open your command line or terminal and navigate to the project directory containing the `Launcher.java` file.

4. Compile the `Launcher.java` file and run the ValerieBot application by running the following command:

   ```
   javac Launcher.java
   java Launcher
   ```


The ValerieBot chatbot application will start, and you will see a command-line interface where you can interact with the chatbot.

Now, you're ready to use ValerieBot! ♡

## Features

ValerieBot provides a wide range of features to manage your tasks effectively :

### Listing Tasks

To view your list of tasks, use the `list` command:

```
list
```
ValerieBot will display all your tasks, including their descriptions and completion status.

### Adding a Task
You can add tasks to your list using the following commands:
- `todo` `<description>` : Add a ToDo task.
```
todo Buy groceries
```

- `deadline` `<description>` /by `<date>` : Add a Deadline task with a due date.
```
deadline Finish report /by 2023-09-30
```

- `event` `<description>` /from `<start>` /to `<end>` : Add an Event task with start and end times.
```
event Team meeting /from 14:00 /to 15:30
```

### Deleting Tasks
If you want to remove a task from your list, use the delete command.
Replace <task-number> with the number of the task you want to delete:

```
delete 3
```

### Marking Tasks as Done
You can mark tasks as done using the mark command. For example, to mark the first task as done, use:

```
mark 1
```

### Unmarking Tasks as Done
If you want to unmark a previously marked task, use the unmark command. For example, to unmark the second task, use:

```
unmark 2
```

### Finding Tasks
To search for tasks containing a specific keyword in their descriptions, use the find command. Replace <keyword> with your search term:

```
find groceries
```

### Getting Help
If you need assistance or want to see a list of available commands, use the help command:

```
help
```

### Exiting the Application
To exit ValerieBot, simply type `bye`, and the application will save your tasks and close.
