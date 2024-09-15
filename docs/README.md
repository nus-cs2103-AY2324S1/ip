# User Guide

# The Emiya Chatbot: Winning the War, One Task At a Time!

Emiya is here to win the Holy Grail War! Emiya is a chatbot that one can interact with through the **Command-Line Interface (CLI)**.
Emiya also contains a **Graphical-User Interface(GUI)** to make your user experience a more eye-pleasing and interesting one!

![EmiyaTaskManager Screenshot](/Ui.png)

## Quick-start Guide

1. Ensure that you have Java 11 installed on your computer.
2. Download the latest `emiya.jar` from this page.
3. Drag and drop the file from your computer's downloads into any folder you want.
4. Open up the Command-Line Interface for your device's operating system.
5. Use the `cd` command to change directories to where `emiya.jar` is stored.
6. Use the command `java -jar emiya.jar` to get the application running.

Congratulations! You have just launched Emiya. Trace On!

## Command Summary

For all commands, all parts in upper-case are variables you can change! Try it out for yourself!

| Commands                                               | Description                                                                               |
|--------------------------------------------------------|-------------------------------------------------------------------------------------------|
| `help`                                                 | Shows all available commands, as well as their usage.                                     |
| `todo TASK_DESCRIPTION`                                | Creates a Todo task with a given description.                                             |
| `deadline TASK_DESCRIPTION /by DEADLINE`               | Creates a Deadline task with a given description and deadline.                            |
| `event TASK_DESCRIPTION /from START_DATE /to END_DATE` | Creates a Event task with a given description, start date and end date.                   |
| `list`                                                 | Lists all tasks currently within the list of tasks.                                       |
| `mark INDEX`                                           | Marks a task at a specified index as completed.                                           |
| `unmark INDEX`                                         | Remove the completed status from a task.                                                  |
| `delete INDEX`                                         | Deletes the task at the specified index in the list of tasks.                             |
| `find WORD`                                            | Searches and displays all tasks within the list of tasks that contain the specified word. |
| `bye`                                                  | Quits the application.                                                                    |

## Main Features

* **Creation of Tasks**: Emiya is able to create Tasks, which are separated into 3 different types: `todo`, `deadline`, and `event`.
    * `todo`: Todo tasks help you to keep track of tasks that do not have strict deadlines.
    * `deadline`: Deadline tasks help you to keep track of tasks that have certain specific deadlines.
    * `event`: Event tasks help you to keep track of events that happen within a specific duration.
* **Marking of Tasks**: Emiya is able to keep track of the status of the tasks. Tasks can be marked as either completed or incomplete, which can be displayed when the tasks are listed out with the `list` command.
* **Deleting of Tasks**: Emiya can delete tasks that have been added into the list.
* **Displaying all Tasks**: Emiya can list out all tasks currently within the task list, allowing users to see all tasks they currently have with a simple `list` command.
* **Displaying all available Commands**: Emiya can display all commands that users can use within the application, along with a description of what each method does and how it can be used.

## Usage

### `help`

Displays all available commands to the user.

Example of usage: 

`help`

Expected outcome:

A list of all available commands for the user.

```
Here's a list of all possible commands!
/* 
    rest of the description of all commands
*/
```

### `todo`

Creates a Todo task with a given description.

Example of usage: 

`todo TASK_DESCRIPTION`

Expected outcome:

Informs the user of the Todo task being successfully created.

```
Sure! I have added this task to the list:
[T][] TASK_DESCRIPTION
Now you have NUMBER_OF_TASKS tasks in your list!
```

### `deadline`

Creates a Deadline task with a given description and deadline.

Example of usage: 

`deadline TASK_DESCRIPTION /by DEADLINE`

Expected outcome:

Informs the user of the Deadline task being successfully created.

```
Sure! I have added this task to the list:
[D][] TASK_DESCRIPTION (by: DEADLINE)
Now you have NUMBER_OF_TASKS tasks in your list!
```

### `event`

Creates an Event task with a given description, start date and end date.

Example of usage: 

`event TASK_DESCRIPTION /from START_DATE /to END_DATE`

Expected outcome:

Informs the user of the Event task being successfully created.

```
Sure! I have added this task to the list:
[E][] TASK_DESCRIPTION (from: START_DATE to: END_DATE)
Now you have NUMBER_OF_TASKS tasks in your list!
```

### `list`

Lists out all tasks currently in the list.

Example of usage: 

`list`

Expected outcome:

Lists out all tasks currently in the list.

```
Lots of things to do! Get to it!
/*
    list of all tasks
*/
```

### `mark`

Mark a task at a specified index as completed.

Example of usage: 

`mark TASK_INDEX`

Expected outcome:

Informs the user that the task has been marked as completed.

```
Nice job! I have marked this task as done:
/*
    insert details of task
*/
Now you have NUMBER_OF_TASKS tasks in your list!
```

### `unmark`

Unmark a task at a specified index to indicate that the task is incompleted.

Example of usage: 

`unmark TASK_INDEX`

Expected outcome:

Informs the user that the task has been unmarked.

```
Oof, alright I have set this task as unmarked:
/*
    insert details of task
*/
Now you have NUMBER_OF_TASKS tasks in your list!
```

### `delete`

Delete a task at a specified index.

Example of usage: 

`delete TASK_INDEX`

Expected outcome:

Informs the user that the task has been deleted.

```
Sure, I shall now delete the following task:
/*
    insert details of task
*/
Now you have NUMBER_OF_TASKS tasks in your list!
```

### `find`

Finds all tasks that contain a specified keyword.

Example of usage: 

`find WORD`

Expected outcome:

Lists all tasks that contain the specified keyword with the task description.

```
Sure, here are all the tasks that have the word in them:
/*
    insert details of tasks containing the word
*/
```

### `bye`

Quits the application.

Example of usage: 

`bye`

Expected outcome:

Prints following message, then quits the application.

```
Goodbye!
```