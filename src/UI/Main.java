package UI;

import Model.GroupofMonsters;
import Model.Hero;
import Model.Maze;
import Model.Monster;

import java.util.Scanner;
/**
 * this is the Main class which contains the main function.
 * Parsa Keikhosravani 3013572013
 * Jaskaran Grewal 301381928
 */
public class Main {
    public static void main(String[] args){
        Menu ourMenu= new Menu();
        Maze Map = Maze.getInstance();
       // Map.MazeGenerator(0,0,);
        Hero OurHero = Hero.getInstance();
        boolean stay = true;
        GroupofMonsters OurMonsters = new GroupofMonsters();
        Scanner in = new Scanner(System.in);
        ourMenu.ShowtheMessageIntheBeginning();
        char c;


        while(stay){
            while(OurHero.isAlive() && OurMonsters.getNumberofmonsterstokill()>0) {
                OurHero.UnlockCells();
                Map.Draw();

                ourMenu.ShowOptions(OurHero,OurMonsters);
                c = in.next().charAt(0);
                while(!OurHero.CheckifMoveisLegal(c)){
                    ourMenu.WrongMove();
                    c = in.next().charAt(0);
                }
                OurMonsters.move(c);
            }
            if(!OurHero.isAlive()){
                OurMonsters.RevealMap();
                Map.Draw();
            }
            if(OurMonsters.getNumberofmonsterstokill()<=0){
                OurMonsters.RevealMap();
                Map.Draw();
            }
            stay=false;
        }
    }
}
