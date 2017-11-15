
$(function() {
    $(".blog-menu .list-group-item").click(function() {

        var url = $(this).attr("url");
        // alert(url)
        $.ajax({
            url: url,
            success: function(data){
                // alert('5')
                $("#rightContainer").html(data);
                // alert(data)
                // console.log(1)
            },
            error : function() {
                alert("error");
            }
        });
    });
    //选中第一项
    $(".blog-menu .list-group-item:first").trigger("click");
});