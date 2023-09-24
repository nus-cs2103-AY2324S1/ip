# CarbonBot User Guide
CarbonBot is a simple chatbot that helps with your tasks management.

## Features
- Add different task types (Todo, Deadline, Event)
- Mark tasks as completed or not completed
- Search for tasks using keyword
- Load task list from another data file

## Usage

### Lists all tasks : `list`

Lists all the tasks stored by the bot.

Format: `list`


### Mark task : `mark`

Mark the specified task as completed.

Format: `mark INDEX`


### Unmark task : `unmark`

Unmark the specified task.

Format: `unmark INDEX`


### Add Todo Task : `todo`

Adds a todo type task.

Format: `todo DESCRIPTION`

Examples:
- `todo work` Adds a todo task with the description 'work'.


### Add Deadline Task : `deadline`

Adds a task with a deadline.

Format: `deadline DESCRIPTION /by DEADLINE`
- The deadline date must follow the format: `d/M/yyyy HHmm`
- Example of valid datetime: `26/12/2019 1800`

Examples:
- `deadline assignment /by 26/04/2023 1500` Adds a deadline task with the description 
    'assignment' and a due date of 26 April 2023 3pm.

### Add Event Task : `event`

Adds a task with a start and end date.

Format: `event DESCRIPTION /from START_DATE /to END_DATE`
- The start and end datetime must follow the format: `d/M/yyyy HHmm`
- Example of valid datetime: `26/12/2019 1800`

Examples:
- `event cs4321 lecture /from 26/10/2023 1500 /to 26/10/2023 1700` 
Adds an event task with the description 'cs4321 lecture' and a start date of 26 October 2023 3pm,
and an end date of 26 October 2023 5pm.

### Delete task : `delete`

Deletes the task at the specified index.

Format: `delete INDEX`
- Deletes the task at the specified index.
- The index refers to the index number in the displayed task list.
- The index must be a positive integer and within bounds.

Examples:
- `list` followed by `delete 1` deletes the 1st task in the list.

### Find tasks : `find`

Find tasks that contains the keyword in the description.

Format: `find KEYWORD`
- The search is case-sensitive. E.g. `run` will not match with `RUN`.
- Only the description of the tasks are searched.

Examples:
- `find lecture` returns task with `cs1234 lecture` and `watch lecture` as the description.

### Load task list : `load`

Loads the data file from the specified file path.

Format: `load FILE_PATH`
- If the command is successful, subsequent updates to the task list will be saved 
    to the specified file. 
- If the file path does not contain a valid data file, the current task list will be retained.

Examples:
- `load ./tasks/study.txt` loads the saved task list from the text file.

### Exiting the program : `bye`

Exits the program.

Format: `bye`
