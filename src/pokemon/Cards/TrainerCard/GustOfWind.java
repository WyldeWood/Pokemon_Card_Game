package pokemon.Cards.TrainerCard;

import java.util.Scanner;

import pokemon.Cards.PokemonCard;
import pokemon.Main.UI;
import pokemon.Player.Player;

public class GustOfWind extends TrainerCard {


	public GustOfWind() {
		super("Gust of Wind");
		// TODO Auto-generated constructor stub
		needsTarget = true;
	}

	@Override
	public void ability(Player p) {
		int x = 0;
		Scanner s = new Scanner(System.in);

		if(p.getPlayerNum() == 1){
			UI.p2().printBoard();
		
		}
		if(p.getPlayerNum() == 2){
			UI.p1().printBoard();
		}
		
		System.out.println("Please select an ENEMY board position to put in the Arena.");
		String swap = s.nextLine();
		while(true){
			if(swap.equals("A")) {
				System.out.println("You must pick a position on the bench! Card discarded.");
				return;
			}
			try { 
				x = Integer.parseInt(swap); 
				if(x > 6) {
					throw new Exception();
				}
				if(x <= 0) {
					throw new Exception();
				}
			}
			catch (Exception e) {
				System.out.println("Invalid input!");
				break;
			}
			if(p.getPlayerNum() == 1) {
				PokemonCard target = UI.p2().pos[x];
				if(target == null) {
					System.out.println("No target in that position, card wasted!");
					return;
				}
				else {
					UI.p2().pos[x] = UI.p2().pos[0];
					UI.p2().pos[0] = target;
				}
			}
			if(p.getPlayerNum() == 2){
				PokemonCard target = UI.p1().pos[x];
				if(target == null) {
					System.out.println("No target in that position, card wasted!");
					return;
				}
				else {
					UI.p1().pos[x] = UI.p2().pos[0];
					UI.p1().pos[0] = target;
				}
				
			}

		}
	}


}
