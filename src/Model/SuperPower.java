package Model;

import java.util.Random;

/**
 * this is our SuperPower class which holds the position of a superpower in the grid and it is reproduced randomly once it is consumed by the hero.
 * Parsa Keikhosravani 3013572013
 * Jaskaran Grewal 301381928
 */
public class SuperPower {
    private static SuperPower instance;
    private int vertical=0;
    private int horizontal=0;

    public int getVertical() {
        return vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public static SuperPower getInstance(){
        if(instance==null){
            instance= new SuperPower();
        }
        return  instance;
    }

    private SuperPower(){

    }

    public void Generate(char[][] test){
        Random temporarynum= new Random();
        do {
            vertical = 1 + temporarynum.nextInt(13);
            horizontal = 1 + temporarynum.nextInt(18);
        }while (test[vertical][horizontal]=='#');
    }


}
