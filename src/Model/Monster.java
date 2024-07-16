package Model;

import java.util.Random;

/**
 * this is our Monster class which takes care o the movement of a monster and holds the current and previous coordinates of a monster.
 * and this class is responsible for The AI of the monsters so that they wouldn't retrace their steps only if they have to.
 * Parsa Keikhosravani 3013572013
 * Jaskaran Grewal 301381928
 */
public class Monster {
    private boolean isisAlive = true;
    private int horizontal;
    private int vertical;
    private int previous_vertical;
    private int previous_horizontal;
    private char previous_char='.';



    public Monster(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    public boolean isIsisAlive() {
        return isisAlive;
    }

    public void setIsisAlive(boolean isisAlive) {
        this.isisAlive = isisAlive;
    }





    public void Monster_Move() {

        Maze OurMaze = Maze.getInstance();
        if(Thereisonlyonemove()){// the only case of back tracking

            MakeTheOnlyMove();
            return;
        }
        Random move= new Random();
        int direction;
        int tempovertivcal;
        int tempohorizontal;
        do{
            tempohorizontal=horizontal;
            tempovertivcal=vertical;
            direction=move.nextInt(4);
            if(direction==0){
                tempovertivcal=vertical+1;
            }
            if(direction==1){
                tempovertivcal=vertical-1;
            }
            if(direction==2){
                tempohorizontal=horizontal+1;
            }
            if(direction==3){
                tempohorizontal=horizontal-1;
            }

        }while(OurMaze.MazeGrid[tempovertivcal][tempohorizontal]=='#' || ((tempohorizontal==previous_horizontal)&& (tempovertivcal==previous_vertical)) );
        sawphorizontalvalues();
        swapverticalvalues();

        OurMaze.MazeGrid[previous_vertical][previous_horizontal] = OurMaze.MazeGrid2[previous_vertical][previous_horizontal];

        vertical=tempovertivcal;
        horizontal=tempohorizontal;
        //previous_char = OurMaze.MazeGrid[vertical][horizontal];
        OurMaze.MazeGrid[vertical][horizontal]='!';

    }

    public void swapverticalvalues(){
        int tmp =vertical;
        vertical=previous_vertical;
        previous_vertical= tmp;
    }

    public void sawphorizontalvalues(){
        int tmp =horizontal;
        horizontal=previous_horizontal;
        previous_horizontal= tmp;
    }

     public boolean Thereisonlyonemove() {

        Maze test = Maze.getInstance();
        int nummoves=0;
        int tmpverticalpos=vertical+1;
        int tmphorizontalpos=horizontal+1;
        int tmpverticalneg=vertical-1;
        int tmphorizontalneg=horizontal-1;
        if(test.MazeGrid[vertical][tmphorizontalpos]=='#'){//horizontal++
            nummoves++;
        }
        if(test.MazeGrid[tmpverticalpos][horizontal]=='#'){//vertical++
            nummoves++;
        }
        if(test.MazeGrid[vertical][tmphorizontalneg]=='#'){//horizontal--
            nummoves++;
        }
        if(test.MazeGrid[tmpverticalneg][horizontal]=='#'){//vertical--
            nummoves++;
        }
        if(nummoves>=3){
            return true;
        }

        return false;
     }
     public void MakeTheOnlyMove(){
         Maze test = Maze.getInstance();
         int nummoves=0;
         previous_vertical=vertical;
         previous_horizontal=horizontal;
         int tmpverticalpos=vertical+1;
         int tmphorizontalpos=horizontal+1;
         int tmpverticalneg=vertical-1;
         int tmphorizontalneg=horizontal-1;
         if(test.MazeGrid[vertical][tmphorizontalpos]!='#'){
             horizontal=tmphorizontalpos;
         }
         if(test.MazeGrid[tmpverticalpos][horizontal]!='#'){
             vertical=tmpverticalpos;
         }
         if(test.MazeGrid[vertical][tmphorizontalneg]!='#'){
             horizontal=tmphorizontalneg;
         }
         if(test.MazeGrid[tmpverticalneg][horizontal]!='#'){
             vertical=tmpverticalneg;
         }

         test.MazeGrid[previous_vertical][previous_horizontal] = test.MazeGrid2[previous_vertical][previous_horizontal];
         test.MazeGrid[vertical][horizontal]='!';
        // swapverticalvalues();
         //fgsawphorizontalvalues();
     }

}
