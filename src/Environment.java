//ERAY YUKLU 2021400273
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.ArrayList;

public class Environment {
    double currentPlayerLocation;
    Player player=new Player(8.0);
    double time=System.currentTimeMillis();
    Bar bar= new Bar(time);
    boolean oneSplitted=false;
    ArrayList<Double> timeList;
    boolean reset=false;
    boolean inProcess=false;//inprocess condition determines the moments that arrow drawing

    public void draw(Arrow arrow,ArrayList<Ball> allBalls){
        int pauseDuration=15;
        player.isPressed();
        currentPlayerLocation=player.location();
        StdDraw.picture(8.0,4.5,"background.png",16.0,9.0);//our canvas is refresh continuously by adding background each loop
        double currentTime= System.currentTimeMillis();
        boolean ready=true;
        if(arrow.timeList.size()<2){
            if(arrow.timeList.size()==0){
                ready=false;
            }
            else{
                arrow.thisIsDrawing=arrow.timeList.get(0);
                arrow.location=currentPlayerLocation;
            }
        }
        else {
            if (arrow.timeList.get(arrow.timeList.size()-1)- arrow.thisIsDrawing>1500 & currentTime-arrow.thisIsDrawing>1500){
                arrow.thisIsDrawing=arrow.timeList.get(arrow.timeList.size()-1);
                arrow.location=currentPlayerLocation;
                oneSplitted=false;
            }
            else{
                inProcess=false;
            }
        }
        //appropriate moments to draw arrow determined by if/else conditions above
        double heightofArrow;
        if (ready & !oneSplitted){
            double scaledHeight = (currentTime - arrow.thisIsDrawing) * 9.0 / 1500;
            heightofArrow=scaledHeight;
            if (scaledHeight < 9.0) {
                inProcess = true;
                StdDraw.picture(arrow.location,scaledHeight/2,"arrow.png",0.2,scaledHeight);
                //arrow drawing
            }
        }
        else{
            heightofArrow=0;
        }
        int k=-1;
        if(arrow.location!=null){
            for(int i=0;i< allBalls.size();i++){
                if (inProcess& (heightofArrow<allBalls.get(i).yPosition   &  (arrow.location-allBalls.get(i).xPosition)*(arrow.location-allBalls.get(i).xPosition)+
                        (heightofArrow-allBalls.get(i).yPosition)*(heightofArrow-allBalls.get(i).yPosition)<allBalls.get(i).radius*allBalls.get(i).radius   ||
                        allBalls.get(i).yPosition<heightofArrow& allBalls.get(i).xPosition+allBalls.get(i).radius> arrow.location & allBalls.get(i).xPosition-
                                allBalls.get(i).radius<arrow.location &heightofArrow>=allBalls.get(i).yPosition-allBalls.get(i).radius)){
                    k=i;
                    if(allBalls.get(i).level>0){
                        Ball newBall1= new Ball(allBalls.get(i).level-1,allBalls.get(i).xPosition,allBalls.get(i).yPosition,true);
                        newBall1.direction();newBall1.setyVelocity();
                        Ball newBall2= new Ball(allBalls.get(i).level-1,allBalls.get(i).xPosition,allBalls.get(i).yPosition,false);
                        newBall2.direction();newBall2.setyVelocity();
                        allBalls.add(newBall1);allBalls.add(newBall2);
                        break;
                    }
                }
            }
        }
        //The intersection of the ball and the arrows was decided and a new ball object was added to the existing allballs arraylist at each intersection.
        if(k!=-1){
            oneSplitted=true;
            allBalls.remove(k);
            //splitted ball object removed from arraylist
        }


        StdDraw.picture(8.0,-0.5,"bar.png",16.0,1.0);//bar drawed

        StdDraw.picture(currentPlayerLocation,0.5,"player_back.png",23.0/37,1.0);//player draweed

        if(bar.xScaleBar()>0){
            StdDraw.setPenColor(255,(int)bar.barGreenRate(),0);
            StdDraw.filledRectangle(0.0,-0.5,bar.xScaleBar(),0.25);
        }//time bar is drawed in each frame

        for(int i=0;i< allBalls.size();i++) {
            double y = allBalls.get(i).yPosition;
            double x = allBalls.get(i).xPosition;
            double l = currentPlayerLocation - 23.0 / 74;
            double r = currentPlayerLocation + 23.0 / 74;
            double a = allBalls.get(i).radius;
            if (y < 1 & x + a > l& x+a<r) {
                finishScreen(false);
            }
            else if (Math.pow(y - 1, 2) + Math.pow(l - x, 2) < a * a & y > 1 & l > x) {
                finishScreen(false);
            }
            else if (y > 1 & y - 1 < a & r-x+a>0 &x+a-l>0) {
                finishScreen(false);
            }
            else if (Math.pow(x - r, 2) + Math.pow(y - 1, 2) < a * a & y > 1 & x > r) {
                finishScreen(false);
            }
            else if (y < 1 & x -a < r& x-a>l) {
                finishScreen(false);
            }
        }
        //all intersection conditions between our "circular" ball objects and our player


        //all balls in the arraylist are drawed

        if(allBalls.size()==0){
            finishScreen(true);
        }
        //if all balls in the arraylist are splitted before time out, player won
        if(bar.xScaleBar()<=0){
            finishScreen(false);
        }
        for(int i=0;i< allBalls.size();i++){
            allBalls.get(i).motion();
            double radi=Math.pow(2,allBalls.get(i).level)*9.0*0.0175;
            StdDraw.picture(allBalls.get(i).xPosition,allBalls.get(i).yPosition,"ball.png",2*allBalls.get(i).radius,2*allBalls.get(i).radius);
        }
        //if time bar consumed before all balls splitted, then player lost.
        StdDraw.show();
        StdDraw.pause(pauseDuration);
    }

    void finishScreen(boolean isWon){
        StdDraw.picture(8.0,9.0/2.18,"game_screen.png",16.0/3.8,9.0/4);
        StdDraw.setPenColor(Color.black);
        Font myFont = new Font("Helvetica", Font.BOLD, 30);
        StdDraw.setFont(myFont);
        if(isWon){
            StdDraw.text(8.0,4.5, "YOU WON!");
        }
        else{
            StdDraw.text(8.0,4.5, "GAME OVER!");
        }
        Font myFont2= new Font("Helvetica", Font.ITALIC, 15);
        StdDraw.setFont(myFont2);
        StdDraw.text(8.0,9.0/2.3, "To Replay Click “Y”");
        StdDraw.text(8.0,9.0/2.6, "To Quit Click “N”");
        StdDraw.show();
        //finish screen is created
        while(true){
            StdDraw.pause(15);
            if(StdDraw.isKeyPressed(KeyEvent.VK_N)){
                System.exit(0);//if user wants to exit, we will terminate the code
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_Y)){
                currentPlayerLocation=8.0;
                reset=true;
                break;//if user wants to play again, reset condition will be true
            }
        }
    }
}
