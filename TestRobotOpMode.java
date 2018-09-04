package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TestRobotOpMode", group="TestRobot")
@Disabled
public class TestRobotOpMode extends LinearOpMode {
    RobotHardware hw = new TestRobotHardware();
    @Override
    public void runOpMode() {
        hw.setCurrOpMode(this);
        hw.init(hardwareMap);
        telemetry.addData("Hey Vsauce", "Michael Here");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            // hw.move((-gamepad1.left_stick_y + gamepad1.left_stick_x),
            //         (-gamepad1.left_stick_y - gamepad1.left_stick_x));


            if(gamepad1.dpad_up) hw.moveArm(0.2);
            else if(gamepad1.dpad_down) hw.moveArm(-0.2);
            else hw.moveArm(0);

            try {
                if(gamepad1.left_bumper) hw.moveClaws(true);
                else if(gamepad1.right_bumper) hw.moveClaws(false);
            }catch(IllegalArgumentException e){
                // nothing happens here.
            }
            try {
                if (gamepad1.y) ((TestRobotHardware) hw).moveExtender(true);
                else if (gamepad1.a) ((TestRobotHardware) hw).moveExtender(false);
            }catch(Exception e){}

            ((TestRobotHardware)hw).cSensor.enableLed(gamepad1.dpad_right);



            telemetry.addData("luclaw", ((TestRobotHardware)hw).luClaw.getPosition());
            telemetry.addData("ldclaw", ((TestRobotHardware)hw).ldClaw.getPosition());
            telemetry.addData("ruclaw", ((TestRobotHardware)hw).ruClaw.getPosition());
            telemetry.addData("rdclaw", ((TestRobotHardware)hw).rdClaw.getPosition());
            telemetry.addData("extender", ((TestRobotHardware)hw).extender.getPosition());
            telemetry.addData("color red", ((TestRobotHardware)hw).cSensor.red());
            telemetry.addData("color blu", ((TestRobotHardware)hw).cSensor.blue());
            telemetry.addData("color gre", ((TestRobotHardware)hw).cSensor.green());
            telemetry.update();

            sleep(50);
        }
    }
}