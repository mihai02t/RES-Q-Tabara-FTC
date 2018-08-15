package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

/**
 * Created by Jay on 05.08.2018.
 */
@TeleOp
public class RESQ extends LinearOpMode {
    private DcMotor left = null;
    private DcMotor right = null;
    private DcMotor elevator = null;
    private DcMotor catapult = null;
    private DigitalChannel upLimit= null;
    private DigitalChannel downLimit = null;
    private int ct=0;
    @Override
    public void runOpMode() {
        left = this.hardwareMap.get(DcMotor.class, "left");
        right = this.hardwareMap.get(DcMotor.class, "right");
        elevator = this.hardwareMap.get(DcMotor.class, "elevator");
        catapult = this.hardwareMap.get(DcMotor.class,"catapult");
        upLimit = this.hardwareMap.get(DigitalChannel.class , "up");
        downLimit = this.hardwareMap.get(DigitalChannel.class , "down");

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            if (this.gamepad1.left_stick_y != 0) {
                left.setPower(this.gamepad1.left_stick_y);
                //left.setPower(gamepad1.left_stick_x);
            }
            if (this.gamepad1.right_stick_y != 0) {
                right.setPower(-this.gamepad1.right_stick_y);
            }
            if (this.gamepad1.left_stick_y == 0) {
                //right.setPower(0);
                left.setPower(0.0);
            }
            if (this.gamepad1.right_stick_y == 0) { right.setPower(0.0); }
            if (this.gamepad2.left_stick_y != 0) {
                elevator.setPower(-this.gamepad2.left_stick_y);
            }
            if(this.gamepad2.left_stick_y == 0)
            {
                elevator.setPower(0.0);
            }
            if(this.gamepad1.a) {
                if(upLimit.getState()) {
                    ct = 0;
                }
                else if(downLimit.getState()) {
                    ct = 1;
                }
            }
            if(ct == 3) {
                catapult.setPower(0.0);
            }
            else if(ct == 0 && !downLimit.getState()) {
                catapult.setPower(-0.2);
            }
            else if(ct == 0 && downLimit.getState()) {
                catapult.setPower(0.0);
                ct = 3;
            }
            else if(ct == 1 && !upLimit.getState()) {
                catapult.setPower(0.2);
            }
            else if(ct == 1 && upLimit.getState()) {
                catapult.setPower(0.0);
                ct = 3;
            }

        }
        /*if(this.gamepad2.right_stick_x != 0)
        {
            if(upLimit.getState()) {catapult.setPower(-0.2); ct = 1;}
            if(downLimit.getState()) {catapult.setPower(0.2);ct=0;}
        }
        else {
            if(downLimit.getState()) {catapult.setPower(0.0);ct=3;}
            if(upLimit.getState()) {catapult.setPower(0.0);ct=3;}
            if (ct == 0) catapult.setPower(0.2);
            if (ct == 1) catapult.setPower(-0.2);
        }
        if(this.gamepad2.right_stick_y > 0 && upLimit.getState()!= true)
        {
            catapult.setPower(0.2);
        }
        else catapult.setPower(0.0);
        if (this.gamepad2.right_stick_y < 0 && upLimit.getState() != true)
        {
            catapult.setPower(-0.2);
        }
        else catapult.setPower(0.0);*/
    }
}


