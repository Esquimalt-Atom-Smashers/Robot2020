//This line tells the compiler that ArduinSensorBoard.java is part of the frc.can package.
package frc.can;

//Imports the class CAN so ArduinoSensorBoard has access to all CAN methods.
import edu.wpi.first.wpilibj.CAN;

//Creates a public class named "ArduinoSensorBoard", keep in mind that the name of this class is the name of the java file .
public class ArduinoSensorBoard
{
    /*Creates a private final int named "CAN_TEAM_USE" and is assigned the int 8.
    This means the value of this integer cant be changed and only ArdunioSensorBoard has access to this variable.*/
    private static final int CAN_TEAM_USE = 8;
    
    /*Creates a private final int named "CAN_TYPE_MISCELLANEOUS" and is assigned the int 10.
    This means the value of this integer cant be changed and only ArdunioSensorBoard has access to this variable.*/
    private static final int CAN_TYPE_MISCELLANEOUS = 10;
    
    //Creates a private CAN variable(CAN just allows to interface with CAN devices) called "board". 
    private CAN board;

    //Creates a public class called "ArduinoSensorBoard" which takes in 1 parameter. The parameter is an integer variable called "deviceID".
    public ArduinoSensorBoard(int deviceID)
    {
        /*  "board" is made into an object and creates a CAN communication interface with the specific (int deviceID, int deviceManufactuar, int DeviceType)
        In this case (deviceID) is equal to whatever integer is placed in ArduinoSensorBoard's parameter.
        And the (deviceManufactuar) parameter is the "CAN_TEAM_USED" variable we made up above so it is equal to 8
        Lastly the (DeviceType) parameter is the "CAN_TYPE_MISCELLANEOUS" variable so its value is equal to 10  */
        board = new CAN(deviceID, CAN_TEAM_USE, CAN_TYPE_MISCELLANEOUS);
    }
    //Creates a object from the class CANEncoder and names it newEncoder. This constructor has two parameters, int 'aChannel' & int 'bChannel'
    public CANEncoder newEncoder(int aChannel, int bChannel)
    {
        //returns the value of the CANEncoder with 0 as its parameter value
        return new CANEncoder(0);
    }

    CAN getCAN()
    {
        return board;
    }
}
