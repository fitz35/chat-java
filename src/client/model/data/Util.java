package client.model.data;

/**
 * add utilities function
 */
public class Util {
    /**
     * return if at least one string in toSeek is in s
     * @param s the string to seek in
     * @param toSeek the string to seek
     * @return if at least one string in toSeek is in s
     */
    public static boolean isInString(String s, String[] toSeek){
        boolean result = false;
        for(int i = 0; i < toSeek.length && !result; i++){
            result = s.contains(toSeek[i]);
        }
        return result;
    }
}
