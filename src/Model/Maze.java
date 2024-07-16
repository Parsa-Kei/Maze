package Model;

import java.util.Random;

/**
 * this is our Maze class which has the maze generating function. and contains the Maze grid of the game.
 * Parsa Keikhosravani 3013572013
 * Jaskaran Grewal 301381928
 */
public class Maze {
    private static Maze instance;
    private final int width = 20;
    private final int height =15;
    public char[][] MazeGrid;
    public char[][] MazeGrid2;

    public static Maze getInstance(){
        if(instance==null){
            instance= new Maze();
        }
        return instance;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private Maze(){
        SuperPower The_Power= SuperPower.getInstance();
        MazeGrid= new char[height][width];
        for(int i=0;i<height; i++){
            for(int j=0; j<width; j++){

                if(i==0 || i==14 || j==0 || j==19 ){
                    MazeGrid[i][j]='#';
                }
                else {
                    MazeGrid[i][j]='.';
                }
            }
        }
        MazeGrid[1][1]='@';
        //MazeGrid[4][1]='$';
        The_Power.Generate(MazeGrid);
        MazeGrid[13][1]='!';
        MazeGrid[13][18]='!';
        MazeGrid[1][18]='!';
        MazeGrid[The_Power.getVertical()][The_Power.getHorizontal()]='$';

        MazeGrid2= new char[height][width];
        for(int i=0;i<height; i++) {
            for (int j = 0; j < width; j++) {

                if (i == 0 || i == 14 || j == 0 || j == 19) {
                    MazeGrid2[i][j] = '#';
                } else {
                    MazeGrid2[i][j] = ' ';
                }
            }
        }
      //  MazeGrid[13][2]='#';

    /*    //MazeGrid2[1][1]='@';
        //MazeGrid2[4][1]='$';
        while(IsThereaSquate()) {
            for(int i=0;i<height; i++) {
                for (int j = 0; j < width; j++) {

                    if (i == 0 || i == 14 || j == 0 || j == 19) {
                        MazeGrid2[i][j] = '#';
                    } else {
                        MazeGrid2[i][j] = ' ';
                    }
                }
                //MazeGenerator(0, 19, 0, 14);
            }

        }*/
        MazeGenerator(0, 19, 0, 14);
        ClearTheCorners();
        MazeGrid2[The_Power.getVertical()][The_Power.getHorizontal()]='$';
        RandomlyRemove();

    }
    public void GenerateNewPower(){
        SuperPower The_Power= SuperPower.getInstance();
        The_Power.Generate(MazeGrid);
        MazeGrid[The_Power.getVertical()][The_Power.getHorizontal()]='$';
        MazeGrid2[The_Power.getVertical()][The_Power.getHorizontal()]='$';
    }

    public void Draw(){
        for(int i=0; i< height;i++){
            System.out.println(MazeGrid[i]);
        }

   //     for(int i=0; i< height;i++){
        //    System.out.println(MazeGrid2[i]);
      //  }
    }

    private void ClearTheCorners(){
        MazeGrid2[1][1]='@';
        MazeGrid2[1][18]='.';
        MazeGrid2[13][1]='.';
        MazeGrid2[13][18]='.';
        for(int i=0; i<height;i++){
            for(int j=0; j<width;j++){
                if(MazeGrid2[i][j]==' '){
                    MazeGrid2[i][j]='.';
                }
            }
        }
    }

    public void RandomlyRemove(){
        int i=0;
        int j=0;
        int north;
        int south;
        int east;
        int west;
        int northeast;
        int northwest;
        int southeast;
        int southwest;
        int VerticalRandom;
        int HorizontalRandom;
        while(i<5){
            Random coordinate = new Random();
            HorizontalRandom =2+ coordinate.nextInt(16);
            VerticalRandom= 2+ coordinate.nextInt(11);
            north= VerticalRandom-1;
            south= VerticalRandom+1;
            east = HorizontalRandom+1;
            west = HorizontalRandom-1;
            if(MazeGrid2[VerticalRandom][HorizontalRandom]=='#'){
                if( (MazeGrid2[north][HorizontalRandom]=='#' || MazeGrid2[north][east]=='#' || MazeGrid2[VerticalRandom][east]=='#') &&
                        (MazeGrid2[north][HorizontalRandom]=='#' || MazeGrid2[north][west]=='#' || MazeGrid2[VerticalRandom][west]=='#')&&
                        (MazeGrid2[south][HorizontalRandom]=='#' || MazeGrid2[south][west]=='#' || MazeGrid2[VerticalRandom][west]=='#')&&
                        (MazeGrid2[south][HorizontalRandom]=='#' || MazeGrid2[south][east]=='#' || MazeGrid2[VerticalRandom][east]=='#')){

                            MazeGrid2[VerticalRandom][HorizontalRandom] = '.';
                            i++;
                }
            }

        }
    }

    public boolean IsThereaSquare() {
        int north;
        int south;
        int east;
        int west;

        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                north = i - 1;
                south = i + 1;
                east = j + 1;
                west = j - 1;
                if (MazeGrid2[i][j] == '#') {
                    if ((MazeGrid2[north][j] == '#' && MazeGrid2[north][east] == '#' && MazeGrid2[i][east] == '#') ||
                            (MazeGrid2[north][j] == '#' && MazeGrid2[north][west] == '#' && MazeGrid2[i][west] == '#') ||
                            (MazeGrid2[south][j] == '#' && MazeGrid2[south][west] == '#' && MazeGrid2[i][west] == '#') ||
                            (MazeGrid2[south][j] == '#' && MazeGrid2[south][east] == '#' && MazeGrid2[i][east] == '#')) {

                        //  MazeGrid2[i][j] = '.';
                        return true;

                    }
                }
                if(MazeGrid2[i][j] == ' ') {
                    if ((MazeGrid2[north][j] == ' ' && MazeGrid2[north][east] == ' ' && MazeGrid2[i][east] == ' ') ||
                            (MazeGrid2[north][j] == ' ' && MazeGrid2[north][west] == ' ' && MazeGrid2[i][west] == ' ') ||
                            (MazeGrid2[south][j] == ' ' && MazeGrid2[south][west] == ' ' && MazeGrid2[i][west] == ' ') ||
                            (MazeGrid2[south][j] == ' ' && MazeGrid2[south][east] == ' ' && MazeGrid2[i][east] == ' ')) {

                        //  MazeGrid2[i][j] = '.';
                        return true;

                    }
                }
            }
        }
        return false;
    }



    public void MazeGenerator(int xMin, int xMax, int yMin, int yMax){
        if ( xMax - xMin < 4 || yMax-yMin < 4){
            return;
        }

        // If 0 vertical line, if 1 horizontal line
        int lineOrientation = 1;
        if ( xMax-xMin>yMax-yMin){
            lineOrientation = 0;
        }
        int randomX = validNumber(xMin, xMax);
        int randomY = validNumber(yMin, yMax);
        //x coordinate is within the dimensions
        if (lineOrientation == 0){
            randomX = validNumber(xMin, xMax);
            randomY = validNumber(yMin-1, yMax+1);
            while (isBlockingPath(0, yMin, yMax, randomX, randomY) == 1){
                randomX = validNumber(xMin, xMax);
                randomY = validNumber(yMin-1, yMax+1);
            }
        }
        else {
            randomX = validNumber(xMin-1, xMax+1);
            randomY = validNumber(yMin, yMax);
            while (isBlockingPath(1, xMin, xMax, randomY, randomX) == 1) {
                randomY = validNumber(yMin, yMax);
                randomX = validNumber(xMin-1, xMax+1);
            }
        }

        if (lineOrientation == 0 ){
            //Splits vertically with line of walls
            for(int y = yMin+1; y < yMax; y++){
                MazeGrid2[y][randomX] = '#';
            }


            //Clears one of blocks to create passage
            MazeGrid2[randomY][randomX] = ' ';


            MazeGenerator(xMin, randomX, yMin, yMax);
            MazeGenerator(randomX, xMax, yMin, yMax);
        }
        else{
            //Splits horizontally with line of walls
            for( int x = xMin+1; x < xMax; x++){
                MazeGrid2[randomY][x] = '#';

            }
            //Clears one of blocks to create passage
            MazeGrid2[randomY][randomX] = ' ';



            MazeGenerator(xMin, xMax, yMin, randomY);
            MazeGenerator(xMin, xMax, randomY, yMax);
        }
    }



    public int validNumber(int min, int max){
        Random rand = new Random();
        int randomNum = rand.nextInt(max);

        while (randomNum <= min+1 || randomNum == max-1 ){
            randomNum = rand.nextInt(max);
        }
        return randomNum;
    }

    public int isBlockingPath(int orientation, int min, int max, int randomXY, int potentialBreak){
        if (orientation == 1 ){
            if( MazeGrid2[randomXY][min] == ' ' || MazeGrid2[randomXY][max] == ' ' ){
                if( MazeGrid2[randomXY][min] == ' ' && MazeGrid2[randomXY][max] == ' '){
                    return 1;
                }
                if ((min+1 == potentialBreak && MazeGrid2[randomXY][min] == ' ' ) || (max-1 == potentialBreak && MazeGrid2[randomXY][max] == ' ')) {
                    return 0;
                }
                return 1;
            }
        }
        else if(orientation == 0){
            if( MazeGrid2[min][randomXY] == ' ' || MazeGrid2[max][randomXY] == ' '){
                if(MazeGrid2[min][randomXY] == ' ' && MazeGrid2[max][randomXY] == ' '){
                    return 1;
                }
                if ((min+1 == potentialBreak && MazeGrid2[min][randomXY] == ' ')|| (max-1 == potentialBreak && MazeGrid2[max][randomXY] == ' ' )){
                    return 0;
                }
                return 1;
            }
        }
        return 0;
    }





}
