import java.util.regex.Pattern;

/** This class represents fractions of form n/d where n and d are long integer 
 * numbers. Basic operations and arithmetics for fractions are provided.
 */
public class Lfraction implements Comparable<Lfraction> {

   private long a;
   private long b;

   /** Main method. Different tests. */
   public static void main (String[] param) {
      // TODO!!! Your debugging tests here
   }

   // TODO!!! instance variables here

   /** Constructor.
    * @param a numerator
    * @param b denominator > 0
    */
   public Lfraction (long a, long b) {
      // TODO!!!
      if (b == 0 && a != 0){
         throw new RuntimeException("can't create! Cause: " +
                 b + " in " + this);
      }
      this.a = a;
      this.b = b;
   }

   /** Public method to access the numerator field. 
    * @return numerator
    */
   public long getNumerator() {
      return a; // TODO!!!
   }

   /** Public method to access the denominator field. 
    * @return denominator
    */
   public long getDenominator() { 
      return b; // TODO!!!
   }

   /** Conversion to string.
    * @return string representation of the fraction
    */
   @Override
   public String toString() {
      return String.valueOf(a) + "/" +  String.valueOf(b);
   }

   /** Equality test.
    * @param m second fraction
    * @return true if fractions this and m are equal
    */
   @Override
   public boolean equals (Object m) {
      Lfraction s = (Lfraction) m;
      if (s.a/s.b != a/b) {
         return false;
      }
      long gcdM = (gcd(s.a, s.b));
      long gcdMain = gcd(a, b);
      Lfraction x = new Lfraction(a, b);
      x.a = a / gcdMain;
      x.b = b / gcdMain;
      Lfraction y = new Lfraction(s.a, s.b);
      y.a = s.a / gcdM;
      y.b = s.b / gcdM;
      return x.a % x.b == y.a % y.b ;// TODO!!!
   }

   /** Hashcode has to be equal for equal fractions.
    * @return hashcode
    */
   @Override
   public int hashCode() {
      long ret = this.a + this.b;
      return (int) ret; // TODO!!!
   }

   /** Sum of fractions.
    * @param m second addend
    * @return this+m
    */
   public Lfraction plus (Lfraction m) {
      long num1 = a;
      long den1 = b;
      long num2 = m.getNumerator();
      long den2 = m.getDenominator();
      Lfraction ret = new Lfraction(0, 0);

      long num, den, x;
      if(den1 == den2)
      {
         num = num1 + num2 ;
         den = den1 ;
      }
      else{
         num = (num1*den2) + (num2*den1);
         den = den1 * den2;
      }
      long gcdMain = gcd(num, den);
      ret.a = num / gcdMain;
      ret.b = den / gcdMain;
      return ret; // TODO!!!
   }
   //https://prepinsta.com/java-program/add-two-fractions-using-java/

   /** Multiplication of fractions.
    * @param m second factor
    * @return this*m
    */
   public Lfraction times (Lfraction m) {
      long num1 = a;
      long den1 = b;
      long num2 = m.getNumerator();
      long den2 = m.getDenominator();
      Lfraction ret = new Lfraction(0, 0);
      long x = num1 * num2;
      long y = den1 * den2;
      long gcdMain = gcd(x, y);
      ret.a = x / gcdMain;
      ret.b = y / gcdMain;
      return ret; // TODO!!!
   }

   /** Inverse of the fraction. n/d becomes d/n.
    * @return inverse of this fraction: 1/this
    */
   public Lfraction inverse() {
      Lfraction ret = new Lfraction(0, 0);
      if (a == 0) {
         throw new RuntimeException("can't inverse, because "
         + a + " is  numerator");
      }
      long x;
      x = a;
      if (a < 0) {
         ret.a = -b;
         ret.b = -x;
      } else {
         ret.a = b;
         ret.b = x;
      }
      return ret; // TODO!!!
   }

   /** Opposite of the fraction. n/d becomes -n/d.
    * @return opposite of this fraction: -this
    */
   public Lfraction opposite() {
      long num1 = a;
      long den1 = b;
      Lfraction ret = new Lfraction(0, 0);
      long x = -a;
      long y = b;
      long gcdMain = gcd(x, y);
      ret.a = -x / gcdMain;
      ret.b = y / gcdMain;
      return ret; // TODO!!!
   }

