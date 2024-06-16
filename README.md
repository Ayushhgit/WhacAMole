Overview
WhacAMole is a simple arcade game implemented in Java using the Swing framework. The objective of the game is to hit the mole that appears randomly on a 3x3 grid while avoiding the plant. The game keeps track of the score, and the player can restart the game at any time.

Features
Randomly appearing moles and plants on a 3x3 grid.
Incremental scoring system: hitting a mole increases the score by 10 points.
Game-over condition: hitting a plant ends the game and displays the final score.
Restart button to reset the game.

Code Explanation
Main Components
Frame Setup: The game is contained within a JFrame which holds the main components of the game.
Panels: There are two main panels:
textPanel for displaying the score and the restart button.
boardPanel for the 3x3 grid of buttons.
Icons: The mole and plant are represented using ImageIcon.
Functionality
Mole and Plant Timers: Two Timer objects control the appearance of moles and plants on the grid. The mole appears every 500ms, and the plant every 550ms.
Action Listeners: Each button on the grid has an ActionListener to handle clicks. Clicking on a mole increases the score, while clicking on a plant ends the game.
Restart Functionality: The restart_game() method resets the score, clears the icons, re-enables the buttons, and restarts the timers.
