package com.example.assignment2;



public class RestaurantInfo {
    private String name,location,phone,rating,desc;

    public RestaurantInfo()
    {

    }

    public RestaurantInfo(String name,String location,String phone,String rating,String desc)
    {
        this.name=name;
        this.location=location;
        this.phone=phone;
        this.rating=rating;
        this.desc=desc;
    }


    public String getName()
    {
        return name;
    }

    public String getLocation()
    {
        return location;
    }
    public String getPhone()
    {
        return phone;
    }
    public String getRating()
    {
        return rating;
    }
    public String getDesc()
    {
        return desc;
    }

    public void setName(String name)
    {
        this.name=name;
    }
    public void setLocation(String location)
    {
        this.location=location;
    }

    public void setPhone(String phone)
    {
        this.phone=phone;
    }

    public void setRating (String rating)
    {
        this.rating=rating;
    }

    public void setDesc(String desc)
    {
        this.desc=desc;
    }

}
