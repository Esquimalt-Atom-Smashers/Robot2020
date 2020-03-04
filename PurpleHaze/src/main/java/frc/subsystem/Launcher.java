package frc.subsystem;

public class Launcher {
    //public boolean shootMode = false;

    public Launcher(){
        System.out.println("Launcher constructor");
    }

    public void shoot() {
        System.out.println("Shooting!!!");
    }

    public boolean shootMotorsOn() {
        return true;
        //turn shoot motors on
    }

    public boolean shootMotorsOff() {
        return true;
        //turn shoot motors off
    }

    public boolean readyToShoot() {
        return true;
        //check motor speed and check if ball in position one
    }
   /* public boolean getShootMode() {
        return shootMode;
    }
    public boolean setShootMode(boolean mode){
        shootMode = mode;
        return shootMode;
    } */
}