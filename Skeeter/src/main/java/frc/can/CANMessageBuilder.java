package frc.can;

import edu.wpi.first.hal.CANData;
import edu.wpi.first.wpilibj.CAN;

/*
    https://docs.wpilib.org/en/latest/docs/software/can-devices/can-addressing.html
*/

public class CANMessageBuilder
{
    public static int buildApiId(int apiClass, int apiIndex)
    {
        int ret = 0;

        ret += apiClass;
        ret = ret << 6;
        ret = ret | apiIndex;

        return ret;
    }
}