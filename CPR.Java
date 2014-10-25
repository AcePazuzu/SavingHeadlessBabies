/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

// Just don't ask kevin
package edu.wpi.first.wpilibj.headlesschildren;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory. Headless Children
 */

public class CPR extends SimpleRobot {
    
    Joystick joyLeft = new Joystick(1);
    Joystick joyRight = new Joystick(2);
    RobotDrive rDrive = new RobotDrive(1, 3, 2, 4);
    Compressor compr = new Compressor(1, 3);
    Relay pickupWheel = new Relay(2);
    Gyro gyroscope = new Gyro(1);
    double angle = 0 ;
    int i = 0;
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        System.out.println("SwagHeadlessBabies");
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {

        compr.start();
        
        pickupWheel.set(Relay.Value.kForward);
        
        while ( isOperatorControl() ) {
           i++;
            if (joyLeft.getTrigger()){
                 rDrive.mecanumDrive_Cartesian(joyLeft.getX(), joyLeft.getY(), 0, 0);
            }
            else{
                rDrive.mecanumDrive_Cartesian( 0, joyLeft.getY(), joyLeft.getX(), 0);
            }
            //rDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
            //rDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
            rDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
            rDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
            if(joyRight.getTrigger()){
                pickupWheel.set(Relay.Value.kForward);
               
            }else{
                pickupWheel.set(Relay.Value.kOff);
            }
            angle = gyroscope.getAngle();
            
            if (i %10 == 0) {
                i = 0;
                System.out.println(angle);
            }
            
            
            
        }
        
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
}
