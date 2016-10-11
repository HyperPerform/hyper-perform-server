package me.hyperperform.user;

public class MockUsers
{
    static String normalUser = "{\"managerEmail\": \"admin@hyperperform.me\", \"userName\":\"rohan\", \"userSurname\":\"chhipa\", \"userEmail\":\"rohanchhipa@live.com\", \"userPassword\":\"1234\", \"role\":\"Employee\", \"position\":\"SoftwareDeveloper\", \"gitUserName\": \"rohan\"}";

    static String noUsername = "{\"managerEmail\": \"admin@hyperperform.me\", \"userName\":\"\", \"userSurname\":\"chhipa\", \"userEmail\":\"rohanchhipa@live.com\", \"userPassword\":\"1234\", \"role\":\"Employee\", \"position\":\"SoftwareDeveloper\", \"gitUserName\": \"rohan\"}";

    static String noSurname = "{\"managerEmail\": \"admin@hyperperform.me\", \"userName\":\"rohan\", \"userSurname\":\"\", \"userEmail\":\"rohanchhipa@live.com\", \"userPassword\":\"1234\", \"role\":\"Employee\", \"position\":\"SoftwareDeveloper\", \"gitUserName\": \"rohan\"}";

    static String noEmail = "{\"managerEmail\": \"admin@hyperperform.me\", \"userName\":\"rohan\", \"userSurname\":\"chhipa\", \"userEmail\":\"\", \"userPassword\":\"1234\", \"role\":\"Employee\", \"position\":\"SoftwareDeveloper\", \"gitUserName\": \"rohan\"}";

    static String noPassword = "{\"managerEmail\": \"admin@hyperperform.me\", \"userName\":\"rohan\", \"userSurname\":\"chhipa\", \"userEmail\":\"rohanchhipa@live.com\", \"userPassword\":\" \", \"role\":\"Employee\", \"position\":\"SoftwareDeveloper\", \"gitUserName\": \"rohan\"}";

    static String noGitUsername = "{\"managerEmail\": \"admin@hyperperform.me\", \"userName\":\"rohan\", \"userSurname\":\"chhipa\", \"userEmail\":\"rohanchhipa@live.com\", \"userPassword\":\"1234\", \"role\":\"Employee\", \"position\":\"SoftwareDeveloper\", \"gitUserName\": \"\"}";

    static String invalidRole = "{\"managerEmail\": \"admin@hyperperform.me\", \"userName\":\"rohan\", \"userSurname\":\"chhipa\", \"userEmail\":\"rohanchhipa@live.com\", \"userPassword\":\"1234\", \"role\":\"Jester\", \"position\":\"SoftwareDeveloper\", \"gitUserName\": \"rohan\"}";

    static String invalidPosition = "{\"managerEmail\": \"admin@hyperperform.me\", \"userName\":\"rohan\", \"userSurname\":\"chhipa\", \"userEmail\":\"rohanchhipa@live.com\", \"userPassword\":\"1234\", \"role\":\"Employee\", \"position\":\"Outside\", \"gitUserName\": \"rohan\"}";


}
