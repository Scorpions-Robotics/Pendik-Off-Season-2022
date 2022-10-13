// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.LED;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class LEDCommand extends CommandBase {
  LEDSubsystem m_led;
  ShooterSubsystem m_shooter;
  VisionSubsystem m_vision;

  public LEDCommand(VisionSubsystem m_vision, ShooterSubsystem m_shooter, LEDSubsystem m_led) {
    this.m_led = m_led;
    this.m_shooter = m_shooter;
    this.m_vision = m_vision;
    addRequirements(m_led);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
 /*   if (m_shooter.required_rpm - 10 < m_shooter.getShooterEncoderRPM()
    && m_shooter.required_rpm + 10 > m_shooter.getShooterEncoderRPM()) {
    if (m_vision.getHoopB() == 1) {
    m_led.setAll(Color.kAliceBlue);
    } else {
    m_led.setAll(Color.kYellow);
    }
    } else {
    m_led.setAll(Color.kRed);
    }*/


if(m_vision.getHoopB() == 1){
    if (Math.abs(m_shooter.required_rpm) < 100 && m_shooter.getShooterEncoderRPM() > 75) {
    m_led.setAll(Color.kYellow);
      if(Math.abs(m_shooter.required_rpm) < 60){
      m_led.setAll(Color.kBlue);
    }
}
  else{
m_led.setAll(Color.kWhite);
  }
}
else{
m_led.setAll(Color.kRed);
}
SmartDashboard.putNumber("target rpmmm", m_shooter.required_rpm);
SmartDashboard.putNumber("shooter rpm", m_shooter.getShooterEncoderRPM());
  }

  @Override
  public void end(boolean interrupted) {
    m_led.setAll(Color.kRed);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
