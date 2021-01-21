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
                                              
     Note: I will be commenting where you can find the variable be used once i find where they are located - Isaac Nelson
    */
    
    /*Declares a public constant int named "SPARK_LEFT_DRIVE_PWM" and sets the value to 0
        LOCATION: LN 47, CLASS DriverBase*/
    public static final int SPARK_LEFT_DRIVE_PWM = 0;
    /*Declares a public constant int named "SPARK_RIGHT_DRIVE_PWM" and sets the value to 1
        LOCATION: LN 48, CLASS DriverBase*/
    public static final int SPARK_RIGHT_DRIVE_PWM = 1;

    /*Declares a public constant int named "CLP_DRIVE_VICTORSPX_CAN_ID" and sets the value to 6
        LOCATION: LN 38, CLASS CLPdrive*/
    public static final int CLP_DRIVE_VICTORSPX_CAN_ID = 6;
    //Declares a public constant int named "CLP_DRIVE_LIMITSWITCH_LOWER" and sets the value to 1
    public static final int CLP_DRIVE_LIMITSWITCH_LOWER = 1;
    //Declares a public constant int named "CLP_DRIVE_LIMITSWITCH_UPPER" and sets the value to 2
    public static final int CLP_DRIVE_LIMITSWITCH_UPPER = 2;

    /*Declares a public constant double named "CLP_DRIVE_GEARING" and sets the value to 180.0d
        LOCATION: LN 42, CLASS CLPdrive*/
    public static final double CLP_DRIVE_GEARING = 180d;
    /*Declares a public constant double named "CIM_ENCODER_PPR" and sets the value to 20.0d
        LOCATION: LN 42, CLASS CLPdrive*/
    public static final double CIM_ENCODER_PPR = 20d;

    /*Declares a public constant int named "CLP_SHOOTER1_VICTORSPX_CAN_ID" and sets the value to 4
        LOCATION: LN 18, CLASS Launcher*/
    public static final int CLP_SHOOTER1_VICTORSPX_CAN_ID = 4;
    /*Declares a public constant int named "CLP_SHOOTER2_VICTORSPX_CAN_ID" and sets the value to 5
        LOCATION: LN 19, CLASS Launcher*/
    public static final int CLP_SHOOTER2_VICTORSPX_CAN_ID = 5;
    //Declares a public constant int named "CLP_TRANSPORT_COLLECTOR_VICTORSPX_CAN_ID" and sets the value to 1
    public static final int CLP_TRANSPORT_COLLECTOR_VICTORSPX_CAN_ID = 1;
    //Declares a public constant int named "CLP_TRANSPORT_CENTER_VICTORSPX_CAN_ID" and sets the value to 2
    public static final int CLP_TRANSPORT_CENTER_VICTORSPX_CAN_ID = 2;
    //Declares a public constant int named "CLP_TRANSPORT_LAUNCH_VICTORSPX_CAN_ID" and sets the value to 3
    public static final int CLP_TRANSPORT_LAUNCH_VICTORSPX_CAN_ID = 3;
    

}
