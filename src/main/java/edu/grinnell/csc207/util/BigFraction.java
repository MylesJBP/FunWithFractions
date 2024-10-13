package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
* @author Myles Bohrer-Purnell.
* Course: CSC207 - Object Oriented Programming.
* Mini-Project 2.
* Instructor: Sam Rebelsky.
* 9/20/2024.
*/
public class BigFraction {

  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /** The default numerator when creating fractions. */
  private static final BigInteger DEFAULT_NUMERATOR = BigInteger.valueOf(0);

  /** The default denominator when creating fractions. */
  private static final BigInteger DEFAULT_DENOMINATOR = BigInteger.valueOf(1);

  /** The number 10 for creating numbertor/denominators. */
  private static final int TEN = 10;

  /** The number 9 for creating numbertor/denominators. */
  private static final int NINE = 9;

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The numerator of the fraction. Can be positive, zero or negative. */
  private BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  private BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(BigInteger numerator, BigInteger denominator) {
    for (int i = TEN; i > 1; i--) {
      BigInteger val = BigInteger.valueOf(i);
      if (numerator.remainder(val).equals(BigInteger.ZERO)
          && denominator.remainder(val).equals(BigInteger.ZERO)) {
        this.num = numerator.divide(val);
        this.denom = denominator.divide(val);
        break;
      } else if (i == 2) {
        this.num = numerator;
        this.denom = denominator;
        break;
      } // else
    } // for
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(int numerator, int denominator) {
    BigInteger numerate = BigInteger.valueOf(numerator);
    BigInteger denominate = BigInteger.valueOf(denominator);
    for (int i = TEN; i > 1; i--) {
      BigInteger val = BigInteger.valueOf(i);
      if ((numerate.remainder(val)).equals(BigInteger.ZERO)
          && (denominate.remainder(val)).equals(BigInteger.ZERO)) {
        this.num = numerate.divide(val);
        this.denom = denominate.divide(val);
        break;
      } else if (i == 2) {
        this.num = numerate;
        this.denom = denominate;
        break;
      } // else
    } // for
  } // BigFraction(int, int)

  /**
   * Build a new fraction by parsing a string.
   *
   *
   * @param str
   *   The fraction in string form
   */
  public BigFraction(String str) {
    String[] bigStr = str.split("/");
    BigInteger numerate = BigInteger.ZERO;
    BigInteger denominate = BigInteger.ONE;
    if (bigStr.length == 1) {
      numerate = new BigInteger(bigStr[0]);
      denominate = BigInteger.ONE;
    } else {
      numerate = new BigInteger(bigStr[0]);
      denominate = new BigInteger(bigStr[1]);
    } // else
    for (int i = TEN; i > 1; i--) {
      BigInteger val = BigInteger.valueOf(i);
      if (numerate.remainder(val).equals(BigInteger.ZERO)
          && denominate.remainder(val).equals(BigInteger.ZERO)) {
        this.num = numerate.divide(val);
        this.denom = denominate.divide(val);
        break;
      } else if (i == 2) {
        this.num = numerate;
        this.denom = denominate;
        break;
      } // else
    } // for
  } // BigFraction

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Express this fraction as a double.
   *
   * @return the fraction approxiamted as a double.
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()

  /**
   * Add another faction to this fraction.
   *
   * @param addend
   *   The fraction to add.
   *
   * @return the result of the addition.
   */
  public BigFraction add(BigFraction addend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(addend.denom);
    resultNumerator =
      (this.num.multiply(addend.denom)).add(addend.num.multiply(this.denom));

    for (int i = TEN; i > 1; i--) {
      BigInteger val = BigInteger.valueOf(i);
      if (resultNumerator.remainder(val).equals(BigInteger.ZERO)
          && resultDenominator.remainder(val).equals(BigInteger.ZERO)) {
        resultNumerator = resultNumerator.divide(val);
        resultDenominator = resultDenominator.divide(val);
        break;
      } // if
    } // for
    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  } // add(BigFraction)

  /**
   * subtract another faction to this fraction.
   *
   * @param subend
   *   The fraction to subtract.
   *
   * @return the result of the subtraction.
   */
  public BigFraction subtract(BigFraction subend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and subend's denominator
    resultDenominator = this.denom.multiply(subend.denom);
    resultNumerator =
      (this.num.multiply(subend.denom)).subtract(subend.num.multiply(this.denom));
    for (int i = TEN; i > 1; i--) {
      BigInteger val = BigInteger.valueOf(i);
      if (resultNumerator.remainder(val).equals(BigInteger.ZERO)
          && resultDenominator.remainder(val).equals(BigInteger.ZERO)) {
        resultNumerator = resultNumerator.divide(val);
        resultDenominator = resultDenominator.divide(val);
        break;
      } // if
    } // for
    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  } // add(BigFraction)

  /**
   * divide another faction to this fraction.
   *
   * @param divend
   *   The fraction to divide.
   *
   * @return the result of the division.
   */
  public BigFraction divide(BigFraction divend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and divend's denominator
    resultDenominator = this.denom.multiply(divend.num);
    resultNumerator =
      (this.num.multiply(divend.denom));

    for (int i = TEN; i > 1; i--) {
      BigInteger val = BigInteger.valueOf(i);
      if (resultNumerator.remainder(val).equals(BigInteger.ZERO)
          && resultDenominator.remainder(val).equals(BigInteger.ZERO)) {
        resultNumerator = resultNumerator.divide(val);
        resultDenominator = resultDenominator.divide(val);
        break;
      } // if
    } // for
    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  } // add(BigFraction)

   /**
   * Multiply another faction to this fraction.
   *
   * @param multend
   *   The fraction to multiply.
   *
   * @return the result of the multiplication.
   */
  public BigFraction multiply(BigFraction multend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and multend's denominator
    resultDenominator = this.denom.multiply(multend.denom);
    resultNumerator =
      (this.num.multiply(multend.num));

    for (int i = TEN; i > 1; i--) {
      BigInteger val = BigInteger.valueOf(i);
      if (resultNumerator.remainder(val).equals(BigInteger.ZERO)
          && resultDenominator.remainder(val).equals(BigInteger.ZERO)) {
        resultNumerator = resultNumerator.divide(val);
        resultDenominator = resultDenominator.divide(val);
        break;
      } // if
    } // for
    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  } // add(BigFraction)

  /**
   * Get the denominator of this fraction.
   *
   * @return the denominator
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()

  /**
   * Get the numerator of this fraction.
   *
   * @return the numerator
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()

  /**
   * Convert this fraction to a string for ease of printing.
   *
   * @return a string that represents the fraction.
   */
  public String toString() {
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } // if it's zero

    if (this.denom.equals(BigInteger.ONE)) {
      return "" + this.num;
    } // if

    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    return this.num + "/" + this.denom;
  } // toString()
} // BigFraction
