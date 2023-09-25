# ReaperRemi: User Guide

ReaperRemi is a reminder bot, built to remind you of all the urgent tasks hanging over your head.

...just kidding. It's meant to help!

![ReaperRemi UI](http://wamps-jp.github.io/ip/Ui.png)[^1]

## Features 

### todo _task_

Adds a general `ToDo` task to the list. A ToDo has a description, but no time constraints.
Examples: `todo taskX` or `todo homework`

### deadline _task_ /by _date_

Adds a `Deadline` task to the list. A Deadline has a description and a deadline.
The deadline can be a regular string or in date form (MM/dd/yyyy or dd/MM/yyyy only).
> [!IMPORTANT]
> Do note the /by command. Deadline tasks won't register without it.
Examples: `deadline taskY /by tomorrow` or `deadline workbook /by 02/21/2024`

### event _task_ /from _date1_ /to _date2_

Adds an `Event` task to the list. An Event has a description, a start date, and an end date.
The start and end dates can be regular strings or in date form (MM/dd/yyyy or dd/MM/yyyy only).
> [!IMPORTANT]
> Do note the /from and /to commands. Event tasks won't register without them.
Examples: `event taskZ /from tomorrow /to Saturday` or `event recess week /from 23/09/2023 /to 30/09/2023`

### duration _task_ /for _time_

Adds a `FixedDurationTask` task to the list. A FixedDurationTask has a description and a time (how long it will take).
> [!IMPORTANT]
> Do note the /for command.
Examples: `duration taskW /for two hours` or `duration sleep /for 10 hours`

### mark _index_

Marks the task at the given index as done. Congratulations!
Example: `mark 1`

### unmark _index_

Marks the task at the given index as incomplete. Not so great.
Example: `unmark 2`

### delete _index_

Deletes the task at the given index.
> [!WARNING]
> Deleted tasks can't be recovered.
Example: `delete 3`

### list

Lists all tasks currently active.

### find _keyword_

Shows all tasks that have the keyword.
Examples: `find study` or `find tomorrow`

### save

ReaperRemi automatically saves changes to the list, so no need for this command!

[^1]: Images from creative commons.
