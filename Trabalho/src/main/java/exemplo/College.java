package exemplo;

import java.util.List;

public class College {
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "College{" +
                "students=" + students +
                '}';
    }
}
