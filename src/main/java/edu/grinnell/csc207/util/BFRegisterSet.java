package edu.grinnell.csc207.util;

/**
* @author Myles Bohrer-Purnell.
* Course: CSC207 - Object Oriented Programming.
* Mini-Project 2.
* Instructor: Sam Rebelsky.
* 9/20/2024.
*/
public class BFRegisterSet {

  /** A constant to parese through the possible characters in the register set. */
  public static final int TWENTY_SIX = 26;

  /** The set of stored values in the register set. */
  private BigFraction[] registerStore = new BigFraction[] {null, null, null, null, null, null,
                                                           null, null, null, null, null, null, null,
                                                           null, null, null, null, null, null, null,
                                                           null, null, null, null, null, null};
  /** The set of possible characters to set in the register. */
  private final char[] regChars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                                             'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                                             's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

  /**
  * Store a BigFraction to a specified character in the the register.
  *
  * @param register
  *   The register character
  * @param val
  *   The BigFraction to be stored
  */
  public void store(char register, BigFraction val) {
    for (int i = 0; i < TWENTY_SIX; i++) {
      if (this.regChars[i] == register) {
        this.registerStore[i] = val;
      } // if
    } // for
  } // store
  /**
  * get the registered value for some stored character in the register set
  * or 0/0 if none is stored.
  *
  * @param register
  *   The register character
  * @return
  *   The registered value or 0/0
  */
  public BigFraction get(char register) {
    for (int i = 0; i < TWENTY_SIX; i++) {
      if (this.regChars[i] == register) {
        return this.registerStore[i];
      } // if
    } // for
    return new BigFraction(0, 0);
  } // get
} // BFRegisterSet
