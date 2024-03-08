# Project Description
This project focuses on developing a Java-based version of Pac-Man, named Tiny-Pac, utilizing Java and JavaFX as part of the Advanced Programming course at Instituto Politécnico de Coimbra.
<p align="center">
  <img src= pac_man.png/>
</p>

---
## Covered Topics
- Implementation of Software Design Patterns
- Creation of fundamental Pac-Man functionalities using Java
- Development of Graphic User Interface with JavaFX
- Handling File Input and Output
- Serialization and Deserialization techniques
- Clear separation between model and interface layers
- Implementation of Finite State Machine (FSM)
- Utilization of Polymorphism
- Documentation with JavaDoc
- Unit Testing for classes related to the Finite State Machine

## Objectives
- Different levels
- Creating an initial menu (including a Top 5 feature)
- Implementing a Wrapping Zone 
- Incorporating Food and Fruits to enhance Pac-Man's abilities
- Utilize the motor de jogo provided by professors
- Incorporating a Pause feature with options to resume or save the game progress 

## Project Structure
- pt.isec.pa.tinypac: package encompassing the entire application.
- pt.isec.pa.tinypac.model: contains the facade class responsible for accessing the logic, internally distributing the requested responsibilities, and managing processing dynamics through a state machine. 
- pt.isec.pa.tinypac.model.fsm: includes classes related to the state machine and its hierarchy.
- pt.isec.pa.tinypac.model.data: comprises classes representing data structures and their management. It includes the Maze class and the IMazeItem interface.
- pt.isec.pa.tinypac.gameengine: provided code related to the game engine.
- pt.isec.pa.tinypac.ui.text: class(es) implementing the text mode interface. 
- pt.isec.pa.tinypac.ui.gui: classes implementing the graphical mode interface in JavaFX.
