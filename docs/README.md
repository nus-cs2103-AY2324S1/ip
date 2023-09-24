# Botty User Guide
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest `botty.jar` from [here](https://github.com/CelestineTan03/ip/releases)
3. Copy the file to the folder you want to use as the home folder for your chatbot.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the
`java -jar botty.jar` command to run the chatbot. 

A GUI similar to the below should appear in a few seconds. 
Note how the app contains some sample data.

![Ui](Ui.png)

## Features 

### Task List

You can add, edit, view or delete tasks in your task list.

### Search

You can search for existing task in your list.

### Mark as done
You can mark or unmark a task as done.

## Usage

### `list` - Display all tasks

Botty will get all the tasks in your list and display them.

Format: `list`

### `todo` - Add a todo task
Botty will add a new todo task to the list of tasks.

Format: `todo DESCRIPTION`

Example of usage: `todo sleep`

### `deadline` - Add a deadline task
Botty will add a new deadline task to the list of tasks.

Format: `deadline DESCRIPTION /by DATE`

Example of usage: `deadline homework /by 2023-09-17 14:00`

### `event` - Add an event task
Botty will add a new event task to the list of tasks.

Format: `event DESCRIPTION /from DATE /to DATE`

Example of usage: `event meeting /from 2023-09-17 14:00 /to 2023-09-17 15:00`

### `mark` - Mark a task as done
Botty will mark the task at the specified index as done.

Format: `mark INDEX`
- The index is a positive integer not larger than the number of tasks.

Example of usage: `mark 1`

### `unmark` - Mark a task as not done
Botty will mark the task at the specified index as not done.

Format: `unmark INDEX`
- The index is a positive integer not larger than the number of tasks.

Example of usage: `unmark 1`

### `delete` - Delete a task
Botty will delete the task at the specified index.

Format: `delete INDEX`
- The index is a positive integer not larger than the number of tasks.

Example of usage: `delete 1`

### `find` - Find tasks
Botty will find all tasks that contain the specified keyword.

Format: `find KEYWORD`
- The search is case sensitive.
- Only the description and the type of the task is searched.
- Any task that contains the keyword will be returned.

Example of usage: `find homework`

### `update` - Update a task
Botty will update the task at the specified index.

Format: `update INDEX DESCRIPTION`
- The index is a positive integer not larget than the number of tasks.

Example of usage: `update 2 homework /by 2023-09-17 15:00`

### `bye` - Exit the program
Botty will exit the program.

Format: `bye`


