# Doraemon

A chatbot that lets you relieve your childhood.

## Features

### GUI

In this chatbot, you assume the character Nobita and ask Doraemon for help organising your task.

## Commands

Input any of the following commands and you will get a reply from Doraemon.

<br>
The following commands are supported by Doraemon.

### `list OR ls`

Lists all tasks as a list.

### `mark/m [index] OR unmark/um [index]`

Marks a command as complete/incomplete respectively.

Example of usage:
`mark 3``m 3` or `unmark 5` `um 5`<br>
Marks the 3rd task as complete, and marks the 5th task as incomplete.

### `todo [description] OR t [description]`

Adds a todo with a description to the task list.

Example of usage:<br>
`todo Buy supper` `t Buy supper `<br>
Adds a todo with the description `Buy supper`.

### `deadline [description] /by [end] OR d [description] /by [end] `

Adds a deadline with a description and deadline.<br>
`[end]` argument takes the format `yyyy-mm-ddTtt:tt`.

Example of usage:<br>
`deadline return book /by 2023-09-21T17:00` `d return book /by 2023-09-21T17:00` <br>
Adds a deadline with the description `return book` with the deadline `Sep 21 2023 17:00`.

### `event [description] /from [start] /to [end] OR e [description] /from [start] /to [end]`

Adds an event with a description, start and end dateTime.<br>
`[start]` & `[end]` arguments take the format `yyyy-mm-ddTtt:tt`.

Example of usage:<br>
`event football match /from 2023-09-21T17:00 /to 2023-09-21T19:00`
`e football match /from 2023-09-21T17:00 /to 2023-09-21T19:00`<br>
Adds an event with the description `football match` starting at `Sep 21 2023 17:00` to `Sep 21 2023 19:00`.

### `delete [index] / del [index]`

Deletes a task from the list.

Example of usage:<br>
`delete 5` `del 5`<br>
Deletes the 5th task in the list.

### `find [string] / f [string]`

Displays all tasks with a description that contains `[string]`

Example of usage:<br>
`find supper` `f supper`<br>
If list of tasks contain above 3 tasks, only the todo task will be displayed as part of search result

### `goodbye`

Exit the Doraemon help session.
