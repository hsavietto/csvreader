package hsavietto.csvreader;

import java.util.List;

/**
 * Created by Helder Savietto (helder.savietto@gmail.com)
 */
public interface ICSVLineConverter<T> {

    /**
     * @param entry
     * @return
     */
    T convert(CSVLine entry);

    /**
     *
     * @return
     */
    List<ICSVColumn> getColumns();

}
