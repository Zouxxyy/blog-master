var articleEditor;
// Tags Input
$('#articleTags').tagsInput({
    width: '100%',
    height: '38px',
    defaultText: '文章标签'
});

//Initialize Select2 Elements
$('.select2').select2()

$(function () {
    articleEditor = editormd("blog-editormd", {
        width: "100%",
        height: 640,
        syncScrolling: "single",
        path: "/admin/plugins/editormd/lib/",
        toolbarModes: 'full',
        onload: function (obj) { //上传成功之后的回调
        }
    });
});

$('#confirmButton').click(function () {
    var articleTitle = $('#articleName').val();
    var articleCategoryId = $('#articleCategoryId').val();
    var articleTags = $('#articleTags').val();
    var articleContent = articleEditor.getMarkdown();
    if (isNull(articleTitle)) {
        swal("请输入文章标题啊", {
            icon: "error",
        });
        return;
    }
    if (!validLength(articleTitle, 150)) {
        swal("标题过长", {
            icon: "error",
        });
        return;
    }
    if (isNull(articleCategoryId)) {
        swal("请选择文章分类", {
            icon: "error",
        });
        return;
    }
    if (isNull(articleTags)) {
        swal("请输入文章标签", {
            icon: "error",
        });
        return;
    }
    if (!validLength(articleTags, 150)) {
        swal("标签过长", {
            icon: "error",
        });
        return;
    }
    if (isNull(articleContent)) {
        swal("请输入文章内容", {
            icon: "error",
        });
        return;
    }
    if (!validLength(articleTags, 100000)) {
        swal("文章内容过长", {
            icon: "error",
        });
        return;
    }
    $('#articleModal').modal('show');
});

$('#saveButton').click(function () {
    var articleId = $('#articleId').val();
    var articleTitle = $('#articleName').val();
    var articleCategoryId = $('#articleCategoryId').val();
    var articleTags = $('#articleTags').val();
    var articleContent = articleEditor.getMarkdown();
    var articleStatus = $("input[name='articleStatus']:checked").val();
    var articleEnableComment = $("input[name='articleEnableComment']:checked").val();
    var url = '/admin/articles/save';
    var swlMessage = '保存成功';
    var data = {
        "articleTitle": articleTitle, "articleCategoryId": articleCategoryId,
        "articleTags": articleTags, "articleContent": articleContent, "articleStatus": articleStatus,
        "articleEnableComment": articleEnableComment
    };
    if (articleId > 0) {
        url = '/admin/articles/update';
        swlMessage = '修改成功';
        data = {
            "articleId": articleId,
            "articleTitle": articleTitle,
            "articleCategoryId": articleCategoryId,
            "articleTags": articleTags,
            "articleContent": articleContent,
            "articleStatus": articleStatus,
            "articleEnableComment": articleEnableComment
        };
    }
    console.log(data);
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        data: data,
        success: function (result) {
            if (result.resultCode == 200) {
                $('#articleModal').modal('hide');
                swal({
                    title: swlMessage,
                    type: 'success',
                    showCancelButton: false,
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: '返回博客列表',
                    confirmButtonClass: 'btn btn-success',
                    buttonsStyling: false
                }).then(function () {
                    window.location.href = "/admin/articles";
                })
            }
            else {
                $('#articleModal').modal('hide');
                swal(result.message, {
                    icon: "error",
                });
            }
            ;
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
        }
    });
});

$('#cancelButton').click(function () {
    window.location.href = "/admin/articles";
});
