package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.math.Pair;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class Turning  extends CommandBase{
    
    // Instance de los subsystems que vas a usar:
    private final DriveSubsystem m_DriveSubsystem;
    /*private final double distance;*/
    private final float angle;
    private final boolean isRight;
    private double speedRight;
    private double speedLeft;

    
    // Nuevo Command:
    public Turning(float angle, boolean isRight) {

        m_DriveSubsystem = DriveSubsystem.getInstance();
        this.angle = m_DriveSubsystem.getYaw() + angle; // --> desde donde estás hasta donde llegas.
        this.isRight = isRight;
        addRequirements(m_DriveSubsystem);
    }

    /* Function para girar a la derecha o a la izquierda */

    public void rightOrLeft (boolean direction) {

        if (direction == true) { // si es true, vuelta a la derecha -> speedright positiva.
            speedLeft = -0.5;
            speedRight = 0.5;
            /* speed left = -0.6 */
        } else {
            speedLeft = 0.5;
            speedRight = -0.5;
        }
  
        DriveSubsystem.tankDrive(speedLeft, speedRight);
    }



    // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Turning Command Started!");
    m_DriveSubsystem.resetYaw();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    rightOrLeft(isRight);
    SmartDashboard.putBoolean("Turning cmmnd Finished", isFinished());
  }


  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DriveSubsystem.tankDrive(0, 0);
    System.out.println("Turning Command Ended Succesfully!");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    // condicional p ver si alcanzó el angle:
    if(m_DriveSubsystem.getYaw() >= angle){
      return true;
    } else {
      return false;
    }
    
  }
}


