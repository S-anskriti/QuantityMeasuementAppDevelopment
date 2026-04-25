public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            validateValue(value);
            validateUnit(unit);
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {
            double convertedValue = convert(this.value, this.unit, targetUnit);
            return new QuantityLength(convertedValue, targetUnit);
        }

        public QuantityLength add(QuantityLength other) {
            return add(this, other);
        }

        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            return add(this, other, targetUnit);
        }

        private double convertToBaseUnit() {
            return value * unit.getConversionFactor();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            QuantityLength quantityLength = (QuantityLength) obj;
            return Math.abs(this.convertToBaseUnit() - quantityLength.convertToBaseUnit()) < 0.000001;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {
        validateValue(value);
        validateUnit(sourceUnit);
        validateUnit(targetUnit);

        double baseValue = value * sourceUnit.getConversionFactor();
        return baseValue / targetUnit.getConversionFactor();
    }

    public static QuantityLength add(QuantityLength first, QuantityLength second) {
        if (first == null || second == null)
            throw new IllegalArgumentException("Quantity length cannot be null");

        return add(first, second, first.unit);
    }

    public static QuantityLength add(QuantityLength first, QuantityLength second, LengthUnit targetUnit) {
        if (first == null || second == null)
            throw new IllegalArgumentException("Quantity length cannot be null");

        validateUnit(targetUnit);

        double firstValueInTargetUnit = convert(first.value, first.unit, targetUnit);
        double secondValueInTargetUnit = convert(second.value, second.unit, targetUnit);
        double result = firstValueInTargetUnit + secondValueInTargetUnit;

        return new QuantityLength(result, targetUnit);
    }

    public static QuantityLength add(double firstValue, LengthUnit firstUnit, double secondValue, LengthUnit secondUnit) {
        QuantityLength first = new QuantityLength(firstValue, firstUnit);
        QuantityLength second = new QuantityLength(secondValue, secondUnit);
        return add(first, second);
    }

    public static QuantityLength add(double firstValue, LengthUnit firstUnit, double secondValue, LengthUnit secondUnit, LengthUnit targetUnit) {
        QuantityLength first = new QuantityLength(firstValue, firstUnit);
        QuantityLength second = new QuantityLength(secondValue, secondUnit);
        return add(first, second, targetUnit);
    }

    private static void validateValue(double value) {
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");
    }

    private static void validateUnit(LengthUnit unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
    }

    public static void main(String[] args) {
        QuantityLength result1 = add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.FEET);

        QuantityLength result2 = add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS);

        System.out.println(result1);
        System.out.println(result2);
    }
}