package hsavietto.csvreader;

/**
 * Created by Helder Savietto (helder.savietto@gmail.com)
 */
public interface ICSVColumn {

    /**
     *
     * @return
     */
    String getColumnName();

    /**
     *
     * @return
     */
    ICSVConverter getConverter();

}
