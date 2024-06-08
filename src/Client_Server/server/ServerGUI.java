package Client_Server.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements IServerView {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    private JButton btnStart, btnStop;
    private JTextArea log;
    private IServerController serverController;

    public ServerGUI(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);
        createPanel();
        setVisible(true);

    }

    public void setServerController(IServerController serverController) {
        this.serverController = serverController;
    }

    public void appendLog(String text){
        log.append(text + "\n");
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverController.isWork()){
                    appendLog("Сервер уже был запущен");
                } else {
                    serverController.setWork(true);
                    appendLog("Сервер запущен!");
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!serverController.isWork()){
                    appendLog("Сервер уже был остановлен");
                } else {
                    serverController.setWork(false);
                    while (!serverController.getClientsList().isEmpty()){
                        serverController.disconnectUser(serverController.getClientsList()
                                .get(serverController.getClientsList().size()-1));
                    }
                    appendLog("Сервер остановлен!");
                }
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }
}
