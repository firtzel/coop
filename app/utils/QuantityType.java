package utils;

import java.io.IOError;
import java.io.IOException;

public enum QuantityType {
	WEIGHT("kg"),
	UNITS("units");
	
	private final String quantity;

	private QuantityType(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return quantity;
	}

	public static QuantityType of(String value) {
		for (QuantityType type : QuantityType.values()) {
			if (type.quantity.equals(value)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Could not find type for value " + value);
	}
}
