/**
 * @filename PowerClass.java
 * @author Colin Allen, Keith Feeney, Patrick Lawlor, Fearghal McMorrow, Cedric Vecchionacce
 * @reference http://stackoverflow.com/questions/5891223/recursively-compute-the-value-of-base-to-the-n-power
 * @date 24 March 2017
 */

//created by group
public class PowersClass1 { //@author Colin Allen, Keith Feeney, Patrick Lawlor, Fearghal McMorrow, Cedric Vecchionacce
    private int pow;
    private int base;

    public PowersClass1() { //@author Colin Allen, Keith Feeney, Patrick Lawlor, Fearghal McMorrow, Cedric Vecchionacce
        pow = 2;
        base = 0;
    }

//*************************************


// created entirely by external link
    public int toThePowerOfTwo(int base){ //@referene: http://stackoverflow.com/questions/5891223/recursively-compute-the-value-of-base-to-the-n-power

        if(pow < 0){
            throw new IllegalArgumentException("Illegal Power Argument");
        }

        else if(pow == 0){
            return 1;
        }

        else{
            return base * base;
        }
    }

//*************************************


 //created by enternal link with add code from authors
     public int power(int base, int pow){ //@author Colin Allen, Keith Feeney, Patrick Lawlor, Fearghal McMorrow, Cedric Vecchionacce
                                          //@referene: http://stackoverflow.com/questions/5891223/recursively-compute-the-value-of-base-to-the-n-power

        if(pow < 0){
            throw new IllegalArgumentException("Illegal Power Argument");
        }

        else if(pow == 0){
            return 1;
        }

        else{
            return base * power(base, pow-1);
        }
    }


}
