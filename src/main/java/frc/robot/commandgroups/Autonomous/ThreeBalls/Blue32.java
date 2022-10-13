// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.Autonomous.ThreeBalls;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commandgroups.ShootAuto;
import frc.robot.commands.Autonomous.AutoAngleTurn;
import frc.robot.commands.Autonomous.AutoStraightDrive;
import frc.robot.commands.Feeder.FeederTurn;
import frc.robot.commands.Intake.IntakePneumaticPush;
import frc.robot.commands.Intake.IntakeTurn;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;


public class balllz3 extends SequentialCommandGroup {

  public balllz3(
  DriveSubsystem m_drive,
  FeederSubsystem m_feeder,
  IntakeSubsystem m_intake,
  ShooterSubsystem m_shooter,
  VisionSubsystem m_vision,
  LEDSubsystem m_led) {
    addCommands(
      new ShootAuto(m_shooter, m_vision, m_led)
      .alongWith(
      new IntakePneumaticPush(m_intake)
      .andThen(new AutoStraightDrive(m_drive,1,false))
      //.raceWith(new IntakeTurn(m_intake, -1))
      
      .andThen(new WaitCommand(0.5))
      
      .andThen(new AutoAngleTurn(m_drive, 165))
      
      
      .andThen(new WaitCommand(0.5))
      .andThen(new FeederTurn(m_feeder, 1).withTimeout(1.5))
      //.deadlineWith(new FeederTurn(m_feeder, 1))
      
      .andThen(new WaitCommand(1))
            
      .andThen(new AutoAngleTurn(m_drive, 76))//.deadlineWith(new FeederTurn(m_feeder, 1))
      
      .andThen(new WaitCommand(1))
      
      .andThen(new AutoStraightDrive(m_drive, 2.35, false)).deadlineWith(new IntakeTurn(m_intake, -1))

      .andThen(new WaitCommand(0.5))
      
      .andThen(new AutoAngleTurn(m_drive, -120))
      
      .andThen(new FeederTurn(m_feeder,1))
      
            )      
    );
  }
}
