package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.AdjustShooterAngle;
// import frc.robot.commands.LED.LEDCommand;
import frc.robot.commands.Shooter.ShooterTurnAuto;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class ShootAuto extends SequentialCommandGroup {

  public ShootAuto(ShooterSubsystem m_shooter, VisionSubsystem m_vision, LEDSubsystem m_led) {
    addCommands(
        new AdjustShooterAngle(m_shooter, m_vision)
            .andThen(new ShooterTurnAuto(m_shooter, m_vision))

        // .alongWith(new LEDCommand(m_vision, m_shooter, m_led))

        );
  }
}
