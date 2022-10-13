package frc.robot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commandgroups.ShootAuto;
import frc.robot.commandgroups.Autonomous.ThreeBalls.Red31;
import frc.robot.commandgroups.Autonomous.ThreeBalls.Blue32;
import frc.robot.commandgroups.Autonomous.TwoBalls.Blue21;
import frc.robot.commandgroups.Autonomous.TwoBalls.Red21;
import frc.robot.commandgroups.LEDCGs.HandsUp;
import frc.robot.commands.Autonomous.AdjustShooterAngle;
import frc.robot.commands.Autonomous.AutoAngleTurnVoltage;
import frc.robot.commands.Autonomous.GoTillBlack;
import frc.robot.commands.Autonomous.TakeAim;
import frc.robot.commands.Climb.ClimbCommand;
import frc.robot.commands.Climb.ModeChange;
import frc.robot.commands.Climb.ToggleClimbPneumatic;
import frc.robot.commands.DriveTrain.TeleopDrive;
import frc.robot.commands.Feeder.FeederTurn;
import frc.robot.commands.Feeder.runfeederwithswitch;
import frc.robot.commands.Intake.IntakePneumaticPull;
import frc.robot.commands.Intake.IntakePneumaticPush;
import frc.robot.commands.Intake.IntakeTurn;
import frc.robot.commands.Shooter.ChangePneumaticMode;
import frc.robot.commands.Shooter.ShooterTurnManual;
import frc.robot.commands.LED.LEDCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;
//import frc.robot.commands.LED.LEDCommand;
import frc.robot.commandgroups.Autonomous.ThreeBalls.Red32;

public class RobotContainer {

  public static final Joystick stick = new Joystick(Constants.OI.kStickId);
  public static final Joystick panel = new Joystick(Constants.OI.kPanelId);
  public static final XboxController xstick = new XboxController(Constants.OI.kXboxStick);

  public final DriveSubsystem m_drive = new DriveSubsystem();
  public final VisionSubsystem m_vision = new VisionSubsystem();
  public final FeederSubsystem m_feeder = new FeederSubsystem();
  public final ShooterSubsystem m_shooter = new ShooterSubsystem();
  public final IntakeSubsystem m_intake = new IntakeSubsystem();
  public final ClimbSubsystem m_climb = new ClimbSubsystem();
  public final LEDSubsystem m_led = new LEDSubsystem();
  

  private final JoystickButton stickButton1 = new JoystickButton(stick, Constants.OI.kButton1);
  private final JoystickButton stickButton2 = new JoystickButton(stick, Constants.OI.kButton2);
  private final JoystickButton stickButton3 = new JoystickButton(stick, Constants.OI.kButton3);
  private final JoystickButton stickButton4 = new JoystickButton(stick, Constants.OI.kButton4);
  private final JoystickButton stickButton5 = new JoystickButton(stick, Constants.OI.kButton5);
  private final JoystickButton stickButton7 = new JoystickButton(stick, Constants.OI.kButton7);
  private final JoystickButton stickButton8 = new JoystickButton(stick, Constants.OI.kButton8);
  private final JoystickButton stickButton6 = new JoystickButton(stick, Constants.OI.kButton6);
  private final JoystickButton stickButton9 = new JoystickButton(stick, Constants.OI.kButton9);
  private final JoystickButton stickButton10 = new JoystickButton(stick, Constants.OI.kButton10);
  private final JoystickButton stickButton11 = new JoystickButton(stick, Constants.OI.kButton11);
  private final JoystickButton stickButton12 = new JoystickButton(stick, Constants.OI.kButton12);

private final JoystickButton A_Button = new JoystickButton(xstick, Constants.OI.kButton1);
private final JoystickButton B_Button = new JoystickButton(xstick, Constants.OI.kButton2);
private final JoystickButton X_Button = new JoystickButton(xstick, Constants.OI.kButton3);
private final JoystickButton Y_Button = new JoystickButton(xstick, Constants.OI.kButton4);
private final JoystickButton LB_Button = new JoystickButton(xstick, Constants.OI.kButton5);
private final JoystickButton RB_Button = new JoystickButton(xstick, Constants.OI.kButton6);
private final JoystickButton Set1_Button = new JoystickButton(xstick, Constants.OI.kButton7);
private final JoystickButton Set2_Button = new JoystickButton(xstick, Constants.OI.kButton8);

