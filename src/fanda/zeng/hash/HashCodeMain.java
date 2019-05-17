package fanda.zeng.hash;

import java.util.Objects;

public class HashCodeMain {

    public static void main(String[] args) {
        Integer a = -42;
        Double b = 3.1415926;
        String c = "leetcode";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
        System.out.println(new Student(3, 9, "zeng", "fanda").hashCode());
        System.out.println(new Student(3, 9, "zeng", "fanda").hashCode());
    }

    public static class Student {
        private int grade;
        private int cls;
        private String firstName;
        private String lastName;

        public Student(int grade, int cls, String firstName, String lastName) {
            this.grade = grade;
            this.cls = cls;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public int hashCode() {

//            int B = 31;
//            int hash = 0;
//            hash = hash * B + grade;
//            hash = hash * B + cls;
//            // 字符串不区分大小写
//            hash = hash * B + firstName.toLowerCase().hashCode();
//            hash = hash * B + lastName.toLowerCase().hashCode();
//            return hash;
            return Objects.hash(grade, cls, firstName, lastName);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return grade == student.grade &&
                    cls == student.cls &&
                    firstName.equals(student.firstName) &&
                    lastName.equals(student.lastName);
        }
    }
}
