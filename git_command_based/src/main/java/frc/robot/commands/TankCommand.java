package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class TankCommand  extends CommandBase{
    
    // Instance de los subsystems que vas a usar:
    private final DriveSubsystem m_DriveSubsystem;
    
    Supplier<Double> izquierda;
    Supplier<Double> derecha;
    

    //double izquierda;
    //double derecha;
    // Nuevo Command:
    public TankCommand(
            Supplier<Double> izquierda, Supplier<Double> derecha){ // Supplier: son funciones que dan el trigger del joystick más reciente.

        m_DriveSubsystem = DriveSubsystem.getInstance();
        this.izquierda = izquierda;
        this.derecha = derecha;
        addRequirements(m_DriveSubsystem);
    }


// Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Tank Subsystem Started!");
    m_DriveSubsystem.resetEncoders();
    m_DriveSubsystem.inTankDrive(); // --> jala?

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    DriveSubsystem.tankDrive(izquierda.get(), derecha.get()); // --> se tuvo que cambiar a static el método de setMotor()
  }

  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("TankDrive Subsystem Ended Succesfully!");

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      return false; 
    
  }
}


