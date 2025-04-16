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

    // Add an employee to the end of the list
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

    // Sort the linked list using Quick Sort
    public void quickSort() {
        head = quickSortRecursive(head, getTail(head));
    }

    // Recursive Quick Sort method
    private Employee quickSortRecursive(Employee head, Employee tail) {
        if (head == null || head == tail) {
            return head;
        }

        Employee[] partitioned = partition(head, tail);
        Employee pivot = partitioned[1];

        if (partitioned[0] != pivot) {
            Employee temp = partitioned[0];
            while (temp.next != pivot) {
                temp = temp.next;
            }
            temp.next = null;
            partitioned[0] = quickSortRecursive(partitioned[0], temp);
            temp = getTail(partitioned[0]);
            temp.next = pivot;
        }

        pivot.next = quickSortRecursive(pivot.next, partitioned[2]);
        return partitioned[0];
    }

    // Partition the list around the pivot (last node)
    private Employee[] partition(Employee head, Employee tail) {
        Employee pivot = tail;
        Employee prev = null, current = head, newHead = null, newTail = pivot;

        while (current != pivot) {
            if (compareEmployees(current, pivot) >= 0) {
                if (newHead == null) {
                    newHead = current;
                }
                prev = current;
            } else {
                if (prev != null) {
                    prev.next = current.next;
                }
                Employee temp = current.next;
                current.next = null;
                newTail.next = current;
                newTail = current;
                current = temp;
                continue;
            }
            current = current.next;
        }

        if (newHead == null) {
            newHead = pivot;
        }

        return new Employee[]{newHead, pivot, newTail};
    }

    // Get the tail of the linked list
    private Employee getTail(Employee node) {
        while (node != null && node.next != null) {
            node = node.next;
        }
        return node;
    }

    // Comparison logic based on salary and age
    private int compareEmployees(Employee e1, Employee e2) {
        if (e1.salary < e2.salary) return -1;  // Lower salary comes first
        if (e1.salary > e2.salary) return 1;   // Higher salary comes later
        if (e1.age < e2.age) return -1;        // Younger age comes first
        if (e1.age > e2.age) return 1;         // Older age comes later
        return 0;                              // Equal salary and age
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