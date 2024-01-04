package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveForward  extends CommandBase{
    
    // Instance de los subsystems que vas a usar:
    private final DriveSubsystem m_DriveSubsystem;
    private final double distance;
    
    // Nuevo Command:
    public DriveForward(double distance) {

        m_DriveSubsystem = DriveSubsystem.getInstance();
        this.distance = m_DriveSubsystem.getDistanceBore() + distance; // --> desde donde estás hasta donde llegas.
        addRequirements(m_DriveSubsystem);
    }

    // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Drive Forward Command Started!");
    DriveSubsystem.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    DriveSubsystem.tankDrive(0.4, 0.4); // --> le pones directo los valores de la velocidad.
    SmartDashboard.putBoolean("Drive FWD", isFinished());
  }

  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DriveSubsystem.tankDrive(0, 0);
    System.out.println("Drive Forward Command Ended Succesfully!");

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    // condicional p ver si alcanzó la distance:
    if(m_DriveSubsystem.getDistanceBore() >= distance){
      return true;
    } else {
      return false;
    }
    
  }
}


