# CLI Chess

This is a command line interface chess game coded in Java. It's adapted from a minimally viable chess program I created for a lab assignment at Algonquin College. I created this using Java with the intention of furthering my understanding of OOP principles such as inheritance and polymorphism.

Currently, a basic game of chess can be played with move and capture functionality for all pieces as well as checkmate and stalemate detection. En passant and castling are not implemented but I may add them in the future if time permits it.

Currently, a basic game of chess can be played with move, capture, and pawn promotion functionality for all pieces as well as checkmate and stalemate detection. En passant and castling are not implemented but I may add them in the future if time permits it.

## Installation

```sh
 git clone https://github.com/dylanboyling/Chess
 cd Chess
 javac PlayChess.java
 java PlayChess
```

## How to play

At the moment, it is played between two human players one of whom will be randomly selected to go first. Moves are entered by entering the coordinates of the piece you wish to move followed by the coordinates you wish to move it to.

e.g. 'a2 a4' to move the piece located at a2 to a4

Enter ? to display all legal moves you may make (WIP) and Q to quit. The game will end when checkmate is detected (player is in check and no moves can be made) or there is a stalemate (player is not in check and no moves can be made). When the game ends the player will be prompted if they'd like to play another game and each player's scores will be updated accordingly.

## Screenshots

![Alt text](/Screenshots/Chess_Intro.png?raw=true )
![Alt text](/Screenshots/Chess_Move.png?raw=true )
![Alt text](/Screenshots/Chess_Promotion.png?raw=true )
![Alt text](/Screenshots/Chess_CheckMate.png?raw=true )
