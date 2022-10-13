package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
//sol motor 2

//sag motor4
public class ClimbSubsystem extends SubsystemBase {
  private WPI_VictorSPX climbMotor1 = new WPI_VictorSPX(Constants.CAN.kClimbMotorLeft1ID);
  private WPI_VictorSPX climbMotor2 = new WPI_VictorSPX(Constants.CAN.kClimbMotorLeft2ID);
  private WPI_VictorSPX climbMotor3 = new WPI_VictorSPX(Constants.CAN.kClimbMotorRight1ID);
  private WPI_VictorSPX climbMotor4 = new WPI_VictorSPX(Constants.CAN.kClimbMotorRight2ID);

 // MotorControllerGroup left = new MotorControllerGroup(climbMotor1, climbMotor2);
  //MotorControllerGroup right = new MotorControllerGroup(climbMotor3, climbMotor4);

  DigitalInput climbSwitch = new DigitalInput(Constants.CLIMB.climbSwitchPort);

  DoubleSolenoid climbPneumatic =
      new DoubleSolenoid(
          PneumaticsModuleType.CTREPCM,
          Constants.PNEUMATICS.kClimbSolenoidForwardChannel,
          Constants.PNEUMATICS.kClimbSolenoidReverseChannel);

  double result;
  double max_min_in_diff;
  double current_min_in_diff;
  double max_min_out_diff;
  double divide_value;

  boolean reverse_mode;
  boolean pneumatic_mode;

  public ClimbSubsystem() {
    climbMotor1.setNeutralMode(NeutralMode.Brake);
    climbMotor2.setNeutralMode(NeutralMode.Brake);
    climbMotor3.setNeutralMode(NeutralMode.Brake);
    climbMotor4.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {



  SmartDashboard.setDefaultBoolean("switch climb", climbSwitch.get());
  }

  public void runClimbUpwards(double speed) {

/*


climbMotor2.set(-speed);
climbMotor4.set(speed);
*/



  if (climbSwitch.get() == true) {
    climbMotor2.set(0);
    climbMotor4.set(0);
  } 
  else {
    climbMotor2.set(-speed);
    climbMotor4.set(speed);
  }
      }   

public void runclimb(double speed){
    climbMotor2.set(speed);
    climbMotor4.set(-speed);
  
}

  public void runClimbDownwards(double speed) {
/*
 if (climbSwitch.get() == true) {
    climbMotor2.set(0);
    climbMotor4.set(0);
  } 
  else {
    climbMotor2.set(-speed);
    climbMotor4.set(speed);
  }
*/
climbMotor2.set(-speed);
climbMotor4.set(speed);

      }
  

public boolean readswitch(){
return climbSwitch.get();

}


  public void changeMode() {
    if (reverse_mode) {
      reverse_mode = false;
    } else {
      reverse_mode = true;
    }
  }

  public void togglePneumatic() {
    if (pneumatic_mode) {
      climbPneumatic.set(DoubleSolenoid.Value.kReverse);
      pneumatic_mode = false;
    } else {
      climbPneumatic.set(DoubleSolenoid.Value.kForward);
      pneumatic_mode = true;
    }
  }

  public void pullPneumatic() {
    climbPneumatic.set(DoubleSolenoid.Value.kReverse);
   // pneumatic_mode = false;
  }


  public void pushPneumatic() {
    climbPneumatic.set(DoubleSolenoid.Value.kForward);
    //pneumatic_mode = true;
  }

  public void stopClimb() {
    climbMotor2.set(Constants.VARIABLES.kZero);
climbMotor4.set(Constants.VARIABLES.kZero);
    //left.set(Constants.VARIABLES.kZero);
    //right.set(Constants.VARIABLES.kZero);
  }
}
