import java.util.*;

/**
 * Formula for Java Hashcode is
 * s[0]*31^(n - 1) + s[1]*31^(n - 2) + ... + s[n - 1]
 *
 * Our approach is finding two small UNEQUAL strings whose  hashcode matches . Examples are "FB and "Ea" ; "Aa" and "BB"
 * We then make combinations of them to make bigger strings, since those UNEQUAL strings will have matching hashcodes
 * In this we use "FB and "Ea"
 *
 * In our algo, the pool of strings squares each iteration (i.e in the outermost for loop).The output is the first N
 * elements of this pool (In the question that is 3 but it could be a 100 too).
 *
 * For instance,
 * In iteration 1 , we start with {"FB","Ea"} which has a size of 2
 * In iteration 2 , we will square 2 to make new pool of size 4
 * In iteration 3 , we will square 4 to make new pool of size 16
 * In iteration 4 , we will square 16 to make new pool of size 256
 * In iteration 5 , we will double 256 to make new pool of size 65536
 * In iteration 6 , we will double 65536 to make new pool of size 65536^2
 *
 * These iteration sizes are stored in the idx array. I stopped at 65536^2 because it is a big number but we could have
 * kept going
 */
public class SameHashCode {

    public static void main(String[] args) {
        SameHashCode obj = new SameHashCode();
        obj.printRecords(3);//You can try 100 1000 or even 65537
        }

    /**
     * Signifies Size of atomicStrings in Powers of 2
     */
    public int idx[] = {1,2,4,8,16,32};

    /**
     * Find index of closet Value in idx array
     * @param arg closet Value to element in idx array
     * @return index of closet Value in idx array
     */
    public int findClosestIndex(int arg) {
        int ret = -1;
        for (int i= 0 ; i<idx.length; i++)
        {
            if(idx[i]>arg)
            {
                ret = i;
                break;
            }
        }
        return ret;
    }

    /**
     * Calculates number of iteration of outermost for loop
     * @param numOutputStrings Number of Strings to produce
     * @return num iteraton of outermost for loop
     */
    public int calcLimit (int numOutputStrings)
    {
        int logOutput = (int) ( Math.log(numOutputStrings) / Math.log(2) );
        int limit = findClosestIndex( logOutput );
        return limit;
    }

    /**
     * @param numOutputStrings Number of Strings to produce
     */
    public void printRecords (int numOutputStrings){

        List<String> atomicStrings = new ArrayList<String>(Arrays.asList("FB", "Ea"));
        List<String> tempStorage = new ArrayList<String>();
        //5
        int limit = calcLimit(numOutputStrings);

        loopBreak:
        for (int i = 0; i < limit; i++) { //Outermost for loop
            int numInputStrings = atomicStrings.size();
            tempStorage = new ArrayList<String>(numInputStrings * numInputStrings);
            int count = 0;
            for (String s : atomicStrings) {
                for (String t : atomicStrings) {
                    if (count == numOutputStrings) {
                        break loopBreak;
                    } else {
                        tempStorage.add(s + t);
                        count++;
                    }
                }
            }
            atomicStrings = tempStorage;
        }
        atomicStrings = tempStorage;

        for (String s : atomicStrings)
        {
            System.out.println(s);
        }

        System.out.println("Limit Variable: "+limit +" .Number of Strings printed: " +atomicStrings.size());

    }
}
