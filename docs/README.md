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
- [Quick Start](#quick-start)
- [Key Features](#key-features)
- [Additional Features](#additional-features)
- [Date Time Formats](#date-time-formats)

## About
Oreo is your trusty canine companion and personal assistant. She's always by your side,  ready to assist. Oreo excels at
keeping track of tasks and schedules, effortlessly organizing your day. Her boundless energy and unwavering loyalty
make her the perfect partner in the chaos of your daily routine. Providing a comforting presence during late-night work
sessions, Oreo will become an indispensable part of your life.

###### Here is a preview:

<p align="center">
  <a href="https://github.com/dloh2236/ip">
    <img src=
        "https://raw.githubusercontent.com/nus-cs2103-AY2324S1/ip/61221a4e1829f430950f4c8166eeeff3700b57ef/docs/Ui.png"
            alt="Logo" height="500">
  </a>
</p>

## Quick Start

Oreo v1.0 is available for download [here](https://github.com/dloh2236/ip/releases/tag/A-Release).

### System Pre-requisites
Make sure your system is running on Java 11, as this might affect your experience while using the chatbot.

> [!IMPORTANT]
> For Mac users who are unable to run the JAR file, you will have to install Azul build of Open JDK 11 version
> choose `JDK FX` version. The download link can be found 
> [here](https://www.azul.com/downloads/?version=java-11-lts&os=macos&architecture=arm-64-bit&package=jdk-fx).

<details>

<summary>Date Time Parsing for 'am' and 'pm' for Certain Java 11</summary>

</details>

### Starting up Oreo
Simply put `oreo.jar` into your desired folder, open your terminal in that folder and enter:
`java -jar oreo.jar` and Oreo would be up and running to assist you!

> [!NOTE]
> Oreo will create a data storage file "oreo.txt" where all your tasks will be saved in
> the program's own format. To ensure that your task is saved properly for your next run,
> please do not tamper or move the file from its current location.

You are all set! Start by saying `hi` to Oreo.

<details>

<summary style="font-size: 14px;">If Oreo Fails to Start!</summary>

> Try these:
> 1. Make sure you are running Java 11.
> 2. If you are a Mac user see [System Pre-requisites](#system-pre-requisites).
> 2. Delete "oreo.txt" file if it exists in the same directory. The file is unfortunately corrupted.
> 3. Move `oreo.jar` to another folder and try again.


</details>

## Key Features

Here are some of the key features that Oreo has!

### Help (`help`, `help <COMMAND>`)

#### Help Commands
Provides in-app help, through in-app guides, to get you started in the app.

###### Format:
`help` - provides a list of all the commands and a brief description of what they do.

`help <COMMAND>` - provides a detailed description of the specified `<COMMAND>`, use this to learn more about the command and
it's proper syntax.

###### Example Usage:
|     Command     | What it does                                                                                          |
|:---------------:|-------------------------------------------------------------------------------------------------------|
|     `help`      | <span style ='color: green;'>Shows a list of all commands and brief descriptions of each command</span> |
|  `help delete`  | <span style ='color: green;'>Shows detailed explanation fo the delete command</span>                  |
|    `help me`    | <span style='color: red;'>**INVALID** input of unknown command</span>                                 |

### Adding tasks (`todo`, `deadlines`, `events`)

#### Todo tasks
Creates a non-time-sensitive task.

###### Format:
`todo <DESCRIPTION>` - creates a task with the specified description.

###### Example Usage:
| Command               | What it does                                                                   |
|-----------------------|--------------------------------------------------------------------------------|
| `todo play with oreo` | <span style='color: green;'>Creates a todo task called "play with oreo"</span> |
| `todo`                | <span style='color: red;'>**INVALID** input without a description</span>       |

> Don't worry about making formatting errors when using Oreo. Oreo is helpful and is more than happy to provide precise
> help when there is any improper syntax. Use in-app `help todo` for further guidance when using the app.

#### Deadline tasks
Creates a time-sensitive deadline task with a specified end date time.

###### Format:
`deadline <DESCRIPTION> /by <DATETIME>` - creates a deadline task with a description and "/by" a date time specified.

<details>

<summary>Notes on deadline date time formatting</summary>

1. If there is both date and time input, both will be saved accordingly. This is the recommended way to save past deadlines or else oreo will assume either the date or the year of the date time input. See [Date Time Assumptions](#date-time-assumptions).

2. If there is a date-only input, time will not be included in the saved task.
   
3. If there is a time-only input, the date will be set to the next occurrence of the time.

</details>

For more info on date time formats, see [Date Time Formats](#date-time-formats)

###### Example Usage:
| Command                                    | What it does                                                                                               |
|--------------------------------------------|------------------------------------------------------------------------------------------------------------|
| `deadline feed oreo /by 01/01/2023, 1800`  | <span style='color: green;'>Creates a deadline task to feed oreo by 1st Jan 2023 6:00 PM</span>            |
| `deadline buy oreo toy /by 1 Jan`          | <span style='color: green;'>Creates a deadline task to get Oreo a toy by the next occurrence of 1st Jan</span> |
| `deadline bathe oreo /by 6:30pm`           | <span style='color: green;'>Creates a deadline task to bathe oreo by next occurrence of 6:30 PM</span>      |
| `deadline /by 6:30pm`                      | <span style='color: red;'>**INVALID** input without description</span>                                         |
| `deadline feed oreo /by`                   | <span style='color: red;'>**INVALID** input without date time input</span>                                     |
| `deadline feed oreo/by6:30pm`              | <span style='color: red;'>**INVALID** input no whitespace _around_ `/by`</span>                                |
| `deadline feed oreo /by 01/01/2023 6:30pm` | <span style='color: red;'>**INVALID** date time input, missing comma</span>                                    |

> Don't worry about making formatting errors when using Oreo. Oreo is helpful and is more than happy to provide precise
> help when there is any improper syntax. Use in-app `help deadline` for further guidance when using the app. 

#### Event tasks
Creates a time-sensitive task with both a specified start and end date time.

###### Format:
`event <DESCRIPTION> /from <DATETIME> /to <DATETIME>` - creates an event task with description, `from` date time and a
`to` date time specified.

**Event `<DATETIME>` combinations accepted:**

|    `/from`     | `/to`        |
|:--------------:|:------------:|
|  `date, time`  | `date, time` |
|  `date, time`  | `time`       |
|     `date`     | `date`       |
|     `time`     | `time`       |
|     `time`     | `date, time` |

If this is too confusing, just do what makes sense!

###### General Rules:
> 1. If `/from` or `/to` has a time, both **must** have a time.
> 1. `/to` must be after `/from`.

<details>

<summary>More notes on event date time formatting</summary>

> 1. Only date input will not include a time saved in the task.
> 1. Only time input will set a date with reference to the `/to` time and give the next occurrence of that duration.
> 1. For `time` to `date, time`  input, the inferred date for `/from` will be the next occurrence of that time instead of.
> referencing the `/to` time.

</details>

For more info on accepted date time formats, see [Date Time Formats](#date-time-formats)

###### Example Usage:
| Command                                                | What it does                                                                                                                                                                     |
|-------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `event playtime /from 01/01/2023, 1200 /to 6:00pm`     | <span style='color: green;'>Creates an event task for playtime on Jan 1 2023 from 12:00 PM - 6:00 PM</span>                                                                      |
| `event staycation /from 1 Jan, 1200 /to Jan 3, 12:00`  | <span style='color: green;'>Creates an event task for stay-cation for the next occurrence of Jan 1 at 12:00 PM to Jan 3 12:00 PM for next occurence of the of date range</span> |
| `event birthday /from 30 oct /to 6 nov`                | <span style='color: green;'>Creates an event task for Oreo's birthday from Oct 30 to Nov 6 without any time saved</span>                                                         |
| `event walk oreo /from 17:00 /to 1800`                 | <span style='color: green;'>Creates an event task to walk oreo for the next occurrence of 5:00 PM to 6:00 PM</span>                                                              |
| `event holiday /from 5pm /to 3 Jan, 12pm`              | <span style='color: green;'>Creates an event task for a holiday from the next occurrence of 5:00 PM to the next occurrence of Jan 3, 12pm</span>                                          |
| `event /from 5pm /to 3 Jan, 12pm`                      | <span style='color: red;'>**INVALID** input without description</span>                                                                                                               |
| `event holiday /from /to `                             | <span style='color: red;'>**INVALID** input without date time input</span>                                                                                                           |
| `event holiday/from5pm/to3 Jan, 12pm`                  | <span style='color: red;'>**INVALID** input no whitespace _around_ `/from` and `/to`</span>                                                                                          |
| `event holiday /from 1 Jan /to 3 Jan, 12pm`            | <span style='color: red;'>**INVALID** input either `/from` or `/to` has time but the other does not</span>                                                                           |
| `event holiday /from 1 Jan 12pm /to 3 Jan, 12pm`       | <span style='color: red;'>**INVALID** date time input, missing comma</span>                                                                                                          |

> Don't worry about making formatting errors when using Oreo. Oreo is helpful and is more than happy to provide precise
> help when there is any improper syntax. Use the in-app `help event` for further guidance when using the app.

### Deleting Tasks (`delete`, `clear`)

#### Delete Commands
Deletes the task at the index specified. It can also be used to delete all tasks, similar to "clear".

###### Format:
`delete <INDEX>` - deletes task at the specified index.

`delete all` - deletes all tasks in the list.

> [!NOTE]
> `<INDEX>` refers to the position of the task in the list.

###### Example Usage:
|    Command     | What it does                                                 |
|:--------------:|--------------------------------------------------------------|
|   `delete 1`   | <span style ='color: green;'>Deletes task at index 1</span>  |
|  `delete all`  | <span style ='color: green;'>Clears current task list</span> |
|  `delete -1`   | <span style='color: red;'>**INVALID** input of index</span>      |

#### Clear Command
Deletes all tasks in the list. Similar to "delete all".

###### Format:
`clear` - deletes all tasks in the list.

###### Example Usage:
|  Command  | What it does                                                     |
|:---------:|------------------------------------------------------------------|
|  `clear`  | <span style ='color: green;'>Deletes all task in the list</span> |

> [!NOTE]
> `clear` ignores any other input after the command.

### Viewing Tasks (`list`, `find`)

#### List Command
Displays the list of tasks you currently have. This includes the type of task, whether it is done and the date and time
(as specified).

###### Format:
`list` - shows all the tasks that are recorded by the user.

###### Example Usage:
|  Command  | What it does                                                        |
|:---------:|---------------------------------------------------------------------|
|  `list`   | <span style ='color: green;'>Shows all task recorded by user</span> |

> [!NOTE]
> `list` ignores any other input after the command.

#### Find Command
Finds any relevant tasks with a description that matches the keyword specified.

###### Format:
`find <KEYWORD>` - finds tasks with a description that matches the keyword and displays it.

###### Example Usage:
|    Command    | What it does                                                                |
|:-------------:|-----------------------------------------------------------------------------|
|  `find oreo`  | <span style ='color: green;'>Displays all tasks with keyword of "oreo"</span> |
|    `find`     | <span style ='color: red;'>**INVALID** input, no keyword defined</span>         |

### Modifying Task (`edit`, `update`, `modify`)

#### Edit, Update and Modify Command (same format for all)
> [!NOTE]
> They all follow the same syntax and have the same functions, `update`, and `modify` are just aliases for `edit`.

Opens editing mode for the task specified by the index. The task specified will appear in the text field for the user to modify. Modified tasks must follow the correct syntax of adding a task.

Users can type `C` or `c` to exit editing mode for the task and resume normal mode.

###### Format:
`edit <INDEX>` - opens editing mode on the task specified in the index and puts the task in the text field for the user to edit.
Users can make the necessary amends and input them to modify the specified task.

> [!NOTE]
> `<INDEX>` refers to the position of the task in the list.

###### Example Usage:
|  Command  | What it does                                                                                                                                                                              |
|:---------:|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `edit 1`  | <span style ='color: green;'>Assume task 1 is TODO: play with oreo. The command will enter editing mode on task 1 and display "todo play with oreo" in the text field for the user to edit</span> |
| `c` / `C` | <span style ='color: green;'>Exits editing mode without editing the task</span>                                                                                                           |
| `edit -1` | <span style='color: red;'>**INVALID** input of index</span>                                                                                                                                   |

After entering edit mode, you can modify the task in the text field however you want. The new task will be reflected in
the same position in the list as the old task.

> [!IMPORTANT]
> After entering editing mode, you will realise a change in GUI to reflect the change in mode. As such, only adding task
> commands and `c` will do anything. Any other command will prompt the user to edit the previously selected task. To
> exit, type `c`.

### Marking and Unmarking Task (`mark`, `unmark`)

#### Mark Command and Unmark Command
> [!NOTE]
> Both commands follow the same syntax. The only difference is they do the opposite of the other.

**Mark:** Marks the task at the index specified as complete, or lets the user know that it is already marked complete.

**Unmark:** Unmarks the task at the index specified as incomplete, or let the user know that it is still incomplete.

###### Format:
`mark <INDEX>`/`unmark <INDEX>` - task at index becomes/stays complete (incomplete for unmark).

> [!NOTE]
> <INDEX> refers to the position of the task in the list.

###### Example Usage (_shown only for mark_):
|  Command  | What it does                                                                                                                |
|:---------:|-----------------------------------------------------------------------------------------------------------------------------|
| `mark 1`  | <span style ='color: green;'>Assuming task at position 1 on list is currently incomplete, it will be marked complete</span> |
| `mark 2`  | <span style ='color: green;'>Assuming task at position 2 on list is currently complete, it will remain complete</span>      |
| `mark -1` | <span style='color: red;'>**INVALID** input of index</span>                                                                     |

## Additional Features

### Saving Tasks (`save`)

#### Save Command

Saves all tasks as of current state to file while the app continues to run.

###### Format:
`save` - saves the current list of tasks to file.

> [!IMPORTANT]
> Task will only save upon the `bye` command or `save` command. Please save your task list regularly to avoid losing it.

###### Example Usage:
| Command | What it does                                                           |
|:-------:|------------------------------------------------------------------------|
| `save`  | <span style ='color: green;'>Saves current list of task to file</span> |

>[!NOTE]
>`save` ignores any other input after the command.

### Exiting the Application (`bye`)

#### Bye Command

Closes the application and saves all tasks the user has inputted.

###### Format:
`bye` - closes the application and saves all tasks.

> [!IMPORTANT]
> Task will only save upon the `bye` command or `save` command. Please save your task list regularly to avoid losing your
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

This input is not a command! Rather we have recognised that first-time users are likely to type this input when they
first start using the app. Hence, greeting Oreo prompts her to greet you back and offer assistance. 

Do say hello to Oreo!

#### Other features
There are some hidden features in Oreo that we won't tell you! Play around with her a while more to find these hidden
easter eggs we have planted in the app! 

## Date Time Formats
One of the features of Oreo is the extensive date time formats it accepts. However, due to the wide variety of date time
formats and combinations that Oreo accepts, there are certain rules that need to be followed. This is to ensure the date
time is recorded properly and for the users to input their desired date time easily.

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

###### Using Date and Time Together:
When using date and time together, you must separate the date and time with `,<space>`.

:white_check_mark: **A GOOD EXAMPLE**
```
1 Jan 2023, 6pm
```
You may use any combination of date and time formats when doing this, even within the same command.
```
event playtime /from 1 jan, 1800 /to 03/1, 10pm    // is valid as well
```
However, the purpose of accepting such varied date time formats is not for this use but to cater to varied
preference of date time formats. 

:x: **BAD EXAMPLES**
```
1 /0  1/2023, 43pm    // not valid date and time, unecessary whitespaces
1 Jan 2023 6pm        // valid date and time but missing comma
```

##### Date Time Assumptions
Oreo allows input of date-only and time-only commands when adding deadlines and events. Data inputs may or may not include a year. As such, there are certain underlying assumptions that Oreo will make based on the entered-in date time. Understanding this would enable you to use the date time expansive date time format effectively.

##### Time-Only Inputs
Oreo will set the date of the task to the next occurrence of the input time. We have implemented it in such as way because
Oreo is a chatbot that keeps track of future tasks for you. Hence, an input without a date would assume that you are
adding the task for the future.

<dl>
  <dt>For example:</dt>
  <dd>
    
**Assume the date and time now is 1st January 2023, 12pm.**

`deadline give oreo breakfast /by 9am`: deadline will be set for 2nd January 2023, 9am.

`deadline give oreo dinner /by 6pm`: deadline will be set for 1st January 2023, 6pm.

</dl>

> [!NOTE]
> There is a **special** case for **event** tasks!

**Assume the date and time now is 1st January 2023, 12pm.**

<dl>
  <dt>Case 1: </dt>
  <dd>

**_from `time` to `time` inputs (Reference `/to` time - next occurrence of `/to` time)_**

`event playtime /from 11am /to 3pm`: event will be set for 1st January 2023, 11-3pm

`event bathe /from 9am /to 9:30am`: event will be set for 2nd January 2023, 11-3pm

  </dd>
  <dt>Case 2: </dt>
  <dd>

**_from `date` `time` to `time` (Reference `/from` date)_**

`event playtime /from 1 Jan, 11am /to 3pm`: event will be set for 1st January 2023, 11-3pm

  </dd>
  <dt>Case 3: </dt>
  <dd>

**_from `time` to `date` `time` (Reference `/from` time)_**

`event oreo's special days /from 9am /to 31 Dec, 11:59pm`: event will be set for 2nd Jan 2023, 9am to 31st Dec 11:59pm

  </dd>

</dl>

For more information about event date time formats, see [Using Date and Time in Events](#using-date-and-time-in-events).

##### Date-Only Inputs
In this case, Oreo will assume these are whole-day tasks and will not set a time for these tasks hence, only the date is
saved.

##### Date Without Year Inputs
This is where things get tricky! Similar to time-only inputs, Oreo will set the year of the date to the next occurrence
of the date or next occurrence of the date specified in `/to` for events.

**Assume the date and time now is 1st January 2023, 12pm.**

<dl>
  <dt>For example:</dt>
  <dd>
    
**Assume the date is 1st January 2023.**

`deadline new years' eve /by 31 Dec`: deadline will be set for 31st December 2024.
  
`event christmas /from 25 Dec /to 5 Jan`: the event will be set for 25th December 2022 to 5th January 2023.
  
`event playtime /from 9am /to 1st Jan, 5pm`: INVALID! As 9am will be read independently and be assumed to be on
    2nd January.
  </dd>

</dl>

##### Using Date and Time in Events
You should have realised that date time for events can be quite tricky due to the permutations of date time that are
possible. There are **9** to be exact. Due to this, we have to limit these permutations that make sense. Otherwise, Oreo would be confused
as to what date and time to set. 

You can read [here](#event-datetime-combinations-accepted) to see the combinations and learn more about the event task
or you can see it below:

|    `/from`     | `/to`        |
|:--------------:|:------------:|
|  `date, time`  | `date, time` |
|  `date, time`  | `time`       |
|     `date`     | `date`       |
|     `time`     | `time`       |
|     `time`     | `date, time` |

If this is too confusing, just do what makes sense!

**General Rules**:
1. If `/from` or `/to` have a time, both **must** have a time.
1. `/to` **must** be after `/from`.

**Easy Formatting:**

For ease of inputting date time for same-day events. We have negated the requirement of requiring a date in `/to` if a
date is specified in `/from`. Oreo will take the date specified in `/from` and apply it to the date time in '/to'. We did this as we would usually present events as such: *Dinner on 1st January, 6 - 8pm*.

Example of how it looks like when adding such an event:
```
event Dinner /from 1 Jan, 6pm /to 8pm
```
This also helps reduce the redundancy of commands.

> [!NOTE]
> #### Additional Notes:
>- Only date input will not include a time saved in the task.
>- Only time input will set a date with reference to the `/to` time and give the next occurrence of that duration.
>- For `time` to `date, time`  input, the inferred date for `/from` will be the next occurrence of that time instead of 
>referencing the `/to` time.
