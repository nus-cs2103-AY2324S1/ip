# Duke
>"Duke" is a sophisticated chatbot designed to be a versatile task manager and assistant for >users. It allows users to input tasks with or without specified deadlines and start dates. Users >can simply describe their tasks and optionally set deadlines and start dates for better >organization.

Overall, Duke serves as an efficient and organized task manager that streamlines task creation, tracking, and retrieval. This chatbot's capabilities make it particularly useful for individuals looking to manage their tasks and responsibilities effectively, whether in an academic context or for personal use. In an academic context, Duke could assist the user, who is an engineering student, in managing assignments, projects, and deadlines related to their social science and arts-related academic writing modules.

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `duke.jar` from [here](https://github.com/lilozz2/ip/releases/download/A-Release/duke.jar).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar duke.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all tasks.

   * `todo buy book` : Adds a task named `add book` to the task list.

   * `delete 3` : Deletes the 3rd task shown in the current list.

   * `bye` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.


## Features

### Task Management

Duke allows users to input tasks, providing a brief description of each task. Users can specify deadlines and start dates for tasks if needed.

### Task Assignment

Task Assignment: Users can assign tasks to Duke by describing the task they want to add, along with any relevant deadlines or start dates.

### Task Completion

Duke enables users to mark tasks as "done" when they are completed. This helps users keep track of their progress.

### Search functionality

Duke includes a search feature that allows users to search for specific strings or keywords within their task descriptions. This makes it easy to find and retrieve specific tasks or information.

### `todo` - Adds a task that is to be done

Example of usage:

`todo [description]`

Expected outcome:

Outputs all the tasks along with the one that has been added

```
[T][ ] description
```

### `deadline` - Adds a task with a deadline to be completed

Example of usage:

`deadline [description] /by [2020-08-08 18:00]`

Expected outcome:

Outputs all the tasks along with the one that has been added

```
[D][ ] description (by: Aug 08 2020 06:00 PM)
```

### `event` - Adds a task with a start and end time

Example of usage:

`event [description] /from [18:00] /to [19:00]`

Expected outcome:

Outputs all the tasks along with the one that has been added

```
[E][ ] description (from: 06:00 PM to: 07:00 PM)
```

### `find` - searches the tasklist for similar strings

Example of usage:

`todo reach house`
`todo go to bed`
`todo house cleaning`
`find house`

Expected outcome:

Outputs all the tasks that have been used so far that match the search string

```
1. [T][ ] reach house
2. [T][ ] house cleaning
```

### `mark / unmark` - Marks a task as done / not done

Example of usage:

`todo reach house`
`todo go to bed`
`todo house cleaning`
`mark 1`

Expected outcome:

Marks the task as done

```
1. [T][X] reach house
2. [T][ ] go to bed
2. [T][ ] house cleaning
```

### `list` - Lists the tasks that have been input

Example of usage:

`todo reach house`
`todo go to bed`
`todo house cleaning`
`list`

Expected outcome:

Outputs all the tasks that have been used so far

```
1. [T][ ] reach house
2. [T][ ] go to bed
2. [T][ ] house cleaning
```