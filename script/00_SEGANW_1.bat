echo off
set TARGET=seganw
set KEYWORD="�Ղ�Ղ�"
set JAR_NAME=websrh.jar
set JRE="jre6\bin\java.exe"

SET /P ANSWER=�L�[���[�h%KEYWORD%�Ō��������{���܂��B��낵���ł��� (Y/N)?
if /i {%ANSWER%}=={y} (goto :YES)
if /i {%ANSWER%}=={yes} (goto :YES)
if /i {%ANSWER%}=={n} (goto :NO)
if /i {%ANSWER%}=={no} (goto :NO)

:YES
%JRE% -jar %JAR_NAME% %TARGET% %KEYWORD%
pause
:NO