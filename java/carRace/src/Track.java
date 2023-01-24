import javax.swing.*;

public class Track extends JFrame{
    private JPanel mainPanel;
    private JProgressBar progressBarCar1;
    private JProgressBar progressBar2;
    private JProgressBar progressBar3;
    private JProgressBar progressBar4;
    private JButton startRaceButton;
    private JLabel lblTitle;
    private JLabel lblCar1;
    private JLabel lblCar2;
    private JLabel lblCar3;
    private JLabel lblCar4;

    public Track(){
        setContentPane(mainPanel);
        setTitle("Car Race");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        Track track = new Track();
    }
        FirstCar firstCar = new FirstCar();
}
