# CLI Chess

This is a command line interface chess game coded in Java. Currently, a game of chess can be played with move, capture, and pawn promotion functionality for all pieces as well as player scores, checkmate, stalemate detection. En passant and castling are not implemented but I may add them in the future if time permits it.

## About The Project
I decided to make chess after creating a minimally viable chess program for a lab assignment at Algonquin College. I created this using Java with the intention of furthering my understanding of OOP principles such as inheritance and polymorphism. Although chess is kind of a boring project to make, I wanted to set a goal to make a project that actually works before I try my hand at more complex ideas or projects.  

About the data structures I used, I decided to represent the chess board itself using a 2D array which would make it easier to check the board spaces around a piece for things like verifying the validity of a move and displaying the board in the console. Additionally, I used ArrayLists to keep track of each player's pieces and to easily add or remove pieces when performing captures or testing the state of the board. Since the columns of a chessboard are typically represented with letters, I used a map to quickly translate the column part of a user's inputs into numbers to access the array representing the board. 

A big takeaway from this project for me is the importance of unit tests. Towards the end of the project I found myself repeatedly doing manual tests to verify that the game still worked as intended. I can only imagine the amount of testing that would be required on larger projects which is why automating a lot of it to unit tests makes sense.

## Installation

```sh
 git clone https://github.com/dylanboyling/Chess
 cd Chess
 javac PlayChess.java
 java PlayChess
```

## How to Play

At the moment, it is played between two human players one of whom will be randomly selected to go first. Moves are entered by entering the coordinates of the piece you wish to move followed by the coordinates you wish to move it to.

e.g. 'a2 a4' to move the piece located at a2 to a4

Enter ? to display all legal moves you may make and Q to quit. The game will end when checkmate is detected (player is in check and no moves can be made) or there is a stalemate (player is not in check and no moves can be made). When the game ends, the player will be prompted if they'd like to play another game and each player's scores will be updated accordingly.

## Screenshots

![Alt text](/Screenshots/Chess_Intro.png?raw=true )
![Alt text](/Screenshots/Chess_Move.png?raw=true )
![Alt text](/Screenshots/Chess_Promotion.png?raw=true )
![Alt text](/Screenshots/Chess_CheckMate.png?raw=true )

## License 

Distributed under the MIT License. See `LICENSE.md` for more information.
