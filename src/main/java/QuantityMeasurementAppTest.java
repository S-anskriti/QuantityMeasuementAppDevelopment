import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.000001;

    @Test
    void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12.0, LengthUnit.INCHES.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0, LengthUnit.YARDS.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(1.0 / 30.48, LengthUnit.CENTIMETERS.getConversionFactor(), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_FeetToFeet() {
        assertEquals(5.0, LengthUnit.FEET.convertToBaseUnit(5.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToFeet() {
        assertEquals(2.0, LengthUnit.FEET.convertFromBaseUnit(2.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    void testQuantityLengthRefactored_Equality() {
        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength inches =
                new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches));
    }

    @Test
    void testQuantityLengthRefactored_ConvertTo() {
        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength result = feet.convertTo(LengthUnit.INCHES);

        assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCHES)));
    }

    @Test
    void testQuantityLengthRefactored_Add() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.FEET);

        assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(2.0, LengthUnit.FEET)));
    }

    @Test
    void testQuantityLengthRefactored_AddWithTargetUnit() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS);

        assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(0.6666666667, LengthUnit.YARDS)));
    }

    @Test
    void testQuantityLengthRefactored_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityMeasurementApp.QuantityLength(1.0, null);
        });
    }

    @Test
    void testQuantityLengthRefactored_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityMeasurementApp.QuantityLength(Double.NaN, LengthUnit.FEET);
        });
    }

    @Test
    void testBackwardCompatibility_UC5ConversionTests() {
        assertEquals(12.0, QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES), EPSILON);
        assertEquals(2.0, QuantityMeasurementApp.convert(24.0, LengthUnit.INCHES, LengthUnit.FEET), EPSILON);
        assertEquals(36.0, QuantityMeasurementApp.convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES), EPSILON);
        assertEquals(1.0, QuantityMeasurementApp.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testBackwardCompatibility_UC6AdditionTests() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCHES));

        assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(2.0, LengthUnit.FEET)));
    }

    @Test
    void testBackwardCompatibility_UC7AdditionWithTargetUnitTests() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES);

        assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(24.0, LengthUnit.INCHES)));
    }

    @Test
    void testRoundTripConversion_RefactoredDesign() {
        double original = 10.5;

        double inches = QuantityMeasurementApp.convert(original, LengthUnit.FEET, LengthUnit.INCHES);
        double feet = QuantityMeasurementApp.convert(inches, LengthUnit.INCHES, LengthUnit.FEET);

        assertEquals(original, feet, EPSILON);
    }

    @Test
    void testUnitImmutability() {
        LengthUnit unit = LengthUnit.FEET;
        assertEquals(LengthUnit.FEET, unit);
    }
}