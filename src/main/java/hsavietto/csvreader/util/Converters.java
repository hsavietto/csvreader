package hsavietto.csvreader.util;

import hsavietto.csvreader.ICSVConverter;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Helder Savietto (helder.savietto@gmail.com)
 */
public class Converters {

    public static final ICSVConverter<String> STRING_CONVERTER;
    public static final ICSVConverter<Integer> INTEGER_CONVERTER;
    public static final ICSVConverter<Float> FLOAT_CONVERTER;
    public static final ICSVConverter<Long> LONG_CONVERTER;
    public static final ICSVConverter<Double> DOUBLE_CONVERTER;
    public static final ICSVConverter<Boolean> BOOLEAN_CONVERTER;
    public static final ICSVConverter<BigInteger> BIGINTEGER_CONVERTER;
    public static final ICSVConverter<BigDecimal> BIGDECIMAL_CONVERTER;

    static {
        STRING_CONVERTER = new ICSVConverter<String>() {
            @Override
            public String convert(String value) {
                return value;
            }
        };

        INTEGER_CONVERTER = new ICSVConverter<Integer>() {
            @Override
            public Integer convert(String value) {
                try {
                    return Integer.parseInt(value);
                } catch (Exception e) {
                    return null;
                }
            }
        };

        FLOAT_CONVERTER = new ICSVConverter<Float>() {
            @Override
            public Float convert(String value) {
                try {
                    return Float.parseFloat(value);
                } catch (Exception e) {
                    return null;
                }
            }
        };

        LONG_CONVERTER = new ICSVConverter<Long>() {
            @Override
            public Long convert(String value) {
                try {
                    return Long.parseLong(value);
                } catch (Exception e) {
                    return null;
                }
            }
        };

        DOUBLE_CONVERTER = new ICSVConverter<Double>() {
            @Override
            public Double convert(String value) {
                try {
                    return Double.parseDouble(value);
                } catch (Exception e) {
                    return null;
                }
            }
        };

        BOOLEAN_CONVERTER = new ICSVConverter<Boolean>() {
            @Override
            public Boolean convert(String value) {
                try {
                    return Boolean.parseBoolean(value);
                } catch (Exception e) {
                    return false;
                }
            }
        };

        BIGINTEGER_CONVERTER = new ICSVConverter<BigInteger>() {
            @Override
            public BigInteger convert(String value) {
                try {
                    return new BigInteger(value);
                } catch (Exception e) {
                    return null;
                }
            }
        };

        BIGDECIMAL_CONVERTER = new ICSVConverter<BigDecimal>() {
            @Override
            public BigDecimal convert(String value) {
                try {
                    return new BigDecimal(value);
                } catch (Exception e) {
                    return null;
                }
            }
        };


    }

    private Converters() {
        // empty private constructor
    }

}
