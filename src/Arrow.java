//ERAY YUKLU 2021400273
import java.awt.event.KeyEvent;
import java.util.ArrayList;
public class Arrow {
    ArrayList<Double> timeList;
    Double location;
    Double thisIsDrawing;
    Arrow(ArrayList<Double> timeList){
        this.timeList=timeList;
    }//constructor is created

    void timeAdder(){
        if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE)){
            double currentTime=System.currentTimeMillis();
            timeList.add(currentTime);
        }//this function store the times when user press space
    }
}
