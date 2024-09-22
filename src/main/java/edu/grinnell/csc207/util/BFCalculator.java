package edu.grinnell.csc207.util;

public class BFCalculator {

  BigFraction lastFraction = new BigFraction(0, 1);

  BigFraction DEFAULT_FRACTION = new BigFraction(0, 1);

  public BigFraction get(){
    return this.lastFraction;
  }
  public void add(BigFraction val){
    this.lastFraction = this.lastFraction.add(val);
  }
  public void subtract(BigFraction val){
    this.lastFraction = this.lastFraction.subtract(val);
  }
  public void multiply(BigFraction val){
    this.lastFraction = this.lastFraction.multiply(val);
  }
  public void divide(BigFraction val){
    this.lastFraction = this.lastFraction.divide(val);
  }
  public void clear(){
    this.lastFraction = DEFAULT_FRACTION;
  }

  public static void main(String[] args){
    
  }
}
