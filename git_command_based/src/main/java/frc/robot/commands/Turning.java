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
    private final double angle;
    private final boolean isRight;
    private double speedRight;
    private double speedLeft;

    
    // Nuevo Command:
    public Turning(double angle, boolean isRight) {

        m_DriveSubsystem = DriveSubsystem.getInstance();
        this.angle = m_DriveSubsystem.getYaw() + angle; // --> desde donde estás hasta donde llegas.
        this.isRight = isRight;
        addRequirements(m_DriveSubsystem);
    }

    /* Function para girar a la derecha o a la izquierda */

    public void rightOrLeft (boolean direction) {

        if (direction == true) { // si es true, vuelta a la derecha -> speedright positiva.
            speedLeft = 0.55;
            speedRight = -0.55;
            /* speed left = -0.6 */
        } else {
            speedLeft = -0.55;
            speedRight = 0.55;
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
    SmartDashboard.putNumber("Speed Left", speedLeft);
    SmartDashboard.putNumber("Speed Right", speedRight);

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

    // 1. Es positivo?
        // sí --> es mayor o igual al ángulo?
            // sí ---> párate
        // no --> es 

    // condicional p ver si alcanzó el angle:
    if(Math.abs(m_DriveSubsystem.getYaw()) >= Math.abs(angle)){ // valor absoluto de todos los ángulos para no hacer condicionales de ángulos positivos y negativos. 
                                                                // la navX returnea negativos, so esa tmb.
      return true;
    } else {
      return false;
    }

    
  }
}


