package projectview;

import javax.swing.Timer;

class TimerUnit
{
    private static final int TICK = 500;
    private boolean autoStepOn = false;
    private Timer timer;
    private Mediator mediator;
    
    TimerUnit(Mediator mediator)
    {
        this.mediator = mediator;
    }
    
    void start()
    {
        this.timer = new Timer(TimerUnit.TICK, e -> {
            if (this.autoStepOn)
                this.mediator.step();
        });
        
        this.timer.start();
    }
    
    void toggleAutoStep()
    {
        this.autoStepOn = !this.autoStepOn;
    }
    
    void setPeriod(int period)
    {
        this.timer.setDelay(period);
    }
    
    boolean isAutoStepOn()
    {
        return this.autoStepOn;
    }
    
    void setAutoStepOn(boolean on)
    {
        this.autoStepOn = on;
    }
}
