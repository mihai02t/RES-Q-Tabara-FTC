package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@TeleOp
public class RESQMihai extends LinearOpMode {
    private DcMotor left;
    private DcMotor right;
    private DcMotor elevator;
    private DcMotor catapult;
    private DigitalChannel upLimit;
    private DigitalChannel downLimit;

    @Override
    public void runOpMode() {
        left = this.hardwareMap.get(DcMotor.class, "left");
        right = this.hardwareMap.get(DcMotor.class, "right");
        elevator = this.hardwareMap.get(DcMotor.class, "elevator");
        catapult = this.hardwareMap.get(DcMotor.class, "catapult");
        upLimit = this.hardwareMap.get(DigitalChannel.class, "up");
        downLimit = this.hardwareMap.get(DigitalChannel.class, "down");

        left.setDirection(DcMotor.Direction.REVERSE);

        int flag = 0;

        telemetry.addData("Status", "Ready");
        telemetry.update();

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            double left_stick_y = -this.gamepad1.left_stick_y;
            double right_stick_y = -this.gamepad1.right_stick_y;
            double left_stick_y_2 = -this.gamepad2.left_stick_y;

            left.setPower(left_stick_y);
            right.setPower(right_stick_y);

            if(left_stick_y_2 > 0.0) {
                elevator.setPower(0.7);
            }
            else if(left_stick_y_2 < 0.0) {
                elevator.setPower(-0.7);
            }
            else {
                elevator.setPower(0.0);
            }

            if(this.gamepad1.a) {
                while(this.gamepad1.a && opModeIsActive() && !isStopRequested());
                if(upLimit.getState()) {
                    flag = 1;
                }
                else if(downLimit.getState()) {
                    flag = 2;
                }
            }
            if(flag == 0) {
                catapult.setPower(0.0);
            }
            else if(flag == 1 && !downLimit.getState()) {
                catapult.setPower(-0.2);
            }
            else if(flag == 1 && downLimit.getState()) {
                catapult.setPower(0.0);
                flag = 0;
            }
            else if(flag == 2 && !upLimit.getState()) {
                catapult.setPower(0.2);
            }
            else if(flag == 2 && upLimit.getState()) {
                catapult.setPower(0.0);
                flag = 0;
            }

            telemetry.addData("Status: ", flag);
            telemetry.update();
        }
    }
}


