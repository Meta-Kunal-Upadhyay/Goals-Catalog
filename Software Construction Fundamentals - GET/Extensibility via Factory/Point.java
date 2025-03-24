public class Point {
    private double xCordinate;
    private double yCordinate;
    Point(double xCordinate, double yCordinate){
        this.xCordinate = xCordinate;
        this.yCordinate = yCordinate;
    }

    public double getX(){return xCordinate;} // return x cordinate
    public double getY(){return yCordinate;} // return y cordinate
    public double distanceFromOrigin(){
        double distance;
        distance = Math.sqrt(Math.pow(xCordinate, 2)+Math.pow(yCordinate, 2));
        return distance;}
}
