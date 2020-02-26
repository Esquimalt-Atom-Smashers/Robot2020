package frc.control;

import edu.wpi.first.hal.sim.DriverStationSim;
import edu.wpi.first.hal.sim.mockdata.DriverStationDataJNI;
import edu.wpi.first.wpilibj.DriverStation;

public class PID
{
    protected double derivative;
    protected double lastDerivative;
    protected double derivativeIntegral;
    protected double maxRate;
    protected double kRateP;
    protected double kRateI;
    protected double kRateD;

    protected boolean doRateLimit = false;

    protected double maxPowerChangePerTick;
    protected double lastPower;


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

    public void setMaxPowerChangePerTick(double max)
    {
        this.maxPowerChangePerTick = max;
    }

    public void setTarget(double target)
    {
        this.target = target;
    }

    public void enableRateLimiting(boolean useRateLimiting)
    {
        this.doRateLimit = useRateLimiting;
    }

    public void initRateLimiting(double kRateP, double kRateI, double kRateD, double maxRate, boolean enableNow)
    {
        this.kRateP = kRateP;
        this.kRateI = kRateI;
        this.kRateD = kRateD;
        this.maxRate = maxRate;
        this.doRateLimit = enableNow;
    }

    private double calculateRateAdjustmentPower(double derivative, double deltaT)
    {
        double rateError = derivative * maxRate;

        double secondDerivative = (derivative - lastDerivative)/deltaT;
        lastDerivative = derivative;
        double powerReduction = secondDerivative * kRateD;
        powerReduction += rateError * kRateP;

        derivativeIntegral += rateError * deltaT;

        powerReduction += derivativeIntegral * kRateI;

        return powerReduction;

   }


    public double getPosition()
    {
        return 0;
    }

    public double update()
    {
        return calculate();
    }
    
    public double calculate()
    {
        double positionChange = getPosition() - position;
        position = getPosition();
        long time = System.currentTimeMillis();
        double delta = (lastCallTime - time)/1d;
        derivative = positionChange/delta;
        lastCallTime = time;
        
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
        power += derivative * kI;

        if (doRateLimit) power -= calculateRateAdjustmentPower(derivative, delta);

        double deltaPower = power - lastPower;
        if (Math.abs(deltaPower) > maxPowerChangePerTick)
        {
            power = lastPower + (maxPowerChangePerTick * Math.signum(deltaPower));
        }

        return power;
    }



}