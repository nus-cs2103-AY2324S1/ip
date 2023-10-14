# Sally

## Features

### Adding a todo event: `todo`

Adds a todo event to the task list.

Format: `todo DESCRIPTION`

Example Input:
* `todo read Pride & Prejudice`

Example Output:
* `Added to My List:`<br>
  `[T][ ] (TBD) read Pride & Prejudice`<br>
  `Now you have 1 task in the list.`

### Adding a deadline event: `deadline`

Adds a deadline event to the task list.

Format: `deadline DESCRIPTION /by DATE & TIME`

* `DATE` must be in the format `YYYY-MM-DD`.
* `DATE` must be a valid date.
* `TIME` must be in the format `HHMM`.
* `TIME` must be a valid time.
* `DATE` and `TIME` must be separated by a space.
* The input can have either date (time is set to midnight), time (date is set to current date) or both.

Example Input:
* `deadline submit CS2103T iP /by 2023-09-17 2359`

Example Output:
* `Added to My List:`<br>
  `[D][ ] (TBD) submit CS2103T iP (by: Sep 17 2023, 11:59 PM)`<br>
  `Now you have 2 tasks in the list.`

### Adding an event: `event`

Adds an event to the task list.

Format: `event DESCRIPTION /from DATE & TIME /to DATE & TIME`

* `DATE` must be in the format `YYYY-MM-DD`.
* `DATE` must be a valid date.
* `TIME` must be in the format `HHMM`.
* `TIME` must be a valid time.
* `DATE` and `TIME` must be separated by a space.
* The input can have either date (time is set to midnight), time (date is set to current date) or both.

Example Input:
* `event attend CS2103T lecture /from 2023-09-17 1400 /to 2023-09-17 1600`

Example Output:
* `Added to My List:`<br>
  `[E][ ] (TBD) attend CS2103T lecture (from: Sep 17 2023, 2:00 PM to: Sep 17 2023, 4:00 PM)`<br>
  `Now you have 3 tasks in the list.`

### Marking a task as done : `mark`

Marks the specified task from the task list as done.

Format: `mark INDEX`

* Marks the task at the specified `INDEX` as done.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …

Example Input:
* `list` followed by `mark 1`

Example Output:
* `Nice! I've marked this task as done:`<br>
  `[T][X] (TBD) read Pride & Prejudice`

### Marking a task as undone : `unmark`

Marks the specified task from the task list as undone.

Format: `unmark INDEX`

* Marks the task at the specified `INDEX` as undone.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …

Example Input:
* `list` followed by `unmark 1`

Example Output:
* `Nice! I've marked this task as undone:`<br>
  `[T][ ] (TBD) read Pride & Prejudice`

### Listing all tasks : `list`

Shows a list of all tasks in the task list.

Format: `list`

Example Input:
* `list`

Example Output:
* `My list:`<br>
  `1.[T][ ] (TBD) read Pride & Prejudice`

### Prioritising a task : `prioritise`

Prioritises the specified task from the task list.

Format: `prioritise INDEX PRIORITY`

* Prioritises the task at the specified `INDEX` with the specified `PRIORITY`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …
* The priority **must be a LOW/MEDIUM/HIGH**.
* The priority is case-insensitive. e.g `high` will match `HIGH`

Example Input:
* `list` followed by `prioritise 1 high`

Example Output:
* `Ok, I've prioritised this task:`<br>
  `[T][ ] (HIGH) read Pride & Prejudice`

### Locating tasks by description: `find`

Find tasks whose description contain any of the given keywords.

Format: `find KEYWORD`

* The search is case-insensitive. e.g `hans` will match `Hans`
* Only the description is searched.

Example Input:
* `find Pride`

Example Output:
* `Here are the matching tasks in your list:`<br>
  `1.[T][ ] (TBD) read Pride & Prejudice`

### Deleting a task : `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …

Example Input:
* `list` followed by `delete 1`

Example Output:
* `Noted. I've removed this task:`<br>
  `[T][ ] (TBD) read Pride & Prejudice`<br>
  `Now you have 0 tasks in the list.`

### Exiting the program : `bye`

Exits the program.

Format: `bye`

### Saving the data

Sally data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action         | Format, Examples                                                                                                                         |
|----------------|------------------------------------------------------------------------------------------------------------------------------------------|
| **todo**       | `todo DESCRIPTION` <br> e.g., `todo read Pride & Prejudice`                                                                              |
| **deadline**   | `deadline DESCRIPTION /by DATE & TIME` <br> e.g., `deadline submit CS2103T iP /by 2023-09-17 2359`                                       |
| **event**      | `event DESCRIPTION /from DATE & TIME /to DATE & TIME`<br> e.g., `event attend CS2103T lecture /from 2023-09-17 1400 /to 2023-09-17 1600` |
| **mark**       | `mark INDEX`<br> e.g.,`mark 1`                                                                                                           |
| **unmark**     | `unmark INDEX`<br> e.g., `unmark 1`                                                                                                      |
| **list**       | `list`                                                                                                                                   |
| **prioritise** | `prioritise INDEX PRIORITY` <br> e.g., `prioritise 1 LOW`                                                                                |
| **find**       | `find KEYWORD` <br> e.g., `find Pride`                                                                                                   |
| **delete**     | `delete INDEX` <br> e.g., `delete 1`                                                                                                     |
| **bye**        | `bye`                                                                                                                                    |