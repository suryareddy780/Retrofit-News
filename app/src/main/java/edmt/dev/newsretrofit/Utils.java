package edmt.dev.newsretrofit;

import java.util.Locale;

public class Utils {
    public static String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }


}
