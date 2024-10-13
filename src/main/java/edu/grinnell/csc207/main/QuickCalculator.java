package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.math.BigInteger;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

/**
* @author Myles Bohrer-Purnell.
* Course: CSC207 - Object Oriented Programming.
* Mini-Project 2.
* Instructor: Sam Rebelsky.
* 9/20/2024.
*/
public class QuickCalculator {
  /** Set of lowercase letters in the alphabet that can be used for register storage. */
  private static final char[] REGCHAR = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                                                   'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                                                   'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                                                   'y', 'z'};

  /**
   * Check the validity of the characters in an input.
   *
   * @param input
   *   An input String from the user.
   * @param reg1
   *   A RegisterSet with stored character-value pairings.
   * @return
   *    1 if valid or 0 if invalid.
   */
  public static int checkValid(String input, BFRegisterSet reg1) {
    char[] valid = new char[] {'-', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '/'};
    for (int i = 0; i < input.length(); i++) {
      for (char nums : valid) {
        int valLett = 0;
        for (char letters : REGCHAR) {
          if (letters == input.charAt(i)) {
            if (reg1.get(input.charAt(i)) == null) {
              return 0;
            } // if
            valLett = 1;
            break;
          } // if
        } // for
        if (valLett == 1) {
          break;
        } else if (input.charAt(i) == nums) {
          break;
        } else if (nums == '/') {
          return 0;
        } // else if
      } // for
    } // for
    return 1;
  } // checkValid

  /**
   * Checks if a string input consists of a single-character operator.
   *
   * @param input
   *    An input String from the user.
   * @return
   *    1 if valid and 0 if invalid.
   */
  public static int checkOperator(String input) {
    char[] valid = new char[] {'+', '/', '*', '-'};
    if (input.length() > 1) {
      return 0;
    } // if (input.length() > 1)
    for (char nums : valid) {
      if (input.charAt(0) == nums) {
        break;
      } else if (nums == '-') {
        return 0;
      } // else if (nums == '-')
    } // for (char nums : valid)
    return 1;
  } // checkOperator

  /**
   * Checks if a String of size 1 is a valid lowercase letter in the alpahbet.
   *
   * @param character
   *    A user-specified string, supposed to be 1 character letter.
   * @return
   *    1 if valid and 0 if not valid.
   */
  public static int alphabetCheck(String character) {
    if (character.length() != 1) {
      return 0;
    } // if
    for (char letter : REGCHAR) {
      if (letter == character.charAt(0)) {
        return 1;
      } // if
    } // for
    return 0;
  } // alphabetCheck

  /**
   * Specifies the different operators and fractions in
   * a string and does appropriate calculations.
   *
   * @param values
   *   A set of user input Strings.
   * @param calc1
   *   A BFCalculator in use.
   * @param reg1
   *   A RegisterSet in use with the calculator.
   * @param pos
   *   A starting position to look at for the user input.
   */
  public static void calculateValid(String[] values, BFCalculator calc1,
                                    BFRegisterSet reg1, int pos) {
    char[] validOps = new char[] {'+', '/', '*', '-'};
    BigFraction varFrac = new BigFraction(0, 1);
    BigFraction initFrac = new BigFraction(0, 1);
    int initFound = 0;
    int found = 0;
    for (char chars : REGCHAR) {
      if (values[0].charAt(0) == chars) {
        initFrac = reg1.get(values[0].charAt(0));
        initFound = 1;
        break;
      } // if
    } // for
    if (initFound == 0) {
      initFrac = new BigFraction(values[0]);
    } // if
    calc1.add(initFrac);
    while (!(pos > values.length - 1)) {
      found = 0;
      for (char chars : REGCHAR) {
        if (values[pos + 1].charAt(0) == chars) {
          varFrac = reg1.get(values[pos + 1].charAt(0));
          found = 1;
          break;
        } // if
      } // for
      if (found == 0) {
        varFrac = new BigFraction(values[pos + 1]);
      } // if
      if (values[pos].charAt(0) == '+') {
        calc1.add(varFrac);
      } else if (values[pos].charAt(0) == '/') {
        calc1.divide(varFrac);
      } else if (values[pos].charAt(0) == '*') {
        calc1.multiply(varFrac);
      } else if (values[pos].charAt(0) == '-') {
        calc1.subtract(varFrac);
      } // else if
      pos += 2;
    } // while
  } // calculateValid

  /**
   * Takes a set of command-line arguments and calculates the results using
   * a BFCalculator and BFRegisterSet.
   *
   * @param args
   *   A set of user inputs.
   *
   */
  public static void main(String[] args) {
    int check = 0;
    int valLett = 0;
    PrintWriter pen = new PrintWriter(System.out, true);
    BFCalculator calc1 = new BFCalculator();
    BFRegisterSet reg1 = new BFRegisterSet();
    for (String arguments : args) {
      if (arguments.isEmpty()) {
        System.err.println("[ERROR] INVALID INPUT");
        break;
      } else {
        String[] values = arguments.split(" ");
        if (values[0].equals("QUIT") && values.length == 1) {
          break;
        // check for storage
        } else if (values[0].equals("STORE")
                   && values.length == 2
                   && alphabetCheck(values[1]) == 1) {
          // fix by creating an object BFRegister
          reg1.store(values[1].charAt(0), calc1.get());
          pen.print(arguments + " -> STORED");
        } else if (values.length == 1) {
          // checking for one fraction or number input
          if (checkValid(values[0], reg1) == 0) {
            pen.print("[ERROR] INVALID INPUT");
          } else {
            calc1.clear();
            // int valLett = 0;
            for (char letters : REGCHAR) {
              if (letters == values[0].charAt(0)) {
                valLett = 1;
                break;
              } // if
            } // for
            if (valLett == 1) {
              pen.print(reg1.get(values[0].charAt(0)));
            } else {
              // create a big fraction with input and add to calculator
              BigFraction addFrac = new BigFraction(values[0]);
              calc1.add(addFrac);
              // print either fraction or whole number
              pen.print(arguments + " -> ");
              if (calc1.get().denominator().equals(BigInteger.ONE)) {
                pen.print(calc1.get().numerator());
              } else {
                pen.print(calc1.get());
              } // else
            } // else
          } // else
        } else if ((values.length - 1) % 2 != 0) {
          pen.print("[ERROR] INVALID INPUT");
        } else {
          int validCuh = 0;
          // check validity of input
          for (int i = 0; i < values.length; i++) {
            if (i % 2 == 0) {
              check = checkValid(values[i], reg1);
            } else {
              check = checkOperator(values[i]);
            } // else
            if (check == 0) {
              pen.print("[ERROR] INVALID INPUT");
              validCuh = 1;
              break;
            } // if
          } // for
          if (validCuh != 1) {
            calc1.clear();
            calculateValid(values, calc1, reg1, 1);
            pen.print(arguments + " -> ");
            if (calc1.get().denominator().equals(BigInteger.ONE)) {
              pen.print(calc1.get().numerator());
            } else {
              pen.print(calc1.get());
            } // else
          } // if
        } // else
        pen.print("\n");
        pen.flush();
      } // else
    } // for
  } // main
} // QuickCalculator
