package accounts;


import dbService.DBException;
import dbService.DBService;
import dbService.DBService;
import dbService.DBServiceInterface;
import dbService.dataSets.UsersDataSet;

import java.util.HashMap;
import java.util.Map;


public class AccountService {
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;
    private DBServiceInterface dbService;

    public AccountService(DBServiceInterface dbService) {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
        this.dbService = dbService;
    }

    public void addNewUser(UserProfile userProfile) throws DBException {
        loginToProfile.put(userProfile.getLogin(), userProfile);
        dbService.addUser(userProfile.getLogin(), userProfile.getPass());
    }

    public UserProfile getUserByLogin(String login) throws DBException {
        UsersDataSet dataSet = dbService.getUser(login);
        if (dataSet != null) {
            return new UserProfile(dataSet.getLogin(), dataSet.getPassword());
        } else {
            return null;
        }
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
