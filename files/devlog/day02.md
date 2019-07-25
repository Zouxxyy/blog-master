# 第2天

真正开始设计管理页面了，先从软柿子开始吧，就是分类功能。

## 分类需求分析

我开始一个功能，喜欢从建表开始，而建表又要首先考虑实际需求，所以先思考想要的功能。博客为了方便管理，它都会有个分类，然后为了更加有个性，人们有会加一些标签。

所以，我这样设计：

- 一篇文章**只能有而且必须要有**个分类(一对一)
- 一篇文章可以有**多个**标签，也可以**没有**(一对多)

当然了，也可以设计一篇文章有多个分类，但一想，那这分类和标签有啥区别啊...所以为了表示不同，咱就这么定了！

## 分类表设计

由于是简单的一对一，所以就：

`category_id` 

`category_name` 

这软柿子够简单吧。。。

## 数据显示设计

页面中最核心也最大的那一块，也就是增删改查

### 查

在显示区域，会显示分类的名字，和该分类文章的数量，且分页展示。我是第一次弄前端分页。。。具体谈谈这里的实现：

- 前端发送一个map给后台：page(第几页) limit(每页个数)

- 后台根据它俩在数据库中找这些分类对象，我起了一个很挫的名字，看名字应该知道是干嘛的

```java
List<Category> categoryList = categoryMapper.getCategoryByStartAndLimit((page - 1) * limit, limit);
```

后台将找到的**list**，还有**全部个数count**(不发这个，人家前端怎么知道可以请求多少页，对不) 和 一些附带的通知信息，一起包装成Result对象（定义的一个工具类，以后都传它）传给前端

- 前端拿到后，把json数据读取出来，用一个jqGrid来显示我们的分类数组（在category.js里）虽然没学过，但仔细看看应该知道差不多就是那个意思。

再解释下**怎么得到分类的数量**

从建的表我们可以发现里面并没有文章个数这一栏，那咋办? 划重点！我通过mybatis的**分步查询**查到它，并封抓装到category的categoryArticleCount属性中。

```
<association property="categoryArticleCount" column="category_id" select="com.zouxxyy.blog.core.dao.ArticleMapper.getArticleCountByCid"/>
```



### 增

在按钮上有增加，修改，删除，先介绍增加：

- 前端选择要改的分类，填好新名字后，前端用post请求，发送给后台
- 后台**新建个对象**，写入属性，用mapper一加就行

```java
Category category = new Category();
category.setCategoryName(categoryName);
return categoryMapper.insertSelective(category) > 0;
```
insertSelective这个mapper是我用逆向工程生成的，它会自动判断category中存在的属性，并把它加入数据库中，很方便有木有。

### 改

```java
Category category = categoryMapper.selectByPrimaryKey(categoryId);
category.setCategoryName(categoryName);
return categoryMapper.updateByPrimaryKeySelective(category) > 0;
```
和增加有点不同，先用mapper**读取出对象**，设置新属性，再用updateByPrimaryKeySelective对数据库进行更新。

### 删
简单的批量删除，前端发key（一个数组），后台根据它删除。但要注意：**由于一个文章必须有一个分类，所以删除有博客的分类是不允许的**

```java
Integer count = articleMapper.getArticleCountByCid(id);
if(count > 0)
	return "不能删除含有文章的分类";
```

全部功能设计完后，长这样，是有点寒酸，不过我们想要的功能都有：

![](https://user-gold-cdn.xitu.io/2019/7/25/16c290cceac6006f?w=2710&h=1670&f=png&s=201814)



## 捋一捋
- 流程方面：controller 截获请求，调用service，service再调用更低层的mapper。由于我们功能比较简单，所以service层看上去感觉可有可无。

- 文件方面：对象存在entity中，mapper存在dao中，xml在mapper中，controller在controler中，service中servie中，工具在util中。虽然东西多了点，但springmvc果然井井有条。

- 前后配合：前后端基本都是上面的增删改查的流程，只要这个分类功能做出来，会发现后面都是依葫芦画瓢。 

ok，开始下一个功能标签的设计吧。

