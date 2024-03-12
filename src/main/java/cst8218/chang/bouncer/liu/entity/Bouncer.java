/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.chang.bouncer.liu.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author liuch
 */
@Entity
@XmlRootElement
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
    public static final int X_DEFAULT = 100;
    public static final int Y_DEFAULT = 100;
    public static final int YV_DEFAULT = 10;

    public Bouncer(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getyVelocity() {
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
        //apply GRAVITY_ACCEL to yV if Bouncer is at the bottom line.
        if (y < F_HEIGHT) {
            yVelocity += GRAVITY_ACCEL;
        }

        //advances its y by its yVelocity (pixels per frame) in each frame
        y += yVelocity;
        
        if (y < 0) {
            y = 0;
            yVelocity = -yVelocity - DECAY_RATE;
        }
        
        if (y > F_HEIGHT) {
            y = F_HEIGHT;
            yVelocity = -yVelocity + DECAY_RATE;
        }
    }
    
    public void update(Bouncer entity) {
        if (entity.getX() != null) {
            this.x = entity.x;
        }
        if (entity.getY() != null) {
            this.y = entity.y;
        }
        if (entity.getyVelocity() != null) {
            this.yVelocity = entity.yVelocity;
        }
    }
    
    public void setDefaultValues() {
        Integer x = this.x;
        Integer y = this.y;
        Integer yV = this.yVelocity;
        
        if (x != null) {
            this.x = X_DEFAULT;
        }
        if (y != null) {
            this.y = Y_DEFAULT;
        }
        if (yV != null) {
            this.yVelocity = YV_DEFAULT;
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
