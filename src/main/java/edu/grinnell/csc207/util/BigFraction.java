package edu.grinnell.csc207.util;

import java.math.BigInteger;

public class BigFraction {
   // +------------------+---------------------------------------------
  // | Design Decisions |
  // +------------------+

  /*
   * (1) Denominators are always positive. Therefore, negative fractions
   * are represented with a negative numerator. Similarly, if a fraction
   * has a negative numerator, it is negative.
   *
   * (2) Fractions are not necessarily stored in simplified form. To
   * obtain a fraction in simplified form, one must call the `simplify`
   * method.
   */

  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /** The default numerator when creating fractions. */
  private static final BigInteger DEFAULT_NUMERATOR = BigInteger.valueOf(0);

  /** The default denominator when creating fractions. */
  private static final BigInteger DEFAULT_DENOMINATOR = BigInteger.valueOf(1);

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The numerator of the fraction. Can be positive, zero or negative. */
  BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * Warning! Not yet stable.
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(BigInteger numerator, BigInteger denominator) {
    this.num = numerator;
    this.denom = denominator;
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * Warning! Not yet stable.
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(int numerator, int denominator) {
    this.num = BigInteger.valueOf(numerator);
    this.denom = BigInteger.valueOf(denominator);
  } // BigFraction(int, int)

  /**
   * Build a new fraction by parsing a string.
   *
   * Warning! Not yet implemented.
   *
   * @param str
   *   The fraction in string form
   */
  public BigFraction(String str) {
    String[] bigStr = str.split("/");
    BigInteger numerate = BigInteger.ZERO;
    BigInteger denominate = BigInteger.ONE;
    if(bigStr.length == 1){
      numerate = new BigInteger(bigStr[0]);
      denominate = BigInteger.ONE;
    } else {
      numerate = new BigInteger(bigStr[0]);
      denominate = new BigInteger(bigStr[1]);
    }
    this.num = numerate;
    this.denom = denominate;
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
    // The numerator is more complicated
    resultNumerator =
      (this.num.multiply(addend.denom)).add(addend.num.multiply(this.denom));

    for(int i = 9; i > 1; i--){
      BigInteger val = BigInteger.valueOf(i);
      if(resultNumerator.remainder(val).equals(BigInteger.ZERO) && resultDenominator.remainder(val).equals(BigInteger.ZERO)){
        resultNumerator = resultNumerator.divide(val);
        resultDenominator = resultDenominator.divide(val);
        break;
      }
    }
    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  } // add(BigFraction)

  public BigFraction subtract(BigFraction subend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(subend.denom);
    // The numerator is more complicated
    resultNumerator =
      (this.num.multiply(subend.denom)).subtract(subend.num.multiply(this.denom));
    
    for(int i = 10; i > 1; i--){
      BigInteger val = BigInteger.valueOf(i);
      if(resultNumerator.remainder(val).equals(BigInteger.ZERO) && resultDenominator.remainder(val).equals(BigInteger.ZERO)) {
        resultNumerator = resultNumerator.divide(val);
        resultDenominator = resultDenominator.divide(val);
        break;
      }
    } 
    
    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  } // add(BigFraction)

  public BigFraction divide(BigFraction devend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(devend.num);
    // The numerator is more complicated
    resultNumerator =
      (this.num.multiply(devend.denom)).multiply(devend.num.multiply(this.num));

    for(int i = 10; i > 1; i--){
      BigInteger val = BigInteger.valueOf(i);
      if(resultNumerator.remainder(val).equals(BigInteger.ZERO) && resultDenominator.remainder(val).equals(BigInteger.ZERO)){
        resultNumerator = resultNumerator.divide(val);
        resultDenominator = resultDenominator.divide(val);
        break;
      }
    }
    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  } // add(BigFraction)

  public BigFraction multiply(BigFraction multend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(multend.denom);
    // The numerator is more complicated
    resultNumerator =
      (this.num.multiply(multend.denom)).multiply(multend.num.multiply(this.denom));

    for(int i = 10; i > 1; i--){
      BigInteger val = BigInteger.valueOf(i);
      if(resultNumerator.remainder(val).equals(BigInteger.ZERO) && resultDenominator.remainder(val).equals(BigInteger.ZERO)){
        resultNumerator = resultNumerator.divide(val);
        resultDenominator = resultDenominator.divide(val);
        break;
      }
    }
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

    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    return this.num + "/" + this.denom;
  } // toString()
}
