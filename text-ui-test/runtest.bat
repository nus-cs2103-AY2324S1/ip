@ECHO OFF

REM Set the working directory to the root directory (ip) before executing the script
CD /D ..\

REM create bin directory if it doesn't exist
if not exist bin mkdir bin

REM delete output from previous run
del .\text-ui-test\ACTUAL.TXT

REM compile the code into the bin folder
javac -cp src\main\java -Xlint:none -d bin src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM because now that the working directory is set to ip, ACTUAL.TXT and EXPECTED.TXT
REM can only be accessed with the appropriate cd

REM Run the program, feed commands from input.txt file, and redirect the output to ACTUAL.TXT
java -classpath bin duke.Duke < .\text-ui-test\input.txt > .\text-ui-test\ACTUAL.TXT

REM Compare the output to the expected output
FC .\text-ui-test\ACTUAL.TXT .\text-ui-test\EXPECTED.TXT

