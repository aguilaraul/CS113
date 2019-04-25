package edu.miracosta.cs113;

public class Term implements Comparable {

    public static void main(String[] args) {
        String poly = "3x^4 + 82x^2 - 9x - 4";

        String[] termsPlus = poly.split(" ");

        System.out.println(poly);
        for(int i = 0; i < termsPlus.length; i++) {
            System.out.println("Term x: " + termsPlus[i]);
        }
    }

    private int coefficient;
    private int exponent;

    public Term(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }


    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public void setAll(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    /**
     * Deep copy constructor
     * @param other used to store deep copy
     */
    public Term(Term other) {
        this.coefficient = other.coefficient;
        this.exponent = other.exponent;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Term other = (Term) obj;

        return (this.coefficient != other.coefficient || this.exponent != other.exponent);
    }

    @Override
    public String toString() {
        return "Coefficient" + coefficient
                + "\nExponent" + exponent;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
