package edu.grinnell.csc207.util;

/**
* @author Myles Bohrer-Purnell.
* Course: CSC207 - Object Oriented Programming.
* Mini-Project 2.
* Instructor: Sam Rebelsky.
* 9/20/2024.
*/
public class BFCalculator {
  /** Last computed fraction. */
  private BigFraction lastFraction = new BigFraction(0, 1);

    /** Default Fraction for clearing the calculator. */
  public static final BigFraction DEFAULT_FRACTION = new BigFraction(0, 1);
  /**
  * Get the last calculated fraction.
  *
  * @return
  *   The last calculated fraction stored by lastFraction.
  */
  public BigFraction get() {
    return this.lastFraction;
  } // get
  /**
  * Add the last stored fraction to some value.
  *
  * @param val
  *   A BigFraction to add to lastFraction.
  */
  public void add(BigFraction val) {
    this.lastFraction = this.lastFraction.add(val);
  } // add
  /**
  * Subtract some value from the last stored fraction.
  *
  * @param val
  *   A BigFraction to subtract from lastFraction.
  */
  public void subtract(BigFraction val) {
    this.lastFraction = this.lastFraction.subtract(val);
  } // subtract
  /**
  * multiply the last stored fraction to some value.
  *
  * @param val
  *   A BigFraction to multiply to lastFraction.
  */
  public void multiply(BigFraction val) {
    this.lastFraction = this.lastFraction.multiply(val);
  } // multiply
  /**
  * Divide the last stored fraction and some value.
  *
  * @param val
  *   A BigFraction to divide with lastFraction.
  */
  public void divide(BigFraction val) {
    this.lastFraction = this.lastFraction.divide(val);
  } // divide
  /**
  * Reset lastFraction to a default fraction.
  */
  public void clear() {
    this.lastFraction = DEFAULT_FRACTION;
  } // clear
} // BFCalculator
