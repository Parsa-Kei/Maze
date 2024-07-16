package UI;

import Model.GroupofMonsters;
import Model.Hero;

/**
 * This is the Menu class which handles the majority of the UI components. Shows the options to the user, shows the game guideline
 * and warns the user incase of an invalid input
 * Parsa Keikhosravani 3013572013
 * Jaskaran Grewal 301381928
 */
public class Menu {


    public void ShowtheMessageIntheBeginning(){
        System.out.println("Directions:");
        System.out.println("     Kill 3 monsters!");
        System.out.println("Legends:");
        System.out.println("     #: Wall");
        System.out.println("     @: You (a hero)");
        System.out.println("     !: Monster");
        System.out.println("     $: Power");
        System.out.println("     .: Unexpected space");
        System.out.println("Moves");
        System.out.println("     Use W (up), A (left), S (down) and D (right) to move.");
        System.out.println("     (You must press enter after each move).");
    }

    public void ShowOptions(Hero TheHero, GroupofMonsters Group){
        System.out.println("Total number of monsters to be killed: "+ Group.getNumberofmonsterstokill());
        System.out.println("Number of powers currently in possession: "+ TheHero.getNumpowers());
        System.out.println("Number of monsters alive: " + Group.getsize());
        System.out.println("Enter your move [WASD?]:");
    }

    public void WrongMove(){
        System.out.println("Invalid move: you cannot move through walls!");
        System.out.println("Enter your move [WASD?]:");
    }
}
