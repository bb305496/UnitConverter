package Model;

/**
 * The TemperatureModel class provides methods for converting temperatures
 * between different units: Celsius (C), Fahrenheit (F), and Kelvin (K).
 * @author Bartek Bielak
 * @version 1.0
 */
public class TemperatureModel {

    /**
     * Converts a temperature value from one unit to another.
     *
     * @param value the temperature value to be converted
     * @param fromUnit the unit of the provided temperature value (e.g., "C", "F", "K")
     * @param toUnit the unit to convert the temperature value to (e.g., "C", "F", "K")
     * @return the converted temperature value in the target unit
     */
    public double convert(double value, String fromUnit, String toUnit) {
        double valueInCelsius = convertToCelsius(value, fromUnit);
        return convertFromCelsius(valueInCelsius, toUnit);
    }

    /**
     * Converts a temperature value to Celsius based on the provided unit.
     *
     * @param value the temperature value to convert
     * @param unit the unit of the provided temperature value (e.g., "C", "F", "K")
     * @return the temperature value in Celsius
     */
    private double convertToCelsius(double value, String unit) {
        switch (unit) {
            case "F": // Fahrenheit
                return (value - 32) * 5 / 9; // Convert Fahrenheit to Celsius
            case "K": // Kelvin
                return value - 273.15; // Convert Kelvin to Celsius
            case "C": // Celsius
            default:
                return value; // Return value in Celsius if already in Celsius
        }
    }

    /**
     * Converts a temperature value from Celsius to the specified target unit.
     *
     * @param value the temperature value in Celsius to convert
     * @param unit the target unit to convert to (e.g., "C", "F", "K")
     * @return the temperature value in the target unit
     */
    private double convertFromCelsius(double value, String unit) {
        switch (unit) {
            case "F": // Fahrenheit
                return (value * 9 / 5) + 32; // Convert Celsius to Fahrenheit
            case "K": // Kelvin
                return value + 273.15; // Convert Celsius to Kelvin
            case "C": // Celsius
            default:
                return value; // Return value in Celsius if already in Celsius
        }
    }
}
