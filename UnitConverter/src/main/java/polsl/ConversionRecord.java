package polsl;

/**
 * The ConversionRecord class represents a record of a unit conversion.
 * It stores the original value, the units involved in the conversion,
 * and the resulting converted value.
 */
public class ConversionRecord {
    
    private final String fromUnit;  
    private final String toUnit; 
    private final double value;    
    private final double result;     

    /**
     * Constructs a ConversionRecord with the specified parameters.
     *
     * @param value the original value to be converted
     * @param fromUnit the unit of the original value
     * @param toUnit the unit of the converted value
     * @param result the result of the conversion
     */
    public ConversionRecord(double value, String fromUnit, String toUnit, double result) {
        this.value = value;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
        this.result = result;
    }

    /**
     * Returns the original value to be converted.
     *
     * @return the original value
     */
    public double getValue() {
        return value;
    }

    /**
     * Returns the unit of the original value.
     *
     * @return the unit of the original value
     */
    public String getFromUnit() {
        return fromUnit;
    }

    /**
     * Returns the unit of the converted value.
     *
     * @return the unit of the converted value
     */
    public String getToUnit() {
        return toUnit;
    }

    /**
     * Returns the result of the conversion.
     *
     * @return the converted value
     */
    public double getResult() {
        return result;
    }
}
