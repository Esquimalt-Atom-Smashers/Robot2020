package frc.subsystem;

public class Collector {
    public Collector(){
        System.out.println("Collector constructor");
    }

    public boolean collect() {
        System.out.println("Collection mode is on!");
        deployCollector();
        collectorRotationOn();
        return true;
    }

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

    }
    public void collectorRotationOff(){

    }
}