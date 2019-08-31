package hsavietto.csvreader;

import java.util.Map;

/**
 * Created by Helder Savietto (helder.savietto@gmail.com)
 */
class CSVLineDefinition {

    private final Map<String, Integer> positions;
    private final Map<String, ICSVConverter> converters;

    public CSVLineDefinition(Map<String, Integer> positions, Map<String, ICSVConverter> converters) {
        this.positions = positions;
        this.converters = converters;
    }

    public Integer getColumnPosition(String columnName) {
        return positions.get(columnName);
    }

    public <T> ICSVConverter<T> getColumnConverter(String columnName) {
        return converters.get(columnName);
    }

}
