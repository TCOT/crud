$(function() {
    //模糊搜索
    $("#searchNameBtn").click(function() {
        var text = $('#searchName').val();
        // alert(text);
        $.ajax({
                url:"/users/" + text +'/search',
                success:function(data){
                    // alert(2)
                    $("#mainContainerRepleace").html(data);
                },
                error:function(data){
                    alert("error")
                }
            })
    });

    //编辑
    $("#rightContainer").on("click",".blog-edit-user", function () {
        $.ajax({
            url: "/users/" + $(this).attr("userId") +"/edit",
            success: function(data){
                $("#userFormContainer").html(data);
            },
            error : function() {
                toastr.error("error!");
            }
        });
    });

    //增加
    $("#addUser").click(function() {
        $.ajax({
            url: "/users/add",
            success: function(data){
                $("#userFormContainer").html(data);
            },
            error : function(data) {
                toastr.error("error!");
            }
        });
    });

    $("#submitEdit").click(function() {
        // alert('进入提交')
        $.ajax({
            url: "/users",
            type: 'POST',
            data:$('#userForm').serialize(),
            success: function(data){
                $('#userForm')[0].reset();
                if (data.success) {
                    // 从新刷新主界面
                       $.ajax({
                           url: "/users/flush",
                           success: function(data){
                               $("#mainContainer").html(data);
                           },
                           error : function() {
                               alert("error");
                           }
                       });
                } else {
                    toastr.error(data.message);
                }

            },
            error : function() {
                toastr.error("error!");
            }
        });
    });

    //删除
    // alert(1)
    $("#mainContainer").on("click",".blog-delete-user", function () {
        $.ajax({
            url: "/users/" + $(this).attr("userId") ,
            type: 'DELETE',
            success: function(data){
                if (data.success) {
                    $.ajax({
                        url: "/users/flush",
                        success: function(data){
                            // console.log(1)
                            $("#mainContainer").html(data);
                        },
                        error : function() {
                            alert("error");
                        }
                    });
                } else {
                    toastr.error(data.message);
                }
            },
            error : function() {
                toastr.error("error!");
            }
        });
    });
});