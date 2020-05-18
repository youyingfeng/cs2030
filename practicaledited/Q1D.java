import java.util.List;
import java.util.ArrayList;

public class D {
    public static <S, T extends S> List<S> add(List<S> list, T obj) {
        List<S> newList = new ArrayList<S>(list);
        newList.add(obj);
        return newList;
    }

    public static <T> List<T> join(List<T> list1, List<? extends T> list2) {
        List<T> newList = new ArrayList<T>(list1);
        
        if (list1 == list2) {
            return newList;
        } else {
            newList.addAll(list2);
        }

        return newList;
    }

}
