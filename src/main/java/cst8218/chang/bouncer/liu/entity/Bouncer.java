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
 * Bouncer DTO. 
 * @author liuch
 */
@Entity
@XmlRootElement
public class Bouncer implements Serializable {

    private static final long serialVersionUID = 1L;
    //auto generated ID field using JPA
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //limit x and y within the range of 0 to Frame width and height, 600
    @Min(0)
    @Max(F_WIDTH)
    private Integer x;
    @Min(0)
    @Max(F_HEIGHT)
    private Integer y;
    
    private Integer yVelocity;
    
    
    //const values
    public static final int GRAVITY_ACCEL = 1;
    public static final int DECAY_RATE = 1;
    public static final int F_WIDTH = 600;
    public static final int F_HEIGHT = 600;
    public static final int X_DEFAULT = 100;
    public static final int Y_DEFAULT = 100;
    public static final int YV_DEFAULT = 10;

    public Bouncer(){}
    
    //Getter and Setter
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

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void setyVelocity(Integer yVelocity) {
        this.yVelocity = yVelocity;
    }
    
    /** 
     * method to handle the move of the bouncer
     * when touching the top or bottom line, the bouncer speed will decreae by one
     * and speed will reverse
     */
    public void advanceOneFrame() {
        //apply GRAVITY_ACCEL to yV if Bouncer is at the bottom line.
        if (y < F_HEIGHT) {
            yVelocity += GRAVITY_ACCEL;
        }

        //advances its y by its yVelocity (pixels per frame) in each frame
        y += yVelocity;
        //if boucned, decreate speed then revers the direction
        if (y < 0) {
            y = 0;
            yVelocity = -yVelocity - DECAY_RATE;
        }
        
        if (y > F_HEIGHT) {
            y = F_HEIGHT;
            yVelocity = -yVelocity + DECAY_RATE;
        }
    }
    //called by rest layer to set the update non-null attributes of the entity selected in DB
    public void setVariable(Bouncer entity) {
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
    
    //called by rest layer to set the whole entity selectd in DB
    public void setEntity(Bouncer entity) {
        if (entity.getX() != null) {
            this.x = entity.getX();
        } else {
            this.x = X_DEFAULT;
        } 

        if (entity.getY() != null) {
            this.y = entity.getY();
        } else {
            this.y = Y_DEFAULT;
        }

        if (entity.getyVelocity() != null) {
            this.yVelocity = entity.getyVelocity();
        } else {
            this.yVelocity = YV_DEFAULT;
        }
    }

    //POJO default behavior
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    //POJO default behavior
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

    //POJO default behavior
    @Override
    public String toString() {
        return "cst8218.chang.bouncer.liu.Bouncer[ id=" + id + " ]";
    }
    
}
