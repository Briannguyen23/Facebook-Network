
package facebook;
import java.io.*;
import java.util.*;


public class Facebook
{
    //private static LList<FacebookUsers> users = new LList<FacebookUsers>();
    private static LinkedDictionary<String, FacebookUsers> users = new LinkedDictionary();

    private static GraphInterface<String> usersGraph = new UndirectedGraph();

    public static void main(String args[])
    {

        int inData=1;
        Scanner input;
        input = new Scanner(System.in);

        while(inData != 8){
            System.out.println("\n\n\nWelcome to Facebook !\n" +
                    "Please enter choice :\n" +
                    "   1. Join the Facebook network\n" +
                    "   2. Leave the Facebook network (remove user and profile)\n" +
                    "   3. Existing user, want to create/modify profile\n" +
                    "   4. Search for friend in Facebook network\n" +
                    "   5. Add/Remove friend to the profile\n" +
                    "   6. Enter status\n" +
                    "   7. Print profile\n" +
                    "   8. Exit"
            );
            inData = getInt("Choice :");
            //System.out.println("Constructing list of players");

            if(inData == 1) joinFacebook();
            else if(inData == 2) leaveFacebook();
            else if(inData == 3) modifyProfile();
            else if(inData == 4) searchFacebook();
            else if(inData == 5) addFriendList();
            else if(inData == 6) enterStatus();
            else if(inData == 7) printProfile();
            else if(inData == 8) {
                System.out.println("Bye!! Come again.");
                //break;
            }

        }

    }


    private static int getInt(String rangePrompt)
    {
        Scanner input;
        int result = 10;        //Default value is 10
        try
        {
            input = new Scanner(System.in);
            System.out.println(rangePrompt);
            result = input.nextInt();

        }
        catch(NumberFormatException e)
        {
            System.out.println("Could not convert input to an integer");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }
        catch(Exception e)
        {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }
        return result;

    }


    public static void joinFacebook()
    {
        Scanner input;
        input = new Scanner(System.in);
        boolean existID=true;
        String ID="";
        String password="";


        //Enter ID name, also cheking if ID is already existed in the network
        while(existID){
            System.out.println("Please enter your ID name");
            ID = input.nextLine();
            System.out.println("Your ID : " + ID);

            //Search for duplicate/existing ID : true/false
            //existID=usersContainsID(ID);
            existID = users.contains(ID);
            if(existID)
                System.out.println("ID already exists in the network = " + existID);
        } // while

        System.out.println("Please enter your password");
        password = input.nextLine();
        System.out.println("Your password : " + password);

        //Save new user to users list
        //need to change to add Vertex
        FacebookUsers addNewUser = new FacebookUsers(ID, password,"Active");
        //users.add(addNewUser);
        users.add(ID, addNewUser);
        // new graph vertex
        boolean addVertexOK = usersGraph.addVertex(ID);
        if(!addVertexOK)System.out.println("Cannot add to usersGraph, ID vertex is already exists");
    }

    public static void leaveFacebook()
    {
        Scanner input;
        input = new Scanner(System.in);
        boolean existID=false;
        FacebookUsers deleteID=null;
        String ID="";
        String password="";



        System.out.println("Please enter your deleting ID name");
        ID = input.nextLine();
        //Enter ID name, also cheking if ID is already existed in the network
        existID = users.contains(ID);
        if(existID) {
            System.out.println("Your deleting ID : " + ID);
            deleteID = users.remove(ID);
        }
    }

    public static void modifyProfile()
    {
        Scanner input;
        input = new Scanner(System.in);
        String ID="";
        boolean existID=false;

        //System.out.println("in modifyProfile");
        System.out.println("Please enter your ID name to modify the profile :");
        ID = input.nextLine();
        //Enter ID name, also cheking if ID is already existed in the network
        existID = users.contains(ID);
        if(existID) {
            System.out.println("Your ID : " + ID);

            //Modify the user profile
            usersProfileModify(ID);
        }

    }





    public static void searchFacebook()
    {
        Scanner input;
        input = new Scanner(System.in);
        boolean existID=false;


        System.out.println("Please enter your searching ID name");
        String ID = input.nextLine();
        System.out.println("Your searching ID : " + ID);

        //Search for duplicate/existing ID : true/false
        existID = users.contains(ID);
        if(existID)
            System.out.println("Your searching ID  exists in the network.");
        else
            System.out.println("Your searching ID  is not found.");

    }

    public static void addFriendList()
    {
        Scanner input;
        input = new Scanner(System.in);
        String ID="";

        System.out.println("Please enter your ID name to modify the friend list :");
        ID = input.nextLine();
        System.out.println("Your ID : " + ID);

        //add friend to friend list
        usersFriendModify(ID);
    }


