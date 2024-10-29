//ERAY YUKLU 2021400273
public class Bar {
    double time;
    Bar(double time){
        this.time=time;
    }//constructor is created

    double  xScaleBar(){
        double currentTime=System.currentTimeMillis();
        double difference=currentTime-time;
        double xScale=16.0-difference*16/40000;
        return xScale;
    }//this function determines the width of time bar depends on time

    double barGreenRate(){
        double currentTime=System.currentTimeMillis();
        double difference=currentTime-time;
        return 255-255.0/40000*difference;
    }//this function determines the green rate in rgb scale depends on the time
}
