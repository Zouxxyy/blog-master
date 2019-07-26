# 第4天

接下来是文章功能

## 文章需求分析

到了博客最重要的部分了——文章方面，我们可以添加自己想要的需求，比如访问量，评论数，点赞数，是否允许评论等等

## 文章表设计

可以看到有`article_category_id`，没有标签信息，因为和标签的联系在文章标签表中

  `article_id` 
  
  `article_user_id` 
  
  `article_category_id` 
  
  `article_title` 

  `article_content`  
  
   `article_view_count` 
   
  `article_comment_count` 
  
  `article_like_count` 
  
  `article_status` （0 - 草稿 1 - 发布）

  `article_enable_comment` 
  
  `article_update_time`  
  
  `article_create_time` 

## 文章显示设计


### 查

分步查询变成了这样

```
<association property="articleCategory" column="article_category_id" select="com.zouxxyy.blog.core.dao.CategoryMapper.selectByPrimaryKey"/>
<association property="tagList" column="article_id" select="com.zouxxyy.blog.core.dao.TagMapper.getTagByAid"/>
```

还是好理解的，一个返回单一对象，一个列表对象。其中针对列表的jqGrid显示如下：

```java
{label: '标签', name: 'tagList', index: 'tagList', width: 80, formatter: function (tagList) {
    var ret = "";
        for (var i in tagList) {
            ret += tagList[i].tagName;
            ret += " ";
        }
        return ret;
    } },
```

还有一点，在文章实体中，按如下方式注释日期的属性，返回的json就会按照我们想要的格式返回。

```java
    // 返回json数据日期格式设定
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date articleUpdateTime;
```

### 增和改

增和改都前端发一串东西，后台接受，存入对象再写入数据库，只是改多了一个属性是文章ID，我们根据它的有无判断是增还是改。（这里多个标签是一个以逗号分隔的字符串传送的）

这里我将逻辑写到一个函数中

```java
    @Override
    public String saveArticle(Article article, String articleTags) {
        String[] tags = articleTags.split(",");
        // 原始标签集合
        Set<String> newTags = new HashSet<>(Arrays.asList(tags));
        if (tags.length > 6) {
            return "标签数量限制为6";
        }

        // 文章添加userID，暂时不添加，因为只有一个作者

        Set<String> deleteTags = new HashSet<>();
        Integer articleId = article.getArticleId();
        // 旧文章
        if(articleId != null) {

            // 根据文章id获得全部的标签名
            List<Tag> oldTagsList =  tagMapper.getTagByAid(articleId);
            for (Tag tag : oldTagsList) {
                if(newTags.contains(tag.getTagName())) {
                    newTags.remove(tag.getTagName());
                }
                else {
                    deleteTags.add(tag.getTagName());
                }
            }
            // 更新文章
            Article newArticle = articleMapper.selectByPrimaryKey(articleId);
            newArticle.setArticleUpdateTime(new Date());
            newArticle.setArticleTitle(article.getArticleTitle());
            newArticle.setArticleContent(article.getArticleContent());
            newArticle.setArticleStatus(article.getArticleStatus());
            newArticle.setArticleEnableComment(article.getArticleEnableComment());
            newArticle.setArticleCategoryId(article.getArticleCategoryId());
            articleMapper.updateByPrimaryKeySelective(newArticle);
        }
        else {
            // 添加新文章
            article.setArticleCreateTime(new Date());
            // useGeneratedKeys="true" 表示开启返回自增ID， keyProperty="articleId" 表示返回主键的名字。
            articleMapper.insertSelective(article);
            // articleId之前为空，此处得到增加后的id
            articleId = article.getArticleId();
        }

        for (String newTag : newTags) {
            // 当新标签集合中的标签不存在Tag表时，把它加入Tag表
            if(tagMapper.selectByTagName(newTag) == null) {
                Tag tag = new Tag();
                tag.setTagName(newTag);
                tagMapper.insertSelective(tag);
            }
            // 添加关系到关系表
            Tag tag = tagMapper.selectByTagName(newTag);
            ArticleTagRef articleTagRef = new ArticleTagRef();
            articleTagRef.setArticleId(articleId);
            articleTagRef.setTagId(tag.getTagId());
            articleTagRefMapper.insertSelective(articleTagRef);
        }

        // 在关系表中删除要删除的标签的关系
        for (String deleteTag : deleteTags) {
            Tag tag = tagMapper.selectByTagName(deleteTag);
            ArticleTagRef articleTagRef = new ArticleTagRef();
            articleTagRef.setTagId(tag.getTagId());
            articleTagRef.setArticleId(articleId);
            articleTagRefMapper.deleteByPrimaryKey(articleTagRef);
        }
        return "SUCCESS";
    }
```

- 难点一：这里前端发过来的分类必须是已经存在的，而标签可以是存在的也可以是不存在的。所以处理新旧标签时就比较复杂。这里我建了两个集合，新加的标签（标签关系表中不存着的），和旧标签集合。

- 难点二：新加的文章后，我们添加文章关系表需要用到文章id，那么我们怎么获得新加文章的id呢？用mybatis的返回自增id功能，如下

`ArticleMapper.xml`中

```
  <!--注意useGeneratedKeys="true" keyProperty="articleId"-->
  <insert id="insertSelective" useGeneratedKeys="true" keyProperty="articleId"
          parameterType="com.zouxxyy.blog.core.entity.Article" >
  ...
```

`ArticleServiceImpl.java`中


```java
            article.setArticleCreateTime(new Date());
            // 在xml中：useGeneratedKeys="true" 表示开启返回自增ID， keyProperty="articleId" 表示返回主键的名字。
            articleMapper.insertSelective(article);
            // articleId之前为空，此处得到增加后的id
            articleId = article.getArticleId();
```

### 删

删除文章和删除标签一样，不仅要删除文章还要删除关系表

```java
// 删除article表
articleMapper.deleteByPrimaryKey(articleId);
// 删除关系表
articleTagRefMapper.deleteByArticleID(articleId);
```


## 界面展示

- 写文章界面

这个md输入框感觉很高大上，不过我们不用关心怎么实现的，我们只需要把它看成一个大点的表单就ok

![](https://user-gold-cdn.xitu.io/2019/7/26/16c2c00aea91a71f?w=2952&h=1770&f=png&s=246042)


- 文章列表界面

![](https://user-gold-cdn.xitu.io/2019/7/26/16c2c0126693ae8e?w=2956&h=1764&f=png&s=27586)