    public static void enterStatus()
    {
        Scanner input;
        input = new Scanner(System.in);
        String ID="";

        System.out.println("Please enter your ID name to enter status :");
        ID = input.nextLine();
        System.out.println("Your ID : " + ID);

        //Enter status
        usersEnterStatus(ID);

    }




    public static void printProfile()
    {
        Scanner input;
        input = new Scanner(System.in);
        String ID="";

        System.out.println("Please enter your ID name to enter status :");
        ID = input.nextLine();
        System.out.println("Your ID : " + ID);

        //Print profile
        usersPrintProfile(ID);

    }


    public static void usersProfileModify(String anEntry) {
        Scanner input;
        input = new Scanner(System.in);
        boolean found = false;
        //int noOfEntries = users.getLength();
        int noOfEntries = users.getSize();
        int count = 1;
        FacebookUsers currentNode=null;

        found = users.contains(anEntry);

        if(!found){
            System.out.println("Your ID : " + anEntry + " is not found in the network ????");
        }
        else{ // found the user, begin to modify the profile
            currentNode = users.getValue(anEntry);
            //Password
            System.out.println("Do you want to change password ?(y/n) :");
            String ans = input.nextLine();
            if(ans.equals("y")){
                System.out.println("Please enter your new password");
                String password = input.nextLine();
                System.out.println("Your new password : " + password);
                currentNode.setPassword(password);
            }

            //Status
            System.out.println("Do you want to change status ?(y/n) :");
            ans = input.nextLine();
            if(ans.equals("y")){
                System.out.println("Please enter your new status");
                String status = input.nextLine();
                System.out.println("Your new status : " + status);
                currentNode.setStatus(status);
            }

            usersFriendModify(anEntry);
            System.out.println("Your new profile : " + currentNode );
        }


        ;
    } // end usersProfileModify




    public static void usersFriendModify(String anEntry) {
        Scanner input;
        input = new Scanner(System.in);
        boolean found = false;
        //int noOfEntries = users.getLength();
        int noOfEntries = users.getSize();
        int count = 1;
        FacebookUsers currentNode=null;


        found = users.contains(anEntry);
        if(!found){
            System.out.println("Your ID : " + anEntry + " is not found in the network ????");
        }
        else{ // found the user, begin to modify the profile

            // use usersGraph to find the friend list
            //currentNode = users.getValue(anEntry);
            QueueInterface<String>  friendList;
            friendList = new LinkedQueue<String>();

            friendList= usersGraph.getBreadthFirstTraversal(anEntry);
            System.out.println("Your current friend list : " + friendList.toString() );

            // loop to add friends
            String ans = "y";
            boolean friendMatch = false;
            boolean edgeOK = false;

            while(ans.equals("y")){
                System.out.println("Do you want to add friend ?(y/n) :");
                ans = input.nextLine();
                if(ans.equals("y")){
                    System.out.println("Please enter your new friend to add");
                    String friend = input.nextLine();

                    // Check for user exists
                    friendMatch = users.contains(friend);
                    if(!friendMatch){
                        System.out.println("Your new friend ID : " + friend + " is not found in the network !!");
                    }
                    else { //user exists, adding to the friend list begins

                        edgeOK = usersGraph.addEdge(anEntry, friend);
                        if(!edgeOK)
                            System.out.println("Your new friend ID : " + friend + " already in the friend list.");
                        else
                            System.out.println("Your new friend ID : " + friend + " is added.");

                    }

                    //QueueInterface<String>  friendList;
                    friendList= usersGraph.getBreadthFirstTraversal(anEntry);
                    if(friendMatch)
                        System.out.println("Your current friend list : " + friendList.toString() );
                } // end if
            } // end while

// Remove due to this feature is not supported in the DirectedGraph
            // loop to remove friends
            ans = "y";
            while(ans.equals("y")){
                System.out.println("Do you want to remove friend ?(y/n) :");
                ans = input.nextLine();
                if(ans.equals("y")){
                    System.out.println("Please enter your new friend to remove");
                    String friend = input.nextLine();
                    System.out.println("Removing friend : " + friend);

                    // Check for user exists
                    friendMatch = users.contains(friend);
                    if(!friendMatch){
                        System.out.println("Your new friend ID : " + friend + " is not found in the network !!");
                    }
                    else { //user exists, removing friend from the list begins
                        edgeOK = usersGraph.removeEdge(anEntry, friend, 0);
                        if(!edgeOK)
                            System.out.println("Your new friend ID : " + friend + " can not be removed from the friend list. This option is not in the current program feature. !!");
                        else
                            System.out.println("Your new friend ID : " + friend + " is removed.");
                    }
                } // end if
            } // end while


            friendList= usersGraph.getBreadthFirstTraversal(anEntry);
            System.out.println("Your current friend list : " + friendList.toString() );




        }// end of if founding the user
    } // end usersFriendModify



