+# simple-cal
 +my android homework only a simple calculator

一款简单小巧便捷的安卓计算器

- 支持四则运算，百分号%和多层括号
- 支持复制粘贴
- 支持任意位置插入数据
- 可保存历史记录，支持单击复制内容及一键清空
- “计算器”界面和“历史记录”界面左右滑屏即可切换

#v2.2.1.160228
- 修复bug：只有滑到历史记录界面后的计算才被记录下来

#v2.2.1.150818
- 修改了获取list item字符串的代码

#v2.2.1.150809
- 修改文本，添加至strings.xml

#v2.2.1.150728
- 修复bug：括号输入不准确

#v2.2.0.150725
##更新：
- 1.“历史记录”单击item可复制内容到剪贴板，单击item 文字和背景变色
- 2.“历史记录”增加一键“清空”功能
- 3.“历史记录”标题栏增加底部阴影（layer-list，selector）

##修复：
- 1.“计算器”界面边界不对称问题
- 2.“计算器”界面按钮“触摸变色”与“滑屏触摸”冲突问题（将java代码onTounchListener实现改为用selector实现）

#v2.1.0.150723
##更新：
- 1.历史记录改为ListView显示，支持退出时自动存储历史记录至内存

#v2.0.0.150719
##更新：
- 1.新增历史记录(暂使用TextView，支持滚动条显示，暂不支持存在本地)
- 2.将计算器界面和历史记录界面封装成Layout类，用ViewPager左右滑屏选择显示，默认显示计算器界面

##修复bug：
- 1.修复了判别负数不准确的bug，更改识别操作数的正则表达
- 2.完善了对于计算器界面的EditText“隐藏输入法，显示光标”的功能

#v1.0.0.150716
- 可进行四则运算，支持百分号%和多层括号，支持复制粘贴
