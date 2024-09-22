package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

public class InteractiveCalculator {

  public static int checkValid(String input, BFRegisterSet reg1){
    char[] valid = new char[] {'-', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '/'};
    char[] reg_char = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    for(int i = 0; i < input.length(); i++){
      for(char nums : valid){
        int valLett = 0;
        for(char letters : reg_char){
          if(letters == input.charAt(i)){
            if(reg1.get(input.charAt(i)) == null){
              return 0;
            }
            valLett = 1;
            break;
          } // if
        } // for
        if (valLett == 1){
          break;
        } else if(input.charAt(i) == nums){
          break;
        } else if(nums == '/'){
          return 0;
        } // else if
      } // for
    } // for
    return 1;
  } // checkValid

  public static int checkOperator(String input){
    char[] valid = new char[] {'+', '/', '*', '-'};
    if(input.length() > 1){
      return 0;
    } // if
    for(char nums : valid){
      if(input.charAt(0) == nums){
        break;
      } else if(nums == '-'){
        return 0;
      } // else if
    } // for
    return 1;
  } // checkOperator

  public static int alphabetCheck(String character){
    char[] reg_char = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    if(character.length() != 1){
      return 0;
    }  // if
    for(char letter : reg_char){
      if(letter == character.charAt(0)){
        return 1;
      } // if
    } // for
    return 0;
  } // alphabetCheck

  public static void calculateValid(String[] values, BFCalculator calc1, BFRegisterSet reg1, int pos){
    char[] validOps = new char[] {'+', '/', '*', '-'};
    char[] reg_char = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    BigFraction varFrac = new BigFraction(0,1);
    BigFraction initFrac = new BigFraction(0,1);
    int Initfound = 0;
    for(char chars : reg_char){
      if(values[0].charAt(0) == chars){
        initFrac = reg1.get(values[0].charAt(0));
        Initfound = 1;
        break;
      } // if
    } // for
    if(Initfound == 0){
      initFrac = new BigFraction(values[0]);
    } // if
    calc1.add(initFrac);
    while(!(pos > values.length-1)){
      int found = 0;
      for(char chars : reg_char){
        if(values[pos+1].charAt(0) == chars){
          varFrac = reg1.get(values[pos+1].charAt(0));
          found = 1;
          break;
        } // if
      } // for
      if(found == 0){
        varFrac = new BigFraction(values[pos+1]);
      } // if
      if(values[pos].charAt(0) == '+') {
        calc1.add(varFrac);
      } else if(values[pos].charAt(0) == '/'){
        calc1.divide(varFrac);
      } else if(values[pos].charAt(0) == '*'){
        calc1.multiply(varFrac);
      } else if(values[pos].charAt(0) == '-'){
        calc1.subtract(varFrac);
      } // else if
      pos+=2;
    } // while
  } // calculateValid

  public static void main(String[] args) {
    int check = 0;
    BFCalculator calc1 = new BFCalculator();
    BFRegisterSet reg1 = new BFRegisterSet();

    while(1 == 1){
      PrintWriter pen = new PrintWriter(System.out, true);
      Scanner eyes = new Scanner(System.in);
      String stuff = eyes.nextLine();
      String[] values = stuff.split(" ");
      if(stuff.isEmpty()){
        System.err.println("[ERROR] INVALID INPUT");
      } else {
        // check for quit message
        if(values[0].equals("QUIT") && values.length == 1) {
          break;
        // check for storage
        } else if (values[0].equals("STORE") && values.length == 2 && alphabetCheck(values[1]) == 1) {
          // fix by creating an object BFRegister
            reg1.store(values[1].charAt(0), calc1.get());
        } else if (values.length == 1) {
          // checking for one fraction or number input
            if(checkValid(values[0], reg1) == 0){
              pen.print("[ERROR] INVALID INPUT");
            } else {
              calc1.clear();
              char[] reg_char = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
              int valLett = 0;
              for(char letters : reg_char){
                if(letters == values[0].charAt(0)) {
                    valLett = 1;
                    break;
                  } // if
                } // for
              if (valLett == 1){
                pen.print(reg1.get(values[0].charAt(0)));
              } else {
                // create a big fraction with input and add to calculator
                BigFraction addFrac = new BigFraction(values[0]);
                calc1.add(addFrac);
                // print either fraction or whole number
                if(calc1.get().denominator().equals(BigInteger.ONE)){
                  pen.print(calc1.get().numerator());
                } else {
                  pen.print(calc1.get());
                } // else
              } // else
            } // else
          } else if((values.length-1) % 2 != 0){
            pen.print("[ERROR] INVALID INPUT");
          } else {
              int validCuh = 0;
              // check validity of input
              for(int i = 0; i < values.length; i++) {
                if(i%2 == 0){
                  check = checkValid(values[i], reg1);
                } else{
                  check = checkOperator(values[i]);
                } // else
                if(check == 0){
                  pen.print("[ERROR] INVALID INPUT");
                  validCuh = 1;
                  break;
                } // if
              } // for
              if(validCuh != 1) {
                calc1.clear();
                calculateValid(values, calc1, reg1, 1);
                if(calc1.get().denominator().equals(BigInteger.ONE)){
                  pen.print(calc1.get().numerator());
                } else {
                  pen.print(calc1.get());
                } // else
              } // if
          } // else
          pen.print("\n");
          pen.flush();
        }
      } // while (1 == 1) : input loop
    } // main
  } // Interactive Caluclator
