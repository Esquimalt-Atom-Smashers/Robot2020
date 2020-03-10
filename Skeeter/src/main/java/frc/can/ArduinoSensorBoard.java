package frc.can;

import edu.wpi.first.wpilibj.CAN;

public class ArduinoSensorBoard
{
    private static final int CAN_TEAM_USE = 8;
    private static final int CAN_TYPE_MISCELLANEOUS = 10;
    private CAN board;

    public ArduinoSensorBoard(int deviceID)
    {
        board = new CAN(deviceID, CAN_TEAM_USE, CAN_TYPE_MISCELLANEOUS);
    }

    public CANEncoder newEncoder(int aChannel, int bChannel)
    {
        return new CANEncoder(0);
    }

    CAN getCAN()
    {
        return board;
    }
}