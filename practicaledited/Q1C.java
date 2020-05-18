   import java.util.ArrayList;

   public class Letter {
      ArrayList<String> list;
      Letter() {
         list = new ArrayList<String>();
      }
      Letter(ArrayList<String> newlist) {
         list = new ArrayList<String>(newlist);
      }

      ArrayList<String> get() {
         return list;
      }

      Letter add(Letter b) {
         ArrayList<String> temp = new ArrayList<>(list);
         ArrayList<String> temp2 = b.get();
         for (String s:temp2) {
            temp.add(s);
         }
         return new Letter(temp);
      }

      public String toString(){
         String output = "";
         for(String s:list) {
            output = output + s;
         }
         return output;
      }
   }

   public class B extends Letter {
      B() {
         super();
         list.add("B");
      }
   }
   public class C extends Letter {
      C() {
         super();
         list.add("C");
      }
   }
