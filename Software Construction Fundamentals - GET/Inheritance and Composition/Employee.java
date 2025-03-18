abstract class Employee {
    String name;
    String id;

    Employee(String name, String id){
        this.name = name;
        this.id = id;
    }

    abstract double getBasicSalary();
    abstract double getBonus();
    abstract double getCompensation();

    double calculateSalary() {
        return getBasicSalary() + getBonus() + getCompensation();
    }

}