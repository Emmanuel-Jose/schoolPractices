import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

class Track extends JFrame implements Observer {
    private Thread[] threads;
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

        threads = new Thread[4];

        startRaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < threads.length; i++){
                    Car c = new Car("Car " + (i + 1));
                    c.addObserver(Track.this);
                    threads[i] = new Thread(c);
                    threads[i].start();
                }
            }
        });

    }

    @Override
    public void update(Observable o, Object arg) {
        Car c = (Car) o;
        int length = (int) arg;
//        System.out.println("Car " + c.getName() + " length: " + length);
        switch (c.getName()){
            case "Car 1":
                progressBarCar1.setValue(length);
                break;
            case "Car 2":
                progressBar2.setValue(length);
                break;
            case "Car 3":
                progressBar3.setValue(length);
                break;
            case "Car 4":
                progressBar4.setValue(length);
                break;
        }
        if ( length >= 100 ){
//            terminar();
        }
    }

    public static void main(String[] args){
        Track track = new Track();
    }
}