    public static void usersEnterStatus(String anEntry) {
        Scanner input;
        input = new Scanner(System.in);
        boolean found = false;
        //int noOfEntries = users.getLength();
        int noOfEntries = users.getSize();
        int count = 1;
        FacebookUsers currentNode=null;

        found = users.contains(anEntry);
        if(!found){
            System.out.println("Your ID : " + anEntry + " is not found in the network ????");
        }
        else{ // found the user, begin to modify the profile
            currentNode = users.getValue(anEntry);
            //Your current status
            System.out.println("Your current status : " + currentNode.getStatus() );
            System.out.println("Please enter your new status");
            String status = input.nextLine();
            //System.out.println("Your new status : " + status);
            currentNode.setStatus(status);
            System.out.println("Your new status : " + currentNode.getStatus() );


        }
    } // end usersEnterStatus





    public static void usersPrintProfile(String anEntry) {
        Scanner input;
        input = new Scanner(System.in);
        boolean found = false;
        //int noOfEntries = users.getLength();
        int noOfEntries = users.getSize();
        int count = 1;
        FacebookUsers currentNode=null;

        found = users.contains(anEntry);
        if(!found){
            System.out.println("Your ID : " + anEntry + " is not found in the network ????");
        }
        else{ // found the user, print the profile
            currentNode = users.getValue(anEntry);
            //Your profile
            System.out.println("Your new profile : " + currentNode );

        }
    } // end usersPrintProfile
}

