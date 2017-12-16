package pokemon.Player;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import pokemon.Cards.*;
import pokemon.Cards.TrainerCard.*;
import pokemon.Main.*;

public class Player {

	private ArrayList<Card> deck = new ArrayList<Card>();
	private String file;
	public PokemonCard[] pos = new PokemonCard[7];
	private ArrayList<Card> hand = new ArrayList<Card>();
	private int playerNum;

	public Player(String file, int playerNum) throws FileNotFoundException { //unfinished
		this.setPlayerNum(playerNum);
		this.file = file;
		FileReader f = new FileReader(file);
		BufferedReader br = new BufferedReader(f);
		String line;
		try {
			while ((line = br.readLine()) != null) {
				if(line.equals("Charmander")){
					Card charmander = new Charmander();
					deck.add(charmander);
				}
				else if(line == "Squirtle"){
					Card squirtle = new Squirtle();
					deck.add(squirtle);
				}
				else if(line.equals("Magikarp")){
					Card magikarp = new Magikarp();
					deck.add(magikarp);
				}
				else if(line.equals("Bulbasaur")){
					Card bulbasaur = new Bulbasaur();
					deck.add(bulbasaur);
				}
				else if(line.equals("Oddish")){
					Card oddish = new Oddish();
					deck.add(oddish);
				}
				else if(line.equals("Voltorb")){
					Card voltorb = new Voltorb();
					deck.add(voltorb);
				}
				else if(line.equals("Magnemite")){
					Card magnemite = new Magnemite();
					deck.add(magnemite);
				}
				else if(line.equals("Evolution Stone")){
					Card evolutionstone = new EvolutionStone();
					deck.add(evolutionstone);
				}
				else if(line.equals("Gust of Wind")){
					Card gustofwind = new GustOfWind();
					deck.add(gustofwind);
				}
				else if(line.equals("Help From Mom")){
					Card helpfrommom = new HelpFromMom();
					deck.add(helpfrommom);
				}
				else if(line.equals("HP Up")){
					Card hpup = new HPUp();
					deck.add(hpup);
				}
				else if(line.equals("Potion")){
					Card potion = new Potion();
					deck.add(potion);
				}
				else if(line.equals("Growlithe")){
					Card growlithe = new Growlithe();
					deck.add(growlithe);
				}

			}
		} catch (IOException e) {
			System.out.println("Sorry, a deck file does not exist. Game broken!");
		}



	}


	public ArrayList<Card> getDeck() {
		return deck;
	}
	public PokemonCard[] getPos() {
		return pos;
	}


