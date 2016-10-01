package me.hyperperform.user;

public class MockUsers
{
    static String normalUser = "{\"managerEmail\": \"admin@hyperperform.me\", \"userName\":\"rohan\", \"userSurname\":\"chhipa\", \"userEmail\":\"rohanchhipa@live.com\", \"userPassword\":\"1234\", \"role\":\"Employee\", \"position\":\"SoftwareDeveloper\"}";

    static String noUsername = "{\"managerEmail\": \"admin@hyperperform.me\", \"userName\":\"\", \"userSurname\":\"chhipa\", \"userEmail\":\"rohanchhipa@live.com\", \"userPassword\":\"1234\", \"role\":\"Employee\", \"position\":\"SoftwareDeveloper\"}";

    static String noSurname = "{\"managerEmail\": \"admin@hyperperform.me\", \"userName\":\"rohan\", \"userSurname\":\"\", \"userEmail\":\"rohanchhipa@live.com\", \"userPassword\":\"1234\", \"role\":\"Employee\", \"position\":\"SoftwareDeveloper\"}";

    static String noEmail = "{\"managerEmail\": \"admin@hyperperform.me\", \"userName\":\"rohan\", \"userSurname\":\"chhipa\", \"userEmail\":\"\", \"userPassword\":\"1234\", \"role\":\"Employee\", \"position\":\"SoftwareDeveloper\"}";

    static String invalidRole = "{\"managerEmail\": \"admin@hyperperform.me\", \"userName\":\"rohan\", \"userSurname\":\"chhipa\", \"userEmail\":\"rohanchhipa@live.com\", \"userPassword\":\"1234\", \"role\":\"Jester\", \"position\":\"SoftwareDeveloper\"}";

    static String invalidPosition = "{\"managerEmail\": \"admin@hyperperform.me\", \"userName\":\"rohan\", \"userSurname\":\"chhipa\", \"userEmail\":\"rohanchhipa@live.com\", \"userPassword\":\"1234\", \"role\":\"Employee\", \"position\":\"Outside\"}";

}
