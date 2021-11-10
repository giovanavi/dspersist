package exemplo;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SerializeXML {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setId(101);
        s1.setName("Giovana");

        Student s2 = new Student();
        s2.setId(102);
        s2.setName("Arthur");

        List<Student> s = new ArrayList<>();
        s.add(s1);
        s.add(s2);

        System.out.println(s);

        College c = new College();
        c.setStudents(s);

        System.out.println(c);
        try {
            FileOutputStream fos = new FileOutputStream("students2.xml");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            XMLEncoder encoder = new XMLEncoder(bos);
            encoder.writeObject(c);
            encoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
