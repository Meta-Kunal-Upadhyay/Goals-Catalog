class Employee {
    String name;
    int age;
    double salary;
    Employee next;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.next = null;
    }
}

class EmployeeLinkedList {
    Employee head;

    // Insert employee at the end of the list
    public void add(String name, int age, double salary) {
        Employee newEmp = new Employee(name, age, salary);
        if (head == null) {
            head = newEmp;
        } else {
            Employee current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newEmp;
        }
    }

    // Sort the linked list using insertion sort
    public void sort() {
        Employee sorted = null;
        Employee current = head;

        while (current != null) {
            Employee next = current.next;
            sorted = insertInSortedOrder(sorted, current);
            current = next;
        }

        head = sorted;
    }

    // Insertion logic based on salary and age
    private Employee insertInSortedOrder(Employee sorted, Employee newEmp) {
        if (sorted == null || compareEmployees(newEmp, sorted) < 0) {
            newEmp.next = sorted;
            return newEmp;
        }

        Employee current = sorted;
        while (current.next != null && compareEmployees(newEmp, current.next) >= 0) {
            current = current.next;
        }

        newEmp.next = current.next;
        current.next = newEmp;
        return sorted;
    }

    // Comparison method for sorting
    private int compareEmployees(Employee e1, Employee e2) {
        if (e1.salary > e2.salary) return -1;
        if (e1.salary < e2.salary) return 1;
        if (e1.age < e2.age) return -1;
        if (e1.age > e2.age) return 1;
        return 0;
    }

    // Print the linked list
    public void printList() {
        Employee current = head;
        while (current != null) {
            System.out.println(current.name + " | Age: " + current.age + " | Salary: " + current.salary);
            current = current.next;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        EmployeeLinkedList list = new EmployeeLinkedList();

        list.add("Alice", 30, 70000);
        list.add("Bob", 25, 90000);
        list.add("Charlie", 28, 70000);
        list.add("David", 40, 90000);
        list.add("Eve", 22, 70000);

        System.out.println("Before Sorting:");
        list.printList();

        list.sort();

        System.out.println("\nAfter Sorting:");
        list.printList();
    }
}
