package hsavietto.csvreader;

/**
 * Created by Helder Savietto (helder.savietto@gmail.com)
 */
public interface ICSVConverter<T> {

    /**
     * @param value
     * @return
     */
    T convert(String value);

}
