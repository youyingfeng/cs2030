import java.util.function.Supplier;
import java.util.function.Predicate;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

class IFL<T> {
    Supplier<T> head;
    Supplier<IFL<T>> tail;
    /* FIELDS AND METHODS START: DO NOT REMOVE */

    /* FIELDS AND METHODS END: DO NOT REMOVE */

    IFL(Supplier<T> head, Supplier<IFL<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    static <T> IFL<T> of(List<? extends T> list) {
        /* OF START: DO NOT REMOVE!!! */

return list.size() <= 0 ? null : new IFL<T>(() -> list.get(0), () -> IFL.of(list.subList(1, list.size())));
        /* OF END: DO NOT REMOVE!!! */
    }

    Optional<T> findMatch(Predicate<? super T> predicate) {
        /* FINDMATCH START: DO NOT REMOVE!!! */
IFL<T> current = this;
if (current == null) {
   return Optional.empty();
}
while (predicate.test(current.head.get()) == false) {
   current = current.tail.get();
   if (current == null) { return Optional.empty(); }
}
return Optional.ofNullable(current.head.get());
        /* FINDMATCH END: DO NOT REMOVE!!! */
    }
}

/* ADDITIONAL CODE START: DO NOT REMOVE */

/* ADDITIONAL CODE END: DO NOT REMOVE */
