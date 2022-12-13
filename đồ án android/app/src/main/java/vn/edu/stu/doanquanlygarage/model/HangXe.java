package vn.edu.stu.doanquanlygarage.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HangXe implements Serializable {
    private int ma;
    private String tenHang;
    private List<Xe> xeList = new ArrayList<>();

    public HangXe(int ma, String tenHang) {
        this.ma = ma;
        this.tenHang = tenHang;

    }

    public HangXe() {
    }

    @Override
    public String toString() {
        return this.tenHang;
    }

    public void addXeToHang(Xe xe){
        xeList.add(xe);
        xe.setHangXe(this);
    }

    public void xoaXeInHang(){
        this.getXeList().clear();

    }
    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public List<Xe> getXeList() {
        return xeList;
    }

    public void setXeList(List<Xe> xeList) {
        this.xeList = xeList;
    }
}
