/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanliquancaphe.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "danhmuc")
public class DanhMuc implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "maDM")
    private int maDM;
    @Column(name = "tenDM")
    private String tenDM;
   
    public DanhMuc() {

    }

    public DanhMuc(int maDM, String tenDM) {
        this.maDM = maDM;
        this.tenDM = tenDM;
    }

    @Override
    public String toString() {
        return this.tenDM;
    }

    /**
     * @return the maDM
     */
    public int getMaDM() {
        return maDM;
    }

    /**
     * @param maDM the maDM to set
     */
    public void setMaDM(int maDM) {
        this.maDM = maDM;
    }

    /**
     * @return the tenDM
     */
    public String getTenDM() {
        return tenDM;
    }

    /**
     * @param tenDM the tenDM to set
     */
    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }

 
}
