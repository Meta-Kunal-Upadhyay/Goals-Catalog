public class Main {
    public static void main(String[] args) {
        EmployeeLinkedList list = new EmployeeLinkedList();

        list.add("Muskan", 19, 70000);
        list.add("Kunal", 21, 90000);
        list.add("Prateek", 23, 70000);
        list.add("Prakshat", 22, 90000);
        list.add("Pankaj", 28, 70000);

        System.out.println("Before Sorting:");
        list.printList();

        list.quickSort();

        System.out.println("\nAfter Sorting:");
        list.printList();
    }
}