package model;

@SuppressWarnings("unused")
public class User {
    //
    private String email;
    private String password;
    private String username;
    private String rank;

    public User(String email, String username, String password, String rank) {
        this.email = email.trim();
        this.username = username.trim();
        this.password = password.trim();
        this.rank = rank.trim();
    }


    @Override
    public String toString() {
        return "Email  [" + email + "],  Username  [" + username + "],  Password  [" + password + "],  Rank  [" + rank + "]";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((rank == null) ? 0 : rank.hashCode());
        return result;
    }

    // Method Allow for Comparisons to be done between This Class Object
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (rank == null) {
            return other.rank == null;
        } else return rank.equals(other.rank);
    }

}