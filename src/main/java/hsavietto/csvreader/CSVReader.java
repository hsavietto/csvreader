package hsavietto.csvreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

/**
 * Created by Helder Savietto (helder.savietto@gmail.com)
 */
public class CSVReader<T> implements Iterable<T> {

    private class CVSReaderIterator implements Iterator<T> {

        private T nextValue;

        CVSReaderIterator() {
            nextValue = fetchNextValue();
        }

        @Override
        public boolean hasNext() {
            return nextValue != null;
        }

        @Override
        public T next() {
            if (nextValue == null) {
                throw new NoSuchElementException();
            }

            T valueToReturn = nextValue;
            nextValue = fetchNextValue();
            return valueToReturn;
        }

        private T fetchNextValue() {
            String line;

            try {
                line = reader.readLine();
            } catch (IOException e) {
                return null;
            }

            if(line == null) {
                return null;
            }

            List<String> values = Arrays.asList(line.split(separator));
            return lineConverter.convert(new CSVLine(values, lineDefinition));
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    private final BufferedReader reader;
    private final String separator;
    private final ICSVLineConverter<T> lineConverter;
    private final CSVLineDefinition lineDefinition;

    /**
     * @param reader
     * @param separator
     * @param lineConverter
     * @throws IOException
     */
    CSVReader(Reader reader, String separator, ICSVLineConverter<T> lineConverter) throws IOException {
        this.reader = new BufferedReader(reader);
        this.separator = separator;
        this.lineConverter = lineConverter;
        this.lineDefinition = setupHeader(lineConverter.getColumns());
    }

    private CSVLineDefinition setupHeader(List<ICSVColumn> columns) throws IOException {
        String header = reader.readLine();
        String[] names = header.split(separator);
        Map<String, Integer> namesPositions = new HashMap<>();

        for (int p = 0; p < names.length; p++) {
            String name = names[p];

            if (!namesPositions.containsKey(name)) {
                namesPositions.put(name, p);
            }
        }

        Map<String, Integer> positions = new HashMap<>();
        Map<String, ICSVConverter> converters = new HashMap<>();

        for (ICSVColumn column : columns) {
            String name = column.getColumnName();
            Integer position = namesPositions.get(name);

            if (position != null) {
                positions.put(name, position);
                converters.put(name, column.getConverter());
            }
        }

        return new CSVLineDefinition(positions, converters);
    }

    /**
     * @return
     */
    public Iterator<T> iterator() {
        return new CVSReaderIterator();
    }
}
