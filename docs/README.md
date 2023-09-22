# User Guide
Duke is a chatbot that is designed for **managing tasks**, optimized for use via a **Command Line Interface (CLI)** while still having the benefits of a **Graphical User Interface (GUI)**

* [Quick start](#quick-start)
* [Features](#features)
    * [Adding a todo task: ``todo``](#adding-a-todo-task--todo)
    * [Adding a deadline task: ``deadline``](#adding-a-deadline-task--deadline)
    * [Adding an event task: ``event``](#adding-an-event-task--event)
    * [Adding a Fixed duration task: ``fixed``](#adding-a-fixed-duration-task--fixed)
    * [Listing all tasks: ``list``](#listing-all-tasks--list)
    * [Mark the task as done: ``mark``](#mark-the-task-as-done--mark)
    * [Mark the task as not done: ``unmark``](#mark-the-task-as-not-done--unmark)
    * [Delete a task: ``delete``](#delete-a-task--delete)
    * [Locating tasks by keyword: ``find``](#locating-tasks-by-keyword--find)
    * [Exit the ChatBot: ``bye``](#exit-the-chatbot--bye)
* [Command Summary](#command-summary)
----------------------------------------------------------------------------------------

## Quick Start
1. Ensure you have the Java '11' or higher installed on your computer or laptop.
2. Download the latest 'Duke.jar' file from [here](https://github.com/angkyakdifp/ip/releases).
3. Copy the file to the folder that you want to use as the *home folder* for the Duke Chatbot.
4. Double-click the file to start the application. The alternative way is to open a command terminal, ``cd`` into the folder where the ``.jar`` file belongs, and use the ``java -jar Duke.jar`` command to run the application. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br />
![Gui Example](Ui.png)
5. Type the command in the box provided and press Enter or the send button to execute it.
6. Refer to the [Features](#features) below for details of each command

## Features 
**Notes about the command format:**
* Words in ``<>`` are the parameters to be supplied by the user. e.g. in ``todo <Description>``, ``<Description>`` is a parameter which can be used as ``todo reading book``
* Items with ``...`` after them can be used multiple times but not including zero times. e.g. ``<Amount><Type>`` can be used as ``2h 3m``, ``2h`` etc.
* The commands are case-insensitive. You can use uppercase or lowercase letters interchangeably. e.g ``list`` and ``LIST`` will refer to the same command.
* Extraneous parameters for commands that do not take in parameters (such as ``list`` and ``bye``) will be ignored. e.g if the command specifies ``list 123``, it will be interpreted as ``list``.

### Adding a todo task: ``todo``
Adds a todo task.

Format: ``todo <Description>``

Parameter:
* ``<Description>``: The description of the task.

Examples:
* ``todo reading books``
* ``todo watching korean drama``

### Adding a deadline task: ``deadline``
Adds a deadline task.

Format: ``deadline <Description> /by <Deadline>``

Parameter:
* ``<Description>``: The description of the task.
* ``<Deadline>``: The deadline of the task. Should be in ``yyyy-mm-dd`` format. e.g ``2023-10-11``.

Examples:
* ``deadline making homework /by 2023-10-11``
* ``deadline study for exam /by 2023-11-12``

### Adding an event task: ``event``
Adds an event task.

Format: ``event <Description> /from <Start> /to <End>``

Parameter:
* ``<Description>``: The description of the task.
* ``<Start>``: The starting time of the event. Should be in ``yyyy-mm-dd`` format. e.g ``2023-10-11``.
* ``<End>``: The ending time of the event. Should be in ``yyyy-mm-dd`` format. e.g ``2023-10-11``.
* ``<Start>`` could not exceed ``<End>``. e.g ``event Watching Formula 1 /from 2023-10-10 /to 2023-10-9`` is not a valid input.

Examples:
* ``event Watching Formula 1 /from 2023-10-10 /to 2023-10-12``
* ``event Student discount deals /from 2023-10-11 /to 2023-12-12``

### Adding a Fixed duration task: ``fixed``
Adds a Fixed duration task.

Format: ``fixed <Description> /need <Amount><Type>``

Parameter:
* ``<Description>``: The description of the task.
* ``<Amount>``: The amount of time needed.
* ``<Type>``: The units of time. Days are represented as ``d``, hours are represented as ``h`` and minutes are represented as ``m``.
* You could combine the units of time. e.g ``Study java /need 10d 20h 50m`` is a valid input
* You can omit or skip the time units that are not needed in a given context. e.g ``fixed playing mobile legends /need 2h`` is a valid input.
* The order of each type should be started from the broadest time range. e.g ``fixed playing mobile legends /need 30m 2h`` is not a valid input, the correct input is ``fixed playing mobile legends /need 2h 30m``.
* The ammount of hours cannot exceed 23 and the ammount of minutes cannot exceed 59. e.g ``fixed drawing /need 60m`` and ``fixed working /need 24h`` is not valid

Examples:
* ``todo reading books``
* ``todo watching korean drama``

### Listing all tasks: ``list``
Shows a list of all tasks.

Format: ``list``

### Mark the task as done: ``mark``
Mark a specified task as done.

Format: ``mark <Index>``

Parameter:
* ``<Index>``: The index should be a positive integer that does not exceed the amount of the tasks.

Example:
* ``mark 1``

### Mark the task as not done: ``unmark``
Mark a specified task as not done.

Format: ``unmark <Index>``

Parameter:
* ``<Index>``: The index should be a positive integer that does not exceed the amount of the tasks.

Example:
* ``unmark 1``

### Delete a task: ``delete``
Delete a specified task.

Format: ``delete <Index>``

Parameter:
* ``<Index>``: The index should be a positive integer that does not exceed the amount of the tasks.

Example:
* ``delete 1``

### Locating tasks by keyword: ``find``
Find tasks that has a description containing any of the given keywords.

Format: ``find <Keyword>``

Parameter: 
* ``<Keyword>``: The keyword of the task to be searched for.
* The search is case-insensitive. e.g ``read`` will match ``Read``

Example:
* ``find read``

### Exit the ChatBot: ``bye``
Quit the ChatBot and save the tasks to the file

Format: ``bye``

----------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action                      | Format and Examples                                                                                       |
|-----------------------------|-----------------------------------------------------------------------------------------------------------|
| **Add Todo**                | `todo <Description>` <br /> e.g., `todo read books`                                                       |
| **Add Deadline**            | `deadline <Description> /by <Deadline>` <br /> e.g. `deadline making homework /by 2023-10-11`             |
| **Add Event**               | `event <Description> /from <Start> /to <End>` <br /> e.g. `event Concert /from 2023-10-11 /to 2023-10-13` |
| **Add Fixed Duration Task** | `fixed <Description> /need <Ammount><Type>` <br /> e.g. `fixed playing mobile legends /need 3h`           |
| **List Tasks**              | `list` <br /> e.g. `list`                                                                                 |
| **Mark Task**               | `mark [index]` <br /> e.g., `mark 1`                                                                      |
| **Unmark Task**             | `unmark [index]` <br /> e.g., `unmark 1`                                                                  |
| **Delete Task**             | `delete [index]` <br /> e.g., `delete 1`                                                                  |
| **Find by Keyword**         | `find [keyword]` <br /> e.g., `find read`                                                                 |                                   |
| **Exit**                    | `bye` <br /> e.g., `bye`                                                                                  |
