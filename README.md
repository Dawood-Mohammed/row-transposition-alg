# Row Transposition Algorithm App

a simple application that encrypt and decrypt texts via the classical encryption algorithm named 'row transposition'.

## run the app
- make sure you got jdk 8 installed and the path is set to %JAVA_HOME%/bin
- open cmd or terminal and type 'javac RowTransposition.java'
- type 'java RowTransposition'

## what it does
- encrypt/decrypt text either entered in the text box or loaded from an external .txt file.
- the result is printed in a label at the bottom.

## how it works
- two text boxes provided one for the text to be encrypted/decrypted and the othor one for the algorithm key.
- if the first text box is empty then you'll be prompted to select a file to load the text from.

# note:-
the key length must be seven and consist from the numbers in the range 1 - 7 in any order(e.g 1354726).