	public String getFile() {
		return file;
	}
	public boolean makeMove(){
		System.out.println("Type the corresponding number to determine your move, Player " + getPlayerNum() + ".");
		System.out.println("1. Pass (ends turn)");
		System.out.println("2. Attack (ends turn)");
		System.out.println("3. View Board");
		System.out.println("4. View Hand");
		System.out.println("5. Play a Card");
		System.out.println("6. Move Pokemon on bench to the arena.");
		System.out.println("7. View enemy board.");
		Scanner s = new Scanner(System.in);
		String response = s.nextLine();
		if(response.equals("1")){ // PASS
			System.out.println("Ok, your turn has ended");
			return false;
		}
		else if(response.equals("2") || response.equals("attack")){ // ATTACK
			UI.printBoard(1);
			UI.printBoard(2);
			return UI.attack(this.getPlayerNum());

		} 
		else if(response.equals("3") || response.equals("print board")) { // VIEW BOARD
			UI.printBoard(this.getPlayerNum());
			System.out.println("Deck Size: " + deck.size());
			return true;
		}
		else if(response.equals("4") || response.equals("print hand")){ // VIEW HAND
			printHand();
			return true;
		}
		else if(response.equals("5") || response.equals("play card")){ // PLAY CARD
			printHand();
			boolean tryLoop = true;
			Play: while(tryLoop == true) {
				try {
					System.out.println("Which card would you like to play?");
					int b = s.nextInt() - 1;
					s.nextLine();
					if (hand.get(b).isPokemon() == true) { // checks if it's a pokemon. if it's not, your user is dumb
						if(pos[0] == null) {
							System.out.println("Since there is no Pokemon in the Arena, your Pokemon is placed there.");
							pos[0] = (PokemonCard) hand.get(b);
							hand.remove(b);
							break;
						}
						else {
							while(true) {
								System.out.println("Which spot on the bench should your Pokemon go?");
								int c = s.nextInt();
								s.nextLine();


								if (pos[c] == null) {
									pos[c] = (PokemonCard) hand.get(b);
									hand.remove(b);
									break Play;
								}
								else {
									System.out.println("There's already a Pokemon there!");
									break Play;
								}
							}
						}
					}
					else if (hand.get(b).isPokemon() == false) {
						if(((TrainerCard) hand.get(b)).getNeedsTarget() == true) {
							boolean inField = false; // makes sure that there is a target
							for(int i=0;i<pos.length;i++){
								if(pos[i] != null) {
									inField = true;
								}
							}
							if(inField == true){
								TrainerCard tc = (TrainerCard) hand.get(b);
								tc.ability(this); // this is a keyword that puts this object as an argument into the ability.
								hand.remove(b);
								break;
							}
							else {
								System.out.println("There is no target!");
								break;
							}
						}
						else {
							((TrainerCard) hand.get(b)).ability(this);
							hand.remove(b);
							break;

						}
					}
					else {
						System.out.println("You have entered bug territory");
					}

				}
				catch (Exception e) {
					System.out.println("Invalid input!");
					break Play;
				}
			}
			return true;
		}
		else if(response.equals("6") || response.equals("switch")) { // SWAP
			printBoard();
			int a = 0;
			while(true){
				System.out.println("Which bench position would you like to move into the arena? (Type 0 to cancel)");
				a = s.nextInt();
				s.nextLine();
				if(a == 0){
					break;
				}
				if (a > 6) {
					System.out.println("Pick a valid bench number!");
				}
				if (pos[a] == null) {
					System.out.println("There's not a Pokemon there!");
				}
				else {
					PokemonCard bench = pos[a];
					PokemonCard attack = pos[0];
					pos[0] = bench;
					pos[a] = attack;
					System.out.println("Alright, they have been switched!");
					break;
				}
			}




			return true;
		}
		else if(response.equals("7") || response.equals("print field")){ // VIEW ENEMY BOARD
			if(this.playerNum == 1){
				UI.printBoard(2);
			}
			if(this.playerNum == 2){
				UI.printBoard(1);
			}
			return true;
		}
		System.out.println("Invalid Input! Try again.");
		return true;
	}
	public void draw() {
		if(this.deck.size() == 0){
			System.out.println("A card was attempted to be drawn, but your deck is empty!");
			return;
		}
		this.hand.add(this.deck.get(0));


		System.out.println("Player " + getPlayerNum() + " has drawn a " +  deck.get(0).toString() + ".");
		this.deck.remove(0);
	}
	public void printBoard(){
		System.out.println("~~~~~~~~~~~~~~~Player " + getPlayerNum() + "'s Board~~~~~~~~~~~~~~~");
		for(int i=0;i<7;i++) {
			if(i == 0){
				System.out.println(pos[i] + " " + (pos[i] instanceof Electrode));
				if(pos[i] != null && (!(pos[i] instanceof Electrode)) ) {
					System.out.println("Arena: " + pos[i].toString() + " ( " + pos[i].getXP() + "XP, " + pos[i].getHP() + "HP, " + pos[i].getDmg() + "DMG)");
				}
				else if(pos[i] != null &&(pos[i] instanceof Electrode)) {
					System.out.println("Arena: " + pos[i].toString() + " ( " + pos[i].getXP() + "XP, " + pos[i].getHP() + "HP, 1-hit-KO DMG)");
				}
				else {
					System.out.println("Arena:");
				}

			}

			else {
				if(pos[i] != null && (!(pos[i] instanceof Electrode))) {
					System.out.println(i + ": " + pos[i].toString() + " ( " + pos[i].getXP() + "XP, " + pos[i].getHP() + "HP, " + pos[i].getDmg() + "DMG)");
				}
				else if(pos[i] != null &&(pos[i] instanceof Electrode)) {
					System.out.println("Arena: " + pos[i].toString() + " ( " + pos[i].getXP() + "XP, " + pos[i].getHP() + "HP, 1-hit-KO DMG [note: Electrode deals 60 damage to ITSELF on attack])");
				}
				else {
					System.out.println(i + ":");
				}
			}
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
	public void printHand(){
		System.out.println("~~~~~~~~~~~~~~~Player " + getPlayerNum() + "'s Hand~~~~~~~~~~~~~~~");
		for(int i=0;i<hand.size();i++){
			System.out.println(i+1 + ": " + this.hand.get(i).toString());
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	public void giveXP() {
		for(int i=0;i<7;i++) {
			if(pos[i] != null){
				pos[i].giveXP();
				if(pos[i].getXP() >= 3) {
					if (pos[i].getEvo() != null) {
						pos[i].getEvo().setHP(pos[i].getEvo().getHP() - (pos[i].getTaken())); // carries over dmg during evolve
						String oldPokemon = pos[i].toString();
						pos[i] = pos[i].getEvo();
						System.out.println(oldPokemon + " has evolved into a " + pos[i].toString() + ".");

					}
				}
			}
		}
	}


	public int getPlayerNum() {
		return playerNum;
	}


	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}
}