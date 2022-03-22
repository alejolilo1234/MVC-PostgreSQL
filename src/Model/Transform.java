package Model;

import java.util.Arrays;
import java.util.List;

public class Transform {
    public static List<String> stringToList(String _string) {
        String string = _string;
        List<String> List = Arrays.asList(string.split(","));
        return List;
    }
}
