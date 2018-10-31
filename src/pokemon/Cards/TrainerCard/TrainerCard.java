package pokemon.Cards.TrainerCard;

import pokemon.Cards.Card;
import pokemon.Cards.PokemonCard;
import pokemon.Cards.Type;
import pokemon.Player.*;
import pokemon.Main.*;

public abstract class TrainerCard extends Card{
	protected String nm;
	protected boolean needsTarget;
	protected int input;
	public boolean isPokemon() {
		return false;
	}

	public TrainerCard(String nm) {
		this.nm = nm;
	}
	//This method will not return anything but will make calls easier in the UI as it is a unified method.
	public abstract void ability(Player p);
	
	public String toString() {
		return this.nm;
	}
	
	public boolean getNeedsTarget(){
		return this.needsTarget;
	}
	
	
}


