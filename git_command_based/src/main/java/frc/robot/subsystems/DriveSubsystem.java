// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  // DECLARAR TODOS LOS COMPONENTES:
  static WPI_TalonSRX motorAtrasIzquierdo = new WPI_TalonSRX(Constants.MotorIDS.motorAtrasIzqID);
  static WPI_VictorSPX motorAdelanteIzquierdo = new WPI_VictorSPX(Constants.MotorIDS.motorAdelanteIzqID);

  static WPI_TalonSRX motorAtrasDerecho = new WPI_TalonSRX(Constants.MotorIDS.motorAtrasDerID);
  static WPI_VictorSPX motorAdelanteDerecho = new WPI_VictorSPX(Constants.MotorIDS.motorAdelanteDerID);

  
  AHRS navX = new AHRS(SPI.Port.kMXP);

  static MotorControllerGroup der = new MotorControllerGroup(motorAdelanteDerecho, motorAtrasDerecho);
  static MotorControllerGroup izq = new MotorControllerGroup(motorAdelanteIzquierdo, motorAtrasIzquierdo);

  static DifferentialDrive chassis = new DifferentialDrive(izq, der);

  static Encoder boreEncoder = new Encoder(Constants.DeviceChannels.kboreEncoderChannelA, Constants.DeviceChannels.kboreEncoderChannelB);


  // variables factor de conversion Encoder.
  double CountsPerRev = Constants.EncoderData.kboreCountsPerRev;
  double diametroLlantaMetros = Constants.EncoderData.kdiametroLlantaMetros; // --> metros


  public DriveSubsystem() {

    // tunear todo:
    izq.setInverted(true);
    // ENCODERS:
    boreEncoder.setDistancePerPulse((Math.PI * diametroLlantaMetros) / CountsPerRev);
    resetEncoders(); // --> funcion void.
    resetYaw();

  }

  // Get Instance:
  private static DriveSubsystem instance;

  public static DriveSubsystem getInstance() {
      if (instance == null){
        instance = new DriveSubsystem();
      }
      return instance;
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  public static void arcadeDrive (double avance, double giro) {
    chassis.arcadeDrive(avance, giro);
  }

  public static void tankDrive (double avanceIzq, double avanceDer) {
    chassis.tankDrive(avanceIzq, avanceDer);
  }
  public double getDistanceBore() {
    return -boreEncoder.getDistance();
  }

  // CHANCE JALA EN EL ROBOT CONTAINER (PRUEBA)
  public boolean inTankDrive() {
    return true;
    
  }

  public static void resetEncoders() {
    boreEncoder.reset();
  }

  public void resetYaw () {
    navX.reset();
  }

  public float getYaw() {
    return navX.getYaw();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // IMPRIMIR VALORES EN LA SHUFFLE BOARD
    SmartDashboard.putNumber("Distance meters bore Encoder:", getDistanceBore());
    SmartDashboard.putNumber("Giro Roll", navX.getRoll());
    SmartDashboard.putNumber("Naricita Pitch", navX.getPitch());
    SmartDashboard.putNumber("Fiun Yaw", navX.getYaw());
    SmartDashboard.putNumber("Angle NavX", navX.getAngle());

    SmartDashboard.putNumber("Extra Angle Adjustment", navX.getAngleAdjustment());
    SmartDashboard.putNumber("Extra Yaw Displacement", navX.getDisplacementX());


  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
