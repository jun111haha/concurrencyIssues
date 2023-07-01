public class CountDownLatchT {
    int cnt = 1;
    public void call(){
        System.out.println("cnt = " + this.cnt++);
    }
}
