/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.chang.bouncer.liu;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;;

/**
 *
 * @author liuch
 */
@Entity
public class Bouncer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Min(0)
    @Max(F_WIDTH)
    private int x;
    @Min(0)
    @Max(F_HEIGHT)
    private int y;
    
    private int yVelocity;

    //const values
    public static final int GRAVITY_ACCEL = 1;
    public static final int DECAY_RATE = 1;
    public static final int F_WIDTH = 600;
    public static final int F_HEIGHT = 600;

    public Bouncer(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }
    
    /** 
     * method to handle the move of the bouncer
     */
    public void advanceOneFrame() {
        //defensive program to make sure the bouncer is in the proper possion
        //TODO:implement this in JSF instead of here
        if (y < 0) {
            y = 0;
        } else if (y > F_HEIGHT) {
            y = F_HEIGHT;
            yVelocity = 0; //the bouncer is placed on the bottom wall
        }

        //apply GRAVITY_ACCEL to yV if Bouncer is at the bottom line.
        if (y < F_HEIGHT) {
            yVelocity += GRAVITY_ACCEL;
        }

        //advances its y by its yVelocity (pixels per frame) in each frame
        y += yVelocity;
        
        //check if bounced on the top or bottom lines.
        if ((y == 0 && yVelocity != 0) || (y == F_HEIGHT && yVelocity != 0)) {
            yVelocity -= (yVelocity > 0) ? DECAY_RATE : -DECAY_RATE;
            yVelocity = -yVelocity;
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bouncer)) {
            return false;
        }
        Bouncer other = (Bouncer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cst8218.chang.bouncer.liu.Bouncer[ id=" + id + " ]";
    }
    
}
