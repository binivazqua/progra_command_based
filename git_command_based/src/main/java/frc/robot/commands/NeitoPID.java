package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.NeitoSubsystem;

public class NeitoPID extends CommandBase{

    private final NeitoSubsystem neitoSubsystem;
    PIDController pidNeo;
    private double speed;

    public NeitoPID(double goal) { // --> no le cambies el nombre al command pq sino SE MUERE TODO.
        neitoSubsystem = NeitoSubsystem.getInstance();
        this.pidNeo = new PIDController(.005, 0, 0);
        pidNeo.setSetpoint(goal);
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(neitoSubsystem);
      }

      // PID MENSO
      /*
      public double getMovementApprox () {
        return neitoSubsystem.getNeitoEncoder();
      }
    */
      boolean RAS;
      public boolean ReallyAtSetpoint () {
       
        double acceptableRange = pidNeo.getSetpoint() / 2;
        if (pidNeo.getPositionError() < acceptableRange){
          RAS = true;
        }
        return RAS;
      }
      


      // Called when the command is initially scheduled.
      @Override
      public void initialize() {
        System.out.println("Neito Subsystem Started!");
        neitoSubsystem.resetEncoders();
        //pidNeo.setTolerance();
      }
    
      // Called every time the scheduler runs while the command is scheduled.
      @Override
      public void execute() {
        speed = pidNeo.calculate(neitoSubsystem.getNeitoEncoder());
        NeitoSubsystem.setMotor(speed); // --> se tuvo que cambiar a static el método de setMotor()
        SmartDashboard.putNumber("PID Neito Setpoint:", pidNeo.getSetpoint());
        SmartDashboard.putNumber("PID Neito Error", pidNeo.getPositionError());
        SmartDashboard.putBoolean("Neito PID Command AT SETPOINT", pidNeo.atSetpoint());
        SmartDashboard.putBoolean("Neito PID Command IS FINISHED", isFinished());
        SmartDashboard.putNumber("Motor velocity Neito", pidNeo.calculate(neitoSubsystem.getNeitoEncoder()));
        //SmartDashboard.putNumber("Tolerancia PID Neito", pidNeo.getPositionTolerance());

      }
    
    
      // Called once the command ends or is interrupted.
      @Override
      public void end(boolean interrupted) {
        System.out.println("Neito Subsystem Ended Succesfully!");
        SmartDashboard.putBoolean("Neito PID Command AT SETPOINT", pidNeo.atSetpoint());
        SmartDashboard.putBoolean("Neito PID Command IS FINISHED", isFinished());
        NeitoSubsystem.setMotor(0); // --> se acabó so, lo paramos.
    
      }
    
      // Returns true when the command should end.
      @Override
      public boolean isFinished() { // respuesta a la pregunta isFinished?
         
        if (Math.abs(speed) < 0.025) { // VALOR ABSOLUTO: o´por si le mando velocidad negativa
            return true;
        } else{
            return false; // no está pusheado -> continua 
        }
        
      }
    
    
}
