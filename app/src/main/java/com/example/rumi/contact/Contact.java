package com.example.rumi.contact;

/**
 * Created by Rumi on 3/11/2017.
 */

public class Contact {
    String email,name,address,university,phone,dept,year,blood;
    public void setemail(String email)
    {
        this.email=email;
    }
    public void setname(String name)
    {
        this.name=name;
    }
    public void setaddress(String address)
    {
        this.address=address;
    }
    public void setphone(String phone)
    {
        this.phone=phone;
    }
    public void setuniversity(String university)
    {
        this.university=university;
    }
    public void setdept(String dept)
    {
        this.dept=dept;
    }
    public void setyear(String year)
    {
        this.year=year;
    }
    public void setblood(String blood)
    {
        this.blood=blood;
    }
    public String getemail()
    {
        return email;
    }
    public String getname()
    {
        return name;
    }
    public String getaddress()
    {
        return address;
    }
    public String getphone()
    {
        return phone;
    }
    public String getuniversity()
    {
        return university;
    }
    public String getdept()
    {
        return dept;
    }
    public String getyear()
    {
        return year;
    }
    public String getblood()
    {
        return blood;
    }
}
