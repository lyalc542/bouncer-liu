package cst8218.chang.bouncer.liu;

import cst8218.chang.bouncer.liu.entity.Bouncer;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Singleton bean that starts at application startup and runs a background task to update bouncers.
 */
@Startup
@Singleton
public class BouncerGame {

    @EJB
    private cst8218.chang.bouncer.liu.BouncerFacade bouncerFacade;
    
    private List<Bouncer> bouncers;
    public static final int CHANGE_RATE = 50;//don't know the value
    
    public BouncerGame(){}
    
    //Accessor for the BouncerFacade bean.
    private BouncerFacade getFacade() {
        return bouncerFacade;
    }
    
    //Initializes and starts the background thread for game logic upon bean's construction.
    @PostConstruct
    public void go() {
        new Thread(new Runnable() {
            public void run() {
                // the game runs indefinitely
                while (true) {
                    //update all the bouncers and save changes to the database
                    bouncers = bouncerFacade.findAll();
                    for (Bouncer bouncer : bouncers) {
                        bouncer.advanceOneFrame();
                        bouncerFacade.edit(bouncer);//TODO: validation exception
                    }
                    //sleep while waiting to process the next frame of the animation
                    try {
                        // wake up roughly CHANGE_RATE times per second
                        Thread.sleep((long)(1.0/CHANGE_RATE*1000)); 
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
