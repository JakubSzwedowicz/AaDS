package List_1;

public class Student implements Comparable {
    // MEMBERS
    private String m_surname;
    private String m_firstName;
    private int m_index;

    // PUBLIC
    // Constructor
    public Student(String a_firstName, String a_surname, int a_index) throws NullPointerException, IllegalArgumentException {
        if (a_firstName == null || a_surname == null) {
            throw new NullPointerException("Personal details cannot be null!\n\t" +
                    "first Name = " + a_firstName + "\n\t" +
                    "surname = " + a_surname);
        } else if (a_firstName.equals("") || a_surname.equals("") || a_index < 0) {
            throw new IllegalAccessError("Given arguments are illegal!\n\t" +
                    "first Name = " + a_firstName + "\n\t" +
                    "surname = " + a_surname + "\n\t" +
                    "index = " + a_index);

        }

        m_firstName = a_firstName;
        m_surname = a_surname;
        m_index = a_index;
    }

    // Setters
    public boolean setFirstName(String a_firstName) {
        if (a_firstName == null || a_firstName.equals("")) {
            return false;
        }
        m_firstName = a_firstName;
        return true;
    }

    public boolean setSurname(String a_surname) {
        if (a_surname == null || a_surname.equals("")) {
            return false;
        }
        a_surname = a_surname;
        return true;
    }

    public boolean setIndex(int a_index) {
        if (a_index < 0) {
            return false;
        }
        m_index = a_index;
        return true;
    }

    // Getters
    public String getFirstName() {
        return m_firstName;
    }

    public String getSurname() {
        return m_surname;
    }

    public int getIndex() {
        return m_index;
    }

    // Prints
    public String toString() {
        return m_firstName + " " + m_surname + " " + m_index;
    }

    @Override
    public int compareTo(Object object) {
        if (object == null || !(object instanceof Student)) {
            return -1;
        } else if (object == this){
            return 0;
        }
        Student student = (Student) object;
        int diff = m_surname.compareTo(student.m_surname);
        if (diff == 0) {
            diff = m_firstName.compareTo(student.m_firstName);
            if (diff == 0) {
                diff = m_index - student.m_index;
            }
        }
        return diff;
    }

    @Override
    public boolean equals(Object object){
        return compareTo(object) == 0;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((m_firstName == null) ? 0 : m_firstName.hashCode());
        result = prime * result
                + ((m_surname == null) ? 0 : m_surname.hashCode());
        result = prime * result + m_index;
        return result;
    }
}
