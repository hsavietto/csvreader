package hsavietto.csvreader;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Helder Savietto (helder.savietto@gmail.com)
 */
public class CSVReaderBuilder<T> {

    private Reader reader = null;
    private String separator = ",";
    private ICSVLineConverter<T> lineConverter = null;

    /**
     * @return
     */
    public CSVReader<T> build() throws IOException {
        if (reader == null) {
            throw new IllegalStateException("no reader defined");
        }

        if (separator == null) {
            throw new IllegalStateException("no separator defined");
        }

        if (lineConverter == null) {
            throw new IllegalStateException("no line converter defined");
        }

        return new CSVReader<>(reader, separator, lineConverter);
    }

    /**
     * @param reader
     * @return
     */
    public CSVReaderBuilder<T> withReader(Reader reader) {
        this.reader = reader;
        return this;
    }

    /**
     * @param lineConverter
     * @return
     */
    public CSVReaderBuilder<T> withConverter(ICSVLineConverter<T> lineConverter) {
        this.lineConverter = lineConverter;
        return this;
    }

    /**
     * @param separator
     * @return
     */
    public CSVReaderBuilder<T> withSeparator(String separator) {
        this.separator = separator;
        return this;
    }

}
