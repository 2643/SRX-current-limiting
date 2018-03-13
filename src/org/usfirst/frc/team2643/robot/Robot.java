/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	
	//DigitalInput limitSwitch1 = new DigitalInput(0);

	//Servo servo = new Servo(0);
	
	Joystick stick = new Joystick(0);
	
	//WPI_TalonSRX rampWinch = new WPI_TalonSRX(13);
	//WPI_TalonSRX topMotor = new WPI_TalonSRX(1);
	//WPI_TalonSRX bottomMotor = new WPI_TalonSRX(2);
	WPI_TalonSRX rightSlave1Motor = new WPI_TalonSRX(3);
	WPI_TalonSRX rightMasterMotor = new WPI_TalonSRX(4);
	WPI_TalonSRX rightSlave2Motor = new WPI_TalonSRX(5);
	WPI_TalonSRX leftMasterMotor = new WPI_TalonSRX(6);
	WPI_TalonSRX leftSlave1Motor = new WPI_TalonSRX(7);
	WPI_TalonSRX leftSlave2Motor = new WPI_TalonSRX(8);

	Encoder encoder = new Encoder(8,9);
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
		
		rightMasterMotor.configContinuousCurrentLimit(40, 0);
		rightMasterMotor.configPeakCurrentLimit(50, 0);
		rightMasterMotor.configPeakCurrentDuration(250, 0);
		rightMasterMotor.enableCurrentLimit(true);
		leftMasterMotor.configContinuousCurrentLimit(40, 0);
		leftMasterMotor.configPeakCurrentDuration(250, 0);
		leftMasterMotor.configPeakCurrentDuration(50, 0);
		leftMasterMotor.enableCurrentLimit(true);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
			case kCustomAuto:
				// Put custom auto code here
				break;
			case kDefaultAuto:
			default:
				// Put default auto code here
				break;
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		/***
		 * This is an example of a two motor drive. It is much easier to define one motor controllers parameters,
		 * then give it an input. Finally after that is done, one just needs to set the motors in the same system 
		 * to use the follow(); method and the parameter should be the 
		 */
		
		
		rightMasterMotor.set(stick.getRawAxis(1));
		rightSlave1Motor.follow(rightMasterMotor);
		rightSlave2Motor.follow(rightMasterMotor);
		leftMasterMotor.set(stick.getRawAxis(5));
		leftSlave1Motor.follow(leftMasterMotor);
		leftSlave2Motor.follow(leftMasterMotor);
		
		System.out.println(encoder.get());
		
		if(stick.getRawButton(1)) {
			encoder.reset();
		}
		
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
