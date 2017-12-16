package pokemon.Cards;
import pokemon.Cards.TrainerCard.*;
import pokemon.Player.*;
import pokemon.Main.*;

public abstract class PokemonCard extends Card{
	protected String nm;
	protected Type type;
	protected int HP;
	protected int dmg;
	protected String attack;
	protected PokemonCard evo;
	protected int xp;
	protected int taken;
	protected boolean hasEvolved;

	public PokemonCard(String nm, Type type, int HP, int dmg, String attack, PokemonCard evo, int xp, boolean hasEvolved) {
		this.nm = nm;
		this.type = type;
		this.HP = HP;
		this.dmg = dmg;
		this.attack = attack;
		this.evo = evo;
		this.xp = xp;
		this.taken = 0;
		this.hasEvolved = hasEvolved;
	
		
	}
	
	public String toString(){
		return this.nm;
	}
	
	public void superEffective(){

		if (this.HP > (this.HP + this.taken)) {
			this.HP = this.HP + this.taken;
		}
		else {
			this.HP = this.HP + 20;
		}
		
	}
	
	public void setXP(int x) {
		this.xp = x;
	}
	
	public void notEffective() {
		this.HP = this.HP - 20;
	}
	
	public int getDmg() {
		return this.dmg;
	}
	
	
	public void HPUp(){
		this.taken = this.taken + 20;
	}
	
	public int getHP() {
		return this.HP;
	}
	
	public int getTaken() {
		return this.taken;
	}
	public void setTaken(int i) {
		this.taken = i;
	}
	public int getXP() {
		return this.xp;
	}
	
	public boolean getHasEvolved() {
		return this.hasEvolved;
	}
	
	public void setHasEvolved(boolean b){
		this.hasEvolved = b;
	}
	
	public PokemonCard getEvo(){
		return this.evo;
	}
	
	public void setHP(int x) {
		this.HP = x;
	}
	
	public void giveXP() {
		this.xp++;
	}
	public boolean isPokemon() {
		return true;
	}
	public Type getType(){
		return this.type;
	}
	
	public String getAttack() {
		return this.attack;
	}
	//public void play(int pos, pokemon.Player.Player p) { //puts the card in position pos on player p's field

	
}
