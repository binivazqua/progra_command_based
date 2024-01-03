// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.NeitoSubsystem;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class NeitoCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final NeitoSubsystem m_subsystem;
  double speed;
  RelativeEncoder neitoEncoder;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */

  public NeitoCommand(double speed) { // --> no le cambies el nombre al command pq sino SE MUERE TODO.
    m_subsystem = NeitoSubsystem.getInstance();
    this.speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Neito Subsystem Started!");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    NeitoSubsystem.setMotor(speed); // --> se tuvo que cambiar a static el método de setMotor()
  }

  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Neito Subsystem Ended Succesfully!");
    NeitoSubsystem.setMotor(0 ); // --> se acabó so, lo paramos.

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (NeitoSubsystem.getLimitSwitch() == true) { // LIMIT SWITH ESTÁ INVERTIDO, SO...
      return true; // si está en true, está apretado -> termina el command
    } else {
      return false; // no está pusheado -> continua 
    }
  }
}
