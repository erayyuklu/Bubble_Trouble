//ERAY YUKLU 2021400273
import java.util.ArrayList;
public class eray_yuklu {
    public static void main(String[] args) {
        StdDraw.setCanvasSize(800, 500);
        StdDraw.setXscale(0.0, 16.0);
        StdDraw.setYscale(-1.0, 9.0);
        //Canvas created
        StdDraw.enableDoubleBuffering();
        while(true){
            runRepeatedly();
        }
        //made the code run from scratch as long as the player wants to continue the game
    }
    public static void runRepeatedly() {
        Environment environment=new Environment(); //environment object created
        ArrayList<Double> spaceTimes=new ArrayList<>(); //an arraylist that includes the times the user presses the space key
        Arrow arrow=new Arrow(spaceTimes);//arrow object created
        ArrayList<Ball> allBalls=new ArrayList<>();//an arraylist containing all the balls that will form throughout the game
        Ball defaultBig= new Ball(2,4.0,4,false);
        defaultBig.direction();defaultBig.setyVelocity();
        Ball defaultMed= new Ball(1,16.0/3,5,true);
        defaultMed.direction();defaultMed.setyVelocity();
        Ball defaultSmall= new Ball(0,3,3,true);
        defaultSmall.direction();defaultSmall.setyVelocity();
        allBalls.add(defaultBig);allBalls.add(defaultMed);allBalls.add(defaultSmall);
        //3 starting ball objects created and they added into our allballs arraylist.
        while(true){//created an infinite loop
            arrow.timeAdder();
            environment.draw(arrow,allBalls);
            if(environment.reset){
                break;//when user wants to play again, code resets itself
            }

        }
    }
}
