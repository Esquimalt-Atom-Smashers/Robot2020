package frc.control;

import edu.wpi.first.hal.sim.DriverStationSim;
import edu.wpi.first.hal.sim.mockdata.DriverStationDataJNI;
import edu.wpi.first.wpilibj.DriverStation;

public class PID
{
    protected double derivative;

    protected double integral;
    protected double integralLimit;
    protected double integralFunctionalRange; //+-

    protected double target;
    protected double position;
    protected long lastCallTime;

    protected double kI;
    protected double kP;
    protected double kD;

    

    public PID(double kI, double kP, double kD, double integralFunctionalRange, double integralLimit)
    {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.derivative = 0;
        this.integral = 0;
        this.integralFunctionalRange = integralFunctionalRange;
        this.integralLimit = integralLimit;

    }

    public void setTarget(double target)
    {
        this.target = target;
    }

    public double getPositition()
    {
        return 0;
    }

    public double update()
    {
        return calculate();
    }
    
    public double calculate()
    {
        double positionChange = getPositition() - position;
        position = getPositition();
        long time = System.currentTimeMillis();
        double detla = (lastCallTime - time)/1d;
        lastCallTime = time;
        double derivative = positionChange/detla;
        
        double error =  target - position;

        double power = 0;

        if (Math.abs(error) < integralFunctionalRange)
        {
            integral += error * delta;
            double integralPower = integral * kI;
            power += (Math.abs(integralPower) > integralLimit)? Math.signum(integralPower) * integralLimit : integralPower;
        }
        else integral = 0;

        power += error * kP;
        pwoer += derivative * kI;
    }



}