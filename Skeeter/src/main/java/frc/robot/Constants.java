//This line tells the compiler that Constants.java is part of the frc.robot package.
package frc.robot;

//Creates a public class called Constants. Note that the name of the class is the same as the file name.
public class Constants
{
    /*
   _____                _              _       
  / ____|              | |            | |      
 | |     ___  _ __  ___| |_ __ _ _ __ | |_ ___ 
 | |    / _ \| '_ \/ __| __/ _` | '_ \| __/ __|
 | |___| (_) | | | \__ \ || (_| | | | | |_\__ \
  \_____\___/|_| |_|___/\__\__,_|_| |_|\__|___/
                                              
    */
    
    //Keep in mind constants are just variables that cannot be changed.
    
    //Declares a public constant int named "SPARK_LEFT_DRIVE_PWM" and sets the value to 0
    public static final int SPARK_LEFT_DRIVE_PWM = 0;
    //Declares a public constant int named "SPARK_RIGHT_DRIVE_PWM" and sets the value to 1
    public static final int SPARK_RIGHT_DRIVE_PWM = 1;

    
    public static final int CLP_DRIVE_VICTORSPX_CAN_ID = 6;
    public static final int CLP_DRIVE_LIMITSWITCH_LOWER = 1;
    public static final int CLP_DRIVE_LIMITSWITCH_UPPER = 2;

    public static final double CLP_DRIVE_GEARING = 180d;

    public static final double CIM_ENCODER_PPR = 20d;

    public static final int CLP_SHOOTER1_VICTORSPX_CAN_ID = 4;
    public static final int CLP_SHOOTER2_VICTORSPX_CAN_ID = 5;
    public static final int CLP_TRANSPORT_COLLECTOR_VICTORSPX_CAN_ID = 1;
    public static final int CLP_TRANSPORT_CENTER_VICTORSPX_CAN_ID = 2;
    public static final int CLP_TRANSPORT_LAUNCH_VICTORSPX_CAN_ID = 3;
    

}
