// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ArcadeCommand;
import frc.robot.commands.ComplexAuto;
import frc.robot.commands.NeitoCommand;
import frc.robot.commands.NeoteCommand;
import frc.robot.commands.TankCommand;
import frc.robot.commands.Turning;
import frc.robot.commands.TurningAuto;
import frc.robot.subsystems.DriveSubsystem;

public class RobotContainer {

  /* create instances */
  private final DriveSubsystem chassis;
  public CommandGenericHID control = new CommandGenericHID(Constants.OperatorConstants.kControlPort);

  Trigger square = control.button(Constants.OperatorConstants.ksquarebutton);
  Trigger circle = control.button(OperatorConstants.kcirclebutton);
  Trigger triangle = control.button(OperatorConstants.ktrianglebutton);
  Trigger cross = control.button(OperatorConstants.kcrossbutton);
  Trigger backLeft = control.button(5);
  Trigger backRight = control.button(6);
  Trigger backDownLeft = control.axisGreaterThan(3, 0.1);



  public RobotContainer() {

    /* assign */
    chassis = DriveSubsystem.getInstance();



    /* Set deffault commands */
    chassis.setDefaultCommand(
      new TankCommand(() -> control.getRawAxis(Constants.OperatorConstants.kFwdAxis) * -0.6, () -> control.getRawAxis(Constants.OperatorConstants.kTankFwdAxis) * -0.6)
    );
    configureBindings();

    /* La velocidad ahora es NEGATIVA pq ahora invertimos el motor derecho.  */
  }

  private void configureBindings() {

    /* create buttons and others */
    square.whileTrue(new NeitoCommand(-0.075));
    circle.whileTrue(new NeitoCommand(0.075));

    triangle.whileTrue(new NeoteCommand(0.5));
    cross.whileTrue(new NeoteCommand(-0.5));

    /* arcade-command shift */
    backLeft.toggleOnTrue(new ArcadeCommand(() -> control.getRawAxis(Constants.OperatorConstants.kFwdAxis) * -0.6, () -> control.getRawAxis(Constants.OperatorConstants.kArcadeTurnAxis) * -0.6));

    // Acelerador:
    backRight.toggleOnTrue(new TankCommand(() -> control.getRawAxis(Constants.OperatorConstants.kFwdAxis) * 0.7, () -> control.getRawAxis(Constants.OperatorConstants.kTankFwdAxis) * 0.7));
      
    // Acelerador Arcade:
    backDownLeft.toggleOnTrue(new ArcadeCommand(() -> control.getRawAxis(Constants.OperatorConstants.kFwdAxis) * 0.7, () -> control.getRawAxis(Constants.OperatorConstants.kTankFwdAxis) * 0.7));
    


  }

  public Command getAutonomousCommand() {
    //return new Turning(90, false);
    return new TurningAuto();
  }
}
