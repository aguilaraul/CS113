/**
 * Term.java: This class that holds information about terms that will be added together to form a polynomial. Such information includes
 *            exponents and coefficient information as well as symbols for term construction.
 *
 * Class Invariant: All terms will contain symbols that constructs it (see static variables below). All terms will have a coefficient in from of them
 *                  and an exponent(with the exception of constants; also see variables below)
 *
 * @author Andrew Tran
 * @version 1.0
 *
 */

package models;

public class Term implements Comparable
{
    //Declare and initialize static sariables to hold symbols that constructs a term.
    private final static char PLUS_SIGN = '+' ;
    private final static char NEGATIVE_SIGN = '-' ;
    private final static char VARIABLE_SYMBOL = 'x' ;
    private final static char EXPONENT_SYMBOL = '^' ;

    //Declare and initialize variables to hold the coefficient and exponent information
    private int coefficient ;
    private int exponent ;

    /**Default constructor that sets coefficient and exponent equal to 1*/
    public Term()
    {
        this.coefficient = 1 ;
        this.exponent = 1 ;
    }

    /**
     * Constructor that allows specific coefficients and exponents to be assigned to a term.
     *
     * @param coefficient   coefficient value to be set to objects term coefficient value
     * @param exponent     exponent value to be set to objects term exponent value
     *
     **/
    public Term(int coefficient, int exponent)
    {
        this.coefficient = coefficient ;
        this.exponent = exponent ;
    }

    /**
     *
     * Copy constructor initiates a new term object from a already existing term object
     *
     *
     * @param copyTerm  copy the contents of a term to be initialized with a new term object
     *
     * */
    public Term(Term copyTerm)
    {

        this.coefficient = copyTerm.getCoefficient() ;
        this.exponent = copyTerm.getExponent() ;

    }

    /**
     * Constructor that takes a string of a term and initializes an object term from it
     *
     * @param stringTerm   string to be converted into term object
     *
     **/
    public Term(String stringTerm)
    {
        //Declare placeholder for term coefficients and exponent
        int stringCoefficient,
            stringExponent ;

        //Check to see if stringTerm is empty or not, if not than proceed to initialize term object from stringTerm information
        if(!stringTerm.isEmpty())
        {
            //Check if string contain term variable x
            if(stringTerm.contains(Character.toString(VARIABLE_SYMBOL)))
            {
                //Split the string so that individual tokens are by itself (not joined by variable x) (store in array)
                String[] splitStringTerm = stringTerm.split(Character.toString(VARIABLE_SYMBOL)) ;

                //If symbol is only available stringCoefficient is set to positive one or negative one
                if(splitStringTerm[0].length() == 1)
                {
                    if(splitStringTerm[0].equals("+"))
                    {
                        stringCoefficient = 1 ;
                    }
                    else
                    {
                        stringCoefficient = -1 ;
                    }
                }
                //Get left side of the x variable for coefficient
                else
                {
                    stringCoefficient = Integer.parseInt(splitStringTerm[0]) ;
                }

                //Check if exponent symbol is available by checking the length of the string
                if(splitStringTerm.length == 2)
                {
                    stringExponent = Integer.parseInt(splitStringTerm[1].substring(1)) ;
                }
                else
                {
                    stringExponent = 1 ;
                }
            }
            else
            {
                //If symbol and coefficient are only present(constant) than precede in making them a constant number term
                stringCoefficient = Integer.parseInt(stringTerm) ;
                stringExponent = 0 ;
            }
        }
        else //else when string is empty assign coeff. and exponent to 0
        {
            stringCoefficient = 0 ;
            stringExponent = 0 ;
        }

        this.coefficient = stringCoefficient ;
        this.exponent = stringExponent ;

    }

    //***Setters and Getters***//

    /**
     * Setter allows user to set the coefficient for the term.
     *
     * @param coefficient   sets coefficient of term object
     *
     **/
    public void setCoefficient(int coefficient)
    {
        this.coefficient = coefficient ;
    }

    /**
     * Setter allows user to set the exponent for the term.
     *
     * @param exponent  sets exponent of term object
     *
     **/
    public void setExponent(int exponent)
    {
        this.exponent = exponent;
    }

    /**
     * Getter returns coefficient of term object
     *
     * @return coefficient   coefficient exponent of term object
     *
     **/
    public int getCoefficient()
    {
        return coefficient;
    }

    /**
     * Getter returns exponent of term object
     *
     * @return exponent   returns exponent of term object
     *
     **/
    public int getExponent()
    {
        return exponent;
    }


    /**
     *
     * addTerm method adds term coefficient to calling object term.
     *
     * @param addTerm   Term to be added to calling object.
     * @return returns the summation of calling object term plus addTerm
     *
     **/
    public Term addTerm(Term addTerm)
    {
        return sumOf(this, addTerm) ;
    }


