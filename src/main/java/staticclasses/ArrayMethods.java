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
    public static String[] removeFromArray(String[] array, String item){
        String[] finalArray = new String[0];
        int index= 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)){
                index = i;
            }
        }
        for (int i = 0; i < index; i++) {
            finalArray = addToArray(finalArray,array[i]);
        }
        for (int i = index+1; i < array.length; i++) {
            finalArray = addToArray(finalArray,array[i]);
        }
        return finalArray;
    }

}
