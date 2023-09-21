# User Guide

This user guide is adapted from [AddressBook Level 3](https://se-education.org/addressbook-level3/UserGuide.html)

Boti Chatbot is a desktop app for **managing tasks**, optimized for use via a [Command Line Interface (CLI)](https://en.wikipedia.org/wiki/Command-line_interface) while still having the benefits of a [Graphical User Interface (GUI)](https://en.wikipedia.org/wiki/Graphical_user_interface).

----------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure to download [Java `11`](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html) or above installed in your Computer.

2. Download the latest `boti.jar` from [here](https://github.com/FerdiHS/ip/releases).

3. Copy the file to the folder you want to use as the home folder for your Boti Chatbot.

4. Open a command terminal, ``cd`` into the folder you put the jar file in, and use the ``java -jar addressbook.jar`` command to run the application.
   The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
   <br /> <br />
   ![Ui](Ui.png)

----------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**Notes about the command format:**<br>

* Command words are case-insensitive.<br>
  e.g. `list`, `List`, and `LIST` all are valid and refer to the same command.

* Words inside the `[]` are the parameters to be supplied by the user.<br>
  e.g. in `todo [task]`, `[task]` is a parameter which can be used as `todo workout`.

* Parameter `[deadline]`, `[start]`, and `[end]` must be supplied in `YYYY-MM-DD` format.<br>
  e.g. `/by [deadline]` can be used as `/by 2023-09-22`

* Parameter `[time]` must be supplied in `[amount] [type]` where the `[amount]` must be an integer and the `[type]` must be equal to either `days`, `day`, `hours`, `hour`, `minutes`, `minute`, `seconds`, or `second`.<br>
  e.g. `/in [time]` can be used as `/in 3 hours`

* Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will result in error.<br>
  e.g. if the command specifies `list whatever`, it will be interpreted as invalid command.

* The expected outcomes given in this user guide is based when the first example command of each features is executed with the same order as the user guide.

</div>

### Adding a task: `todo`, `deadline`, `event`, `timed`

Adds a task to the list of tasks. Task is divided into 4 types:
1. Todo
   Task which does not have any time constraint.
   e.g. *buy a chair* which can be done anytime without any time constraint.
3. Deadline
   Task which needs to be done before the time constraint.
   e.g. *CS2103T ip by 22 September 2023* which need to be done before 22 September 2023.
5. Event
   Task which occurs in a certain period of time.
   e.g. *Recess week from 25 September 2023 to 29 September 2023* which occurs between 25-29 September 2023.
7. Timed
   Task which can be done in a certain amount of time.
   e.g. *Watch Oppenheimer in 3 hours* which can be done in 3 hours.

#### Adding a todo task: `todo`

Adds a todo task to the list of tasks.

Format: `todo [name]`

Parameter:

- `name`: The name/description of the todo task.

Example:

- `todo buy a chair`
- `todo fix the rooftop`
- `todo tidy up the bedroom`

Expected outcome:

```
    ____________________________________________________________
    Got it. I've added this todo:
    [T][ ] buy a chair
    Now you have 1 tasks in the list.
    ____________________________________________________________
```

#### Adding a deadline task: `deadline`

Adds a deadline task to the list of tasks.

Format: `deadline [name] /by [deadline]`

Parameter:

- `name`: The name/description of the deadline task.
- `deadline`: The deadline of the task.

Example:

- `deadline CS2103T ip /by 2023-09-22`
- `deadline pay tuition /by 2023-08-30`
- `deadline Online Assessment Shopee /by 2023-10-01`

Expected outcome:

```
    ____________________________________________________________
    Got it. I've added this deadline:
    [D][ ] CS2103 ip  (by: SEP 22 2023)
    Now you have 2 tasks in the list.
    ____________________________________________________________
```

#### Adding an event task: `event`

Adds an event task to the list of tasks.

Format: `event [name] /from [start] /to [end]`

Parameter:

- `name`: The name/description of the event task.
- `start`: The start of the event task.
- `end`: The end of the event task.

Example:

- `event Recess Week /from 2023-09-25 /to 2023-09-29`
- `event Apple Student Discount 2023 /from 2023-06-22 /to 2023-10-02`
- `event TikTok Open Intern Application /from 2023-08-01 /to 2023-09-30`

Expected outcome:

```
    ____________________________________________________________
    Got it. I've added this event:
    [E][ ] Recess Week (from: SEP 25 2023 to: SEP 29 2023)
    Now you have 3 tasks in the list.
    ____________________________________________________________
```

#### Adding a timed task: `timed`

Adds a timed task to the list of tasks.

Format: `timed [name] /in [time]`

Parameter:

- `name`: The name/description of the timed task.
- `time`: The amount of time needed to finish the timed task.

Example:

- `timed Watch Oppenheimer /in 3 hours`
- `timed Read CS2103 Week 7 Materials /in 45 minutes`
- `timed Kaggle Virtual Competition /in 2 days`

Expected outcome:

```
    ____________________________________________________________
    Got it. I've added this timed task:
    [TT][ ] Watch Oppenheimer (in: 3 hours)
    Now you have 4 tasks in the list.
    ____________________________________________________________
```

### Listing all tasks: `list`

Shows a list of all tasks, including other informations such as whether the task is marked and the date related to the task.

Format: `list`

Example:

- `list`

Expected outcome:

```
    ____________________________________________________________
    Here are the tasks in your list:
    1. [T][ ] buy a chair
    2. [D][ ] CS2103 ip  (by: SEP 22 2023)
    3. [E][ ] Recess Week (from: SEP 25 2023 to: SEP 29 2023)
    4. [TT][ ] Watch Oppenheimer (in: 3 hours)
    ____________________________________________________________
```

### Marking a task as done: `mark`

Marks the task with the given index as done.

Format: `mark [index]`

Parameter:

- `index`: The index of the task that will be marked with $1$ &le; `index` &le; the number of tasks in the list of tasks.

Example:

- `mark 2`

Expected outcome:

```
    ____________________________________________________________
    Nice! I've marked this task as done:
    [D][X] CS2103 ip  (by: SEP 22 2023)
    ____________________________________________________________
```

### Unmarking a task as not done: `unmark`

Unmarks the task with the given index as not done.

Format: `unmark [index]`

Parameter:

- `index`: The index of the task that will be unmarked with $1$ &le; `index` &le; the number of tasks in the list of tasks.

Example:

- `mark 2`

Expected outcome:

```
    ____________________________________________________________
    OK, I've unmarked this task as not done yet:
    [D][ ] CS2103 ip  (by: SEP 22 2023)
    ____________________________________________________________
```

### Removing a task: `delete`

Removes the task with the given index from the list of tasks.

Format: `delete [index]`

Parameter:

- `index`: The index of the task that will be removed with $1$ &le; `index` &le; the number of tasks in the list of tasks.

Example:

- `delete 2`

Expected outcome:

```
    ____________________________________________________________
    Noted. I've removed this task:
    [D][ ] CS2103 ip  (by: SEP 22 2023)
    Now you have 3 tasks in the list.
    ____________________________________________________________
```

### Finding a list of tasks with a certain keyword: `find`

Finds the list of tasks that contain a certain keyword in their name/description.

Format: `find [keyword]`

Parameter:

- `keyword`: The keyword of the task.

Example:

- `find  chair`

Expected outcome:

```
    ____________________________________________________________
    The list of tasks containing the keyword is:
    1. [T][ ] buy a chair
    ____________________________________________________________
```

### Exiting the program: `bye`
Exits the Boti Chatbot.

Format: `bye`

Example:

- `bye`

Sample Outcome:

```
    ____________________________________________________________
    Bye. Hope to see you again soon!
    ____________________________________________________________
```

----------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action               | Format and Examples                                                                                    |
|----------------------|--------------------------------------------------------------------------------------------------------|
| **Add Todo**         | `todo [name]` <br /> e.g., `todo buy a chair`                                                          |
| **Add Deadline**     | `deadline [name] /by [deadline]` <br /> e.g. `deadline CS2103T ip /by 2023-09-22`                      |
| **Add Event**        | `event [name] /from [start] /to [end]` <br /> e.g. `event Recess Week /from 2023-09-25 /to 2023-09-29` |
| **Add Timed Task**   | `timed [name] /in [time]` <br /> e.g. `timed Watch Oppenheimer /in 3 hours`                            |
| **List Tasks**       | `list` <br /> e.g. `list`                                                                              |
| **Mark Task**        | `mark [index]` <br /> e.g., `mark 2`                                                                   |
| **Unmark Task**      | `unmark [index]` <br /> e.g., `unmark 2`                                                               |
| **Delete Task**      | `delete [index]` <br /> e.g., `delete 2`                                                               |
| **Find by Name**     | `find [keyword]` <br /> e.g., `find chair`                                                             |                                   |
| **Exit**             | `bye` <br /> e.g., `bye`                                                                               |

----------------------------------------------------------------------------------------------------------------------