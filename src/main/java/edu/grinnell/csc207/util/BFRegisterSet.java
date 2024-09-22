package edu.grinnell.csc207.util;

public class BFRegisterSet {

  BigFraction[] register = new BigFraction[] {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};
  char[] reg_char = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
  
  
  public void store(char register, BigFraction val){
    for(int i = 0; i < 26; i++){
      if(this.reg_char[i] == register){
        this.register[i] = val;
      }
    }
  }

  public BigFraction get(char register){
    for(int i = 0; i < 26; i++){
      if(this.reg_char[i] == register){
        return this.register[i];
      }
    }
    return new BigFraction(0,0);
  }

  public static void main(String[] args){

  }
}