    /**
     *
     * sumOf helper method takes two term objects and adds them together only if the exponents match
     *
     * @param termOne One term to be added
     * @param termTwo One term to be added
     *
     * @return summationTerm  newly added term
     *
     *
     **/
    public static Term sumOf(Term termOne, Term termTwo)
    {
        //Local variables to help calculation
        Term summationTerm = null ;
        int summationCoefficients ;

        //Exponents of the terms must match with eachother for addition to work
        if(termOne.exponent == termTwo.exponent)
        {
            //Add both coefficients together
            summationCoefficients = termOne.coefficient + termTwo.coefficient ;

            //Condition in which the coefficient summation is 0, return null
            if(summationCoefficients == 0)
            {
                return null ;
            }
            else
            {
                //Create new term with summation coefficient
                summationTerm = new Term(summationCoefficients, termOne.exponent) ;
            }
        }

        return summationTerm ;
    }




    /**
     * compareTo method compares exponent values of calling term object with another term object
     *
     * @param otherTerm     term variable to be compared to calling object term
     * @return  1 or -1 or 0    returns 1 if calling object exponent is higher than the compared term object
     *                          returns -1 if calling object exponent is less than the compared term object
     *                          returns 0 if calling object
     *
     **/
    public int compareTo(Object otherTerm)
    {
        Term compareTerm = (Term) otherTerm ;

        if(this.exponent > compareTerm.getExponent())
        {
            return 1 ;
        }
        else if(this.exponent < compareTerm.getExponent())
        {
            return -1 ;
        }

        return 0;
    }

    /**
     *
     * toString method to return the calling object term as a string
     *
     * @return  returns the calling object term as a string
     *
     *
     **/
    public String toString()
    {
        String stringTerm = "" ;

        //If coefficient is equal to zero than return ""
        if(this.coefficient == 0)
        {
            return "" ;
        }

        //If exponent is equal to zero than return without x^#
        if(this.exponent == 0)
        {
            //Add a plus symbol in front of the string if coeff. is greater than 0
            if(this.coefficient > 0)
            {
                stringTerm += PLUS_SIGN ;

                //Add the coefficient in behind the symbol if greater than one
                if(this.coefficient > 1)
                {
                    stringTerm += this.coefficient ;
                }
            }
            //Add negative sign if coeff. is negative
            else
            {
                    stringTerm += this.coefficient ;
            }

            return stringTerm ;

        }

        if(this.exponent >= 1 || this.exponent < -1)
        {
            //Add a plus symbol in front of the string if coeff. is greater than 0
            if(this.coefficient > 0)
            {
                stringTerm += PLUS_SIGN ;

                //Add the coefficient in behind the symbol if greater than one
                if(this.coefficient == 1)
                {
                    stringTerm += VARIABLE_SYMBOL ;

                    if(this.exponent > 1)
                    {
                        stringTerm += EXPONENT_SYMBOL ;
                        stringTerm += this.exponent ;
                    }
                    else if(this.exponent == 1)
                    {
                        return stringTerm ;
                    }

                    else
                    {
                        stringTerm += EXPONENT_SYMBOL ;
                        stringTerm += this.exponent ;
                    }
                }
                else
                {
                    stringTerm += this.coefficient ;
                    stringTerm += VARIABLE_SYMBOL ;

                    if(this.exponent == 1)
                    {
                        return stringTerm ;
                    }
                    if(this.exponent > 1 || this.exponent < -1)
                    {
                        stringTerm += EXPONENT_SYMBOL ;
                        stringTerm += this.exponent ;
                    }
                }

            }
            else
            {

                if(this.coefficient == -1)
                {
                    stringTerm += NEGATIVE_SIGN ;
                    stringTerm += VARIABLE_SYMBOL ;

                    if(this.exponent > 1)
                    {
                        stringTerm += EXPONENT_SYMBOL ;
                        stringTerm += this.exponent ;
                    }
                    if(this.exponent < -1)
                    {
                        stringTerm += EXPONENT_SYMBOL ;
                        stringTerm += this.exponent ;
                    }
                }
                else
                {
                    stringTerm += this.coefficient ;
                    stringTerm += VARIABLE_SYMBOL ;

                    if(this.exponent > 1 || this.exponent < -1)
                    {
                        stringTerm += EXPONENT_SYMBOL ;
                        stringTerm += this.exponent ;
                    }


                }
            }
        }

        return stringTerm ;

    }

    /**
     *
     * setAll method set both the coefficient and exponent of the term.
     *
     **/
    public void setAll(int coefficient, int exponent)
    {
        this.coefficient = coefficient ;
        this.exponent = exponent ;
    }

    /**
     *
     * clone method returns a deep copy of another term
     *
     **/
    public Term clone()
    {
        return new Term(this.coefficient, this.exponent) ;
    }

    /**
     * equals method compares the coefficients and exponents of term objects.
     *
     * @param compareTerm   term to be tested for equality of coefficients and exponents.
     * @return boolean  returns a boolean true for same coeff. and exponents, false otherwise.
     *
     **/
    public boolean equals(Object compareTerm)
    {
        if(compareTerm == null || compareTerm.getClass() != this.getClass())
        {
            return false ;
        }

        Term tempTerm = (Term) compareTerm ;

        return ((this.coefficient == tempTerm.getCoefficient()) && (this.exponent == tempTerm.getExponent())) ;

    }

}


