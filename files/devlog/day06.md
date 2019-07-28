# 第6天

接下来是日志功能

## 日志需求分析

日志功能用于记录操作的时间，如记录何时添加修改文章，何时修改密码等等。


## 日志表设计

  `id` 
  
  `time`  


  `type` （类型，如修改文章，添加分类等等）

  `detail`  （细节：如修改的是哪篇文章）


  `ip`


## 日志显示设计


只有增、查、删操作，查和删操作较简单。


### 增

采用了比较笨的方法，就是在每个其他表的增删改操作前，添加一个加log的操作，把操作类型和操作细节、时间、ip记录即可。

这个过程仔细一点，确保每个地方都添加了就行。

如：

```java
    /**
     * 分类添加
     */
    @RequestMapping(value = "/categories/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestParam("categoryName") String categoryName) {
        if (StringUtils.isEmpty(categoryName)) {
            return new Result<>(500, "请输入分类名称！", null);
        }
        if (categoryService.saveCategory(categoryName)) {
            logService.addLog("添加分类", categoryName);
            return new Result<>(200, "SUCCESS", null);
        } else {
            return new Result<>(500, "分类名称重复", null);
        }
    }
```

## 界面展示

![日志](../image/log.png)
