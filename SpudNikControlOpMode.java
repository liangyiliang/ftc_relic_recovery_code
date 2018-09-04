package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="SpudNikControlOpMode", group="SpudNik")
public class SpudNikControlOpMode extends LinearOpMode {
    SpudNikHardware hw = new SpudNikHardware();
    @Override
    public void runOpMode() {
        hw.setCurrOpMode(this);
        hw.init(hardwareMap);
        telemetry.addData("Hey Vsauce", "Michael Here");
        telemetry.update();
        hw.retExtender();
        waitForStart();
        while (opModeIsActive()) {
            hw.move((-gamepad1.left_stick_y + gamepad1.left_stick_x / 2),
                    (-gamepad1.left_stick_y - gamepad1.left_stick_x / 2));

            if(-gamepad1.right_stick_y >= 0.5){
                hw.moveArm(0.5);
            } else if(-gamepad1.right_stick_y <= -0.5){
                hw.moveArm(-0.3);
            } else hw.moveArm(0);

            if(gamepad1.right_trigger > 0){
                hw.shift(gamepad1.right_trigger);
            } else if (gamepad1.left_trigger > 0){
                hw.shift(-gamepad1.left_trigger);
            } else {
                hw.shift(0);
            }

            try {
                if(gamepad1.left_bumper) hw.openClaws();
                else if(gamepad1.right_bumper) hw.closeClaws();
            }catch(IllegalArgumentException e){
                // nothing happens here.
            }

            telemetry.update();

            sleep(50);
            idle();
        }
    }
}