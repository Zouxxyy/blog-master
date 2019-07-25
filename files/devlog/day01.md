# 第1天

完成登陆功能，和管理界面的熟悉

## 登陆功能

### 用户表

其实只要前面4个就行，后面3个是留着给以后设计博客使用的，如果要设计的出的话。。。

  `user_id` 
  
  `user_name` 
  
  `user_pass` 
  
  `user_nickname` 
  
  `user_signature` （签名）
  
  `user_email` 

  `user_profile` （个人简介）
  
 
### 设计
 
这个功能几乎以前学的每个教学视频都有，感觉是大同小异，没什么可说的。

```java
session.setAttribute("loginUser", user.getUserNickname());
session.setAttribute("loginUserId", user.getUserId());
```
把这些存入session中，用于后面设计的登陆检查，以及不时之需。这里我先不加登陆检查，而且把原作者的验证码功能去除，因为每次测试都要输入，实在麻烦。

在密码的设计上，采用了md5加密的方式

```java
String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
```

我们从数据库可以看到，密码为123456加密后长这样：e10adc3949ba59abbe56e057f20f883e

## 管理界面熟悉

由于前端不是我学习的重点，所以只要知道它啥意思就好。不过由于强迫症，我还是对它进行了修改。（其实写到后面会发现，知道啥意思根本不够，你要知道怎么改才行，不过用着用着就会了，所以不用担心...）

页面包括3个部分：header，sidebar 和 数据显示区域

![](https://user-gold-cdn.xitu.io/2019/7/25/16c28d86744a791d?w=2754&h=1666&f=png&s=344437)

- header 就是顶层的白条


- sidebar 可以看到 sidebar 有很多功能，通过它得到想要的功能（主页、博客、评论等等）。

- 数据显示区域就是展示各种功能的地方。其中主页会显示各种东西的统计信息。





