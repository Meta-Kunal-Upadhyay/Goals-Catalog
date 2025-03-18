class Manager extends Employee {
    private double basicSalary;
    private double bonus;
    private double compensation;
    
    Manager(String name, String id, double basicSalary, double bonus, double compensation) {
        super(name, id);
        this.basicSalary = basicSalary;
        this.bonus = bonus;
        this.compensation = compensation;
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
        return compensation;
    }
}
