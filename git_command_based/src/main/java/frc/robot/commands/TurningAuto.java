package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;




public class TurningAuto extends SequentialCommandGroup {

    public TurningAuto () {

        addCommands(
            new DriveForward(1.0),
            new Turning(90, true),
            new DriveForward(0.5),
            new Turning(90, false),
            new DriveForward(1.0)

            
        );
    }
}
