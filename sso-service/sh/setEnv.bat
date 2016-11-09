echo off
rem ================================
rem	You must install Java Software Development Kit 1.4 or later!
rem	You must set the path variable APP_HOME and JDK_HOME before running it!
rem	You MAY NOT use spaces in the path names
rem	APP_HOME is the full path name of this program's installation directory, such as D:\ultrapower\ultranms
rem	JDK_HOME is the full path name of the Java Software Development Kit's installation directory, such as D:\j2sdk1.4.2 
rem ================================

set APP_HOME=..
set JDK_HOME=C:\Java\jdk1.6.0_45
set JAVA_HOME=%JDK_HOME%
set CATALINA_HOME=%APP_HOME%\wfserver

if not exist %APP_HOME%\lib goto NO_APP_HOME

set PATH=%JDK_HOME%\bin;%PATH%;%APP_HOME%\bin

set CLASSPATH=%APP_HOME%\bin;%APP_HOME%\conf;%JDK_HOME%\lib;%JDK_HOME%\jre\lib;%APP_HOME%\lib;%APP_HOME%\classes

for %%i in (%APP_HOME%\lib\*.jar) do call  appendcp.bat %%i
for %%i in (%APP_HOME%\lib\*.zip) do call  appendcp.bat %%i

goto END

:NO_APP_HOME
echo     Error: APP_HOME variable does not point to your Ultra NMS directory
echo 		installation directory. Please edit your setEnv.bat script. 
pause
goto END

:NO_JDK_HOME
echo warnning: JDK_HOME variable does not point to your J2SDK1.4
echo 	 	installation directory. Please edit your setEnv.bat script. 
pause
goto END

:END
