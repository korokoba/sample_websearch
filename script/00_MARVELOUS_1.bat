echo off
set TARGET=marvelous
set KEYWORD="��͂艴��"
set JAR_NAME=websearch.jar

SET /P ANSWER=�L�[���[�h%KEYWORD%�Ō��������{���܂��B��낵���ł��� (Y/N)?
if /i {%ANSWER%}=={y} (goto :YES)
if /i {%ANSWER%}=={yes} (goto :YES)
if /i {%ANSWER%}=={n} (goto :NO)
if /i {%ANSWER%}=={no} (goto :NO)

:YES
"jre1.6\bin\java.exe" -jar %JAR_NAME% %TARGET% %KEYWORD%
pause
:NO
