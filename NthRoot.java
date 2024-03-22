public class NthRoot {

    /**
     * Return num ^ exp. Similar to Math.pow function
     * @param num number
     * @param exp exponent
     * @return num ^ exp
     */
    public long powerFunc(long num, long exp) {
        long result = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = result * num;
                exp = exp - 1;
            } else {
                //We reduce exponents by half since we can square num
                num = num * num;
                exp = exp / 2;
            }
        }
        return result;
    }

    /**
     * Lets use example 2nd root of 100.
     * We find NaturalNumber ^ rootNum iteratively starting with 1. Return NaturalNumber if targetNum is found otherwise -1
     * @param rootNum which root to find. example 2nd root
     * @param targetNum whose root to find. In this example 100
     * @return root or -1. Example root is 10
     */
    public long findRoot(long rootNum, long targetNum) {
        long ret = -1;
        for (long i = 1; i <= targetNum; i++) {
            long val = powerFunc(i, rootNum);
            if (val == targetNum) {
                ret = i;
                break;
            } else if (val > targetNum) {
                break;
            }
        }
        return ret;
    }
    public  static  void main (String [] args)
    {
        //Question implies Natural numbers so using long everywhere.
        NthRoot obj = new NthRoot();
        long rootNum = 5, targetNum = 59049;
        long answer = obj.findRoot(rootNum, targetNum);
        System.out.println(rootNum+" root of "+targetNum+" is "+ answer);
    }
}
