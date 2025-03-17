import java.util.*;

class Marksheet {
    private int[] grades; 
    private int totalStudents; 


    public Marksheet(int[] grades) {
        this.grades = grades;
        this.totalStudents = grades.length;
    }

    
    public double getAverageGrade() throws ArithmeticException {
        if (totalStudents == 0) {
            throw new ArithmeticException("No students available to calculate average.");
        }

        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return Math.round((sum / (double) totalStudents) * 100.0) / 100.0;
    }

    
    public int getMaximumGrade() throws ArithmeticException {
        if (totalStudents == 0) {
            throw new ArithmeticException("No students available to find maximum grade.");
        }
        
        int maxGrade = grades[0];
        for (int grade : grades) {
            if (grade > maxGrade) {
                maxGrade = grade;
            }
        }
        return maxGrade;
    }

    
    public int getMinimumGrade() throws ArithmeticException {
        if (totalStudents == 0) {
            throw new ArithmeticException("No students available to find minimum grade.");
        }

        int minGrade = grades[0];
        for (int grade : grades) {
            if (grade < minGrade) {
                minGrade = grade;
            }
        }
        return minGrade;
    }

    
    public double getPassPercentage() throws ArithmeticException {
        if (totalStudents == 0) {
            throw new ArithmeticException("No students available to calculate pass percentage.");
        }

        int passCount = 0;
        for (int grade : grades) {
            if (grade >= 40) {
                passCount++;
            }
        }

        return Math.round(((passCount / (double) totalStudents) * 100) * 100.0) / 100.0;
    }

    public void displayResults() {
        try {
            System.out.println("Average Grade: " + getAverageGrade());
            System.out.println("Maximum Grade: " + getMaximumGrade());
            System.out.println("Minimum Grade: " + getMinimumGrade());
            System.out.println("Pass Percentage: " + getPassPercentage() + "%");
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Number of students must be greater than zero.");
            return;
        }

        int[] grades = new int[n];
        System.out.println("Enter the grades for " + n + " students (between 0-100):");

        for (int i = 0; i < n; i++) {
            grades[i] = sc.nextInt();

            if (grades[i] < 0 || grades[i] > 100) {
                System.out.println("Invalid grade entered. Please enter grades between 0 and 100.");
                return;
            }
        }

        Marksheet marksheet = new Marksheet(grades);
        marksheet.displayResults();



        sc.close();
    }
}