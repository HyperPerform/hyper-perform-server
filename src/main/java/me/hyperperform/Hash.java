package me.hyperperform;

import java.security.MessageDigest;
import java.util.Random;

/**
 * This class is a util class providing useful functions to the system
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/09/26
 */
public class Hash
{
    /**
     * Takes a string and generates a SHA-256 hash
     * @param password - the password string to hash
     * @return string - the hashed password
     */
    public static String gethash(String password)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++)
            {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }


            return sb.toString();
        }
        catch (Exception e)
        {

        }
        return "";
    }

    /**
     * Generates random characters of the length provided
     * @param length - the number of random characters to be generated
     * @return string of random characters to the provided length
     */
    public static String randPass(int length)
    {
        String alphabet = new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        int n = alphabet.length();

        String result = new String();
        Random r = new Random();

        for (int i=0; i<length; i++)
            result = result + alphabet.charAt(r.nextInt(n));

        return result;
    }

}
