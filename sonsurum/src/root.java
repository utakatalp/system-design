public class root {
    private Registration registration;
    private User user;
    private AdministratorUI administrator;
    private ApartmentResidentUI siteSakini;
    private ResidentLoginUI main2;

    public root() {
        registration = new Registration();
        while (registration.getUser() == null) { // preventing null pointer exception while registration processing
            try {
                Thread.sleep(100); // Wait 100ms then check again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        user = registration.getUser();
        System.out.println(user.getUserName() + user.getName() + new String(user.getPassword()));
    }
    //registration.setUserReadyListener(user -> {
    // This code will run once the user is set
    //System.out.println(user.getUserName() + user.getName() + new String(user.getPassword()));
    //});
    public static void main(String[] args) {
        new root();
    }
}

