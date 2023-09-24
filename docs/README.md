# User Guide for Bob

Bob the chatbot was created with user interaction in mind and offers a wide range of 
features and task management capabilities.

## Quick Start
1. Ensure that you have Java 11 installed on your machine
2. Download bob.jar [here](https://github.com/leontan2/ip/releases)
3. Move the installation files to your desired directory
4. Open your terminal and change into directory with the file using `cd`.
5. Run using the command in your terminal: java -jar bob.jar.
6. Refer to Usage below for details of each command.

## Features

### Add Task

Adds a task to the list. There are 3 types of tasks. Todos, Events, Deadlines
* Todos which contain a description
* Events which contain a description with a start and end time.
* Deadlines which contains a description with a due date.

### Mark Task

Marks a task as done.

### Unmark Task

Unmarks a done task.

### Delete Task

Delete the task from the list.

### Find Task

Find tasks in list using keywords.

### List Tasks

List down the entire task list.

### Undo Tasks

Undo the most recently used command.

### Bye

Exits the programme.

## Usage

### `todo`

Adds a todo task to list.

Format:

`todo [TASK_DESCRIPTION]`

Example of usage:

`todo Tidy Room`

Expected Outcome:

Adds a todo task to the list. The Ui would display a successful message with 
the task description and the total number of tasks currently in the list.

```
Got it. I've added this task:
[T][ ] Tidy Room
Now you have 6 tasks in the list.
```

### `deadline`

Adds a deadline task with a specified time to complete it by.

Format:

`deadline [TASK_DESCRIPTION] /by [YYYY-MM-DD HHmm]`

Example of usage:

`deadline ip /by 2023-11-11 2359`

Expected Outcome:

Adds a deadline task to the list. The Ui would display a successful message with
the task description, along its deadline and the total number of tasks currently in the list.

```
Got it. I've added this task:
[D][ ] ip (by: Nov 11 2023, 11:59pm)
Now you have 7 tasks in the list.
```

### `event`

Adds a event task with a start and end time to the list.

Format:

`event [TASK_DESCRIPTION] /from [YYYY-MM-DD HHmm] /to [YYYY-MM-DD HHmm]`

Example of usage:

`event submission /from 2023-08-11 2359 /to 2023-11-09 2359`

Expected Outcome:

Adds an event task to the list. The Ui would display a successful message with
the task description, along its start, end time and the total number of tasks currently in the list.

```
Got it. I've added this task:
[E][ ] submission (from: Aug 11 2023, 11:59pm to: Nov 09 2023, 11:59pm)
Now you have 8 tasks in the list.
```

### `mark`

Marks task as done.

Format:

`mark [INDEX]`

Example of usage:

`mark 4`

Expected Outcome:

Marks the specific task. The Ui would display a successful message with
the task description and the marked status.

```
Nice! I've marked this task as done:
[T][X] read book
```

### `unmark`

Unmarks task as not done.

Format:

`unmark [INDEX]`

Example of usage:

`unmark 5`

Expected Outcome:

Unmarks the specific task. The Ui would display a successful message with
the task description and the unmarked status.

```
OK, I've marked this task as not done yet:
[T][ ] project meeting
```

### `delete`

Deletes the specified task from list.

Format: 

`delete [INDEX]`

Example of usage: 

`delete 3`

Expected Outcome:

Deletes the task at the specified index. The Ui would display a successful message with
the task description and the total number of tasks left in the list.

```
Noted. I've removed this task:
[T][X] run
Now you have 7 tasks in the list.
```

### `find`

Finds the tasks that match with the keywords.

Format: 

`find [KEYWORDS]`

Example of usage: 

`find Tidy`

Expected Outcome:

Finds the tasks with the keywords included. The Ui would display a successful message with
the task description.

```
Here are the matching tasks in your list: 
1. [T][ ] Tidy Room
```

### `list`

Lists all tasks.

Format: 
`list`

Expected Outcome:

List all the current tasks. The Ui would display a successful message with
the task description and their mark status.

```
Here are the tasks in your list:
1. [T][X] read book
2. [D][ ] ip (by: Nov 11 2023, 11:59pm)
3. [E][ ] submission (from: Aug 11 2023, 11:59pm to: Nov 09 2023, 11:59pm)
4. [T][ ] tidy room
```

### `undo`

Undo the most recent executed command.

Format:

`undo`

Expected Outcome:

Undo the most recent command. The Ui would display a successful message telling
the user which command has been undone.

```
Got it. Undo for the unmark command was successful!
```

### `bye`

Exits the programme and updates the task lists locally.

Format:

`bye`

Expected outcome:

Program will close.