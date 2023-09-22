

public class Generics {

   public static void main(String[] args) {
       
       Integer intArray[]= new Integer[]{2,4,6,8};
       
       System.out.println("Value "+6+" placed at index "+getPosition(intArray,4,6));
       
       System.out.println("Greater number in (10,5) is "+(greaterThan(10,5)));

   }
  
   
   public static<E> int getPosition(E[] testArray,int length,E element) {
       for(int i=0;i<length;i++) {
           if(testArray[i].equals(element)) {
               return i;
           }
       }
       return -1;
   }
  
   
   public static <E> Object greaterThan(Object a,Object b) {
       return value(a)> value(b) ? a :b;
   }
   
   public static int value(Object val) {
       String temp=String.valueOf(val);
       int result=0;
       for(int i=0;i<temp.length();i++) {
           result+=(int)temp.charAt(i);
       }
       return result;
   }

}

