# Java Codenames Game

## Developer Information
- **Name:** Yonatan Kagan
- **ID:** 315786541
- **Email:** [yokojona@gmail.com](mailto:yokojona@gmail.com)

## Description
This Java console application is an implementation of the popular board game Codenames. 
It allows players to engage with the game through a command-line interface,
simulating the experience of the tabletop version with features like card guessing, team scoring, and game configuration through XML files.

## Project Structure
The system is divided into three main modules, each residing in its own package and handling distinct aspects of the application:

### 1. DTO (Data Transfer Objects)
Located in the `package dto;`, responsible for the data being transferred between the Engine and the UI.
- **GameSpec:** Holds all the specifications for a game setup.
- **WordCard:** Represents a single card, containing a word and associated metadata.

### 2. Engine
Located in `package engine;`, manages the game logic and state.
#### Sub-packages:
- **game:**
  - **Game:** Manages the state and logic of a single game session.
  - **TeamIndexFinder:** Utility class for finding a team's index.
  - **WordCardFactory:** Creates a valid deck of unique WordCard objects.
- **jaxb:**
  - **generated:** Contains all JAXB generated classes.
  - **JaxbParser:** Utilizes JAXB to parse XML files into a GameSpec object.
  - **SpecValidator:** Validates the integrity and consistency of a GameSpec object.
- **Engine:** Defines the contract for the game engine.
- **EngineImp:** Implementation of the Engine interface.

### 3. UI (User Interface)
Located in `package ui;`, handles the game display and user's input.
#### Sub-packages:
- **input:**
  - **TurnInputGetter:** Obtains input from the user during a game turn.
- **main:**
  - **Main:** Entry point of the game application.
- **printer:**
  - **BoardPrinter:** Prints the game board in a structured and formatted manner.
  - **FileLoadMessagePrinter:** Prints messages related to file loading outcomes.
  - **GuessResultPrinter:** Prints the outcome of a player's guess.
  - **MenuPrinter:** Prints the main game menu.
  - **ScoresPrinter:** Prints the scores of each team participating in the game.
  - **SpecPrinter:** Prints the current game specifications.
- **UI:** Defines the user interface contract for managing interactions within the game.
- **UIImp:** Implementation of the UI interface.

## Installation
To run this project, ensure you have Java 8 installed on your system.

# Clone the repository
git clone [https://github.com/Yokojona/Code-Names](https://github.com/Yokojona/Code-Names)
