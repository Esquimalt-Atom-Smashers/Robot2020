package frc.control;

import edu.wpi.first.hal.sim.DriverStationSim;
import edu.wpi.first.hal.sim.mockdata.DriverStationDataJNI;
import edu.wpi.first.wpilibj.DriverStation;
import frc.util.Range;


public class PID
{
    protected double derivative;
    protected double lastDerivative;
    protected double derivativeIntegral;
    protected double maxRate;
    protected double kRateP;
    protected double kRateI;
    protected double kRateD;
    protected Range controllerOutputRange;
    protected boolean doRateLimit = false;

    protected double maxPowerChangePerTick;
    protected double lastPower;


    protected double positionTolerance;

    protected double integral;
    protected double integralPowerLimit;
    protected double integralLimit;
    protected double integralFunctionalRange; //+-

    protected double target;
    protected double lastPosition;
    protected long lastCallTime;

     

    protected double kI;
    protected double kP;
    protected double kD;

    

    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    public PID(double kI, double kP, double kD, double integralFunctionalRange, double integralPowerLimit, double integralLimit)
    {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.derivative = 0;
        this.integral = 0;
        this.integralFunctionalRange = integralFunctionalRange;
        this.integralPowerLimit = integralPowerLimit;
        this.integralLimit = integralLimit;
    }

    public void setTolerance(double tolerance)
    {
        this.positionTolerance = tolerance;
    }

    public boolean reachedPosition()
    {
        return target - lastPosition < positionTolerance;
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

    public void setOutputRange(Range range)
    {
        this.controllerOutputRange = range;
    }

    public void setMaxRate(double maxRate)
    {
        this.maxRate = maxRate;
    }

    public double getPosition()
    {
        return 0;
    }

    public double update()
    {
        return calculate(getPosition());
    }

    public void resetPowerOutput()
    {
        lastPower = 0;
    }

    public double getLastPower()
    {
        return lastPower;
    }

    public void setLastPower(double lastPower)
    {
        this.lastPower = lastPower;
    }
    
    public double calculate(double position)
    {
        double positionChange = lastPosition- position;
        lastPosition = getPosition();
        long time = System.currentTimeMillis();
        double delta = (lastCallTime - time)/1d;
        derivative = positionChange/delta;
        lastCallTime = time;
        
        double error =  target - position;

        double power = 0;

        if (Math.abs(error) < integralFunctionalRange)
        {
            integral += error * delta;
            if (Math.abs(integral) > integralLimit)
            {
                integral = Math.signum(integral) * integralLimit;
            }
            double integralPower = integral * kI;
            power += (Math.abs(integralPower) > integralPowerLimit)? Math.signum(integralPower) *  integralPowerLimit: integralPower;
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

        if (controllerOutputRange != null)
        {
            if (controllerOutputRange.contains(power)) return power;
            else if (power < controllerOutputRange.getMin()) return controllerOutputRange.getMin();
            else return controllerOutputRange.getMax();
        }
        return power;
    }



}