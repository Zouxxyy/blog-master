# 第4天

接下来是评论功能

## 评论需求分析

除了评论，我们当然希望有回复功能，而且支持一条评论多个回复。所以就准备设计了一个`comment_pid` 来确定评论与回复的关联。（目前仅设计管理员可以回复评论）


## 评论表设计

评论和回复本质一样，就不多设计表了。用`comment_status`区分


`comment_id` 

`comment_pid` 

 `comment_article_id`

  `comment_author_name`

 `comment_author_email` 

  `comment_author_ip` 

  `comment_content` 

  `comment_create_time` 

  `comment_status` （0 - 未审核的评论 1 - 已审核的评论 2 - 管理员回复）
  

  `comment_like_count` （评论的点赞数）


## 评论显示设计


### 查

由于评论和回复所需要的数据有点不同，所以mybatis中的resultMap设计了两个

- 评论Map


```
  <resultMap id="BaseResultMap" type="com.zouxxyy.blog.core.entity.Comment" >
    <id column="comment_id" property="commentId" jdbcType="BIGINT" />
    <result column="comment_pid" property="commentPid" jdbcType="BIGINT" />
    <result column="comment_article_id" property="commentArticleId" jdbcType="BIGINT" />
    <result column="comment_author_name" property="commentAuthorName" jdbcType="VARCHAR" />
    <result column="comment_author_email" property="commentAuthorEmail" jdbcType="VARCHAR" />
    <result column="comment_author_ip" property="commentAuthorIp" jdbcType="VARCHAR" />
    <result column="comment_content" property="commentContent" jdbcType="VARCHAR" />
    <result column="comment_create_time" property="commentCreateTime" jdbcType="TIMESTAMP" />
    <result column="comment_status" property="commentStatus" jdbcType="TINYINT" />
    <result column="comment_like_count" property="commentLikeCount" jdbcType="INTEGER" />
  </resultMap>

  <resultMap id="CommentResultMap" type="com.zouxxyy.blog.core.entity.Comment" extends="BaseResultMap">
    <association property="commentArticleTitle" column="comment_article_id" select="com.zouxxyy.blog.core.dao.ArticleMapper.getArticleTitleByAid"/>
  </resultMap>
```

- 回复Map

在该map中，因为是我们自己的回复，所以省去了作者信息。另外添加了`commentPContent`也就是该回复对应的评论，通过`comment_pid`分步查询得到

```
  <resultMap id="ReplyResultMap" type="com.zouxxyy.blog.core.entity.Comment" >
    <id column="comment_id" property="commentId" jdbcType="BIGINT" />
    <result column="comment_pid" property="commentPid" jdbcType="BIGINT" />
    <result column="comment_article_id" property="commentArticleId" jdbcType="BIGINT" />
    <result column="comment_content" property="commentContent" jdbcType="VARCHAR" />
    <result column="comment_create_time" property="commentCreateTime" jdbcType="TIMESTAMP" />
    <association property="commentArticleTitle" column="comment_article_id" select="com.zouxxyy.blog.core.dao.ArticleMapper.getArticleTitleByAid"/>
    <association property="commentPContent" column="comment_pid" select="com.zouxxyy.blog.core.dao.CommentMapper.getCommentContentByCommentId"/>
  </resultMap>
```


### 增和改

- 评论不支持增加和修改，但支持审核。审核就是把`comment_status` 由 0 变 1 

- 回复支持增加修改。这俩都很简单，没啥说的。



### 删

也就是根据id数组批量删除


## 界面展示

- 评论列表界面

![](https://user-gold-cdn.xitu.io/2019/7/26/16c2c139cd06798d?w=2928&h=1770&f=png&s=252437)

- 回复列表界面

![](https://user-gold-cdn.xitu.io/2019/7/26/16c2c143789d0303?w=3026&h=1762&f=png&s=291239)