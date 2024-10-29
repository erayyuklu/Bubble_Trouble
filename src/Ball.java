//ERAY YUKLU 2021400273
public class Ball {
    double initialTime=System.currentTimeMillis();
    int level;
    double minPossibleRadius=9.0 * 0.0175;
    double radiusMultiplier=2.0;
    double radius;
    double xPosition;
    double xVelocity;
    double yVelocity=0;
    double yPosition;
    double gravity=-0.0002*9.0;
    boolean rightOrLeft;
    double minPossibleHeight=1.4;
    double currentHeight;
    double heightMultiplier=1.75;
    //all constants and necessary variables are created

    Ball(int level,double xPosition,double yPosition,boolean rightOrLeft){
        this.level=level;
        this.xPosition=xPosition;
        this.yPosition=yPosition;
        this.rightOrLeft=rightOrLeft;
    }//constructor is created

    void motion(){
        radius=Math.pow(radiusMultiplier,level)*minPossibleRadius;
        yVelocity+=gravity;
        if (xPosition<=radius || xPosition>=16.0-radius){
            xVelocity=-xVelocity;
            xPosition+=xVelocity;
        }
        else{
            xPosition+=xVelocity;
        }
        if(yPosition+yVelocity<=radius){
            yVelocity=Math.pow(-gravity*minPossibleHeight*Math.pow(heightMultiplier,level)*2,0.5);
            yPosition+=yVelocity;
        }
        else{
            yPosition+=yVelocity;
        }
    }

    void direction(){
        if(rightOrLeft){
            xVelocity=(16.0)/15000*45;
        }
        else{
            xVelocity=-(16.0)/15000*45;
        }
    }

    void setyVelocity(){

        yVelocity=(Math.pow(-gravity*minPossibleHeight*Math.pow(heightMultiplier,level),0.5));
    }


}