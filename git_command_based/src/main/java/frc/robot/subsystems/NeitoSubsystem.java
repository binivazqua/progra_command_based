package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NeitoSubsystem extends SubsystemBase{

    static CANSparkMax neito = new CANSparkMax(5, MotorType.kBrushless);
    RelativeEncoder neitoEncoder = neito.getEncoder();

    // LIMIT SWITCH:
    static DigitalInput limitSwitch = new DigitalInput(6);

        
    public NeitoSubsystem() {
        neito.restoreFactoryDefaults();
        neitoEncoder.setPosition(0);
    }

    // get instance:

    private static NeitoSubsystem instance;

    public static NeitoSubsystem getInstance() {
        if(instance == null){
            instance = new NeitoSubsystem();
        }

        return instance;
    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    public double getNeitoEncoder() {
        return neitoEncoder.getPosition(); // --> return pq si no no sacas nada jeje.
    }

    public double getSpeedNeitoEncoder() {
        return neitoEncoder.getVelocity();
    }

    public void resetEncoders() {
        neitoEncoder.setPosition(0);
    }
    public static boolean getLimitSwitch() {
        return !limitSwitch.get(); // ESTÁ INVERTIDO
         // LIMIT SWITCH:
        // si es false -> APRETADO -> neito para.
        // si es true -> LIBRE -> neito se mueve.
    }

    public static void setMotor(double speed) {
        neito.set(speed);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Position Neito Encoder", getNeitoEncoder());
        SmartDashboard.putBoolean("Limit Switch On?", getLimitSwitch());

        // Que jale el motor dependiendo del limit switch, porque tiene que pasar siempre
    }

}
