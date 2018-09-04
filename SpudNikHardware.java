package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class SpudNikHardware extends RobotHardware
{
    public static final double SENSITIVITY_FACTOR = 0.5;

    public static final double LEFT_UP_CLAW_OPEN_POS = 0.25, LEFT_DOWN_CLAW_OPEN_POS = 0.75,
            RIGHT_UP_CLAW_OPEN_POS = LEFT_DOWN_CLAW_OPEN_POS, RIGHT_DOWN_CLAW_OPEN_POS = LEFT_UP_CLAW_OPEN_POS;
    public static final double LEFT_UP_CLAW_CLOSE_POS = 0.70, LEFT_DOWN_CLAW_CLOSE_POS = 0.30,
            RIGHT_UP_CLAW_CLOSE_POS = LEFT_DOWN_CLAW_CLOSE_POS, RIGHT_DOWN_CLAW_CLOSE_POS = LEFT_UP_CLAW_CLOSE_POS;
    public static final double EXT_RET_POS = 0.02, EXT_EXT_POS = 0.80;

    public DcMotor     lfDrive     = null;
    public DcMotor     lbDrive     = null;
    public DcMotor     rfDrive     = null;
    public DcMotor     rbDrive     = null;
    public DcMotor     armDrive    = null;
    public DcMotor     horiDrive   = null;
    public Servo       ldClaw      = null;
    public Servo       rdClaw      = null;
    public Servo       luClaw      = null;
    public Servo       ruClaw      = null;
    public Servo       extender    = null;
    public ColorSensor cSensor = null;

    public SpudNikHardware(){
        return;
    }


    // results
    /*
    extender ret 0.02
             ext 0.80
    claws
             loose
             lu 0.38 ld 0.62 ru 0.62 rd 0.38
             tight
             lu 0.64 ld 0.36 ru 0.36 rd 0.64
     */
    @Override
    public void init(HardwareMap ahwMap) {
        super.hwMap = ahwMap;
        lfDrive  = (DcMotor)super.get(DeviceType.DC_MOTOR, "lfDrive" );
        lbDrive  = (DcMotor)super.get(DeviceType.DC_MOTOR, "lbDrive" );
        rfDrive  = (DcMotor)super.get(DeviceType.DC_MOTOR, "rfDrive" );
        rbDrive  = (DcMotor)super.get(DeviceType.DC_MOTOR, "rbDrive" );
        armDrive = (DcMotor)super.get(DeviceType.DC_MOTOR, "armDrive");
        horiDrive = (DcMotor)super.get(DeviceType.DC_MOTOR, "lateral");
        ldClaw   = (Servo)  super.get(DeviceType.SERVO,    "ldClaw"  );
        rdClaw   = (Servo)  super.get(DeviceType.SERVO,    "rdClaw"  );
        luClaw   = (Servo)  super.get(DeviceType.SERVO,    "luClaw"  );
        ruClaw   = (Servo)  super.get(DeviceType.SERVO,    "ruClaw"  );

        cSensor  = (ColorSensor) super.get(DeviceType.COLOR_SENSOR, "cSensor");
        extender = (Servo) super.get(DeviceType.SERVO, "extender");
        luClaw.setPosition(LEFT_UP_CLAW_OPEN_POS );
        ruClaw.setPosition(RIGHT_UP_CLAW_OPEN_POS);
        ldClaw.setPosition(LEFT_DOWN_CLAW_OPEN_POS );
        rdClaw.setPosition(RIGHT_DOWN_CLAW_OPEN_POS);
        return;
    }

    @Override
    public void move(double lPower, double rPower){
        lfDrive.setPower(scalePower(lPower, SENSITIVITY_FACTOR));
        lbDrive.setPower(scalePower(lPower, SENSITIVITY_FACTOR));
        rfDrive.setPower(scalePower(rPower, SENSITIVITY_FACTOR));
        rbDrive.setPower(scalePower(rPower, SENSITIVITY_FACTOR));
        return;
    }

    public void shift(double power){
        horiDrive.setPower(power);
    }

    @Override
    public void moveArm(double power){
        armDrive.setPower(power);
        return;
    }

    @Override
    public void moveClaws(boolean forward){
        // if(forward){
        //     luClaw.setPosition(luClawCurrPos += SpudNikHardware.LEFT_UP_CLAW_SPD);
        //     ruClaw.setPosition(ruClawCurrPos += SpudNikHardware.RIGHT_UP_CLAW_SPD);
        //     ldClaw.setPosition(ldClawCurrPos += SpudNikHardware.LEFT_DOWN_CLAW_SPD);
        //     rdClaw.setPosition(rdClawCurrPos += SpudNikHardware.RIGHT_DOWN_CLAW_SPD);
        //     return;
        // }
        // luClaw.setPosition(luClawCurrPos -= SpudNikHardware.LEFT_UP_CLAW_SPD);
        // ruClaw.setPosition(ruClawCurrPos -= SpudNikHardware.RIGHT_UP_CLAW_SPD);
        // ldClaw.setPosition(ldClawCurrPos -= SpudNikHardware.LEFT_DOWN_CLAW_SPD);
        // rdClaw.setPosition(rdClawCurrPos -= SpudNikHardware.RIGHT_DOWN_CLAW_SPD);
        // return;
    }

    @Override
    public void openClaws(){
        luClaw.setPosition(LEFT_UP_CLAW_OPEN_POS );
        ruClaw.setPosition(RIGHT_UP_CLAW_OPEN_POS);
        ldClaw.setPosition(LEFT_DOWN_CLAW_OPEN_POS );
        rdClaw.setPosition(RIGHT_DOWN_CLAW_OPEN_POS);
    }

    @Override
    public void closeClaws(){
        luClaw.setPosition(LEFT_UP_CLAW_CLOSE_POS );
        ruClaw.setPosition(RIGHT_UP_CLAW_CLOSE_POS);
        ldClaw.setPosition(LEFT_DOWN_CLAW_CLOSE_POS );
        rdClaw.setPosition(RIGHT_DOWN_CLAW_CLOSE_POS);
    }

    public void extExtender(){
        extender.setPosition(EXT_EXT_POS);
    }

    public void retExtender(){
        extender.setPosition(EXT_RET_POS);
    }

    private double scalePower(double power, double factor){
        if(power > 1.0 * factor) return 1.0 * factor;
        if(power < -1.0 * factor) return -1.0 * factor;
        return power;
    }
}