# CatBot User Guide

Hi! CatBot is a friendly and informal task assistant. 

## Features 

### It's really uncertain...
It's trying its best.

### You can type to do things
Like the 1337coder you are. :)

## Usage

### `list` - view tracked tasks

Command: `list`

Example of usage: 
`list`

Expected outcome:
Lists all tasks currently tracked, or infoms you if you have no currently tracked tasks.

### `mark` - mark a task as done

Command: `mark <index>`
where `<index>` is the corresponding number of the task when displayed with `list`.

Example of usage: 
`mark 5`

Expected outcome:
Marks the fifth task in your list as done, or informs you that the index is out of range.

### `unmark` - mark a task as not done

Command: `unmark <index>`
where `<index>` is the corresponding number of the task when displayed with `list`.

Example of usage: 
`unmark 5`

Expected outcome:
Marks the fifth task in your list as not done, or informs you that the index is out of range.

### `delete` - stop tracking a task

Command: `delete <index>`
where `<index>` is the corresponding number of the task when displayed with `list`.

Example of usage: 
`delete 5`

Expected outcome:
Removes the task that was the fifth task before deletion, or informs you that the index is out of range.

### `todo` - create a task with a simple description

Command: `todo <description>`
where `<description>` is any text without `/` (character reserved for commands).

Example of usage: 
`todo tP brainstorming`

Expected outcome:
Starts tracking a task called "tP brainstorming".

### `deadline` - create a task with a deadline

Command: `deadline <description> /by <date>`
where `<description>` is any text without `/` (character reserved for commands),
and `<date>` is a date in `YYYY-MM-DD` format.

Example of usage: 
`deadline iP is due /by 2023-09-22`

Expected outcome:
Starts tracking a task called "iP is due" that has a due date that is 22nd September, 2023.

### `event` - create a task with start and end dates

Command: `event <description> /from <date> /to <date>`
where `<description>` is any text without `/` (character reserved for commands),
and `<date>` is a date in `YYYY-MM-DD` format.

Example of usage: 
`event iP-related panic /from 2023-09-15 /to 2023-09-22`

Expected outcome:
Starts tracking an event called "iP-related panic" that started on 15th September 2023, and is still ongoing at the time of writing.

### `edit` - change the details of a task

Command: `edit <index> /<parameter> <value>`
where `<index>` is the corresponding number of the task when displayed with `list`,
`<parameter>` is a word describing the specific information to change (see elaboration and examples below),
and `value` is any text without `/` (character reserved for commands), or a date in `YYYY-MM-DD` format (depending on the `<info>`).

Other than the description, which uses the `desc` parameter, all other parameters follow those used during task creation.
Edit does not change the type of task (does not change a `todo` to a `deadline`).

Example of usage: 
`edit 2 /by 2023-09-25`

Expected outcome:
If the second task in the list is a `deadline` with a due date of 22nd September, extends its due date back by three days to 25th September.

### `find` - find tasks with matching descriptions

Command: `find <description>`
where `<description>` is any text without `/` (character reserved for commands).
Matches partial descriptions as well.

Example of usage: 
`find iP`

Expected outcome:
Provides an unnumbered list of tasks whose descriptions contain the text searched, or informs you that there are no matches.

### `bye` - close the application

Command: `bye`

Expected outcome:
Catbot says bye, and the application closes in a few seconds.
