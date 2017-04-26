package org.ensure.forgetnot.utility;

/**
 * class Pair.
 * @author rayandrew
 */
public class Pair<A, B> {
  private final A first;
  private final B second;

  /**
   * Constructor.
   * @param first tipe data pertama
   * @param second tipe data kedua
   */
  public Pair(A first, B second) {
    this.first = first;
    this.second = second;
  }

  /**
   * Method untuk mengembalikan int dr hashCode.
   * @return int
   */
  public int hashCode() {
    int hashFirst = first != null ? first.hashCode() : 0;
    int hashSecond = second != null ? second.hashCode() : 0;

    return (hashFirst + hashSecond) * hashSecond + hashFirst;
  }

  /**
   * Method untuk menyamapakan current objek dan Objek other.
   * @param other object yang akan dibandingkan
   * @return boolean apakah current objek sama dengan Objek other
   */
  public boolean equals(Object other) {
    if(other instanceof Pair){
      Pair otherPair = (Pair) other;
      return
          (
              (
                  this.first == otherPair.first || (
                      this.first != null && otherPair.first != null
                          && this.first.equals(otherPair.first)
                  )
              ) && (
                  this.second == otherPair.second
                      || (this.second != null && otherPair.second != null
                      && this.second.equals(otherPair.second))
              )
          );
    }
    return false;
  }

  /**
   * Implementasi toString.
   * @return print Pair
   */
  public String toString() {
    return "(" + first + ", " + second + ")";
  }

  /**
   * getter left value.
   * @return value pertama
   */
  public A getLeft() {
    return first;
  }

  /**
   * getter right value.
   * @return value kedua
   */
  public B getRight() {
    return second;
  }
}
