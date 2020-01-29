package frc.util;

public interface MathExtended
{
    public default double sec(double theta)
    {
        return 1/Math.cos(theta);
    }

    public default double csc(double theta)
    {
        return 1/Math.sin(theta);
    }

    public default double cot(double theta)
    {
        return 1/Math.tan(theta);
    }

    public default double sec2(double theta)
    {
        return Math.pow(sec(theta), 2);
    }

    public default double[] polysolve2ndDeg(double a, double b, double c)
    {
        double[] solutions = new double[2];

        if (Math.pow(b,2) + 4*a*c < 0) return null; //check for negative discrimant

        solutions[0] = (-b+Math.sqrt(Math.pow(b, 2) + 4*a*c))/(2*a);

        solutions[1] = (-b-Math.sqrt(Math.pow(b, 2) + 4*a*c))/(2*a);

        return solutions;
    }

    
}