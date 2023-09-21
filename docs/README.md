# User Guide

ChatBot Alpha is a **desktop app that can store, check, uncheck and delete
events, todos and deadlines via a Command Line Interface (CLI).**

<div markdown="block" class="alert alert-info">

**Notes about the command format:**

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.


* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

## Features 

#### Listing all tasks: ``list``
Shows all tasks in the current task list.

Format: ``list``

#### Adding a Todo: ``todo``
Adds a todo with a description that is initially unchecked and can be checked or unchecked.
Format: ``todo DESCRIPTION``

Examples:
-``todo meeting``
-``todo journal entry``

#### Adding a Deadline: ``deadline``
Adds a deadline with a description and a date and time deadline that is initially unchecked and can be checked or unchecked.

Format: ``deadline DESCRIPTION /by YYYY-MM-DD [HHMM]``

Examples:
- ``deadline eat dinner /by 2023-04-10``
- ``deadline midterm assignment /by 2022-12-25 2359``

#### Adding an Event: ``event``
Adds an event with a description, start date and time and end date and time that is initially unchecked and can be checked or unchecked.

Format: ``event DESCRIPTION /from YYYY-MM-DD [HHMM] /to YYYY-MM-DD [HHMM]``

Examples:

- ``event lecture /from 2022-02-03 /to 2022-02-04``
- ``event homework /from 2022-02-03 /to 2022-02-04 1300``
- ``event journal entry /from 2022-01-01 1000 /to 2022-01-02 1200``


### deleting a task: ``delete``

Deletes the task at the specified index in the task list.

Format: ``delete INDEX``
- Deletes the person at the specified index.
- The index must be positive and must refer to a task in the displayed list.
- If you need to check which task is at which index, please use ``list``.

Examples:
- ``list`` followed by ``delete 2`` deletes the 2nd person in the task list.

### Marking a task: ``mark``

Marks a task at the specified index in the task list.

Format: ``mark INDEX``
- Marks the task at the specified index.
- The index must be positive and must refer to a task in the displayed list.
- If you need to check which task is at which index, please use ``list``.
- If the task at the specified index is already marked, this command does nothing.

Examples:
- ``list`` followed by ``mark 4`` marks the 2nd task in the task list.

### Unmarking a task: ``unmark``

Format: ``unmark INDEX``
- Unmarks the task at the specified index.
- The index must be positive and must refer to a task in the displayed list.
- If you need to check which task is at which index, please use ``list``.
- If the task at the specified index is already unmarked, this command does nothing.

Examples:
- ``list`` followed by ``unmark 3`` unmarks the 2nd task in the task list.

### Finding a task: ``find``

Format: ``find ``
Finds tasks with descriptions that contain a keyword.

Format: ``find KEYWORD``
- This keyword is case-insensitive. e.g. ``meeting`` will match with ``Meeting``.
- The tasks are displayed based on their order within the task list.
- Only the description of tasks is searched.
- Sub-words wil be matched e.g. ``cut`` will match with ``cute``.
- The indices displayed in the find list are cosmetic. Do not use these indices in your ``delete``, ``mark`` or ``unmark`` commands. Use the indices from ``list`` instead.

Examples:
- ``find meeting`` finds all task descriptions with the word ``meeting`` in them.
- ``find a`` finds all task descriptions with at least one instance of the letter `a`.


### Saving the data

Task list data is automatically stored on the hard disk in the file ``alpha.txt`` in the ``/data/`` directory.

### Editing the data

Task list data can be edited through manipulation of the ``alpha.txt`` in the ``/data`` directory. Ensure that you follow correct formatting or the program will be unable to read the file and crash.