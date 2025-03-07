import java.util.Scanner;
import java.util.*;
class JobScheduler {

    public static void sortbyColumn(int a[][], int c){      
            Arrays.sort(a, (x, y) -> Integer.compare(x[c],y[c]));  
    }

    private int[][] processes;
    private int[] completionTime;
    private int[] waitingTime;
    private int[] turnAroundTime;


    public JobScheduler(int[][] processes) {
        this.processes = processes;
        int n = processes.length;
        completionTime = new int[n];
        waitingTime = new int[n];
        turnAroundTime = new int[n];
    }

// AT  BT  CT  WT TAT
// [0][10]  10  0 10 
// [6][20]  30  4  24 
// [60][10] 70  0  10   
// [110][5] 115 0   5 
    public void execute() {
        int n = processes.length;
        int currentTime = 0;

        // Calculating Completion Time
        for (int i = 0; i < n; i++) {
            int arrivalTime = processes[i][0];
            int burstTime = processes[i][1];

            if (currentTime < arrivalTime) {
                currentTime = arrivalTime; 
            }

            currentTime += burstTime;
            completionTime[i] = currentTime;
        }

        // Calculating Waiting Time
        for (int i = 0; i < n; i++) {
            waitingTime[i] = completionTime[i] - processes[i][0] - processes[i][1];
            if (waitingTime[i] < 0) waitingTime[i] = 0; 
        }


        // Calculating Turn Around Time
        for (int i = 0; i < n; i++) {
            turnAroundTime[i] = completionTime[i] - processes[i][0];
        }


        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += waitingTime[i];
        }

        int maxWait = waitingTime[0];
        for (int i = 1; i < n; i++) {
            if (waitingTime[i] > maxWait) {
                maxWait = waitingTime[i];
            }
        }

        System.out.println("\nProcess\tArrival\tBurst\tCompletion\tWaiting\tTurnAround");
        for (int i = 0; i < n; i++) {
            System.out.println(i + 1 + "\t" + processes[i][0] + "\t" + processes[i][1] + "\t" + completionTime[i] + "\t\t" + waitingTime[i] + "\t" + turnAroundTime[i]);
        }

        System.out.println("\nAverage Waiting Time: " + ((double) sum / n));
        System.out.println("Maximum Waiting Time: " + maxWait);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
       
        int[][] processes = new int[n][2];
       
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time and Burst Time for Process " + (i + 1) + ": ");
            processes[i][0] = sc.nextInt();
            processes[i][1] = sc.nextInt();
        }
        
        JobScheduler s1 = new JobScheduler(processes);
        s1.sortbyColumn(processes, 0);
        // s1.sortbyColumn(processes, 1);
        s1.execute();
       
        sc.close();
    }
}

