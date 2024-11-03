package Model;

import Exceptions.NegativeValueException;

/**
 * The LengthModel class provides methods to convert lengths between various units.
 * It supports conversions between centimeters (cm), millimeters (mm), meters (m), and kilometers (km).
 * 
 * @author Bartek Bielak
 * @version 1.0
 */
public class LengthModel {

    /**
     * Converts a given length value from one unit to another.
     * 
     * @param value the length value to be converted
     * @param fromUnit the unit of the provided value (e.g., "cm", "mm", "m", "km")
     * @param toUnit the unit to convert the value to (e.g., "cm", "mm", "m", "km")
     * @return the converted length value in the target unit
     * @throws NegativeValueException if the provided value is negative
     */
    public double convert(double value, String fromUnit, String toUnit) throws NegativeValueException {
        if (value < 0) {
            throw new NegativeValueException("Value cannot be negative: " + value);
        }
        double valueInMeters = convertToMeters(value, fromUnit);
        return convertFromMeters(valueInMeters, toUnit);
    }

    /**
     * Converts a length value to meters based on the provided unit.
     * 
     * @param value the length value to convert
     * @param unit the unit of the provided value (e.g., "cm", "mm", "m", "km")
     * @return the length value in meters
     */
    private double convertToMeters(double value, String unit) {
        switch (unit) {
            case "cm":
                return value / 100; // Convert centimeters to meters
            case "mm":
                return value / 1000; // Convert millimeters to meters
            case "km":
                return value * 1000; // Convert kilometers to meters
            case "m":
            default:
                return value; // Return value in meters if unit is already meters
        }
    }

    /**
     * Converts a length value from meters to the specified target unit.
     * 
     * @param value the length value in meters to convert
     * @param unit the target unit to convert to (e.g., "cm", "mm", "m", "km")
     * @return the length value in the target unit
     */
    private double convertFromMeters(double value, String unit) {
        switch (unit) {
            case "cm":
                return value * 100; // Convert meters to centimeters
            case "mm":
                return value * 1000; // Convert meters to millimeters
            case "km":
                return value / 1000; // Convert meters to kilometers
            case "m":
            default:
                return value; // Return value in meters if unit is already meters
        }
    }
}
