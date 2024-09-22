

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
 


@TeleOp(name = "YA4", group = "LinearOpMode")

public class YA4 extends LinearOpMode {
    private DcMotor frontleftDrive = null;
    private DcMotor backleftDrive = null;
    private DcMotor frontrightDrive = null;
    private DcMotor backrightDrive = null;
//    Servo hookR;
    Servo hookL = null;


    @Override
    public void runOpMode() {
        frontleftDrive = hardwareMap.get(DcMotor.class, "fl");
        frontrightDrive = hardwareMap.get(DcMotor.class, "fr");
        backleftDrive = hardwareMap.get(DcMotor.class, "bl");
        backrightDrive = hardwareMap.get(DcMotor.class, "br");
        
        hookL = hardwareMap.get(Servo.class, "leftHangerServo");
        
        frontleftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontrightDrive.setDirection(DcMotor.Direction.FORWARD);
        backleftDrive.setDirection(DcMotor.Direction.REVERSE);
        backrightDrive.setDirection(DcMotor.Direction.FORWARD);
        
        

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        double drive, turn, strafe;
        double flpower, frpower, blpower, brpower;
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            
            drive =-gamepad1.left_stick_y;
            strafe =gamepad1.left_stick_x;
            turn =gamepad1.right_stick_x;
            
            frpower = drive - turn - strafe;
            brpower = drive - turn + strafe;
            flpower = drive + turn + strafe;
            blpower = drive + turn - strafe;
            
            double maxPower = Math.max(+-       Math.abs(frpower),Math.max(Math.abs(brpower),Math.max(Math.abs(flpower), Math.abs(blpower))));
            if(maxPower > 1){
                frpower /= maxPower;
                brpower /= maxPower;
                flpower /= maxPower;
                blpower /= maxPower;
            }
            
            frontrightDrive.setPower(frpower);
            backrightDrive.setPower(brpower);
            frontleftDrive.setPower(flpower);
            backleftDrive.setPower(blpower);
          
         // if (gamepad1.y){
              
         //      hookL.setPosition(0);
              
         //  } 
          
         //  else if(gamepad1.x){
         //      hookL.setPosition(0.7);
              
         //  }
          
         //  else if(gamepad1.a){
              
         //      hookL.setPosition(1);
              
         //  }

         
            movearm();
            
            
            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
   
        
    
     public void movearm(){
         telemetry.addData("moveArm", "Running");
        if (gamepad1.y ){
            hookL.setPosition(0);
        }
        else if(gamepad1.b ){
            hookL.setPosition(0.5);
        }
        else if(gamepad1.a){
            hookL.setPosition(0.5);
        }
        else if(gamepad1.x){
            hookL.setPosition(0);
        }
     }
}
}
