package pokemon.Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import pokemon.Cards.*;
import pokemon.Cards.TrainerCard.*;
import pokemon.Player.*;


public class UI {
	static Player p1;
	static Player p2;

	public static void main(String[] args) throws IOException {

		System.out.println("Welcome to Pokemon!");
		try { // takes in a text file for deck and a player number that shouldn't be changed or bad things happen
			p1 = new Player("p1.txt", 1);

			p2 = new Player("p2.txt", 2);
		}
		catch (FileNotFoundException e){
			System.out.println("Game failure! Deck file not found.");

		}
		UI.printBoard(1);
		//variables initiated to repeat player moves if necessary
		boolean repeatp1 = true;
		boolean repeatp2 = true;
		for(int i=0;i<5;i++){
			p1.draw();
		}
		for(int i=0;i<5;i++){
			p2.draw();
		}

		while(true) {
			p1.draw();
			p1.giveXP();
			//repeat as long as player does not attack or pass.
			while(repeatp1 != false){

				repeatp1 = p1.makeMove();
			}
			repeatp1 = true;
			p2.draw();
			p2.giveXP();
			while(repeatp2 != false){
				repeatp2 = p2.makeMove();
			}
			repeatp2 = true;
		}

	}

	public static void printBoard(int playerNum){
		if(playerNum == 1){
			p1.printBoard();
		}else{
			p2.printBoard();
		}

	}

	public static boolean attack(int playerNum) {
		Player a = null;
		Player d = null;
		int before = 0;
		int after = 0;

		if(playerNum == 1){
			a = p1;
			d= p2;
		}
		else if(playerNum == 2){
			a = p2;
			d = p1;
		}
		else{ 
			System.out.println("You have entered bug-land!");
			System.exit(1);
		}

		if(a.pos[0] == null) {
			System.out.println("You cannot attack, you do not have a Pokemon in the arena!");
			return true;
		}

		else {
			if((a.pos[0].getXP() == 0) && (a.pos[0].getHasEvolved() == false)){
				System.out.println(a.pos[0].toString() + " was just played and cannot attack!");
				return true;
			}
			else {
				if(d.pos[0] == null) {
					System.out.println("There is no enemy Pokemon in the Arena!");
					victory(a);
				}
				before = d.pos[0].getHP();
				if(a.pos[0] instanceof Electrode) { // special case for Electrodes attacking
					d.pos[0].setHP(0);
					System.out.println("BOOM! 1-hit-KO! Electrode takes 60 damage.");
					a.pos[0].setHP(a.pos[0].getHP() - 60);

				}
				System.out.println(a.pos[0].toString() + " uses " + a.pos[0].getAttack() + " on " + d.pos[0].toString() + ".");
				int atk = a.pos[0].getDmg();
				d.pos[0].setHP(d.pos[0].getHP() - atk);
				Type aType = a.pos[0].getType();
				Type dType = d.pos[0].getType();


				if(aType == Type.fire) {
					if(dType == Type.grass) {
						d.pos[0].superEffective();
						System.out.println("It's super effective! Attack hits for " + (atk + 20) + ".");
					}

					if(dType == Type.water) {
						if(atk > 20) {
							d.pos[0].notEffective();
							System.out.println("It's not very effective. Attack hits for " + (atk - 20));
						}
						else { // prevents low-damage attacks from healing
							System.out.println("It's not very effective. Attack hits for 0.");
						}
					}
				}

				if(aType == Type.grass) {
					if(dType == Type.water) {
						d.pos[0].superEffective();
						System.out.println("It's super effective! Attack hits for " + (atk + 20) + ".");
					}

					if(dType == Type.fire) {
						if(atk > 20) {
							d.pos[0].notEffective();
							System.out.println("It's not very effective. Attack hits for " + (atk - 20));
						}
						else { // prevents low-damage attacks from healing
							System.out.println("It's not very effective. Attack hits for 0.");
						}
					}


				}

				if(aType == Type.water) {
					if(dType == Type.fire) {
						d.pos[0].superEffective();
						System.out.println("It's super effective! Attack hits for " + (atk + 20) + ".");
					}

					if(dType == Type.grass) {
						if(atk > 20) {
							d.pos[0].notEffective();
							System.out.println("It's not very effective. Attack hits for " + (atk - 20));
						}
						else { // prevents low-damage attacks from healing
							System.out.println("It's not very effective. Attack hits for 0.");
						}
					}

				}

				if(aType == Type.electric) {
					if(dType == Type.water) {
						d.pos[0].superEffective();
						System.out.println("It's super effective! Attack hits for " + (atk + 20) + ".");
					}

				}
			}
		}
		after = d.pos[0].getHP();

		d.pos[0].setTaken(before - after);

		if(d.pos[0].getHP() <= 0) {
			System.out.println(d.pos[0].toString() + " has fainted!");
			d.pos[0] = null;


		}
		return false;
	}

	public static void victory(Player p){
		System.out.println("Player " + p.getPlayerNum() + " has won! Game closing...");
		System.exit(0);
	}
	public static Player p1(){
		return p1;
	}

	public static Player p2(){
		return p2;
	}
}
