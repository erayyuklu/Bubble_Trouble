//ERAY YUKLU 2021400273
import java.awt.event.KeyEvent;
public class Player {
    double currentPlayerLocation;
    double playerVelocity=0.1;
    Player(double x){
        this.currentPlayerLocation=x;
    }//constructor is created

    void isPressed(){
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
            if(currentPlayerLocation>=16.0-23.0/74 ){
                currentPlayerLocation+=0;
            }//boundary condition
            else{
                currentPlayerLocation += playerVelocity;
            }
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
            if(currentPlayerLocation<=23.0/74 ){
                currentPlayerLocation-=0;
            }//boundary condition
            else{
                currentPlayerLocation -= playerVelocity;
            }
        }
    }

    double location(){
        return currentPlayerLocation;
    }
}
