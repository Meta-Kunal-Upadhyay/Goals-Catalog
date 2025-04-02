import java.util.*;

class CounselingProcess {
    static class Program {
        String name;
        int capacity;
        Program(String name, int capacity) { 
            this.name = name; this.capacity = capacity; 
        }
    }
    
    static class Student {
        String name;
        List<String> preferences;
        String allocated = "Not Allocated";
        Student(String name, List<String> preferences) {
            this.name = name;
            this.preferences = preferences;
        }
    }
    
    public static void allocatePrograms(List<Program> programs, List<Student> students) {
        Map<String, Integer> programSeats = new HashMap<>();

        for (Program p : programs) {
            programSeats.put(p.name, p.capacity);
        }

        Queue<Student> queue = new CircularQueue<>(students.size());

        for (Student s : students) {
            queue.enqueue(s);
        }
        
        while (!queue.isEmpty()) {
            Student student = queue.dequeue();
            for (String choice : student.preferences) {
                if (programSeats.get(choice) > 0) {
                    student.allocated = choice;
                    programSeats.put(choice, programSeats.get(choice) - 1);
                    break;
                }
            }
        }
        
        for (Student s : students) {
            System.out.println(s.name + " -> " + s.allocated);
        }
    }

    public static void main(String[] args) {
        List<Program> programs = List.of(new Program("CSE", 2), new Program("ECE", 1));
        List<Student> students = List.of(
            new Student("Alice", List.of("CSE", "ECE")),
            new Student("Bob", List.of("ECE", "CSE")),
            new Student("Charlie", List.of("CSE", "ECE")),
            new Student("Kunal", List.of("CSE", "ECE"))

        );
        allocatePrograms(programs, students);
    }
}