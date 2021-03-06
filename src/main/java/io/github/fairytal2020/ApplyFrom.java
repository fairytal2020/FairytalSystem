

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author wangshengkai
 * @author email:wangshengkai2007_code1@outlook.com
 */
public class ApplyFrom extends JFrame {

    private JPanel contentPane;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyFrom.class);

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
                LOGGER.info("Sending email...");
                MailUtils mail = new MailUtils("outlook.live.com" , "fairytal2020@outlook.com" , "fairytalbzfx2020");
                try {
                    mail.send("加入申请通知" , new String[]{sender} , new String[]{} , sender + "：\r\n我们十分愉快的通知您，您的加入申请已经被批准，稍后您将被加入到我们的内部群聊中。\r\n关于我们的更多信息将在稍后的邮件中通知您。\r\nFairytal团队，敬上");
                    mail.send("关于我们的更多信息" , new String[]{sender} , new String[]{} , sender + "：\r\n下面是关于我们的更多信息：\r\n1.我们的网站：https://fairytal2020.github.io/blog/\r\n2.我们的邮箱：fairytal2020@outlook.com，密码（请勿泄露）：fairytalbzfx2020\r\n3.我们的内部网站：https://fairytal2020.github.io/StorageVault1/，密码与邮箱相同\r\n4.其他信息请咨询梁伯颜\r\nFairytal团队，敬上");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton notok = new JButton("\u62D2\u7EDD");
        notok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LOGGER.info("Sending email...");
                MailUtils mail = new MailUtils("outlook.live.com" , "fairytal2020@outlook.com" , "fairytalbzfx2020");
                try {
                    mail.send("加入申请通知" , new String[]{sender} , new String[]{} , sender + "：\r\n十分抱歉，您的加入申请没有被批准。\r\nFairytal团队，敬上");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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
        LOGGER.info("Setting information...");
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
