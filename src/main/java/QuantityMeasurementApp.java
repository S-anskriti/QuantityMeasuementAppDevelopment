public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(12.0),
        INCH(1.0),
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
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }

        private double convertToInches() {
            return value * unit.getConversionFactor();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            QuantityLength quantityLength = (QuantityLength) obj;
            return Math.abs(this.convertToInches() - quantityLength.convertToInches()) < 0.0001;
        }
    }

    public static void main(String[] args) {
        QuantityLength yards = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength centimeters = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        QuantityLength inches = new QuantityLength(0.393701, LengthUnit.INCH);

        System.out.println("Yard and Feet Equal: " + yards.equals(feet));
        System.out.println("Centimeter and Inch Equal: " + centimeters.equals(inches));
    }
}