import java.util.function.Function;
 
class Voem<T> {
   T v;
   boolean success;
   String msg;
   
   Voem(T v, boolean success, String msg) {
      this.v = v;
      this.success = success;
      this.msg = msg;
   }
   
   static <T> Voem<T> ok(T v) {
      return new Voem<T>(v, true, "");
   }
 
   static <T> Voem<T> fail(String msg) {
      return new Voem<T>(null, false, msg);
   }
 
   <U> Voem<U> map(Function<? super T, ? extends U> mapper) {
      try {
         if (!success) { return new Voem<U>(null, false, msg); }
         U newValue = mapper.apply(v);
         return Voem.ok(newValue);
      } catch (Exception e){
         return Voem.fail(e.getMessage());
      }
   }
 
   <U> Voem<U> flatMap(Function<? super T, Voem<U>> flatmapper) {
      try {
         if (!success) { return new Voem<U>(null, false, msg); }
         return flatmapper.apply(v);
      } catch (Exception e) {
         return Voem.fail(e.getMessage());
      }
   }
 
   T orElse(T d) {
      try {
         if (success) {
            return v;
         } else {
            throw new Exception(msg);
         }
      } catch (Exception e) {
         return d;
      }
   }
 
   @Override
   public String toString() {
      if (success) {
         return "Done: " + v;
      } else {
         return "Oops: " + msg;
      }
   }
}
