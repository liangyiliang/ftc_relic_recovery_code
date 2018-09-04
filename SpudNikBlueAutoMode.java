package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="SpudNikAutoBlue", group="SpudNik")
public class SpudNikBlueAutoMode extends LinearOpMode {
    SpudNikHardware hw = new SpudNikHardware();
    @Override
    public void runOpMode() {
        hw.setCurrOpMode(this);
        hw.init(hardwareMap);
        telemetry.addData("Hey Vsauce", "Michael Here");
        telemetry.update();
        waitForStart();
        int counter = 0;
        boolean back = false;
        while (opModeIsActive()) {
            hw.cSensor.enableLed(true);
            switch(counter){
                case 0:
                    hw.extExtender();
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {}
                    counter++;
                    break;
                case 1:
                    if(hw.cSensor.red() > hw.cSensor.blue()){
                        hw.move(-0.3, -0.3);
                        try {
                            Thread.sleep(350);
                        } catch(Exception e){}
                        hw.move(0.0, 0.0);
                        back = true;
                        counter++;
                    } else if(hw.cSensor.blue() > hw.cSensor.red()){
                        hw.move(0.3, 0.3);
                        try {
                            Thread.sleep(350);
                        } catch(Exception e){}
                        hw.move(0.0, 0.0);
                        back = false;
                        counter++;
                    }
                    break;
                case 2:
                    hw.retExtender();
                    counter++;
                    break;
                case 3:
                    hw.move(0.39, 0.45);
                    try{
                        Thread.sleep(back ? 1200 : 1400);
                    } catch(Exception e){}
                    hw.move(0, 0);
                    counter++;
                    break;
                case 4:
                    break;
            }
            telemetry.update();

            sleep(50);
        }
    }
}