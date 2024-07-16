package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * this class holds a list of monsters in the game and it take care of the conflict between monsters.
 * Parsa Keikhosravani 3013572013
 * Jaskaran Grewal 301381928
 */
public class GroupofMonsters {
    private List<Monster> TheMonsters= new ArrayList<Monster>();
    private int numberofmonsterstokill=3;

    public int getNumberofmonsterstokill() {
        return numberofmonsterstokill;
    }

    public void setNumberofmonsterstokill(int numberofmonsterstokill) {
        this.numberofmonsterstokill = numberofmonsterstokill;
    }

    public GroupofMonsters() {
        TheMonsters.add(new Monster(1,13));
        TheMonsters.add(new Monster(18,1));
        TheMonsters.add(new Monster(18,13));
    }

    public int getsize(){
        return TheMonsters.size();
    }

    public void move(char c){
        if(c=='m' || c=='M'){
            RevealMap();
            return;
        }
        if(c=='c'||c=='C'){
            numberofmonsterstokill=1;
            return;
        }
        Hero TheHero =Hero.getInstance();
       // TheHero.unlockcells();
        TheHero.move(c);
        for(int i=0; i<TheMonsters.size();i++) {
            TheMonsters.get(i).Monster_Move();
        }
        if(NumofConflict()!=0){
            SolveConflict();
        }
    }

    public void RevealMap() {
        Hero TheHero =Hero.getInstance();
        SuperPower ThePower = SuperPower.getInstance();
        Maze TheMaze= Maze.getInstance();
        for(int i=0; i<TheMaze.getHeight();i++){
            for(int j=0; j<TheMaze.getWidth(); j++){

                if(TheMaze.MazeGrid2[i][j]==' ' || TheMaze.MazeGrid2[i][j]=='.'|| TheMaze.MazeGrid2[i][j]=='$'){
                    TheMaze.MazeGrid[i][j]=' ';
                    TheMaze.MazeGrid2[i][j]=' ';
                }
                else if(TheMaze.MazeGrid2[i][j]=='#'){
                    TheMaze.MazeGrid[i][j]='#';
                }
            }
        }
        TheMaze.MazeGrid[ThePower.getVertical()][ThePower.getHorizontal()]='$';
        TheMaze.MazeGrid2[ThePower.getVertical()][ThePower.getHorizontal()]='$';
        TheMaze.MazeGrid[TheHero.getVertical()][TheHero.getHorizontal()]='@';
        TheMaze.MazeGrid2[TheHero.getVertical()][TheHero.getHorizontal()]='@';
        for(int i=0; i<TheMonsters.size(); i++){
            if(TheMaze.MazeGrid[TheMonsters.get(i).getVertical()][TheMonsters.get(i).getHorizontal()]==TheMaze.MazeGrid[TheHero.getVertical()][TheHero.getHorizontal()] && !TheHero.isAlive()){
                TheMaze.MazeGrid[TheMonsters.get(i).getVertical()][TheMonsters.get(i).getHorizontal()]='X';
            }
            else{
                TheMaze.MazeGrid[TheMonsters.get(i).getVertical()][TheMonsters.get(i).getHorizontal()]='!';
            }
        }

    }


    public int NumofConflict(){
        Hero TheHero= Hero.getInstance();
        int numconflicts =0;
        for(int i=0; i<TheMonsters.size();i++){
            if(TheHero.getHorizontal()==TheMonsters.get(i).getHorizontal() && TheHero.getVertical()==TheMonsters.get(i).getVertical()){
                numconflicts++;
            }
        }
        return numconflicts;
    }
    public void SolveConflict(){
        Maze TheMaze = Maze.getInstance();
        Hero TheHero = Hero.getInstance();
        if(NumofConflict()>TheHero.getNumpowers()){
            TheHero.setAlive(false);
            TheMaze.MazeGrid[TheHero.getVertical()][TheHero.getHorizontal()]='X';
            return;
        }
        else{
            int powers = TheHero.getNumpowers();
            for(int i=TheMonsters.size()-1; i>=0;i--){
                if(TheHero.getHorizontal()==TheMonsters.get(i).getHorizontal() && TheHero.getVertical()==TheMonsters.get(i).getVertical()){
                    TheMonsters.remove(i);
                    powers--;
                    TheHero.setNumpowers(powers);
                    numberofmonsterstokill--;
                    TheMaze.MazeGrid[TheHero.getVertical()][TheHero.getHorizontal()]='@';
                }
            }
        }
    }

}
