package cn.edu.sustech.cs307.dto;

public class Instructor extends User {
    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
