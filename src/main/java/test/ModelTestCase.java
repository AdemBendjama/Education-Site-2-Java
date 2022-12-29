package test;

import model.*;
import org.junit.jupiter.api.*;

public class ModelTestCase {
    //
    private static final UserManager userManager = new UserManager();
    private static final AdminManager adminManager = new AdminManager();
    private static final LoginAuthenticator loginAuthenticator = new LoginAuthenticator();

    @Nested
    @DisplayName("TESTS")
    class Tests {

        // Add Multiple Accounts before the tests
        @BeforeAll
        static void addTestUsers() {
            //
            userManager.addUser(new User("adambendjamaa2013@gmail.com", "adamo", "Adamo Bendjamaa", "student"));

            userManager.addUser(new User("dawnson@gmail.com", "dawnson", "dawnson", "student"));
        }

        // Remove the Added Accounts before the tests
        @AfterAll
        static void removeTestUsers() {
            //
            userManager.deleteUser("adambendjamaa2013@gmail.com");

            userManager.deleteUser("dawnson@gmail.com");
            userManager.deleteUser("tracymcgrady@gmail.com");
        }

        @Nested
        @DisplayName("LoginAuthenticator Class")
        class LoginAuthenticator {

            @Nested
            @DisplayName("authenticate() Method")
            class authenticate {

                @Tag("database")
                @Test
                void authenticateWithValidAdminAccount() {
                    Admin admin = new Admin("adembendjama22@gmail.com", "Adem Bendjama", "ademben");


                    String privilegeLVL = loginAuthenticator.authenticate(admin.getEmail(), admin.getPassword());
                    Assertions.assertEquals("admin", privilegeLVL);
                }


                @Tag("database")
                @Test
                void authenticateWithValidTeacherAccount() {
                    User user = new User("testcase@gmail.com", "testcase", "testcase", "teacher");

                    userManager.addUser(user);

                    String privilegeLVL = loginAuthenticator.authenticate(user.getEmail(), user.getPassword());
                    Assertions.assertEquals("teacher", privilegeLVL);

                    userManager.deleteUser(user.getEmail());

                }

                @Tag("database")
                @Test
                void authenticateWithValidStudentAccount() {
                    User user = new User("testcase@gmail.com", "testcase", "testcase", "student");

                    userManager.addUser(user);

                    String privilegeLVL = loginAuthenticator.authenticate(user.getEmail(), user.getPassword());
                    Assertions.assertEquals("student", privilegeLVL);

                    userManager.deleteUser(user.getEmail());
                }

                @Tag("database")
                @Test
                void authenticateWithUnvalidAccount() {
                    User user = new User("unvalidcredential", "sqfqrgdf", "qsd", "sdq");


                    String privilegeLVL = loginAuthenticator.authenticate(user.getEmail(), user.getPassword());
                    Assertions.assertNull(privilegeLVL);

                }
            }
        }

        @Nested
        @DisplayName("UserManager Class")
        class UserManager {

            @Nested
            @DisplayName("testConnection() Method")
            class testConnection {
                //
                @Tag("connection")
                @Test
                void connection() {
                    //
                    Assertions.assertTrue(userManager.testConnection());
                }
            }

            @Nested
            @DisplayName("checkUser() Method")
            class checkUser {

                @Tag("DataBase")
                @Test
                void checkUserWithValidEmail() {
                    //
                    boolean result = userManager.checkUser("adambendjamaa2013@gmail.com");
                    Assertions.assertTrue(result);
                }

                @Tag("DataBase")
                @Test
                void checkUserWithUnvalidEmail() {
                    //
                    boolean result = userManager.checkUser("bbbbbbbb");
                    Assertions.assertFalse(result);
                }


            }

            @Nested
            @DisplayName("getUser() Method")
            class getUser {

                @Tag("DataBase")
                @Test
                void getTestUserPassword() {
                    // The Test Fails Because the Information Fetched From the Databased Is Saved
                    // Incorrectly in the getUser Class
                    User user = userManager.getUser("adambendjamaa2013@gmail.com");
                    String originalPassword = user.getPassword();
                    Assertions.assertEquals("Adamo Bendjamaa", originalPassword);
                }

            }

