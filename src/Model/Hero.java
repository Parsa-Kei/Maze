package Model;

/**
 * this is our Hero class. This class holds the coordinates and state of the hero and also contains the move function for hero.
 * Parsa Keikhosravani 3013572013
 * Jaskaran Grewal 301381928
 */
public class Hero {
    private boolean isAlive = true;
    private int numpowers=0;
    private int horizontal=1;
    private int vertical=1;

    private static Hero instance;
    public static Hero getInstance(){
        if(instance==null){
            instance= new Hero();
        }
        return instance;
    }


    private Hero() {

    }
    public int getNumpowers() {
        return numpowers;
    }

    public void setNumpowers(int numpowers) {
        this.numpowers = numpowers;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
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
    public void move(char X){
        int tmpvertical = vertical;
        int tmphorizontal= horizontal;
        Maze OurMaze = Maze.getInstance();
        if(X=='w' || X=='W'){
            vertical--;
        }
        if(X=='s' || X=='S'){
            vertical++;
        }
        if(X=='d'||X=='D'){
            horizontal++;
        }
        if(X=='A'||X=='a'){
            horizontal--;
        }
        if(OurMaze.MazeGrid[vertical][horizontal]=='$'){
            numpowers++;
            OurMaze.GenerateNewPower();
        }
        OurMaze.MazeGrid[vertical][horizontal]='@';
        OurMaze.MazeGrid2[vertical][horizontal]='@';
        //OurMaze.MazeGrid2[vertical][horizontal]='@';
        OurMaze.MazeGrid[tmpvertical][tmphorizontal]=' ';
        OurMaze.MazeGrid2[tmpvertical][tmphorizontal]=' ';

    }

    public void UnlockCells(){
        Maze TheMaze = Maze.getInstance();
        int VERTICAL=vertical+1;
        int HORIZONTAL=horizontal+1;
        int VERTICALneg=vertical-1;
        int HORIZONTALneg=horizontal-1;

        if(TheMaze.MazeGrid[vertical][HORIZONTAL]=='!' || TheMaze.MazeGrid[VERTICAL][horizontal]=='!' || TheMaze.MazeGrid[vertical][HORIZONTALneg]=='!' ||TheMaze.MazeGrid[VERTICALneg][horizontal]=='!'){
            return;
        }

        if(TheMaze.MazeGrid2[vertical][HORIZONTAL]=='.' || TheMaze.MazeGrid2[vertical][HORIZONTAL]==' '){
            TheMaze.MazeGrid2[vertical][HORIZONTAL]=' ';
            TheMaze.MazeGrid[vertical][HORIZONTAL]=' ';
        }
        if(TheMaze.MazeGrid2[vertical][HORIZONTAL]=='#'){
            TheMaze.MazeGrid[vertical][HORIZONTAL]='#';
        }
        if(TheMaze.MazeGrid2[VERTICAL][horizontal]=='.' || TheMaze.MazeGrid2[VERTICAL][horizontal]==' '){
            TheMaze.MazeGrid2[VERTICAL][horizontal]=' ';
            TheMaze.MazeGrid[VERTICAL][horizontal]=' ';
        }
        if(TheMaze.MazeGrid2[VERTICAL][horizontal]=='#'){
            TheMaze.MazeGrid[VERTICAL][horizontal]='#';
        }



        if(TheMaze.MazeGrid2[vertical][HORIZONTALneg]=='.' || TheMaze.MazeGrid2[vertical][HORIZONTALneg]==' '){
            TheMaze.MazeGrid2[vertical][HORIZONTALneg]=' ';
            TheMaze.MazeGrid[vertical][HORIZONTALneg]=' ';
        }
        if(TheMaze.MazeGrid2[vertical][HORIZONTALneg]=='#'){
            TheMaze.MazeGrid[vertical][HORIZONTALneg]='#';
        }
        if(TheMaze.MazeGrid2[VERTICALneg][horizontal]=='.' || TheMaze.MazeGrid2[VERTICALneg][horizontal]==' '){
            TheMaze.MazeGrid2[VERTICALneg][horizontal]=' ';
            TheMaze.MazeGrid[VERTICALneg][horizontal]=' ';
        }
        if(TheMaze.MazeGrid2[VERTICALneg][horizontal]=='#'){
            TheMaze.MazeGrid[VERTICALneg][horizontal]='#';
        }
    }

    public boolean CheckifMoveisLegal(char X){
        int tempvertical=vertical;
        int temphorizontal= horizontal;
        Maze OurMaze = Maze.getInstance();
        if(X=='w' || X=='W'){
            vertical--;
        }
        if(X=='s' || X=='S'){
            vertical++;
        }
        if(X=='d'||X=='D'){
            horizontal++;
        }
        if(X=='A'||X=='a'){
            horizontal--;
        }
        if(OurMaze.MazeGrid[vertical][horizontal]=='#'){
            vertical=tempvertical;
            horizontal=temphorizontal;
            return false;
        }
        vertical=tempvertical;
        horizontal=temphorizontal;
        return true;
    }
}
