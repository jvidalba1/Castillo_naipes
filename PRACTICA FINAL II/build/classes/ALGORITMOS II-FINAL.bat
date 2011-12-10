@echo off

PATH "%PROGRAMFILES%\Java\jdk1.6.0_01\bin";%PATH%
PATH "%PROGRAMFILES%\Java\jdk1.6.0_01\lib";%PATH%

cls

javac Carta.java
javac Coordenada.java
javac Juego.java
javac Interfaz.java
javac Jugador.java
javac Main.java


color c0
cls

echo -----------------------------------------------------------------
echo  			PRACTICA FINAL

echo  VERSION 1.0
echo  			AUTORES 

echo		WINDSOR ANGELHO FRANCO QUIROZ		200810061010
echo		ALEJANDRO MARIN BURTICA			200810060010
echo		JOSE MATEO VIDAL BARRERA		200810012010

echo ------------------------------------------------------------------
java Main

echo ------------------------------------------------------------------
pause
