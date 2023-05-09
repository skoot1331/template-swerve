
package frc.robot.commands;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

/** An example command that uses an example subsystem. */
public class DriveForwardByTime extends CommandBase {
    private DrivetrainSubsystem drivetrain;
    private int command_duration;
    private final Timer timer; 
    private ChassisSpeeds chassisSpeeds;

  public DriveForwardByTime(DrivetrainSubsystem drivetrain, int command_duration) {
    this.drivetrain = drivetrain;
    this.command_duration = command_duration;
    this.timer = new Timer();
    this.chassisSpeeds = new ChassisSpeeds(Constants.kAutoDriveSpeed, 0.0, 0.0);

    addRequirements(this.drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.drive(chassisSpeeds);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
  }

  @Override
  public boolean isFinished() {
    return timer.hasElapsed(command_duration);
  }
}