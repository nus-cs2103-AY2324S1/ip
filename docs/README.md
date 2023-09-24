<p align="center">
  <a href="https://github.com/dloh2236/ip">
    <img src="https://i.imgur.com/VLUORmp.png" alt="Logo" width="72" height="72">
  </a>
</p>
  <h1 align="center">Oreo</h1>
  <p align="center">
    <b>Your trusty canine friend for all your tasks! 🐶</b>
  </p>


## Table of contents

- [About](#about)
- [Quick start](#quick-start)
- [Key Features](#key-features)
- [Not Key But Still Important Feauture](#not-key-but-still-important-features)
- [Date Time Formats](#date-time-formats)

## About
Oreo is your trusty canine companion and personal assistant. She's always by your side,  ready to assist. Oreo excels at
keeping track of tasks and schedules, effortlessly organizing your day. Her boundless energy and unwavering loyalty make
her the perfect partner in the chaos of your daily routine.Providing a comforting presence during late-night work
sessions, Oreo will be indispensable part of your life.

###### Here is preview:

<p align="center">
  <a href="https://github.com/dloh2236/ip">
    <img src=
        "https://raw.githubusercontent.com/nus-cs2103-AY2324S1/ip/61221a4e1829f430950f4c8166eeeff3700b57ef/docs/Ui.png"
            alt="Logo" width="300">
  </a>
</p>

## Quick start

Oreo v1.0 is available for download here [Oreo v1.0](https://github.com/dloh2236/ip/releases/tag/A-Release)

### System Pre-requisites
Make sure your system is running on Java 11, if not some features may not be available to you
and might affect your experience while using the chat-bot.

> [!IMPORTANT]
> For Mac users who are unable to run the JAR file, you will have to install Azul build of Open JDK 11 version
> choose `JDK FX` version. Download link can be found 
> [here](https://www.azul.com/downloads/?version=java-11-lts&os=macos&architecture=arm-64-bit&package=jdk-fx).

<details>

<summary>Date Time Parsing for 'am' and 'pm' for Certain Java 11</summary>

###### Date Time Parsing Errors
> Certain Java 11 versions cannot parse time formats with 'am' or 'pm' input, if any errors encountered
> try other accepted time formats.
> 
> **Example:** use this `17:00` instead of `5pm`

</details>

### Starting up Oreo
Simply put `oreo.jar` into your desired folder and open your terminal at that folder and enter:
`java -jar oreo.jar` and Oreo would be up and running to assist you!

> [!NOTE]
> Oreo will create a data storage file "oreo.txt" where all your tasks will be saved in
> the programs own format. To ensure that your task is saved properly for your next run,
> please do not tamper or move the file from its current location.

You are all set! Start by saying `hi` to oreo.

<details>

<summary style="font-size: 10pt;">If Oreo fails to Start!</summary>

> Try these:
> 1. Make sure you are running Java 11
> 2. If you are a Mac user see [System Pre-requisites](#system-pre-requisites).
> 2. Delete "oreo.txt" file if it exist in the same the same directory. The file is unfortunately corrupted.
> 3. Move `oreo.jar` to another folder and try again.


</details>

## Key Features

Here are some of the key features that Oreo has!

### Help (`help`, `help <COMMAND>`)

#### Help Commands
To get you started in the app, an in-app guide is provided.

###### Format:
`help` - provide a list of all the commands and a brief description of what they do.

`help <COMMAND>` - provide detailed description of the <command> specified, use this to learn more about the command and
the proper syntax to use it.

###### Example Usage:
|     Command     | What it does                                                                                          |
|:---------------:|-------------------------------------------------------------------------------------------------------|
|     `help`      | <span style ='color: green;'>Shows list of all commands and brief descriptions of each command</span> |
|  `help delete`  | <span style ='color: green;'>Shows detailed explanation fo the delete command</span>                  |
|    `help me`    | <span style='color: red;'>Invalid input of unknown command</span>                                     |

### Adding tasks (`todo`, `deadlines`, `events`)

#### Todo tasks
Creates a non-sensitive task

###### Format:
`todo <DESCRIPTION>` - creates a task with the specified description

###### Example Usage:
| Command               | What it does                                                                   |
|-----------------------|--------------------------------------------------------------------------------|
| `todo play with oreo` | <span style='color: green;'>Creates a todo task called "play with oreo"</span> |
| `todo`                | <span style='color: red;'>Invalid input without a description</span>           |

Don't worry about making formatting errors when using Oreo. Oreo is helpful and is more than happy to provide precise
help when there are any improper syntax. Use in-app `help todo` for further guidance when using the app.

#### Deadline tasks
Creates a time sensitive deadline task with a specified end date time.

###### Format:
`deadline <DESCRIPTION> /by <DATETIME>` - creates a deadline task with description and "/by" a date time specified.

<details>

<summary>Notes on deadline date time formatting</summary>

> 1. Date and time input, will be saved accordingly. Past deadlines should be specified in this format, else Oreo will
> find next occurrence of date time input.
> 1. Date only, time will not be included in the saved task
> 1. Time only, date will to next occurrence of the task

</details>

For more info on accepted date time formats, see [Date Time Formats](#date-time-formats)

###### Example Usage:
| Command                                    | What it does                                                                                               |
|--------------------------------------------|------------------------------------------------------------------------------------------------------------|
| `deadline feed oreo /by 01/01/2023, 1800`  | <span style='color: green;'>Creates a deadline task to feed oreo by 1st Jan 2023 6:00 PM</span>            |
| `deadline buy oreo toy /by 1 Jan`          | <span style='color: green;'>Creates a deadline task to get oreo a toy by next occurrence of 1st Jan</span> |
| `deadline bathe oreo /by 6:30pm`           | <span style='color: green;'>Creates a deadline task to bathe oreo by next occurence of 6:30 PM</span>      |
| `deadline /by 6:30pm`                      | <span style='color: red;'>Invalid input without description</span>                                         |
| `deadline feed oreo /by`                   | <span style='color: red;'>Invalid input without date time input</span>                                     |
| `deadline feed oreo/by6:30pm`              | <span style='color: red;'>Invalid input no whitespace _around_ `/by`</span>                                |
| `deadline feed oreo /by 01/01/2023 6:30pm` | <span style='color: red;'>Invalid date time input, missing comma</span>                                    |

See [date time parsing errors](#date-time-parsing-errors) if using correct examples above
give errors.

Don't worry about making formatting errors when using Oreo. Oreo is helpful and is more than happy to provide precise
help when there are any improper syntax. Use in-app `help deadline` for further guidance when using the app. 

#### Event tasks
Creates a time sensitive event with a specified start and end datetime.

###### Format:
`event <DESCRIPTION> /from <DATETIME> /to <DATETIME>` - creates an event task with description, `from` date time and a
`to` date time specified

###### Event `<DATETIME>` combinations accepted:

|    `/from`     | `/to`        |
|:--------------:|--------------|
|  `date, time`  | `date, time` |
|  `date, time`  | `time`       |
|     `date`     | `date`       |
|     `time`     | `time`       |
|     `time`     | `date, time` |

If this is too confusing, just do what makes sense!

###### General Rules:
1. If `/from` or `/to` has a time, both **must** have a time.
1. If `/to` must be after `/from`.

<details>

<summary>More notes on event date time formatting</summary>

> 1. Only date input will not include a time saved in the task.
> 1. Only time input will set a date with reference to the `/to` time and give the next occurence of that duration.
> 1. For `time` to `date, time`  input, the inferred date for `/from` will be the next occurence of that time instead of
> referencing the `/to` time.

</details>

For more info on accepted date time formats, see [Date Time Formats](#date-time-formats)

###### Example Usage:
| Command                                                | What it does                                                                                                                                                                     |
|:-------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `event playtime /from 01/01/2023, 1200 /to 6:00pm`     | <span style='color: green;'>Creates an event task for playtime on Jan 1 2023 from 12:00 PM - 6:00 PM</span>                                                                      |
| `event staycation /from 1 Jan, 1200 /to Jan 3, 12:00`  | <span style='color: green;'>Creates an event task for stay-cation for next occurrence of Jan 1 at 12:00 PM to Jan 3 2023 12:00 PM for next occurence of the of date range</span> |
| `event birthday /from 30 oct /to 6 nov`                | <span style='color: green;'>Creates an event task for Oreo's birthday from Oct 30 to Nov 6 without any time saved</span>                                                         |
| `event walk oreo /from 17:00 /to 1800`                 | <span style='color: green;'>Creates an event task to walk oreo for the next occurrence of 5:00 PM to 6:00 PM</span>                                                              |
| `event holiday /from 5pm /to 3 Jan, 12pm`              | <span style='color: green;'>Creates an event task for holiday from next occcurrence of 5:00 PM to next occurrence of Jan 3, 12pm</span>                                          |
| `event /from 5pm /to 3 Jan, 12pm`                      | <span style='color: red;'>Invalid input without description</span>                                                                                                               |
| `event holiday /from /to `                             | <span style='color: red;'>Invalid input without date time input</span>                                                                                                           |
| `event holiday/from5pm/to3 Jan, 12pm`                  | <span style='color: red;'>Invalid input no whitespace _around_ `/from` and `/to`</span>                                                                                          |
| `event holiday /from 1 Jan /to 3 Jan, 12pm`            | <span style='color: red;'>Invalid input either `/from` or `/to` has time but the other does not</span>                                                                           |
| `event holiday /from 1 Jan 12pm /to 3 Jan, 12pm`       | <span style='color: red;'>Invalid date time input, missing comma</span>                                                                                                          |

See [date time parsing errors](#date-time-parsing-errors) if using correct examples above
give errors.

Don't worry about making formatting errors when using Oreo. Oreo is helpful and is more than happy to provide precise
help when there are any improper syntax. Use in-app `help event` for further guidance when using the app.

### Deleting Tasks (`delete`, `clear`)

#### Delete Commands
Deletes the task at the index specified. It can also be used to delete all tasks, similar to "clear".

###### Format:
`delete <INDEX>` - deletes task at the specified index.

`delete all` - deletes all task in the list.

> [!NOTE]
> index refers to position of task in list.

###### Example Usage:
|    Command     | What it does                                                 |
|:--------------:|--------------------------------------------------------------|
|   `delete 1`   | <span style ='color: green;'>Deletes task at index 1</span>  |
|  `delete all`  | <span style ='color: green;'>Clears current task list</span> |
|  `delete -1`   | <span style='color: red;'>Invalid input of index</span>      |

#### Clear Command
Deletes all task in the list. Similar to "delete all".

###### Format:
`clear` - deletes all task in the list.

###### Example Usage:
|  Command  | What it does                                                     |
|:---------:|------------------------------------------------------------------|
|  `clear`  | <span style ='color: green;'>Deletes all task in the list</span> |

> [!NOTE]
> `clear` ignore any other input after the command

### Viewing Tasks (`list`, `find`)

#### List Command
Displays the list of tasks you currently have. This includes the type of task, whether it is done and the date time
(as specified).

###### Format:
`list` - Shows all the task that are recorded by the user.

###### Example Usage:
|  Command  | What it does                                                        |
|:---------:|---------------------------------------------------------------------|
|  `list`   | <span style ='color: green;'>Shows all task inputted by user</span> |

> [!NOTE]
> `list` ignores any other input after the command

#### Find Command
Finds any relevant tasks with a description that matches the keyword specified.

###### Format:
`find <KEYWORD>` - finds tasks with a description that matches the keyword and displays it.

###### Example Usage:
|    Command    | What it does                                                                |
|:-------------:|-----------------------------------------------------------------------------|
|  `find oreo`  | <span style ='color: green;'>Displays all tasks with keyword of oreo</span> |
|    `find`     | <span style ='color: red;'>Invalid input, no keyword defined</span>         |

### Viewing Tasks (_list, find_)

#### List Command
Displays the list of tasks you currently have. This includes the type of task, whether it is done and the date time
(as specified).

###### Format:
`list` - Shows all the task that are recorded by the user.

###### Example Usage:
|  Command  | What it does                                                        |
|:---------:|---------------------------------------------------------------------|
|  `list`   | <span style ='color: green;'>Shows all task inputted by user</span> |

> [!NOTE]
>`list` ignores any other input after the command

### Modifying Task (`edit`, `update`, `modify`)

#### Edit, Update and Modify Command (same usage for all)
> [!NOTE]
> they all follow the same syntax and have the same functions, `update`, and `modify` are just aliases for `edit`.

Opens editing mode for the task specified by the index. Task specified will appear in the text field for user to modify
the task. Modified task must follow the correct syntax when modifying.

User can type `C` or `c` to exit editing mode for the task and resume normal input.

###### Format:
`edit <INDEX>` - opens editing mode on task specified in index and puts task in the text field for user to edit.
User can make the necessary amends and input it to modify the specified task.

> [!NOTE]
> index refers to the position of task in list.

###### Example Usage:
|  Command  | What it does                                                                                                                                                                              |
|:---------:|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `edit 1`  | <span style ='color: green;'>Assume task 1 is TODO: play with oreo. Command will enter editing mode on task 1 and display "todo play with oreo" in the text field for user to edit</span> |
| `c` / `C` | <span style ='color: green;'>Exits editing mode without editing the task</span>                                                                                                           |
| `edit -1` | <span style='color: red;'>Invalid input of index</span>                                                                                                                                   |

After entering edit mode, you can modify the task in the text field however you want. The new task will be reflected in
the same position as the old task.

> [!IMPORTANT]
> After entering editing mode, you will realise a change in GUI to reflect the change in mode. As such, only adding task
> commands and `c` will do anything. Any other command will prompt user to edit the previously selected task to edit. To
> exit, type `c`.

### Marking and Unmarking Task (`mark`, `unmark`)

#### Mark Command and Unmark Command
> [!NOTE]
> both commands follows the same syntax. Only difference is they do the opposite of the other.

**Mark:** Marks the task at the index specified as complete, or let the user know that it is already marked complete.

**Unmark:** Unmarks the task at the index specified as incomplete, or let the user know that it is still incomplete.

###### Format:
`mark <index>`/`unmark <index>` - task at index becomes/stays complete (incomplete for unmark).

> [!NOTE]
> index refers to the position of task in list.

###### Example Usage (_shown only for mark_):
|  Command  | What it does                                                                                                                |
|:---------:|-----------------------------------------------------------------------------------------------------------------------------|
| `mark 1`  | <span style ='color: green;'>Assuming task at position 1 on list is currently incomplete, it will be marked complete</span> |
| `mark 2`  | <span style ='color: green;'>Assuming task at position 2 on list is currently complete, it will remain complete</span>      |
| `mark -1` | <span style='color: red;'>Invalid input of index</span>                                                                     |

## Not Key but Still Important Features

### Saving Tasks (`save`)

#### Save Command

Saves all task as of current state to file while app continues to run.

###### Format:
`save` - saves current list of task to file

> [!IMPORTANT]
> Task will only save upon `bye` command or `save` command. Please save your task list regularly to avoid losing your
> task list.

###### Example Usage:
| Command | What it does                                                           |
|:-------:|------------------------------------------------------------------------|
| `save`  | <span style ='color: green;'>Saves current list of task to file</span> |

>[!NOTE]
>`save` ignores any other input after the command

### Exiting the Application (`bye`)

#### Bye Command

Closes the application and saves all tasks user has inputted.

###### Format:
`bye` - closes application and saves all tasks.

> [!IMPORTANT]
> Task will only save upon `bye` command or `save` command. Please save your task list regularly to avoid losing your
> task list.

###### Example Usage:
| Command | What it does                                                                                 |
|:-------:|----------------------------------------------------------------------------------------------|
|  `bye`  | <span style ='color: green;'>Closes application and saves all tasks user has inputted</span> |
| `bye!`  | <span style ='color: red;'>Invalid character appended to `bye`</span>                        |

>[!NOTE]
>`bye` ignores any other input after the command. However, appending any characters to bye will be invalid and an
> unrecognised command.

### Miscellaneous

#### `Hello` / `hi`

This input is not a command! Rather we have recognised that first time users are likely to type this input when they
first start using the app. Hence, greeting Oreo prompts her to greet you back and offer assistance. 

Do say hello to Oreo!

#### Other features
There are some hidden features in Oreo which we won't tell you! Play around with her a while more to find these hidden
easter eggs we have planted in the app! 

## Date Time Formats
One of the features of Oreo is the extensive date time formats it accepts. However, due to the wide variety of date time
formats and combinations that Oreo accepts, there are certain rules that need to be followed. This is to ensure the date
time is recorded properly and for the user to input their desired date time easily. 

> [!IMPORTANT]
> As of now, we are still figuring out why `am`, `pm`, `AM` and `PM` time formats are not accepted on certain Java 11
> versions. Do see read [here](#date-time-parsing-errors) for more information.

###### Accepted Date Formats:
|    Format    | Examples                   | Remarks            |
|:------------:|----------------------------|--------------------|
|  `d/M/yyyy`  | `1/1/2023`, `01/12/2023`   |                    |
|  `d-M-yyyy`  | `1-1-2023`, `01-12-2023`   |                    |
|   `d/M/yy`   | `1/1/23`, `01/12/12`       |                    |
|   `d-M-yy`   | `1-1-23`, `01-12-12`       |                    |
| `MMM d yyyy` | `Jan 1 2023`, `jan 1 2023` | Not case sensitive |
|  `MMM d yy`  | `Jan 1 23`, `jan 1 23`     | Not case sensitive |
| `d MMM yyyy` | `1 Jan 2023`, `1 jan 2023` | Not case sensitive |
|  `d MMM yy`  | `Jan 1 2023`, `1 jan 23`   | Not case sensitive |
|  `yyyy/M/d`  | `2023/1/1`, `2023/12/01`   |                    |
|   `yy/M/d`   | `23/1/1`, `23/12/01`       |                    |
|  `yyyy-M-d`  | `2023-1-1`, `2023-12-01`   |                    |
|   `yy-M-d`   | `23-1-1`, `23-12-01`       |                    |
|    `d/M`     | `1/1`, `01/12`             |                    |
|    `d-M`     | `1-1`, `01-12`             |                    |
|   `MMM d`    | `Jan 1`, `jan 1`           | Not case sensitive |
|   `d MMM`    | `1 Jan`, `jan 1`           | Not case sensitive |

###### Accepted Time Formats:
|  Format  | Examples             | Remarks                                                |
|:--------:|----------------------|--------------------------------------------------------|
|  `HHmm`  | `1800`, `0600`       |                                                        |
|  `h a`   | `6 pm`, `6 PM`       | Might not work on some systems                         |
|   `ha`   | `6pm`, `6PM`         |                                                        |
| `HH:mm`  | `18:00`, `06:00`     |                                                        |
| `h:mm a` | `6:00 PM`, `6:00 am` | Not case sensitive,<br/>Might not work on some systems |
| `h:mma`  | `6:00pm`, `6:00AM`   | Not case sensitive,<br/>Might not work on some systems |
| `HH.mm`  | `18.00`, `06.00`     |                                                        |
| `h.mm a` | `6.00 pm`, `6.00 AM` | Not case sensitive,<br/>Might not work on some systems |
| `h.mma`  | `6.00PM`, `6.00am`   | Not case sensitive,<br/>Might not work on some systems |

###### Using Date and Time together:
When using date and time together, you must separate the date and time with `,<space>`.

:white_check_mark: **A GOOD EXAMPLE**
```
1 Jan 2023, 6pm
```
You may use any combination of date time formats when doing this, even within the same command.
```
event playtime /from 1 jan, 1800 /to 03/1, 10pm    // is valid as well
```
However, the purpose of accepting of such varied date time formats is not for this use but to cater to varied
preference of date time formats. 

:x: **BAD EXAMPLES**
```
1 /0  1/2023, 43pm    // not valid date and time, unecessary whitespaces
1 Jan 2023 6pm        // valid date and time but missing comma
```

##### Date Time Assumptions
Oreo allows input of date only and time only commands when adding deadlines and events. Also, date inputs does not
require a year. As such, there are certain underlying assumptions that Oreo will make based on your keyed in date time.

###### Only Time Inputs
Oreo will set the date of the task to the next occurrence of the input time. We have implemented in such as way because
Oreo is a chat bot that keeps track of future task for you. Hence, an input without a date would assume that you are
adding the task for the future.

**For example:**

Assume the date and time now is 1st January 2023, 12pm.

`deadline give oreo breakfast /by 9am`: deadline will be set for 2nd January 2023, 9am.
`deadline give oreo dinner /by 6pm`: deadline will be set for 1st January 2023, 6pm.

> [!NOTE]
> There is a **special** case for **events** task!

Case 1: from `time` to `time` inputs (Reference `/to` time - next occurrence of `/to` time)

`event playtime /from 11am /to 3pm`: event will be set for 1st January 2023, 11-3pm
`event bathe /from 9am /to 9:30am`: event will be set for 2nd January 2023, 11-3pm

Case 2: from `date` `time` to `time` (Reference `/from` date):

`event playtime /from 1 Jan, 11am /to 3pm`: event will be set for 1st January 2023, 11-3pm

Case 3: from `time` to `date` `time` (Reference `/from` time)

`event oreo's special days /from 9am /to 31 Dec, 11:59pm`: event will be set for 2nd Jan 2023, 9am to 31st Dec 11:59pm.

For more information about event date time formats, see [Using Date and Time in Events](#using-date-and-time-in-events)

###### Only Date Inputs
In this case, Oreo will assume these are whole day task and will not set a time in these task hence, only the date is
saved.

###### Date without year inputs
This is where things get tricky! Similar to time only inputs, Oreo will set the year of the date to the next occurrence
of the date or next occurrence of the date specified in `/to` for events.

**For example:**

Assume the date is 1st January 2023.

`deadline new years' eve /by 31 Dec`: deadline will be set for 31st December 2024
`event christmas /from 25 Dec /to 5 Jan`: event will be set for 25th December 2022 to 5th January 2023.
`event playtime /from 9am /to 1st Jan, 5pm`: INVALID! As 9am will be read independently and be assumed to be on
2nd January.

##### Using Date and Time in Events
You should have realised that date time for events can be quite tricky due to the permutations date time that are
possible. **9** to be exact. Due to this we have to limit these permutations that make sense, else Oreo would be confused
to what date and time to set. 

You can read [here](#event-datetime-combinations-accepted-) to see the combinations and learn more about the event task
or you can see it below:

|    `/from`     | `/to`        |
|:--------------:|--------------|
|  `date, time`  | `date, time` |
|  `date, time`  | `time`       |
|     `date`     | `date`       |
|     `time`     | `time`       |
|     `time`     | `date, time` |

If this is too confusing, just do what makes sense!

**General Rules**:
1. If `/from` or `/to` has a time, both **must** have a time.
1. If `/to` must be after `/from`.

**Easy Formating:**

For ease of inputting date time for same day events. We have negated the requirement of requiring a date in `/to` if a
date is specified in `/from`. Oreo will take the date specified in `/from` and apply it to you time in '/to'. This is
logical as it is how we usually converse such as: *Dinner on 1st January, 6 - 8pm*.

Example of how it looks like when adding such an event
```
event Dinner /from 1 Jan, 6pm /to 8pm
```
This reduces the redundancy for commands.

> [!NOTE]
> #### Additional Notes:
>- Only date input will not include a time saved in the task.
>- Only time input will set a date with reference to the `/to` time and give the next occurrence of that duration.
>- For `time` to `date, time`  input, the inferred date for `/from` will be the next occurrence of that time instead of 
>- referencing the `/to` time.
