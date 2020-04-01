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

package io.github.fairytal2020.test;

import io.github.fairytal2020.*;

public class Test {

    @org.junit.Test
    public void test(){
        System.out.println("Starting test");
        MailUtils mail = new MailUtils("" , "" , "");
        System.out.println("MailUtil test success");
        MailReader<MailJoinInApply> reader = new MailReader<>("" , "" , "" , "" , "" , MailJoinInApply.class);
        System.out.println("MailReader test success");
        MailSubject subject = new MailSubject("" , "");
        System.out.println("MailSubject test success");
        MailJsonReader json = new MailJsonReader();
        System.out.println("MailJsonReader test success");
        MainFrom mform = new MainFrom();
        System.out.println("MainFrom test success");
        ApplyFrom afrom = new ApplyFrom("" , "" , "" , "" , "");
        System.out.println("ApplyFrom test success");
        System.out.println("All test succeed");
    }
}
