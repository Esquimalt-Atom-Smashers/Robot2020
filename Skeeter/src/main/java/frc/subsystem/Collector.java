package frc.subsystem;

public class Collector {
    //This is Collector's constructor
    public Collector(){
        System.out.println("Collector constructor");
    }
    /*creates a public method named 'collect' which returns a boolean 
    This method will be called when we want the collection mode to be turned on*/
    public boolean collect() {
        System.out.println("Collection mode is on!");
        deployCollector();
        collectorRotationOn();
        return true;
    }
    /*creates a public method named 'stopcollect' which returns a boolean 
    This method will be called when we want the collection mode to be turned off*/
    public boolean stopCollect() {
        System.out.println("Collection mode is off!");
        withdrawCollector();
        collectorRotationOff();
        return true;
    }

    public void deployCollector(){
    
    }
    public void withdrawCollector(){

    }
    public void collectorRotationOn(){

        /*
        * Put code in here to turn on the moters which control the rotation of the collecter
        *
        *
        */
        
    }
    public void collectorRotationOff(){

        /*
        * Put code in here to turn off the moters which control the rotation of the collecter
        *
        *
        */
        
    }
}
