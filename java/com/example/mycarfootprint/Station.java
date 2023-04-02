package com.example.mycarfootprint;

public class Station {
    private String name;
    private String price;
    private String amount;
    private String fuel;
    private String date;
    Station(String name, String price, String litre, String fuel, String date) {
        this.name = name;
        this.price = price;
        this.amount = litre;
        this.fuel = fuel;
        this.date = date;
    }

    String getCarbon(){
        if (!this.amount.isEmpty()){   //Check if litre brought has an entry, for the multiplication to be valid
            if (this.fuel=="Gasoline") {
                return Integer.toString((int)Math.round(Double.parseDouble(amount)*2.32));
            } else if (this.fuel=="Diesel"){
                return Integer.toString((int)Math.round(Double.parseDouble(amount)*2.69));
            }
        }
        return "0";
    }

    Double getTotalCost(){    //if price or litre brought entry was empty, return 0
        if (!this.amount.isEmpty() && !this.price.isEmpty()) {
            return Math.round((Double.parseDouble(this.price) * Double.parseDouble(this.amount)) * 100.0) / 100.0;
       }
        return 0.00;
    }
    String getStationName() {
        return this.name;
    }

    void setStationName(String name) {
        this.name = name;
    }

    String getPrice() {return this.price;}

    void setPrice(String price) {this.price=price;}

    String getLitres() {
        return this.amount;
    }

    void setLitres(String litre) {
        this.amount = litre;
    }

    String getFuelName() {return this.fuel;}

    void setFuelName(String fuel) {this.fuel=fuel;}

    String getDate() {return this.date;}
    void setDate(String date) {this.date=date;}


}


