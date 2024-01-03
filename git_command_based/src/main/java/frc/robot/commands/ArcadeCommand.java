package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class ArcadeCommand  extends CommandBase{
    
    // Instance de los subsystems que vas a usar:
    private final DriveSubsystem subsystem;
    Supplier<Double> avance;
    Supplier<Double> giro;

    // Nuevo Command:
    public ArcadeCommand(
            Supplier<Double> avance, Supplier<Double> giro){ // Supplier: son funciones que dan el trigger del joystick más reciente.
        subsystem = DriveSubsystem.getInstance();
        this.avance = avance;
        this.giro = giro;
        addRequirements(subsystem);
    }

    // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Arcade Subsystem Started!");
    subsystem.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    DriveSubsystem.arcadeDrive(avance.get(), giro.get()); // --> se tuvo que cambiar a static el método de setMotor()
  }

  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Arcade Subsystem Ended Succesfully!");

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      return false; 
    
  }
}


