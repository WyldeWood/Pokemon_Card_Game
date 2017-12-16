# pokemon
HSE Pokemon Project
Junho Moon / Andy Wood
October, 2015

A. Instructions
Place your two deck files (.txt) in the Pokemon directory where src and bin are located. The deck files should only include these following cards:
	Charmander
	Squirtle
	Bulbasaur
	Voltorb
	Potion
	HP UP
	Help from Mom
	Evolution Stone
	Gust of Wind
However, the cards can be repeated and/or be organized in any order. Once the file is ready, run the program. The program begins by asking for you and your opponent’s name and the two deck files. If entered correctly, the program will register the all information and begin the game itself. Once each turn is over, pass the computer to your opponent. The play continues back and forth until one player is attacked without a Pokemon in the arena, losing the game.

B. Overview of Design and Function
The Card superclass is extended by PokemonCard and TrainerCard. Deck constructs Deck in JunhosUserInterface, which assigns the newly constructed decks to Player. In UserInterface, classes such as Pokemon, Trainer Cards, ElementalTypes (Attack), and ArenaBench are called and connected to one another to create an user-friendly interface. Outside UserInterface, each Pokemon class (Charmander, Squirtle, et al.) contains specific information such as AttackStat and MaxHP, which are ultimately, again, called in UserInterface through the PokemonCard class.

C. Possible Bugs or Problems
Decks with less than 6 cards cannot be used in the program. Contact Junho Moon or Andy Wood if any more problems are found.

D. Extra Features
In UserInterface, using ‘print field’ and ‘print hand’ prints cards with descriptions in [] form. For example, a Pokemon in a field would be printed its name, type, HP, attack name and stat, and experience. In addition, option ‘help’ is added to resolve confusions. Overall improved user experience.