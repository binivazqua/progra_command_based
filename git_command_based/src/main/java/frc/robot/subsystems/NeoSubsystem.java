package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NeoSubsystem extends SubsystemBase {
    static CANSparkMax neote = new CANSparkMax(6, MotorType.kBrushless);
    RelativeEncoder encoderNeote = neote.getEncoder();

    public NeoSubsystem () {
        neote.restoreFactoryDefaults();
        encoderNeote.setPosition(0);
    }

    private static NeoSubsystem instance;
    public static NeoSubsystem getInstance() {
        if(instance == null){
            instance = new NeoSubsystem();
        }
        return instance;
    }


    
    public double getNeoteEncoder() {
        return encoderNeote.getPosition();
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Position Neote Encoder", getNeoteEncoder());
    }

    // Para este subssystem no haces una funcion para recibir axis, pero sí para hacer que los motores se muevan.
    public static void setMotor(double speed){
        neote.set(speed);
    }


}
