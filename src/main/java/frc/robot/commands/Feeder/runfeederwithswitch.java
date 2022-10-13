// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Feeder;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FeederSubsystem;

public class runfeederwithswitch extends CommandBase {
  /** Creates a new runfeederwithswitch. */
  FeederSubsystem m_feeder;
  
  public runfeederwithswitch(FeederSubsystem m_feeder) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_feeder = m_feeder;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_feeder.getSwitchValue() == false) {
      m_feeder.runFeeder(0.1);
    }
    else{
      m_feeder.stopFeeder();
    } 
  }
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_feeder.stopFeeder();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
