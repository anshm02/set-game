# set-game

This project is a Java implementation of a twist on the popular card game called "Set." The game is played by running the SetGame.java file and providing input to specify the type of game you want to play.

# Game Rules
The traditional Set game involves finding sets of three cards from a deck of unique cards based on specific matching criteria. In this implementation, there are two variations of the game available: "Set Three" and "General."

# Set Three
To play Set Three, run the program and enter three when prompted for the game type. The rules for Set Three are as follows:

1. The game starts with a deck of unique cards, which are initially shuffled and placed face down.
2. The program will display 12 cards from the deck on the game board.
3. Your task is to identify sets of three cards on the board that meet specific matching criteria.
4. A set consists of three cards where each feature (color, shape, number, and shading) is either all the same or all different for each card.
5. When you find a valid set, type the index numbers of the three cards (separated by spaces) to select them.
6. The program will verify if your selected cards form a valid set. If it does, those cards will be replaced with new cards from the deck.
7. The game continues until there are no more sets on the board or no more cards in the deck that can be used for replacement.
8. You win the game if either of the above conditions is met.

# How to Run
To run the Set Game Java implementation, follow these steps:

1. Ensure you have Java installed on your machine.
2. Clone or download this repository to your local system.
3. Open a command prompt or terminal and navigate to the project directory.
4. Compile the Java source file by executing the following command: 
``` javac SetGame.java ```

Run the compiled program by executing the following command:
``` java SetGame ```

