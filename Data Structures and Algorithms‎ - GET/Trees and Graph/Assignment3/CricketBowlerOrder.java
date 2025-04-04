import java.util.*;

class Bowler {
    String name;
    int ballsLeft;

    public Bowler(String name, int ballsLeft) {
        this.name = name;
        this.ballsLeft = ballsLeft;
    }
}

public class CricketBowlerOrder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of bowlers: ");
        int numBowlers = scanner.nextInt();

        System.out.println("Enter the total number of balls Virat will play: ");
        int totalBalls = scanner.nextInt();
        scanner.nextLine(); 

        PriorityQueue<Bowler> pq = new PriorityQueue<>(new Comparator<Bowler>() {
            @Override
            public int compare(Bowler b1, Bowler b2) {
                return Integer.compare(b2.ballsLeft, b1.ballsLeft);
            }
        });


        for (int i = 0; i < numBowlers; i++) {
            System.out.println("Enter the name of the bowler " + (i + 1) + ": ");
            String name = scanner.nextLine();

            System.out.println("Enter the number of balls remaining for " + name + ": ");
            int ballsLeft = scanner.nextInt();
            scanner.nextLine();

            pq.offer(new Bowler(name, ballsLeft));
        }

        System.out.println("\nSequence of balls being bowled:");
        for (int i = 1; i <= totalBalls; i++) {
            Bowler bowler = pq.poll();
            System.out.println("Ball " + i + ": " + bowler.name);

            bowler.ballsLeft--;

            if (bowler.ballsLeft > 0) {
                pq.offer(bowler);
            }
        }

        scanner.close();
    }
}
