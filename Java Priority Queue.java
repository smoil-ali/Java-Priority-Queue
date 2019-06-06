import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;
import java.util.PriorityQueue;
class Student{
    int id;
    float cgpa;
    String name;
    Student(int id,float cgpa,String name){
        super();
        this.id=id;
        this.cgpa=cgpa;
        this.name=name;
    }
    String getName(){
        return name;
    }
    float getCgpa(){
        return cgpa;
    }
    int getToken(){
        return id;
    }
}

class Priorities{
    List<Student> getStudents(List<String> list1){
        PriorityQueue < Student > student_queue = new PriorityQueue(Comparator.comparing(Student::getCgpa).reversed().thenComparing(Student::getName).thenComparing(Student::getToken));
        
        List<Student> students=new ArrayList<Student>();
        for(String val:list1){
            Scanner sc=new Scanner(val);
            String type=sc.next();
            if(type.equals("ENTER")){
                String name=sc.next();
                float cgpa=sc.nextFloat();
                int id=sc.nextInt();
                Student student=new Student(id,cgpa,name);
                student_queue.add(student);    
            }else if(type.equals("SERVED")){
                student_queue.poll();
            }sc.close();
        }
        Student first = student_queue.poll();
        if (first == null) {
            return students;
        } else {
            while (first != null) {

                students.add(first);
                first = student_queue.poll();

            }
            return students;
        }
    }
}


public class Solution {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();
    
    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());    
        List<String> events = new ArrayList<>();
        
        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }
        
        List<Student> students = priorities.getStudents(events);
        
        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }
}
