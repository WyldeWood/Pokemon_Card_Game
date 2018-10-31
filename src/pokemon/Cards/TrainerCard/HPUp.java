package pokemon.Cards.TrainerCard;

import java.util.Scanner;

import pokemon.Player.Player;

public class HPUp extends TrainerCard {



	public HPUp() {
		super("HP Up");
		needsTarget = true;
	}

	public void ability(Player p) {
		Scanner s = new Scanner(System.in);
		System.out.println("Which board position is your target?");
		String inp = s.nextLine();
		int in = 0;

		if (inp.equals("A")){
			in = 0;
		}
		else {
			while(true){
				try { 
					in = Integer.parseInt(inp);
					System.out.println(in);
					if(in > 6 || in < 1){
						throw new NumberFormatException();
					}
					break;
				}

				catch (NumberFormatException e){
					System.out.println("Invalid input!");
					break;
				}
			}
		}
		p.pos[in].setHP(p.pos[in].getHP()+20); // adds 20 hp
		p.pos[in].HPUp();
	}
}
