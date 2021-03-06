package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Tim on 10/25/2015.
 */
public class MidknightAutonRed extends OpHelperClean{


    //establish run states for auton
    enum RunState{
        RESET_STATE,
        FIRST_STATE,
        FIRST_RESET,
        SECOND_STATE,
        SECOND_RESET,
        THIRD_STATE,
        THIRD_RESET,
        FOURTH_STATE,
        FOURTH_RESET,
        FIFTH_STATE,
        FIFTH_RESET,
        SIXTH_STATE,
        LAST_STATE
    }

    private RunState rs = RunState.RESET_STATE;

    public MidknightAutonRed() {}


    @Override
    public void loop() {


        basicTel();
        setToEncoderMode();

        switch(rs) {
            case RESET_STATE:
            {

                rs=RunState.FIRST_STATE;
                break;
            }
            case FIRST_STATE:
            {

                if(runStraight(-22, false) ){
                    rs = RunState.FIRST_RESET;
                }
                break;
            }
            case FIRST_RESET:
            {
                if(resetEncoders())
                {
                    rs = RunState.SECOND_STATE;
                }
                break;

            }
            case SECOND_STATE:
            {
                if (setTargetValueTurn(-43))
                {
                    rs = RunState.SECOND_RESET;
                }
                break;
            }
            case SECOND_RESET:
            {
                if(resetEncoders()){
                    rs = RunState.THIRD_STATE;
                }
                break;
            }
            case THIRD_STATE:
            {
                if(runStraight(60,false))
                {
                    rs = RunState.THIRD_RESET;
                }
                break;
            }
            case THIRD_RESET:
            {
                if(resetEncoders()){
                    rs = RunState.FOURTH_STATE;
                }
                break;
            }
            case FOURTH_STATE:
            {
                if(setTargetValueTurn(-100)){
                    rs = RunState.FOURTH_RESET;
                }
                break;
            }
            case FOURTH_RESET:
            {
                if(resetEncoders()){
                    rs = RunState.FIFTH_STATE;
                }
                break;
            }
            case FIFTH_STATE:
            {
                if (setZipLinePosition(1))
                {
                    rs = RunState.FIFTH_RESET;
                }
            }
            case FIFTH_RESET:
            {
                if (resetEncoders()){
                    rs = RunState.SIXTH_STATE;
                }
            }
            case SIXTH_STATE:
            {
                if(runStraight(70,true))
                {
                    rs = RunState.LAST_STATE;
                }
            }
            case LAST_STATE:
            {
                stop();
            }
        }
    }
}
