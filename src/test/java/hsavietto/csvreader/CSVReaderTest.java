package hsavietto.csvreader;

import hsavietto.csvreader.util.Converters;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Helder Savietto (helder.savietto@gmail.com)
 */
public class CSVReaderTest {

    private static class TestObject {
        private Integer integerField;
        private String stringField;

        public TestObject(Integer intValue, String strValue) {
            this.integerField = intValue;
            this.stringField = strValue;
        }

        public Integer getIntegerField() {
            return integerField;
        }

        public void setIntegerField(Integer integerField) {
            this.integerField = integerField;
        }

        public String getStringField() {
            return stringField;
        }

        public void setStringField(String stringField) {
            this.stringField = stringField;
        }
    }

    private enum TestColumns implements ICSVColumn {

        INT_VALUE("INT_COL", Converters.INTEGER_CONVERTER),
        STR_VALUE("STR_COL", Converters.STRING_CONVERTER);

        private final String columnName;
        private final ICSVConverter converter;

        TestColumns(String columnName, ICSVConverter converter) {
            this.columnName = columnName;
            this.converter = converter;
        }

        @Override
        public String getColumnName() {
            return columnName;
        }

        @Override
        public ICSVConverter getConverter() {
            return converter;
        }
    }

    private static class TestObjectConverter implements ICSVLineConverter<TestObject> {

        private static final List<ICSVColumn> columns;

        static {
            columns = new ArrayList<>();

            for (ICSVColumn column : TestColumns.values()) {
                columns.add(column);
            }
        }

        @Override
        public TestObject convert(CSVLine entry) {
            Integer intValue = entry.getValue(TestColumns.INT_VALUE);
            String strValue = entry.getValue(TestColumns.STR_VALUE);
            return new TestObject(intValue, strValue);
        }

        @Override
        public List<ICSVColumn> getColumns() {
            return columns;
        }
    }

    @Test
    public void testCSVReader() throws IOException {
        String csvData = "STR_COL,INT_COL,FLOAT_COL,LONG_COL,DOUBLE_COL,BIGINT_COL,BIGDECIMAL_COL,EXTRA_COL\n" +
                "str1,1,1.2,12345,3.14159,54321,1234.67890\n";
        CSVReaderBuilder<TestObject> builder = new CSVReaderBuilder<>();
        CSVReader<TestObject> csvReader =
                builder.withConverter(new TestObjectConverter()).withReader(new StringReader(csvData)).build();
        Iterator<TestObject> iterator = csvReader.iterator();
        Assert.assertTrue(iterator.hasNext());
        TestObject testObject = iterator.next();
        Assert.assertEquals(1, testObject.getIntegerField().intValue());
        Assert.assertEquals("str1", testObject.getStringField());
        Assert.assertFalse(iterator.hasNext());

        try {
            iterator.next();
            Assert.fail();
        } catch(NoSuchElementException ex) {
            // expected exception
        }
    }
}
