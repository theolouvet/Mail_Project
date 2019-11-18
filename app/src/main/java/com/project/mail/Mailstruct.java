package com.project.mail;

public class Mailstruct {
    private long id;
    private String address;
    private String pwd;

    public Mailstruct(long id, String address, String pwd){
        super();
        this.id = id;
        this.address = address;
        this.pwd = pwd;
    }

    public long getId() {
        return id;
    }



    public String getAddress() {
        return address.toLowerCase();
    }

    public void setAddress(String address) {
        this.address = address.toLowerCase();
    }

    public String getPwd() {
        return pwd;
    }

    @Override
    public String toString(){
        return Integer.toString((int) id) + " " + address + " " + pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
