

package io.io.github.fairytal2020;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ApplyFrom extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */


    /**
     * Create the frame.
     */
    public ApplyFrom(String nameStr , String skillStr , String contactStr , String otherStr , String sender) {
        setTitle("FairytalSystem\u529E\u516C\u7CFB\u7EDF--\u52A0\u5165\u7533\u8BF7\u6279\u9605");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 354, 480);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("\u59D3\u540D\uFF1A");

        JLabel lblNewLabel_1 = new JLabel("\u6280\u80FD\uFF1A");

        JScrollPane scrollPane = new JScrollPane();

        JScrollPane scrollPane_1 = new JScrollPane();

        JLabel lblNewLabel_2 = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");

        JScrollPane scrollPane_2 = new JScrollPane();

        JLabel lblNewLabel_3 = new JLabel("\u5907\u6CE8\uFF1A");

        JScrollPane scrollPane_3 = new JScrollPane();

        JButton ok = new JButton("\u540C\u610F");
        ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MailUtils mail = new MailUtils("outlook.live.com" , "fairytal2020@outlook.com" , "fairytalbzfx2020");

            }
        });

        JButton notok = new JButton("\u62D2\u7EDD");
        notok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MailUtils mail = new MailUtils("outlook.live.com" , "fairytal2020@outlook.com" , "fairytalbzfx2020");
            }
        });
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblNewLabel)
                                                        .addComponent(lblNewLabel_1)
                                                        .addComponent(lblNewLabel_2)
                                                        .addComponent(lblNewLabel_3)
                                                        .addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(62)
                                                .addComponent(ok)
                                                .addGap(34)
                                                .addComponent(notok)))
                                .addContainerGap(16, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNewLabel)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(lblNewLabel_1)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(lblNewLabel_2)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(lblNewLabel_3)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(ok)
                                        .addComponent(notok))
                                .addContainerGap(58, Short.MAX_VALUE))
        );

        JTextArea other = new JTextArea();
        other.setEditable(false);
        scrollPane_3.setViewportView(other);

        JTextArea contact = new JTextArea();
        contact.setEditable(false);
        scrollPane_2.setViewportView(contact);

        JTextArea skill = new JTextArea();
        skill.setEditable(false);
        scrollPane_1.setViewportView(skill);

        JTextArea name = new JTextArea();
        name.setEditable(false);
        scrollPane.setViewportView(name);
        contentPane.setLayout(gl_contentPane);
        other.setText(otherStr);
        contact.setText(contactStr);
        skill.setText(skillStr);
        name.setText(nameStr);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

}