  public RobotContainer() {

    m_drive.setDefaultCommand(
        new TeleopDrive(
            m_drive,
            () -> stick.getRawAxis(0),
            () -> stick.getRawAxis(1),
            () -> stick.getThrottle()));
          
  //m_shooter.setDefaultCommand(new ShootAuto(m_shooter, m_vision, m_led));

  m_led.setDefaultCommand(new LEDCommand(m_vision, m_shooter, m_led));

  configureButtonBindings();

  }

private void configureButtonBindings() {

/*new Trigger(() -> !m_feeder.isBallIn())
.whenActive(
new FeederTurn(m_feeder, 1)
.withInterrupt((() -> m_feeder.getSwitchValue() || xstick.getRawButton(11))));
*/
stickButton1.whileHeld(new FeederTurn(m_feeder, 1));

new JoystickButton(xstick, 12).whileHeld(new FeederTurn(m_feeder, -1));

stickButton2.whileHeld(new IntakeTurn(m_intake, 1));

stickButton3.whileHeld(new TakeAim(m_drive, m_vision, m_led));

stickButton5.whileHeld(new ShootAuto(m_shooter, m_vision, m_led));

stickButton11.whenPressed(new ToggleClimbPneumatic(m_climb,false));

stickButton12.whenPressed(new ToggleClimbPneumatic(m_climb,true));

X_Button.whenPressed(new ToggleClimbPneumatic(m_climb,false));

B_Button.whenPressed(new ToggleClimbPneumatic(m_climb,true));

stickButton10.whenPressed(new InstantCommand(() -> m_shooter.pushPneumatic()));

stickButton9.whenPressed(new InstantCommand(() -> m_shooter.pullPneumatic()));

Y_Button.whenHeld(new ShooterTurnManual(
m_shooter, 
() -> stick.getThrottle(),
() -> m_shooter.pneumatic_mode));


A_Button.whenHeld(new IntakeTurn(m_intake, -1));

RB_Button.whenHeld(new ClimbCommand(m_climb, -0.7));

LB_Button.whenHeld(new ClimbCommand(m_climb, 0.7));

Set1_Button.whenPressed(new IntakePneumaticPull(m_intake));

Set2_Button.whenPressed(new IntakePneumaticPush(m_intake));

//7kapa 8ac
    //A_Button.whileHeld(new IntakeTurn(m_intake, -1));
    //stickButton3.whileHeld(new FeederTurn(m_feeder, 0.3));
   // stickButton4.whileHeld(new FeederTurn(m_feeder, -0.3));
    //stickButton5.whileHeld(new runfeederwithswitch(m_feeder));
      //stickButton6.whileHeld(new alertcommand(m_climb));
    //LB_Button.whileHeld(new ClimbCommand(m_climb, 0.7));
    //RB_Button.whileHeld(new ClimbCommand(m_climb, -0.7));
    //panelButton3.whileHeld(new GoTillBlack(m_drive));
    //Y_Button.whenPressed(new TakeAim(m_drive, m_vision, m_led));
  //  panelButton8.whenPressed(new ModeChange(m_climb));
    //stickButton7.whenPressed(new ToggleClimbPneumatic(m_climb,true));
    //stickButton7.whenHeld(new ShootAuto(m_shooter, m_vision, m_led));
    //stickButton8.whenPressed(new ToggleClimbPneumatic(m_climb,false));
    //stickButton8.whenPressed(new TakeAim(m_drive, m_vision, m_led));
    //stickButton9.whenPressed(new ToggleClimbPneumatic(m_climb,true));
    //X_Button.whenPressed(new IntakePneumaticPush(m_intake));
    //B_Button.whenReleased(new IntakePneumaticPull(m_intake));
    //new JoystickButton(panel, 9).whileHeld(new HandsUp(m_led));
    //new JoystickButton(stick, 8).whenPressed(new AutoAngleTurnVoltage(m_drive, 90));
  }

  public Command getAutonomousCommand(int mode) {

    String alliance = DriverStation.getAlliance().toString();

    if (alliance == "Blue") {
      switch (mode) {

        case 1:
          return new Blue21(m_drive, m_feeder, m_intake, m_shooter, m_vision, m_led);

        case 2:
          return new Blue32(m_drive, m_feeder, m_intake, m_shooter, m_vision, m_led);

        default:
          return new Blue21(m_drive, m_feeder, m_intake, m_shooter, m_vision, m_led);
      }
    } else {
      switch (mode) {

        case 1:
          return new Red21(m_drive, m_feeder, m_intake, m_shooter, m_vision, m_led);

        case 2:
          return new Red32(m_drive, m_feeder, m_intake, m_shooter, m_vision, m_led);

        default:
          return new Blue21(m_drive, m_feeder, m_intake, m_shooter, m_vision, m_led);
      }
    }
  }
}