/**
 Welcome to Facebook !
 Please enter choice :
 1. Join the Facebook network
 2. Leave the Facebook network (remove user and profile)
 3. Existing user, want to create/modify profile
 4. Search for friend in Facebook network
 5. Add/Remove friend to the profile
 6. Enter status
 7. Print profile
 8. Exit
 Choice :
 1
 Please enter your ID name
 Brian
 Your ID : Brian
 Please enter your password
 Nguyen
 Your password : Nguyen



 Welcome to Facebook !
 Please enter choice :
 1. Join the Facebook network
 2. Leave the Facebook network (remove user and profile)
 3. Existing user, want to create/modify profile
 4. Search for friend in Facebook network
 5. Add/Remove friend to the profile
 6. Enter status
 7. Print profile
 8. Exit
 Choice :
 1
 Please enter your ID name
 Peter
 Your ID : Peter
 Please enter your password
 Vu
 Your password : Vu



 Welcome to Facebook !
 Please enter choice :
 1. Join the Facebook network
 2. Leave the Facebook network (remove user and profile)
 3. Existing user, want to create/modify profile
 4. Search for friend in Facebook network
 5. Add/Remove friend to the profile
 6. Enter status
 7. Print profile
 8. Exit
 Choice :
 1
 Please enter your ID name
 Koby
 Your ID : Koby
 Please enter your password
 Gonzales
 Your password : Gonzales



 Welcome to Facebook !
 Please enter choice :
 1. Join the Facebook network
 2. Leave the Facebook network (remove user and profile)
 3. Existing user, want to create/modify profile
 4. Search for friend in Facebook network
 5. Add/Remove friend to the profile
 6. Enter status
 7. Print profile
 8. Exit
 Choice :
 1
 Please enter your ID name
 Jennifer
 Your ID : Jennifer
 Please enter your password
 Quach
 Your password : Quach



 Welcome to Facebook !
 Please enter choice :
 1. Join the Facebook network
 2. Leave the Facebook network (remove user and profile)
 3. Existing user, want to create/modify profile
 4. Search for friend in Facebook network
 5. Add/Remove friend to the profile
 6. Enter status
 7. Print profile
 8. Exit
 Choice :
 1
 Please enter your ID name
 Mirsaeid
 Your ID : Mirsaeid
 Please enter your password
 Abolghasemi
 Your password : Abolghasemi



 Welcome to Facebook !
 Please enter choice :
 1. Join the Facebook network
 2. Leave the Facebook network (remove user and profile)
 3. Existing user, want to create/modify profile
 4. Search for friend in Facebook network
 5. Add/Remove friend to the profile
 6. Enter status
 7. Print profile
 8. Exit
 Choice :
 3
 Please enter your ID name to modify the profile :
 Brian
 Your ID : Brian
 Do you want to change password ?(y/n) :
 y
 Please enter your new password
 Huh
 Your new password : Huh
 Do you want to change status ?(y/n) :
 n
 Your current friend list : { <Brian> }
 Do you want to add friend ?(y/n) :
 y
 Please enter your new friend to add
 Peter
 Your new friend ID : Peter is added.
 Your current friend list : { <Brian> <Peter> }
 Do you want to add friend ?(y/n) :
 y
 Please enter your new friend to add
 Jennifer
 Your new friend ID : Jennifer is added.
 Your current friend list : { <Brian> <Peter> <Jennifer> }
 Do you want to add friend ?(y/n) :
 n
 Do you want to remove friend ?(y/n) :
 y
 Please enter your new friend to remove
 Jennifer
 Removing friend : Jennifer
 Your new friend ID : Jennifer is removed.
 Do you want to remove friend ?(y/n) :
 n
 Your current friend list : { <Brian> <Peter> }
 Your new profile : ID = Brian,  password = Huh,  status = Active



 Welcome to Facebook !
 Please enter choice :
 1. Join the Facebook network
 2. Leave the Facebook network (remove user and profile)
 3. Existing user, want to create/modify profile
 4. Search for friend in Facebook network
 5. Add/Remove friend to the profile
 6. Enter status
 7. Print profile
 8. Exit
 Choice :
 4
 Please enter your searching ID name
 Mirsaeid
 Your searching ID : Mirsaeid
 Your searching ID  exists in the network.



 Welcome to Facebook !
 Please enter choice :
 1. Join the Facebook network
 2. Leave the Facebook network (remove user and profile)
 3. Existing user, want to create/modify profile
 4. Search for friend in Facebook network
 5. Add/Remove friend to the profile
 6. Enter status
 7. Print profile
 8. Exit
 Choice :
 5
 Please enter your ID name to modify the friend list :
 Koby
 Your ID : Koby
 Your current friend list : { <Koby> }
 Do you want to add friend ?(y/n) :
 y
 Please enter your new friend to add
 Mirsaeid
 Your new friend ID : Mirsaeid is added.
 Your current friend list : { <Koby> <Mirsaeid> }
 Do you want to add friend ?(y/n) :
 n
 Do you want to remove friend ?(y/n) :
 n
 Your current friend list : { <Koby> <Mirsaeid> }



 Welcome to Facebook !
 Please enter choice :
 1. Join the Facebook network
 2. Leave the Facebook network (remove user and profile)
 3. Existing user, want to create/modify profile
 4. Search for friend in Facebook network
 5. Add/Remove friend to the profile
 6. Enter status
 7. Print profile
 8. Exit
 Choice :
 6
 Please enter your ID name to enter status :
 Peter
 Your ID : Peter
 Your current status : Active
 Please enter your new status
 Inactive
 Your new status : Inactive



 Welcome to Facebook !
 Please enter choice :
 1. Join the Facebook network
 2. Leave the Facebook network (remove user and profile)
 3. Existing user, want to create/modify profile
 4. Search for friend in Facebook network
 5. Add/Remove friend to the profile
 6. Enter status
 7. Print profile
 8. Exit
 Choice :
 7
 Please enter your ID name to enter status :
 Peter
 Your ID : Peter
 Your new profile : ID = Peter,  password = Vu,  status = Inactive



 Welcome to Facebook !
 Please enter choice :
 1. Join the Facebook network
 2. Leave the Facebook network (remove user and profile)
 3. Existing user, want to create/modify profile
 4. Search for friend in Facebook network
 5. Add/Remove friend to the profile
 6. Enter status
 7. Print profile
 8. Exit
 Choice :
 7
 Please enter your ID name to enter status :
 Brian
 Your ID : Brian
 Your new profile : ID = Brian,  password = Huh,  status = Active



 Welcome to Facebook !
 Please enter choice :
 1. Join the Facebook network
 2. Leave the Facebook network (remove user and profile)
 3. Existing user, want to create/modify profile
 4. Search for friend in Facebook network
 5. Add/Remove friend to the profile
 6. Enter status
 7. Print profile
 8. Exit
 Choice :
 2
 Please enter your deleting ID name
 Jennifer
 Your deleting ID : Jennifer



 Welcome to Facebook !
 Please enter choice :
 1. Join the Facebook network
 2. Leave the Facebook network (remove user and profile)
 3. Existing user, want to create/modify profile
 4. Search for friend in Facebook network
 5. Add/Remove friend to the profile
 6. Enter status
 7. Print profile
 8. Exit
 Choice :
 7
 Please enter your ID name to enter status :
 Jennifer
 Your ID : Jennifer
 Your ID : Jennifer is not found in the network ????



 Welcome to Facebook !
 Please enter choice :
 1. Join the Facebook network
 2. Leave the Facebook network (remove user and profile)
 3. Existing user, want to create/modify profile
 4. Search for friend in Facebook network
 5. Add/Remove friend to the profile
 6. Enter status
 7. Print profile
 8. Exit
 Choice :
 8
 Bye!! Come again.

 Process finished with exit code 0
 */