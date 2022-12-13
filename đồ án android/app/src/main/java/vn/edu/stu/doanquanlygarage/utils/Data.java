package vn.edu.stu.doanquanlygarage.utils;

import java.util.ArrayList;
import java.util.List;

import vn.edu.stu.doanquanlygarage.model.HangXe;
import vn.edu.stu.doanquanlygarage.model.Xe;

public class Data {
    public static Data instance = null;
    private List<Xe> xeList = null;
    private List<HangXe> hangXeList = null;
    
    public static Data init(){
        if (instance == null){
            instance = new Data(new ArrayList<>(),new ArrayList<>());
        }
        return instance;
    }

    private Data(List<Xe> xeList, List<HangXe> hangXeList) {
        this.xeList = xeList;
        this.hangXeList = hangXeList;
    }

    public Xe timXeTheoMa(int ma){
        for (Xe xe : this.xeList){
            if (xe.getMa() == ma){
                return  xe;
            }
        }
        return null;
    }

    public static Data getInstance() {
        return instance;
    }

    public List<Xe> getXeList() {
        return xeList;
    }

    public void setXeList(List<Xe> xeList) {
        this.xeList = xeList;
    }

    public List<HangXe> getHangXeList() {
        return hangXeList;
    }

    public void setHangXeList(List<HangXe> hangXeList) {
        this.hangXeList = hangXeList;
    }
}
