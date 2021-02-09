package facebook;

public class FacebookUsers
{
    //person’s name, optional image, current status, and a list of friends
    private String ID;
    private String password;
    private String status;
    //private LList<String> friends;


    public FacebookUsers() {
        ID = "";
        password = "";
        status = "inactive";

    } // end default constructor

    public FacebookUsers(String userID, String userPassword, String userStatus)
    {
        ID = userID;
        password = userPassword;
        status = userStatus;

    } // end default constructor

    public void setID(String userID) {
        ID = userID;
    }

    public void setPassword(String userPassword) {
        password = userPassword;
    }

    public void setStatus(String userStatus) {
        status = userStatus;
    }

    public String getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }


    public String toString() {
        String result = "ID = " + this.getID();
        result = result + ",  password = " + this.getPassword();
        result = result + ",  status = " + this.getStatus();

        return result;
    }
}
