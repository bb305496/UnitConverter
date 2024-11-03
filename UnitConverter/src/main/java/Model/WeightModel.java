package Model;

import Exceptions.NegativeValueException;

/**
 * The WeightModel class provides methods to convert weights between various units.
 * It supports conversions between grams (g), milligrams (mg), decigrams (dg), and kilograms (kg).
 * 
 * 
 * @author Bartek Bielak
 * @version 1.0
 */
public class WeightModel {
    
    /**
     * Converts a given weight value from one unit to another.
     * 
     * @param value the weight value to be converted
     * @param fromUnit the unit of the provided weight value (e.g., "g", "mg", "dg", "kg")
     * @param toUnit the unit to convert the weight value to (e.g., "g", "mg", "dg", "kg")
     * @return the converted weight value in the target unit
     * @throws NegativeValueException if the provided weight value is negative
     */
    public double convert(double value, String fromUnit, String toUnit) throws NegativeValueException {
        if (value < 0) {
            throw new NegativeValueException("Value cannot be negative: " + value);
        }
        
        double valueInGrams = convertToGrams(value, fromUnit);
        return convertFromGrams(valueInGrams, toUnit);
    }

    /**
     * Converts a weight value to grams based on the provided unit.
     * 
     * @param value the weight value to convert
     * @param unit the unit of the provided weight value (e.g., "g", "mg", "dg", "kg")
     * @return the weight value in grams
     */
    private double convertToGrams(double value, String unit) {
        switch (unit) {
            case "dg": // decigrams
                return value / 10; // Convert decigrams to grams
            case "mg": // milligrams
                return value / 1000; // Convert milligrams to grams
            case "kg": // kilograms
                return value * 1000; // Convert kilograms to grams
            case "g": // grams
            default:
                return value; // Return value in grams if already in grams
        }
    }

    /**
     * Converts a weight value from grams to the specified target unit.
     * 
     * @param value the weight value in grams to convert
     * @param unit the target unit to convert to (e.g., "g", "mg", "dg", "kg")
     * @return the weight value in the target unit
     */
    private double convertFromGrams(double value, String unit) {
        switch (unit) {
            case "dg": // decigrams
                return value * 10; // Convert grams to decigrams
            case "mg": // milligrams
                return value * 1000; // Convert grams to milligrams
            case "kg": // kilograms
                return value / 1000; // Convert grams to kilograms
            case "g": // grams
            default:
                return value; // Return value in grams if already in grams
        }
    }
}
