package frc.util;

public class MathExtended
{
    public static double sec(double theta)
    {
        return 1/Math.cos(theta);
    }

    public static double csc(double theta)
    {
        return 1/Math.sin(theta);
    }

    public static double cot(double theta)
    {
        return 1/Math.tan(theta);
    }

    public static double sec2(double theta)
    {
        return Math.pow(sec(theta), 2);
    }

    public static double[] polysolve2ndDeg(double a, double b, double c)
    {
        double[] solutions = new double[2];

        if (Math.pow(b,2) + 4*a*c < 0) return null; //check for negative discrimant

        solutions[0] = (-b+Math.sqrt(Math.pow(b, 2) + 4*a*c))/(2*a);

        solutions[1] = (-b-Math.sqrt(Math.pow(b, 2) + 4*a*c))/(2*a);

        return solutions;
    }

    
}