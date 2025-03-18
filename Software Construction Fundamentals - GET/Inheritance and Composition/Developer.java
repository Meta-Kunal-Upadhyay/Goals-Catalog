class Developer extends Employee {
    private double basicSalary;
    private double bonus;
    
    Developer(String name, String id, double basicSalary, double bonus) {
        super(name, id);
        this.basicSalary = basicSalary;
        this.bonus = bonus;
    }

    @Override
    double getBasicSalary() {
        return basicSalary;
    }

    @Override
    double getBonus() {
        return bonus;
    }

    @Override
    double getCompensation() {
        return 0; 
    }
}
