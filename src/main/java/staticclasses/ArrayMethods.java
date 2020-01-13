package staticclasses;

public class ArrayMethods {
    public static String[] addToArray(String[] array, String item){
        String[] arrayHolder = new String[array.length+1];
        for (int i = 0; i <array.length ; i++) {
            arrayHolder[i] = array[i];
        }
        arrayHolder[arrayHolder.length-1] = item;
        return arrayHolder;
    }
}
