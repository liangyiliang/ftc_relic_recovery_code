package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TestRobotHardware extends RobotHardware
{
    public static final double LEFT_CLAW_INIT_POS = 0.5, RIGHT_CLAW_INIT_POS = 0.5;
    public static final double LEFT_DOWN_CLAW_SPD = 0.02, RIGHT_DOWN_CLAW_SPD = -0.02, LEFT_UP_CLAW_SPD = -0.02, RIGHT_UP_CLAW_SPD = 0.02;
    public double ldClawCurrPos = 0.5, rdClawCurrPos = 0.5, luClawCurrPos = 0.5, ruClawCurrPos = 0.5;

    public DcMotor lfDrive    = null;
    public DcMotor lbDrive    = null;
    public DcMotor rfDrive    = null;
    public DcMotor rbDrive    = null;
    public DcMotor armDrive   = null;
    public Servo   ldClaw     = null;
    public Servo   rdClaw     = null;
    public Servo   luClaw     = null;
    public Servo   ruClaw     = null;
    public Servo   extender   = null;
    public ColorSensor cSensor = null;

    public TestRobotHardware(){
        return;
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.hwMap = ahwMap;
        lfDrive  = (DcMotor)super.get(DeviceType.DC_MOTOR, "lfDrive" );
        lbDrive  = (DcMotor)super.get(DeviceType.DC_MOTOR, "lbDrive" );
        rfDrive  = (DcMotor)super.get(DeviceType.DC_MOTOR, "rfDrive" );
        rbDrive  = (DcMotor)super.get(DeviceType.DC_MOTOR, "rbDrive" );
        armDrive = (DcMotor)super.get(DeviceType.DC_MOTOR, "armDrive");
        ldClaw   = (Servo)  super.get(DeviceType.SERVO,    "ldClaw"  );
        rdClaw   = (Servo)  super.get(DeviceType.SERVO,    "rdClaw"  );
        luClaw   = (Servo)  super.get(DeviceType.SERVO,    "luClaw"  );
        ruClaw   = (Servo)  super.get(DeviceType.SERVO,    "ruClaw"  );
        extender = (Servo)  super.get(DeviceType.SERVO,    "extender");
        cSensor  = (ColorSensor) super.get(DeviceType.COLOR_SENSOR, "cSensor");

        luClaw.setPosition(LEFT_CLAW_INIT_POS );
        ruClaw.setPosition(RIGHT_CLAW_INIT_POS);
        ldClaw.setPosition(LEFT_CLAW_INIT_POS );
        rdClaw.setPosition(RIGHT_CLAW_INIT_POS);
        extender.setPosition(0.5);
        return;
    }

    @Override
    public void move(double lPower, double rPower){
        lfDrive.setPower(scalePower(lPower, .5));
        lbDrive.setPower(scalePower(lPower, .5));
        rfDrive.setPower(scalePower(rPower, .5));
        rbDrive.setPower(scalePower(rPower, .5));
        return;
    }

    @Override
    public void moveArm(double power){
        armDrive.setPower(power);
        return;
    }

    @Override
    public void moveClaws(boolean forward){
        if(forward){
            luClaw.setPosition(luClawCurrPos += TestRobotHardware.LEFT_UP_CLAW_SPD);
            ruClaw.setPosition(ruClawCurrPos += TestRobotHardware.RIGHT_UP_CLAW_SPD);
            ldClaw.setPosition(ldClawCurrPos += TestRobotHardware.LEFT_DOWN_CLAW_SPD);
            rdClaw.setPosition(rdClawCurrPos += TestRobotHardware.RIGHT_DOWN_CLAW_SPD);
            return;
        }
        luClaw.setPosition(luClawCurrPos -= TestRobotHardware.LEFT_UP_CLAW_SPD);
        ruClaw.setPosition(ruClawCurrPos -= TestRobotHardware.RIGHT_UP_CLAW_SPD);
        ldClaw.setPosition(ldClawCurrPos -= TestRobotHardware.LEFT_DOWN_CLAW_SPD);
        rdClaw.setPosition(rdClawCurrPos -= TestRobotHardware.RIGHT_DOWN_CLAW_SPD);
        return;
    }

    public void moveExtender(boolean forward){
        if(forward){
            extender.setPosition(extender.getPosition() + 0.03);
        } else {
            extender.setPosition(extender.getPosition() - 0.03);
        }
    }

    public void closeClaws(){}
    public void openClaws(){}
    private double scalePower(double power, double factor) {
        if (power > 1.0 * factor) return 1.0 * factor;
        if (power < -1.0 * factor) return -1.0 * factor;
        return power;
    }
}