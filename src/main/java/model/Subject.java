package model;

public class Subject {
    //
    private String name;
    private String specialty;

    //
    public Subject(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getSpecialty() {
        return specialty;
    }


    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }


    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", specialty='" + specialty + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (!name.equals(subject.name)) return false;
        return specialty.equals(subject.specialty);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + specialty.hashCode();
        return result;
    }
}
