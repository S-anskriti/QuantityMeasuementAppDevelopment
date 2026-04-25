import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testEquality_KilogramToKilogram_SameValue() {
        QuantityWeight q1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight q2 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_KilogramToKilogram_DifferentValue() {
        QuantityWeight q1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight q2 = new QuantityWeight(2.0, WeightUnit.KILOGRAM);

        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_KilogramToGram_EquivalentValue() {
        QuantityWeight q1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight q2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_GramToKilogram_EquivalentValue() {
        QuantityWeight q1 = new QuantityWeight(1000.0, WeightUnit.GRAM);
        QuantityWeight q2 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_KilogramToPound_EquivalentValue() {
        QuantityWeight q1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight q2 = new QuantityWeight(2.20462, WeightUnit.POUND);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_PoundToGram_EquivalentValue() {
        QuantityWeight q1 = new QuantityWeight(1.0, WeightUnit.POUND);
        QuantityWeight q2 = new QuantityWeight(453.592, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_WeightVsLength_Incompatible() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityMeasurementApp.QuantityLength length =
                new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(weight.equals(length));
    }

    @Test
    void testEquality_NullComparison() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertFalse(weight.equals(null));
    }

    @Test
    void testEquality_SameReference() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertTrue(weight.equals(weight));
    }

    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityWeight(1.0, null);
        });
    }

    @Test
    void testEquality_ZeroValue() {
        QuantityWeight q1 = new QuantityWeight(0.0, WeightUnit.KILOGRAM);
        QuantityWeight q2 = new QuantityWeight(0.0, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_NegativeWeight() {
        QuantityWeight q1 = new QuantityWeight(-1.0, WeightUnit.KILOGRAM);
        QuantityWeight q2 = new QuantityWeight(-1000.0, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_LargeWeightValue() {
        QuantityWeight q1 = new QuantityWeight(1000000.0, WeightUnit.GRAM);
        QuantityWeight q2 = new QuantityWeight(1000.0, WeightUnit.KILOGRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_SmallWeightValue() {
        QuantityWeight q1 = new QuantityWeight(0.001, WeightUnit.KILOGRAM);
        QuantityWeight q2 = new QuantityWeight(1.0, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testConversion_PoundToKilogram() {
        QuantityWeight result = new QuantityWeight(2.20462, WeightUnit.POUND).convertTo(WeightUnit.KILOGRAM);

        assertTrue(result.equals(new QuantityWeight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testConversion_KilogramToPound() {
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.POUND);

        assertTrue(result.equals(new QuantityWeight(2.20462, WeightUnit.POUND)));
    }

    @Test
    void testConversion_SameUnit() {
        QuantityWeight result = new QuantityWeight(5.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.KILOGRAM);

        assertTrue(result.equals(new QuantityWeight(5.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testConversion_ZeroValue() {
        QuantityWeight result = new QuantityWeight(0.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM);

        assertTrue(result.equals(new QuantityWeight(0.0, WeightUnit.GRAM)));
    }

    @Test
    void testConversion_NegativeValue() {
        QuantityWeight result = new QuantityWeight(-1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM);

        assertTrue(result.equals(new QuantityWeight(-1000.0, WeightUnit.GRAM)));
    }

    @Test
    void testConversion_RoundTrip() {
        QuantityWeight result = new QuantityWeight(1.5, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM)
                .convertTo(WeightUnit.KILOGRAM);

        assertTrue(result.equals(new QuantityWeight(1.5, WeightUnit.KILOGRAM)));
    }

    @Test
    void testAddition_SameUnit_KilogramPlusKilogram() {
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(2.0, WeightUnit.KILOGRAM));

        assertTrue(result.equals(new QuantityWeight(3.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testAddition_CrossUnit_KilogramPlusGram() {
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000.0, WeightUnit.GRAM));

        assertTrue(result.equals(new QuantityWeight(2.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testAddition_CrossUnit_PoundPlusKilogram() {
        QuantityWeight result = new QuantityWeight(2.20462, WeightUnit.POUND)
                .add(new QuantityWeight(1.0, WeightUnit.KILOGRAM));

        assertTrue(result.equals(new QuantityWeight(4.40924, WeightUnit.POUND)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_Gram() {
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);

        assertTrue(result.equals(new QuantityWeight(2000.0, WeightUnit.GRAM)));
    }

    @Test
    void testAddition_Commutativity() {
        QuantityWeight first = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM);

        QuantityWeight second = new QuantityWeight(1000.0, WeightUnit.GRAM)
                .add(new QuantityWeight(1.0, WeightUnit.KILOGRAM), WeightUnit.KILOGRAM);

        assertTrue(first.equals(second));
    }

    @Test
    void testAddition_WithZero() {
        QuantityWeight result = new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(0.0, WeightUnit.GRAM));

        assertTrue(result.equals(new QuantityWeight(5.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testAddition_NegativeValues() {
        QuantityWeight result = new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(-2000.0, WeightUnit.GRAM));

        assertTrue(result.equals(new QuantityWeight(3.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testAddition_LargeValues() {
        QuantityWeight result = new QuantityWeight(1000000.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000000.0, WeightUnit.KILOGRAM));

        assertTrue(result.equals(new QuantityWeight(2000000.0, WeightUnit.KILOGRAM)));
    }
}