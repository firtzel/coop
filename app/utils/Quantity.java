package utils;

public class Quantity {
	private final QuantityType type;
	private final float ammount;

	public static enum Type {
		WEIGHT, UNITS
	}

	public Quantity(QuantityType type, float ammount) {
		this.type = type;
		this.ammount = ammount;
		
	}

	public QuantityType getType() {
		return type;
	}

	public float getAmmount() {
		return ammount;
	}

	@Override
	public String toString() {
		return ammount + " " + type;
	}

	public static Quantity fromString(String value) {
		String[] splitValues = value.split(" ");
		assert splitValues.length == 2;
		return new Quantity(QuantityType.of(splitValues[1]), Float.valueOf(splitValues[0]));
	}
}
