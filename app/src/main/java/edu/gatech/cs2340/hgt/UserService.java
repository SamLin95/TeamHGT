package edu.gatech.cs2340.hgt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;

/**
 * Created by root on 2/3/15.
 * @author Sizhe Lin
 */

/**
 *
 */
public class UserService {
    //private HashMap<String, String> userdata;
    private UserDB userDB;

    /**
     *
     * @param context
     */
    public UserService(Context context) {
        userDB = new UserDB(context);
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public boolean validate(String username, String password) {
        if (username == null || password == null) {
            return false;
        } else {
            String pw = userDB.getPassword(username);
            System.out.println(pw);
            return password.equals(pw);
        }
    }

    /**
     *
     * @param username
     * @return
     */
    public boolean isUsernameExist(String username) {
        return userDB.getPassword(username) != null;
    }

    /**
     *
     * @param username
     * @return
     */
    public boolean checkUsernameFormat(String username) {
        if (username == null) return false;
        return !username.isEmpty() && checkUsernameLength(username);
    }

    /**
     *
     * @param username
     * @return
     */
    private boolean checkUsernameLength(String username) {
          return username.length() >= 8 && username.length() <= 16;
    }

    /**
     *
     * @param password
     * @return
     */
    public boolean checkPasswordFormat(String password) {
        if (password == null) return false;
        //need to add more function to check illegal characters
        return checkUsernameFormat(password) && checkPassword(password);
    }

    /**
     *
     * @param pw
     * @param pwR
     * @return
     */
    public boolean checkPasswordMatch(String pw, String pwR) {
        if (pw == null || pwR == null) {
            return false;
        }
        return pw.equals(pwR);
    }

    /**
     *
     * @param email
     * @return
     */
    public boolean checkEmailFormat(String email) {
        return true;
    }

    /**
     *
     * @param password
     * @return
     */
    private boolean checkPassword(String password) {
        //need to add more functions
        return !password.contains(" ");
    }

    /**
     *
     * @param password
     * @return
     */
    private boolean checkPasswordLength(String password) {
       return checkUsernameLength(password);
    }

    /**
     *
     * @param name
     * @param username
     * @param pw
     * @param email
     * @return
     */
    public boolean createUser(String name, String username, String pw, String email) {
        return userDB.insertUser(name, username, pw, email);
    }

    /**
     *
     * @param username
     * @return
     */
    public boolean deleteUser(String username) {
        return userDB.deleteUser(username);
    }

}

