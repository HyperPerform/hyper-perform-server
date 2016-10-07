package me.hyperperform;

import java.security.MessageDigest;
import java.util.Random;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/09/26
 * Feature:
 */
public class Hash
{
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
