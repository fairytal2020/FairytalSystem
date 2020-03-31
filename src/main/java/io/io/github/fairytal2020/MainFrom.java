

package io.io.github.fairytal2020;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainFrom extends JFrame {

    private JPanel contentPane;
    private JTextField subject;
    private JTextField path;
    private JTextField author;
    private JTextField time;
    JList applyList = new JList();;

    /**
     * Launch the application.
     */


    /**
     * Create the frame.
     */
    public MainFrom() {
        setTitle("FairtalSystem\u529E\u516C\u7CFB\u7EDF--\u4E3B\u754C\u9762");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 498, 542);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("\u516C\u544A\u53D1\u5E03\uFF08\u672A\u5B9E\u73B0\uFF09");

        JLabel lblNewLabel_1 = new JLabel("\u52A0\u5165\u7533\u8BF7\u6279\u9605");

        JLabel lblNewLabel_2 = new JLabel("\u6807\u9898\uFF1A");

        subject = new JTextField();
        subject.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("\u5185\u5BB9\uFF1A");

        JScrollPane scrollPane = new JScrollPane();

        JScrollPane scrollPane_1 = new JScrollPane();

        JButton watch = new JButton("\u67E5\u770B&\u6279\u9605");
        watch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                final ApplyFrom[] from = new ApplyFrom[1];
                int index = applyList.getSelectedIndex();
                ListModel<String> model= applyList.getModel();
                String nameSe = model.getElementAt(index);
                ArrayList<MailJoinInApply> mails = Main.reader.getMailReadied();
                String skillSe = null;
                String contactSe = null;
                String otherSe = null;
                String sender = null;
                for(MailJoinInApply apply : mails){
                    if(apply.getContent("name").equals(nameSe)){
                        skillSe = apply.getContent("skill");
                        contactSe = apply.getContent("contact");
                        otherSe = apply.getContent("other");
                        sender = Main.reader.getSenders().get(mails.indexOf(apply));
                    }
                }
                String finalSkillSe = skillSe;
                String finalContactSe = contactSe;
                String finalOtherSe = otherSe;
                String finalSender = sender;
                Runnable run = new Runnable() {

                    @Override
                    public void run() {
                        from[0] = new ApplyFrom(nameSe , finalSkillSe, finalContactSe, finalOtherSe , finalSender);
                        from[0].setVisible(true);
                    }
                };
                Thread t = new Thread(run);
                t.start();
            }
        });

        JLabel lblNewLabel_4 = new JLabel("\u6587\u4EF6\u4E0A\u4F20\uFF08\u672A\u5B9E\u73B0\uFF09");

        JLabel lblNewLabel_5 = new JLabel("\u6587\u4EF6\u8DEF\u5F84\uFF1A");

        path = new JTextField();
        path.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("\u4F5C\u8005\uFF1A");

        author = new JTextField();
        author.setColumns(10);

        JLabel lblNewLabel_7 = new JLabel("\u65F6\u95F4\uFF1A");

        time = new JTextField();
        time.setColumns(10);

        JButton upload = new JButton("\u4E0A\u4F20");
        upload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

        JLabel state = new JLabel("\u72B6\u6001\uFF1A");
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                .addComponent(lblNewLabel)
                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                        .addGap(10)
                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                .addComponent(lblNewLabel_3)
                                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                        .addComponent(lblNewLabel_2)
                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                        .addComponent(subject, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                        .addGap(74)
                                                                        .addComponent(watch))
                                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                        .addGap(18)
                                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                                .addComponent(lblNewLabel_1)
                                                                                .addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)))))
                                                .addComponent(lblNewLabel_4)
                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                        .addGap(10)
                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                        .addComponent(lblNewLabel_5)
                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                        .addComponent(path))
                                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                        .addComponent(lblNewLabel_6)
                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                        .addComponent(author, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18)
                                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                                .addComponent(upload)
                                                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                                        .addComponent(lblNewLabel_7)
                                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                                        .addComponent(time, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))))))
                                        .addComponent(state))
                                .addContainerGap(25, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel)
                                        .addComponent(lblNewLabel_1))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(lblNewLabel_2)
                                                        .addComponent(subject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(lblNewLabel_3)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18)
                                                .addComponent(watch)))
                                .addGap(18)
                                .addComponent(lblNewLabel_4)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel_5)
                                        .addComponent(path, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel_6)
                                        .addComponent(author, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_7)
                                        .addComponent(time, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(upload)
                                .addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addComponent(state))
        );


        applyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane_1.setViewportView(applyList);

        JTextArea content = new JTextArea();
        scrollPane.setViewportView(content);
        contentPane.setLayout(gl_contentPane);
    }

   /* @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    public void setContentPane(JPanel contentPane) {
        this.contentPane = contentPane;
    }

    public JTextField getSubject() {
        return subject;
    }

    public void setSubject(JTextField subject) {
        this.subject = subject;
    }

    public JTextField getPath() {
        return path;
    }

    public void setPath(JTextField path) {
        this.path = path;
    }

    public JTextField getAuthor() {
        return author;
    }

    public void setAuthor(JTextField author) {
        this.author = author;
    }

    public JTextField getTime() {
        return time;
    }

    public void setTime(JTextField time) {
        this.time = time;
    }

    public JList getApplyList() {
        return applyList;
    }

    public void setApplyList(JList applyList) {
        this.applyList = applyList;
    }*/
}
