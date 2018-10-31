package pokemon.Cards.TrainerCard;

import pokemon.Player.Player;

public class HelpFromMom extends TrainerCard {

	public HelpFromMom() {
		super("Help From Mom");
		// TODO Auto-generated constructor stub
		needsTarget = false;
	}

	@Override
	public void ability(Player p) {
		System.out.println("Mom has helped you!");
		p.draw();
		p.draw();
		p.draw();
		
	}



}
