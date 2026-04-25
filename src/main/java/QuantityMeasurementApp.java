QuantityWeight kilogram = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
QuantityWeight gram = new QuantityWeight(1000.0, WeightUnit.GRAM);

System.out.println(kilogram.equals(gram));
System.out.println(kilogram.convertTo(WeightUnit.GRAM));
System.out.println(kilogram.add(gram));