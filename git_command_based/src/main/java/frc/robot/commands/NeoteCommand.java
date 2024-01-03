// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.NeitoSubsystem;
import frc.robot.subsystems.NeoSubsystem;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class NeoteCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final NeoSubsystem m_subsystem;
  double speed;
  

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */

  public NeoteCommand(double speed) { // --> no le cambies el nombre al command pq sino SE MUERE TODO.
    m_subsystem = NeoSubsystem.getInstance();
    this.speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Neote Subsystem Started!");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    NeoSubsystem.setMotor(speed); // --> se tuvo que cambiar a static el método de setMotor()
  }

  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Neote Subsystem Ended Succesfully!");
    NeoSubsystem.setMotor(0 ); // --> se acabó so, lo paramos.

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    
      return false; 
    
  }
}
