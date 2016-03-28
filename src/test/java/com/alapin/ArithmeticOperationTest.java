package com.alapin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Andrey Lapin on 25.03.2016.
 */
@Features("Arithmetic operations")
@Stories("Check operations from file")
@RunWith(Parameterized.class)
public class ArithmeticOperationTest {
    private final String operand1;
    private final String operand2;
    private final String operation;
    private final String result;

    @Parameters(name = "{index}: {0} {2} {1} = {3}")
    public static Collection<Object[]> data() throws IOException {
        List<String> strings = Files.readAllLines(Paths.get("src/test/resources/test.txt"));
        return strings.stream().map(w -> w.split(";")).collect(toList());
    }

    public ArithmeticOperationTest(String operand1, String operand2, String operation, String result) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
        this.result = result;
    }

    @Test
    public void testArithmeticOperation() {
        System.out.println("operand1 = '" + operand1 + "', operand2 = '" + operand2
                + "', operation = '" + operation + "', result = '" + result + "'.");
        Integer operand1Int = getIntValue(operand1);
        Integer operand2Int = getIntValue(operand2);
        checkOperationResult(operand1Int, operand2Int);
    }





    @Step
    private Integer getIntValue(String operation) {
        return Integer.valueOf(operation);
    }

    @Step
    private void checkOperationResult(Integer operand1Int, Integer operand2Int) {
        switch(operation) {
            case "+":
                // sum or subtraction of 2 int may be long.
                Long resultLong = getResultAsLongValue(this.result);
                Long operandSum = getExpectedSum(operand1Int, operand2Int);
                verifyEqualsLong("Sum operation return value not equal to expected.", resultLong, operandSum);
                break;
            case "-" :
                resultLong = getResultAsLongValue(this.result);
                Long operandSub = getExpectedSubst(operand1Int, operand2Int);
                verifyEqualsLong("Substraction operation return value not equal to expected.", resultLong, operandSub);
                break;
            case "*" :
                resultLong = getResultAsLongValue(this.result);
                Long operandMul = getExpectedMultiplt(operand1Int, operand2Int);
                verifyEqualsLong("Multiplication operation return value not equal to expected.", resultLong, operandMul);
                break;
            case "/" :
                Double resultDouble = getResultAsDoubleValue(this.result);
                Double operandDiv = getExpectedDiv(operand1Int, operand2Int);
                verifyDivCorrect(resultDouble, operandDiv);
                break;
            default:
                // invalid operation
                assertTrue("Invalid operation.", false);
                break;
        }
    }

    @Step
    private double getExpectedDiv(double operand1Int, Integer operand2Int) {
        return operand1Int / operand2Int;
    }

    @Step
    private long getExpectedMultiplt(long operand1Int, Integer operand2Int) {
        return operand1Int * operand2Int;
    }

    @Step
    private long getExpectedSubst(long operand1Int, Integer operand2Int) {
        return operand1Int - operand2Int;
    }

    @Step
    private Long getResultAsLongValue(String operation) {
        return Long.valueOf(operation);
    }

    @Step
    private Double getResultAsDoubleValue(String operation) {
        return Double.valueOf(operation);
    }

    @Step
    private Long getExpectedSum(Integer operand1Int, Integer operand2Int) {
        return (long) operand1Int + operand2Int;
    }

    @Step
    private void verifyEqualsLong(String errorMessage, Long expected, Long actual) {
        assertEquals(errorMessage, expected, actual);
    }

    @Step
    private void verifyDivCorrect(Double resultDouble, Double operandDiv) {
        assertTrue("Division operation return value not equal to expected.", Math.abs(operandDiv - resultDouble) < .0001);
    }
}
