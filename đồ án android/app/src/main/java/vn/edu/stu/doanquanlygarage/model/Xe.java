package vn.edu.stu.doanquanlygarage.model;

import java.io.Serializable;
import java.util.List;

import vn.edu.stu.doanquanlygarage.utils.Data;

public class Xe implements Serializable {
    private int ma;
    private String tenXe;
    private String mau;
    private int soCho;
    private byte[] hinh;
    private String quocGia;
    private HangXe hangXe;

    @Override
    public String toString() {
        return "Xe{" +
                "ma=" + ma +
                ", tenXe='" + tenXe + '\'' +
                ", mau='" + mau + '\'' +
                ", soCho=" + soCho +
                ", quocGia='" + quocGia + '\'' +
                '}';
    }

    public Xe(int ma, String tenXe, String mau, int soCho, byte[] hinh, String quocGia) {
        this.ma = ma;
        this.tenXe = tenXe;
        this.mau = mau;
        this.soCho = soCho;
        this.hinh = hinh;
        this.quocGia = quocGia;
    }

    public void addHangToXe(HangXe hangXe){
        this.setHangXe(hangXe);
        hangXe.getXeList().add(this);
    }
    public void xoaHang() {
        List<Xe> xeList = this.hangXe.getXeList();
        for (Xe xe : xeList) {
            if (xe.getMa() == this.ma) {
                xeList.remove(xe);
                break;
            }
        }
        this.hangXe = null;
    }

    public Xe() {
    }
    public void xoaXe(){
        for (Xe xe:this.getHangXe().getXeList()){
            if (this == xe){

            }
        }
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public int getSoCho() {
        return soCho;
    }

    public void setSoCho(int soCho) {
        this.soCho = soCho;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    public HangXe getHangXe() {
        return hangXe;
    }

    public void setHangXe(HangXe hangXe) {
        this.hangXe = hangXe;
    }
}
