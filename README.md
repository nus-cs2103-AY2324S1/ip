# Alice Task Manager

Alice Task Manager (Alice) is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, she can get your tasks done faster than traditional GUI apps.

This is a greenfield Java project in the CS2103T Software Engineering module, which is part of the curriculum of National University of Singapore (NUS).

Link to the user guide: [Alice Task Manager](https://ncduy0303.github.io/ip/)

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/alice/Alice.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
    ____________________________________________________________
    Hewwo~! I'm Alice
    What can I do for you nyaa~? ˃̵ᴗ˂̵
    ____________________________________________________________
   ```
