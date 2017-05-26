/**
 * Java. TeRoundButtonsCalculator
 *
 * @author Ternyuk Igor
 * @version 1.0 dated May 25, 2017
 */
package teroundbuttonscalculator;

public class Model {

    private double result_dgt;
    private String result_str;

    public Model() {
        result_dgt = 0.f;
        result_str = new String();
    }

    public double getDigitalResult() {
        return result_dgt;
    }

    public String getTextResult() {
        return result_str;
    }

    public void clearResult() {
        result_dgt = 0;
        result_str = "";
    }

    public void calculate(double first, double second, Operation operand) {
        switch(operand){
            case ADDITION :
                result_dgt = first + second;
                result_str = String.valueOf(result_dgt);
                break;
            case SUBTRACTION :
                result_dgt = first - second;
                result_str = String.valueOf(result_dgt);
                break;
            case MULTIPLICATION :
                result_dgt = first * second;
                result_str = String.valueOf(result_dgt);
                break;
            case DIVISION :
                result_dgt = first / second;
                result_str = String.valueOf(result_dgt);
                break;
            case POWER:
                result_dgt = Math.pow(first, second);
                result_str = String.valueOf(result_dgt);
                break;
        }
    }

    public static enum Operation {
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION,
        POWER
    }
}
