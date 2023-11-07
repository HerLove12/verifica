package com.example;

public class Biglietto {
    Integer n;

    Biglietto(Integer n){
        this.n = n;
    }

    public void updateNumero(){
        this.n = this.n--;
    }

    public Integer getN(){
        return this.n;
    }

    public void setN(Integer n){
        this.n = n;
    }
}
