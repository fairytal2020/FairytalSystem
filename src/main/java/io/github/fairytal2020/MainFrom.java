

/*
 *
 *     FairytalSystem
 *     A tool for Fairytal team
 *     Copyright (c) 2020 Fairytal team
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *     Contact me : fairytal2020@outlook.com
 *
 *     FairytalSystem
 *     Fairytal团队工具
 *     版权所有（C）2020 Fairytal团队
 *     本程序为自由软件，在自由软件联盟发布的GNU通用公共许可协议的约束下，你可以对其进行再发布及修改。协议版本为第三版或（随你）更新的版本。
 *     我们希望发布的这款程序有用，但不保证，甚至不保证它有经济价值和适合特定用途。详情参见GNU通用公共许可协议。
 *     你理当已收到一份GNU通用公共许可协议的副本，如果没有，请查阅<http://www.gnu.org/licenses/>
 *
 *     联系方式： fairytal2020@outlook.com
 */

package io.github.fairytal2020;

import cn.hutool.dfa.WordTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
/**
 * @author wangshengkai
 * @author email:wangshengkai2007_code1@outlook.com
 */
public class MainFrom extends JFrame {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainFrom.class);
    private JPanel contentPane;
    private JTextField subject;
    private JTextField path;
    private JTextField author;
    private JTextField time;
    private WordTree tree = new WordTree();
    JList applyList = new JList();



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
                LOGGER.info("Initializing apply from...");
                final ApplyFrom[] from = new ApplyFrom[1];
                int index = applyList.getSelectedIndex();
                ListModel<String> model= applyList.getModel();
                String nameSe = null;
                try{
                    nameSe = model.getElementAt(index);
                }catch (Exception e){
                    LOGGER.warn(e.toString());
                    return;
                }

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
                String finalNameSe = nameSe;
                Runnable run = new Runnable() {

                    @Override
                    public void run() {
                        from[0] = new ApplyFrom(finalNameSe, finalSkillSe, finalContactSe, finalOtherSe , finalSender);
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

        JLabel state = new JLabel("其实那些都实现了，只是我太懒，懒得改");
        JTextArea content = new JTextArea();
        JButton btnNewButton = new JButton("\u53D1\u5E03");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(tree.isMatch(subject.getText()) || tree.isMatch(content.getText())){
                    LOGGER.warn("DFA check failed");
                }else{
                    LOGGER.info("Publishing...");
                    MailUtils mail = new MailUtils("outlook.live.com" , "fairytal2020@outlook.com" , "fairytalbzfx2020");
                    try {
                        mail.send(subject.getText() , new String[]{"fairytal2020-send1@outlook.com"} , null , content.getText());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }


            }
        });
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                .addComponent(lblNewLabel)
                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                        .addGap(10)
                                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(lblNewLabel_3)
                                                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                                        .addComponent(lblNewLabel_2)
                                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                                        .addComponent(subject, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))))
                                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                        .addGap(72)
                                                                        .addComponent(btnNewButton)))
                                                        .addPreferredGap(ComponentPlacement.RELATED)
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
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(lblNewLabel_2)
                                                        .addComponent(subject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(lblNewLabel_3)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(scrollPane))
                                        .addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(watch)
                                        .addComponent(btnNewButton))
                                .addGap(24)
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


        scrollPane.setViewportView(content);
        contentPane.setLayout(gl_contentPane);
        tree.addWords("死" , "死马" , "傻" , "傻逼" , "傻吊" , "沙雕");
    }
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

