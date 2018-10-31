package pokemon.Cards.TrainerCard;
import java.util.Scanner;

import pokemon.Player.Player;

public class EvolutionStone extends TrainerCard {

	public EvolutionStone() {
		super("Evolution Stone");
	}


	public void ability(Player p) {
		System.out.println("Choose a Pokemon to evolve. Type 'A' to evolve the pokemon in the Arena.");
		Scanner s = new Scanner(System.in);
		p.printBoard();
		String in = s.nextLine();
		int x;
		

		if(in.equals("A")) {
			x = 0;
		}
		else { 
			while(true){
				try { 
					x = Integer.parseInt(in);
					System.out.println(x);
					if(x > 6 || x < 1){
						throw new NumberFormatException();
					}
					break;
				}

				catch (NumberFormatException e){
					System.out.println("Invalid input!");
				}
			}
		}
		if (p.pos[x] == null) {
			System.out.println("There's no Pokemon in this position, card wasted!");
		}
		if (p.pos[x].getEvo() != null) {
			p.pos[x].getEvo().setHP(p.pos[x].getEvo().getHP() - (p.pos[x].getTaken())); // carries over dmg during evolve
			String oldPokemon = p.pos[x].toString();
			p.pos[x] = p.pos[x].getEvo();
			p.pos[x].setHasEvolved(false);
			System.out.println(oldPokemon + " has evolved into a " + p.pos[x].toString() + ".");
		}
		else {
			System.out.println("Evolution Stone unusable! Card wasted!");
		}
		
	}


}
