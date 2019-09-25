package vo;

import counters.Counter;
import util.CounterFactory;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @Author: Mexinz
 * @Date: 2019/9/24
 */
public class Welcome {
    private JTextField textField1;
    private JTextArea textArea1;
    private JButton button1;
    private JLabel label1;
    private JPanel panel1;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Welcome");
        frame.setSize(650, 500);
        frame.setLocation(600, 200);
        frame.setContentPane(new Welcome().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private Welcome() {
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.showOpenDialog(button1);
                //获取绝对路径
                String file = jFileChooser.getSelectedFile().getAbsolutePath();
                //要执行的操作
                String[] options = new String[]{"-l","-c","-w","-a"};
                //要输出到界面的内容
                StringBuilder printfStr = new StringBuilder(file+"\r\n");
                    for (String option : options) {
                        //获取对应的操作处理类
                        Counter counter = CounterFactory.getCounter(option);
                        if (counter != null) {
                            printfStr.append(counter.analyseFile(file).getResult());
                            textArea1.setText(printfStr.toString());
                        } else {
                            textArea1.setText("输入的参数错误");
                        }
                    }
            }
        });
    }
}
