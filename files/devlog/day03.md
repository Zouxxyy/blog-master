# 第3天

接下来是标签功能设计

## 标签需求分析

前面说到：一篇文章可以有**多个**标签，也可以**没有**(一对多)

所以这是一个一对多。我们就要多设计一个关系表

## 标签表设计

`tag_id` 

`tag_name`
 
和分类表一毛一样，但还有个关联表 `article_tag_ref`

`article_id` 
  
`tag_id` 

有了它，想怎么查怎么查

## 数据显示设计

增加与修改和分类的设计一毛一样

### 查

分步查询变成了这样

```
<association property="tagArticleCount" column="tag_id" select="com.zouxxyy.blog.core.dao.ArticleTagRefMapper.getArticleCountByTid"/>
```

- 因为文章和标签的关系只在关系表中，所以自然用**关系表的mapper**。
- 之前我们分类是用**文章的mapper**是因为一个文章必须有一个分类，所以文章中包含了分类id。就是它将文章和分类联系起来。。。

### 删

由于文章可以没标签，所以删除标签时，不考虑有没有文章含有该标签。删标签，那些文章就不带该标签了。你会发现进行了两步删除操作：删关系表，删标签

```java
// 删除文章标签关系表中的数据
articleTagRefMapper.deleteByTagIds(ids);

// 删除标签数据
return tagMapper.deleteTagByIds(ids) > 0;
```

设计完后标签界面长这样

![](https://user-gold-cdn.xitu.io/2019/7/25/16c29466dbe1ad8d?w=2704&h=1602&f=png&s=293257)