            @Nested
            @DisplayName("addUser() Method")
            class addUser {

                @Tag("DataBase")
                @Test
                void compareAddedUser() {
                    // The Error Here Is Also Related to The GetUser() method
                    User user = new User("tracymcgrady@gmail.com", "tmac404", "T-Mac", "student");
                    boolean resultOfAddition = userManager.addUser(user);
                    Assertions.assertTrue(resultOfAddition);
                    //
                    User userFromDatabase = userManager.getUser("tracymcgrady@gmail.com");
                    Assertions.assertEquals(user, userFromDatabase);
                }

                @Tag("DataBase")
                @Test
                void addExistantUser() {
                    User user = new User("adambendjamaa2013@gmail.com", "adamo", "Adamo Bendjamaa", "student");
                    Assertions.assertFalse(userManager.addUser(user));
                }

                @Tag("DataBase")
                @Test
                void addUserWithValidEmail() {
                    Assertions.assertTrue(userManager.addUser(new User("yamaha45bro@gmail.com", "theyamahadude", "kawazaki22", "student")));
                    userManager.deleteUser("yamaha45bro@gmail.com");
                }

                @Tag("DataBase")
                @Test
                void addUserWithUnvalidEmail() {
                    Assertions.assertFalse(userManager.addUser(new User("bbbbbb", "bbbbbbb", "bbbbbbb", "student")));
                    userManager.deleteUser("bbbbbb");
                }

            }

            @Nested
            @DisplayName("updateUser() Method")
            class updateUser {

                @Tag("DataBase")
                @Test
                void updateRankToTeacher() {
                    //
                    User user = new User("dawnson@gmail.com", "dawnson", "dawnson", "student");
                    user.setRank("teacher");
                    Assertions.assertTrue(userManager.updateUser("dawnson@gmail.com", user));
                }

                @Tag("DataBase")
                @Test
                void updateRankToUnvalidValue() {
                    // Test Fails Because You Should not be Able to Enter an unvalid Rank
                    // Rank Should be "student" or "teacher"
                    User user = new User("dawnson@gmail.com", "dawnson", "dawnson", "student");
                    user.setRank("afegsdgresgdwfgsfd");
                    Assertions.assertFalse(userManager.updateUser("dawnson@gmail.com", user));
                }

                @Tag("DataBase")
                @Test
                void updateNonExistantUser() {
                    User user = new User("65sb4h6d5m", "dahkghwnson", "dagkgjhgkwnson", "kjfhkjh");
                    Assertions.assertFalse(userManager.updateUser("hdfdguyutrdhd", user));

                }

            }

            @Nested
            @DisplayName("deleteUser() Method")
            class deleteUser {
                //
                @Tag("DataBase")
                @Test
                void deleteExistantUser() {
                    //
                    Assertions.assertTrue(userManager.addUser(new User("david@gmail.com", "david", "david", "student")));
                    Assertions.assertTrue(userManager.deleteUser("david@gmail.com"));

                }

                //
                @Tag("DataBase")
                @Test
                void deleteNonExistantUser() {
                    //
                    Assertions.assertFalse(userManager.deleteUser("hdfdgtrsghfhdgfdhd"));

                }

            }

        }

        @Nested
        @DisplayName("AdminManager Class")
        class AdminManager {

            @Nested
            @DisplayName("testConnection() Method")
            class testConnection {
                //
                @Tag("connection")
                @Test
                void connection() {
                    //
                    Assertions.assertTrue(adminManager.testConnection());
                }
            }

            @Nested
            @DisplayName("checkCredentials() Method")
            class checkCredentials {
                //
                @Tag("DataBase")
                @Test
                void enterCorrectAdminEmailPassword() {
                    //
                    Assertions.assertTrue(adminManager.checkCredentials("adembendjama22@gmail.com", "ademben"));
                }

                //
                @Tag("DataBase")
                @Test
                void enterUncorrectAdminEmailPassword() {
                    //
                    Assertions.assertFalse(adminManager.checkCredentials("qsdqsdqsd@gmail.com", "stevisdgqgo800"));
                }
            }

        }

    }

}
