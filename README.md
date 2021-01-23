# Robot2020

## Run on your computer
1/22/2021

**Step 1**

Make sure you have the WPILib version of Visual Studio Code running on your computer.

**Step 2**

Download and run the exe for the [CTRE Phoenix Library](https://github.com/CrossTheRoadElec/Phoenix-Releases/releases/tag/v5.19.4.1).
If Windows tells you "This program could be harmful to your device" click "More Info" then "Run Anyway".

Once you reach this screen select only roboRIO-FRC -> C++/Java.
![](https://i.imgur.com/c1yGJbn.png)
Once you've finished the installation close and reopen VS Code then run the ">Manage Vendor Libraries" command and the ">Install Libraries (Offline)" command. At this point there should be a list of libraries to install, check the one titled "Phoenix" then press enter.

The code should compile successfully.

# Code still not compiling

## Gradle not installed

If you are receiving the following error:
```
'gradlew' is not recognized as an internal or external command, operable program or batch file.
The terminal process "cmd.exe /d /c gradlew build   -Dorg.gradle.java.home="C:\Users\Public\wpilib\2021\jdk"" failed to launch (exit code: 1).
```
you have not updated the project to the 2021 extension. To do this you will need to close and reopen the project and you should get a prompt asking you to update the project, click yes.

On the page to update the project make sure that under:
`Gradle Project. Select the build.gradle file of the gradle project to import.`
you have entered the proper location of the build.gradle file. I.e. "D:Users\\...\Robot-2020\\**Skeeter**\build.gradle" instead of "D:Users\\...\Robot-2020\build.gradle"

Once you have updated the project you should have the `'gradle' is not recognized...` error fixed.

>If you have any other issues with compiling feel free to ask me (Zev) on slack.
>And if you have an issue with compiling which you've solved feel free to show others how to resolve that issue by adding a section to this file!
