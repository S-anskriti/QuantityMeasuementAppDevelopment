import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.FEET)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                QuantityMeasurementApp.LengthUnit.INCHES);

        assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(24.0, QuantityMeasurementApp.LengthUnit.INCHES)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                QuantityMeasurementApp.LengthUnit.YARDS);

        assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(0.6666666667, QuantityMeasurementApp.LengthUnit.YARDS)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCHES),
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCHES),
                QuantityMeasurementApp.LengthUnit.CENTIMETERS);

        assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(5.08, QuantityMeasurementApp.LengthUnit.CENTIMETERS)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.YARDS),
                new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.YARDS);

        assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.YARDS)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.YARDS),
                new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(9.0, QuantityMeasurementApp.LengthUnit.FEET)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {
        QuantityMeasurementApp.QuantityLength firstResult = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                QuantityMeasurementApp.LengthUnit.YARDS);

        QuantityMeasurementApp.QuantityLength secondResult = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.YARDS);

        assertTrue(firstResult.equals(secondResult));
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(0.0, QuantityMeasurementApp.LengthUnit.INCHES),
                QuantityMeasurementApp.LengthUnit.YARDS);

        assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(1.6666666667, QuantityMeasurementApp.LengthUnit.YARDS)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(-2.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.INCHES);

        assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(36.0, QuantityMeasurementApp.LengthUnit.INCHES)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        QuantityMeasurementApp.QuantityLength first =
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength second =
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.add(first, second, null);
        });
    }

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1000.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(500.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.INCHES);

        assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(18000.0, QuantityMeasurementApp.LengthUnit.INCHES)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                QuantityMeasurementApp.LengthUnit.YARDS);

        assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(0.6666666667, QuantityMeasurementApp.LengthUnit.YARDS)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_RawValues() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                1.0,
                QuantityMeasurementApp.LengthUnit.FEET,
                12.0,
                QuantityMeasurementApp.LengthUnit.INCHES,
                QuantityMeasurementApp.LengthUnit.INCHES);

        assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(24.0, QuantityMeasurementApp.LengthUnit.INCHES)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_InstanceMethod() {
        QuantityMeasurementApp.QuantityLength first =
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength second =
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength result = first.add(second, QuantityMeasurementApp.LengthUnit.YARDS);

        assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(0.6666666667, QuantityMeasurementApp.LengthUnit.YARDS)));
    }
}