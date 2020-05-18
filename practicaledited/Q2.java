import java.util.List;
import java.util.ArrayList;

interface TypeCaster <S, T> {
    T cast(S obj);
}

class ToString<S> implements TypeCaster<S, String> {
    public String cast(S obj) {
        return obj.toString();
    }
}

class Round implements TypeCaster<Double, Integer> {
    public Integer cast (Double obj) {
        return (int) Math.round(obj);
    }
}

class ToList<T> implements TypeCaster<T[], List<T>> {
    public List<T> cast(T[] obj) {
        List<T> output= new ArrayList<T>();

        for (T element:obj) {
            output.add(element);
        }

        return output;
    }
}

class ListCaster {
    public static <S, T> List<T> castList (List<S> list, TypeCaster<S, T> typecaster) {
        List<T> output = new ArrayList<T>();

        for (S element:list) {
            output.add(typecaster.cast(element));
        }

        return output;
    }
}
