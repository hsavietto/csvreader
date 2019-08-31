package hsavietto.csvreader;

import java.util.List;

/**
 * Created by Helder Savietto (helder.savietto@gmail.com)
 */
class CSVLine {

    private final List<String> values;
    private final CSVLineDefinition lineDefinition;

    /**
     * @param values
     */
    public CSVLine(List<String> values, CSVLineDefinition lineDefinition) {
        this.values = values;
        this.lineDefinition = lineDefinition;
    }

    /**
     *
     * @param column
     * @param <T>
     * @return
     */
    public <T> T getValue(ICSVColumn column) {
        int position = lineDefinition.getColumnPosition(column.getColumnName());

        if (position < 0 || position >= this.values.size()) {
            return null;
        }

        ICSVConverter<T> columnConverter = lineDefinition.getColumnConverter(column.getColumnName());
        return columnConverter.convert(values.get(position));
    }

}
