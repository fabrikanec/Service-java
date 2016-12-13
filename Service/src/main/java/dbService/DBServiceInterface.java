package dbService;

import dbService.dataSets.UsersDataSet;

/**
 * Created by nikitaborodulin on 23/01/16.
 */
public interface DBServiceInterface {
    public long addUser(String login, String password) throws DBException;
    public UsersDataSet getUser(String login) throws DBException;
}