   /** Difference of fractions.
    * @param m subtrahend
    * @return this-m
    */
   public Lfraction minus (Lfraction m) {
      long num1 = a;
      long den1 = b;
      long num2 = m.getNumerator();
      long den2 = m.getDenominator();
      Lfraction ret = new Lfraction(0, 0);

      long num, den, x;
      if(den1 == den2)
      {
         num = num1 - num2 ;
         den = den1 ;
      }
      else{
         num = (num1*den2) - (num2*den1);
         den = den1 * den2;
      }
      long gcdMain = gcd(num, den);
      if (gcdMain < 0) {
         ret.a = -num / gcdMain;
         ret.b = -den / gcdMain;
      } else {
         ret.a = num / gcdMain;
         ret.b = den / gcdMain;
      }
      return ret; // TODO!!!
   }

   /** Quotient of fractions.
    * @param m divisor
    * @return this/m
    */
   public Lfraction divideBy (Lfraction m) {
      long num1 = a;
      long den1 = b;
      long num2 = m.getNumerator();
      if (num2 == 0) {
         throw new RuntimeException("Divide by zero! " + num2 + " in " + m);
      }
      long den2 = m.getDenominator();
      Lfraction ret = new Lfraction(0, 0);
      long x = num1 * den2;
      long y = num2 * den1;
      long gcdMain = gcd(x, y);
      ret.a = x / gcdMain;
      ret.b = y / gcdMain;
      return ret; // TODO!!!
   }

   /** Comparision of fractions.
    * @param m second fraction
    * @return -1 if this < m; 0 if this==m; 1 if this > m
    */
   @Override
   public int compareTo (Lfraction m) {
      Lfraction temp = this.minus(m);
      if (temp.a > 0) {
         return 1;
      } else if (temp.a < 0) {
         return -1;
      } else return 0;
   }

   /** Clone of the fraction.
    * @return new fraction equal to this
    */
   @Override
   public Object clone() throws CloneNotSupportedException {
      Lfraction ret = new Lfraction(1, 1);
      ret.a = a;
      ret.b = b;
      return ret;
   }

   /** Integer part of the (improper) fraction. 
    * @return integer part of this fraction
    */
   public long integerPart() {
      return a / b; // TODO!!!
   }

   /** Extract fraction part of the (improper) fraction
    * (a proper fraction without the integer part).
    * @return fraction part of this fraction
    */
   public Lfraction fractionPart() {
      Lfraction ret = new Lfraction(a, b);
      Lfraction intPartLfr = new Lfraction(integerPart(), 1);
      System.out.println(intPartLfr);
      if (intPartLfr.a != 0) {
         ret = this.minus(intPartLfr);
         System.out.println(ret);
      } else {
         return ret;
      }
      return ret; // TODO!!!
   }

   /** Approximate value of the fraction.
    * @return numeric value of this fraction
    */
   public double toDouble() {
      return (double) a / (double) b; // TODO!!!
   }

   /** Double value f presented as a fraction with denominator d > 0.
    * @param f real number
    * @param d positive denominator for the result
    * @return f as an approximate fraction of form n/d
    */
   public static Lfraction toLfraction (double f, long d) {
      Lfraction ret = new Lfraction(0, 0);
      double math = Math.round(f * d);
      ret.a = (long) math;
      ret.b = d;
      return ret; // TODO!!!
   }

   /** Conversion from string to the fraction. Accepts strings of form
    * that is defined by the toString method.
    * @param s string form (as produced by toString) of the fraction
    * @return fraction represented by s
    */
   public static Lfraction valueOf (String s) {
      Pattern pattern = Pattern.compile(".*[a-zA-Z]+.*");
      s = s.replaceAll(String.valueOf(pattern), "");
      String[] sss = s.split("/");
      long num = Long.parseLong(sss[0]);
      long den = Long.parseLong(sss[1]);
      return new Lfraction(num, den); // TODO!!!
   }

   // GreatestCommonDivision
   /*
   public Lfraction gcd (long num, long den) {
      Lfraction ret = new Lfraction(0, 0);
      long x = Math.max(num, den);
      for (int i = 1; i <= x; i++) {
         if (num % i == 0 && den % i == 0) {
            num = num / i;
            den = den / i;
         }
      }
      //logic for getting simplified fraction
      long n = 1;
      long p = num;
      long q = den;
      if (num != den) {
         while (n != 0) {
            //storing remainder
            n = num % den;
            if (n != 0) {
               num = den;
               den = n;
            }
         }
      }
      ret.a = p / den;
      ret.b = q / den;
      return ret;
   }

    */

   public long gcd (long num, long den) {
      if (num == 0)
         return den;

      return gcd(den%num, num);
   }
}

