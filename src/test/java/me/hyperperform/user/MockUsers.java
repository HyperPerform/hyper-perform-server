package me.hyperperform.user;

/**
 * Created by rohan on 2016/09/21.
 */
public class MockUsers
{
    static String normalUser = "{\"userName\":\"rohan\", \"userSurname\":\"chhipa\", \"userEmail\":\"rohanchhipa@live.com\", \"userPassword\":\"1234\", \"role\":\"Employee\", \"position\":\"SoftwareDeveloper\"}";

    static String noUsername = "{\"userName\":\"\", \"userSurname\":\"chhipa\", \"userEmail\":\"rohanchhipa@live.com\", \"userPassword\":\"1234\", \"role\":\"Employee\", \"position\":\"SoftwareDeveloper\"}";

    static String noSurname = "{\"userName\":\"rohan\", \"userSurname\":\"\", \"userEmail\":\"rohanchhipa@live.com\", \"userPassword\":\"1234\", \"role\":\"Employee\", \"position\":\"SoftwareDeveloper\"}";

    static String noEmail = "{\"userName\":\"rohan\", \"userSurname\":\"chhipa\", \"userEmail\":\"\", \"userPassword\":\"1234\", \"role\":\"Employee\", \"position\":\"SoftwareDeveloper\"}";

    static String invalidRole = "{\"userName\":\"rohan\", \"userSurname\":\"chhipa\", \"userEmail\":\"rohanchhipa@live.com\", \"userPassword\":\"1234\", \"role\":\"Jester\", \"position\":\"SoftwareDeveloper\"}";

    static String invalidPosition = "{\"userName\":\"rohan\", \"userSurname\":\"chhipa\", \"userEmail\":\"rohanchhipa@live.com\", \"userPassword\":\"1234\", \"role\":\"Employee\", \"position\":\"Outside\"}";

}
