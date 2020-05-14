package io.github.fairytal2020;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
/**
 * @author wangshengkai
 * @author email:wangshengkai2007_code1@outlook.com
 */
public class LoginFrom extends JFrame {

    private ShearCaptcha cap = CaptchaUtil.createShearCaptcha(150, 65, 4, 4);

    private JPanel contentPane;
    private JTextField txtFairytaloutlookcom;
    private JTextField password;
    private JTextField userInuptCaptcha;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginFrom.class);

    /**
     * Create the frame.
     */
    public LoginFrom() {
        setTitle("FairytalSystem--\u767B\u5F55");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 375, 255);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");

        txtFairytaloutlookcom = new JTextField();
        txtFairytaloutlookcom.setEnabled(false);
        txtFairytaloutlookcom.setEditable(false);
        txtFairytaloutlookcom.setText("fairytal2020@outlook.com");
        txtFairytaloutlookcom.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");

        password = new JTextField();
        password.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("\u6211\u4E0D\u662F\u673A\u5668\u4EBA\uFF1A");

        JLabel captcha = new JLabel("");

        userInuptCaptcha = new JTextField();
        userInuptCaptcha.setColumns(10);

        JButton login = new JButton("\u767B\u5F55");

        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LOGGER.info("Verifying captcha...");
                if("fairytalbzfx2020".equals(password.getText()) && cap.verify(userInuptCaptcha.getText())){
                    LOGGER.info("Captcha verify succeeded , calling main from...");
                    setVisible(false);
                    Runnable run = () -> {
                        new Main().go();
                    };
                    Thread t = new Thread(run);
                    t.start();
                }else{
                    LOGGER.info("Captcha verify failed.");
                }
                cap.createCode();
                cap.write(new File("cap_img.jpg"));
                Image img = Toolkit.getDefaultToolkit().createImage("cap_img.jpg");
                captcha.setIcon(new ImageIcon(img));

            }
        });
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(lblNewLabel)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(txtFairytaloutlookcom, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(lblNewLabel_1)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(password, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
                                                        .addComponent(lblNewLabel_2)
                                                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                                                .addComponent(captcha, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(userInuptCaptcha, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap())
                                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                                .addComponent(login)
                                                .addGap(143))))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(24)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel)
                                        .addComponent(txtFairytaloutlookcom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel_1)
                                        .addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(lblNewLabel_2)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(captcha, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(54)
                                                .addComponent(userInuptCaptcha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(login)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LOGGER.info("Generating captcha...");
        contentPane.setLayout(gl_contentPane);
        cap.write(new File("cap_img.jpg"));
        LOGGER.info("Captcha generate have done.");
        captcha.setIcon(new ImageIcon("cap_img.jpg"));

    }
}






