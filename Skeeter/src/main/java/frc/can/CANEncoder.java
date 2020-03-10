package frc.can;

import edu.wpi.first.wpilibj.CAN;

public class CANEncoder
{
    private ArduinoSensorBoard host;

    CANEncoder(int port)
    {

    }  
    
    public double get()
    {
        CAN can = host.getCAN();
        can.writePacket(null, CANMessageBuilder.buildApiId(0, 0));
        return 0;
    }
}