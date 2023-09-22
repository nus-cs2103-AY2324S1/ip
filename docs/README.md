# Geck

## Features 

### `todo [description] OR t [description]`

Adds a todo with a description to the task list.

Example of usage:<br>
`todo Read book` `t Read book `<br>
Adds a todo with the description `Read book`.

### `list or l`

List all existing tasks in a list.

Example of usage:
`list``l` <br>
Lists all existing tasks in a list.

### `mark [index] OR unmark [index]`

Marks a command as complete/incomplete respectively.

Example of usage: mark 2 or unmark 5
\
Marks the 2nd task as complete, and marks the 5th task as incomplete.

### `deadline [description] /by [end] OR d [description] /by [end] `

Adds a deadline with a description and deadline.<br>
`[end]` argument takes the format `dd/mm/yyyy HHmm`.

Example of usage:<br>
`deadline return book /by 09/02/2023 1422` `d return book /by 09/02/2023 1422` <br>
Adds a deadline with the description `return book` with the deadline `9 Feb 2023 2.22pm`.

### `event [description] /from [start] /to [end] OR e [description] /from [start] /to [end]`

Adds an event with a description, start and end dateTime.<br>
`[start]` & `[end]` arguments take the format `dd/mm/yyyy HHmm`.

Example of usage:<br>
`event return book /from 09/02/2023 1422 /to 10/02/2023 1422`
`e return book /from 09/02/2023 1422 /to 10/02/2023 1422`<br>
Adds an event with the description `return book` starting at `9 Feb 2023 2.22pm` to `10 Feb 2023 2.22pm`.

### `delete [index] / del [index]`

Deletes a task from the list.

Example of usage:<br>
`delete 3` `del 3`<br>
Deletes the 3rd task in the list.

### `find [string] / f [string]`

Displays all tasks with a description that contains `[string]`

Example of usage:<br>
`find book` `f book`<br>
If a task contains "book" in its description, it will be listed out.

### `bye`

Exit the chatbot.

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
