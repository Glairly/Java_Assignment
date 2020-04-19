/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university.FrontEnd;

import java.util.TimerTask;

/**
 *
 * @author USER
 */
public abstract class coresTimerTask extends TimerTask {

    private boolean run = false;

    public boolean isRun() {
        return this.run;
    }

    public void setRun(boolean r) {
        this.run = r;
    }

}
