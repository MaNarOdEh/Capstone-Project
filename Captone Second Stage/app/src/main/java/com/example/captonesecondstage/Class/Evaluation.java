package com.example.captonesecondstage.Class;

public class Evaluation {
    String total;
    String number;

    public String getNumber() {
        if(number==null)return "1";
        return number;
    }

    public String getTotal() {
        if(total==null)return "5";
        return total;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    public Evaluation(){

    }
    public void increase(int evl){
        if(total==null||number==null){
            total="5";
            number="1";
        }
        number=Double.parseDouble(number)+1+"";
        total=Double.parseDouble(total)+evl+"";

    }
    public Evaluation(String total,String number){

    }
}
