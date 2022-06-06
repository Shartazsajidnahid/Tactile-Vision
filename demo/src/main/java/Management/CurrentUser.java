package Management;

public class CurrentUser {
    private static CurrentUser instance = new CurrentUser();

    //make the constructor private so that this class cannot be
    //instantiated
    private String name,email,phone, userType;
    private boolean isset = false;
    private CurrentUser(){}

    //Get the only object available
    public static CurrentUser getInstance(){
        if (instance==null)
            instance = new CurrentUser();
        return instance;
    }

    public void setUserDetails(String userType, String name, String email, String phone){
        setUserType(userType);
        setName(name);
        setEmail(email);
        setPhone(phone);
        setIsset(true);
//        toString();
    }




    public static void setInstance(CurrentUser instance) {
        CurrentUser.instance = instance;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isIsset() {
        return isset;
    }

    public void setIsset(boolean isset) {
        this.isset = isset;
    }

    @Override
    public String toString() {
        System.out.println("CurrentUser{" + "name='" + name + ", email='" + email + ", phone='" + phone + ", isset=" + isset + "  Usertype : " +userType);
        return null;
    }
}
