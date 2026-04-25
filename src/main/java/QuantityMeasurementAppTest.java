import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    @Test
    void testEquality_SameValue() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(1.0);
        assertTrue(f1.equals(f2));
    }

    @Test
    void testEquality_DifferentValue() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(2.0);
        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEquality_NullComparison() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
        assertFalse(f1.equals(null));
    }

    @Test
    void testEquality_SameReference() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
        assertTrue(f1.equals(f1));
    }

    @Test
    void testEquality_NonNumericInput() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
        String other = "test";
        assertFalse(f1.equals(other));
    }
}