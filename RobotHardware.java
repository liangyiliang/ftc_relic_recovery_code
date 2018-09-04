package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Represents an overall Robot hardware class structure.
 * Abstract class.
 * Can be implemented by other hardware classes.
 * */
public abstract class RobotHardware {
    protected HardwareMap hwMap = null;
    protected ElapsedTime elapsedTime = new ElapsedTime();
    private OpMode currOpMode = null;
    /**
     * Initializes the hardwares using the HardwareMap parameter.
     * Abstract method.
     * @param ahwMap the HardwareMap used to search and initialize the hardwares.
     * */
    public abstract void init(HardwareMap ahwMap);
    /**
     * Moves the robot using the powers specified.
     * Abstract method.
     * When implemented, if this method is not applicable for the robot,
     * 1) DO NOTHING, or
     * 2) Throw a MethodNotApplicableException
     * @param lPower the power inputed into the left motor.
     * @param rPower the power inputed into the right motor.
     * */
    public abstract void move(double lPower, double rPower);
    /**
     * Moves the arm around using the power specified.
     * Abstract method.
     * When implemented, if this method is not applicable for the robot,
     * 1) DO NOTHING, or
     * 2) Throw a MethodNotApplicableException
     * @param power the power inputed into the arm motor
     * */
    public abstract void moveArm(double power);
    /**
     * Moves the claws around using using the already-specified speed..
     * Abstract method.
     * When implemented, if this method is not applicable for the robot,
     * 1) DO NOTHING, or
     * 2) Throw a MethodNotApplicableException
     * @param forward whether the claws should move forward or not.
     * */
    public abstract void moveClaws(boolean forward);
    public abstract void openClaws();
    public abstract void closeClaws();
    /**
     * sets the current OpMode used.
     * @param o the OpMode
     * */
    public void setCurrOpMode(OpMode o){
        this.currOpMode = o;
    }
    /**
     * Different types of devices connected to the robot.
     * */
    public enum DeviceType {DC_MOTOR, SERVO, COLOR_SENSOR, TOUCH_SENSOR};
    /**
     * From hwMap get the specified hardware using the DeviceType enum and name.
     * @param t specifies which kind of device it is
     * @param name the name of the device
     * */
    public HardwareDevice get(DeviceType t, String name) {
        try {
            if (t == DeviceType.DC_MOTOR) return hwMap.dcMotor.get(name);
            else if (t == DeviceType.SERVO) return hwMap.servo.get(name);
            else if (t == DeviceType.COLOR_SENSOR) return hwMap.colorSensor.get(name);
            else if (t == DeviceType.TOUCH_SENSOR) return hwMap.touchSensor.get(name);
        } catch(IllegalArgumentException e){
            currOpMode.telemetry.addData("Device not found", name);
        }
        return null;
    }

}